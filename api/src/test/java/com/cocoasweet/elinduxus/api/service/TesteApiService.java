package com.cocoasweet.elinduxus.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.cocoasweet.elinduxus.api.dto.DataDTO;
import com.cocoasweet.elinduxus.api.dto.RequestComposicaoTimeDTO;
import com.cocoasweet.elinduxus.api.dto.RequestIntegranteDTO;
import com.cocoasweet.elinduxus.api.entity.TimeEntity;
import com.cocoasweet.elinduxus.api.service.impl.ApiService;
import com.cocoasweet.elinduxus.api.service.impl.ComposicaoTimeImpl;
import com.cocoasweet.elinduxus.api.service.impl.IntegranteImpl;
import com.cocoasweet.elinduxus.api.service.impl.TimeImpl;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;

@RunWith(DataProviderRunner.class)
public class TesteApiService {

    private final static LocalDate data1993 = LocalDate.of(1993,1, 1);
    private final static LocalDate data1994 = LocalDate.of(1994,1, 1);
    private final static LocalDate data1995 = LocalDate.of(1995,1, 1);

    @Mock
    private TimeImpl timeService;

    @Mock
    private ComposicaoTimeImpl composicaoTimeService;

    @Mock
    private IntegranteImpl integranteService;

    @Spy
    @InjectMocks
    private ApiService apiService;
    
    private static DadosParaTesteApiService dadosParaTesteApiService = new DadosParaTesteApiService();



    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testTimeDaData() {

        LocalDate data = LocalDate.of(1993, 1, 1);

        TimeEntity timeEntity1994 = dadosParaTesteApiService.getTimeChicagoBullsDe1994();
        List<Long> ids = new ArrayList<>();
        List<RequestComposicaoTimeDTO> compTimes = new ArrayList<>();
        List<RequestIntegranteDTO> integrantes = new ArrayList<>();
        ids.add(1L);

        compTimes = List.of(
                new RequestComposicaoTimeDTO(timeEntity1994, dadosParaTesteApiService.getMichael_jordan()),
                new RequestComposicaoTimeDTO(timeEntity1994, dadosParaTesteApiService.getScottie_pippen())
            );
        integrantes = List.of(
                new RequestIntegranteDTO(dadosParaTesteApiService.getMichael_jordan().getId(), "NBA","Michael Jordan", "ala"),
                new RequestIntegranteDTO(dadosParaTesteApiService.getScottie_pippen().getId(), "NBA","Scottie Pippen", "ala")
            );
        
        List<String> nomeIntegrantes = List.of("Scottie Pippen", "Michael Jordan");

        

        when(timeService.procurarIdPorData(data)).thenReturn(ids);
        when(composicaoTimeService.findCompByTimeId(timeEntity1994.getId())).thenReturn(compTimes);
        when(integranteService.extrairIntegrante(compTimes.get(0))).thenReturn(List.of(integrantes.get(0)));
        when(integranteService.extrairIntegrante(compTimes.get(1))).thenReturn(List.of(integrantes.get(1)));
        when(integranteService.extrairNomeIntegrante(List.of(integrantes.get(0)))).thenReturn(List.of("Michael Jordan"));
        when(integranteService.extrairNomeIntegrante(List.of(integrantes.get(1)))).thenReturn(List.of("Scottie Pippen"));

        List<String> timeRetornado = apiService.timeDaData(data);

        assertEquals(nomeIntegrantes, timeRetornado);
    }



