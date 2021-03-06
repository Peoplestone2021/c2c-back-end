package com.git.c2cexchange.Item;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(indexes = @Index(name = "idx_market_item_data_1", columnList = "crcHave, cntHave"))
public class MarketItemData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int itemId;
	private String hostName;
	private int crcHave;
	private String cntHave;
	private int crcWant;
	private String cntWant;
//	private String crcHave;
//	private int cntHave;
//	private String crcWant;
//	private int cntWant;
	private String content;
	private boolean status;
	private String dday;

}
