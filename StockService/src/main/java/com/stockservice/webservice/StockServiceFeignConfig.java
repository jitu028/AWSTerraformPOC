package com.stockservice.webservice;

import org.springframework.context.annotation.Bean;

import feign.Retryer;
import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.HashSet;
import java.util.Set;

import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;


public class StockServiceFeignConfig {
	
	 @Bean
	  public Retryer retryer() {
	    return new Retryer.Default(100, SECONDS.toMillis(1), 3);
	  }

	  @Bean
	  public RequestInterceptor feignRequestInterceptor() {
		  
	    return t -> t.header("x-rapidapi-key", "1c166ffaf2msh3d0fe1a1205694ap1ec782jsnf88e53ed8511").header("x-rapidapi-host", "apidojo-yahoo-finance-v1.p.rapidapi.com");
	  }

	  @Bean
	  public ErrorDecoder errorDecoder() {
		
	    Set<Integer> retryableStatusCodes = new HashSet<>();
	    retryableStatusCodes.add(500);
	    retryableStatusCodes.add(503);
	    
	    return new FeignErrorDecoder(retryableStatusCodes);
	  }

}
