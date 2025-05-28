package com.spaceX.spaceX.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
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

    @Autowired
    private Cloudinary cloudinary;

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
            // Converte o MultipartFile para um Map que o Cloudinary pode usar
            Map uploadResult = cloudinary.uploader().upload(imagem.getBytes(), ObjectUtils.emptyMap());
            
            // Retorna a URL da imagem do Cloudinary
            return ResponseEntity.ok(uploadResult.get("url").toString());
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Erro ao salvar imagem: " + e.getMessage());
        }
    }
}
