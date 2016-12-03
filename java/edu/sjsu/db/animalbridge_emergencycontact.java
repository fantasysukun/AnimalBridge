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

public class animalbridge_emergencycontact {

	private int EmergencyContact_ID;
	private String EmergencyContact_Title;
	private String EmergencyContact_Description;
	private String EmergencyContact_Date;
	private String EmergencyContact_ZipCode;
	private BufferedImage EmergencyContact_Image;
	private String EmergencyContact_ContactEmail;
	private int EmergencyContact_OwnerID;
	private String EmergencyContact_OwnerName;

	public animalbridge_emergencycontact (

		int EmergencyContact_ID,
		String EmergencyContact_Title,
		String EmergencyContact_Description,
		String EmergencyContact_Date,
		String EmergencyContact_ZipCode,
		BufferedImage EmergencyContact_Image,
		String EmergencyContact_ContactEmail,
		int EmergencyContact_OwnerID,
		String EmergencyContact_OwnerName
	) {
		this.EmergencyContact_ID = EmergencyContact_ID;
		this.EmergencyContact_Title = EmergencyContact_Title;
		this.EmergencyContact_Description = EmergencyContact_Description;
		this.EmergencyContact_Date = EmergencyContact_Date;
		this.EmergencyContact_ZipCode = EmergencyContact_ZipCode;
		this.EmergencyContact_Image = EmergencyContact_Image;
		this.EmergencyContact_ContactEmail = EmergencyContact_ContactEmail;
		this.EmergencyContact_OwnerID = EmergencyContact_OwnerID;
		this.EmergencyContact_OwnerName = EmergencyContact_OwnerName;
	}

	public int GetEmergencyContact_ID() {
		return EmergencyContact_ID;
	}

	public String GetEmergencyContact_Title() {
		return EmergencyContact_Title;
	}

	public String GetEmergencyContact_Description() {
		return EmergencyContact_Description;
	}

	public String GetEmergencyContact_Date() {
		return EmergencyContact_Date;
	}

	public String GetEmergencyContact_ZipCode() {
		return EmergencyContact_ZipCode;
	}

	public BufferedImage GetEmergencyContact_Image() {
		return EmergencyContact_Image;
	}

	public String GetEmergencyContact_ContactEmail() {
		return EmergencyContact_ContactEmail;
	}

	public int GetEmergencyContact_OwnerID() {
		return EmergencyContact_OwnerID;
	}

	public String GetEmergencyContact_OwnerName() {
		return EmergencyContact_OwnerName;
	}

}
