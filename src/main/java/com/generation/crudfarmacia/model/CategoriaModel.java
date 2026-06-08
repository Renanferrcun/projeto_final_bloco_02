package com.generation.crudfarmacia.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_categoria")
public class CategoriaModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O Atributo Classe é obrigatório")
	private String classe;
	
	@NotBlank(message = "O Atributo Principio Ativo é obrigatório")
	private String principioAtivo;
	
	@NotBlank(message = "O Atributo Laboratório é obrigatório")
	private String laboratorio;
	
	@NotBlank(message = "O Atributo Tarja é obrigatório")
	private String tarja;
	
	@NotBlank(message = "O Atributo Descrição é obrigatório")
	private String tipo;
	
	@NotNull(message = "O Atributo Requer Receita é obrigatório")
	private Boolean requerReceita;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "categoria", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("categoria")
	private List<ProdutoModel> produto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public String getPrincipioAtivo() {
		return principioAtivo;
	}

	public void setPrincipioAtivo(String principioAtivo) {
		this.principioAtivo = principioAtivo;
	}

	public String getLaboratorio() {
		return laboratorio;
	}

	public void setLaboratorio(String laboratorio) {
		this.laboratorio = laboratorio;
	}

	public String getTarja() {
		return tarja;
	}

	public void setTarja(String tarja) {
		this.tarja = tarja;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Boolean getRequerReceita() {
		return requerReceita;
	}

	public void setRequerReceita(Boolean requerReceita) {
		this.requerReceita = requerReceita;
	}

	public java.util.List<ProdutoModel> getProduto() {
		return produto;
	}

	public void setProduto(java.util.List<ProdutoModel> produto) {
		this.produto = produto;
	} 

}
