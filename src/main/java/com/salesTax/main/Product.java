package com.salesTax.main;

public class Product extends CalculateBasicTax {

	private String name;
	private float price;
	
	public Product(String name, float price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	public boolean isImported() {
		return name.contains(" imported ");
	}
	
	public String[] splits() {
		String[] arrs;
		if (isImported()) {
			arrs = name.split(" imported ");
		} else {
			arrs = name.split(" ", 2);
		}
		return arrs;
	}
	
	public String ToString(double taxCost) {
		return (name + ": " + taxCost);
	}
}
