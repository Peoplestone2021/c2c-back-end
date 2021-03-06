package com.git.c2cexchange.market;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Entity
public class MarketItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int marketId;
	private int itemId;
	private String hostName;
//	private int crcHave;
//	private String cntHave;
//	private int crcWant;
//	private String cntWant;
	private String crcHave;
	private int cntHave;
	private String crcWant;
	private int cntWant;
	@Column(columnDefinition = "VARCHAR(1000)")
	private String content;
	private boolean status;
	private String dday;

}
