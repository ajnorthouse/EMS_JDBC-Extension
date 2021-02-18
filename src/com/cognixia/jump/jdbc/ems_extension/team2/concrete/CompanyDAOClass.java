package com.cognixia.jump.jdbc.ems_extension.team2.concrete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cognixia.jump.jdbc.ems_extension.team2.ConnectionManager;
import com.cognixia.jump.jdbc.ems_extension.team2.interfaces.CompanyDAO;
import com.cognixia.jump.jdbc.ems_extension.team2.model.Company;
import com.cognixia.jump.jdbc.ems_extension.team2.model.Department;

public class CompanyDAOClass implements CompanyDAO {
	
	Connection conn = ConnectionManager.getConnection();
	private String idMatchClause = " WHERE company.comp_id = ? ";
	private String companySelectClause = " SELECT c.*, a.address FROM company AS c INNER JOIN address AS a on c.address_id = a.address_id ";
	
	@Override
	public List<Company> getAllCompanies() {
		// TODO Auto-generated method stub
		
		String allCompsQuery = companySelectClause;
		List<Company> list = new ArrayList<Company>();
		try {
			PreparedStatement allCompsStmt = conn.prepareStatement(allCompsQuery);
			ResultSet compResults = allCompsStmt.executeQuery();
			do {
				list.add(ObjectCreator.createCompanyObject(compResults));
			} while (compResults.next());
			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return list;
		}

	}

	
	@Override
	public Company getCompany(int id) {
		// TODO Auto-generated method stub
		String compQuery = companySelectClause + idMatchClause;
		Company toReturn = null;
		
		try {
			PreparedStatement compStmt = conn.prepareStatement(compQuery);
			compStmt.setInt(1, id);
			ResultSet compResult = compStmt.executeQuery();
			toReturn = ObjectCreator.createCompanyObject(compResult);
			return toReturn;
		} catch (SQLException e) {
			e.printStackTrace();
			return toReturn;
		}
		
	}

	
	@Override
	public boolean createCompany(Company c) {
		// TODO Auto-generated method stub
		String createQuery = "INSERT INTO company (comp_name, comp_budget) VALUES( ? , ? )";
		try {
			PreparedStatement createStmt = conn.prepareStatement(createQuery);
			createStmt.setString(1, c.getName()); 
			createStmt.setInt(2, c.getBudget()); 
			return createStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	
	@Override
	public boolean deleteCompany(Company c) {
		// TODO Auto-generated method stub
		
		String deleteQuery = " DELETE FROM company " + idMatchClause;
		try {
			PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery);
			deleteStmt.setInt(1, c.getId());
			return deleteStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
	

	@Override
	public boolean updateCompany(Company c) {
		// TODO Auto-generated method stub
		
		String updateQuery = " UPDATE company SET comp_name = ? , City = ? " + idMatchClause;
		try {
			PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
			updateStmt.setString(1, c.getName());
			updateStmt.setInt(2, c.getBudget());
			updateStmt.setInt(3, c.getId());
			return updateStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	
	@Override
	public List<Department> getCompanyDepartments(Company c) {
		// TODO Auto-generated method stub
		String deptsQuery = " SELECT d.* FROM department AS d INNER JOIN company AS c on d.comp_id = c.comp_id " + idMatchClause;
		List<Department> list = new ArrayList<Department>();
		
		try {
			PreparedStatement deptsStmt = conn.prepareStatement(deptsQuery);
			deptsStmt.setInt(1, c.getId());
			ResultSet deptResults = deptsStmt.executeQuery();
			
			do {
				list.add(ObjectCreator.createDepartmentObject(deptResults));
			} while (deptResults.next());
			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return list;
		}
	}
	
}

