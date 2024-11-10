package com.cocoasweet.elinduxus.api.dto;

import org.springframework.beans.BeanUtils;
import com.cocoasweet.elinduxus.api.entity.IntegranteEntity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class RequestIntegranteDTO {

	private long id;
	private String franquia;
	private String nome;
	private String funcao;


	public RequestIntegranteDTO(IntegranteEntity integrante) {
		BeanUtils.copyProperties(integrante, this);
	}


	@Override
	public String toString() {
		return "[franquia=" + franquia + ", nome=" + nome + ", funcao=" + funcao+ "]";
	}
	
	
	
}
