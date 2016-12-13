package edu.sjsu.db;

/**
 * COPYRIGHT 2016 TeamMinion. All Rights Reserved. 
 * Animal Bridge
 * CS160 Group Project
 * @author Kun Su, Archer Zhao, Nelson Liang, Marco Kuang, Peilu Liu
 * @version 1.00 2016/11/30
 */

/**
 * A new data structure for storing Attribute Name and Date Type 
 * @author Kun
 *
 */
public class Attribute {

	private String AttributeName;
	private String DataType;
	
	public Attribute(String AttributeName) {
		this.AttributeName = AttributeName;
		if(AttributeName.contains("ID")) {
			this.DataType = "int";
		}
		else if(AttributeName.contains("Date")) {
			this.DataType = "String";
		}
		else if(AttributeName.contains("Image")) {
			this.DataType = "BufferedImage";
		}
		else {
			this.DataType = "String";
		}
	}
	
	public String GetAttributeName() {
		return AttributeName;
	}
	
	public String GetDataType() {
		return DataType;
	}
	
	public void SetDataType(String DataType) {
		this.DataType = DataType;
	}
}
