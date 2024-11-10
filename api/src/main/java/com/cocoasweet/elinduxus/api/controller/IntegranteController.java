package com.cocoasweet.elinduxus.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cocoasweet.elinduxus.api.dto.IntegranteDTO;
import com.cocoasweet.elinduxus.api.entity.IntegranteEntity;
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
	
	@GetMapping("/resgatar-integrantes")
	public List<IntegranteEntity> listaIntegrantes(){
		return integranteService.listarIntegrantes();
	}
}
