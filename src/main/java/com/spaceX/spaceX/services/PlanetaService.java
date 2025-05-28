package com.spaceX.spaceX.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spaceX.spaceX.entities.Planeta;
import com.spaceX.spaceX.repositories.PlanetaRepository;

import jakarta.persistence.EntityNotFoundException;

// Indica que esta classe é um "Service" do Spring, responsável por conter a lógica de negócio.
@Service
public class PlanetaService {
	
    // Injeta automaticamente uma instância de PlanetaRepository
	@Autowired
	private PlanetaRepository planetaRepository;

    // Método para salvar um novo planeta no banco de dados
	@Transactional
	public Planeta salvar(Planeta planeta) throws Exception {
		if (planeta.getNomePlaneta() == null || planeta.getNomePlaneta().trim().isEmpty()) {
			throw new Exception("Nome do planeta é obrigatório");
		}
		
		Planeta existente = planetaRepository.findByNomePlaneta(planeta.getNomePlaneta());
		if(existente != null) {
			throw new Exception("Esse planeta já está cadastrado :(");
		}
		
		return planetaRepository.save(planeta);
	}

    // Método que retorna todos os planetas cadastrados no banco
    public List<Planeta> listarTodos() {
        return planetaRepository.findAll(); // Retorna uma lista com todos os planetas
    }

    // Método para buscar um planeta pelo seu ID
    public Optional<Planeta> buscarPorId(Long id) {
        return planetaRepository.findById(id); // Retorna um Optional, que pode ou não conter um planeta
    }

    // Método para atualizar os dados de um planeta existente
    @Transactional
    public Planeta atualizar(Long id, Planeta novoPlaneta) {
        return planetaRepository.findById(id) // Busca o planeta pelo ID
            .map(planeta -> {
                // Se encontrado, atualiza os campos
                planeta.setNomePlaneta(novoPlaneta.getNomePlaneta());
                planeta.setImgUrl(novoPlaneta.getImgUrl());
                planeta.setDescricao(novoPlaneta.getDescricao());
                return planetaRepository.save(planeta); // Salva as alterações
            })
            .orElseThrow(() -> new RuntimeException("Planeta não encontrado com id: " + id)); 
            // Se não encontrado, lança exceção
    }

    // Método para deletar um planeta pelo ID
    @Transactional
    public void deletar(Long id) {
        if (!planetaRepository.existsById(id)) {
            // Se o planeta não existir, lança exceção
            throw new RuntimeException("Planeta não encontrado com id: " + id);
        }
        planetaRepository.deleteById(id); // Deleta o planeta pelo ID
    }
}
