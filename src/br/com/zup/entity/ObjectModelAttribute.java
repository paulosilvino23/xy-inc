package br.com.zup.entity;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import br.com.zup.enums.DataTypeEnum;
import br.com.zup.exceptions.DataTypeNotSupported;

public class ObjectModelAttribute implements Serializable {

	private static final long serialVersionUID = -7164698271575456051L;

	private ModelAttribute metadado;
	private Object value;

	public ObjectModelAttribute() {

	}

	public ObjectModelAttribute(ModelAttribute modelAttribute, Object value) throws DataTypeNotSupported {

		this.metadado = modelAttribute;

		if (ObjectModelAttribute.isSupportedValue(modelAttribute, value))
			this.value = value;

	}

	public ModelAttribute getMetadado() {
		return metadado;
	}

	public void setMetadado(ModelAttribute metadado) {
		this.metadado = metadado;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	private static boolean isSupportedValue(ModelAttribute metadado, Object value) throws DataTypeNotSupported {

		if (metadado.getType() == DataTypeEnum.Double) {
			try {
				Double.parseDouble(value.toString());
			}
			catch (NumberFormatException e) {
				throw new DataTypeNotSupported("Value is not a " + DataTypeEnum.Double.toString() + " value.");
			}
		}
		else if (metadado.getType() == DataTypeEnum.Integer) {
			try {
				Integer.parseInt(value.toString());
			}
			catch (NumberFormatException e) {
				throw new DataTypeNotSupported("Value is not a " + DataTypeEnum.Integer.toString() + " value.");
			}
		}
		else if (metadado.getType() == DataTypeEnum.Date) {
			try {
				new SimpleDateFormat("dd/MM/yyyy").parse(value.toString());
			}
			catch (ParseException e) {
				throw new DataTypeNotSupported("Value is not a " + DataTypeEnum.Date.toString() + " value.");
			}
		}

		return true;
	}

}
