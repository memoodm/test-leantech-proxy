package co.com.leantech.proxy.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.leantech.proxy.service.ProxyService;

@RestController
public class ProxyController {
	
	@Autowired
	private ProxyService proxyService;
	
	@RequestMapping(value = "/redirect-web", method = RequestMethod.GET)
	public ResponseEntity<?> redirect(
			@RequestParam(required = true) String target,
			@RequestBody(required=false) Object body,
			HttpServletRequest request){	
		return proxyService.redirect(target,body,request);
	}

}
