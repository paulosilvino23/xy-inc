package br.com.zup.entity;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "items")
public class ObjectModelsList {

	private List<ObjectModel> objectModels;
	private AtomicInteger objectId;

	public ObjectModelsList() {
		objectModels = new CopyOnWriteArrayList<ObjectModel>();
		objectId = new AtomicInteger();
	}

	@XmlElement(name="objects")
	public List<ObjectModel> getObjectModels() {
		return objectModels;
	}

	public void setObjectModels(List<ObjectModel> objectModels) {
		this.objectModels = objectModels;
	}

	public int add(ObjectModel objectModel) {

		int id = objectId.incrementAndGet();
		objectModel.setId(id);
		objectModels.add(objectModel);
		return id;
	}
}
