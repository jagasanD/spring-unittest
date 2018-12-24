package com.junit.test.controller;

import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.junit.model.Item;
import com.junit.repository.ItemRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class ItemControllerIntegrationTest {

	@Autowired	
	TestRestTemplate restTemplate;
	
	@MockBean
	ItemRepository repository;
	
	@Test
	public void integrationTest() throws JSONException {
		String response= restTemplate.getForObject("/get-items", String.class);
		System.out.println(response);
		JSONAssert.assertEquals("{id:1,name:omega,price:10,quantity:120}", response, false);
	}
	
	@Test
	public void mockBeanTest() throws JSONException {
		when(repository.findAll()).thenReturn(Arrays.asList(
				new Item(1,"abc",10,20),
				new Item(2,"jk",10,10),
				new Item(3,"mrq",30,30))
				);
		
		String response= restTemplate.getForObject("/get-items", String.class);
		System.out.println(response);
		String excpectedRes="{id:1,name:abc,price:10,quantity:20},{id:2,name:jk,price:10,quantity:10},{id:3,name:mrq,price:30,quantity:30}";
		JSONAssert.assertEquals(excpectedRes, response, false);
		
		
		
		
	}
}
