package com.junit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.junit.bean.ItemBean;
import com.junit.service.ItemBeanService;

@RestController
public class ItemBeanController {

	@Autowired
	ItemBeanService itemSerive;
	
	@GetMapping("/get-items")
	public ItemBean getItem() {
		return new ItemBean(1,"omega",10,120);
	}
	
	@GetMapping("/item-from-bussiness-service")
	public ItemBean getItemFromService() {
		return itemSerive.getItem();
	}
	
}
