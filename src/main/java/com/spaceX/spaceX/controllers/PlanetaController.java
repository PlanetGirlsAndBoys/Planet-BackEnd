package com.spaceX.spaceX.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import com.spaceX.spaceX.dtos.PlanetaDTO;
import com.spaceX.spaceX.services.PlanetaService;

@RestController
@RequestMapping("/planetas")
public class PlanetaController {

    @Autowired
    private PlanetaService planetaService;

    @GetMapping
    public ResponseEntity<List<PlanetaDTO>> listarTodos() {
        List<PlanetaDTO> planetas = planetaService.listarTodos();
        return ResponseEntity.ok().body(planetas);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PlanetaDTO> buscarPorId(@PathVariable Long id) {
        PlanetaDTO planeta = planetaService.buscarPorId(id);
        return ResponseEntity.ok().body(planeta);
    }

    @PostMapping
    public ResponseEntity<PlanetaDTO> criarPlaneta(@RequestBody PlanetaDTO planetaDTO) {
        PlanetaDTO planetaSalvo = planetaService.criarPlaneta(planetaDTO);
        return ResponseEntity.ok().body(planetaSalvo);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PlanetaDTO> atualizarPlaneta(@PathVariable Long id, @RequestBody PlanetaDTO planetaDTO) {
        PlanetaDTO planetaAtualizado = planetaService.atualizarPlaneta(id, planetaDTO);
        return ResponseEntity.ok().body(planetaAtualizado);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletarPlaneta(@PathVariable Long id) {
        planetaService.deletarPlaneta(id);
        return ResponseEntity.ok().build();
    }
}
