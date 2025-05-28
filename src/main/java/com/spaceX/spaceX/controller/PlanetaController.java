package com.spaceX.spaceX.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.spaceX.spaceX.entities.Planeta;
import com.spaceX.spaceX.services.PlanetaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/planetas")
@Tag(name = "Planetas", description = "Operações relacionadas aos planetas")
public class PlanetaController {

    @Autowired
    private PlanetaService planetaService;

    @Operation(summary = "Criar um novo planeta")
    @PostMapping("/criar")
    public ResponseEntity<Planeta> criar(@RequestBody Planeta planeta) throws Exception {
        Planeta salvo = planetaService.salvar(planeta);
        return ResponseEntity.ok(salvo);
    }

    @Operation(summary = "Listar todos os planetas")
    @GetMapping
    public ResponseEntity<List<Planeta>> listarTodos() {
        return ResponseEntity.ok(planetaService.listarTodos());
    }

    @Operation(summary = "Buscar planeta por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Planeta> buscarPorId(@PathVariable Long id) {
        return planetaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Atualizar um planeta existente")
    @PutMapping("/{id}")
    public ResponseEntity<Planeta> atualizar(@PathVariable Long id, @RequestBody Planeta planeta) {
        try {
            Planeta atualizado = planetaService.atualizar(id, planeta);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Deletar um planeta")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            planetaService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Upload de imagem")
    @PostMapping("/imagem")
    public ResponseEntity<String> uploadImagem(@RequestParam("imagem") MultipartFile imagem) {
        try {
            // Cria pasta se não existir
            File pasta = new File("imagens");
            if (!pasta.exists()) {
                pasta.mkdir();
            }

            // Salva a imagem
            String nomeArquivo = imagem.getOriginalFilename();
            File arquivo = new File(pasta, nomeArquivo);
            imagem.transferTo(arquivo);

            // Retorna a URL da imagem
            return ResponseEntity.ok("http://localhost:8081/imagens/" + nomeArquivo);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Erro ao salvar imagem: " + e.getMessage());
        }
    }
}
