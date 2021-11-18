package com.git.c2cexchange.sale;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleService {
	
	private RabbitTemplate rabbit;
	
	private SaleRepository saleRepository;
	
	@Autowired
	public SaleService(RabbitTemplate rabbit) {
		this.rabbit = rabbit;
	}
	
	public void sendMessage(byte[] message) {
		rabbit.send("service.market", new Message(message));
	}
}
