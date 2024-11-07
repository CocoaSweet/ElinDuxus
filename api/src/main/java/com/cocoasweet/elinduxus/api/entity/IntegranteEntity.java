package com.cocoasweet.elinduxus.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "integrantes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IntegranteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_integrante")
	private long id;
	
	@Column(nullable = false,  name = "franquia")
	private String franquia;
	
	@Column(nullable = false,  name = "nome")
	private String nome;
	
	@Column(nullable = false , name = "funcao")
	private String funcao;
	
	
}
