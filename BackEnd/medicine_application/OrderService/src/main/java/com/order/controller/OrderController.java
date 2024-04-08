package com.order.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.bean.OrderBean;
import com.order.exceptions.AddressNotFoundException;
import com.order.exceptions.OrderNotFoundException;
import com.order.service.OrderService;

@RestController
@RequestMapping("/order")
@CrossOrigin("*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    private static Logger log = LoggerFactory.getLogger(OrderController.class.getSimpleName());

    /**
     * Saves an order.
     * 
     * @param order The order to be saved.
     * @return The saved order.
     */
    @PostMapping("/save")
    public ResponseEntity<OrderBean> saveOrder(@RequestBody OrderBean order) {
        log.info("OrderController::saveOrder::Started");
        try {
            order = orderService.placeOrder(order);
            log.info("OrderController::saveOrder::Ended");
            return new ResponseEntity<OrderBean>(order, HttpStatus.CREATED);
        } catch (IllegalArgumentException | AddressNotFoundException e) {
            log.error("OrderController::saveOrder::" + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Retrieves all orders.
     * 
     * @return A list of all orders.
     */
    @GetMapping("/get/all")
    public ResponseEntity<List<OrderBean>> getOrders() {
        log.info("OrderController::getOrders::Started");
        List<OrderBean> orders;
        try {
            orders = orderService.getAllOrders();
            log.info("OrderController::getOrders::Ended");
            return new ResponseEntity<List<OrderBean>>(orders, HttpStatus.OK);
        } catch (OrderNotFoundException e) {
            log.error("OrderController::getOrders::" + e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
