package com.junit.test.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.junit.model.Item;
import com.junit.repository.ItemRepository;
import com.junit.serviceimpl.ItemServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceTest {
	
	@InjectMocks
	ItemServiceImpl itemServiceImpl;
	
	@Mock
	ItemRepository itemRepository;
	
	@Test
	public void getItemsFromBusiness() {
		List<Item> items = new ArrayList<>();
		items.add(new Item(1,"item1",10,120));
		items.add(new Item(2,"item2",10,60));
		when(itemRepository.findAll()).thenReturn(items);
		
		List<Item>testItems = itemServiceImpl.getAllItems();
		
		System.out.println("---first Index value--"+testItems.get(0).getValue());
		assertEquals(1200, testItems.get(0).getValue());
		System.out.println("---second Index value--"+testItems.get(1).getValue());
		assertEquals(600, testItems.get(1).getValue());
		
	}
	

}
