package com.courseservice.java.feign;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


@FeignClient(name = "userservice")
public interface UserServiceClient {

	// Getting role by passing userId from USERSERVICE
	//@RequestMapping(method = RequestMethod.GET, value = "/api/users/userId")
    
	@CircuitBreaker(name = "usercircuitbreaker", fallbackMethod = "userFallBackMethod")
    @GetMapping("/users/userId")
	public String getRoleByUserId(@Valid @RequestParam Integer userId);
	
	public default String userFallBackMethod(Exception ex) {
		return "user service is down wait for some time";
	}

}
