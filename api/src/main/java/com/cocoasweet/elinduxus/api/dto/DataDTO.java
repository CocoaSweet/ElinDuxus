package com.cocoasweet.elinduxus.api.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DataDTO {

	LocalDate dataInicial;
	LocalDate dataFinal;
}
