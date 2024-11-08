package com.cocoasweet.elinduxus.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.cocoasweet.elinduxus.api.entity.IntegranteEntity;
import com.cocoasweet.elinduxus.api.entity.TimeEntity;
import com.cocoasweet.elinduxus.api.service.impl.ApiService;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

public class DadosParaTesteApiService {

    // datas
    private final LocalDate data1993 = LocalDate.of(1993,1, 1);
    private final LocalDate data1994 = LocalDate.of(1994,1, 1);
    private final LocalDate data1995 = LocalDate.of(1995,1, 1);

    // franquias
    private final String franquiaNBA = "NBA";


    // integrantes
    // nba
    private IntegranteEntity michael_jordan;
    private IntegranteEntity denis_rodman;
    private IntegranteEntity scottie_pippen;

    private TimeEntity timeChicagoBullsDe1994;
    private @RunWith(DataProviderRunner.class)
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

            List<Time> todosOsTimes = dadosParaTesteApiService.getTodosOsTimes();

            Time timeChicagoBullsDe1995 = dadosParaTesteApiService.getTimeChicagoBullsDe1995();
            Time timeDetroidPistonsDe1993 = dadosParaTesteApiService.getTimeDetroidPistonsDe1993();

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
        public void testTimeDaData(LocalDate data, List<Time> todosOsTimes, Time esperado) {

            Time timeRetornado = apiService.timeDaData(data, todosOsTimes);

            assertEquals(esperado, timeRetornado);
        }



