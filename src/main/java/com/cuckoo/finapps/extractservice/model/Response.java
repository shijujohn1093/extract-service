package com.cuckoo.finapps.extractservice.model;

import java.util.ArrayList;
import java.util.List;

public class Response<T> {

	private T payload;
	private boolean isSuccess;
	private List<String> messages;
	private List<String> errorMessage;

	public Response(T payload, boolean isSuccess, List<String> messages,
			List<String> errorMessage) {
		super();
		this.payload = payload;
		this.isSuccess = isSuccess;
		this.messages = messages;
		this.errorMessage = errorMessage;
	}

	public T getPayload() {
		return payload;
	}

	public void setPayload(T payload) {
		this.payload = payload;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public List<String> getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(List<String> errorMessage) {
		this.errorMessage = errorMessage;
	}

	public static class ResponseBuilder<T> {

		private T payload;
		private boolean isSuccess;
		private List<String> messages = new ArrayList<String>();
		private List<String> errorMessages = new ArrayList<String>();

		public Response<T> build() {
			Response<T> response = new Response<T>(payload, isSuccess, messages,
					errorMessages);
			return response;
		}

		public ResponseBuilder<T> payload(T payload) {
			this.payload = payload;
			return this;
		}

		public ResponseBuilder<T> success(boolean isSuccess) {
			this.isSuccess = isSuccess;
			return this;
		}

		public ResponseBuilder<T> message(List<String> messages) {
			this.messages = messages;
			return this;
		}
		public ResponseBuilder<T> errorMessage(List<String> errorMessages) {
			this.errorMessages = errorMessages;
			return this;
		}
		public ResponseBuilder<T> addErrorMessage(String errorMessage) {
			errorMessages.add(errorMessage);
			return this;
		}

		public ResponseBuilder<T> addMessage(String message) {
			messages.add(message);
			return this;
		}
	}

}
