package com.cocoasweet.elinduxus.api.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cocoasweet.elinduxus.api.dto.DataDTO;
import com.cocoasweet.elinduxus.api.dto.RequestComposicaoTimeDTO;
import com.cocoasweet.elinduxus.api.dto.RequestIntegranteDTO;
import com.cocoasweet.elinduxus.api.entity.TimeEntity;

@Service
public class ApiService {
	
	@Autowired
	private ComposicaoTimeImpl composicaoTime;
	@Autowired
	private TimeImpl timeService;
	@Autowired
	private IntegranteImpl integranteService;
	    /**
	     * Vai retornar uma lista com os nomes dos integrantes do time daquela data
	     */
	    public List<String> timeDaData(LocalDate data){
	    	List<Long> ids = timeService.procurarIdPorData(data);
	    	Set<String> nomeIntegrantes = new HashSet<String>();
	    	for(Long id: ids) {
	    		List<RequestComposicaoTimeDTO> compTime = composicaoTime.findCompByTimeId(id);
	    		for(RequestComposicaoTimeDTO compTimes: compTime) {
	    			List<RequestIntegranteDTO> integrantes = integranteService.extrairIntegrante(compTimes);
	    			nomeIntegrantes.addAll(integranteService.extrairNomeIntegrante(integrantes));
	    		}
		    }
	    	return nomeIntegrantes.stream().toList();
	    }
	    
	    /**
	     * Vai retornar o integrante que tiver presente na maior quantidade de times
	     * dentro do período
	     */
	    public RequestIntegranteDTO integranteMaisUsado(DataDTO datas){
	    	List<Long> ids = timeService.procurarIdsPorData(datas.getDataInicial(), datas.getDataFinal());
	    	Map<RequestIntegranteDTO, Integer> quantidadeIntegrantes = new HashMap<>();
	    	//Procura composição dos times pela chave timeID
	    	for(Long id: ids) {
	    		List<RequestComposicaoTimeDTO> compTimes = composicaoTime.findCompByTimeId(id);
	    		//Percorre a lista de composição de time para extrair os integrantes
	    			for(RequestComposicaoTimeDTO compTime: compTimes) {
	    				List<RequestIntegranteDTO> integrantes = integranteService.extrairIntegrante(compTime);
	    				for(RequestIntegranteDTO integrante: integrantes) {
	    					//Para cada integrante, cria uma nova chave se ainda não estiver na estrutura Map
	    					if(!quantidadeIntegrantes.containsKey(integrante)) {
	    						quantidadeIntegrantes.put(integrante, 0);
	    					}
	    					int presencaTemp = quantidadeIntegrantes.get(integrante);
	    					quantidadeIntegrantes.put(integrante, presencaTemp+1);
	    				}
	    		}
	    	}
	    	RequestIntegranteDTO integrante = null;
	    	int maiorFrequencia = 0;
	    	for(Map.Entry<RequestIntegranteDTO, Integer> entry : quantidadeIntegrantes.entrySet()) {
	    		if(entry.getValue() > maiorFrequencia) {
	    			maiorFrequencia = entry.getValue();
	    			integrante = entry.getKey();
	    		}
	    	}
	        return integrante;
	    }
	    
	    /**
	     * Vai retornar uma lista com os nomes dos integrantes do time mais comum
	     * dentro do período
	     */
	    public List<String> timeMaisComum(DataDTO datas){
	    	List<Long> ids = timeService.procurarIdsPorData(datas.getDataInicial(), datas.getDataFinal());
	    	Map<TimeEntity, Integer> times = new HashMap<>();
	    	for(Long id: ids) {
	    		List<RequestComposicaoTimeDTO> composicoes = composicaoTime.findCompByTimeId(id);
	    		for(RequestComposicaoTimeDTO composicao: composicoes) {
	    			if(!times.containsKey(composicao.getTime())) {
	    				times.put(composicao.getTime(), 0);
	    			}
	    			int presencaTemp = times.get(composicao.getTime());
	    			times.put(composicao.getTime(), presencaTemp +1);
	    		}
	    	}
	    	TimeEntity maisComum = null;
	    	int maiorFrequencia = 0;
	    	for(Map.Entry<TimeEntity, Integer> entry: times.entrySet()) {
	    		if(entry.getValue() > maiorFrequencia) {
	    			maiorFrequencia = entry.getValue();
	    			maisComum = entry.getKey();
	    		}
	    	}
	    	List<RequestComposicaoTimeDTO> composicoes = composicaoTime.findCompByTimeId(maisComum.getId());
	    	List<RequestIntegranteDTO> integrantes = new ArrayList<>();
	    	Set<String> nomeIntegrantes = new HashSet<String>();
	    	for(RequestComposicaoTimeDTO composicao: composicoes) {
	    		integrantes = integranteService.extrairIntegrante(composicao);
	    		nomeIntegrantes.addAll(integranteService.extrairNomeIntegrante(integrantes));
	    	}
	    	
	        return nomeIntegrantes.stream().toList();
	    }
	    
