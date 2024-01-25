package com.order.service.impl;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.RegistrationBean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.order.bean.Address;
import com.order.entity.AddressEntity;
import com.order.exceptions.AddressNotFoundException;
import com.order.repository.AddressRepository;
import com.order.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public void save(Address address) {
		if (address == null) {
			throw new IllegalArgumentException("Address cannot be null");
		}

		AddressEntity addressEntity = new AddressEntity();
		beanToEntity(address, addressEntity);
		addressRepository.save(addressEntity);

	}

	@Override
	public Address findById(int id) throws AddressNotFoundException {
		AddressEntity addressEntity = addressRepository.findById(id)
				.orElseThrow(() -> new AddressNotFoundException("Address not found with ID: " + id));

		Address address = new Address();
		entityToBean(address, addressEntity);
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
	public void update(int id, Address updatedAddress) throws AddressNotFoundException {
		Optional<AddressEntity> optionalAddressEntity = addressRepository.findById(id);

		if (optionalAddressEntity.isPresent()) {
			AddressEntity addressEntity = optionalAddressEntity.get();
			addressEntity.setAddressId(id);
			beanToEntity(updatedAddress, addressEntity);
			addressRepository.save(addressEntity);
		} else {
			throw new AddressNotFoundException("Address not found with ID: " + id);
		}
	}

	@Override
	public void deactivateAddress(int id) throws AddressNotFoundException {
		if (addressRepository.existsById(id)) {
			addressRepository.updateStatusById(id);
		} else {
			throw new AddressNotFoundException("Address not found with ID: " + id);
		}

	}

	public void beanToEntity(Address address, AddressEntity addressEntity) {
		addressEntity.setStreetName(address.getStreetName());
		addressEntity.setCity(address.getCity());
		addressEntity.setState(address.getState());
		addressEntity.setPinCode(address.getPinCode());
		addressEntity.setUserId(address.getUserId());
		addressEntity.setStatus("active");

	}

	public void entityToBean(Address address, AddressEntity addressEntity) {
		address.setAddressId(addressEntity.getAddressId());
		address.setStreetName(addressEntity.getStreetName());
		address.setCity(addressEntity.getCity());
		address.setState(addressEntity.getState());
		address.setPinCode(addressEntity.getPinCode());
		address.setUserId(addressEntity.getUserId());
		address.setStatus(addressEntity.getStatus());

	}

	public void entitiesToBeans(List<Address> addresses, List<AddressEntity> addressEntities) {

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
