package com.git.myworkspace.opendata.sale;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleListService {
	
	private RabbitTemplate rabbit;
	
	private SaleListRepository salelistRepository;
	
	@Autowired
	public SaleListService(RabbitTemplate rabbit) {
		this.rabbit = rabbit;
	}
	
	public void sendSaleList(SaleList saleList) {
//		rabbit.send("test.hello.1", new Message(message));
//		rabbit.send("service.lobby", new Message(message));
		rabbit.convertAndSend("service.market", saleList);
//		rabbit.convertAndSend("service.market", message);
//		rabbit.send("test.hello.3", new Message(message));
//		System.out.println("service.sendMessage: "+saleList);
		System.out.println("send.saleList.getClass: "+saleList.getClass().getSimpleName());
//		System.out.println("service.convertAndSendMessage: "+message);
	}
	
	public void sendConvertedMessage(SaleList saleList) {
		rabbit.convertAndSend("service.lobby", saleList);
		rabbit.convertAndSend("service.market", saleList);
	}
}
