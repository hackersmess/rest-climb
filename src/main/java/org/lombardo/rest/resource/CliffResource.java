package org.lombardo.rest.resource;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.lombardo.rest.model.Cliff;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("api/cliffs")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped

public class CliffResource {

	@Inject EntityManager entityManager;

	private Set<Cliff> cliffs = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

	/**
	 * 
	 * CURL PER PROMPT WINDOWS: $ curl http://localhost:8080/api/cliffs
	 *
	 */

	@GET
	public List<Cliff> list() {

		return entityManager.createNamedQuery("Cliffs.findAll", Cliff.class).getResultList();
	}

	/**
	 * 
	 * CURL PER PROMPT WINDOWS: $ curl http://localhost:8080/api/cliffs/1
	 *
	 */

	@GET
	@Path("{id}")
	public Cliff getSingle(@PathParam("id") Integer id) {
		Cliff entity = entityManager.find(Cliff.class, id);
		if (entity == null) {
			throw new WebApplicationException("Fruit with id of " + id + " does not exist.", 404);
		}
		return entity;
	}

	/**
	 * 
	 * CURL PER PROMPT WINDOWS: curl -X POST -H "Content-Type: application/json" -d "{ \"name\": \"Nome della scogliera\", \"description\": \"Descrizione della falesia\" }"
	 * http://localhost:8080/api/cliffs
	 * 
	 */

	@POST
	@Transactional
	public Response create(Cliff cliff) {
		if (cliff.getId() != null) {
			throw new WebApplicationException("Id was invalidly set on request.", 422);
		}

		entityManager.persist(cliff);
		return Response.ok(cliff).status(201).build();
	}

	/**
	 * 
	 * CURL PER PROMPT WINDOWS: curl -X DELETE http://localhost:8080/api/cliffs/1
	 * 
	 */

	@DELETE
	@Path("{id}")
	@Transactional
	public Response delete(@PathParam("id") Integer id) {
		Cliff entity = entityManager.getReference(Cliff.class, id);
		if (entity == null) {
			throw new WebApplicationException("Fruit with id of " + id + " does not exist.", 404);
		}
		entityManager.remove(entity);
		return Response.status(204).build();
	}

}