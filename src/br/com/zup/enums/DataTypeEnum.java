package br.com.zup.enums;

@SuppressWarnings("unused")
public enum DataTypeEnum {

	String ("S"),
	Integer ("I"),
	Double ("U"),
	Date ("D");

	private final String cod;

	DataTypeEnum(String cod) {
		this.cod = cod;
	}
}
