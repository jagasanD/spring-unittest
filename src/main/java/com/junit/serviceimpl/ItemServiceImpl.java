package com.junit.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.junit.model.Item;
import com.junit.repository.ItemRepository;
import com.junit.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService{

	@Autowired
	ItemRepository itemRepository;
	
	@Override
	public List<Item> getAllItems() {
		List<Item> items = itemRepository.findAll();
		for(Item item:items) {
			System.out.println("--call service---"+item.getName()+"  "+item.getPrice()*item.getQuantity());
			item.setValue(item.getPrice()*item.getQuantity());
		}
		return items;
	}

	@Override
	public Boolean saveItem(Item item) {
		try {
			System.out.println("---inside service------");
			itemRepository.save(item);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	

}
