package com.cocoasweet.elinduxus.api.service.impl;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cocoasweet.elinduxus.api.dto.TimeDTO;
import com.cocoasweet.elinduxus.api.entity.TimeEntity;
import com.cocoasweet.elinduxus.api.repository.TimeRepository;
import com.cocoasweet.elinduxus.api.service.Time;

@Service
public class TimeImpl implements Time {
	
	@Autowired
	public TimeRepository timeRepository;

	@Override
	public Long saveTime(TimeDTO time) {
		TimeEntity timeEntity = new TimeEntity(time);
		timeRepository.save(timeEntity);
		return timeEntity.getId();
		
	}
	public List<Long> procurarIdPorData(LocalDate data) {
		List<TimeEntity> requestTime = timeRepository.findByData(data);
		List<Long> ids = requestTime.stream().map(TimeEntity::getId).toList();
		return ids;
		
	}

}