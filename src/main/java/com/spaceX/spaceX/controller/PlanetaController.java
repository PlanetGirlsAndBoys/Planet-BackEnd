package com.spaceX.spaceX.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.spaceX.spaceX.entities.Planeta;
import com.spaceX.spaceX.services.PlanetaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

@RestController
@RequestMapping("/planetas")
@Tag(name = "Planetas", description = "Operações relacionadas aos planetas")
public class PlanetaController {

    @Autowired
    private PlanetaService planetaService;

    @Operation(summary = "Upload de imagem")
    @PostMapping("/imagem")
    public ResponseEntity<String> uploadImagem(@RequestParam("imagem") MultipartFile imagem) {
        try {
            if (imagem == null || imagem.isEmpty()) {
                return ResponseEntity.badRequest().body("Nenhuma imagem foi enviada");
            }

            // Verifica o tipo do arquivo
            String contentType = imagem.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                return ResponseEntity.badRequest().body("O arquivo enviado não é uma imagem válida");
            }

            // Verifica o tamanho do arquivo (máximo 5MB)
            if (imagem.getSize() > 5 * 1024 * 1024) {
                return ResponseEntity.badRequest().body("A imagem deve ter no máximo 5MB");
            }

            // Converte a imagem para base64
            byte[] imageBytes = imagem.getBytes();
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
            
            // Adiciona o prefixo data:image para exibição no frontend
            String imageData = "data:" + contentType + ";base64," + base64Image;

            return ResponseEntity.ok(imageData);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Erro ao processar a imagem: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Erro inesperado: " + e.getMessage());
        }
    }

    @Operation(summary = "Criar um novo planeta")
    @PostMapping("/criar")
    public ResponseEntity<?> criar(@RequestBody Planeta planeta) {
        try {
            if (planeta.getNomePlaneta() == null || planeta.getNomePlaneta().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Nome do planeta é obrigatório");
            }

            if (planeta.getImgUrl() != null && planeta.getImgUrl().length() > 16777215) { // Tamanho máximo do MEDIUMTEXT
                return ResponseEntity.badRequest().body("Imagem muito grande. Por favor, use uma imagem menor.");
            }

            Planeta salvo = planetaService.salvar(planeta);
            return ResponseEntity.ok(salvo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Erro ao salvar planeta: " + e.getMessage());
        }
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
}
