package com.cocoasweet.elinduxus.api.service.impl;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cocoasweet.elinduxus.api.dto.IntegranteDTO;
import com.cocoasweet.elinduxus.api.dto.RequestComposicaoTimeDTO;
import com.cocoasweet.elinduxus.api.dto.RequestIntegranteDTO;
import com.cocoasweet.elinduxus.api.entity.IntegranteEntity;
import com.cocoasweet.elinduxus.api.repository.IntegranteRepository;
import com.cocoasweet.elinduxus.api.service.Integrante;

@Service
public class IntegranteImpl implements Integrante{

	@Autowired
	private IntegranteRepository integranteRepository;
	
	@Override
	public Long saveIntegrante(IntegranteDTO integrante) {
		IntegranteEntity integranteEntity = new IntegranteEntity(integrante);
		integranteRepository.save(integranteEntity);
		return integranteEntity.getId();
	}
	
	public List<RequestIntegranteDTO> extrairIntegrante(RequestComposicaoTimeDTO composicaoTime) {
		List<IntegranteEntity> integrantes = Arrays.asList(composicaoTime.getIntegrante());
		return integrantes.stream().map(RequestIntegranteDTO::new).toList();
	}
	
	public List<String> extrairNomeIntegrante(List<RequestIntegranteDTO> integrantes){
		return integrantes.stream().map(RequestIntegranteDTO::getNome).toList();
	}

}