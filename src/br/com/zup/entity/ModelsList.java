package br.com.zup.entity;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "modelsList")
public class ModelsList {

	private List<Model> models;
	private AtomicInteger modelId;

	public ModelsList() {
		models = new CopyOnWriteArrayList<Model>();
		modelId = new AtomicInteger();
	}

	@XmlElement(name="model")
	public List<Model> getModels() {
		return this.models;
	}

	public void setModels(List<Model> models) {
		this.models = models;
	}

	@Override
	public String toString() {
		String s = "";
		for(Model model : models) {
			s += model.toString();
		}
		return s;
	}

	public Model find(int id) {

		Model m = null;

		for(Model model : models) {
			if(model.getId() == id) {
				m = model;
				break;
			}
		}
		return m;
	}

	public Model find(String name) {

		Model m = null;

		for(Model model : models) {
			if(model.getName().equalsIgnoreCase(name)) {
				m = model;
				break;
			}
		}
		return m;
	}

	public int add(Model model) {

		int id = modelId.incrementAndGet();
		model.setId(id);
		models.add(model);
		return id;
	}
}