	    /**
	     * Vai retornar a função mais comum nos times dentro do período
	     */
	    public String funcaoMaisComum(DataDTO datas){
	    	List<Long> ids = timeService.procurarIdsPorData(datas.getDataInicial(), datas.getDataFinal());
	    	Map<String, Integer> funcoes = new HashMap<>();
	    	for(Long id: ids) {
	    		List<RequestComposicaoTimeDTO> composicoes = composicaoTime.findCompByTimeId(id);
	    		for(RequestComposicaoTimeDTO composicao: composicoes) {
	    			List<RequestIntegranteDTO> integrantes = integranteService.extrairIntegrante(composicao);
	    			for(RequestIntegranteDTO integrante: integrantes) {
	    				if(!funcoes.containsKey(integrante.getFuncao())) {
	    					funcoes.put(integrante.getFuncao(), 0);
	    				}
	    				int presencaTemp = funcoes.get(integrante.getFuncao());
	    				funcoes.put(integrante.getFuncao(), presencaTemp+1);
	    			}
	    		}
	    		
	    	}
	    	String funcao = null;
	    	int maiorFrequencia = 0;
	    	for(Map.Entry<String, Integer> entry:funcoes.entrySet()) {
	    		if(entry.getValue() > maiorFrequencia) {
	    			maiorFrequencia = entry.getValue();
	    			funcao = entry.getKey();
	    		}
	    	}
	        return funcao;
	    }
	    
	    /**
	     * Vai retornar o nome da Franquia mais comum nos times dentro do período
	     */
	    public String franquiaMaisFamosa(DataDTO datas) {
	    	List<Long> ids = timeService.procurarIdsPorData(datas.getDataInicial(), datas.getDataFinal());
	    	Map<String, Integer> franquias = new HashMap<>();
	    	for(Long id: ids) {
	    		List<RequestComposicaoTimeDTO> composicoes = composicaoTime.findCompByTimeId(id);
	    		for(RequestComposicaoTimeDTO composicao: composicoes) {
	    			List<RequestIntegranteDTO> integrantes = integranteService.extrairIntegrante(composicao);
	    			for(RequestIntegranteDTO integrante: integrantes) {
	    				if(!franquias.containsKey(integrante.getFranquia())) {
	    					franquias.put(integrante.getFranquia(), 0);
	    				}
	    				int presencaTemp = franquias.get(integrante.getFranquia());
	    				franquias.put(integrante.getFranquia(), presencaTemp + 1);
	    			}
	    		}
	    	}
	    	String franquia = null;
	    	int maiorFrequencia = 0;
	    	for(Map.Entry<String, Integer> entry: franquias.entrySet()) {
	    		if(entry.getValue() > maiorFrequencia) {
	    			maiorFrequencia = entry.getValue();
	    			franquia = entry.getKey();
	    		}
	    	}
	        return franquia;
	    }
	    
	    /**
	     * Vai retornar o número (quantidade) de Franquias dentro do período
	     */
	    public Map<String, Long> contagemPorFranquia(DataDTO datas){
	    	List<Long> ids = timeService.procurarIdsPorData(datas.getDataInicial(), datas.getDataFinal());
	    	Map<String, Long> quantidadeFranquias = new HashMap<>();
	    	for(Long id: ids) {
	    		List<RequestComposicaoTimeDTO> composicoes = composicaoTime.findCompByTimeId(id);
	    		for(RequestComposicaoTimeDTO composicao: composicoes) {
	    			List<RequestIntegranteDTO> integrantes = integranteService.extrairIntegrante(composicao);
	    			for(RequestIntegranteDTO integrante: integrantes) {
	    				if(!quantidadeFranquias.containsKey(integrante.getFranquia())) {
	    					quantidadeFranquias.put(integrante.getFranquia(), (long) 0);
	    				}
	    				Long presencaTemp = quantidadeFranquias.get(integrante.getFranquia());
	    				quantidadeFranquias.put(integrante.getFranquia(), presencaTemp+1);
	    			}
	    		}
	    	}
	        return quantidadeFranquias;
	    }
	    
	    /**
	     * Vai retornar o número (quantidade) de Funções dentro do período
	     */
	    public Map<String, Long> contagemPorFuncao(DataDTO datas){
	    	List<Long> ids = timeService.procurarIdsPorData(datas.getDataInicial(), datas.getDataFinal());
	    	Map<String, Long> quantidadeFuncoes = new HashMap<>();
	    	for(Long id: ids) {
	    		List<RequestComposicaoTimeDTO> composicoes = composicaoTime.findCompByTimeId(id);
	    		for(RequestComposicaoTimeDTO composicao: composicoes) {
	    			List<RequestIntegranteDTO> integrantes = integranteService.extrairIntegrante(composicao);
	    			for(RequestIntegranteDTO integrante: integrantes) {
	    				if(!quantidadeFuncoes.containsKey(integrante.getFuncao())) {
	    					quantidadeFuncoes.put(integrante.getFuncao(), (long) 0);
	    				}
	    				Long presencaTemp = quantidadeFuncoes.get(integrante.getFuncao());
	    				quantidadeFuncoes.put(integrante.getFuncao(), presencaTemp+1);
	    			}
	    		}
	    	}
	        return quantidadeFuncoes;
	    }
	    
	}