package com.proyecto.airticket.protectController;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AppController {
 
	
	@PostMapping("/prueba")
	public String welcome() {
		
		return "Welcome from secure endpoint";
	}
	
}
