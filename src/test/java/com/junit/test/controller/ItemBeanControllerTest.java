package com.junit.test.controller;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.junit.bean.ItemBean;
import com.junit.controller.ItemBeanController;
import com.junit.serviceimpl.ItemBeanServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(ItemBeanController.class)
public class ItemBeanControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	ItemBeanServiceImpl itemServiceImpl;
	
	@Test
	public void getItem() throws Exception {
		RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/get-items").accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(reqBuilder).andExpect(status().isOk()).andExpect(content().json("{\"id\":1,\"name\":\"omega\",\"price\":10,\"quantity\":120}"));
	}
	
	@Test
	public void getItemSimple() throws Exception {
		
		RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/get-items").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(reqBuilder).andExpect(status().isOk()).andReturn();
		assertEquals("{\"id\":1,\"name\":\"omega\",\"price\":10,\"quantity\":120}", result.getResponse().getContentAsString());
	}
	
	@Test
	public void testItemFromBusinessService() throws Exception {
		when(itemServiceImpl.getItem()).thenReturn(new ItemBean(1,"omega",10,120));
		
		RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/item-from-bussiness-service").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(reqBuilder).andExpect(status().isOk()).andReturn();
		JSONAssert.assertEquals("{id:1,name:omega,price:10,quantity:120}", result.getResponse().getContentAsString(),false);
	}
	
}
