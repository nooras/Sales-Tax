package com.salesTax.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.salesTax.dto.ProductDTO;

@Service
public interface ProductService {
	
	public int addProduct(ProductDTO productDTO) throws Exception;
	
	public List<ProductDTO> findByName(String name) throws Exception;
	
	public void runSalesTaxApp(ArrayList<String> arr) throws Exception;
	
	public float finalItemPriceWithTax(boolean isImported, float itemPrice, String itemName) throws Exception;
	
	
}
