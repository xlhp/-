package com;

public class ConnectionObject {
	private String ID;
	private String NAME;
	private String TITLE;
	private float PRICE;
	private int YR;
	private String DESCRIPTION;
	private int SALA_AMOUNT;
	public ConnectionObject(String id,String name,String title,float price,int yr,String description,int sala_amount) {
		this.ID = id;
		this.NAME = name;
		this.TITLE = title;
		this.PRICE = price;
		this.YR = yr;
		this.DESCRIPTION = description;
		this.SALA_AMOUNT = sala_amount;
	}
	public String getID() {
		return ID;
	}
	public String getNAME() {
		return NAME;
	}
	public String getTITLE() {
		return TITLE;
	}
	public float getPRICE() {
		return PRICE;
	}
	public int getYR() {
		return YR;
	}
	public String getDESCRIPTION() {
		return DESCRIPTION;
	}
	public int getSALA_AMOUNT() {
		return SALA_AMOUNT;
	}
	
}
