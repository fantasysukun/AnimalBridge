package edu.sjsu.db;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.imageio.ImageIO;

import java.sql.Blob;

/**
 * COPYRIGHT 2016 TeamMinion. All Rights Reserved. 
 * Animal Bridge
 * CS160 Group Project
 * @author Kun Su, Archer Zhao, Nelson Liang, Marco Kuang, Peilu Liu
 * @version 1.00 2016/11/22
 */

public class Model {

	// variables used for manager
	private static Connection connection = Dao.getConn();
	private static Statement statement = Dao.getStatement(connection);
	
	/**
	 * Generate a HashMap with attribute's name and data type from a txt file with DB table attribute
	 * @param ClassName DB table's name
	 * @return HashMap with attribute's name and data type
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static HashMap<Integer, Attribute> DBinput(String ClassName) {
		
		HashMap<Integer, Attribute> MashMapInput = new HashMap<Integer, Attribute>();
		//Get DB input from txt file
		try(BufferedReader br = new BufferedReader(new FileReader(ClassName))) {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    String everything = sb.toString();
		    String[] Result = everything.split(", ");
		    
			for(int i = 0; i < Result.length; i++) {
				if(i == Result.length-1){
					Result[i] = Result[i].substring(0, Result[i].length()-2);	//Delete the \n at the end of an array -Kun
				}
				MashMapInput.put(i, new Attribute(Result[i]));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return MashMapInput;
	}
	
	/**
	 * Convert Blob type of data from DB To bufferedImage type of data in Java
	 * @param blob
	 * @return bufferedImage type of data in Java
	 */
	public static BufferedImage ConvertBlobTobufferedImage (Blob blob){

		BufferedImage Image = null;
		try {
	        int blobLength = (int) blob.length();
	        byte[] blobAsBytes = blob.getBytes(1, blobLength);
        
			Image = ImageIO.read(new ByteArrayInputStream(blobAsBytes));
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Image;
	}
	
	public static HashMap<Integer, AnimalBridge_users> AnimalBridge_users() {
		HashMap<Integer, AnimalBridge_users> ResultMap = new HashMap<Integer, AnimalBridge_users>();

		String SQLquery = "SELECT * FROM AnimalBridge_users;";
		try {
			ResultSet rs = statement.executeQuery(SQLquery);
			while (rs.next()) {
				AnimalBridge_users User = new AnimalBridge_users(rs.getInt("user_ID"), rs.getString("user_Name"), rs.getString("user_Email"), 
						rs.getString("user_Pass"), rs.getString("user_ComfirmStatus"), rs.getDate("user_RegisteredDate"), rs.getString("tokenCode"));
				ResultMap.put(rs.getInt("user_ID"), User);
			}
			rs.close();
			return ResultMap;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Auto Generate HashMap for each class
	 * @param ClassName
	 */
	public static void HashMapGenerator(String ClassName) {
		
		HashMap<Integer, Attribute> MashMapInput = DBinput(ClassName);
		ClassName = ClassName.substring(0, ClassName.length()-4);
		String Output = "";
		Output += "	public static HashMap<Integer, " + ClassName + "> " + ClassName + "() {\n\n";
		Output += "		HashMap<Integer, " + ClassName + "> ResultMap = new HashMap<Integer, " + ClassName + ">();\n";
		Output += "		String SQLquery = \"SELECT * FROM " + ClassName + ";\";\n";
		Output += "		try {\n";
		Output += "			ResultSet rs = statement.executeQuery(SQLquery);\n";
		Output += "			while (rs.next()) {\n";
		Output += "				" +ClassName + " NewObject = new " + ClassName + "(\n";
		for(int i = 0 ; i < MashMapInput.size(); i++) {
			String AttributeName = MashMapInput.get(i).GetAttributeName();
			if(AttributeName.contains("Image")) {
				Output += "					ConvertBlobTobufferedImage(rs.get";
				Output += "Blob";
				Output += "(\"" + MashMapInput.get(i).GetAttributeName() + "\"))";
			}
			else {
				Output += "					rs.get";

				if(AttributeName.contains("ID")) {
					Output += "Int";
				}
				else if(AttributeName.contains("Date")) {
					Output += "Date";
				}
				else {
					Output += "String";
				}
			Output += "(\"" + MashMapInput.get(i).GetAttributeName() + "\")";
			}
			if(i < MashMapInput.size()-1 ) {
				Output += ",\n";
			}
		}
		Output += ");\n";
		Output += "				ResultMap.put(rs.getInt(\"" + MashMapInput.get(0).GetAttributeName() + "\"), NewObject);\n";
		Output += "			}\n";
		Output += "			rs.close();\n";
		Output += "			return ResultMap;\n";
		Output += "		} catch (SQLException e) {\n";
		Output += "			e.printStackTrace();\n";
		Output += "			return null;\n";
		Output += "		}\n";
		Output += "	}\n";
		System.out.println(Output);
	}
	
	/**
	 * The class will auto generate a java class from a DB table
	 * @param ClassName
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void DB_To_Java_ClassGenerator (String ClassName) {
	
		HashMap<Integer, Attribute> MashMapInput;

		MashMapInput = DBinput(ClassName);

		String Output = "";
		Output += "public class " + ClassName.substring(0, ClassName.length()-4) + " {\n\n";
		for(int i = 0; i < MashMapInput.size(); i++) {
			Output += "	private " + MashMapInput.get(i).GetDataType() + " " + MashMapInput.get(i).GetAttributeName() + ";\n";
		}
		Output += "\n";
		
		Output += "	public " + ClassName.substring(0, ClassName.length()-4) + " (\n";
		Output += "\n";
		for(int i = 0; i < MashMapInput.size(); i++) {
			Output += "		" + MashMapInput.get(i).GetDataType() + " " + MashMapInput.get(i).GetAttributeName();
			if(i < MashMapInput.size()-1) {
				Output += ",";
			}
			Output += "\n";
		}
		
		Output += "	) {\n";
		for(int i = 0; i < MashMapInput.size(); i++) {
			Output += "		this." + MashMapInput.get(i).GetAttributeName() + " = " + MashMapInput.get(i).GetAttributeName() + ";\n";
		}
		Output += "	}\n\n";
		
		for(int i = 0; i < MashMapInput.size(); i++) {
			Output += "	public " + MashMapInput.get(i).GetDataType() + " Get" + MashMapInput.get(i).GetAttributeName() + "() {\n";
			Output += "		return " + MashMapInput.get(i).GetAttributeName() + ";\n";
			Output += "	}\n\n";
		}

		Output += "}";
		System.out.println(Output);
	}
	
	//Auto Generate Classes
	public static HashMap<Integer, animalbridge_aboutus> animalbridge_aboutus() {

		HashMap<Integer, animalbridge_aboutus> ResultMap = new HashMap<Integer, animalbridge_aboutus>();
		String SQLquery = "SELECT * FROM animalbridge_aboutus;";
		try {
			ResultSet rs = statement.executeQuery(SQLquery);
			while (rs.next()) {
				animalbridge_aboutus NewObject = new animalbridge_aboutus(
					rs.getInt("AboutUs_ID"),
					rs.getString("AboutUs_Title"),
					rs.getString("AboutUs_Description"),
					rs.getDate("AboutUs_Date"),
					ConvertBlobTobufferedImage(rs.getBlob("AboutUs_Image")));
				ResultMap.put(rs.getInt("AboutUs_ID"), NewObject);
			}
			rs.close();
			return ResultMap;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static HashMap<Integer, animalbridge_animals> animalbridge_animals() {

		HashMap<Integer, animalbridge_animals> ResultMap = new HashMap<Integer, animalbridge_animals>();
		String SQLquery = "SELECT * FROM animalbridge_animals;";
		try {
			ResultSet rs = statement.executeQuery(SQLquery);
			while (rs.next()) {
				animalbridge_animals NewObject = new animalbridge_animals(
					rs.getInt("Animals_ID"),
					rs.getString("Animals_Categories"),
					rs.getString("Animals_Name"),
					rs.getString("Animals_Ago"),
					rs.getString("Animals_Breeds"),
					rs.getString("Animals_Price"),
					rs.getString("Animals_Address"),
					rs.getString("Animals_Color"),
					rs.getString("Animals_Description"),
					ConvertBlobTobufferedImage(rs.getBlob("Animals_Image")),
					rs.getString("Animals_Size"),
					rs.getString("Animals_Gender"),
					rs.getInt("Animals_OwnerID"),
					rs.getString("Animals_OwnerName"));
				ResultMap.put(rs.getInt("Animals_ID"), NewObject);
			}
			rs.close();
			return ResultMap;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static HashMap<Integer, animalbridge_emergencycontact> animalbridge_emergencycontact() {

		HashMap<Integer, animalbridge_emergencycontact> ResultMap = new HashMap<Integer, animalbridge_emergencycontact>();
		String SQLquery = "SELECT * FROM animalbridge_emergencycontact;";
		try {
			ResultSet rs = statement.executeQuery(SQLquery);
			while (rs.next()) {
				animalbridge_emergencycontact NewObject = new animalbridge_emergencycontact(
					rs.getInt("EmergencyContact_ID"),
					rs.getString("EmergencyContact_Title"),
					rs.getString("EmergencyContact_Description"),
					rs.getDate("EmergencyContact_Date"),
					rs.getString("EmergencyContact_ZipCode"),
					ConvertBlobTobufferedImage(rs.getBlob("EmergencyContact_Image")),
					rs.getString("EmergencyContact_ContactEmail"),
					rs.getInt("EmergencyContact_OwnerID"),
					rs.getString("EmergencyContact_OwnerName"));
				ResultMap.put(rs.getInt("EmergencyContact_ID"), NewObject);
			}
			rs.close();
			return ResultMap;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static HashMap<Integer, animalbridge_homepage> animalbridge_homepage() {

		HashMap<Integer, animalbridge_homepage> ResultMap = new HashMap<Integer, animalbridge_homepage>();
		String SQLquery = "SELECT * FROM animalbridge_homepage;";
		try {
			ResultSet rs = statement.executeQuery(SQLquery);
			while (rs.next()) {
				animalbridge_homepage NewObject = new animalbridge_homepage(
					rs.getInt("HomePage_ID"),
					rs.getString("HomePage_Title"),
					rs.getString("HomePage_Description"),
					rs.getDate("HomePage_Date"),
					ConvertBlobTobufferedImage(rs.getBlob("HomePage_Image")),
					rs.getString("HomePage_RecentNews"));
				ResultMap.put(rs.getInt("HomePage_ID"), NewObject);
			}
			rs.close();
			return ResultMap;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static HashMap<Integer, animalbridge_posting> animalbridge_posting() {

		HashMap<Integer, animalbridge_posting> ResultMap = new HashMap<Integer, animalbridge_posting>();
		String SQLquery = "SELECT * FROM animalbridge_posting;";
		try {
			ResultSet rs = statement.executeQuery(SQLquery);
			while (rs.next()) {
				animalbridge_posting NewObject = new animalbridge_posting(
					rs.getInt("Posting_ID"),
					rs.getString("Posting_Categories"),
					rs.getString("Posting_Priority"),
					rs.getString("Posting_Title"),
					rs.getString("Posting_Address"),
					rs.getString("Posting_Description"),
					rs.getDate("Posting_Date"),
					rs.getString("Posting_StartingTime"),
					rs.getString("Posting_EndingTIme"),
					ConvertBlobTobufferedImage(rs.getBlob("Posting_Image")),
					rs.getString("Posting_Price"),
					rs.getString("Posting_ContactEmail"),
					rs.getInt("Posting_OwnerID"),
					rs.getString("Posting_OwnerName"));
				ResultMap.put(rs.getInt("Posting_ID"), NewObject);
			}
			rs.close();
			return ResultMap;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static HashMap<Integer, animalbridge_contactus> animalbridge_contactus() {

		HashMap<Integer, animalbridge_contactus> ResultMap = new HashMap<Integer, animalbridge_contactus>();
		String SQLquery = "SELECT * FROM animalbridge_contactus;";
		try {
			ResultSet rs = statement.executeQuery(SQLquery);
			while (rs.next()) {
				animalbridge_contactus NewObject = new animalbridge_contactus(
					rs.getInt("ContactUs_ID"),
					rs.getString("ContactUs_Title"),
					rs.getString("ContactUs_Description"),
					rs.getDate("ContactUs_Date"),
					ConvertBlobTobufferedImage(rs.getBlob("ContactUs_Image")),
					rs.getString("ContactUs_ContactEmail"));
				ResultMap.put(rs.getInt("ContactUs_ID"), NewObject);
			}
			rs.close();
			return ResultMap;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}




	
	public static void main(String[] args) {
		
		//DB_To_Java_ClassGenerator("animalbridge_contactus.txt");
		//HashMapGenerator("animalbridge_contactus.txt");
		
		HashMap<Integer, animalbridge_aboutus> ResultMap1 = new HashMap<Integer, animalbridge_aboutus>();
		HashMap<Integer, animalbridge_animals> ResultMap2 = new HashMap<Integer, animalbridge_animals>();
		HashMap<Integer, animalbridge_contactus> ResultMap3 = new HashMap<Integer, animalbridge_contactus>();
		HashMap<Integer, animalbridge_emergencycontact> ResultMap4 = new HashMap<Integer, animalbridge_emergencycontact>();
		HashMap<Integer, animalbridge_homepage> ResultMap5 = new HashMap<Integer, animalbridge_homepage>();
		HashMap<Integer, animalbridge_posting> ResultMap6 = new HashMap<Integer, animalbridge_posting>();
		HashMap<Integer, AnimalBridge_users> ResultMap7 = new HashMap<Integer, AnimalBridge_users>();

		
		/* I will do a simple unit Test later -Kun
		int ID = 5;
		HashMap<Integer, AnimalBridge_users> Testing = AnimalBridge_users();
		System.out.println("Running");
		System.out.println("Getuser_ID(): " + Testing.get(ID).Getuser_ID());
		System.out.println("Getuser_Name(): " + Testing.get(ID).Getuser_Name());
		System.out.println("Getuser_Email(): " + Testing.get(ID).Getuser_Email());
		System.out.println("Getuser_Pass(): " + Testing.get(ID).Getuser_Pass());
		System.out.println("Getuser_ComfirmStatus(): " + Testing.get(ID).Getuser_ComfirmStatus());
		System.out.println("Getuser_RegisteredDate(): " + Testing.get(ID).Getuser_RegisteredDate());
		System.out.println("GettokenCode(): " + Testing.get(ID).GettokenCode());
		*/
	}
}
