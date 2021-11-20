package com.git.c2cexchange.market;

import com.git.c2cexchange.Result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MarketItemCreatedResponse {
	private MarketItem marketItem;
	private Result result;

}
