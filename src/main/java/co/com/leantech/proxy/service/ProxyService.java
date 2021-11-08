package co.com.leantech.proxy.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import co.com.leantech.proxy.client.ProxyClient;
import co.com.leantech.proxy.model.HttpMessage;
import co.com.leantech.proxy.utils.HttpException;
import co.com.leantech.proxy.utils.Mapper;

@Service
public class ProxyService {
	
	@Autowired
	private ProxyClient proxyClient;

	@Autowired
	private LogsService logsService;
	
	public ResponseEntity<String> redirect(String targetUrl,Object body,HttpServletRequest servletRequest){
		try {
			HttpMessage request = Mapper.toDto(targetUrl, body, servletRequest);
			HttpMessage response = proxyClient.peek(request);
			logsService.generateRequestLogs(request);
			logsService.generateResponseLogs(response);
			return ResponseEntity
					.status(response.isStatusCodeSatisfactory() || response.isStatusCodeRedirect() ? HttpStatus.FOUND : HttpStatus.NOT_ACCEPTABLE)
					.location(response.getUriUrl())
					.headers(response.geHttpHeaders())
					.body(response.getBody());
		}
		catch(Exception e) {
			throw new HttpException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	} 	
	
}
