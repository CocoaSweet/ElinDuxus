package com.cocoasweet.elinduxus.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cocoasweet.elinduxus.api.dto.IntegranteDTO;
import com.cocoasweet.elinduxus.api.service.impl.IntegranteImpl;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/")
public class IntegranteController {
	
	@Autowired
	private IntegranteImpl integranteService;
	
	@PostMapping("/criar-integrante")
	public Long criarIntegrante(@RequestBody IntegranteDTO integrante) {
		return integranteService.saveIntegrante(integrante);
	}
}
