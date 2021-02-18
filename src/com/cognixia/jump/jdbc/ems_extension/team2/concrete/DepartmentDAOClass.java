package com.cognixia.jump.jdbc.ems_extension.team2.concrete;

import java.sql.Connection;
import java.util.List;

import com.cognixia.jump.jdbc.ems_extension.team2.ConnectionManager;
import com.cognixia.jump.jdbc.ems_extension.team2.interfaces.DepartmentDAO;
import com.cognixia.jump.jdbc.ems_extension.team2.model.Department;
import com.cognixia.jump.jdbc.ems_extension.team2.model.Employee;

public class DepartmentDAOClass implements DepartmentDAO {
	
	Connection conn = ConnectionManager.getConnection();
	
	@Override
	public List<Department> getAllDepartments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Department getDepartment(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createDepartment(Department d) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteDepartment(Department d) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateDepartment(Department d) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Employee> getDepartmentEmployees(Department d) {
		// TODO Auto-generated method stub
		return null;
	}

}
