package com.salesTax.main;

public class CalculateBasicTax extends Base {

	@Override
	public boolean isApplicableForBasicTax(String name) {
		if (null == isContainsProductType(name)
				|| (null != isContainsProductType(name) && isContainsProductType(name).equals(ProductType.Other))) {
			return true; // if its not present in Data list OR other than book/food/medical then applicable of basic tax
		}
		return false;
	}
}
