package com.spaceX.spaceX.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spaceX.spaceX.entities.Planeta;
import com.spaceX.spaceX.dtos.PlanetaDTO;
import com.spaceX.spaceX.repositories.PlanetaRepository;

@Service
public class PlanetaService {
	
	@Autowired
	private PlanetaRepository planetaRepo;
	
	public List<PlanetaDTO> listarTodos() {
		List<Planeta> planetas = planetaRepo.findAll();
		
		if (planetas.isEmpty()) {
			throw new RuntimeException("Nenhum planeta encontrado");
		}
		
		return planetas.stream()
				.map(this::converterParaDTO)
				.collect(Collectors.toList());
	}
	
	public PlanetaDTO buscarPorId(Long id) {
		Optional<Planeta> planeta = planetaRepo.findById(id);
		
		return planeta.map(this::converterParaDTO)
				.orElseThrow(() -> new RuntimeException("Planeta não encontrado"));
	}
	
	public PlanetaDTO criarPlaneta(PlanetaDTO planetaDTO) {
		Planeta planeta = converterParaEntidade(planetaDTO);
		Planeta planetaSalvo = planetaRepo.save(planeta);
		return converterParaDTO(planetaSalvo);
	}
	
	public PlanetaDTO atualizarPlaneta(Long id, PlanetaDTO planetaDTO) {
		Optional<Planeta> planetaExistente = planetaRepo.findById(id);
		
		if (planetaExistente.isPresent()) {
			Planeta planeta = planetaExistente.get();
			planeta.setNomePlaneta(planetaDTO.getNomePlaneta());
			// Adicione outros campos para atualização
			
			Planeta planetaAtualizado = planetaRepo.save(planeta);
			return converterParaDTO(planetaAtualizado);
		} else {
			throw new RuntimeException("Planeta não encontrado para atualização");
		}
	}
	
	public void deletarPlaneta(Long id) {
		if (!planetaRepo.existsById(id)) {
			throw new RuntimeException("Planeta não encontrado para deleção");
		}
		planetaRepo.deleteById(id);
	}
	
	public PlanetaDTO converterParaDTO(Planeta planeta) {
		PlanetaDTO dto = new PlanetaDTO();
		dto.setId(planeta.getId());
		dto.setNomePlaneta(planeta.getNomePlaneta());
		dto.setImgUrl(planeta.getImgUrl());
		dto.setDescricao(planeta.getDescricao());
		return dto;
	}
	
	public Planeta converterParaEntidade(PlanetaDTO dto) {
		Planeta planeta = new Planeta();
		planeta.setNomePlaneta(dto.getNomePlaneta());
		planeta.setImgUrl(dto.getImgUrl());
		planeta.setDescricao(dto.getDescricao());
		return planeta;
	}
}
