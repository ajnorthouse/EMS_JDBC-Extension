package com.cognixia.jump.jdbc.ems_extension.team2.interfaces;

import java.util.List;

import com.cognixia.jump.jdbc.ems_extension.team2.model.Address;

public interface AddressDAO {
	public List<Address> getAllAddresses();
	public Address getAddress(int id);
	public boolean createAddress(Address a);
	public boolean deleteAddress(Address a);
	public boolean updateAddress(Address a);
}