    @Test
    public void testIntegranteMaisUsado() {
    	DataDTO datas = new DataDTO(data1993, data1995);
        TimeEntity timeEntity1993 = dadosParaTesteApiService.getTimeDetroidPistonsDe1993();
        TimeEntity timeEntity1995 = dadosParaTesteApiService.getTimeChicagoBullsDe1995();
        TimeEntity timeEntity1994 = dadosParaTesteApiService.getTimeChicagoBullsDe1994();
        List<Long> ids = new ArrayList<>();
        List<RequestComposicaoTimeDTO> compTimes = new ArrayList<>();
        List<RequestIntegranteDTO> integrantes = new ArrayList<>();
        ids.add(1L);

        compTimes = List.of(
                new RequestComposicaoTimeDTO(timeEntity1993, dadosParaTesteApiService.getDenis_rodman()),
                new RequestComposicaoTimeDTO(timeEntity1994, dadosParaTesteApiService.getMichael_jordan()),
                new RequestComposicaoTimeDTO(timeEntity1994, dadosParaTesteApiService.getDenis_rodman()),
                new RequestComposicaoTimeDTO(timeEntity1994, dadosParaTesteApiService.getScottie_pippen()),
                new RequestComposicaoTimeDTO(timeEntity1995, dadosParaTesteApiService.getMichael_jordan()),
                new RequestComposicaoTimeDTO(timeEntity1995, dadosParaTesteApiService.getDenis_rodman()),
                new RequestComposicaoTimeDTO(timeEntity1995, dadosParaTesteApiService.getScottie_pippen())
            );
        
        integrantes = List.of(
                new RequestIntegranteDTO(dadosParaTesteApiService.getMichael_jordan().getId(), "NBA", "Michael Jordan", "ala"),
                new RequestIntegranteDTO(dadosParaTesteApiService.getMichael_jordan().getId(), "NBA", "Michael Jordan", "ala"),
                new RequestIntegranteDTO(dadosParaTesteApiService.getScottie_pippen().getId(), "NBA","Scottie Pippen", "ala"),
                new RequestIntegranteDTO(dadosParaTesteApiService.getScottie_pippen().getId(), "NBA","Scottie Pippen", "ala"),
                new RequestIntegranteDTO(dadosParaTesteApiService.getDenis_rodman().getId(), "NBA","Denis Rodman", "ala-pivô"),
                new RequestIntegranteDTO(dadosParaTesteApiService.getDenis_rodman().getId(), "NBA","Denis Rodman", "ala-pivô"),
                new RequestIntegranteDTO(dadosParaTesteApiService.getDenis_rodman().getId(), "NBA", "Denis Rodman", "ala-pivô")

            );

        RequestIntegranteDTO esperado = new RequestIntegranteDTO(dadosParaTesteApiService.getDenis_rodman().getId(),
        		dadosParaTesteApiService.getDenis_rodman().getFranquia(), dadosParaTesteApiService.getDenis_rodman().getNome(),
        		dadosParaTesteApiService.getDenis_rodman().getFuncao());
        
        when(timeService.procurarIdsPorData(datas.getDataInicial(),datas.getDataFinal())).thenReturn(ids);
        when(composicaoTimeService.findCompByTimeId(timeEntity1993.getId())).thenReturn(compTimes);
        when(composicaoTimeService.findCompByTimeId(timeEntity1994.getId())).thenReturn(compTimes);
        when(composicaoTimeService.findCompByTimeId(timeEntity1995.getId())).thenReturn(compTimes);
        when(integranteService.extrairIntegrante(compTimes.get(0))).thenReturn(List.of(integrantes.get(0)));
        when(integranteService.extrairIntegrante(compTimes.get(1))).thenReturn(List.of(integrantes.get(1)));
        when(integranteService.extrairIntegrante(compTimes.get(2))).thenReturn(List.of(integrantes.get(2)));
        when(integranteService.extrairIntegrante(compTimes.get(3))).thenReturn(List.of(integrantes.get(3)));
        when(integranteService.extrairIntegrante(compTimes.get(4))).thenReturn(List.of(integrantes.get(4)));
        when(integranteService.extrairIntegrante(compTimes.get(5))).thenReturn(List.of(integrantes.get(5)));
        when(integranteService.extrairIntegrante(compTimes.get(6))).thenReturn(List.of(integrantes.get(6)));

    	RequestIntegranteDTO integranteRetornado = apiService.integranteMaisUsado(datas);

        assertEquals(esperado, integranteRetornado);
   }

