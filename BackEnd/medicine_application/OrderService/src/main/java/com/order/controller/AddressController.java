package com.order.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.bean.AddressBean;
import com.order.bean.UserBean;
import com.order.exceptions.AddressNotFoundException;
import com.order.exceptions.UserNotFoundException;
import com.order.service.AddressService;
import com.order.service.UserService;

@RestController
@RequestMapping("/address")
@CrossOrigin("*")
public class AddressController {
    
    @Autowired
    private AddressService addressService;
    
    @Autowired
    private UserService userService;
    
    private static Logger log = LoggerFactory.getLogger(AddressController.class.getSimpleName());
    
    /**
     * Saves an address.
     * 
     * @param address The address to be saved.
     * @return The saved address.
     */
    @PostMapping("/save")
    public ResponseEntity<AddressBean> saveAddress(@RequestBody AddressBean address) {
        log.info("AddressController::saveAddress::Started ", address);
        try {
            address = addressService.saveAddress(address);
            log.info("AddressController::saveAddress::Ended");
            return new ResponseEntity<AddressBean>(address, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            log.error("AddressController::saveAddress:: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        
    }
    
    /**
     * Updates an address.
     * 
     * @param address The updated address.
     * @param id The ID of the address to be updated.
     * @return The updated address.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<AddressBean> updateAddress(@RequestBody AddressBean address, @PathVariable(value = "id") int id) {
        log.info("AddressController::updateAddress::Started AddressId : " + id + "Address : " + address);
        try {
            address = addressService.updateAddress(id, address);
            log.info("AddressController::updateAddress::Ended");
            return new ResponseEntity<>(address, HttpStatus.OK);
        } catch (AddressNotFoundException e) {
            log.error("AddressController::updateAddress::" + e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    /**
     * Retrieves an address by ID.
     * 
     * @param id The ID of the address to retrieve.
     * @return The address with the specified ID.
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<AddressBean> getAddressById(@PathVariable(value = "id") int id) {
        log.info("AddressController::getAddressById::Started AddressId ", id);
        try {
            AddressBean address = addressService.getAddressById(id);
            log.info("AddressController::getAddressById::Ended");
            return new ResponseEntity<>(address, HttpStatus.OK);
        } catch (AddressNotFoundException e) {
            log.error("AddressController::getAddressById::" + e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    /**
     * Retrieves a user by ID.
     * 
     * @param id The ID of the user to retrieve.
     * @return The user with the specified ID.
     */
    @GetMapping("getuser/{id}")
    public ResponseEntity<UserBean> getUser(@PathVariable(value = "id") int id) {
        log.info("AddressController::getUser::Started");
        log.info("UserId : " + id);
        UserBean user;
        try {
            user = userService.getUserBean(id);
            log.info("AddressController::getUser::Ended");
            return new ResponseEntity<>(user, HttpStatus.FOUND);
        } catch (UserNotFoundException e) {
            log.error("AddressController::getUser::" + e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }    
    }
    
    /**
     * Retrieves addresses by user ID.
     * 
     * @param id The ID of the user whose addresses are to be retrieved.
     * @return A list of addresses associated with the specified user ID.
     */
    @GetMapping("/getbyuserid/{id}")
    public ResponseEntity<List<AddressBean>> getAddressByUserId(@PathVariable(value = "id") int id) {
        log.info("AddressController::getAddressByUserId::Started");
        log.info("UserId : " + id);
        try {
            List<AddressBean> address = addressService.getAddressByUserId(id);
            log.info("AddressController::getAddressByUserId::Ended");
            return new ResponseEntity<>(address, HttpStatus.OK);
        } catch (AddressNotFoundException e) {
            log.error("AddressController::getAddressByUserId::" + e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
