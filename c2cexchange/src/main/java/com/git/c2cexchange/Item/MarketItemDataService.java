package com.git.c2cexchange.Item;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.git.c2cexchange.Item.event.ExchangeItem;

@Service
public class MarketItemDataService {
	

	private MarketItemDataRepository repo;
	
	@Autowired
	public MarketItemDataService(MarketItemDataRepository repo) {
		this.repo = repo;
	}


	// 데이터가 갱신되는 시점에 캐시 삭제
	// ex) 타 시스템에서 데이터를 받아와서 저장함
//	@CacheEvict(value="products", allEntries = true)
//	@RabbitListener(queues = "sales.product.create")
	@Scheduled(fixedRate = 1000 * 60 * 60 * 1)
//	@Scheduled(cron = "0 * * * * *")
//	@CacheEvict(value = "marketItem-current", allEntries = true)
	public void requestMarketItem() throws IOException{
System.out.println(new Date().toLocaleString());
		
		StringBuilder builder = new StringBuilder();
////		http://54.180.135.245:8080/saleItemList
		builder.append("http://54.180.135.245");
		builder.append(":8080");
		builder.append("/saleItemList");
		
		URL url= new URL(builder.toString());
		System.out.println(url);
		
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		
		byte[] response = con.getInputStream().readAllBytes();
		
		// ====
		
		String data = new String(response, "UTF-8");
		
		System.out.println(data);
		
//		String json = XML.toJSONObject(data).toString(2);
		
//		System.out.println(json);
		
//		MarketItemResponse response = new Gson().fromJson(json, MarketItemResponse.class);

		ObjectMapper mapper = new ObjectMapper();
		
		List<MarketItemDataResponse> list = 
				mapper.readValue(data, new TypeReference<List<MarketItemDataResponse>>(){});
					
//		for(MarketItemResponse res: list) {
//			
//		}
		
		List<MarketItemData> item = new ArrayList<MarketItemData>();
		for(MarketItemDataResponse res:list) {
			MarketItemData record= MarketItemData
					.builder()
					.itemId(res.getItemId())
					.hostName(res.getHostName()
							.isEmpty() ? null:String
							.valueOf(res.getHostName()))
//					.crcHave(res.getCrcHave()
//							.isEmpty() ? null:String
//							.valueOf(res.getCrcHave()))
//					.cntHave(res.getCntHave())
//					.crcWant(res.getCrcWant()
//							.isEmpty() ? null:String
//							.valueOf(res.getCrcWant()))
//					.cntWant(res.getCntWant())
					.crcHave(res.getCrcHave())
					.cntHave(res.getCntHave()
							.isEmpty() ? null:String
							.valueOf(res.getCntHave()))
					.cntWant(res.getCntWant()
							.isEmpty() ? null:String
							.valueOf(res.getCntWant()))
					.crcWant(res.getCrcWant())
					.content(res.getContent()
							.isEmpty() ? null:String
							.valueOf(res.getContent()))
					.status(res.isStatus())
					.dday(res.getDday()
							.isEmpty() ? null:String
							.valueOf(res.getDday()))
							.build();
			item.add(record);
		}
		
//		for(MarketItemResponse.Response item:response
//				.getResponse().getItem()) {
//			MarketItem record = MarketItem
//					.builder()
//					.itemId(item.getItemId())
//					.hostName(item.getHostName()
//							.isEmpty() ? null:String
//							.valueOf(item.getHostName()))
//					.crcHave(item.getCrcHave()
//							.isEmpty() ? null:String
//									.valueOf(item.getCrcHave()))
//					.cntHave(item.getCntHave())
//					.crcWant(item.getCrcWant()
//							.isEmpty() ? null:String
//									.valueOf(item.getCrcWant()))
//					.cntWant(item.getCntWant())
//					.content(item.getContent()
//							.isEmpty() ? null:String
//									.valueOf(item.getContent()))
//					.status(item.isStatus())
//					.dday(item.getDday()
//							.isEmpty() ? null:String
//									.valueOf(item.getDday()))
//					.build();
//			
//			System.out.println("record: "+record);
//			
//			list.add(record);
//		}
		repo.saveAll(item);
	}
	}
