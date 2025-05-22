package com.spaceX.spaceX.dtos;

import com.spaceX.spaceX.entities.Planeta;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class PlanetaDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nomePlaneta;
	private String imgUrl;
	private String descricao;

	public PlanetaDTO() {
	}

	public PlanetaDTO(Long id, String nomePlaneta, String imgUrl, String descricao) {
		this.id = id;
		this.nomePlaneta = nomePlaneta;
		this.imgUrl = imgUrl;
		this.descricao = descricao;
	}

	
	public PlanetaDTO(Planeta entidade) {
		id = entidade.getId();
		nomePlaneta = entidade.getNomePlaneta();
		imgUrl = entidade.getImgUrl();
		descricao = entidade.getDescricao();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomePlaneta() {
		return nomePlaneta;
	}

	public void setNomePlaneta(String nomePlaneta) {
		this.nomePlaneta = nomePlaneta;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
