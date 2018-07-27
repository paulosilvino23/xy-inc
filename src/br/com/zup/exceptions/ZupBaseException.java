package br.com.zup.exceptions;

public class ZupBaseException extends Exception {

	private static final long serialVersionUID = 6699050724458435243L;

	String msg;

	public ZupBaseException(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}
}
