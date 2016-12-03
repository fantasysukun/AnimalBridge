package edu.sjsu.db;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
		if(blob == null) {
			return Image;
		}
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
					Output += "String";
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
	
public static void AddQueryGenerator(String ClassName) {
		
		HashMap<Integer, Attribute> MashMapInput = DBinput(ClassName);
		ClassName = ClassName.substring(0, ClassName.length()-4);
		String Output = "";
		Output += "	public static boolean Add" + ClassName + "(" + ClassName + " InputObject) {\n\n";
		for(int i = 0 ; i < MashMapInput.size(); i++) {

			String AttributeName = MashMapInput.get(i).GetAttributeName();
			if(!AttributeName.contains("ID")){
				Output += "		String " + MashMapInput.get(i).GetAttributeName() + " = ";
				Output += "InputObject.Get" + MashMapInput.get(i).GetAttributeName() + "().replace(\"\'\", \"\'\'\");\n";
							
			}
		}
		Output += "\n";

		Output += "		String query = String.format(\"INSERT INTO " + ClassName + "(\"\n";
		for(int i = 0 ; i < MashMapInput.size(); i++) {
			String AttributeName = MashMapInput.get(i).GetAttributeName();
			if(!AttributeName.contains("ID")){
				Output += "			+ \"" + MashMapInput.get(i).GetAttributeName();
				if(i < MashMapInput.size() - 1) {
					Output += ",\"\n";
				}
			}
		}
		Output += ")\"\n";
		Output += "		+ \" VALUES(";

		for(int i = 0 ; i < MashMapInput.size(); i++) {
			String AttributeName = MashMapInput.get(i).GetAttributeName();
			if(!AttributeName.contains("ID")){
				Output += "\'%s\'";
				if(i < MashMapInput.size() - 1) {
					Output += ", ";
				}
			}
		}
		Output += ")\",\n";
		for(int i = 0 ; i < MashMapInput.size(); i++) {
			String AttributeName = MashMapInput.get(i).GetAttributeName();
			if(!AttributeName.contains("ID")){
				Output += "			" +MashMapInput.get(i).GetAttributeName();
				if(i < MashMapInput.size() - 1) {
					Output += ",\n";
				}
			}
		}
		Output += ");\n";
		
		Output += "		try {\n";
		Output += "			statement.execute(query);\n";
		Output += "			return true;\n";
		Output += "		} catch (SQLException e) {\n";

		Output += "			e.printStackTrace();\n";
		Output += "			return false;\n";
		Output += "		}\n";
		Output += "	}\n";
		System.out.println(Output);
	}

	//Auto Generate Classes
	public static HashMap<Integer, animalbridge_users> animalbridge_users() {

		HashMap<Integer, animalbridge_users> ResultMap = new HashMap<Integer, animalbridge_users>();
		String SQLquery = "SELECT * FROM animalbridge_users;";
		try {
			ResultSet rs = statement.executeQuery(SQLquery);
			while (rs.next()) {
				animalbridge_users NewObject = new animalbridge_users(
					rs.getInt("user_ID"),
					rs.getString("user_Name"),
					rs.getString("user_Email"),
					rs.getString("user_Pass"),
					rs.getString("user_ComfirmStatus"),
					rs.getString("user_RegisteredDate"),
					rs.getString("tokenCode"));
				ResultMap.put(rs.getInt("user_ID"), NewObject);
			}
			rs.close();
			return ResultMap;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
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
					rs.getString("AboutUs_Date"),
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
					rs.getString("EmergencyContact_Date"),
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
					rs.getString("HomePage_Date"),
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
					rs.getString("Posting_Date"),
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
					rs.getString("ContactUs_Date"),
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


/*
	public static boolean Addanimalbridge_users(animalbridge_users user) {		
		
		String user_Name = user.Getuser_Name().replace("'", "''");
		String user_Email = user.Getuser_Email().replace("'", "''");
		String user_Pass = user.Getuser_Pass().replace("'", "''");
		String user_ComfirmStatus = user.Getuser_ComfirmStatus().replace("'", "''");
		String user_RegisteredDate = user.Getuser_RegisteredDate().replace("'", "''");
		String tokenCode = user.GettokenCode().replace("'", "''");

		
		String query = String.format("INSERT INTO animalbridge_users(user_Name, user_Email, user_Pass, user_ComfirmStatus, user_RegisteredDate, tokenCode)"
				+ " VALUES('%s','%s','%s','%s',%s,'%s')", 
				user_Name, user_Email, user_Pass, user_ComfirmStatus, user_RegisteredDate, tokenCode);
		try {
			statement.execute(query);

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
*/
	public static boolean Addanimalbridge_users(animalbridge_users InputObject) {

		String user_Name = InputObject.Getuser_Name().replace("'", "''");
		String user_Email = InputObject.Getuser_Email().replace("'", "''");
		String user_Pass = InputObject.Getuser_Pass().replace("'", "''");
		String user_ComfirmStatus = InputObject.Getuser_ComfirmStatus().replace("'", "''");
		String user_RegisteredDate = InputObject.Getuser_RegisteredDate().replace("'", "''");
		String tokenCode = InputObject.GettokenCode().replace("'", "''");

		String query = String.format("INSERT INTO animalbridge_users("
			+ "user_Name,"
			+ "user_Email,"
			+ "user_Pass,"
			+ "user_ComfirmStatus,"
			+ "user_RegisteredDate,"
			+ "tokenCode)"
		+ " VALUES('%s', '%s', '%s', '%s', '%s', '%s')",
			user_Name,
			user_Email,
			user_Pass,
			user_ComfirmStatus,
			user_RegisteredDate,
			tokenCode);
		try {
			statement.execute(query);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean Addanimalbridge_aboutus(animalbridge_aboutus InputObject) {

		String AboutUs_Title = InputObject.GetAboutUs_Title().replace("'", "''");
		String AboutUs_Description = InputObject.GetAboutUs_Description().replace("'", "''");
		String AboutUs_Date = InputObject.GetAboutUs_Date().replace("'", "''");
		String AboutUs_Image = null;

		String query = String.format("INSERT INTO animalbridge_aboutus("
			+ "AboutUs_Title,"
			+ "AboutUs_Description,"
			+ "AboutUs_Date,"
			+ "AboutUs_Image)"
		+ " VALUES('%s', '%s', '%s', '%s')",
			AboutUs_Title,
			AboutUs_Description,
			AboutUs_Date,
			AboutUs_Image);
		try {
			statement.execute(query);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean Addanimalbridge_animals(animalbridge_animals InputObject) {

		String Animals_Categories = InputObject.GetAnimals_Categories().replace("'", "''");
		String Animals_Name = InputObject.GetAnimals_Name().replace("'", "''");
		String Animals_Ago = InputObject.GetAnimals_Ago().replace("'", "''");
		String Animals_Breeds = InputObject.GetAnimals_Breeds().replace("'", "''");
		String Animals_Price = InputObject.GetAnimals_Price().replace("'", "''");
		String Animals_Address = InputObject.GetAnimals_Address().replace("'", "''");
		String Animals_Color = InputObject.GetAnimals_Color().replace("'", "''");
		String Animals_Description = InputObject.GetAnimals_Description().replace("'", "''");
		String Animals_Image = null;
		String Animals_Size = InputObject.GetAnimals_Size().replace("'", "''");
		String Animals_Gender = InputObject.GetAnimals_Gender().replace("'", "''");
		String Animals_OwnerName = InputObject.GetAnimals_OwnerName().replace("'", "''");

		String query = String.format("INSERT INTO animalbridge_animals("
			+ "Animals_Categories,"
			+ "Animals_Name,"
			+ "Animals_Ago,"
			+ "Animals_Breeds,"
			+ "Animals_Price,"
			+ "Animals_Address,"
			+ "Animals_Color,"
			+ "Animals_Description,"
			+ "Animals_Image,"
			+ "Animals_Size,"
			+ "Animals_Gender,"
			+ "Animals_OwnerName)"
		+ " VALUES('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
			Animals_Categories,
			Animals_Name,
			Animals_Ago,
			Animals_Breeds,
			Animals_Price,
			Animals_Address,
			Animals_Color,
			Animals_Description,
			Animals_Image,
			Animals_Size,
			Animals_Gender,
			Animals_OwnerName);
		try {
			statement.execute(query);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean Addanimalbridge_contactus(animalbridge_contactus InputObject) {

		String ContactUs_Title = InputObject.GetContactUs_Title().replace("'", "''");
		String ContactUs_Description = InputObject.GetContactUs_Description().replace("'", "''");
		String ContactUs_Date = InputObject.GetContactUs_Date().replace("'", "''");
		String ContactUs_Image = null;
		String ContactUs_ContactEmail = InputObject.GetContactUs_ContactEmail().replace("'", "''");

		String query = String.format("INSERT INTO animalbridge_contactus("
			+ "ContactUs_Title,"
			+ "ContactUs_Description,"
			+ "ContactUs_Date,"
			+ "ContactUs_Image,"
			+ "ContactUs_ContactEmail)"
		+ " VALUES('%s', '%s', '%s', '%s', '%s')",
			ContactUs_Title,
			ContactUs_Description,
			ContactUs_Date,
			ContactUs_Image,
			ContactUs_ContactEmail);
		try {
			statement.execute(query);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean Addanimalbridge_emergencycontact(animalbridge_emergencycontact InputObject) {

		String EmergencyContact_Title = InputObject.GetEmergencyContact_Title().replace("'", "''");
		String EmergencyContact_Description = InputObject.GetEmergencyContact_Description().replace("'", "''");
		String EmergencyContact_Date = InputObject.GetEmergencyContact_Date().replace("'", "''");
		String EmergencyContact_ZipCode = InputObject.GetEmergencyContact_ZipCode().replace("'", "''");
		String EmergencyContact_Image = null;
		String EmergencyContact_ContactEmail = InputObject.GetEmergencyContact_ContactEmail().replace("'", "''");
		String EmergencyContact_OwnerName = InputObject.GetEmergencyContact_OwnerName().replace("'", "''");

		String query = String.format("INSERT INTO animalbridge_emergencycontact("
			+ "EmergencyContact_Title,"
			+ "EmergencyContact_Description,"
			+ "EmergencyContact_Date,"
			+ "EmergencyContact_ZipCode,"
			+ "EmergencyContact_Image,"
			+ "EmergencyContact_ContactEmail,"
			+ "EmergencyContact_OwnerName)"
		+ " VALUES('%s', '%s', '%s', '%s', '%s', '%s', '%s')",
			EmergencyContact_Title,
			EmergencyContact_Description,
			EmergencyContact_Date,
			EmergencyContact_ZipCode,
			EmergencyContact_Image,
			EmergencyContact_ContactEmail,
			EmergencyContact_OwnerName);
		try {
			statement.execute(query);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean Addanimalbridge_homepage(animalbridge_homepage InputObject) {

		String HomePage_Title = InputObject.GetHomePage_Title().replace("'", "''");
		String HomePage_Description = InputObject.GetHomePage_Description().replace("'", "''");
		String HomePage_Date = InputObject.GetHomePage_Date().replace("'", "''");
		String HomePage_Image = null;
		String HomePage_RecentNews = InputObject.GetHomePage_RecentNews().replace("'", "''");

		String query = String.format("INSERT INTO animalbridge_homepage("
			+ "HomePage_Title,"
			+ "HomePage_Description,"
			+ "HomePage_Date,"
			+ "HomePage_Image,"
			+ "HomePage_RecentNews)"
		+ " VALUES('%s', '%s', '%s', '%s', '%s')",
			HomePage_Title,
			HomePage_Description,
			HomePage_Date,
			HomePage_Image,
			HomePage_RecentNews);
		try {
			statement.execute(query);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean Addanimalbridge_posting(animalbridge_posting InputObject) {

		String Posting_Categories = InputObject.GetPosting_Categories().replace("'", "''");
		String Posting_Priority = InputObject.GetPosting_Priority().replace("'", "''");
		String Posting_Title = InputObject.GetPosting_Title().replace("'", "''");
		String Posting_Address = InputObject.GetPosting_Address().replace("'", "''");
		String Posting_Description = InputObject.GetPosting_Description().replace("'", "''");
		String Posting_Date = InputObject.GetPosting_Date().replace("'", "''");
		String Posting_StartingTime = InputObject.GetPosting_StartingTime().replace("'", "''");
		String Posting_EndingTIme = InputObject.GetPosting_EndingTIme().replace("'", "''");
		String Posting_Image = null;
		String Posting_Price = InputObject.GetPosting_Price().replace("'", "''");
		String Posting_ContactEmail = InputObject.GetPosting_ContactEmail().replace("'", "''");
		String Posting_OwnerName = InputObject.GetPosting_OwnerName().replace("'", "''");

		String query = String.format("INSERT INTO animalbridge_posting("
			+ "Posting_Categories,"
			+ "Posting_Priority,"
			+ "Posting_Title,"
			+ "Posting_Address,"
			+ "Posting_Description,"
			+ "Posting_Date,"
			+ "Posting_StartingTime,"
			+ "Posting_EndingTIme,"
			+ "Posting_Image,"
			+ "Posting_Price,"
			+ "Posting_ContactEmail,"
			+ "Posting_OwnerName)"
		+ " VALUES('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
			Posting_Categories,
			Posting_Priority,
			Posting_Title,
			Posting_Address,
			Posting_Description,
			Posting_Date,
			Posting_StartingTime,
			Posting_EndingTIme,
			Posting_Image,
			Posting_Price,
			Posting_ContactEmail,
			Posting_OwnerName);
		try {
			statement.execute(query);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}





















	
	
	
	public static void main(String[] args) {
		
		//DB_To_Java_ClassGenerator("animalbridge_users.txt");
		//HashMapGenerator("animalbridge_users.txt");


		/*
		String Date = "2016-12-02";
		animalbridge_users user = new animalbridge_users(0, "Second Testing", "Second.Testing@sjsu.edu", "123456", "Y", Date, "0001");
		Addanimalbridge_users(user);
		*/
		AddQueryGenerator("animalbridge_posting.txt");
		
		/*
		HashMap<Integer, animalbridge_aboutus> ResultMap1 = animalbridge_aboutus();
		HashMap<Integer, animalbridge_animals> ResultMap2 = animalbridge_animals();
		HashMap<Integer, animalbridge_contactus> ResultMap3 = animalbridge_contactus();
		HashMap<Integer, animalbridge_emergencycontact> ResultMap4 = animalbridge_emergencycontact();
		HashMap<Integer, animalbridge_homepage> ResultMap5 = animalbridge_homepage();
		HashMap<Integer, animalbridge_posting> ResultMap6 = animalbridge_posting();
		
		HashMap<Integer, animalbridge_users> ResultMap7 = animalbridge_users();
		
		
		// I will do a simple unit Test later -Kun
		
		int ID;
		System.out.println("ResultMap1.size(): " + ResultMap1.size());
		System.out.println("ResultMap2.size(): " + ResultMap2.size());
		System.out.println("ResultMap3.size(): " + ResultMap3.size());
		System.out.println("ResultMap4.size(): " + ResultMap4.size());
		System.out.println("ResultMap5.size(): " + ResultMap5.size());
		System.out.println("ResultMap6.size(): " + ResultMap6.size());
		System.out.println("ResultMap7.size(): " + ResultMap7.size());
		
		for(int i = 1; i < ResultMap7.size()+1; i++) {
			ID = i;
			System.out.println("Running");
			System.out.println("Getuser_ID(): " + ResultMap7.get(ID).Getuser_ID());
			System.out.println("Getuser_Name(): " + ResultMap7.get(ID).Getuser_Name());
			System.out.println("Getuser_Email(): " + ResultMap7.get(ID).Getuser_Email());
			System.out.println("Getuser_Pass(): " + ResultMap7.get(ID).Getuser_Pass());
			System.out.println("Getuser_ComfirmStatus(): " + ResultMap7.get(ID).Getuser_ComfirmStatus());
			System.out.println("Getuser_RegisteredDate(): " + ResultMap7.get(ID).Getuser_RegisteredDate());
			System.out.println("GettokenCode(): " + ResultMap7.get(ID).GettokenCode());			
		}
		*/
		
		/*
		ID = 1;
		HashMap<Integer, AnimalBridge_users> Testing = AnimalBridge_users();
		System.out.println("Running");
		System.out.println("Getuser_ID(): " + Testing.get(ID).Getuser_ID());
		System.out.println("Getuser_Name(): " + Testing.get(ID).Getuser_Name());
		System.out.println("Getuser_Email(): " + Testing.get(ID).Getuser_Email());
		System.out.println("Getuser_Pass(): " + Testing.get(ID).Getuser_Pass());
		System.out.println("Getuser_ComfirmStatus(): " + Testing.get(ID).Getuser_ComfirmStatus());
		System.out.println("Getuser_RegisteredDate(): " + Testing.get(ID).Getuser_RegisteredDate());
		System.out.println("GettokenCode(): " + Testing.get(ID).GettokenCode());
		
		ID = 2;
		System.out.println("Running");
		System.out.println("Getuser_ID(): " + Testing.get(ID).Getuser_ID());
		System.out.println("Getuser_Name(): " + Testing.get(ID).Getuser_Name());
		System.out.println("Getuser_Email(): " + Testing.get(ID).Getuser_Email());
		System.out.println("Getuser_Pass(): " + Testing.get(ID).Getuser_Pass());
		System.out.println("Getuser_ComfirmStatus(): " + Testing.get(ID).Getuser_ComfirmStatus());
		System.out.println("Getuser_RegisteredDate(): " + Testing.get(ID).Getuser_RegisteredDate());
		System.out.println("GettokenCode(): " + Testing.get(ID).GettokenCode());
		
		ID = 3;
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
