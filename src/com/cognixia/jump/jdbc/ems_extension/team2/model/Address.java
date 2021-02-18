package com.cognixia.jump.jdbc.ems_extension.team2.model;

public class Address {

	private int id;
	private String streetAddr;
	private String city;
	private char[] state;
	private String zipCode;
	
	private static int idCounter = 0;
	
	public Address(int id, String streetAddr, String city, char[] state, String zipCode) {
		super();
		this.id = id;
		this.streetAddr = streetAddr;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}
	
	public Address(String streetAddr, String city, char[] state, String zipCode) {
		super();
		this.id = generateNewId();
		this.streetAddr = streetAddr;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStreetAddr() {
		return streetAddr;
	}
	public void setStreetAddr(String streetAddr) {
		this.streetAddr = streetAddr;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public char[] getState() {
		return state;
	}
	public void setState(char[] state) {
		this.state = state;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	
	private int generateNewId() {
		idCounter++;
		return idCounter;
	}
	
	
}
