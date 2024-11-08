package com.cocoasweet.elinduxus.api.entity;

import java.time.LocalDate;
import org.springframework.beans.BeanUtils;
import com.cocoasweet.elinduxus.api.dto.TimeDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "times")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TimeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_time")
	private long id;

	@Column(name = "data")
	private LocalDate data;
	
	public TimeEntity(TimeDTO time) {
		BeanUtils.copyProperties(time, this);
	}
}
