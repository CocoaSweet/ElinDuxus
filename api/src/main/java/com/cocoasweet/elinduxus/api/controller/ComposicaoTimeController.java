package com.cocoasweet.elinduxus.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cocoasweet.elinduxus.api.dto.ComposicaoTimeDTO;
import com.cocoasweet.elinduxus.api.service.impl.ComposicaoTimeImpl;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/")
public class ComposicaoTimeController {
	
	@Autowired
	private ComposicaoTimeImpl composicaoTime;
	
	@PostMapping("/compor-time")
	public void createComposicaoTime(@RequestBody ComposicaoTimeDTO composicao) {
		composicaoTime.saveComposicaoTime(composicao);
	}

}
