package edu.mum.coffee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.mum.coffee.domain.Product;
import edu.mum.coffee.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping(path = "/saveproduct")
	public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
		productService.save(product);
		return ResponseEntity.ok(product);
	}

	@GetMapping(value = "/list")
	@ResponseBody
	public List<Product> getProducts() {
		return productService.getAllProduct();
	}

	@PutMapping(path = "/udpate/{productID}")
	public ResponseEntity<Product> update(@RequestBody Product product, @PathVariable int productID) {
		if (product.getId() == productID) {
			productService.save(product);
			return ResponseEntity.ok(product);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping(path = "/delete/{productID}")
	public ResponseEntity<Product> delete(@RequestBody Product product, @PathVariable int productID) {
		if (product.getId() == productID) {
			productService.delete(product);
			return ResponseEntity.ok(product);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
