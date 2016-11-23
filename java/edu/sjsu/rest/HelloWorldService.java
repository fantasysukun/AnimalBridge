package edu.sjsu.rest;

import java.sql.SQLException;
import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import edu.sjsu.db.AnimalBridge_users;
import edu.sjsu.db.Dao;
import edu.sjsu.db.Model;
@Path("/hello")
public class HelloWorldService {
	@GET
	@Path("/{param}")
	public Response getMsg(@PathParam("param") String msg) {
		String output = "Hello " + msg;
		
		HashMap<Integer, AnimalBridge_users> Testing = Model.AnimalBridge_users();
		output += "Running";
		return Response.status(200).entity(output).build();
	} //method
	
	@GET
	@Path("/phone/{param}")
	public Response getPhone(@PathParam("param") String name) {
		String output = "";
		try {
			output = Dao.getPhone(name);
		} catch (SQLException e) {
			e.printStackTrace();
			output += "{\"phone\" : \"Name not found!\"}";
		}
		return Response.status(200).entity(output).build();
	} //method
} //class