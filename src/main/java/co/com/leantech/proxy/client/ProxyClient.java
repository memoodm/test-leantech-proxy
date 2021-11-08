package co.com.leantech.proxy.client;

import java.io.IOException;

import co.com.leantech.proxy.model.HttpMessage;

public interface ProxyClient {

	public HttpMessage peek(HttpMessage reques) throws IOException, InterruptedException;
	
}
