package sample.api.sample.error;

import java.io.Serializable;

public class ApiError implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int status;
	private String message;
	
	public ApiError(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
