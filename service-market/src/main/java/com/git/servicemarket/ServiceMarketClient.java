package com.git.servicemarket;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("service-2")
public interface ServiceMarketClient {
	@GetMapping(value="/test")
	public String test();
	@GetMapping(value="/saleItemList")
	public List<MarketItemList> getSaleItemList();

}
