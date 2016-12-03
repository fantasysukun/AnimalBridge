package edu.sjsu.db;

import java.awt.image.BufferedImage;
import java.util.Date;

/**
 * COPYRIGHT 2016 TeamMinion. All Rights Reserved. 
 * Animal Bridge
 * CS160 Group Project
 * @author Kun Su, Archer Zhao, Nelson Liang, Marco Kuang, Peilu Liu
 * @version 1.00 2016/11/30
 */

public class animalbridge_contactus {

	private int ContactUs_ID;
	private String ContactUs_Title;
	private String ContactUs_Description;
	private String ContactUs_Date;
	private BufferedImage ContactUs_Image;
	private String ContactUs_ContactEmail;

	public animalbridge_contactus (

		int ContactUs_ID,
		String ContactUs_Title,
		String ContactUs_Description,
		String ContactUs_Date,
		BufferedImage ContactUs_Image,
		String ContactUs_ContactEmail
	) {
		this.ContactUs_ID = ContactUs_ID;
		this.ContactUs_Title = ContactUs_Title;
		this.ContactUs_Description = ContactUs_Description;
		this.ContactUs_Date = ContactUs_Date;
		this.ContactUs_Image = ContactUs_Image;
		this.ContactUs_ContactEmail = ContactUs_ContactEmail;
	}

	public int GetContactUs_ID() {
		return ContactUs_ID;
	}

	public String GetContactUs_Title() {
		return ContactUs_Title;
	}

	public String GetContactUs_Description() {
		return ContactUs_Description;
	}

	public String GetContactUs_Date() {
		return ContactUs_Date;
	}

	public BufferedImage GetContactUs_Image() {
		return ContactUs_Image;
	}

	public String GetContactUs_ContactEmail() {
		return ContactUs_ContactEmail;
	}

}
