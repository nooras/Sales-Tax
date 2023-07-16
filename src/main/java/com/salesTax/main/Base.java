package com.salesTax.main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Base {

	enum ProductType {
		Food, Medical, Book, Other
	}
	
	static Map<ProductType, List<String>> data = new HashMap<ProductType, List<String>>();
	
	Base() {
		data.put(ProductType.Food, Arrays.asList("chocolate", "chocolates", "chocolate bar", "box of chocolates", "box of imported chocolates"));
		data.put(ProductType.Medical, Arrays.asList("pills", "packet of headache pills"));
		data.put(ProductType.Book, Arrays.asList("book"));
		data.put(ProductType.Other, Arrays.asList("bottle of perfume"));
	}

	public ProductType isContainsProductType(String value) {
		for (Map.Entry<ProductType, List<String>> entry: data.entrySet()) {
			if(entry.getValue().contains(value)) {
				return entry.getKey();
			}
		}
		return null;
	}
	
	abstract boolean isApplicableForBasicTax(String name);
}
