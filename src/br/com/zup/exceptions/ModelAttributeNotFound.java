package br.com.zup.exceptions;

public class ModelAttributeNotFound extends ZupBaseException {

	private static final long serialVersionUID = 7300218351668342111L;

	public ModelAttributeNotFound(String msg) {

		super("Attribute " + msg + " not found");
	}
}
