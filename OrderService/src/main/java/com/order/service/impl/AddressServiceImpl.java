package com.order.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.bean.Address;
import com.order.entity.AddressEntity;
import com.order.exceptions.AddressNotFoundException;
import com.order.repository.AddressRepository;
import com.order.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService{
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Override
	public void save(Address address) {
		AddressEntity addressEntity = new AddressEntity();
		beanToEntity(address, addressEntity);
		addressRepository.save(addressEntity);
		
	}
	
	@Override
	public Address findById(int id) {
		Optional<AddressEntity> addressEntity = addressRepository.findById(id);
		Address address = new Address();
		entityToBean(address, addressEntity.get());
		return address;
	}
	
	@Override
	public List<Address> findAll() {
		List<AddressEntity> addressEntities = addressRepository.findAll();
		List<Address> addresses = new ArrayList<>();
		entitiesToBeans(addresses, addressEntities);
		return addresses;
		
	}
	
	@Override
	public void delete(int id) throws AddressNotFoundException {
		if(addressRepository.existsById(id)) {
			addressRepository.updateStatusById(id);
		}else {
			throw new AddressNotFoundException();
		}
		
	}
	
	public void beanToEntity(Address address , AddressEntity addressEntity) {
		addressEntity.setStreetName(address.getStreetName());
		addressEntity.setCity(address.getCity());
		addressEntity.setState(address.getState());
		addressEntity.setPinCode(address.getPinCode());
		addressEntity.setUserId(address.getUserId());
		addressEntity.setStatus("active");
		
	}
	
	public void entityToBean(Address address , AddressEntity addressEntity) {
		address.setAddressId(addressEntity.getAddressId());
		address.setStreetName(addressEntity.getStreetName());
		address.setCity(addressEntity.getCity());
		address.setState(addressEntity.getState());
		address.setPinCode(addressEntity.getPinCode());
		address.setUserId(addressEntity.getUserId());
		address.setStatus(addressEntity.getStatus());
		
	}
	
	public void entitiesToBeans(List<Address> addresses , List<AddressEntity> addressEntities) {
		
		addressEntities.stream().forEach(addressEntity -> {
			Address address = new Address();
			address.setAddressId(addressEntity.getAddressId());
			address.setStreetName(addressEntity.getStreetName());
			address.setCity(addressEntity.getCity());
			address.setState(addressEntity.getState());
			address.setPinCode(addressEntity.getPinCode());
			address.setUserId(addressEntity.getUserId());
			address.setStatus(addressEntity.getStatus());
			addresses.add(address);
		});
		
	}

}
