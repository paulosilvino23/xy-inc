package br.com.zup.entity;

import java.io.Serializable;

public class Parameter implements Serializable {

	private static final long serialVersionUID = -745990360787274423L;

	private String name;
	private String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