    @Test
    public void testTimeMaisComum() {

    	DataDTO datas = new DataDTO(data1993, data1994);
        TimeEntity timeEntity1993 = dadosParaTesteApiService.getTimeDetroidPistonsDe1993();
        TimeEntity timeEntity1994 = dadosParaTesteApiService.getTimeChicagoBullsDe1994();
        List<Long> ids = new ArrayList<>();
        List<RequestComposicaoTimeDTO> compTimes = new ArrayList<>();
        List<RequestIntegranteDTO> integrantes = new ArrayList<>();
        ids.add(1L);

        
        compTimes = List.of(
                new RequestComposicaoTimeDTO(timeEntity1993, dadosParaTesteApiService.getDenis_rodman()),
                new RequestComposicaoTimeDTO(timeEntity1994, dadosParaTesteApiService.getMichael_jordan()),
                new RequestComposicaoTimeDTO(timeEntity1994, dadosParaTesteApiService.getDenis_rodman()),
                new RequestComposicaoTimeDTO(timeEntity1994, dadosParaTesteApiService.getScottie_pippen())
            );
        
        integrantes = List.of(
                new RequestIntegranteDTO(dadosParaTesteApiService.getMichael_jordan().getId(), "NBA","Michael Jordan", "ala"),
                new RequestIntegranteDTO(dadosParaTesteApiService.getScottie_pippen().getId(), "NBA","Scottie Pippen", "ala"),
                new RequestIntegranteDTO(dadosParaTesteApiService.getDenis_rodman().getId(),"NBA", "Denis Rodman", "ala-pivô"),
                new RequestIntegranteDTO(dadosParaTesteApiService.getDenis_rodman().getId(), "NBA","Denis Rodman","ala-pivô")

            );
        
        when(timeService.procurarIdsPorData(datas.getDataInicial(),datas.getDataFinal())).thenReturn(ids);
        when(composicaoTimeService.findCompByTimeId(timeEntity1993.getId())).thenReturn(compTimes);
        when(composicaoTimeService.findCompByTimeId(timeEntity1994.getId())).thenReturn(compTimes);
        when(integranteService.extrairIntegrante(compTimes.get(0))).thenReturn(List.of(integrantes.get(0)));
        when(integranteService.extrairIntegrante(compTimes.get(1))).thenReturn(List.of(integrantes.get(1)));
        when(integranteService.extrairIntegrante(compTimes.get(2))).thenReturn(List.of(integrantes.get(2)));
        when(integranteService.extrairIntegrante(compTimes.get(3))).thenReturn(List.of(integrantes.get(3)));
        when(integranteService.extrairNomeIntegrante(List.of(integrantes.get(0)))).thenReturn(List.of("Michael Jordan"));
        when(integranteService.extrairNomeIntegrante(List.of(integrantes.get(1)))).thenReturn(List.of("Scottie Pippen"));
        when(integranteService.extrairNomeIntegrante(List.of(integrantes.get(2)))).thenReturn(List.of("Denis Rodman"));

        List<String> nomeIntegrantes = List.of("Scottie Pippen", "Michael Jordan", "Denis Rodman");

        List<String> nomeDosIntegrantesDoTimeMaisComum = apiService.timeMaisComum(datas);

        if(nomeDosIntegrantesDoTimeMaisComum != null){
            nomeDosIntegrantesDoTimeMaisComum.sort(Comparator.naturalOrder());
        }

        assertEquals(nomeIntegrantes, nomeDosIntegrantesDoTimeMaisComum);
    }

