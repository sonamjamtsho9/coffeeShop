package edu.mum.coffee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.mum.coffee.domain.Order;
import edu.mum.coffee.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping(path = "/create")
	public ResponseEntity<Order> createOrder(@RequestBody Order order) {
		orderService.save(order);
		return ResponseEntity.ok(order);
	}

	@GetMapping(path = "/list")
	public ResponseEntity<List<Order>> listAll() {
		List<Order> list = orderService.findAll();
		return ResponseEntity.ok(list);
	}
}
