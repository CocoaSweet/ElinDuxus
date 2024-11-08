package com.cocoasweet.elinduxus.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "composicao_time")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ComposicaoTimeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_composicao_time")
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "id_time")
	private TimeEntity time = new TimeEntity();

	@ManyToOne
	@JoinColumn(name = "id_integrante")
	private IntegranteEntity integrante = new IntegranteEntity();

	public ComposicaoTimeEntity(Long idTime, Long idIntegrante) {
		this.time.setId(idTime);
		this.integrante.setId(idIntegrante);
	}
	
}
