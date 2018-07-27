package br.com.zup.repository;

import br.com.zup.entity.ModelsList;

public class ModelRepository {

	public static ModelsList mlist;

	static {

		if (getMlist() == null) {
			mlist = new ModelsList();
		}
	}

	public static ModelsList getMlist() {
		return mlist;
	}

}
