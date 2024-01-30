package com.order.service;

import java.util.List;

import com.order.bean.Address;
import com.order.bean.UserBean;
import com.order.exceptions.AddressNotFoundException;

public interface AddressService {
	
	void save(Address address);

	Address findById(int id) throws AddressNotFoundException;

	List<Address> findAll();

	void deactivateAddress(int id) throws AddressNotFoundException;

	void update(int id, Address updatedAddress) throws AddressNotFoundException;


}