//	
//	@SuppressWarnings("deprecation")
//	public void requestMarketItem(String crcHave) throws IOException{
//		System.out.println(new Date().toLocaleString());
//		
//		StringBuilder builder = new StringBuilder();
//////		http://54.180.135.245:8080/saleItemList
//		builder.append("http://54.180.135.245");
//		builder.append(":8080");
//		builder.append("/saleItemList");
//		
//		URL url= new URL(builder.toString());
//		System.out.println(url);
//		
//		HttpURLConnection con = (HttpURLConnection) url.openConnection();
//		
//		byte[] response = con.getInputStream().readAllBytes();
//		
//		// ====
//		
//		String data = new String(response, "UTF-8");
//		
//		System.out.println(data);
//		
////		String json = XML.toJSONObject(data).toString(2);
//		
////		System.out.println(json);
//		
////		MarketItemResponse response = new Gson().fromJson(json, MarketItemResponse.class);
//
//		ObjectMapper mapper = new ObjectMapper();
//		
//		List<MarketItemResponse> list = 
//				mapper.readValue(data, new TypeReference<List<MarketItemResponse>>(){});
//					
////		for(MarketItemResponse res: list) {
////			
////		}
//		
//		List<MarketItem> item = new ArrayList<MarketItem>();
//		for(MarketItemResponse res:list) {
//			MarketItem record= MarketItem
//					.builder()
//					.itemId(res.getItemId())
//					.hostName(res.getHostName()
//							.isEmpty() ? null:String
//							.valueOf(res.getHostName()))
////					.crcHave(res.getCrcHave()
////							.isEmpty() ? null:String
////							.valueOf(res.getCrcHave()))
////					.cntHave(res.getCntHave())
////					.crcWant(res.getCrcWant()
////							.isEmpty() ? null:String
////							.valueOf(res.getCrcWant()))
////					.cntWant(res.getCntWant())
//					.crcHave(res.getCrcHave())
//					.cntHave(res.getCntHave()
//							.isEmpty() ? null:String
//							.valueOf(res.getCntHave()))
//					.cntWant(res.getCntWant()
//							.isEmpty() ? null:String
//							.valueOf(res.getCntWant()))
//					.crcWant(res.getCrcWant())
//					.content(res.getContent()
//							.isEmpty() ? null:String
//							.valueOf(res.getContent()))
//					.status(res.isStatus())
//					.dday(res.getDday()
//							.isEmpty() ? null:String
//							.valueOf(res.getDday()))
//							.build();
//			item.add(record);
//		}
//		
////		for(MarketItemResponse.Response item:response
////				.getResponse().getItem()) {
////			MarketItem record = MarketItem
////					.builder()
////					.itemId(item.getItemId())
////					.hostName(item.getHostName()
////							.isEmpty() ? null:String
////							.valueOf(item.getHostName()))
////					.crcHave(item.getCrcHave()
////							.isEmpty() ? null:String
////									.valueOf(item.getCrcHave()))
////					.cntHave(item.getCntHave())
////					.crcWant(item.getCrcWant()
////							.isEmpty() ? null:String
////									.valueOf(item.getCrcWant()))
////					.cntWant(item.getCntWant())
////					.content(item.getContent()
////							.isEmpty() ? null:String
////									.valueOf(item.getContent()))
////					.status(item.isStatus())
////					.dday(item.getDday()
////							.isEmpty() ? null:String
////									.valueOf(item.getDday()))
////					.build();
////			
////			System.out.println("record: "+record);
////			
////			list.add(record);
////		}
//		repo.saveAll(item);
//	}
////	private int itemId;
////	private String hostName;
////	private String crcHave;
////	private int cntHave;
////	private String crcWant;
////	private int cntWant;
////	private String content;
////	private boolean status;
////	private String dday;
//
////	public MarketItem saveProduct(SalesProduct salesProduct) {
////		MarketItem product = Product
////							.builder()
////							.category(salesProduct.getCategory())
////							.productCode(salesProduct.getCode())
////							.productName(salesProduct.getName())
////							.price(salesProduct.getPrice())
////							.salesProductId(salesProduct.getId())
////							.build();
////		repo.save(product);
////
////		return product;
////	}

