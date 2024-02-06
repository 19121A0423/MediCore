package com.order.service.impl;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.RegistrationBean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.order.bean.Address;
import com.order.controller.AddressController;
import com.order.entity.AddressEntity;
import com.order.exceptions.AddressNotFoundException;
import com.order.repository.AddressRepository;
import com.order.service.AddressService;

import lombok.extern.slf4j.Slf4j;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private RestTemplate restTemplate;

	private static Logger log = LoggerFactory.getLogger(AddressServiceImpl.class.getSimpleName());


	@Override
	public Address saveAddress(Address address) {
		log.info("AddressServiceImpl::SaveAddress::Started");
		if (address.getCity() == null || address.getStreetName() == null || address.getState() == null ||
				address.getPinCode() == null || address.getUserId() == null) {
			throw new IllegalArgumentException("Address properties cannot be null");
		}

		AddressEntity addressEntity = new AddressEntity();
		beanToEntity(address, addressEntity);
		addressRepository.save(addressEntity);
		entityToBean(address, addressEntity);
		log.info("AddressServiceImpl::SaveAddress::Ended");
		return address;

	}

	@Override
	public Address getAddressById(int id) throws AddressNotFoundException {
		log.info("AddressServiceImpl::SaveAddress::Started");
		AddressEntity addressEntity = addressRepository.findById(id)
				.orElseThrow(() -> new AddressNotFoundException("Address not found with ID: " + id));

		Address address = new Address();
		entityToBean(address, addressEntity);
		log.info("AddressServiceImpl::getAddressById::Ended");
		return address;
	}

	@Override
	public List<Address> getAllAddresses() throws AddressNotFoundException {
		log.info("AddressServiceImpl::SaveAddress::Started");
		List<AddressEntity> addressEntities = addressRepository.findAll();
		if(addressEntities.isEmpty()) {
			throw new AddressNotFoundException("No addresses found");
		}
		else {
			List<Address> addresses = new ArrayList<>();
			entitiesToBeans(addresses, addressEntities);
			log.info("AddressServiceImpl::getAllAddresses::Ended");
			return addresses;
		}

	}

	@Override
	public void updateAddress(int id, Address updatedAddress) throws AddressNotFoundException {
		log.info("AddressServiceImpl::updateAddress::Started");
		Optional<AddressEntity> optionalAddressEntity = addressRepository.findById(id);

		if (optionalAddressEntity.isPresent()) {
			AddressEntity addressEntity = optionalAddressEntity.get();
			addressEntity.setAddressId(id);
			beanToEntity(updatedAddress, addressEntity);
			addressRepository.save(addressEntity);
		} else {
			throw new AddressNotFoundException("Address not found with ID: " + id);
		}
		log.info("AddressServiceImpl::updateAddress::Ended");
	}

	@Override
	public void deactivateAddress(int id) throws AddressNotFoundException {
		log.info("AddressServiceImpl::deactivateAddress::Started");
		if (addressRepository.existsById(id)) {
			addressRepository.updateStatusById(id);
		} else {
			throw new AddressNotFoundException("Address not found with ID: " + id);
		}
		log.info("AddressServiceImpl::deactivateAddress::Ended");

	}

	@Override
	public void beanToEntity(Address address, AddressEntity addressEntity) {
		log.info("AddressServiceImpl::beanToEntity::Started");
		addressEntity.setStreetName(address.getStreetName());
		addressEntity.setCity(address.getCity());
		addressEntity.setState(address.getState());
		addressEntity.setPinCode(address.getPinCode());
		addressEntity.setUserId(address.getUserId());
		addressEntity.setStatus("active");
		log.info("AddressServiceImpl::beanToEntity::Ended");

	}

	@Override
	public void entityToBean(Address address, AddressEntity addressEntity) {
		log.info("AddressServiceImpl::entityToBean::Started");
		address.setAddressId(addressEntity.getAddressId());
		address.setStreetName(addressEntity.getStreetName());
		address.setCity(addressEntity.getCity());
		address.setState(addressEntity.getState());
		address.setPinCode(addressEntity.getPinCode());
		address.setUserId(addressEntity.getUserId());
		address.setStatus(addressEntity.getStatus());
		log.info("AddressServiceImpl::entityToBean::Ended");
	}

	@Override
	public void entitiesToBeans(List<Address> addresses, List<AddressEntity> addressEntities) {
		log.info("AddressServiceImpl::entitiesToBeans::Started");
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
		log.info("AddressServiceImpl::entitiesToBeans::Ended");

	}

}
