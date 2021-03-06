package com.git.c2cexchange.sale;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaleController {
	private SaleRepository repo;
	private SaleService service;
	
	@Autowired
	public SaleController(SaleRepository repo, SaleService service) throws InterruptedException{
		this.repo = repo;
		this.service = service;
	}
	
//	 Get
		@GetMapping(value = "/send-message")
		public List<Sale> getSale() {
			return repo.findAll();
		}

}
