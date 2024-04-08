package com.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admin.bean.CartBean;
import com.admin.exception.CartIdNotFoundException;
import com.admin.exception.CartListNotFoundException;
import com.admin.exception.UserIdNotFoundException;
import com.admin.service.CartService;

@RestController
@CrossOrigin("*")
@RequestMapping("/cartcontroller")
public class CartController {
    
    private static Logger log = LoggerFactory.getLogger(CartController.class.getSimpleName());
    
    @Autowired
    private CartService service;
    
    /**
     * Saves a cart.
     * 
     * @param cart The cart to be saved.
     * @return The saved cart.
     * @throws UserIdNotFoundException If the user ID associated with the cart is not found.
     */
    @PostMapping("/cart/save")
    public ResponseEntity<CartBean> saveCart(@RequestBody CartBean cart) throws UserIdNotFoundException {      
        log.info("CartController saveCart Start => {} ", cart);
        try {
          cart = service.saveCart(cart);
        } catch (IllegalArgumentException e) {           
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("CartController End => {} ", cart);
        return  ResponseEntity.status(HttpStatus.OK).body(cart);
    }   
    
    /**
     * Retrieves a cart by user ID.
     * 
     * @param userId The ID of the user associated with the cart.
     * @return The cart associated with the specified user ID.
     * @throws CartIdNotFoundException If the cart ID is not found.
     * @throws UserIdNotFoundException If the user ID is not found.
     * @throws CartListNotFoundException If the cart list is not found.
     */
    @GetMapping("/cart/{userId}")
    public ResponseEntity<CartBean> getCartById(@PathVariable Integer userId) 
            throws CartIdNotFoundException, UserIdNotFoundException, CartListNotFoundException {
        log.info("CartController getCartById Start => {} ", userId);
        CartBean cart = null; 
        try {
            cart = service.getCartById(userId);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("CartController getCartById End => {} ", userId);
        return  ResponseEntity.status(HttpStatus.OK).body(cart);
    }
    
    /**
     * Deletes a product from the cart.
     * 
     * @param cartId The ID of the cart.
     * @param productId The ID of the product to be deleted from the cart.
     * @return The updated cart after deletion.
     * @throws CartIdNotFoundException If the cart ID is not found.
     */
    @DeleteMapping("/cart/delete/{cartId}/{productId}")
    public ResponseEntity<CartBean> delete(@PathVariable Integer cartId, @PathVariable Integer productId) 
            throws CartIdNotFoundException {
        
        log.info("Cart controller delete cart method start " + cartId + ", " + productId);
        try {
            CartBean cart = service.delete(cartId, productId);
            log.info("Cart controller delete product in cart method end try block {} " + cartId);
            return ResponseEntity.status(HttpStatus.OK).body(cart);
        } catch (IllegalArgumentException e) {
            log.info("Cart controller delete cart method end catch block {} " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
    }
    
    /**
     * Retrieves details of all carts.
     * 
     * @return A list of all carts.
     * @throws CartListNotFoundException If the cart list is not found.
     */
    @GetMapping("/cart/getall")
    public ResponseEntity<List<CartBean>> getCartDetails() throws CartListNotFoundException {
        log.info("Cart controller getCartDetails method start");
        List<CartBean> cartDetails = service.getCartDetails();
        log.info("Cart controller getCartDetails method End");
        return ResponseEntity.status(HttpStatus.OK).body(cartDetails);
    }

    /**
     * Updates a product in the cart.
     * 
     * @param cart The updated cart.
     * @param productId The ID of the product to be updated.
     * @return The updated cart.
     * @throws CartIdNotFoundException If the cart ID is not found.
     */
    @PutMapping("/cart/update/{productId}")
    public ResponseEntity<CartBean> update(@RequestBody CartBean cart, @PathVariable int productId) 
            throws CartIdNotFoundException {
        
        log.info("Cart controller update method {}  " + cart);
        cart = service.update(cart, productId);
        return ResponseEntity.status(HttpStatus.OK).body(cart);
    
    }
    
    /**
     * Updates the status of the cart.
     * 
     * @param cart The cart to update the status for.
     * @return The updated cart.
     * @throws CartIdNotFoundException If the cart ID is not found.
     */
    @PutMapping("/cart/updateStatus")
    public ResponseEntity<CartBean> updateCartStatus(@RequestBody CartBean cart) 
            throws CartIdNotFoundException {
        log.info("Cart Controller Update Cart Status Method Start");
        try {
            cart = service.updateCartStatus(cart);
            log.info("Cart Controller Update Cart Status Method End");
            return ResponseEntity.status(HttpStatus.OK).body(cart);
        } catch (IllegalArgumentException e) {
            log.info("Cart controller Update Cart Status Method End " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
