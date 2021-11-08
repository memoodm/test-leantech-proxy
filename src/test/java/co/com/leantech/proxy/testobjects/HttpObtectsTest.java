package co.com.leantech.proxy.testobjects;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpObtectsTest {

	public static final String REDIRECT_ENDPOINT = "/redirect-web";
	public static final String VALID_BASE_TARGET = "https://www.facebook.com";
	public static final String VALID_COMPLETE_URL = "/redirect-web?target=https://www.facebook.com";
	
	public static URI getValidUriUrl() {
		try {
			return new URI(VALID_BASE_TARGET);
		}
		catch(Exception e) {
			return null;
		}
	}
	
	public static String getValidBodyJson() {		
		return "{\"color\" : \"Black\", \"type\" : \"BMW\"}";
	}
	
	public static Object getValidBodyObject(ObjectMapper objectMapper) {		
		try {
			return objectMapper.readValue("{\"color\" : \"Black\", \"type\" : \"BMW\"}",Object.class);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		List<String> h1 = new ArrayList<>();
		h1.add("any");
		headers.put("device_type", h1);
		return headers;
	}	
	
	public static ResponseEntity<String> validResponse() {
		return ResponseEntity
				.status(HttpStatus.FOUND)
				.location(getValidUriUrl())
				.headers(getHeaders())
				.body(getValidBodyJson());
	}
	
}
