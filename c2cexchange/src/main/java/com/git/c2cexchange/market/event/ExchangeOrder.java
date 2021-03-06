package com.git.c2cexchange.market.event;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExchangeOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int marketId;
	private int itemId;
	private String hostName;
	private String crcHave;
	private int cntHave;
	private String crcWant;
	private int cntWant;
	private String content;
	private boolean status;
	private String dday;

}
