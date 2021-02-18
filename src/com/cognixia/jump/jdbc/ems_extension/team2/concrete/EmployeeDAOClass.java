package com.cognixia.jump.jdbc.ems_extension.team2.concrete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cognixia.jump.jdbc.ems_extension.team2.ConnectionManager;
import com.cognixia.jump.jdbc.ems_extension.team2.interfaces.EmployeeDAO;
import com.cognixia.jump.jdbc.ems_extension.team2.model.Department;
import com.cognixia.jump.jdbc.ems_extension.team2.model.Employee;

public class EmployeeDAOClass implements EmployeeDAO {
	
	Connection conn = ConnectionManager.getConnection();
	private String idMatchClause = " WHERE employee.emp_id = ? ";
	private String emplSelectClause = " SELECT e.* FROM employee AS e ";
	
	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		String allEmplsQuery = emplSelectClause;
		List<Employee> list = new ArrayList<Employee>();
		try {
			PreparedStatement allEmplsStmt = conn.prepareStatement(allEmplsQuery);
			ResultSet emplResults = allEmplsStmt.executeQuery();
			do {
				list.add(ObjectCreator.createEmployeeObject(emplResults));
			} while (emplResults.next());
			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return list;
		}
	}

	@Override
	public Employee getEmployee(int id) {
		// TODO Auto-generated method stub
		String emplQuery = emplSelectClause + idMatchClause;
		Employee empl = null;
		try {
			PreparedStatement emplStmt = conn.prepareStatement(emplQuery);
			emplStmt.setInt(1, id);
			ResultSet emplResults = emplStmt.executeQuery();
			empl = ObjectCreator.createEmployeeObject(emplResults);
			return empl;
		} catch (SQLException e) {
			e.printStackTrace();
			return empl;
		}
		
	}

	@Override
	public boolean createEmployee(Employee em) {
		// TODO Auto-generated method stub
		
		String createQuery = "INSERT INTO employee (first_name, last_name, salary, job_title, phone_num, address_id, dept_id) VALUES( ? , ? , ? , ? , ? , ? , ? )";
		try {
			PreparedStatement createStmt = conn.prepareStatement(createQuery);
			createStmt.setString(1, em.getFirstName()); 
			createStmt.setString(2, em.getFirstName());
			createStmt.setInt(3, em.getSalary()); 
			createStmt.setString(4, em.getJobTitle()); 
			createStmt.setString(5, em.getPhoneNumber()); 
			createStmt.setInt(6, em.getAddressId()); 
			createStmt.setInt(7, em.getDepartmentId()); 
			return createStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	
	}

	@Override
	public boolean deleteEmployee(Employee em) {
		// TODO Auto-generated method stub
		String deleteQuery = " DELETE FROM employee " + idMatchClause;
		try {
			PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery);
			deleteStmt.setInt(1, em.getId());
			return deleteStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean updateEmployee(Employee em) {
		// TODO Auto-generated method stub
		String updateQuery = " UPDATE department SET first_name = ? , last_name = ?, salary = ? , phone_num = ? , job_title = ? , address_id = ? , dept_id = ? " + idMatchClause;
		try {
			PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
			updateStmt.setString(1, em.getFirstName()); 
			updateStmt.setString(2, em.getFirstName());
			updateStmt.setInt(3, em.getSalary()); 
			updateStmt.setString(4, em.getJobTitle()); 
			updateStmt.setString(5, em.getPhoneNumber()); 
			updateStmt.setInt(6, em.getAddressId()); 
			updateStmt.setInt(7, em.getDepartmentId()); 
			return updateStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
