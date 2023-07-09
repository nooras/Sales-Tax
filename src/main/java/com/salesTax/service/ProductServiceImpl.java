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

}
