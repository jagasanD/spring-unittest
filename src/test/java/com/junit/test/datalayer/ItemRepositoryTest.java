package com.junit.test.datalayer;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.junit.model.Item;
import com.junit.repository.ItemRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ItemRepositoryTest {
	
	@Autowired
	ItemRepository itemRepository;
	
	@Test
	public void saveItem() {
		List<Item> items = new ArrayList<>();
		items.add(new Item(1,"item1",10,120));
		items.add(new Item(2,"item2",10,60));
		items.add(new Item(4,"item4",30,30));
		itemRepository.saveAll(items);
		
	}
	@Test
	public void findAll() {
		
		List<Item> items=itemRepository.findAll();
		items.forEach(item->{
			System.out.println(" id "+item.getId()+" name "+item.getName()+" price "+item.getPrice());
			
		});
		
	}

}
