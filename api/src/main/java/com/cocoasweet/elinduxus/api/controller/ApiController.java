package com.cocoasweet.elinduxus.api.controller;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.cocoasweet.elinduxus.api.dto.DataDTO;
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
	
	@PostMapping("/integrante-mais-usado")
	public String integranteMaisUsado(@RequestBody DataDTO datas ) {
		return apiService.integranteMaisUsado(datas).toString();
	}
	
	@PostMapping("/time-mais-comum")
	public List<String> timeMaisComum(@RequestBody DataDTO datas){
		return apiService.timeMaisComum(datas);
	}
	
	@PostMapping("/funcao-mais-comum")
	public String funcaoMaisComum(@RequestBody DataDTO datas) {
		return apiService.funcaoMaisComum(datas);
	}
	
	@PostMapping("/franquia-mais-famosa")
	public String franquiaMaisFamosa(@RequestBody DataDTO datas) {
		return apiService.franquiaMaisFamosa(datas);
	}
	
	
}
