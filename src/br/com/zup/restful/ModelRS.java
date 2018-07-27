package br.com.zup.restful;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.zup.entity.Model;
import br.com.zup.entity.ModelAttribute;
import br.com.zup.enums.DataTypeEnum;
import br.com.zup.exceptions.ZupBusinessException;
import br.com.zup.repository.ModelRepository;

@Path("/model")
public class ModelRS {

	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Response getModel() {

		return Response.ok(ModelRepository.getMlist(), "application/json").build();
	}

	@GET
	@Path("/{id: \\d+}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getModel(@PathParam("id") int id) {

		try {

			Model m = ModelRepository.getMlist().find(id);

			if (m == null)
				throw new ZupBusinessException("Model " + id + " not found.\n");

			return Response.ok(m, "application/json").build();
		}
		catch (ZupBusinessException e) {

			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMsg()).type(MediaType.TEXT_PLAIN).build();
		}

	}

	@POST
	@Produces({MediaType.TEXT_PLAIN})
	public Response createModel(@FormParam("name") String name) {

		try {

			// Properties required
			if(name == null) {
				throw new ZupBusinessException("Property 'name' is missing.\n");
			}

			// Otherwise, create the Model and add it to the collection.
			int id = addModel(new Model(name));

			return Response.ok("Model " + id + " created: (name = " + name + ").\n", "text/plain").build();

		}
		catch (ZupBusinessException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMsg()).type(MediaType.TEXT_PLAIN).build();
		}

	}

	@PUT
	@Path("/{modelName: .*}")
	@Produces({MediaType.TEXT_PLAIN})
	public Response updateModel(@PathParam("modelName") String modelName,
			@FormParam("name") String name) {

		try {

			Model m = ModelRepository.getMlist().find(modelName);

			if(m == null)
				throw new ZupBusinessException("Model " + modelName + " not found.\n");

			if(name == null)
				throw new ZupBusinessException("Property 'name' is missing.\n");

			// Update.
			m.setName(name);

			return Response.ok("Model " + m.getId() + " updated successfully.\n", "text/plain").build();
		}
		catch (ZupBusinessException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMsg()).type(MediaType.TEXT_PLAIN).build();
		}

	}

	@DELETE
	@Path("/{modelName: .*}")
	@Produces({MediaType.TEXT_PLAIN})
	public Response deleteModel(@PathParam("modelName") String modelName) {

		try {

			Model m = ModelRepository.getMlist().find(modelName);

			if(m == null)
				throw new ZupBusinessException("Model " + modelName + " not found.\n");

			// Delete.
			ModelRepository.getMlist().getModels().remove(m);

			return Response.ok("Model " + m.getId() + " deleted successfully.\n", "text/plain").build();
		}
		catch (ZupBusinessException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMsg()).type(MediaType.TEXT_PLAIN).build();
		}

	}

	@POST
	@Path("/{modelName: .*}")
	@Produces({MediaType.TEXT_PLAIN})
	public Response createAttribute(@PathParam("modelName") String modelName,
			@FormParam("attributeName") String attributeName,
			@FormParam("attributeDataType") String attributeDataType) {

		try {

			Model m = ModelRepository.getMlist().find(modelName);

			if (m == null)
				throw new ZupBusinessException("Model " + modelName + " not found.\n");

			// Properties are required
			if(attributeName == null || attributeDataType == null)
				throw new ZupBusinessException("Parameter is missing.\n");

			// Check is data type is supported
			if(!attributeDataType.equals(DataTypeEnum.String.toString())
					&& !attributeDataType.equals(DataTypeEnum.Integer.toString())
					&& !attributeDataType.equals(DataTypeEnum.Date.toString())
					&& !attributeDataType.equals(DataTypeEnum.Double.toString())) {

				throw new ZupBusinessException("Data Type not supported.\n\nTry " + DataTypeEnum.String.toString()
												+ ", " + DataTypeEnum.Integer.toString()
												+ ", " + DataTypeEnum.Double.toString()
												+ ", " + DataTypeEnum.Date.toString() + "\n");
			}

			// Add new attribute
			m.getAttributes().add(new ModelAttribute(attributeName, DataTypeEnum.valueOf(attributeDataType)));

			return Response.ok("Attribute added successfully for Model " + m.getId() + " (name = " + m.getName() + ").\n", "text/plain").build();

		}
		catch (ZupBusinessException e) {

			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMsg()).type(MediaType.TEXT_PLAIN).build();
		}

	}

	private int addModel(Model model) {
		return ModelRepository.getMlist().add(model);
	}

}
