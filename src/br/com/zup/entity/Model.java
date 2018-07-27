package br.com.zup.entity;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.zup.exceptions.ModelAttributeNotFound;

@XmlRootElement
public class Model implements Comparable<Model> {

	private int id;
	private String name;
	private List<ModelAttribute> attributes;

	public Model() {

	}

	public Model(String name) {
		super();
		this.name = name;
		this.attributes = new ArrayList<ModelAttribute>();
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAttributes(List<ModelAttribute> attributes) {
		this.attributes = attributes;
	}

	@XmlElement
	public int getId() { return id; }

	@XmlElement
	public String getName() { return name; }

	@XmlElement
	public List<ModelAttribute> getAttributes() {
		return attributes;
	}

	@Override
	public String toString() {

		String listAttrs = null;

		for (ModelAttribute attr : this.attributes) {
			listAttrs += attr + "\n";
		}

		return String.format("%2d: ", id) + name + " ==> " + "\r\n\n" + listAttrs;
	}

	public int compareTo(Model other) {
		return this.id - other.id;
	}

	public boolean hasAttribute(String name) {

		for (ModelAttribute attr : this.getAttributes()) {
			if (attr.getName().equals(name)) {
				return true;
			}
		}

		return false;
	}

	public ModelAttribute findAttribute(String name) throws ModelAttributeNotFound {

		for (ModelAttribute attr : this.getAttributes()) {
			if (attr.getName().equals(name)) {
				return attr;
			}
		}

		throw new ModelAttributeNotFound(name);
	}
}
