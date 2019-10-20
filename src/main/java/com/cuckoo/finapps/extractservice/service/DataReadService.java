package com.cuckoo.finapps.extractservice.service;

import java.util.Date;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cuckoo.finapps.extractservice.model.HealthModel;

@Service
public class DataReadService<T> {
	private static final Logger logger = LoggerFactory
			.getLogger(DataReadService.class);
	private final long maximumCount = 1000000;

	public void publish(Consumer<HealthModel> consumer) {
		long count = 0;
		Date startDate = new Date();
		while (true) {
			if (count == maximumCount) {
				break;
			}
			try {
				HealthModel healthModel = new HealthModel(count++, "/rbe",
						startDate, new Date(), Boolean.TRUE);
				logger.info("Sending data " + healthModel.toString());
				consumer.accept(healthModel);
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}

		}
	}

}
