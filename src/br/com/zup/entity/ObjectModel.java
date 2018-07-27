package br.com.zup.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class ObjectModel implements Serializable {

	private static final long serialVersionUID = 7459871273343313401L;

	private int id;
	private Model metadado;
	private List<ObjectModelAttribute> attributes;

	public ObjectModel() {

	}

	public ObjectModel(Model model) {
		super();
		this.metadado = model;
		this.attributes = new ArrayList<ObjectModelAttribute>();
	}

	@XmlElement
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlElement
	public Model getMetadado() {
		return metadado;
	}

	public void setMetadado(Model metadado) {
		this.metadado = metadado;
	}

	@XmlElement
	public List<ObjectModelAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<ObjectModelAttribute> attributes) {
		this.attributes = attributes;
	}

}
