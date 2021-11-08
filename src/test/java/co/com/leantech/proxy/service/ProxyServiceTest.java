package co.com.leantech.proxy.service;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.com.leantech.proxy.client.ProxyClient;
import co.com.leantech.proxy.model.HttpMessage;
import co.com.leantech.proxy.testobjects.HttpObtectsTest;
import co.com.leantech.proxy.testobjects.ProxyClientObjectTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;

@SpringBootTest
public class ProxyServiceTest {

	ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	private ProxyService proxyService;
	
	@Test
	public void validClientResponse() throws IOException, InterruptedException {
		ProxyClient proxyClient = Mockito.mock(ProxyClient.class);
	    Mockito.when(proxyClient.peek(ProxyClientObjectTest.getValidRequest()))
	    	.thenReturn(ProxyClientObjectTest.getValidResponse());    
	    ResponseEntity<String> response = proxyService.redirect(
	    		HttpObtectsTest.VALID_BASE_TARGET,
				HttpObtectsTest.getValidBodyObject(objectMapper),
				new MockHttpServletRequest("GET", HttpObtectsTest.VALID_COMPLETE_URL));
	    assertEquals(302, response.getStatusCodeValue());
	    assertThat(0 < response.getHeaders().size());
	 }
	
	@Test
	public void redirectClientResponse() throws IOException, InterruptedException {
		ProxyClient proxyClient = Mockito.mock(ProxyClient.class);
	    Mockito.when(proxyClient.peek(ProxyClientObjectTest.getValidRequest()))
	    	.thenReturn(ProxyClientObjectTest.getValidResponse());    
	    ResponseEntity<String> response = proxyService.redirect(
	    		HttpObtectsTest.VALID_BASE_TARGET,
				HttpObtectsTest.getValidBodyObject(objectMapper),
				new MockHttpServletRequest("GET", HttpObtectsTest.VALID_COMPLETE_URL));
	    assertEquals(302, response.getStatusCodeValue());
	    assertThat(0 < response.getHeaders().size());
	 }
	
	@Test
	public void errorClientResponse() throws IOException, InterruptedException {
		ProxyClient proxyClient = Mockito.mock(ProxyClient.class);
	    Mockito.when(proxyClient.peek(ArgumentMatchers.any(HttpMessage.class)))
	    	.thenReturn(ProxyClientObjectTest.getInvalidResponse());    	 
	    Exception exception = null;
	    try {
	    	ResponseEntity<String> response = proxyService.redirect(
		    		HttpObtectsTest.VALID_BASE_TARGET,
					HttpObtectsTest.getValidBodyObject(objectMapper),
					new MockHttpServletRequest("GET", HttpObtectsTest.VALID_COMPLETE_URL));
	    } catch (Exception t) {
	        exception = t;
	    }
	    assertThat(exception!=null);
	 }
	
}
