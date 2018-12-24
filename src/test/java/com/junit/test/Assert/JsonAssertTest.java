package com.junit.test.Assert;

import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class JsonAssertTest {
	String actualResult="{\"id\":1,\"name\":\"omega\",\"price\":10,\"quantity\":120}";
	@Test
	public void testAssert() throws JSONException {
		String expected="{\"id\":1,\"name\":\"omega\",\"price\":10,\"quantity\":120}";
		JSONAssert.assertEquals(expected, actualResult,true);
		//removed some content form expected json 
		String expectedCh="{\"id\":1,\"name\":\"omega\"}";
		JSONAssert.assertEquals(expectedCh, actualResult,false);
	}
	@Test
	public void testAssertStrictTrue() throws JSONException {
		//removed some content form expected json 
		String expectedCh="{\"id\":1,\"name\":\"omega\"}";
		// strict true means actual expected result must be same
		JSONAssert.assertEquals(expectedCh, actualResult,true);	
	}
	
	@Test
	public void testAssertWithOutScapChar() throws JSONException {
		String actualResult="{id:1,name:omega,price:10,quantity:120}";
		String expectedCh="{id:1,name:omega}";
		JSONAssert.assertEquals(expectedCh, actualResult,false);	
	}

}
