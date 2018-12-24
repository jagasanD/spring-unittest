package com.junit.test.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.junit.controller.ItemController;
import com.junit.model.Item;
import com.junit.serviceimpl.ItemServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(ItemController.class)
public class ItemControllerTest {

	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	ItemServiceImpl itemServiceImpl;
	
	@Test
	public void getItemFromDB() throws Exception {
		List<Item> items = new ArrayList<>();
		items.add(new Item(1,"omega",10,120));
		when(itemServiceImpl.getAllItems()).thenReturn(items);
		
		RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/api/get-items").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(reqBuilder).andExpect(status().isOk()).andReturn();
		JSONAssert.assertEquals("[{id:1,name:omega,price:10,quantity:120}]", result.getResponse().getContentAsString(),false);
		
	}
	
}
