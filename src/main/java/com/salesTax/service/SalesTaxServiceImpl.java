package com.salesTax.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.salesTax.main.Product;

@Service("SalesTaxService")
public class SalesTaxServiceImpl implements SalesTaxService {
	
	private String[] arrs;
	private float totalSalesTax = 0;

	@Override
	public void runSalesTaxApp(ArrayList<String> arr) throws Exception {
		try {
			float totalAmount = 0;

			for (String products : arr) {
				final String[] splitedArr = products.split(" at ");
				Product product = new Product(splitedArr[0], Float.parseFloat(splitedArr[1].trim()));

				arrs = product.splits();

				final String itemName = arrs[1].trim();
				final float finalItemPriceWithTax = finalItemPriceWithTax(product.isImported(), product.isApplicableForBasicTax(itemName), product.getPrice());

				totalAmount += finalItemPriceWithTax;

				System.out.println(product.ToString(roundOffValue(finalItemPriceWithTax)));
			}
			System.out.println("Sales Taxes: " + roundOffValue(totalSalesTax));
			System.out.println("Total: " + roundOffValue(totalAmount));
		} catch (Exception e) {
			System.out.println("You may have added wrong inputs. Enter Correct inputs. " + e.getMessage());
		}
	}

	@Override
	public float finalItemPriceWithTax(boolean isImported, boolean isApplicableForBasicTax, float itemPrice) throws Exception {
		float finalItemPriceWithTax = 0;
		try {
			if (isImported && isApplicableForBasicTax) {
				totalSalesTax += itemPrice * 0.15;
				finalItemPriceWithTax += itemPrice * 0.15 + itemPrice;
			} else if (isImported) {
				totalSalesTax += itemPrice * 0.05;
				finalItemPriceWithTax += itemPrice * 0.05 + itemPrice;
			} else if (isApplicableForBasicTax) {
				totalSalesTax += itemPrice * 0.1;
				finalItemPriceWithTax += itemPrice * 0.1 + itemPrice;
			}
			if (finalItemPriceWithTax == 0) {
				finalItemPriceWithTax = itemPrice;
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return finalItemPriceWithTax;
	}

	@Override
	public double roundOffValue(float value) {
		return Math.round(value * Math.pow(10, 2)) / Math.pow(10, 2);
	}
}