    @Test
    public void testFuncaoMaisComum() {
    	
    	DataDTO datas = new DataDTO(data1993, data1995);
    	TimeEntity timeEntity1993 = dadosParaTesteApiService.getTimeDetroidPistonsDe1993();
        TimeEntity timeEntity1994 = dadosParaTesteApiService.getTimeChicagoBullsDe1994();
        TimeEntity timeEntity1995 = dadosParaTesteApiService.getTimeChicagoBullsDe1995();
        List<Long> ids = new ArrayList<>();
        List<RequestComposicaoTimeDTO> compTimes = new ArrayList<>();
        List<RequestIntegranteDTO> integrantes = new ArrayList<>();
        ids.add(1L);

        compTimes = List.of(
                new RequestComposicaoTimeDTO(timeEntity1993, dadosParaTesteApiService.getDenis_rodman()),
                new RequestComposicaoTimeDTO(timeEntity1994, dadosParaTesteApiService.getMichael_jordan()),
                new RequestComposicaoTimeDTO(timeEntity1994, dadosParaTesteApiService.getDenis_rodman()),
                new RequestComposicaoTimeDTO(timeEntity1994, dadosParaTesteApiService.getScottie_pippen()),
                new RequestComposicaoTimeDTO(timeEntity1995, dadosParaTesteApiService.getMichael_jordan()),
                new RequestComposicaoTimeDTO(timeEntity1995, dadosParaTesteApiService.getDenis_rodman()),
                new RequestComposicaoTimeDTO(timeEntity1995, dadosParaTesteApiService.getScottie_pippen())
            );
        integrantes = List.of(
                new RequestIntegranteDTO(dadosParaTesteApiService.getMichael_jordan().getId(), "Michael Jordan", "NBA", "ala"),
                new RequestIntegranteDTO(dadosParaTesteApiService.getMichael_jordan().getId(), "Michael Jordan", "NBA", "ala"),
                new RequestIntegranteDTO(dadosParaTesteApiService.getScottie_pippen().getId(), "Scottie Pippen", "NBA", "ala"),
                new RequestIntegranteDTO(dadosParaTesteApiService.getScottie_pippen().getId(), "Scottie Pippen", "NBA", "ala"),
                new RequestIntegranteDTO(dadosParaTesteApiService.getDenis_rodman().getId(), "Denis Rodman", "NBA", "ala-pivô"),
                new RequestIntegranteDTO(dadosParaTesteApiService.getDenis_rodman().getId(), "Denis Rodman", "NBA", "ala-pivô"),
                new RequestIntegranteDTO(dadosParaTesteApiService.getDenis_rodman().getId(), "Denis Rodman", "NBA", "ala-pivô")

            );
        when(timeService.procurarIdsPorData(datas.getDataInicial(),datas.getDataFinal())).thenReturn(ids);
        when(composicaoTimeService.findCompByTimeId(timeEntity1993.getId())).thenReturn(compTimes);
        when(composicaoTimeService.findCompByTimeId(timeEntity1994.getId())).thenReturn(compTimes);
        when(composicaoTimeService.findCompByTimeId(timeEntity1995.getId())).thenReturn(compTimes);
        when(integranteService.extrairIntegrante(compTimes.get(0))).thenReturn(List.of(integrantes.get(0)));
        when(integranteService.extrairIntegrante(compTimes.get(1))).thenReturn(List.of(integrantes.get(1)));
        when(integranteService.extrairIntegrante(compTimes.get(2))).thenReturn(List.of(integrantes.get(2)));
        when(integranteService.extrairIntegrante(compTimes.get(3))).thenReturn(List.of(integrantes.get(3)));
        when(integranteService.extrairIntegrante(compTimes.get(4))).thenReturn(List.of(integrantes.get(4)));
        when(integranteService.extrairIntegrante(compTimes.get(5))).thenReturn(List.of(integrantes.get(5)));
        when(integranteService.extrairIntegrante(compTimes.get(6))).thenReturn(List.of(integrantes.get(6)));
        
    	String esperado = "ala";

        String funcaoMaisComum = apiService.funcaoMaisComum(datas);

        assertEquals(esperado, funcaoMaisComum);
    }

    @DataProvider
    

