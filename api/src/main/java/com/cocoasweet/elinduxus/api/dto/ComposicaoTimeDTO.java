package com.cocoasweet.elinduxus.api.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComposicaoTimeDTO {

	Long idTime;
	List<Long> idIntegrante;
	
}
