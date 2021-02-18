package com.cognixia.jump.jdbc.ems_extension.team2.concrete;

import java.io.Serializable;
import java.util.ArrayList;

import com.cognixia.jump.jdbc.ems_extension.team2.model.Company;
import com.cognixia.jump.jdbc.ems_extension.team2.model.Department;
import com.cognixia.jump.jdbc.ems_extension.team2.model.Employee;
/***
 * 
 * @author Alex Donaldson
 *
 */
public class CompanyManager implements Serializable {

	private static final long serialVersionUID = 1L;
	private Company company;
	
	public CompanyManager(Company company) {
		this.company = company;
	}
	
	public CompanyManager() {
		this.company = new Company();
	}
	
	
	//Edit methods
	
	public boolean editCompany(String name, int budget, String location) {
		
		if (budget < company.getBudgetUsed()) {
			System.out.println("That budget is too low to accomodate the current budget in use!");
			return false;
		}
		company.setName(name);
		company.setBudget(budget);
		company.setLocation(location);
		
		return true;
	}
	
	public boolean resetCompany() {
		this.company = new Company();
		return true;
	}
	
	public void editDepartment(int deptId, String name, String phoneNumber, int budget) {
		Department deptToEdit = company.getDeptByID(deptId);
		deptToEdit.setName(name);
		deptToEdit.setPhoneNumber(phoneNumber);
		deptToEdit.setBudget(budget);
	}
	
	public void editEmployee(int emplId, int deptId, String firstName, String lastName, int salary, String title, String phoneNumber) {
		Department dept = company.getDeptByID(deptId);
		Employee emplToEdit = dept.getEmployeeByID(emplId);
		emplToEdit.setFirstName(firstName);
		emplToEdit.setLastName(lastName);
		emplToEdit.setSalary(salary);
		emplToEdit.setJobTitle(title);
		emplToEdit.setPhoneNumber(phoneNumber);	
	}
	
	
	//Add methods
	
	public int addDepartment(String name, String phoneNumber, int budget) {
		Department deptToAdd = new Department(name, phoneNumber, budget);
		if (company.addDepartment(deptToAdd)) {
			return deptToAdd.getId();
		} else {
			return 0;
		}
	}
	
	public int addEmployee(int deptId, String firstName, String lastName, int salary, String title, String phoneNumber) {
		Employee emplToAdd = new Employee(firstName, lastName, salary, title, phoneNumber);
		Department deptToAddTo = company.getDeptByID(deptId);
		if (deptToAddTo != null) {
			deptToAddTo.addEmployee(emplToAdd);
			return emplToAdd.getId();
		}
		return 0;
	}
	
	
	//Remove methods
	
	public boolean removeDepartment(int deptId) {
		return company.removeDepartmentByID(deptId);
	}
	
	public boolean removeEmployee(int emplId, int deptId) {
		Department toRemoveFrom = company.getDeptByID(deptId);
		if (toRemoveFrom != null) {
			return toRemoveFrom.removeEmployeeByID(emplId);
		}
		return false;
	}
	
	
	//Getters
	public Company getCompany() {
		return company;
	}
	
	public ArrayList<Department> getAllDepartments() {
		return company.getDepts();
	}
	
	public ArrayList<Employee> getAllEmployees(int deptId) {
		return company.getDeptByID(deptId).getEmployee();
	}
	
	public Department getDepartment(int deptId) {
		return company.getDeptByID(deptId);
	}
	
	public Employee getEmployee(int emplId, int deptId) {
		return company.getDeptByID(deptId).getEmployeeByID(emplId);
	}
	
}