    @Test
    public void testFranquiaMaisFamosa() {

    	DataDTO datas = new DataDTO(data1993, data1995);
    	TimeEntity timeEntity1993 = dadosParaTesteApiService.getTimeDetroidPistonsDe1993();
        TimeEntity timeEntity1994 = dadosParaTesteApiService.getTimeChicagoBullsDe1994();
        TimeEntity timeEntity1995 = dadosParaTesteApiService.getTimeChicagoBullsDe1995();
        List<Long> ids = new ArrayList<>();
        List<RequestComposicaoTimeDTO> compTimes = new ArrayList<>();
        List<RequestIntegranteDTO> integrantes = new ArrayList<>();
        ids.add(1L);
        
        compTimes = List.of(
                new RequestComposicaoTimeDTO(timeEntity1993, dadosParaTesteApiService.getDenis_rodman()),
                new RequestComposicaoTimeDTO(timeEntity1994, dadosParaTesteApiService.getMichael_jordan()),
                new RequestComposicaoTimeDTO(timeEntity1994, dadosParaTesteApiService.getDenis_rodman()),
                new RequestComposicaoTimeDTO(timeEntity1994, dadosParaTesteApiService.getScottie_pippen()),
                new RequestComposicaoTimeDTO(timeEntity1995, dadosParaTesteApiService.getMichael_jordan()),
                new RequestComposicaoTimeDTO(timeEntity1995, dadosParaTesteApiService.getDenis_rodman()),
                new RequestComposicaoTimeDTO(timeEntity1995, dadosParaTesteApiService.getScottie_pippen())
            );
        integrantes = List.of(
                new RequestIntegranteDTO(dadosParaTesteApiService.getMichael_jordan().getId(),"NBA", "Michael Jordan", "ala"),
                new RequestIntegranteDTO(dadosParaTesteApiService.getMichael_jordan().getId(),"NBA", "Michael Jordan", "ala"),
                new RequestIntegranteDTO(dadosParaTesteApiService.getScottie_pippen().getId(),"NBA", "Scottie Pippen", "ala"),
                new RequestIntegranteDTO(dadosParaTesteApiService.getScottie_pippen().getId(), "NBA","Scottie Pippen", "ala"),
                new RequestIntegranteDTO(dadosParaTesteApiService.getDenis_rodman().getId(), "NBA","Denis Rodman","ala-pivô"),
                new RequestIntegranteDTO(dadosParaTesteApiService.getDenis_rodman().getId(), "NBA","Denis Rodman", "ala-pivô"),
                new RequestIntegranteDTO(dadosParaTesteApiService.getDenis_rodman().getId(), "NBA","Denis Rodman","ala-pivô")

            );
        
        when(timeService.procurarIdsPorData(datas.getDataInicial(),datas.getDataFinal())).thenReturn(ids);
        when(composicaoTimeService.findCompByTimeId(timeEntity1993.getId())).thenReturn(compTimes);
        when(composicaoTimeService.findCompByTimeId(timeEntity1994.getId())).thenReturn(compTimes);
        when(composicaoTimeService.findCompByTimeId(timeEntity1995.getId())).thenReturn(compTimes);
        when(integranteService.extrairIntegrante(compTimes.get(0))).thenReturn(List.of(integrantes.get(0)));
        when(integranteService.extrairIntegrante(compTimes.get(1))).thenReturn(List.of(integrantes.get(1)));
        when(integranteService.extrairIntegrante(compTimes.get(2))).thenReturn(List.of(integrantes.get(2)));
        when(integranteService.extrairIntegrante(compTimes.get(3))).thenReturn(List.of(integrantes.get(3)));
        when(integranteService.extrairIntegrante(compTimes.get(4))).thenReturn(List.of(integrantes.get(4)));
        when(integranteService.extrairIntegrante(compTimes.get(5))).thenReturn(List.of(integrantes.get(5)));
        when(integranteService.extrairIntegrante(compTimes.get(6))).thenReturn(List.of(integrantes.get(6)));

        String esperado = dadosParaTesteApiService.getFranquiaNBA();
        String franquiaMaisFamosa = apiService.franquiaMaisFamosa(datas);
        assertEquals(esperado, franquiaMaisFamosa);
    }
    
