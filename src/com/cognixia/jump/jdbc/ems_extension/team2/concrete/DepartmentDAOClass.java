package com.cognixia.jump.jdbc.ems_extension.team2.concrete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cognixia.jump.jdbc.ems_extension.team2.ConnectionManager;
import com.cognixia.jump.jdbc.ems_extension.team2.interfaces.DepartmentDAO;
import com.cognixia.jump.jdbc.ems_extension.team2.model.Department;
import com.cognixia.jump.jdbc.ems_extension.team2.model.Employee;

public class DepartmentDAOClass implements DepartmentDAO {
	
	Connection conn = ConnectionManager.getConnection();
	private String idMatchClause = " WHERE dept_id = ?";
	private String deptSelectClause = " SELECT * FROM department ";
	
	@Override
	public List<Department> getAllDepartments() {
		// TODO Auto-generated method stub
		String allDeptsQuery = deptSelectClause;
		List<Department> list = new ArrayList<Department>();
		try {
			PreparedStatement allDeptsStmt = conn.prepareStatement(allDeptsQuery);
			ResultSet deptResults = allDeptsStmt.executeQuery();
			while (deptResults.next()) {
				list.add(ObjectCreator.createDepartmentObject(deptResults));
			}
			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return list;
		}
		
	}

	@Override
	public Department getDepartment(int id) {
		// TODO Auto-generated method stub
		String deptsQuery = deptSelectClause + idMatchClause;
		Department dept = null;
		try {
			PreparedStatement deptsStmt = conn.prepareStatement(deptsQuery);
			deptsStmt.setInt(1, id);
			ResultSet deptResults = deptsStmt.executeQuery();
			deptResults.next();
			dept = ObjectCreator.createDepartmentObject(deptResults);
			return dept;
		} catch (SQLException e) {
			e.printStackTrace();
			return dept;
		}
	}

	@Override
	public boolean createDepartment(Department d) {
		// TODO Auto-generated method stub
		
		String createQuery = "INSERT INTO department (dept_name, dept_phone_num, dept_budget, comp_id) VALUES( ? , ? , ? , ? )";
		try {
			PreparedStatement createStmt = conn.prepareStatement(createQuery);
			createStmt.setString(1, d.getName()); 
			createStmt.setString(2, d.getPhoneNumber()); 
			createStmt.setInt(3, d.getBudget()); 
			createStmt.setInt(4, d.getCompanyId()); 
			return createStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean deleteDepartment(Department d) {
		// TODO Auto-generated method stub
		String deleteQuery = " DELETE FROM department " + idMatchClause;
		try {
			PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery);
			deleteStmt.setInt(1, d.getId());
			return deleteStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean updateDepartment(Department d) {
		// TODO Auto-generated method stub
		
		String updateQuery = " UPDATE department SET dept_name = ? , dept_phone_num = ? , budget = ? , comp_id = ? " + idMatchClause;
		try {
			PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
			updateStmt.setString(1, d.getName());
			updateStmt.setString(2, d.getPhoneNumber());
			updateStmt.setInt(3, d.getBudget());
			updateStmt.setInt(4, d.getCompanyId());
			return updateStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public List<Department> getAllDepartmentsOfCompany(int companyId) {
		// TODO Auto-generated method stub
		String deptsQuery = " SELECT * FROM department WHERE comp_id = ?";
		List<Department> list = new ArrayList<Department>();
		
		try {
			PreparedStatement deptsStmt = conn.prepareStatement(deptsQuery);
			deptsStmt.setInt(1, companyId);
			ResultSet deptResults = deptsStmt.executeQuery();
			
			while (deptResults.next()) {
				list.add(ObjectCreator.createDepartmentObject(deptResults));
			}
			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return list;
		}
	}

	public List<Employee> getDepartmentEmployees(int departmentId) {
		String query = "select * from employee WHERE dept_id = ?";
		List<Employee> list = new ArrayList<Employee>();
		try {
			PreparedStatement allEmplsStmt = conn.prepareStatement(query);
			allEmplsStmt.setInt(1, departmentId);
			ResultSet emplResults = allEmplsStmt.executeQuery();
			while (emplResults.next()) {
				list.add(ObjectCreator.createEmployeeObject(emplResults));
			}
			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return list;
		}
	}

}
