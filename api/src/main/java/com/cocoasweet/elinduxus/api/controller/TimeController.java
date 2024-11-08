package com.cocoasweet.elinduxus.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cocoasweet.elinduxus.api.dto.TimeDTO;
import com.cocoasweet.elinduxus.api.service.impl.TimeImpl;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/")
public class TimeController {

	@Autowired
	private TimeImpl timeService;
	
	@PostMapping("/criar-time")
	public Long criarTime(@RequestBody TimeDTO time) {
		return timeService.saveTime(time);
	}
}
