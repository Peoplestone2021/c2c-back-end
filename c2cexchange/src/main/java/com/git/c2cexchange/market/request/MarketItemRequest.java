package com.git.c2cexchange.market.request;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MarketItemRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long marketId;
	private long itemId;
	private String hostName;
	private String crcHave;
	private int cntHave;
	private String crcWant;
	private int cntWant;
	@Column(columnDefinition = "VARCHAR(1000)")
	private String content;
	private boolean status;
	private String dday;
}
