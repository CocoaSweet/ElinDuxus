package com.cocoasweet.elinduxus.api.controller;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cocoasweet.elinduxus.api.service.impl.ApiService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/")
public class ApiController {

	@Autowired
	private ApiService apiService;
	
	@GetMapping("/time-da-data")
	public List<String> timeDaData(@RequestParam("data") LocalDate data){
		return apiService.timeDaData(data);
	}
	
	
}
