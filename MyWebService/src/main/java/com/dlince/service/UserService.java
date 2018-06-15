package com.dlince.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dlince.dao.UserDAO;
import com.dlince.model.User;

@Path("/users")
public class UserService {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsers() {
		List<User>list = UserDAO.getUsers();
		return Response.ok(list).build();
	}	
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser(@PathParam("id") Integer id) {
		User user = UserDAO.getUser(id);
		return Response.ok(user).build();
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUser(@PathParam("id") Integer id) {
		UserDAO.deleteUser(id);
		return Response.status(Response.Status.OK).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addUser(User user) {
		UserDAO.addUser(user);
		return Response.status(Response.Status.CREATED).entity(user).build();
	}
	
}
