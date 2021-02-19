package com.cognixia.jump.jdbc.ems_extension.team2.concrete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cognixia.jump.jdbc.ems_extension.team2.ConnectionManager;
import com.cognixia.jump.jdbc.ems_extension.team2.interfaces.EmployeeDAO;
import com.cognixia.jump.jdbc.ems_extension.team2.model.Employee;

public class EmployeeDAOClass implements EmployeeDAO {
	
	Connection conn = ConnectionManager.getConnection();
	private String idMatchClause = " WHERE emp_id = ?";
	private String emplSelectClause = " SELECT * FROM employee";
	
	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		String allEmplsQuery = emplSelectClause;
		List<Employee> list = new ArrayList<Employee>();
		try {
			PreparedStatement allEmplsStmt = conn.prepareStatement(allEmplsQuery);
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
	
	public List<Employee> getAllEmployeesOfCompany(int companyId) {
		String query = "select employee.* from employee, department WHERE employee.dept_id = department.dept_id AND department.comp_id = ?";
		List<Employee> list = new ArrayList<Employee>();
		try {
			PreparedStatement allEmplsStmt = conn.prepareStatement(query);
			allEmplsStmt.setInt(1, companyId);
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

	@Override
	public Employee getEmployee(int id) {
		// TODO Auto-generated method stub
		String emplQuery = emplSelectClause + idMatchClause;
		Employee empl = null;
		try {
			PreparedStatement emplStmt = conn.prepareStatement(emplQuery);
			emplStmt.setInt(1, id);
			ResultSet emplResults = emplStmt.executeQuery();
			emplResults.next();
			empl = ObjectCreator.createEmployeeObject(emplResults);
			return empl;
		} catch (SQLException e) {
			e.printStackTrace();
			return empl;
		}
		
	}

	@Override
	public int createEmployee(Employee em) {
		// TODO Auto-generated method stub
		
		String createQuery = "INSERT INTO employee (first_name, last_name, salary, job_title, phone_num, address_id, dept_id) VALUES( ? , ? , ? , ? , ? , ? , ? )";
		try {
			PreparedStatement createStmt = conn.prepareStatement(createQuery);
			createStmt.setString(1, em.getFirstName()); 
			createStmt.setString(2, em.getLastName());
			createStmt.setInt(3, em.getSalary()); 
			createStmt.setString(4, em.getJobTitle()); 
			createStmt.setString(5, em.getPhoneNumber()); 
			createStmt.setInt(6, em.getAddressId()); 
			createStmt.setInt(7, em.getDepartmentId()); 
			createStmt.execute();

			createQuery = "SELECT emp_id FROM employee WHERE first_name = ? AND last_name = ? AND salary = ? AND job_title = ? AND "
					+ "phone_num = ? AND address_id = ? AND dept_id = ?";
			createStmt = conn.prepareStatement(createQuery);
			createStmt.setString(1, em.getFirstName()); 
			createStmt.setString(2, em.getLastName());
			createStmt.setInt(3, em.getSalary()); 
			createStmt.setString(4, em.getJobTitle()); 
			createStmt.setString(5, em.getPhoneNumber()); 
			createStmt.setInt(6, em.getAddressId()); 
			createStmt.setInt(7, em.getDepartmentId()); 
			ResultSet rs = createStmt.executeQuery();
			rs.next();
			return rs.getInt("emp_id");
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	
	}

	@Override
	public boolean deleteEmployee(Employee em) {
		// TODO Auto-generated method stub
		String deleteQuery = " DELETE FROM employee " + idMatchClause;
		try {
			PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery);
			deleteStmt.setInt(1, em.getId());
			return !deleteStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean updateEmployee(Employee em) {
		// TODO Auto-generated method stub
		String updateQuery = " UPDATE employee SET first_name = ? , last_name = ?, salary = ? , phone_num = ? , job_title = ? , address_id = ? , dept_id = ? " + idMatchClause;
		try {
			PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
			updateStmt.setString(1, em.getFirstName()); 
			updateStmt.setString(2, em.getLastName());
			updateStmt.setInt(3, em.getSalary()); 
			updateStmt.setString(4, em.getJobTitle()); 
			updateStmt.setString(5, em.getPhoneNumber()); 
			updateStmt.setInt(6, em.getAddressId()); 
			updateStmt.setInt(7, em.getDepartmentId()); 
			updateStmt.setInt(8, em.getId()); 
			return updateStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
