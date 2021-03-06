package com.git.servicemarket;

public class MarketItemList {
	private long itemId;
	private String hostName;
	private String cntHave;
	private int crcHave;
	private String cntWant;
	private int crcWant;
	private String content;
	private boolean status;
	private String dday;
	
	public long getItemid() {
		return itemId;
	}
	
	public void setId(long itemId) {
		this.itemId = itemId;
	}
	
	public String getHostName() {
		return hostName;
	}
	
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	
	public String getCntHave() {
		return cntHave;
	}
	
	public void setCntHave(String cntHave) {
		this.cntHave = cntHave;
	}
	
	public int getCrcHave() {
		return crcHave;
	}
	
	public void setCrcHave(int crcHave) {
		this.crcHave = crcHave;
	}
	
	public String getCntWant() {
		return cntWant;
	}
	
	public void setCntWant(String cntWant) {
		this.cntWant = cntWant;
	}
	
	public int getCrcWant() {
		return crcWant;
	}
	
	public void setCrcWant(int crcWant) {
		this.crcWant = crcWant;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public boolean getStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public String getDday() {
		return dday;
	}
	
	public void setDday(String dday) {
		this.dday = dday;
	}
	
	
	
//    "itemId": 1,
//    "hostName": "change",
//    "cntHave": "USD",
//    "crcHave": 100,
//    "cntWant": "KRW",
//    "crcWant": 118470,
//    "content": "22",
//    "status": true,
//    "dday": "202111"

}
