package com.cognixia.jump.jdbc.ems_extension.team2.concrete;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.cognixia.jump.jdbc.ems_extension.team2.model.Address;
import com.cognixia.jump.jdbc.ems_extension.team2.model.Company;
import com.cognixia.jump.jdbc.ems_extension.team2.model.Department;
import com.cognixia.jump.jdbc.ems_extension.team2.model.Employee;

public class ObjectCreator {

	public static Company createCompanyObject(ResultSet rs) throws SQLException {
		int id = rs.getInt("comp_id");
		String name = rs.getString("comp_name");
		int budget = rs.getInt("comp_budget");
		
		Company toReturn = new Company(name, budget);
		toReturn.setId(id);
		
		return toReturn;
	}
	
	
	public static Department createDepartmentObject(ResultSet rs) throws SQLException {
		int id = rs.getInt("dept_id");
		String name = rs.getString("dept_name");
		String phoneNum = rs.getString("dept_phone_num");
		int budget = rs.getInt("dept_budget");
		
		Department toReturn = new Department(name, phoneNum, budget);
		toReturn.setId(id);
		
		return toReturn;
	}
	
	
	public static Employee createEmployeeObject(ResultSet rs) throws SQLException {
		int id = rs.getInt("emp_id");
		int deptId = rs.getInt("dept_id");
		int addrId = rs.getInt("address_id");
		String firstName = rs.getString("first_name");
		String lastName = rs.getString("last_name");
		String jobTitle = rs.getString("job_title");
		String phoneNum = rs.getString("phone_num");
		int salary = rs.getInt("salary");
		
		Employee toReturn = new Employee(firstName, lastName, salary, jobTitle, phoneNum);
		toReturn.setId(id);
		toReturn.setAddressId(addrId);
		toReturn.setDepartmentId(deptId);
		
		return toReturn;
	}
	
	
	public static Address createAddressObject(ResultSet rs) throws SQLException {
		int id = rs.getInt("address_id");
		String street = rs.getString("address");
		String city = rs.getString("city");
		String state = rs.getString("state");
		String zip = rs.getString("zip_code");
		
		Address toReturn = new Address(id, street, city, state, zip);
		toReturn.setId(id);
		
		return toReturn;
	}
	
}
