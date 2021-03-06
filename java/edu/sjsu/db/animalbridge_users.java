package edu.sjsu.db;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.SerializedName;

/**
 * COPYRIGHT 2016 TeamMinion. All Rights Reserved.
 * Animal Bridge
 * CS160 Group Project
 * @author Kun Su, Archer Zhao, Nelson Liang, Marco Kuang, Peilu Liu
 * @version 1.00 2016/11/22
 */
//@XmlRootElement(name = "user")
public class animalbridge_users {
	private int user_ID;
	@SerializedName("name")
	private String user_Name;
	@SerializedName("email")
	private String user_Email;
	@SerializedName("password")
	private String user_Pass;
	private String user_ComfirmStatus;
	private String user_RegisteredDate;
	private String tokenCode;

	public animalbridge_users (

		int user_ID,
		String user_Name,
		String user_Email,
		String user_Pass,
		String user_ComfirmStatus,
		String user_RegisteredDate,
		String tokenCode
	) {
		this.user_ID = user_ID;
		this.user_Name = user_Name;
		this.user_Email = user_Email;
		this.user_Pass = user_Pass;
		this.user_ComfirmStatus = user_ComfirmStatus;
		this.user_RegisteredDate = user_RegisteredDate;
		this.tokenCode = tokenCode;
	}

	public int Getuser_ID() {
		return user_ID;
	}

	public String Getuser_Name() {
		return user_Name;
	}

	public String Getuser_Email() {
		return user_Email;
	}

	public String Getuser_Pass() {
		return user_Pass;
	}

	public String Getuser_ComfirmStatus() {
		return user_ComfirmStatus;
	}

	public String Getuser_RegisteredDate() {
		return user_RegisteredDate;
	}

	public String GettokenCode() {
		return tokenCode;
	}

}
