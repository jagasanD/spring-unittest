package com.junit.service;

import java.util.List;

import com.junit.model.Item;

public interface ItemService {

	List<Item> getAllItems();

	Boolean saveItem(Item item);

	
	
}
