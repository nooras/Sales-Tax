package com.salesTax;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.salesTax.dto.ProductDTO;
import com.salesTax.service.ProductService;

@ComponentScan
@EnableJpaRepositories
@SpringBootApplication
public class SalesTaxApplication implements CommandLineRunner {
	
	@Autowired
	private ProductService ps;

	public static void main(String[] args) {
		SpringApplication.run(SalesTaxApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try {
//			addProduct();
			getDataFromUserAndRun();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getDataFromUserAndRun() throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter items of shopping baskets: (Example: 1 book at 12.49/ 1 imported book at 12.49) ");
		ArrayList<String> arr = new ArrayList<>();

		while (true) {
			String input = sc.nextLine();
			if (input.equals("")) {
				break;
			}
			arr.add(input);
		}
		sc.close();

		ps.runSalesTaxApp(arr);
	}

	private void addProduct() throws Exception {
		List<ProductDTO> list = new ArrayList<ProductDTO>();
		list.add(new ProductDTO("book", "Book"));
		list.add(new ProductDTO("Medical", "packet of headache pills"));
		list.add(new ProductDTO("Food", "box of chocolates"));
		list.add(new ProductDTO("Other", "bottle of perfume"));
		list.add(new ProductDTO("Food", "chocolate bar"));
		list.add(new ProductDTO("Other", "music CD"));

		try {
			for (ProductDTO product : list) {
				ps.addProduct(product);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}
}

/*
Enter items of shopping baskets: (Example: 1 book at 12.49/ 1 imported book at 12.49) 
1 book at 12.49
1 music CD at 14.99
1 chocolate bar at 0.85

1 book: 12.49
1 music CD: 16.49
1 chocolate bar: 0.85
Sales Taxes: 1.5
Total: 29.83
---------------------------------------------------------------------------------------
Enter items of shopping baskets: (Example: 1 book at 12.49/ 1 imported book at 12.49) 
1 imported box of chocolates at 10.00
1 imported bottle of perfume at 47.50

1 imported box of chocolates: 10.5
1 imported bottle of perfume: 54.63
Sales Taxes: 7.63
Total: 65.13
---------------------------------------------------------------------------------------
Enter items of shopping baskets: (Example: 1 book at 12.49/ 1 imported book at 12.49) 
1 imported bottle of perfume at 27.99
1 bottle of perfume at 18.99
1 packet of headache pills at 9.75
1 box of imported chocolates at 11.25

1 imported bottle of perfume: 32.19
1 bottle of perfume: 20.89
1 packet of headache pills: 9.75
1 box of imported chocolates: 11.81
Sales Taxes: 6.66
Total: 74.64
*/
