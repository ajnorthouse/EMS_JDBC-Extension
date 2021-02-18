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

public class CompanyDAOClass implements CompanyDAO {
	Connection conn = ConnectionManager.getConnection();
	private String idMatchClause = " WHERE comp_id = ?";
	private String companySelectClause = " SELECT * FROM company";
	
	@Override
	public List<Company> getAllCompanies() {
		// TODO Auto-generated method stub
		
		String allCompsQuery = companySelectClause;
		List<Company> list = new ArrayList<Company>();
		try {
			PreparedStatement allCompsStmt = conn.prepareStatement(allCompsQuery);
			ResultSet compResults = allCompsStmt.executeQuery();
			while (compResults.next()) {
				list.add(ObjectCreator.createCompanyObject(compResults));
			}
			return list;
			
		} catch (SQLException e) {
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
			compResult.next();
			toReturn = ObjectCreator.createCompanyObject(compResult);
			return toReturn;
		} catch (SQLException e) {
			e.printStackTrace();
			return toReturn;
		}
		
	}

	
	@Override
	public int createCompany(Company c) {
		// TODO Auto-generated method stub
		String createQuery = "INSERT INTO company (comp_name, comp_budget) VALUES (?, ?);";
		try {
			PreparedStatement createStmt = conn.prepareStatement(createQuery);
			createStmt.setString(1, c.getName()); 
			createStmt.setInt(2, c.getBudget());
			createStmt.execute();
			createStmt = conn.prepareStatement("SELECT comp_id FROM company WHERE comp_name = ? AND com_budget = ?");
			createStmt.setString(1, c.getName()); 
			createStmt.setInt(2, c.getBudget());
			ResultSet rs = createStmt.executeQuery();
			rs.next();
			return rs.getInt(0);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
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
		
		String updateQuery = " UPDATE company SET comp_name = ? , budget = ? " + idMatchClause;
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
	
}

