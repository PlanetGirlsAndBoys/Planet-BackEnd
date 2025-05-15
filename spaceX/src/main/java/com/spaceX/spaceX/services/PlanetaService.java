package com.spaceX.spaceX.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spaceX.spaceX.entities.Planeta;
import com.spaceX.spaceX.repositories.PlanetaRepository;

@Service
public class PlanetaService {
	
	@Autowired
	private PlanetaRepository planetaRepo;
	
	private List<Planeta> listarTodos(){
		return planetaRepo.findAll();
	}

}
