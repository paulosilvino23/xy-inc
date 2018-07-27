package br.com.zup.entity;

import java.io.Serializable;
import javax.ws.rs.core.Response.Status;

public class ResponseResource implements Serializable {

	private static final long serialVersionUID = 1L;

	private Status status;

	private String message;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "STATUS: " + status.toString() + ", MESSAGE: " + message;
	}

}
