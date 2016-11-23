package edu.sjsu.db;

import java.sql.Connection;
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
	
	public static void main(String[] args) {
		HashMap<Integer, AnimalBridge_users> Testing = AnimalBridge_users();
		System.out.println("Running");
		System.out.println("Getuser_ID(): " + Testing.get(5).Getuser_ID());
		System.out.println("Getuser_Name(): " + Testing.get(5).Getuser_Name());
		System.out.println("Getuser_Email(): " + Testing.get(5).Getuser_Email());
		System.out.println("Getuser_Pass(): " + Testing.get(5).Getuser_Pass());
		System.out.println("Getuser_ComfirmStatus(): " + Testing.get(5).Getuser_ComfirmStatus());
		System.out.println("Getuser_RegisteredDate(): " + Testing.get(5).Getuser_RegisteredDate());
		System.out.println("GettokenCode(): " + Testing.get(5).GettokenCode());
	}
}
