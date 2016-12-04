package edu.sjsu.rest;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import edu.sjsu.db.animalbridge_users;
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
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.annotations.SerializedName;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

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
    public static Response getMsg(@PathParam("param") String msg) {
        String output = "Hello " + msg;

        HashMap<Integer, animalbridge_users> Testing = Model.animalbridge_users();
        System.out.println(Testing.get(5).toString());
        output += "Running";
        Gson gson = new Gson();
        animalbridge_users newUser = new animalbridge_users(888, "marco kuang", "marco@gmail.com", "asdfasdf", "asdfasdfrerr", "xxxx", "yyyy");
        String json = gson.toJson(newUser);
        return Response.status(200).entity(json).build();
//        return Response.status(200).entity(Testing.get(5).Getuser_Name()).build();
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
    		animalbridge_users user = new animalbridge_users(0, jsonObject.getString("name"), jsonObject.getString("email"), jsonObject.getString("password"), jsonObject.getString("confirm"), jsonObject.getString("date"), jsonObject.getString("token"));
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
    @Path("/posting/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void savePosting(animalbridge_posting post)
    {
    	Model.Addanimalbridge_posting(post);
    }

    @GET
    @Path("/animals/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public static void saveAnimals(animalbridge_animals animal)
    {
    	Model.Addanimalbridge_animals(animal);
    }

    @GET
    @Path("/users/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public static void saveUser(animalbridge_users user)
    {
    	Model.Addanimalbridge_users(user);
    }

    @GET
    @Path("/emergencycontact/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public static animalbridge_emergencycontact saveEmergencyContact(animalbridge_emergencycontact emergency)
    {
    	return emergency;
    }

    @GET
    @Path("/testGetxxxx/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public static Response getUser ()
    {
        HashMap<Integer, animalbridge_users> users = Model.animalbridge_users();
        Gson gson = new Gson();
        String json = gson.toJson(users.toString());

//        return json;
        return Response.status(200).entity(json).build();
    }

    @GET
    @Path("/testGet/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public static Response getJSON ()
    {
    	String json = "{\"phone\" : \"Name not found!\"}";
    	Gson gson = new Gson();
        String json2 = gson.toJson(json);
    	return Response.status(200).entity(json).build();
    }

    @POST
    @Path("/testPost")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
//	public Response crunchifyREST(InputStream incomingData) {
    public Response crunchifyREST(String JSONData) {
    	Gson gson = new Gson();
    	HashMap<Integer, animalbridge_users> Testing = Model.animalbridge_users();
    	Wrapper ee = null;
    	try{
//    		String kk = "{\"email\": \"dd\", \"password\":\"rr\"}";
    		String kk = JSONData.replaceAll("\"", "\\\"");
    		ee = gson.fromJson(kk, Wrapper.class);
    		System.out.println(ee.email);
    		System.out.println(ee.PWD);
    	}
    	catch(Exception r){
    		r.printStackTrace();
    	}
//		StringBuilder crunchifyBuilder = new StringBuilder();
//		try {
//			BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
//			String line = null;
//			while ((line = in.readLine()) != null) {
//				System.out.println(line);
//				crunchifyBuilder.append(line);
//			}
//		} catch (Exception e) {
//			System.out.println("Error Parsing: - ");
//		}
//		System.out.println("Data Received: " + crunchifyBuilder.toString());
//		Wrapper w = gson.fromJson(new Reader., Wrapper.class);
		boolean valid = false;
	    String tempEmail = ee.email;
	    String tempPassword = ee.PWD;
		for(Entry<Integer, animalbridge_users> entry : Testing.entrySet()) {
		    int key = (int) entry.getKey();
		    animalbridge_users value = entry.getValue();
//		    String tempEmail = gson.fromJson("\"email\"", String.class);
//		    String tempPassword = gson.fromJson("\"email\"", String.class);

		    System.out.println("SEARCHING: email: " + value.Getuser_Email() + " password: "+ value.Getuser_Pass());
		    if(value.Getuser_Email().equalsIgnoreCase(tempEmail) && value.Getuser_Pass().equalsIgnoreCase(tempPassword)){
		    	valid = true;
		    	System.out.println("FOUND::: email: " + tempEmail + " password: "+ tempPassword);
		    	break;
		    }
		}


		if(valid){
			String json = "{\"user\" : \"true\"}";
			return Response.status(200).entity(json).build();
		}
		else{
			String json = "{\"user\" : \"false\"}";
			return Response.status(200).entity(json).build();
		}
		// return HTTP response 200 in case of success
//		return Response.status(200).entity(crunchifyBuilder.toString()).build();
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
        animalbridge_users user = new animalbridge_users(10, "name", "123@gmail.com", "pass", "N", "2016-12-01", "0001");
        //saveUser(user);
    	System.out.println(getPost());
    	//getMsg("abc");
    }

    public class Wrapper{
    	@SerializedName("email")
    	public String email;
    	@SerializedName("password")
    	public String PWD;
    }

//    public class Email{
//    	public String e;
//    }
//
//    public class Password{
//    	public String pwd;
//    }

}
