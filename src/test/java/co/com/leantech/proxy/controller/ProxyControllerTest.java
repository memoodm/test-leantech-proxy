package co.com.leantech.proxy.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.com.leantech.proxy.service.ProxyService;
import co.com.leantech.proxy.testobjects.HttpObtectsTest;

@SpringBootTest
@AutoConfigureMockMvc
public class ProxyControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private ProxyService service;
	
	@Test
	public void endPointIsOpen() throws Exception {		
		when(service.redirect(
				HttpObtectsTest.VALID_BASE_TARGET,
				HttpObtectsTest.getValidBodyObject(objectMapper),
				new MockHttpServletRequest("GET", HttpObtectsTest.VALID_COMPLETE_URL)))
			.thenReturn(HttpObtectsTest.validResponse());
		mockMvc.perform(get(HttpObtectsTest.VALID_COMPLETE_URL)
				.content(objectMapper.writeValueAsString(HttpObtectsTest.getValidBodyObject(objectMapper)))
			    .contentType("application/json"))
				.andExpect(status().isOk())
			    .andReturn();
	}
	
	@Test
	public void pathTargetValueIsMandatory() throws Exception {		
		when(service.redirect(
				"",
				HttpObtectsTest.getValidBodyObject(objectMapper),
				new MockHttpServletRequest("GET", HttpObtectsTest.VALID_COMPLETE_URL)))
			.thenReturn(HttpObtectsTest.validResponse());
		mockMvc.perform(get(HttpObtectsTest.REDIRECT_ENDPOINT)
				.content(objectMapper.writeValueAsString(HttpObtectsTest.getValidBodyObject(objectMapper)))
			    .contentType("application/json"))
				.andExpect(status().is4xxClientError())
			    .andReturn();
	}
	
	@Test
	public void bodyIsOptional() throws Exception {		
		when(service.redirect(
				HttpObtectsTest.VALID_BASE_TARGET,
				HttpObtectsTest.getValidBodyObject(null),
				new MockHttpServletRequest("GET", HttpObtectsTest.VALID_COMPLETE_URL)))
			.thenReturn(HttpObtectsTest.validResponse());
		mockMvc.perform(get(HttpObtectsTest.VALID_COMPLETE_URL)
				.content(objectMapper.writeValueAsString(HttpObtectsTest.getValidBodyObject(objectMapper)))
			    .contentType("application/json"))
				.andExpect(status().isOk())
			    .andReturn();
	}
	
	@Test
	public void allowPostCall() throws Exception {		
		when(service.redirect(
				HttpObtectsTest.VALID_BASE_TARGET,
				HttpObtectsTest.getValidBodyObject(null),
				new MockHttpServletRequest("POST", HttpObtectsTest.VALID_COMPLETE_URL)))
			.thenReturn(HttpObtectsTest.validResponse());
		mockMvc.perform(get(HttpObtectsTest.VALID_COMPLETE_URL)
				.content(objectMapper.writeValueAsString(HttpObtectsTest.getValidBodyObject(objectMapper)))
			    .contentType("application/json"))
				.andExpect(status().isOk())
			    .andReturn();
	}
	
	@Test
	public void allowPutCall() throws Exception {		
		when(service.redirect(
				HttpObtectsTest.VALID_BASE_TARGET,
				HttpObtectsTest.getValidBodyObject(null),
				new MockHttpServletRequest("PUT", HttpObtectsTest.VALID_COMPLETE_URL)))
			.thenReturn(HttpObtectsTest.validResponse());
		mockMvc.perform(get(HttpObtectsTest.VALID_COMPLETE_URL)
				.content(objectMapper.writeValueAsString(HttpObtectsTest.getValidBodyObject(objectMapper)))
			    .contentType("application/json"))
				.andExpect(status().isOk())
			    .andReturn();
	}
	
	@Test
	public void allowDeleteCall() throws Exception {		
		when(service.redirect(
				HttpObtectsTest.VALID_BASE_TARGET,
				HttpObtectsTest.getValidBodyObject(null),
				new MockHttpServletRequest("DELETE", HttpObtectsTest.VALID_COMPLETE_URL)))
			.thenReturn(HttpObtectsTest.validResponse());
		mockMvc.perform(get(HttpObtectsTest.VALID_COMPLETE_URL)
				.content(objectMapper.writeValueAsString(HttpObtectsTest.getValidBodyObject(objectMapper)))
			    .contentType("application/json"))
				.andExpect(status().isOk())
			    .andReturn();
	}
	
	@Test
	public void allowPathCall() throws Exception {		
		when(service.redirect(
				HttpObtectsTest.VALID_BASE_TARGET,
				HttpObtectsTest.getValidBodyObject(null),
				new MockHttpServletRequest("PATCH", HttpObtectsTest.VALID_COMPLETE_URL)))
			.thenReturn(HttpObtectsTest.validResponse());
		mockMvc.perform(get(HttpObtectsTest.VALID_COMPLETE_URL)
				.content(objectMapper.writeValueAsString(HttpObtectsTest.getValidBodyObject(objectMapper)))
			    .contentType("application/json"))
				.andExpect(status().isOk())
			    .andReturn();
	}
	
	@Test
	public void allowOptionsCall() throws Exception {		
		when(service.redirect(
				HttpObtectsTest.VALID_BASE_TARGET,
				HttpObtectsTest.getValidBodyObject(null),
				new MockHttpServletRequest("OPTIONS", HttpObtectsTest.VALID_COMPLETE_URL)))
			.thenReturn(HttpObtectsTest.validResponse());
		mockMvc.perform(get(HttpObtectsTest.VALID_COMPLETE_URL)
				.content(objectMapper.writeValueAsString(HttpObtectsTest.getValidBodyObject(objectMapper)))
			    .contentType("application/json"))
				.andExpect(status().isOk())
			    .andReturn();
	}
	
	@Test
	public void allowHeadCall() throws Exception {		
		when(service.redirect(
				HttpObtectsTest.VALID_BASE_TARGET,
				HttpObtectsTest.getValidBodyObject(null),
				new MockHttpServletRequest("HEAD", HttpObtectsTest.VALID_COMPLETE_URL)))
			.thenReturn(HttpObtectsTest.validResponse());
		mockMvc.perform(get(HttpObtectsTest.VALID_COMPLETE_URL)
				.content(objectMapper.writeValueAsString(HttpObtectsTest.getValidBodyObject(objectMapper)))
			    .contentType("application/json"))
				.andExpect(status().isOk())
			    .andReturn();
	}

}
