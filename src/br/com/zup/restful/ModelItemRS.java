package br.com.zup.restful;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.zup.entity.Model;
import br.com.zup.entity.ModelAttribute;
import br.com.zup.entity.ObjectModel;
import br.com.zup.entity.ObjectModelAttribute;
import br.com.zup.entity.Parameter;
import br.com.zup.exceptions.DataTypeNotSupported;
import br.com.zup.exceptions.ModelAttributeNotFound;
import br.com.zup.exceptions.ZupBusinessException;
import br.com.zup.repository.ModelRepository;
import br.com.zup.repository.ObjectModelRepository;

@Path("/modelitem")
public class ModelItemRS {

	@POST
	@Path("/{modelName: .*}")
	@Produces({MediaType.TEXT_PLAIN})
	@Consumes(value = MediaType.APPLICATION_JSON)
	public Response createItem(@PathParam("modelName") String modelName, List<Parameter> params) {

		String msg = null;

		try {

			Model m = ModelRepository.getMlist().find(modelName);

			if(m == null)
				throw new ZupBusinessException("Model " + modelName + " not found.\n");

			ObjectModel om = new ObjectModel(m);

			for(Parameter param : params) {

				ModelAttribute attr = m.findAttribute(param.getName());

				om.getAttributes().add(new ObjectModelAttribute(attr, param.getValue()));

			}


			int id = addObjectModel(om);

			msg = "Item " + id + " created successfully.\n";

		}
		catch (ZupBusinessException e) {

			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMsg()).type(MediaType.TEXT_PLAIN).build();
		}
		catch (ModelAttributeNotFound e) {

			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMsg()).type(MediaType.TEXT_PLAIN).build();
		}
		catch (DataTypeNotSupported e) {

			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMsg()).type(MediaType.TEXT_PLAIN).build();
		}
		catch (Exception e) {

			return Response.status(Response.Status.BAD_REQUEST).entity("Fatal error!").type(MediaType.TEXT_PLAIN).build();
		}

		return Response.ok(msg, "text/plain").build();
	}

	@GET
	@Produces({MediaType.TEXT_PLAIN})
	public Response getTest() {

//		return Response.ok("Hello World.\n", "text/plain").build();
		return Response.ok(ObjectModelRepository.getOmlist(), "application/json").build();
	}

	private int addObjectModel(ObjectModel objectModel) {
		return ObjectModelRepository.getOmlist().add(objectModel);
	}
}