    @Test
    public void testContagemPorFranquia() {
    	DataDTO datas = new DataDTO(data1993, data1995);
    	TimeEntity timeEntity1993 = dadosParaTesteApiService.getTimeDetroidPistonsDe1993();
        TimeEntity timeEntity1994 = dadosParaTesteApiService.getTimeChicagoBullsDe1994();
        TimeEntity timeEntity1995 = dadosParaTesteApiService.getTimeChicagoBullsDe1995();
        List<Long> ids = new ArrayList<>();
        List<RequestComposicaoTimeDTO> compTimes = new ArrayList<>();
        List<RequestIntegranteDTO> integrantes = new ArrayList<>();
        ids.add(1L);
        
        compTimes = List.of(
                new RequestComposicaoTimeDTO(timeEntity1993, dadosParaTesteApiService.getDenis_rodman()),
                new RequestComposicaoTimeDTO(timeEntity1994, dadosParaTesteApiService.getMichael_jordan()),
                new RequestComposicaoTimeDTO(timeEntity1994, dadosParaTesteApiService.getDenis_rodman()),
                new RequestComposicaoTimeDTO(timeEntity1994, dadosParaTesteApiService.getScottie_pippen()),
                new RequestComposicaoTimeDTO(timeEntity1995, dadosParaTesteApiService.getMichael_jordan()),
                new RequestComposicaoTimeDTO(timeEntity1995, dadosParaTesteApiService.getDenis_rodman()),
                new RequestComposicaoTimeDTO(timeEntity1995, dadosParaTesteApiService.getScottie_pippen())
            );
        integrantes = List.of(
                new RequestIntegranteDTO(dadosParaTesteApiService.getMichael_jordan().getId(), "Michael Jordan", "NBA", "ala"),
                new RequestIntegranteDTO(dadosParaTesteApiService.getMichael_jordan().getId(), "Michael Jordan", "NBA", "ala"),
                new RequestIntegranteDTO(dadosParaTesteApiService.getScottie_pippen().getId(), "Scottie Pippen", "NBA", "ala"),
                new RequestIntegranteDTO(dadosParaTesteApiService.getScottie_pippen().getId(), "Scottie Pippen", "NBA", "ala"),
                new RequestIntegranteDTO(dadosParaTesteApiService.getDenis_rodman().getId(), "Denis Rodman", "NBA", "ala-pivô"),
                new RequestIntegranteDTO(dadosParaTesteApiService.getDenis_rodman().getId(), "Denis Rodman", "NBA", "ala-pivô"),
                new RequestIntegranteDTO(dadosParaTesteApiService.getDenis_rodman().getId(), "Denis Rodman", "NBA", "ala-pivô")

            );
        
        when(timeService.procurarIdsPorData(datas.getDataInicial(),datas.getDataFinal())).thenReturn(ids);
        when(composicaoTimeService.findCompByTimeId(timeEntity1993.getId())).thenReturn(compTimes);
        when(composicaoTimeService.findCompByTimeId(timeEntity1994.getId())).thenReturn(compTimes);
        when(composicaoTimeService.findCompByTimeId(timeEntity1995.getId())).thenReturn(compTimes);
        when(integranteService.extrairIntegrante(compTimes.get(0))).thenReturn(List.of(integrantes.get(0)));
        when(integranteService.extrairIntegrante(compTimes.get(1))).thenReturn(List.of(integrantes.get(1)));
        when(integranteService.extrairIntegrante(compTimes.get(2))).thenReturn(List.of(integrantes.get(2)));
        when(integranteService.extrairIntegrante(compTimes.get(3))).thenReturn(List.of(integrantes.get(3)));
        when(integranteService.extrairIntegrante(compTimes.get(4))).thenReturn(List.of(integrantes.get(4)));
        when(integranteService.extrairIntegrante(compTimes.get(5))).thenReturn(List.of(integrantes.get(5)));
        when(integranteService.extrairIntegrante(compTimes.get(6))).thenReturn(List.of(integrantes.get(6)));

        Map<String, Long> esperado = new HashMap<String, Long>();
        esperado.put("NBA",(long) 7);
    	

        Map<String, Long> contagemPorFranquia = apiService.contagemPorFranquia(datas);
        assertEquals(esperado, contagemPorFranquia);
    }


