
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
    public static Response getUser (int id)
    {
        HashMap<Integer, AnimalBridge_users> users = Model.AnimalBridge_users();
        //Animalbridge_users user = new Animalbridge_users(users.get(id).user_ID)
        Gson gson = new Gson();
        String json = gson.toJson(users.get(id));
        try {
            File file = new File("C:\\", "users.json");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(json);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Response.status(200).entity("").build();
    }
    public static Response getPost (@PathParam("param") int id)
    {
        HashMap<Integer, animalbridge_posting> posts = Model.animalbridge_posting();
        //Animalbridge_users user = new Animalbridge_users(users.get(id).user_ID)
        Gson gson = new Gson();
        String json = gson.toJson(posts.get(id));
        try {
            File file = new File("C:\\", "posts.json");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(json);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Response.status(200).entity("").build();
    }
    public static Response getHomepage (@PathParam("param") int id)
    {
        HashMap<Integer, animalbridge_homepage> home = Model.animalbridge_homepage();
        //Animalbridge_users user = new Animalbridge_users(users.get(id).user_ID)
        Gson gson = new Gson();
        String json = gson.toJson(home);
        try {
            File file = new File("C:\\", "homepage.json");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(json);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Response.status(200).entity("").build();
    }
    public static Response getAboutUs (@PathParam("param") int id)
    {
        HashMap<Integer, animalbridge_aboutus> aboutus = Model.animalbridge_aboutus();
        //Animalbridge_users user = new Animalbridge_users(users.get(id).user_ID)
        Gson gson = new Gson();
        String json = gson.toJson(aboutus);
        try {
            File file = new File("C:\\", "aboutus.json");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(json);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Response.status(200).entity("").build();
    }
    public static Response getContactUs (@PathParam("param") int id)
    {
        HashMap<Integer, animalbridge_contactus> contact = Model.animalbridge_contactus();
        //Animalbridge_users user = new Animalbridge_users(users.get(id).user_ID)
        Gson gson = new Gson();
        String json = gson.toJson(contact);
        try {
            File file = new File("C:\\", "contact.json");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(json);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Response.status(200).entity("").build();
    }
    public static Response getEmergency (@PathParam("param") int id)
    {
        HashMap<Integer, animalbridge_emergencycontact> emergency = Model.animalbridge_emergencycontact();
        //Animalbridge_users user = new Animalbridge_users(users.get(id).user_ID)
        Gson gson = new Gson();
        String json = gson.toJson(emergency.get(id));
        try {
            File file = new File("C:\\", "emergency.json");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(json);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Response.status(200).entity("").build();
    }
    public static Response getAnimals (@PathParam("param") int id)
    {
        HashMap<Integer, animalbridge_animals> animal = Model.animalbridge_animals();
        //Animalbridge_users user = new Animalbridge_users(users.get(id).user_ID)
        Gson gson = new Gson();
        String json = gson.toJson(animal.get(id));
        try {
            File file = new File("C:\\", "animal.json");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(json);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Response.status(200).entity("").build();
    }
    public static File getJson(String name)
    {
        File file = new File("C:\\" + name);
        return file;
    }
    public static void main(String[] args) throws IOException
    {	
    	//getJson("user.json");
    }
   
} //class
	

	
	
