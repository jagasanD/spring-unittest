package com.junit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.junit.model.Item;
import com.junit.service.ItemService;

@RestController
@RequestMapping("/api")
public class ItemController {

	@Autowired
	ItemService itemService;
	
	@GetMapping("/get-items")
	public List<Item> getItems() {
		return itemService.getAllItems();
		
	}
	
	@PostMapping("/save-item")
	public Boolean saveItem(@RequestBody Item item) {
		System.out.println("----------call save method------");
		Boolean value=itemService.saveItem(item);
		System.out.println("----------value------"+value);
		return value;
		
		
	}
}
