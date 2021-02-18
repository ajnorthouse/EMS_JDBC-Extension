package com.cognixia.jump.jdbc.ems_extension.team2.model;

import java.util.ArrayList;
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
	private ArrayList<Employee> employee;
	public Department(String name, String phoneNumber, int budget, ArrayList<Employee> employee) {
		super();
		this.id = generateNewId();
		this.name = name;
		this.budget = budget;
		this.phoneNumber = phoneNumber;
		this.employee = employee;
	}

	//Constructor
	public Department(String name, String phoneNumber, int budget)
	{
		super();
		this.id = generateNewId();
		this.name = name;
		this.employee = new ArrayList<Employee>();
		this.phoneNumber = phoneNumber;
		this.budget = budget;
	}
	
	
	//Class methods
	private int generateNewId() {
		idCounter++;
		return idCounter;
	}
	//This method allows the user to add a new employee to a department
	public void addEmployee(Employee emp){
			employee.add(emp);
			System.out.println(emp.getFirstName() + " " + emp.getLastName() + " has been added to the department!");
	}
	//This method removes an employee from a department
	public void removeEmployee(Employee emp) {
		for (int iterator = 0; iterator < employee.size(); iterator++) {
			if ((employee.get(iterator)).getId() == emp.getId()) {
				employee.remove(iterator);
				break;
			}
		}
	}
	//This method allows the user to remove an employee by ID
	public boolean removeEmployeeByID(int id) {
		Employee emplToRemove = getEmployeeByID(id);
		
		if (emplToRemove != null) {
			employee.remove(emplToRemove);
			return true;
		}
		
		return false;
	}
	//Lists the deparment's information
	public String listInfo() {
		return "Department [id = " + id + ", name = " + name + ", phoneNumber = " + phoneNumber + ", budget = " + budget
				+ ", number of Employees = " + employee.size() + "]";
	}
	//Lists the department's assigned employees
	public String listEmployees() {
		StringBuilder strBuilder = new StringBuilder("");
		strBuilder.append("\nCurrently Assigned Employees:\n");
		strBuilder.append("ID - Name:\n");
		for (Employee employee : employee) {
			strBuilder.append(employee.getId() + " - ");
			strBuilder.append(employee.getFirstName() + " ");
			strBuilder.append(employee.getLastName() + "\n");
		}
		return strBuilder.toString();
	}
	//End of class methods
	
	
	//Getters and Setters
	public Employee getEmployeeByID(int id) {
		
		for (int i = 0; i < employee.size(); i++) {
			Employee empl = employee.get(i);
			if (empl.getId() == id) return empl;
		}
		
		return null;
	}
	public int getId() {
		return id;
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
	public ArrayList<Employee> getEmployee() {
		return employee;
	}
	public void setEmployee(ArrayList<Employee> employee) {
		this.employee = employee;
	}
	@Override
	public String toString() {
		return "Department [id = " + id + ", name = " + name + ", phoneNumber = " + phoneNumber + ", budget = " + budget
				+ ", employee = " + employee + "]";
	}
}
