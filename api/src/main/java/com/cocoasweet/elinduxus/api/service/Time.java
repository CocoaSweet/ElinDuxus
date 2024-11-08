package com.cocoasweet.elinduxus.api.service;

import java.time.LocalDate;
import java.util.List;
import com.cocoasweet.elinduxus.api.dto.TimeDTO;

public interface Time {

	Long saveTime(TimeDTO time);
	List<Long> procurarIdPorData(LocalDate data);
	List<Long> procurarIdsPorData(LocalDate dataInicial, LocalDate dataFinal);

}
