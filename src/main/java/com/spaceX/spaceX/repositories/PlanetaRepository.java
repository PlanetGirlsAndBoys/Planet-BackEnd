package com.spaceX.spaceX.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spaceX.spaceX.entities.Planeta;

@Repository
public interface PlanetaRepository extends JpaRepository<Planeta, Long>{

	Planeta findByNomePlaneta(String nomePlaneta);
	
}
