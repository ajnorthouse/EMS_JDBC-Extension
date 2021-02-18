package com.cognixia.jump.jdbc.ems_extension.team2model;

import java.io.Serializable;
import java.util.ArrayList;

import com.cognixia.jump.jdbc.ems_extension.team2.OverBudgetException;

public class Company implements Serializable{

	private static final int DEFAULT_BUDGET = 1000000;
	private static final String DEFAULT_NAME = "Default Company Name";
	private static final String DEFAULT_LOCATION = "123 Sesame Street";
	private static final long serialVersionUID = 1L;
	private static int idCounter = 0;
	
	private int id;
	private String name;
	private ArrayList<Department> depts;
	private int budget;
	private String location;
	
	// Constructors
	/*
	 * All constructors assign the value returned by generateNewId() to this.id .
	 * Otherwise, they use different combinations of parameters to assign to the
	 * name, depts, and budget variables. If a variable doesn't have an associated
	 * parameter, it is assigned a default/empty value. 
	 * 
	 */
	
	public Company(String name, int budget, String location, ArrayList<Department> depts) {
		super();
		this.id = generateNewId();
		this.name = name;
		this.depts = depts;
		this.budget = budget;
		this.location = location;
	}


	public Company(String name, int budget, String location) {
		super();
		this.id = generateNewId();
		this.name = name;
		this.depts = new ArrayList<Department>();
		this.budget = budget;
		this.location = location;
	}
	
	public Company(String name, int budget) {
		super();
		this.id = generateNewId();
		this.name = name;
		this.depts = new ArrayList<Department>();
		this.budget = budget;
		this.location = DEFAULT_LOCATION;
	}
	
	public Company(String name) {
		super();
		this.id = generateNewId();
		this.name = name;
		this.depts = new ArrayList<Department>();
		this.budget = DEFAULT_BUDGET;
		this.location = DEFAULT_LOCATION;
	}
	
	public Company() {
		super();
		this.id = generateNewId();
		this.name = DEFAULT_NAME;
		this.depts = new ArrayList<Department>();
		this.budget = DEFAULT_BUDGET;
		this.location = DEFAULT_LOCATION;
	}

	
	// Helper methods
	
	private int generateNewId() {
		idCounter++;
		return idCounter;
	}
	
	
	/*
	 * Allows for generic actions defined by the Functional Interface 
	 * PerDeptAction parameter to be applied to each and every department
	 */
	private void actOnAllDepartments(PerDeptAction pda) {
		for (Department d : depts) {
			pda.execute(d);
		}
	}
	
	

	//Class methods
	
	/*
	 * Attempts to add dept to the list of departments; throws OverBudgetException if
	 * dept's budget would put the total budget used by all departments over the limit.
	 * Otherwise, dept is added to depts.
	 * 
	 * Uses a stream to calculate the sum of all current departments' budgets
	 */
	public boolean addDepartment(Department dept){
		try {
			int budgetUsed = getBudgetUsed();
			budgetUsed += dept.getBudget();		
			if (budgetUsed > budget) {
				throw new OverBudgetException("That department costs too much!");
			} else {
				depts.add(dept);
				System.out.println(dept.getName() + " added to the list of departments!");
				return true;
			}
		} catch (OverBudgetException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	/*
	 * Attempts to remove the department at the index deptIdx from depts; catches 
	 * IndexOutOfBoundsException and returns false. Otherwise, the department is removed
	 * and the function returns true.
	 */
	public boolean removeDepartment(int deptIdx) {
		try {
			depts.remove(deptIdx);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("The index of depts you tried to remove from was out of bounds!");
			return false;
		}
		return true;
	}
	
		
	/*
	 * Attempts to remove the department at the index deptIdx from depts; catches 
	 * IndexOutOfBoundsException and returns false. Otherwise, the department is removed
	 * and the function returns true.
	 */
	public boolean removeDepartmentByID(int deptId) {
		Department deptToRemove = getDeptByID(deptId);
		
		if (deptToRemove != null) {
			depts.remove(deptToRemove);
			return true;
		}
		
		return false;
	}
	
	
	/*
	 * Clears out all contents of the depts ArrayList
	 */
	public void removeAllDepartments() {
		depts.clear();
	}
	
	
	/*
	 * Defines a PerDeptAction to print out the department's name
	 * and passes the PerDeptAction to actOnAllDepartments
	 */
	public void listAllDepartments() {
		PerDeptAction pda = (a) -> { System.out.println(a.toString()); };
		actOnAllDepartments(pda);
	}
	
	
	public int getBudgetUsed() {
		int budgetUsed = depts.stream().mapToInt(d -> d.getBudget()).sum();
		return budgetUsed;
	}

	
	
	// Getters & Setters
	
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

	public ArrayList<Department> getDepts() {
		return depts;
	}

	public void setDepts(ArrayList<Department> depts) {
		this.depts = depts;
	}

	public int getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	/*
	 * Returns the department in depts with the given id; if the department
	 * doesn't exist, returns null.
	 */
	public Department getDeptByID(int id) {
		for (int i = 0; i < depts.size(); i++) {
			Department dept = depts.get(i);
			if (dept.getId() == id) return dept;
		}
		return null;
	}
	


	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", depts=" + depts + ", budget=" + budget + ", location="
				+ location + "]";
	}
	
	//specialized toString-like methods:
	//Lists the company's information & size of depts
	public String listInfo() {
		return "Company [id = " + id + ", name = " + name + ", active departments=" + depts.size()
				+ ", budget = " + budget + ", location = " + location + "]";
	}
	//Lists the company's assigned departments
	public String listDepartments() {
		StringBuilder strBuilder = new StringBuilder("");
		strBuilder.append("\nCurrently Active Departments:\n");
		strBuilder.append("ID - Name:\n");
		for (Department department : depts) {
			strBuilder.append(department.getId() + " - ");
			strBuilder.append(department.getName() + "\n");
		}
		return strBuilder.toString();
	}

}

@FunctionalInterface
interface PerDeptAction {
	public void execute(Department d);
}