    @Test
    public void testContagemPorFuncao() {

    	DataDTO datas = new DataDTO(data1993, data1995);
    	TimeEntity timeEntity1993 = dadosParaTesteApiService.getTimeDetroidPistonsDe1993();
        TimeEntity timeEntity1994 = dadosParaTesteApiService.getTimeChicagoBullsDe1994();
        TimeEntity timeEntity1995 = dadosParaTesteApiService.getTimeChicagoBullsDe1995();
        List<Long> ids = new ArrayList<>();
        List<RequestComposicaoTimeDTO> compTimes = new ArrayList<>();
        List<RequestIntegranteDTO> integrantes = new ArrayList<>();
        ids.add(1L);
        
        compTimes = List.of(
                new RequestComposicaoTimeDTO(timeEntity1993, dadosParaTesteApiService.getDenis_rodman()),
                new RequestComposicaoTimeDTO(timeEntity1994, dadosParaTesteApiService.getMichael_jordan()),
                new RequestComposicaoTimeDTO(timeEntity1994, dadosParaTesteApiService.getDenis_rodman()),
                new RequestComposicaoTimeDTO(timeEntity1994, dadosParaTesteApiService.getScottie_pippen()),
                new RequestComposicaoTimeDTO(timeEntity1995, dadosParaTesteApiService.getMichael_jordan()),
                new RequestComposicaoTimeDTO(timeEntity1995, dadosParaTesteApiService.getDenis_rodman()),
                new RequestComposicaoTimeDTO(timeEntity1995, dadosParaTesteApiService.getScottie_pippen())
            );
        integrantes = List.of(
                new RequestIntegranteDTO(dadosParaTesteApiService.getMichael_jordan().getId(), "Michael Jordan", "NBA", "ala"),
                new RequestIntegranteDTO(dadosParaTesteApiService.getMichael_jordan().getId(), "Michael Jordan", "NBA", "ala"),
                new RequestIntegranteDTO(dadosParaTesteApiService.getScottie_pippen().getId(), "Scottie Pippen", "NBA", "ala"),
                new RequestIntegranteDTO(dadosParaTesteApiService.getScottie_pippen().getId(), "Scottie Pippen", "NBA", "ala"),
                new RequestIntegranteDTO(dadosParaTesteApiService.getDenis_rodman().getId(), "Denis Rodman", "NBA", "ala-pivô"),
                new RequestIntegranteDTO(dadosParaTesteApiService.getDenis_rodman().getId(), "Denis Rodman", "NBA", "ala-pivô"),
                new RequestIntegranteDTO(dadosParaTesteApiService.getDenis_rodman().getId(), "Denis Rodman", "NBA", "ala-pivô")

            );
        
        when(timeService.procurarIdsPorData(datas.getDataInicial(),datas.getDataFinal())).thenReturn(ids);
        when(composicaoTimeService.findCompByTimeId(timeEntity1993.getId())).thenReturn(compTimes);
        when(composicaoTimeService.findCompByTimeId(timeEntity1994.getId())).thenReturn(compTimes);
        when(composicaoTimeService.findCompByTimeId(timeEntity1995.getId())).thenReturn(compTimes);
        when(integranteService.extrairIntegrante(compTimes.get(0))).thenReturn(List.of(integrantes.get(0)));
        when(integranteService.extrairIntegrante(compTimes.get(1))).thenReturn(List.of(integrantes.get(1)));
        when(integranteService.extrairIntegrante(compTimes.get(2))).thenReturn(List.of(integrantes.get(2)));
        when(integranteService.extrairIntegrante(compTimes.get(3))).thenReturn(List.of(integrantes.get(3)));
        when(integranteService.extrairIntegrante(compTimes.get(4))).thenReturn(List.of(integrantes.get(4)));
        when(integranteService.extrairIntegrante(compTimes.get(5))).thenReturn(List.of(integrantes.get(5)));
        when(integranteService.extrairIntegrante(compTimes.get(6))).thenReturn(List.of(integrantes.get(6)));

        Map<String, Long> esperado = new HashMap<String, Long>();
        esperado.put("ala", (long) 4);
        esperado.put("ala-pivô", (long) 3);

        
        Map<String, Long> contagemPorFuncao = apiService.contagemPorFuncao(datas);
        assertEquals(esperado, contagemPorFuncao);
    }

}