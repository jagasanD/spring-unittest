package com.junit.test.controller;



import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.junit.controller.HelloWorldController;

@RunWith(SpringRunner.class)
@WebMvcTest(HelloWorldController.class)
public class HelloControllerTest {
	//Spring bean 
	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void helloWorldBasicTest() throws Exception {
		RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/hello-world").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(reqBuilder)
				.andExpect(status().isOk())
				.andExpect(content().string("Hello world"))
				.andReturn();
		assertEquals("Hello world", result.getResponse().getContentAsString());
		
	}

}
