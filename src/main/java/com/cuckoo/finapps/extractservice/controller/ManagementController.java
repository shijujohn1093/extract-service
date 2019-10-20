package com.cuckoo.finapps.extractservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cuckoo.finapps.extractservice.model.Response;

@RestController
@RequestMapping("/mgmt")
public class ManagementController {

	private static final Logger logger = LoggerFactory
			.getLogger(ManagementController.class);

	@GetMapping("/healthcheck")
	public Response<String> healthCheck() {
		return new Response.ResponseBuilder<String>()
				.addMessage("Succefully checked.").payload("Hello World !!")
				.build();
	}

}
