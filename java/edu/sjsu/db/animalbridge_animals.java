package edu.sjsu.db;

import java.awt.image.BufferedImage;

/**
 * COPYRIGHT 2016 TeamMinion. All Rights Reserved. 
 * Animal Bridge
 * CS160 Group Project
 * @author Kun Su, Archer Zhao, Nelson Liang, Marco Kuang, Peilu Liu
 * @version 1.00 2016/11/30
 */

public class animalbridge_animals {

	private int Animals_ID;
	private String Animals_Categories;
	private String Animals_Name;
	private String Animals_Ago;
	private String Animals_Breeds;
	private String Animals_Price;
	private String Animals_Address;
	private String Animals_Color;
	private String Animals_Description;
	private BufferedImage Animals_Image;
	private String Animals_Size;
	private String Animals_Gender;
	private int Animals_OwnerID;
	private String Animals_OwnerName;

	public animalbridge_animals (

		int Animals_ID,
		String Animals_Categories,
		String Animals_Name,
		String Animals_Ago,
		String Animals_Breeds,
		String Animals_Price,
		String Animals_Address,
		String Animals_Color,
		String Animals_Description,
		BufferedImage Animals_Image,
		String Animals_Size,
		String Animals_Gender,
		int Animals_OwnerID,
		String Animals_OwnerName
	) {
		this.Animals_ID = Animals_ID;
		this.Animals_Categories = Animals_Categories;
		this.Animals_Name = Animals_Name;
		this.Animals_Ago = Animals_Ago;
		this.Animals_Breeds = Animals_Breeds;
		this.Animals_Price = Animals_Price;
		this.Animals_Address = Animals_Address;
		this.Animals_Color = Animals_Color;
		this.Animals_Description = Animals_Description;
		this.Animals_Image = Animals_Image;
		this.Animals_Size = Animals_Size;
		this.Animals_Gender = Animals_Gender;
		this.Animals_OwnerID = Animals_OwnerID;
		this.Animals_OwnerName = Animals_OwnerName;
	}

	public int GetAnimals_ID() {
		return Animals_ID;
	}

	public String GetAnimals_Categories() {
		return Animals_Categories;
	}

	public String GetAnimals_Name() {
		return Animals_Name;
	}

	public String GetAnimals_Ago() {
		return Animals_Ago;
	}

	public String GetAnimals_Breeds() {
		return Animals_Breeds;
	}

	public String GetAnimals_Price() {
		return Animals_Price;
	}

	public String GetAnimals_Address() {
		return Animals_Address;
	}

	public String GetAnimals_Color() {
		return Animals_Color;
	}

	public String GetAnimals_Description() {
		return Animals_Description;
	}

	public BufferedImage GetAnimals_Image() {
		return Animals_Image;
	}

	public String GetAnimals_Size() {
		return Animals_Size;
	}

	public String GetAnimals_Gender() {
		return Animals_Gender;
	}

	public int GetAnimals_OwnerID() {
		return Animals_OwnerID;
	}

	public String GetAnimals_OwnerName() {
		return Animals_OwnerName;
	}

}
