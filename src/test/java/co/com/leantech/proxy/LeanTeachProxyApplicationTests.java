package co.com.leantech.proxy;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import co.com.leantech.proxy.client.ProxyClient;
import co.com.leantech.proxy.controller.ProxyController;
import co.com.leantech.proxy.service.ProxyService;

@SpringBootTest
class LeanTeachProxyApplicationTests {
	
	@Autowired
	private ProxyController proxyController;
	
	@Autowired
	private ProxyService proxyService;
	
	@Autowired
	private ProxyClient proxyClient;

	@Test
	void controllerContextLoads() {
		assertThat(proxyController).isNotNull();
	}
	
	@Test
	void serviceContextLoads() {
		assertThat(proxyService).isNotNull();
	}
	
	@Test
	void clientContextLoads() {
		assertThat(proxyClient).isNotNull();
	}

}
