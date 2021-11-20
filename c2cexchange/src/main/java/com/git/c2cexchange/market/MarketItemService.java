package com.git.c2cexchange.market;

import java.io.UnsupportedEncodingException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.git.c2cexchange.market.event.ExchangeOrder;

@Service
public class MarketItemService {
	
	private final MarketItemRepository repo;
	
	private RabbitTemplate rabbit;
	
	public MarketItemService(MarketItemRepository repo) {
		this.repo = repo;
		this.rabbit = rabbit;
	}
	
//	@Autowired
//	public MarketItemService(RabbitTemplate rabbit) {
////		this.marketItemRepo = marketItemRepo;
//		this.rabbit = rabbit;
//	}

	@CacheEvict(value="items", allEntries = true)
	@RabbitListener(queues = "service.market")
	public void receiveSaleListItem(ExchangeOrder exchangeOrder)
//			throws UnsupportedEncodingException 
	{
		System.out.println("[log]saleListItem: " + exchangeOrder);
//		System.out.println("[log]receiveMarketItem.Class.Name: " + exchangeOrder.getClass().getSimpleName());
		saveMarketItem(exchangeOrder);

//		System.out.println("[log]receiveMarketItem: "+marketItem);
	}
	
	public MarketItem saveMarketItem(ExchangeOrder exchangeOrder) {
		MarketItem marketItem = MarketItem
				.builder()
//				.marketId(exchangeOrder.getMarketId())
				.itemId(exchangeOrder.getItemId())
				.hostName(exchangeOrder.getHostName())
				.crcHave(exchangeOrder.getCrcHave())
				.cntHave(exchangeOrder.getCntHave())
				.crcWant(exchangeOrder.getCrcWant())
				.cntWant(exchangeOrder.getCntWant())
				.content(exchangeOrder.getContent())
				.dday(exchangeOrder.getDday())
				.status(exchangeOrder.isStatus())
				.build();
		
		System.out.println("[log]saveMarketItem: " + marketItem);
		repo.save(marketItem);
		return marketItem;
	}

//====		보내는 쪽



	
	public void sendItem(MarketItem marketItem) {
		rabbit.convertAndSend("service.manager", marketItem);
		rabbit.convertAndSend("service.market", marketItem);
	}
	
	
	
	

}
