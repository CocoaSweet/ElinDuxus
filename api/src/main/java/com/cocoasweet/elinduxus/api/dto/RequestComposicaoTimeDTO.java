package com.cocoasweet.elinduxus.api.dto;

import org.springframework.beans.BeanUtils;

import com.cocoasweet.elinduxus.api.entity.ComposicaoTimeEntity;
import com.cocoasweet.elinduxus.api.entity.IntegranteEntity;
import com.cocoasweet.elinduxus.api.entity.TimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestComposicaoTimeDTO {
	
	private long id;
	private TimeEntity time;
	private IntegranteEntity integrante;
	
	public RequestComposicaoTimeDTO(ComposicaoTimeEntity composicaoTime) {
		BeanUtils.copyProperties(composicaoTime, this);
	}

}
