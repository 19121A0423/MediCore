package com.order.scheduler;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.order.bean.Orders;
import com.order.entity.OrderEntity;
import com.order.exceptions.AddressNotFoundException;
import com.order.exceptions.OrderNotFoundException;
import com.order.service.OrderService;

@Component
public class OrderScheduler {
	
	@Autowired
    private OrderService orderService;

    @Scheduled(fixedDelay = 600000, initialDelay = 600000) // Check every 10 minutes, with an initial delay of 10 minutes
    public void checkProducts() throws OrderNotFoundException, AddressNotFoundException {
        List<Orders> orders = orderService.getAllOrders();
        LocalDateTime currentTime = LocalDateTime.now();

        for (Orders order : orders) {
            if ("Ordered".equals(order.getStatus()) && currentTime.isAfter(order.getOrderedDate().plusMinutes(15))) {
            	order.setStatus("Delivered");
                // Update the product status in the database
                orderService.placeOrder(order);
            }
        }
    }

}
