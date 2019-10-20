package com.thengara.cuckoo.extractservice.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.thengara.cuckoo.extractservice.interceptor.RequestLoggingInterceptor;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
	private static final Logger logger = LoggerFactory
			.getLogger(WebConfig.class);
	private final RequestLoggingInterceptor requestLoggingInterceptor;

	public WebConfig(RequestLoggingInterceptor requestLoggingInterceptor) {
		super();
		this.requestLoggingInterceptor = requestLoggingInterceptor;
	}

	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(requestLoggingInterceptor);
	}
	//
	// @Bean(name = "taskExecutor")
	// public AsyncTaskExecutor getAsyncExecutor() {
	// logger.debug("Creating Async Task Executor");
	// ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	// executor.setCorePoolSize(5);
	// executor.setMaxPoolSize(10);
	// executor.setQueueCapacity(25);
	// return executor;
	// }

	@Bean(name = "taskExecutor")
	public AsyncTaskExecutor getAsyncExecutor() {
		logger.debug("Creating Async Task Executor");
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(5);
		executor.setMaxPoolSize(20);
		executor.setQueueCapacity(1000);
		executor.setThreadNamePrefix("async-task-");
		return executor;
	}

	@Override
	protected void configureAsyncSupport(AsyncSupportConfigurer configurer) {
		configurer.setDefaultTimeout(1800000L)
				.setTaskExecutor(getAsyncExecutor());

		super.configureAsyncSupport(configurer);
	}

}