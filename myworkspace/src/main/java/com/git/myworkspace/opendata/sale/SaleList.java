package com.git.myworkspace.opendata.sale;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SaleList {
	// 매물 ID
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int itemId;
	// 유저 아이디
	private String hostName;
	// 가지고있는 국가
	private String crcHave;
	// 가지고있는 돈
	private int cntHave;
	// 원하는환전 국가
	private String crcWant;
	// 원하는환전 액
	private int cntWant;
	// 거래일자
	private String dday;
	// 본문
	private String content;
	// 거래상태
	private boolean status;
}
