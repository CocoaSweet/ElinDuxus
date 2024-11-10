package com.cocoasweet.elinduxus.api.service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.cocoasweet.elinduxus.api.entity.ComposicaoTimeEntity;
import com.cocoasweet.elinduxus.api.entity.IntegranteEntity;
import com.cocoasweet.elinduxus.api.entity.TimeEntity;

/**
 * Centraliza os dados que existem para os times, integrantes e composições
 */
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
    private TimeEntity timeChicagoBullsDe1995;
    private TimeEntity timeDetroidPistonsDe1993;

    private List<TimeEntity> todosOsTimes;

    public DadosParaTesteApiService() {
        inicializa();
    }

    public void inicializa(){

        List<ComposicaoTimeEntity> composicaoTime1993 = new ArrayList<>();
        List<ComposicaoTimeEntity> composicaoTime1994 = new ArrayList<>();
        List<ComposicaoTimeEntity> composicaoTime1995 = new ArrayList<>();
        List<ComposicaoTimeEntity> composicaoTime1994E1995 = new ArrayList<>();

        // integrantes
        // nba
        michael_jordan = new IntegranteEntity(1, franquiaNBA, "Michael Jordan", "ala");
        denis_rodman = new IntegranteEntity(2, franquiaNBA, "Denis Rodman", "ala-pivô");
        scottie_pippen = new IntegranteEntity(3, franquiaNBA, "Scottie Pippen", "ala");

        timeChicagoBullsDe1994 = new TimeEntity(1, data1994);
        timeChicagoBullsDe1995 = new TimeEntity(2, data1995);
        timeDetroidPistonsDe1993 = new TimeEntity(3, data1993);

        // composição chicago bulls
        composicaoTime1994.add(new ComposicaoTimeEntity(timeChicagoBullsDe1994.getId(), michael_jordan.getId()));
        composicaoTime1994.add(new ComposicaoTimeEntity(timeChicagoBullsDe1994.getId(), denis_rodman.getId()));
        composicaoTime1994.add(new ComposicaoTimeEntity(timeChicagoBullsDe1994.getId(), scottie_pippen.getId()));

        composicaoTime1995.add(new ComposicaoTimeEntity(timeChicagoBullsDe1995.getId(), michael_jordan.getId()));
        composicaoTime1995.add(new ComposicaoTimeEntity(timeChicagoBullsDe1995.getId(), denis_rodman.getId()));
        composicaoTime1995.add(new ComposicaoTimeEntity(timeChicagoBullsDe1995.getId(), scottie_pippen.getId()));

        // composição detroid pistons
        composicaoTime1993.add(new ComposicaoTimeEntity(timeDetroidPistonsDe1993.getId(), denis_rodman.getId()));


        composicaoTime1994E1995.addAll(composicaoTime1994);
        composicaoTime1994E1995.addAll(composicaoTime1995);

        todosOsTimes = new ArrayList<>();
        todosOsTimes.add(timeChicagoBullsDe1994);
        todosOsTimes.add(timeChicagoBullsDe1995);
        todosOsTimes.add(timeDetroidPistonsDe1993);
    }


    public List<TimeEntity> getTodosOsTimes() {
        return todosOsTimes;
    }

    public TimeEntity getTimeChicagoBullsDe1995() {
        return timeChicagoBullsDe1995;
    }

    public TimeEntity getTimeDetroidPistonsDe1993() {
        return timeDetroidPistonsDe1993;
    }
    
    public TimeEntity getTimeDetroidPistonsDe1994() {
        return timeChicagoBullsDe1994;
    }

    public String getFranquiaNBA() {
        return franquiaNBA;
    }

    public IntegranteEntity getDenis_rodman() {
        return denis_rodman;
    }

    public IntegranteEntity getMichael_jordan() {
        return michael_jordan;
    }

    public IntegranteEntity getScottie_pippen() {
        return scottie_pippen;
    }
}