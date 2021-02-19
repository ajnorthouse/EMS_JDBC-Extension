package com.cognixia.jump.jdbc.ems_extension.team2.concrete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cognixia.jump.jdbc.ems_extension.team2.ConnectionManager;
import com.cognixia.jump.jdbc.ems_extension.team2.interfaces.AddressDAO;
import com.cognixia.jump.jdbc.ems_extension.team2.model.Address;

public class AddressDAOClass implements AddressDAO{

	Connection conn = ConnectionManager.getConnection();
	private String idMatchClause = " WHERE address_id = ? ";
	private String addrSelectClause = "SELECT * FROM address";
	
	@Override
	public List<Address> getAllAddresses() {
		// TODO Auto-generated method stub
		
		String allAddrQuery = addrSelectClause;
		List<Address> list = new ArrayList<Address>();
		try {
			PreparedStatement allAddrStmt = conn.prepareStatement(allAddrQuery);
			ResultSet addrResults = allAddrStmt.executeQuery();
			while (addrResults.next()) {
				list.add(ObjectCreator.createAddressObject(addrResults));
			}
			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return list;
		}
		
	}
	@Override
	public Address getAddress(int id) {
		// TODO Auto-generated method stub
		String addrQuery = addrSelectClause + idMatchClause;
		Address addr = null;
		try {
			PreparedStatement addrStmt = conn.prepareStatement(addrQuery);
			addrStmt.setInt(1, id);
			ResultSet addrResults = addrStmt.executeQuery();
			addrResults.next();
			addr = ObjectCreator.createAddressObject(addrResults);
			return addr;
		} catch (SQLException e) {
			e.printStackTrace();
			return addr;
		}

	}
	@Override
	public int createAddress(Address a) {
		// TODO Auto-generated method stub
		String createQuery = "INSERT INTO address (address, city, state, zip_code) VALUES( ? , ? , ? , ? )";
		try {
			PreparedStatement createStmt = conn.prepareStatement(createQuery);
			createStmt.setString(1, a.getStreetAddr()); 
			createStmt.setString(2, a.getCity()); 
			createStmt.setString(3, a.getState()); 
			createStmt.setString(4, a.getZipCode()); 
			createStmt.execute();
			
			
			String selectQuery = "SELECT address_id FROM address WHERE address = ? AND city = ? AND state = ? AND zip_code = ?";
			PreparedStatement queryStmt = conn.prepareStatement(selectQuery);
			queryStmt.setString(1, a.getStreetAddr());
			queryStmt.setString(2, a.getCity());
			queryStmt.setString(3, a.getState());
			queryStmt.setString(4, a.getZipCode());
			ResultSet rs = queryStmt.executeQuery();
			rs.next();
			return rs.getInt("address_id");
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}

	}
	@Override
	public boolean deleteAddress(int addressId) {
		// TODO Auto-generated method stub
		String deleteQuery = " DELETE FROM address " + idMatchClause;
		try {
			PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery);
			deleteStmt.setInt(1, addressId);
			return !deleteStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
	@Override
	public boolean updateAddress(Address a) {
		// TODO Auto-generated method stub
		String updateQuery = " UPDATE address SET address = ? , city = ? , state = ? , zip_code = ? " + idMatchClause;
		try {
			PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
			updateStmt.setString(1, a.getStreetAddr()); 
			updateStmt.setString(2, a.getCity()); 
			updateStmt.setString(3, a.getState()); 
			updateStmt.setString(4, a.getZipCode()); 
			return updateStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
	
}