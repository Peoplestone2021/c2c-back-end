package com.git.servicemarket;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

public class ServiceMarketController {
	
	ServiceMarketClient client;
	
	@Autowired
	public ServiceMarketController(ServiceMarketClient client) {
		this.client = client;
	}
	
	@GetMapping(value="/test")
	public String test(HttpServletRequest req) {
		System.out.println("--service market--");
		return "123";
	}
	
	@GetMapping(value="/test2")
	public String test2() {
		return client.test();
	}
	
	public List<MarketItemList> getMarketItems(){
		System.out.println("--serviceMarketItem--");
		List<MarketItemList> marketItemList = new ArrayList<MarketItemList>();
		
		// 샘플데이터
		MarketItemList marketItem1 = new MarketItemList();
		marketItem1.setId(1);
		marketItem1.setHostName("hong");
		marketItem1.setCntHave("USD");
		marketItem1.setCrcHave(1000);
		marketItem1.setCntWant("KRW");
		marketItem1.setCrcWant(1174000);
		marketItem1.setContent("11111111");
		marketItem1.setStatus(true);
		marketItem1.setDday("20211212");
		
		return marketItemList;
	}


}
