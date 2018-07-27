package br.com.zup.repository;

import br.com.zup.entity.ObjectModelsList;

public class ObjectModelRepository {

	public static ObjectModelsList omlist;

	static {

		if (getOmlist() == null) {
			omlist = new ObjectModelsList();
		}
	}

	public static ObjectModelsList getOmlist() {
		return omlist;
	}
}
