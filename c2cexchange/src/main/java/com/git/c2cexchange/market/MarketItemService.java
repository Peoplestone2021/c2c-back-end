package com.git.c2cexchange.market;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.transaction.Transactional;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.git.c2cexchange.market.request.MarketItemRequest;

@Service
public class MarketItemService {
	
	private RabbitTemplate rabbit;
	
	private MarketItemRepository marketItemRepo;
	
	
	
	public SseEmitter getEmitter(String marketItem) {
		
		return this.emitters.get(marketItem);
	}
	public void putEmitter(String marketItem, SseEmitter emitter) {
		this.emitters.put(marketItem, emitter);
		System.out.println(emitters.size());
	}
	public void removeEmitter(String marketItem) {
		this.emitters.remove(marketItem);
	}

	@RabbitListener(queues = "service.market")
	public void receiveMarketItem(String marketItem) throws UnsupportedEncodingException {

		System.out.println("-- service.market --");
		System.out.println("ServiceRBListener: " + new String(marketItem));

		String recieved = new String(marketItem);
		System.out.println(recieved);
		
//		emitters.values().parallelStream().forEach(emitter -> {
//			try {
//				emitter.send(marketItem);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		});

	}
	private Map<String, SseEmitter> emitters = new ConcurrentHashMap<String, SseEmitter>();

	
	@Transactional
//	(rollbackOn = Exeption.class)
	public MarketItem saveOrder(MarketItemRequest reqMarketItem) {
		
		MarketItem toSaveMarketItem = MarketItem.builder()
				.itemId(reqMarketItem.getItemId())
				.hostName(reqMarketItem.getHostName())
				.crcHave(reqMarketItem.getCrcHave())
				.cntHave(reqMarketItem.getCntHave())
				.crcWant(reqMarketItem.getCrcWant())
				.cntWant(reqMarketItem.getCntWant())
				.content(reqMarketItem.getContent())
				.status(reqMarketItem.isStatus())
				.dday(reqMarketItem.getDday())
				.build();
		
		MarketItem saveMarketItem = marketItemRepo.save(toSaveMarketItem);
				
				
		return saveMarketItem;
	}
	
//====		보내는 쪽
	
	@Autowired
	public MarketItemService(RabbitTemplate rabbit) {
		this.marketItemRepo = marketItemRepo;
		this.rabbit = rabbit;
	}
	
	public void sendItem(byte[] marketItem) {
		rabbit.send("service.manager", new Message(marketItem));
	}
	
	
	
	

}