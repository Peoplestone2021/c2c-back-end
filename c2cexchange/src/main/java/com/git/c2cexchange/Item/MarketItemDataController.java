package com.git.c2cexchange.Item;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MarketItemDataController {
	
	MarketItemDataRepository repo;
	
	public MarketItemDataController(MarketItemDataRepository repo) {
		this.repo = repo;
	}

//	@Cacheable(value = "items"
				// 카테고리별 앞쪽 10개의 레코드까지만 캐시함
//				, condition="(#page + 1) * #size <= 10"
				// 페이지크기가 고정이면 카테고리별로 0번째 페이지만 캐시
//				, condition="#page == 0"
				// 캐시키이름) 카테고리-페이지-사이즈  ex) all-0-10, beverage-0-5
//				, key = "#category+'-'+#page+'-'+#size")
				// 페이지크기가 고정이면
				// 캐시키이름) 카테고리-페이지  ex) all-0, beverage-0	
				//, key = "#category+'-'+#page")
	@GetMapping(value="/market/current")
	// Page객체는 캐시 생성후 조회가 안 됨(기본 생성자 사용불가)
	// Page 데이터를 응답하는 별도의 응답객체 ProductPageResponse를 만들어서 처리함	
	public List<MarketItemData> getMarketItemCurrent(
//			@PathVariable int itemId 
//			, @RequestParam int page, 
//			@RequestParam int size
			){
		List<Order> orders=new ArrayList<Order>();
		orders.add(new Order(Sort.Direction.DESC, "cntHave"));
		orders.add(new Order(Sort.Direction.ASC,"gubun"));
		
		return repo.findAll(PageRequest.of(0, 25, Sort.by(orders))).toList();
	}
	
	@GetMapping(value="/market/current/{crcHave}")
	public List<MarketItemData> getMarketItemCurrent(
			@PathVariable String crcHave
			){
		Pageable page = PageRequest.of(0, 14, Sort.by("crcHave").descending());
		return repo.findByCrcHave(page, crcHave);
	}
		
//		if(category.equals("all")) {
//			// 전체조회
//			Page<Product> productsPage = repo.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
//			// Page<Product> 객체를 ProductPageResponse 객체로 변환하여 리턴			
//			return ProductPageResponse.builder()
//					.isLast(productsPage.isLast())
//					.totalElements(productsPage.getTotalElements())
//					.content(productsPage.getContent())
//					.build();
//		} else {
//			// 카테고리 필터 조회
//			Page<Product> productsPage = repo.findByCategory(PageRequest.of(page, size, Sort.by("id").descending()), category);
//			// Page<Product> 객체를 ProductPageResponse 객체로 변환하여 리턴
//			return ProductPageResponse.builder()
//					.isLast(productsPage.isLast())
//					.totalElements(productsPage.getTotalElements())
//					.content(productsPage.getContent())
//					.build();
//		}
}

