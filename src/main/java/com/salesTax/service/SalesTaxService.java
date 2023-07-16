package com.salesTax.service;

import java.util.ArrayList;

public interface SalesTaxService {
	public void runSalesTaxApp(ArrayList<String> arr) throws Exception;
	
	public float finalItemPriceWithTax(boolean isImport, boolean isApplicableForBasicTax, float itemPrice) throws Exception;
	
	public double roundOffValue(float value);
}
