package com.git.c2cexchange.Item.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExchangeItem {

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
