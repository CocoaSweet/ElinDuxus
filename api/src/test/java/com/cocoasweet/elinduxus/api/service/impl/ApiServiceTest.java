package com.cocoasweet.elinduxus.api.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cocoasweet.elinduxus.api.dto.DataDTO;
import com.cocoasweet.elinduxus.api.dto.RequestComposicaoTimeDTO;
import com.cocoasweet.elinduxus.api.dto.RequestIntegranteDTO;
import com.cocoasweet.elinduxus.api.entity.TimeEntity;
import com.cocoasweet.elinduxus.api.repository.TimeRepository;
import com.cocoasweet.elinduxus.api.service.DadosParaTesteApiService;
import com.cocoasweet.elinduxus.api.service.Integrante;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

@ExtendWith(MockitoExtension.class)
@RunWith(DataProviderRunner.class)
class ApiServiceTest {
	
	@InjectMocks
	private ApiService apiService;
	
	@Mock
	private TimeRepository timeRepository;
	
	@Mock
	private ComposicaoTimeImpl composicaoService;
	
	@Mock
	private TimeEntity timeEntity;
	
	@Mock
	private RequestComposicaoTimeDTO requestComposicaoTimeDTO;
	
	@Mock
	private TimeImpl timeService;
	
    private DadosParaTesteApiService dadosParaTeste;


	@BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        dadosParaTeste = new DadosParaTesteApiService();
    }
	
	
	@Test
    @UseDataProvider("testIntegranteMaisUsadoParams")
	public void timeDaData(){
        LocalDate data = LocalDate.of(1994, 1, 1);
		List<Long> ids = new ArrayList<>();
        List<RequestComposicaoTimeDTO> compTimeList = new ArrayList<>();        
        TimeEntity timeEntity1994 = dadosParaTeste.getTimeDetroidPistonsDe1994();

		
		when(timeService.procurarIdPorData(data)).thenReturn(ids);

        

		
		
	}
	@Test
    @UseDataProvider("testIntegranteMaisUsadoParams")
    public void testIntegranteMaisUsado(LocalDate dataInicial, LocalDate dataFinal, List<TimeEntity> todosOsTimes, Integrante esperado) {
    	DataDTO datas = new DataDTO(dataInicial, dataFinal);
    	RequestIntegranteDTO integranteRetornado = apiService.integranteMaisUsado(datas);

        assertEquals(esperado, integranteRetornado);
    }
}


