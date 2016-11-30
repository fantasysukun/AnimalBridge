package edu.sjsu.db;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

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
	 * Generate a HashMap with attribute's name and data type from a txt file with DB table attribute
	 * @param ClassName DB table's name
	 * @return HashMap with attribute's name and data type
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static HashMap<Integer, Attribute> DBinput(String ClassName) throws FileNotFoundException, IOException {
		
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
		}
		return MashMapInput;
	}
	
	/**
	 * The class will auto generate a java class from a DB table
	 * @param ClassName
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void DB_To_Java_ClassGenerator (String ClassName) throws FileNotFoundException, IOException {
		
		HashMap<Integer, Attribute> MashMapInput = DBinput(ClassName);

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
	
	public static void main(String[] args) {
		
		try {
			DB_To_Java_ClassGenerator("animalbridge_aboutus.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		HashMap<Integer, AnimalBridge_users> Testing = AnimalBridge_users();
		System.out.println("Running");
		System.out.println("Getuser_ID(): " + Testing.get(5).Getuser_ID());
		System.out.println("Getuser_Name(): " + Testing.get(5).Getuser_Name());
		System.out.println("Getuser_Email(): " + Testing.get(5).Getuser_Email());
		System.out.println("Getuser_Pass(): " + Testing.get(5).Getuser_Pass());
		System.out.println("Getuser_ComfirmStatus(): " + Testing.get(5).Getuser_ComfirmStatus());
		System.out.println("Getuser_RegisteredDate(): " + Testing.get(5).Getuser_RegisteredDate());
		System.out.println("GettokenCode(): " + Testing.get(5).GettokenCode());
		*/
	}
}
