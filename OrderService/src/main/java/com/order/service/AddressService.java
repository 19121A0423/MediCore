package com.order.service;

import java.util.List;

import com.order.bean.Address;
import com.order.exceptions.AddressNotFoundException;

public interface AddressService {
	
	void saveAddress(Address address);

	Address getAddressById(int id) throws AddressNotFoundException;

	List<Address> getAllAddresses() throws AddressNotFoundException;

	void deactivateAddress(int id) throws AddressNotFoundException;

	void updateAddress(int id, Address updatedAddress) throws AddressNotFoundException;


}
