package com.cocoasweet.elinduxus.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.cocoasweet.elinduxus.api.dto.DataDTO;
import com.cocoasweet.elinduxus.api.dto.RequestIntegranteDTO;
import com.cocoasweet.elinduxus.api.entity.IntegranteEntity;
import com.cocoasweet.elinduxus.api.entity.TimeEntity;
import com.cocoasweet.elinduxus.api.service.impl.ApiService;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

@RunWith(DataProviderRunner.class)
public class TesteApiService {

    private final static LocalDate data1993 = LocalDate.of(1993,1, 1);
    private final static LocalDate data1995 = LocalDate.of(1995,1, 1);

    @Spy
    private ApiService apiService;


    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }


    @DataProvider
    public static Object[][] testTimeDaDataParams() {

        DadosParaTesteApiService dadosParaTesteApiService = new DadosParaTesteApiService();

        List<TimeEntity> todosOsTimes = dadosParaTesteApiService.getTodosOsTimes();

        TimeEntity timeChicagoBullsDe1995 = dadosParaTesteApiService.getTimeChicagoBullsDe1995();
        TimeEntity timeDetroidPistonsDe1993 = dadosParaTesteApiService.getTimeDetroidPistonsDe1993();

        return new Object[][]{
                {
                        data1995,
                        todosOsTimes,
                        timeChicagoBullsDe1995
                },
                {
                        data1993,
                        todosOsTimes,
                        timeDetroidPistonsDe1993
                }
        };
    }

    @Test
    @UseDataProvider("testTimeDaDataParams")
    public void testTimeDaData(LocalDate data, TimeEntity esperado) {

        List<String> timeRetornado = apiService.timeDaData(data);

        assertEquals(esperado, timeRetornado);
    }



    @DataProvider
    public static Object[][] testIntegranteMaisUsadoParams() {

        DadosParaTesteApiService dadosParaTesteApiService = new DadosParaTesteApiService();

        List<TimeEntity> todosOsTimes = dadosParaTesteApiService.getTodosOsTimes();

        return new Object[][]{
                {
                        data1993,
                        data1995,
                        todosOsTimes,
                        dadosParaTesteApiService.getDenis_rodman()
                }
        };
    }


    @Test
    @UseDataProvider("testIntegranteMaisUsadoParams")
    public void testIntegranteMaisUsado(LocalDate dataInicial, LocalDate dataFinal, List<TimeEntity> todosOsTimes, Integrante esperado) {
    	DataDTO datas = new DataDTO(dataInicial, dataFinal);
    	RequestIntegranteDTO integranteRetornado = apiService.integranteMaisUsado(datas);

        assertEquals(esperado, integranteRetornado);
    }



    @DataProvider
    public static Object[][] testTimeMaisComumParams() {
        DadosParaTesteApiService dadosParaTesteApiService = new DadosParaTesteApiService();
        List<TimeEntity> todosOsTimes = dadosParaTesteApiService.getTodosOsTimes();

        List<String> integrantesEsperados = Arrays.asList(
                dadosParaTesteApiService.getDenis_rodman().getNome(),
                dadosParaTesteApiService.getMichael_jordan().getNome(),
                dadosParaTesteApiService.getScottie_pippen().getNome()
        );
        return new Object[][]{
                {
                        data1993,
                        data1995,
                        todosOsTimes,
                        integrantesEsperados
                }
        };
    }
/*
    @Test
    @UseDataProvider("testTimeMaisComumParams")
    public void testTimeMaisComum(LocalDate dataInicial, LocalDate dataFinal, List<TimeEntity> todosOsTimes, List<String> esperado) {

        List<String> nomeDosIntegrantesDoTimeMaisComum = apiService.timeMaisComum(dataInicial, dataFinal, todosOsTimes);

        if(nomeDosIntegrantesDoTimeMaisComum != null){
            nomeDosIntegrantesDoTimeMaisComum.sort(Comparator.naturalOrder());
        }

        assertEquals(esperado, nomeDosIntegrantesDoTimeMaisComum);
    }



    @DataProvider
    public static Object[][] testFuncaoMaisComumParams() {

        DadosParaTesteApiService dadosParaTesteApiService = new DadosParaTesteApiService();
        List<TimeEntity> todosOsTimes = dadosParaTesteApiService.getTodosOsTimes();

        return new Object[][]{
                {
                        data1993,
                        data1995,
                        todosOsTimes,
                        "ala"
                }
        };
    }

    @Test
    @UseDataProvider("testFuncaoMaisComumParams")
    public void testFuncaoMaisComum(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes, String esperado) {

        String funcaoMaisComum = apiService.funcaoMaisComum(dataInicial, dataFinal, todosOsTimes);

        assertEquals(esperado, funcaoMaisComum);
    }

    @DataProvider
    public static Object[][] testFranquiaMaisFamosaParams() {
        DadosParaTesteApiService dadosParaTesteApiService = new DadosParaTesteApiService();
        List<TimeEntity> todosOsTimes = dadosParaTesteApiService.getTodosOsTimes();

        return new Object[][]{
                {
                        data1993,
                        data1995,
                        todosOsTimes,
                        dadosParaTesteApiService.getFranquiaNBA()
                }
        };
    }

    @Test
    @UseDataProvider("testFranquiaMaisFamosaParams")
    public void testFranquiaMaisFamosa(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes, String esperado) {

        String franquiaMaisFamosa = apiService.franquiaMaisFamosa(dataInicial, dataFinal, todosOsTimes);
        assertEquals(esperado, franquiaMaisFamosa);
    }

    @DataProvider
    public static Object[][] testContagemPorFranquiaParams() {

        DadosParaTesteApiService dadosParaTesteApiService = new DadosParaTesteApiService();
        List<TimeEntity> todosOsTimes = dadosParaTesteApiService.getTodosOsTimes();

        Map<String, Long> esperado = new HashMap<>();
        esperado.put(dadosParaTesteApiService.getFranquiaNBA(), 2L);

        return new Object[][]{
                {
                        data1993,
                        data1995,
                        todosOsTimes,
                        esperado
                }
        };
    }

    @Test
    @UseDataProvider("testContagemPorFranquiaParams")
    public void testContagemPorFranquia(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes, Map<String, Long> esperado) {

        Map<String, Long> contagemPorFranquia = apiService.contagemPorFranquia(dataInicial, dataFinal, todosOsTimes);
        assertEquals(esperado, contagemPorFranquia);
    }



    @DataProvider
    public static Object[][] testContagemPorFuncaoParams() {

        DadosParaTesteApiService dadosParaTesteApiService = new DadosParaTesteApiService();
        List<TimeEntity> todosOsTimes = dadosParaTesteApiService.getTodosOsTimes();

        Map<String, Long> esperado = new HashMap<>();
        esperado.put("ala", 2L);
        esperado.put("ala-pivô", 1L);

        return new Object[][]{
                {
                        data1993,
                        data1995,
                        todosOsTimes,
                        esperado
                }
        };
    }

    @Test
    @UseDataProvider("testContagemPorFuncaoParams")
    public void testContagemPorFuncao(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes, Map<String, Long> esperado) {

        Map<String, Long> contagemPorFuncao = apiService.contagemPorFuncao(dataInicial, dataFinal, todosOsTimes);
        assertEquals(esperado, contagemPorFuncao);
    }*/

}