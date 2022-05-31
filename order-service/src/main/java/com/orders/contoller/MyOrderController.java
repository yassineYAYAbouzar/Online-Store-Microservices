package com.orders.contoller;


import com.orders.entities.OrderEntitie;
import com.orders.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@RestController
@RequestMapping("/order")
public class MyOrderController {
    private final OrderService orderService;
    @GetMapping("/admin/allOrders")
    public List<OrderEntitie> getAllOrders(){
        List<OrderEntitie> orderEntities =orderService.getAllOrder();
        return orderEntities;
    }
    @CircuitBreaker(name = "one" , fallbackMethod = "serviceAFallback")
    @PostMapping("/user/createOrder")
    public ResponseEntity<?> createOrder(@RequestBody @Valid OrderEntitie orderEntitie) throws Exception {
        orderService.createOrder(orderEntitie);
        return new ResponseEntity<OrderEntitie>(orderEntitie, HttpStatus.CREATED) ;
    }
    public ResponseEntity<?> serviceAFallback(Exception e) {
        return  ResponseEntity.badRequest().body("no product yeat !") ;
    }

    @DeleteMapping(path = "/user/deleteOrder/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable String orderId ) {
        Optional<OrderEntitie> oneOrder = orderService.getOneOrder(orderId);
        if(oneOrder.isPresent()){
            orderService.deleteOrder(oneOrder.get());
            return  new ResponseEntity<OrderEntitie>(  HttpStatus.NO_CONTENT) ;
        }
        return ResponseEntity.badRequest().body(new NoOrder("not Order Yeat !") );
    }

    @GetMapping(path = "/user/showOrder/{orderId}")
    public ResponseEntity<?> getOrder(@PathVariable String orderId )  {
        Optional<OrderEntitie> oneOrder = orderService.getOneOrder(orderId);
        if(oneOrder.isPresent()){
            return  new ResponseEntity<OrderEntitie>( oneOrder.get() , HttpStatus.OK) ;
        }
        return ResponseEntity.badRequest().body(new NoOrder("not Order Yeat !") );

    }
}


record NoOrder(String message) {
}