        @DataProvider
        public static Object[][] testIntegranteMaisUsadoParams() {

            DadosParaTesteApiService dadosParaTesteApiService = new DadosParaTesteApiService();

            List<Time> todosOsTimes = dadosParaTesteApiService.getTodosOsTimes();

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
        public void testIntegranteMaisUsado(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes, Integrante esperado) {

            Integrante integranteRetornado = apiService.integranteMaisUsado(dataInicial, dataFinal, todosOsTimes);

            assertEquals(esperado, integranteRetornado);
        }



        @DataProvider
        public static Object[][] testTimeMaisComumParams() {
            DadosParaTesteApiService dadosParaTesteApiService = new DadosParaTesteApiService();
            List<Time> todosOsTimes = dadosParaTesteApiService.getTodosOsTimes();

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

        @Test
        @UseDataProvider("testTimeMaisComumParams")
        public void testTimeMaisComum(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes, List<String> esperado) {

            List<String> nomeDosIntegrantesDoTimeMaisComum = apiService.timeMaisComum(dataInicial, dataFinal, todosOsTimes);

            if(nomeDosIntegrantesDoTimeMaisComum != null){
                nomeDosIntegrantesDoTimeMaisComum.sort(Comparator.naturalOrder());
            }

            assertEquals(esperado, nomeDosIntegrantesDoTimeMaisComum);
        }



        @DataProvider
        public static Object[][] testFuncaoMaisComumParams() {

            DadosParaTesteApiService dadosParaTesteApiService = new DadosParaTesteApiService();
            List<Time> todosOsTimes = dadosParaTesteApiService.getTodosOsTimes();

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
            List<Time> todosOsTimes = dadosParaTesteApiService.getTodosOsTimes();

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
            List<Time> todosOsTimes = dadosParaTesteApiService.getTodosOsTimes();

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
            List<Time> todosOsTimes = dadosParaTesteApiService.getTodosOsTimes();

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
        }

    } timeChicagoBullsDe1995;

    private @RunWith(DataProviderRunner.class)
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

            List<Time> todosOsTimes = dadosParaTesteApiService.getTodosOsTimes();

            Time timeChicagoBullsDe1995 = dadosParaTesteApiService.getTimeChicagoBullsDe1995();
            Time timeDetroidPistonsDe1993 = dadosParaTesteApiService.getTimeDetroidPistonsDe1993();

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
        public void testTimeDaData(LocalDate data, List<Time> todosOsTimes, Time esperado) {

            Time timeRetornado = apiService.timeDaData(data, todosOsTimes);

            assertEquals(esperado, timeRetornado);
        }



        @DataProvider
        public static Object[][] testIntegranteMaisUsadoParams() {

            DadosParaTesteApiService dadosParaTesteApiService = new DadosParaTesteApiService();

            List<Time> todosOsTimes = dadosParaTesteApiService.getTodosOsTimes();

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
        public void testIntegranteMaisUsado(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes, Integrante esperado) {

            Integrante integranteRetornado = apiService.integranteMaisUsado(dataInicial, dataFinal, todosOsTimes);

            assertEquals(esperado, integranteRetornado);
        }



        @DataProvider
        public static Object[][] testTimeMaisComumParams() {
            DadosParaTesteApiService dadosParaTesteApiService = new DadosParaTesteApiService();
            List<Time> todosOsTimes = dadosParaTesteApiService.getTodosOsTimes();

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

        @Test
        @UseDataProvider("testTimeMaisComumParams")
        public void testTimeMaisComum(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes, List<String> esperado) {

            List<String> nomeDosIntegrantesDoTimeMaisComum = apiService.timeMaisComum(dataInicial, dataFinal, todosOsTimes);

            if(nomeDosIntegrantesDoTimeMaisComum != null){
                nomeDosIntegrantesDoTimeMaisComum.sort(Comparator.naturalOrder());
            }

            assertEquals(esperado, nomeDosIntegrantesDoTimeMaisComum);
        }



        @DataProvider
        public static Object[][] testFuncaoMaisComumParams() {

            DadosParaTesteApiService dadosParaTesteApiService = new DadosParaTesteApiService();
            List<Time> todosOsTimes = dadosParaTesteApiService.getTodosOsTimes();

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
            List<Time> todosOsTimes = dadosParaTesteApiService.getTodosOsTimes();

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
            List<Time> todosOsTimes = dadosParaTesteApiService.getTodosOsTimes();

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
            List<Time> todosOsTimes = dadosParaTesteApiService.getTodosOsTimes();

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
        }

    } timeDetroidPistonsDe1993;

    private List<Time> todosOsTimes;

    public DadosParaTesteApiService() {
        inicializa();
    }

    public void inicializa(){

        List<ComposicaoTime> composicaoTime1993 = new ArrayList<>();
        List<ComposicaoTime> composicaoTime1994 = new ArrayList<>();
        List<ComposicaoTime> composicaoTime1995 = new ArrayList<>();
        List<ComposicaoTime> composicaoTime1994E1995 = new ArrayList<>();

        // integrantes
        // nba
        michael_jordan = new IntegranteEntity(franquiaNBA, "Michael Jordan", "ala", composicaoTime1994E1995);
        denis_rodman = new IntegranteEntityte(franquiaNBA, "Denis Rodman", "ala-pivô", composicaoTime1995);
        scottie_pippen = new IntegranteEntity(franquiaNBA, "Scottie Pippen", "ala", composicaoTime1995);

        timeChicagoBullsDe1994 = new @RunWith(DataProviderRunner.class)
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

                List<Time> todosOsTimes = dadosParaTesteApiService.getTodosOsTimes();

                Time timeChicagoBullsDe1995 = dadosParaTesteApiService.getTimeChicagoBullsDe1995();
                Time timeDetroidPistonsDe1993 = dadosParaTesteApiService.getTimeDetroidPistonsDe1993();

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
            public void testTimeDaData(LocalDate data, List<Time> todosOsTimes, Time esperado) {

                Time timeRetornado = apiService.timeDaData(data, todosOsTimes);

                assertEquals(esperado, timeRetornado);
            }



            @DataProvider
            public static Object[][] testIntegranteMaisUsadoParams() {

                DadosParaTesteApiService dadosParaTesteApiService = new DadosParaTesteApiService();

                List<Time> todosOsTimes = dadosParaTesteApiService.getTodosOsTimes();

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
            public void testIntegranteMaisUsado(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes, Integrante esperado) {

                Integrante integranteRetornado = apiService.integranteMaisUsado(dataInicial, dataFinal, todosOsTimes);

                assertEquals(esperado, integranteRetornado);
            }



            @DataProvider
            public static Object[][] testTimeMaisComumParams() {
                DadosParaTesteApiService dadosParaTesteApiService = new DadosParaTesteApiService();
                List<Time> todosOsTimes = dadosParaTesteApiService.getTodosOsTimes();

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

            @Test
            @UseDataProvider("testTimeMaisComumParams")
            public void testTimeMaisComum(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes, List<String> esperado) {

                List<String> nomeDosIntegrantesDoTimeMaisComum = apiService.timeMaisComum(dataInicial, dataFinal, todosOsTimes);

                if(nomeDosIntegrantesDoTimeMaisComum != null){
                    nomeDosIntegrantesDoTimeMaisComum.sort(Comparator.naturalOrder());
                }

                assertEquals(esperado, nomeDosIntegrantesDoTimeMaisComum);
            }



            @DataProvider
            public static Object[][] testFuncaoMaisComumParams() {

                DadosParaTesteApiService dadosParaTesteApiService = new DadosParaTesteApiService();
                List<Time> todosOsTimes = dadosParaTesteApiService.getTodosOsTimes();

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
                List<Time> todosOsTimes = dadosParaTesteApiService.getTodosOsTimes();

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
                List<Time> todosOsTimes = dadosParaTesteApiService.getTodosOsTimes();

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
                List<Time> todosOsTimes = dadosParaTesteApiService.getTodosOsTimes();

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
            }

        }(data1994, composicaoTime1994);
        timeChicagoBullsDe1995 = new Time(data1995, composicaoTime1995);
        timeDetroidPistonsDe1993 = new Time(data1993, composicaoTime1993);

        // composição chicago bulls
        composicaoTime1994.add(new ComposicaoTime(timeChicagoBullsDe1994, michael_jordan));
        composicaoTime1994.add(new ComposicaoTime(timeChicagoBullsDe1994, denis_rodman));
        composicaoTime1994.add(new ComposicaoTime(timeChicagoBullsDe1994, scottie_pippen));

        composicaoTime1995.add(new ComposicaoTime(timeChicagoBullsDe1995, michael_jordan));
        composicaoTime1995.add(new ComposicaoTime(timeChicagoBullsDe1995, denis_rodman));
        composicaoTime1995.add(new ComposicaoTime(timeChicagoBullsDe1995, scottie_pippen));

        // composição detroid pistons
        composicaoTime1993.add(new ComposicaoTime(timeDetroidPistonsDe1993, denis_rodman));


        composicaoTime1994E1995.addAll(composicaoTime1994);
        composicaoTime1994E1995.addAll(composicaoTime1995);

        todosOsTimes = new ArrayList<>();
        todosOsTimes.add(timeChicagoBullsDe1994);
        todosOsTimes.add(timeChicagoBullsDe1995);
        todosOsTimes.add(timeDetroidPistonsDe1993);
    }


    public List<Time> getTodosOsTimes() {
        return todosOsTimes;
    }

    public Time getTimeChicagoBullsDe1995() {
        return timeChicagoBullsDe1995;
    }

    public Time getTimeDetroidPistonsDe1993() {
        return timeDetroidPistonsDe1993;
    }

    public String getFranquiaNBA() {
        return franquiaNBA;
    }

    public Integrante getDenis_rodman() {
        return denis_rodman;
    }

    public Integrante getMichael_jordan() {
        return michael_jordan;
    }

    public Integrante getScottie_pippen() {
        return scottie_pippen;
    }
}