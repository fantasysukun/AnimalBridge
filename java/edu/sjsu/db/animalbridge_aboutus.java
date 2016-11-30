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

public class animalbridge_aboutus {

	private int AboutUs_ID;
	private String AboutUs_Title;
	private String AboutUs_Description;
	private Date AboutUs_Date;
	private BufferedImage AboutUs_Image;

	public animalbridge_aboutus (

		int AboutUs_ID,
		String AboutUs_Title,
		String AboutUs_Description,
		Date AboutUs_Date,
		BufferedImage AboutUs_Image
	) {
		this.AboutUs_ID = AboutUs_ID;
		this.AboutUs_Title = AboutUs_Title;
		this.AboutUs_Description = AboutUs_Description;
		this.AboutUs_Date = AboutUs_Date;
		this.AboutUs_Image = AboutUs_Image;
	}

	public int GetAboutUs_ID() {
		return AboutUs_ID;
	}

	public String GetAboutUs_Title() {
		return AboutUs_Title;
	}

	public String GetAboutUs_Description() {
		return AboutUs_Description;
	}

	public Date GetAboutUs_Date() {
		return AboutUs_Date;
	}

	public BufferedImage GetAboutUs_Image() {
		return AboutUs_Image;
	}

}
