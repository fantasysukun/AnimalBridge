package edu.sjsu.rest;
import java.util.HashMap;

import javax.ws.rs.Consumes;
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

//import java.awt.PageAttributes.MediaType;
import java.io.IOException;
import java.sql.SQLException;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
 
//import javax.print.attribute.standard.Media;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
    /*
    @GET
    @Path("/posting/{param}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void insertObject(@PathParam("param") String name, String json) throws JSONException {
    	if (name == "post")
    	{
    		JSONObject jsonObject = new JSONObject(json);    		
        	animalbridge_posting post = new animalbridge_posting(0, jsonObject.getString("category"), jsonObject.getString("priority"), jsonObject.getString("title"), jsonObject.getString("address"), jsonObject.getString("description"), jsonObject.getString("day"), jsonObject.getString("startingTime"), jsonObject.getString("endingTime"), null, jsonObject.getString("price"), jsonObject.getString("email"), 0, "0");
   		    //insertPost(post);
    	}
    	if (name == "user")
    	{
    		JSONObject jsonObject = new JSONObject(json);
    		AnimalBridge_users user = new AnimalBridge_users(0, jsonObject.getString("name"), jsonObject.getString("email"), jsonObject.getString("password"), jsonObject.getString("confirm"), jsonObject.getString("date"), jsonObject.getString("token"));
    		//insertUser(user);
    	}
    	if (name == "animal")
    	{
    		JSONObject jsonObject = new JSONObject(json);
    		animalbridge_animals animal = new animalbridge_animals(0, jsonObject.getString("category"), jsonObject.getString("name"), jsonObject.getString("age"), jsonObject.getString("breeds"), jsonObject.getString("price"), jsonObject.getString("address"),jsonObject.getString("color"), jsonObject.getString("description"), null, jsonObject.getString("size"), jsonObject.getString("gender"), 0, "");
    		//insertUser(animal);
    	}
    	if (name == "emergencycontact")
    	{
    		JSONObject jsonObject = new JSONObject(json);
    		animalbridge_emergencycontact emergency = new animalbridge_emergencycontact(0, jsonObject.getString("title"), jsonObject.getString("description"), jsonObject.getString("date"), jsonObject.getString("zipcode"), null, jsonObject.getString("email"), 0, "");
    		//insertEmergencyContact(emergency);
    	}
    }
    */
    @GET
    @Path("/posting/{param}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public animalbridge_posting savePosting(animalbridge_posting post)
    {
    	return post;
    }
    
    @GET
    @Path("/animals/{param}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public animalbridge_animals saveAnimals(animalbridge_animals animal)
    {
    	return animal;
    }
    
    @GET
    @Path("/users/{param}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AnimalBridge_users saveUser(AnimalBridge_users user)
    {
    	return user;
    }
    
    @GET
    @Path("/emergencycontact/{param}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public animalbridge_emergencycontact saveEmergencyContact(animalbridge_emergencycontact emergency)
    {
    	return emergency;
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
        Gson gson = new Gson();
        String json = gson.toJson(posts);

        return json;
    }
    public static String getHomepage ()
    {
        HashMap<Integer, animalbridge_homepage> home = Model.animalbridge_homepage();
        Gson gson = new Gson();
        String json = gson.toJson(home);

        return json;
    }
    public static String getAboutUs ()
    {
        HashMap<Integer, animalbridge_aboutus> aboutus = Model.animalbridge_aboutus();
        Gson gson = new Gson();
        String json = gson.toJson(aboutus);

        return json;
    }
    public static String getContactUs ()
    {
        HashMap<Integer, animalbridge_contactus> contact = Model.animalbridge_contactus();
        Gson gson = new Gson();
        String json = gson.toJson(contact);

        return json;
    }
    public static String getEmergency ()
    {
        HashMap<Integer, animalbridge_emergencycontact> emergency = Model.animalbridge_emergencycontact();
        Gson gson = new Gson();
        String json = gson.toJson(emergency);

        return json;
    }
    public static String getAnimals ()
    {
        HashMap<Integer, animalbridge_animals> animal = Model.animalbridge_animals();
        Gson gson = new Gson();
        String json = gson.toJson(animal);

        return json;
    }

    public static void main(String[] args) throws IOException
    {	
    	System.out.println(getPost());
    }
   
} 

	
	
