package com.junit.test.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.junit.controller.ItemController;
import com.junit.model.Item;
import com.junit.service.ItemService;

@RunWith(SpringRunner.class)
@WebMvcTest(ItemController.class)
public class TestPostMethod {
	 @Autowired
	 MockMvc mvc;
	@MockBean
	ItemService itemService;
	
	@Test
	public void testPostMethod() throws JsonProcessingException, Exception {
		
		Item item = new Item(110,"test",20,20);
		
		//when(itemService.saveItem(item)).thenReturn(Boolean.TRUE);
		
		  String inputJson = mapToJson(item);
		   RequestBuilder reqBuilder = MockMvcRequestBuilders.post("/api/save-item").
				   contentType(MediaType.APPLICATION_JSON).content(inputJson);
			MvcResult result = mvc.perform(reqBuilder)
					.andExpect(status().isOk())
					.andReturn();
			
		
		
	}
	//Convert Object to Json
	 protected String mapToJson(Object obj) throws JsonProcessingException {
	      ObjectMapper objectMapper = new ObjectMapper();
	      return objectMapper.writeValueAsString(obj);
	   }

}
