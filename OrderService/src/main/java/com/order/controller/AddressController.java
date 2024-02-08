package com.order.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.bean.Address;
import com.order.bean.UserBean;
import com.order.exceptions.AddressNotFoundException;
import com.order.exceptions.UserNotFoundException;
import com.order.service.AddressService;
import com.order.service.UserService;

@RestController
@RequestMapping("/address")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private UserService userService;
	
	private static Logger log = LoggerFactory.getLogger(AddressController.class.getSimpleName());
	
	@PostMapping("/save")
	public ResponseEntity<Address> saveAddress(@RequestBody Address address){
		log.info("AddressController::SaveAddress::Started");
		log.info("Address : "+address);
		try {
			addressService.saveAddress(address);
			log.info("AddressController::SaveAddress::Ended");
			return new ResponseEntity<Address>(address, HttpStatus.CREATED);
		}
		catch(IllegalArgumentException e) {
			log.error("AddressController::SaveAddress::"+e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Address> updateAddress(@RequestBody Address address, @PathVariable(value = "id") int id) {
		log.info("AddressController::updateAddress::Started");
		log.info("AddressId : "+id +"Address : "+address);
		try {
	        addressService.updateAddress(id, address);
	        log.info("AddressController::updateAddress::Ended");
	        return new ResponseEntity<>(address, HttpStatus.OK);
	    } catch (AddressNotFoundException e) {
	    	log.error("AddressController::updateAddress::"+e.getMessage());
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Address> getAddressById(@PathVariable(value = "id") int id) {
		log.info("AddressController::getAddressById::Started");
		log.info("AddressId : "+id);
	    try {
	        Address address = addressService.getAddressById(id);
	        log.info("AddressController::getAddressById::Ended");
	        return new ResponseEntity<>(address, HttpStatus.OK);
	    } catch (AddressNotFoundException e) {
	    	log.error("AddressController::getAddressById::"+e.getMessage());
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

	@GetMapping("/get/all")
	public ResponseEntity<List<Address>> getAddresses() {
		log.info("AddressController::getAddresses::Started");
	    List<Address> addresses;
		try {
			addresses = addressService.getAllAddresses();
			log.info("AddressController::getAddresses::Ended");
			return new ResponseEntity<>(addresses, HttpStatus.OK);	
		} catch (AddressNotFoundException e) {
			log.error("AddressController::getAddresses::"+e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deactivateAddressById(@PathVariable(value="id") int id) {
		log.info("AddressController::SaveAddress::Started");
		log.info("AddressId : "+id);
		try {
	        addressService.deactivateAddress(id);
	        log.info("AddressController::deactivateAddressById::Ended");
	        return new ResponseEntity<>("Successfully deleted order of id: " + id, HttpStatus.OK);
	    } catch (AddressNotFoundException e) {
	    	log.error("AddressController::deactivateAddressById::"+e.getMessage());
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	    }
	}
	
	@GetMapping("getUser/{id}")
	public ResponseEntity<UserBean> getUser(@PathVariable(value="id") int id) {
		log.info("AddressController::SaveAddress::Started");
		log.info("UserId : "+id);
	    UserBean user;
		try {
			user = userService.getUserBean(id);
			log.info("AddressController::getUser::Ended");
			return new ResponseEntity<>(user, HttpStatus.FOUND);
		} catch (UserNotFoundException e) {
			log.error("AddressController::getUser::"+e.getMessage());
			return new ResponseEntity<>( HttpStatus.NOT_FOUND);
		}	
	}
	
	@GetMapping("/getByUserId/{id}")
	public ResponseEntity<List<Address>> getAddressByUserId(@PathVariable(value = "id") int id) {
		log.info("AddressController::getAddressByUserId::Started");
		log.info("UserId : "+id);
	    try {
	        List<Address> address = addressService.getAddressByUserId(id);
	        log.info("AddressController::getAddressByUserId::Ended");
	        return new ResponseEntity<>(address, HttpStatus.OK);
	    } catch (AddressNotFoundException e) {
	    	log.error("AddressController::getAddressByUserId::"+e.getMessage());
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

}
