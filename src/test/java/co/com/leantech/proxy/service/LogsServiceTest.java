package co.com.leantech.proxy.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.com.leantech.proxy.client.ProxyClient;
import co.com.leantech.proxy.service.LogsService;
import co.com.leantech.proxy.service.ProxyService;
import co.com.leantech.proxy.testobjects.HttpObtectsTest;
import co.com.leantech.proxy.testobjects.ProxyClientObjectTest;

@SpringBootTest
public class LogsServiceTest {

	ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	private LogsService logsService;
		
	@Test
	public void validateRequestLogsPrinted() throws IOException, InterruptedException {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    logsService.generateRequestLogs(ProxyClientObjectTest.getValidRequest());
	    assertThat(outContent.toString().contains("REQUEST:"));
	    assertThat(outContent.toString().contains("Url(https://www.facebook.com)"));
	    assertThat(outContent.toString().contains("METHOD:"));
	    assertThat(outContent.toString().contains("BODY:"));
	    assertThat(outContent.toString().contains("HEADERS:"));
	 }
	
	@Test
	public void validateResponseLogsPrinted() throws IOException, InterruptedException {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    logsService.generateResponseLogs(ProxyClientObjectTest.getValidResponse());
	    assertThat(outContent.toString().contains("RESPONSE:"));
	    assertThat(outContent.toString().contains("Url(https://www.facebook.com)"));
	    assertThat(outContent.toString().contains("HEADERS:"));
	 }
	
}
