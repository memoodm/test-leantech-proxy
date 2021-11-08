package co.com.leantech.proxy.testobjects;

import co.com.leantech.proxy.model.HttpMessage;

public class ProxyClientObjectTest {
	
	public static HttpMessage getValidRequest() {
		HttpMessage message = new HttpMessage();
		message.setBody("{}");
		message.geHttpHeaders().add("device_type", "any");
		message.setUrl("");
		message.setMethod("");
		message.setHttpStatus(200);
		return message;
	}
	
	public static HttpMessage getRedirectResponse() {
		HttpMessage message = new HttpMessage();
		message.setBody("{}");
		message.geHttpHeaders().add("device_type", "any");
		message.setUrl("");
		message.setMethod("");
		message.setHttpStatus(302);
		return message;
	}
	
	public static HttpMessage getInvalidResponse() {
		HttpMessage message = new HttpMessage();
		message.setBody("{}");
		message.setUrl("");
		message.setMethod("");
		message.setHttpStatus(400);
		return message;
	}
	
	public static HttpMessage getValidResponse() {
		HttpMessage message = new HttpMessage();
		message.setBody("{}");
		message.geHttpHeaders().add("device_type", "any");
		message.setUrl("");
		message.setMethod("");
		message.setHttpStatus(200);
		return message;
	}
	
	public static HttpMessage getErrorResponse() {
		HttpMessage message = new HttpMessage();
		message.setBody(null);
		message.setUrl(null);
		message.setMethod(null);
		message.setHttpStatus(400);
		return message;
	}
	
}
