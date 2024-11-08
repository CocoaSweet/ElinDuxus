package com.cocoasweet.elinduxus.api.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cocoasweet.elinduxus.api.dto.RequestComposicaoTimeDTO;
import com.cocoasweet.elinduxus.api.dto.RequestIntegranteDTO;

@Service
public class ApiService {
	
	@Autowired
	private ComposicaoTimeImpl composicaoTime;
	@Autowired
	private TimeImpl time;
	@Autowired
	private IntegranteImpl integrante;
	    /**
	     * Vai retornar uma lista com os nomes dos integrantes do time daquela data
	     */
	    public List<String> timeDaData(LocalDate data){
	    	List<Long> ids = time.procurarIdPorData(data);
	    	List<String> nomeIntegrantes = new ArrayList<String>();
	    	for(Long id: ids) {
	    		List<RequestComposicaoTimeDTO> compTime = composicaoTime.findCompByTimeId(id);
	    		for(RequestComposicaoTimeDTO compTimes: compTime) {
	    			List<RequestIntegranteDTO> integrantes = integrante.extrairIntegrante(compTimes);
	    			nomeIntegrantes.addAll(integrante.extrairNomeIntegrante(integrantes));
	    		}
		    }
	    	return nomeIntegrantes;
	    }

	    
	}