package com.cocoasweet.elinduxus.api.dto;

import java.time.LocalDate;
import org.springframework.beans.BeanUtils;
import com.cocoasweet.elinduxus.api.entity.TimeEntity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class RequestTimeDTO {

	private long id;
	private LocalDate data;

	public RequestTimeDTO(TimeEntity time) {
		BeanUtils.copyProperties(time, this);
	}

}
