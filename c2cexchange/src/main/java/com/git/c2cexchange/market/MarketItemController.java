package com.git.c2cexchange.market;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.git.c2cexchange.Result;
import com.git.c2cexchange.lib.TextProcesser;
import com.git.c2cexchange.market.event.ExchangeOrder;
import com.git.c2cexchange.market.request.MarketItemRequest;

@RestController
public class MarketItemController {
	
	MarketItemRepository repo;
	private MarketItemService service;
	
	@Autowired
	public MarketItemController(MarketItemRepository repo) {
		this.repo=repo;
	}
	
	@GetMapping(value="/marketItems")
	public List<MarketItem> getMarketItems() throws InterruptedException{
		return repo.findAll(Sort.by("marketId").descending());
	}
	
	@GetMapping(value="/saleItemList")
	public List<MarketItem>getSaleItems() throws InterruptedException{
		return repo.findAll(Sort.by("marketId").descending());
	}

	@Cacheable(value="market", condition="(#page + 1) * #size <= 10"
			,key="#crcHave+'-'+#page+'-'+#size")
	@GetMapping("/marketItems/{crcHave}")
	public Page<MarketItem> getMarketItemsPaging(@RequestParam int page, @RequestParam int size){
		return repo.findAll(PageRequest.of(page,  size, Sort.by("marketId").descending()));
	}
	
//	====		service recieve
//	private MarketItemService service;

	
	//====		service sendMessage
	MarketItemController(MarketItemService service) {
		this.service=service;
	}
	
	@PostMapping(value = "/send-message")
	public boolean sendItem(@RequestBody String marketItem, HttpServletRequest req) {
		System.out.println(req.getHeader("content-type"));
		System.out.println(marketItem);
//		service.sendItem(marketItem);
		
		return true;
	}
	
	//====		post MarketItem
	@PostMapping("/marketItems")
	public MarketItem postMarketItem(@RequestBody MarketItem marketItem, HttpServletResponse res) throws InterruptedException {
		
		if(TextProcesser.isEmpyText(marketItem.getHostName())) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		if(TextProcesser.isEmpyText(marketItem.getCrcHave())) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		if(TextProcesser.isEmpyText(marketItem.getCrcWant())) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		if(TextProcesser.isEmpyText(marketItem.getContent())) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		if(TextProcesser.isEmpyText(marketItem.getDday())) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
		MarketItem marketItemList = MarketItem.builder()
				.itemId(marketItem.getItemId())
				.hostName(marketItem.getHostName())
				.crcHave(marketItem.getCrcHave())
				.cntHave(marketItem.getCntHave())
				.crcWant(marketItem.getCrcWant())
				.cntWant(marketItem.getCntWant())
				.content(marketItem.getContent())
				.status(marketItem.isStatus())
				.dday(marketItem.getDday())
				.build();
		
		MarketItem marketItemSaved = repo.save(marketItemList);
		
		return marketItemSaved;
	}

}
