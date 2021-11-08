package co.com.leantech.proxy.model;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

@Data
public class HttpMessage {

	private String url;
	private String body;
	private Map<String,String> headers = new HashMap<>();
	private String method;
	private Integer httpStatus;
	
	public void setHeaders(HttpServletRequest sourceRequest) {
		Iterable<String> iterable = () -> sourceRequest.getHeaderNames().asIterator();
		StreamSupport.stream(iterable.spliterator(), false)
			.forEach(e->this.headers.put(e, sourceRequest.getHeader(e)));
	}
	
	public void setBody(Object body) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			String jsonBody = body==null ? "{}" : objectMapper.writeValueAsString(body);
			this.body = jsonBody;
		}catch(Exception e) {
			this.body = "{}";
		}
	}
	
	public URI getUriUrl() throws URISyntaxException {
		return new URI(this.url);
	}
	
	public HttpHeaders geHttpHeaders() {
        HttpHeaders responseHeaders = new HttpHeaders();
        this.headers.keySet()
	        .stream()
	        .forEach(keyValue -> responseHeaders.add(keyValue,this.headers.get(keyValue) ));
        return responseHeaders;
	}

	public boolean isStatusCodeSatisfactory() {
		String code = this.httpStatus.toString().trim();
		return code.charAt(0)=='2' ? true : false;
	}

	public boolean isStatusCodeRedirect() {
		String code = this.httpStatus.toString().trim();
		return code.charAt(0)=='3' ? true : false;
	}

	
}
