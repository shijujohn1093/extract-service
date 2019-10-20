package com.thengara.cuckoo.extractservice.controller;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.thengara.cuckoo.extractservice.model.HealthModel;
import com.thengara.cuckoo.extractservice.service.DataReadService;

@RestController
@RequestMapping("/extract")
public class DataReadController {
	private static final Logger logger = LoggerFactory
			.getLogger(DataReadController.class);

	private final DataReadService<HealthModel> dataReadService;

	@Autowired
	public DataReadController(DataReadService<HealthModel> dataReadService) {
		super();
		this.dataReadService = dataReadService;
	}

	private ExecutorService executor = Executors.newCachedThreadPool();

	private final long maximumCount = 1000000;

	/**
	 * ResponseBodyEmitter : It took 6 minutes to fetch 1Million individual
	 * records from node client.
	 * 
	 * Response Header : {"transfer-encoding":"chunked","date":"Sun, 20 Oct 2019
	 * 09:08:45 GMT","connection":"close"}
	 * 
	 * 
	 * @return
	 */
	@GetMapping("/rbe")
	public ResponseEntity<ResponseBodyEmitter> handleRbe(
			@RequestParam String profileId) {
		ResponseBodyEmitter emitter = new ResponseBodyEmitter();
		Date startDate = new Date();
		executor.execute((() -> {
			try {
				long count = 0;
				while (true) {
					if (count == maximumCount) {
						break;
					}
					HealthModel healthModel = new HealthModel(count++, "/rbe",
							startDate, new Date(), Boolean.TRUE);
					logger.info("Sending data " + healthModel.toString());
					emitter.send(healthModel, MediaType.APPLICATION_JSON);
				}
				emitter.complete();
			} catch (Exception ex) {
				logger.error("Exception occured !!", ex);
				emitter.completeWithError(ex);
			}
		}));
		return new ResponseEntity<ResponseBodyEmitter>(emitter, HttpStatus.OK);
	}

	/**
	 * SseEmitter : It took 16 minutes to fetch 1Million individual records from
	 * node client.
	 * 
	 * Response Header :
	 * {"content-type":"text/event-stream;charset=UTF-8","transfer-encoding":"chunked","date":"Sun,
	 * 20 Oct 2019 09:08:08 GMT","connection":"close"}
	 * 
	 * @return
	 */
	@GetMapping("/sse")
	public SseEmitter handleSse(@RequestParam String profileId) {
		SseEmitter emitter = new SseEmitter();
		Date startDate = new Date();
		executor.execute(() -> {
			try {
				long count = 0;
				while (true) {
					if (count == maximumCount) {
						break;
					}
					HealthModel healthModel = new HealthModel(count++, "/sse",
							startDate, new Date(), Boolean.TRUE);
					logger.info("Sending data " + healthModel.toString());
					emitter.send(healthModel, MediaType.APPLICATION_JSON);
				}
				emitter.complete();
			} catch (Exception ex) {
				logger.error("Exception occured !!", ex);
				emitter.completeWithError(ex);
			}
		});
		return emitter;
	}

	/**
	 * StreamingResponseBody : It took 7 minutes to fetch 1Million individual
	 * records from node client.
	 * 
	 * Response Header : {"transfer-encoding":"chunked","date":"Sun, 20 Oct 2019
	 * 09:06:59 GMT","connection":"close"}
	 * 
	 * @return
	 */
	@GetMapping("/srb")
	public ResponseEntity<StreamingResponseBody> handleSrb(
			@RequestParam String profileId) {
		Date startDate = new Date();
		StreamingResponseBody stream = out -> {
			long count = 0;
			while (true) {
				if (count == maximumCount) {
					break;
				}
				HealthModel healthModel = new HealthModel(count++, "/srb",
						startDate, new Date(), Boolean.TRUE);
				logger.info("Sending data " + healthModel.toString());
				out.write(healthModel.toString().getBytes());
				out.flush();
			}
			out.close();
		};
		return new ResponseEntity<StreamingResponseBody>(stream, HttpStatus.OK);
	}
}
