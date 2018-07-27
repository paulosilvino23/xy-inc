package br.com.zup.entity;

import br.com.zup.enums.DataTypeEnum;

public class ModelAttribute {

	private String name;
	private DataTypeEnum type;

	public ModelAttribute(String name, DataTypeEnum type) {
		super();
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public DataTypeEnum getType() {
		return type;
	}

	public void setType(DataTypeEnum type) {
		this.type = type;
	}

}
