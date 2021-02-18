package com.cognixia.jump.jdbc.ems_extension.team2.model;

/***
 * 
 * @author Raymund Palafox
 *
 */
public class Department
{
	//Static Variables
	private static int idCounter = 0;
	//object variables
	private int id;
	private String name;
	private String phoneNumber;
	private int budget;
	private int companyId;

	//Constructor
	public Department(String name, String phoneNumber, int budget, int companyId)
	{
		super();
		this.id = generateNewId();
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.budget = budget;
		this.companyId = companyId;
	}
	
	
	//Class methods
	private int generateNewId() {
		idCounter++;
		return idCounter;
	}
	
	//Lists the deparment's information
	public String listInfo() {
		return "Department [id = " + id + ", name = " + name + ", phoneNumber = " + phoneNumber + ", budget = " + budget + "]";
	}
	//Lists the department's assigned employees
	public String listEmployees() {
		StringBuilder strBuilder = new StringBuilder("");
		strBuilder.append("\nCurrently Assigned Employees:\n");
		strBuilder.append("ID - Name:\n");
		return strBuilder.toString();
	}
	//End of class methods
	
	
	//Getters and Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBudget() {
		return budget;
	}
	public void setBudget(int budget) {
		this.budget = budget;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * @return	the companyId
	 */
	public int getCompanyId() {
		return companyId;
	}

	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", phoneNumber=" + phoneNumber + ", budget=" + budget
				+ ", companyId=" + companyId + "]";
	}
}
