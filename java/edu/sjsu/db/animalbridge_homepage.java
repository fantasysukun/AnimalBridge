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

public class animalbridge_homepage {

	private int HomePage_ID;
	private String HomePage_Title;
	private String HomePage_Description;
	private String HomePage_Date;
	private BufferedImage HomePage_Image;
	private String HomePage_RecentNews;

	public animalbridge_homepage (

		int HomePage_ID,
		String HomePage_Title,
		String HomePage_Description,
		String HomePage_Date,
		BufferedImage HomePage_Image,
		String HomePage_RecentNews
	) {
		this.HomePage_ID = HomePage_ID;
		this.HomePage_Title = HomePage_Title;
		this.HomePage_Description = HomePage_Description;
		this.HomePage_Date = HomePage_Date;
		this.HomePage_Image = HomePage_Image;
		this.HomePage_RecentNews = HomePage_RecentNews;
	}

	public int GetHomePage_ID() {
		return HomePage_ID;
	}

	public String GetHomePage_Title() {
		return HomePage_Title;
	}

	public String GetHomePage_Description() {
		return HomePage_Description;
	}

	public String GetHomePage_Date() {
		return HomePage_Date;
	}

	public BufferedImage GetHomePage_Image() {
		return HomePage_Image;
	}

	public String GetHomePage_RecentNews() {
		return HomePage_RecentNews;
	}

}
