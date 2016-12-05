package edu.sjsu.db;

import java.awt.image.BufferedImage;
import java.util.Date;

import com.google.gson.annotations.SerializedName;

/**
 * COPYRIGHT 2016 TeamMinion. All Rights Reserved. 
 * Animal Bridge
 * CS160 Group Project
 * @author Kun Su, Archer Zhao, Nelson Liang, Marco Kuang, Peilu Liu
 * @version 1.00 2016/11/30
 */

public class animalbridge_posting {

	@SerializedName("Posting_ID")
	private int Posting_ID;
	@SerializedName("Posting_Categories")
	private String Posting_Categories;
	@SerializedName("Posting_Priority")
	private String Posting_Priority;
	@SerializedName("Posting_Title")
	private String Posting_Title;
	@SerializedName("Posting_Address")
	private String Posting_Address;
	@SerializedName("Posting_Description")
	private String Posting_Description;
	@SerializedName("Posting_Date")
	private String Posting_Date;
	@SerializedName("Posting_StartingTime")
	private String Posting_StartingTime;
	@SerializedName("Posting_EndingTIme")
	private String Posting_EndingTIme;
	@SerializedName("Posting_Image")
	private BufferedImage Posting_Image;
	@SerializedName("Posting_Price")
	private String Posting_Price;
	@SerializedName("Posting_ContactEmail")
	private String Posting_ContactEmail;
	@SerializedName("Posting_OwnerID")
	private int Posting_OwnerID;
	@SerializedName("Posting_OwnerName")
	private String Posting_OwnerName;
	
	public animalbridge_posting (

		int Posting_ID,
		String Posting_Categories,
		String Posting_Priority,
		String Posting_Title,
		String Posting_Address,
		String Posting_Description,
		String Posting_Date,
		String Posting_StartingTime,
		String Posting_EndingTIme,
		BufferedImage Posting_Image,
		String Posting_Price,
		String Posting_ContactEmail,
		int Posting_OwnerID,
		String Posting_OwnerName
	) {
		this.Posting_ID = Posting_ID;
		this.Posting_Categories = Posting_Categories;
		this.Posting_Priority = Posting_Priority;
		this.Posting_Title = Posting_Title;
		this.Posting_Address = Posting_Address;
		this.Posting_Description = Posting_Description;
		this.Posting_Date = Posting_Date;
		this.Posting_StartingTime = Posting_StartingTime;
		this.Posting_EndingTIme = Posting_EndingTIme;
		this.Posting_Image = Posting_Image;
		this.Posting_Price = Posting_Price;
		this.Posting_ContactEmail = Posting_ContactEmail;
		this.Posting_OwnerID = Posting_OwnerID;
		this.Posting_OwnerName = Posting_OwnerName;
	}

	public int GetPosting_ID() {
		return Posting_ID;
	}

	public String GetPosting_Categories() {
		return Posting_Categories;
	}

	public String GetPosting_Priority() {
		return Posting_Priority;
	}

	public String GetPosting_Title() {
		return Posting_Title;
	}

	public String GetPosting_Address() {
		return Posting_Address;
	}

	public String GetPosting_Description() {
		return Posting_Description;
	}

	public String GetPosting_Date() {
		return Posting_Date;
	}

	public String GetPosting_StartingTime() {
		return Posting_StartingTime;
	}

	public String GetPosting_EndingTIme() {
		return Posting_EndingTIme;
	}

	public BufferedImage GetPosting_Image() {
		return Posting_Image;
	}

	public String GetPosting_Price() {
		return Posting_Price;
	}

	public String GetPosting_ContactEmail() {
		return Posting_ContactEmail;
	}

	public int GetPosting_OwnerID() {
		return Posting_OwnerID;
	}

	public String GetPosting_OwnerName() {
		return Posting_OwnerName;
	}

}
