package com.thengara.cuckoo.extractservice.service;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.thengara.cuckoo.extractservice.model.HealthModel;

@Service
public class DataReadService<T> {
	private static final Logger logger = LoggerFactory
			.getLogger(DataReadService.class);

	public void publish(Consumer<HealthModel> consumer) {

	}

}
