package co.com.leantech.proxy.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.com.leantech.proxy.model.HttpMessage;
import co.com.leantech.proxy.utils.Mapper;

@Service
public class RestClient implements ProxyClient{
	
	ObjectMapper objectMapper = new ObjectMapper();
	List<String> unmodificableHeaders = Arrays.asList("host","connection","name","content-length");
	List<String> ignoreResponseHeaders = Arrays.asList("content-encoding");

	@Autowired
	private HttpClient client;
	
	@Override
	public HttpMessage peek(HttpMessage request) throws IOException, InterruptedException {
		Builder requestBuilder = HttpRequest.newBuilder()
				.uri(URI.create(request.getUrl()))
				.method(request.getMethod(), getBody(request.getBody()));
		this.addHeaders(request.getHeaders(),requestBuilder);
		HttpResponse<String> httpResponse = client.send(requestBuilder.build(),HttpResponse.BodyHandlers.ofString());
		return Mapper.toDto(httpResponse,ignoreResponseHeaders);
	}
	
	private void addHeaders(Map<String,String> headers,Builder build) {
		headers.keySet().stream()
			.filter(e->!unmodificableHeaders.contains(e.toLowerCase()))
			.forEach(e->build.setHeader(e, headers.get(e)));
	}

	private BodyPublisher getBody(String body) {
		return HttpRequest.BodyPublishers.ofString(body);
	}
	
}
