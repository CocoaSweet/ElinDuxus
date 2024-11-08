package com.cocoasweet.elinduxus.api.service;

import java.util.List;
import com.cocoasweet.elinduxus.api.dto.IntegranteDTO;
import com.cocoasweet.elinduxus.api.dto.RequestComposicaoTimeDTO;
import com.cocoasweet.elinduxus.api.dto.RequestIntegranteDTO;

public interface Integrante {

	Long saveIntegrante(IntegranteDTO integrante);
	List<RequestIntegranteDTO> extrairIntegrante(RequestComposicaoTimeDTO composicaoTime);
	List<String> extrairNomeIntegrante(List<RequestIntegranteDTO> integrantes);
}
