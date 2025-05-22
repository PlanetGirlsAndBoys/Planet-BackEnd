package com.spaceX.spaceX.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Planeta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nomePlaneta;
	private String imgUrl;

	@Column(length = 1000)
	private String descricao;

	public Planeta() {
	}

	public Planeta(Long id, String nomePlaneta, String imgUrl, String descricao) {
		this.id = id;
		this.nomePlaneta = nomePlaneta;
		this.imgUrl = imgUrl;
		this.descricao = descricao;
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
