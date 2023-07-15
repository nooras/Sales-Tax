package com.salesTax.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesTax.dto.ProductDTO;
import com.salesTax.entity.Product;
import com.salesTax.repository.ProductRepository;

@Service("productService")
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	private String[] arrs;
	private float totalSalesTax = 0;
	
	@Override
	public int addProduct(ProductDTO productDTO) throws Exception {
		try {
			Product product = new Product();
			product.setName(productDTO.getName());
			product.setCategory(productDTO.getCategory());
			productRepository.save(product);
			return product.getId();
		} catch (Exception e) {
			throw new Exception("Error while adding new product into DB. " + e.getMessage());
		}
	}

	@Override
	public List<ProductDTO> findByName(String name) throws Exception {
		try {
			List<ProductDTO> productDTOList = new ArrayList<ProductDTO>();
			List<Product> productList = productRepository.findByNameContaining(name);
			if (null != productList) {
				for (Product product : productList) {
					productDTOList.add(new ProductDTO(product.getId(), product.getCategory(), product.getName()));
				}
			}
			return productDTOList;
		} catch (Exception e) {
			throw new Exception("Error while retrieving data from DB. " + e.getMessage());
		}
	}

	@Override
	public void runSalesTaxApp(ArrayList<String> arr) throws Exception {
		try {
			float totalAmount = 0;

			for (String products : arr) {
				final String[] splitedArr = products.split(" at ");
				final String firstPart = splitedArr[0];
				boolean isImported = false;

				if (containsImport(firstPart)) {
					arrs = firstPart.split(" imported ");
					isImported = true;
				} else {
					arrs = firstPart.split(" ", 2);
				}

				final float itemPrice = Float.parseFloat(splitedArr[1].trim());
				final String itemName = arrs[1].trim();
				final float finalItemPriceWithTax = finalItemPriceWithTax(isImported, itemPrice, itemName);

				totalAmount += finalItemPriceWithTax;

				System.out.println(firstPart + ": " + roundOffValue(finalItemPriceWithTax));
			}
			System.out.println("Sales Taxes: " + roundOffValue(totalSalesTax));
			System.out.println("Total: " + roundOffValue(totalAmount));
		} catch (Exception e) {
			System.out.println("You may have added wrong inputs. Enter Correct inputs. " + e.getMessage());
		}
	}

	@Override
	public float finalItemPriceWithTax(boolean isImported, float itemPrice, String itemName) throws Exception {
		float finalItemPriceWithTax = 0;
		try {
			if (isImported && isApplicableForBasicTax(itemName)) {
				totalSalesTax += itemPrice * 0.15;
				finalItemPriceWithTax += itemPrice * 0.15 + itemPrice;
			} else if (isImported) {
				totalSalesTax += itemPrice * 0.05;
				finalItemPriceWithTax += itemPrice * 0.05 + itemPrice;
			} else if (isApplicableForBasicTax(itemName)) {
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

	private boolean containsImport(String str) {
		return str.contains("imported");
	}

	private boolean isApplicableForBasicTax(String itemName) throws Exception {
		try {
			List<ProductDTO> productDTOList = findByName(itemName);
			for (ProductDTO product : productDTOList) {
				if (null != product && null != product.getCategory()
						&& (product.getCategory().equalsIgnoreCase("food")
								|| product.getCategory().equalsIgnoreCase("medical")
								|| product.getCategory().equalsIgnoreCase("book"))) {
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	private double roundOffValue(float value) {
		return Math.round(value * Math.pow(10, 2)) / Math.pow(10, 2);
	}
}
