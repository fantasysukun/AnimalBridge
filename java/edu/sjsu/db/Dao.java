package edu.sjsu.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

/**
 * COPYRIGHT 2016 TeamMinion. All Rights Reserved.
 * Animal Bridge
 * CS160 Group Project
 * @author Kun Su, Archer Zhao, Nelson Liang, Marco Kuang, Peilu Liu
 * @version 1.00 2016/11/22
 */

public class Dao {

	/**
	 * Get a connection to database using DriverManager
	 * @return a connection to database from driver manager
	 */
	public static Connection getConn() {

		//***** The information below might need to be stored in a local file ***** -Kun
		String driverStr = "com.mysql.jdbc.Driver";
		String urlStr = "jdbc:mysql://localhost:3306/cs160test?autoReconnect=true&useSSL=false";
		String uid = "root";
		String pwd = "root";

		// marco's data
		// String urlStr = "jdbc:mysql://localhost:3307/cs160test?autoReconnect=true&useSSL=false";
		// String uid = "root";
		// String pwd = "root";
		try {
			Class.forName(driverStr);
			return DriverManager.getConnection(urlStr, uid, pwd);
		} catch (SQLException | ClassNotFoundException ex) {
			System.err.println("The connection failed because " + ex.getMessage());
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * Close a connection
	 * @param conn - a connection to be closed
	 */
	public static void closeConnection(Connection conn) {

		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get a statement from a given connection
	 * @param conn - a connection
	 * @return a statement
	 */
	public static Statement getStatement(Connection conn) {
		Statement stmt = null;

		try {
			stmt = conn.createStatement();
			return stmt;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return stmt;
	}

	/**
	 * Close a statement
	 * @param stmt - a statement to be closed
	 */
	public static void closeStatement(Statement stmt) {

		try {
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*this function might be deleted sometimes later -Kun */
	public static String getPhone(String name) throws SQLException {

		String output = "{\"phone\" : ";
		Connection conn = getConn();
		if (conn == null) return output + "\"connection failed\"}";
		String sqlStr = "select * from phones where name=?;";
		ResultSet rs = null;
		PreparedStatement pstmt = conn.prepareStatement(sqlStr);
		pstmt.setString(1, name);
		rs = pstmt.executeQuery();
		if (rs == null)
			output += "\"Name not found!\"";
		else {
			rs.next();
			output += "\"" + rs.getString(2) + "\"";
		}
		return output + "}";
	} // method

	/*
	public static HashMap<Integer, AnimalBridge_users> GetAnimalBridge_users() {

		HashMap<Integer, AnimalBridge_users> ResultMap = new HashMap<Integer, AnimalBridge_users>();

		String sqlStr = "SELECT * FROM animalbridge_users;";

		try {
			ResultSet rs = statement.executeQuery(sqlStr);
			while (rs.next()) {
				Room room = new Room(rs.getInt("roomid"), rs.getDouble("costPerNight"), rs.getString("roomtype"));
				Reservation res = new Reservation(rs.getInt("reservationid"), rs.getString("customer"), room,
						rs.getDate("startdate"), rs.getDate("enddate"), rs.getInt("numOfDays"), rs.getDouble("totalCost"),
						rs.getBoolean("canceled"));
				resList.add(res);
			}
			rs.close();
			return ResultMap;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	*/
}
