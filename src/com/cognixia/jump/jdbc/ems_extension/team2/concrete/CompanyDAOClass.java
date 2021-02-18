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
	private String idMatchClause = " WHERE comp_id = ? ";
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
				list.add(createCompanyObject(compResults));
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
			toReturn = createCompanyObject(compResult);
			return toReturn;
		} catch (SQLException e) {
			e.printStackTrace();
			return toReturn;
		}
		
	}

	@Override
	public boolean createCompany(Company c) {
		// TODO Auto-generated method stub
		
		//Gonna have to handle address weirdly
		
		return false;
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
		
		//String updateQuery = "UPDATE Customers SET ContactName = , City= 'Frankfurt'";
		
		return false;
	}

	@Override
	public List<Department> getCompanyDepartments(Company c) {
		// TODO Auto-generated method stub
		return null;
	}

	
	private Company createCompanyObject(ResultSet rs) throws SQLException {
		
		int id = rs.getInt("comp_id");
		String name = rs.getString("comp_name");
		int budget = rs.getInt("comp_budget");
		String address = rs.getString("address");
		Company toReturn = new Company(name, budget);
		toReturn.setId(id);
		return toReturn;
	}
	
}

