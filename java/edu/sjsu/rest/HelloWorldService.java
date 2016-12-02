
package edu.sjsu.rest;

import java.sql.SQLException;
import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

import edu.sjsu.db.AnimalBridge_users;
import edu.sjsu.db.Dao;
import edu.sjsu.db.Model;
import edu.sjsu.db.animalbridge_aboutus;
import edu.sjsu.db.animalbridge_animals;
import edu.sjsu.db.animalbridge_contactus;
import edu.sjsu.db.animalbridge_emergencycontact;
import edu.sjsu.db.animalbridge_homepage;
import edu.sjsu.db.animalbridge_posting;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

//add <dependency>
//<groupId>com.google.code.gson</groupId>
//<artifactId>gson</artifactId>
//<version>2.2.4</version>
//</dependency> in pom file
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
    }
    public void insertObject(@PathParam("param") String name, String json) throws JSONException {

    	if (name == "aboutus")
    	{
    		insertAboutUs(json);
    	}
    }
    public animalbridge_aboutus insertAboutUs(String json) throws JSONException
    {
    	//JSONObject jsonObject = new JSONObject(json);
    	//animalbridge_aboutus aboutus = new animalbridge_aboutus(json.getInt(AboutUs_ID), json.get);
		return null;
    	
    }
    public static String getUser ()
    {
        HashMap<Integer, AnimalBridge_users> users = Model.AnimalBridge_users();
        Gson gson = new Gson();
        String json = gson.toJson(users);

        return json;
    }
    public static String getPost ()
    {
        HashMap<Integer, animalbridge_posting> posts = Model.animalbridge_posting();
        //Animalbridge_users user = new Animalbridge_users(users.get(id).user_ID)
        Gson gson = new Gson();
        String json = gson.toJson(posts);

        return json;
    }
    public static String getHomepage ()
    {
        HashMap<Integer, animalbridge_homepage> home = Model.animalbridge_homepage();
        //Animalbridge_users user = new Animalbridge_users(users.get(id).user_ID)
        Gson gson = new Gson();
        String json = gson.toJson(home);

        return json;
    }
    public static String getAboutUs ()
    {
        HashMap<Integer, animalbridge_aboutus> aboutus = Model.animalbridge_aboutus();
        //Animalbridge_users user = new Animalbridge_users(users.get(id).user_ID)
        Gson gson = new Gson();
        String json = gson.toJson(aboutus);

        return json;
    }
    public static String getContactUs ()
    {
        HashMap<Integer, animalbridge_contactus> contact = Model.animalbridge_contactus();
        //Animalbridge_users user = new Animalbridge_users(users.get(id).user_ID)
        Gson gson = new Gson();
        String json = gson.toJson(contact);

        return json;
    }
    public static String getEmergency ()
    {
        HashMap<Integer, animalbridge_emergencycontact> emergency = Model.animalbridge_emergencycontact();
        //Animalbridge_users user = new Animalbridge_users(users.get(id).user_ID)
        Gson gson = new Gson();
        String json = gson.toJson(emergency);

        return json;
    }
    public static String getAnimals ()
    {
        HashMap<Integer, animalbridge_animals> animal = Model.animalbridge_animals();
        //Animalbridge_users user = new Animalbridge_users(users.get(id).user_ID)
        Gson gson = new Gson();
        String json = gson.toJson(animal);

        return json;
    }

    public static void main(String[] args) throws IOException
    {	
    	System.out.println(getAnimals());
    }
   
} //class
	

	
	
