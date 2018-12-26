package com.junit.test.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.junit.model.Item;
import com.junit.repository.ItemRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class IntegrationTesting {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	ItemRepository repository;
	
	  ObjectMapper objectMapper = new ObjectMapper();
	
	@Test
	public void integrationTest() throws Exception {
		
		 RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/get-items").
				   contentType(MediaType.APPLICATION_JSON);
			MvcResult result = mockMvc.perform(reqBuilder)
					.andExpect(status().isOk())
					.andReturn();
	
		System.out.println(result.getResponse().getContentAsString());
		JSONAssert.assertEquals("{id:1,name:omega,price:10,quantity:120}",result.getResponse().getContentAsString(), false);
	}
	
	
	@Test
	public void testPostMethod() throws JsonProcessingException, Exception {
		Item item = new Item(110,"test",20,20);
		  String inputJson = objectMapper.writeValueAsString(item);
		   RequestBuilder reqBuilder = MockMvcRequestBuilders.post("/api/save-item").
				   contentType(MediaType.APPLICATION_JSON).content(inputJson);
			MvcResult result = mockMvc.perform(reqBuilder)
					.andExpect(status().isOk())
					.andReturn();
			
		
		
	}
	
	@Test
	public void getMethodTest() throws Exception {
		 RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/get-items").
				   contentType(MediaType.APPLICATION_JSON);
			MvcResult result = mockMvc.perform(reqBuilder)
					.andExpect(status().isOk())
					.andReturn();
			String resp=result.getResponse().getContentAsString();
			Item item = objectMapper.convertValue(resp, Item.class);
		
	}
	

}
