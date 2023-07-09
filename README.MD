# Sales Tax Application

## Problem:
Basic sales tax is applicable at a rate of 10% on all goods, except books, food,
and medical products that are exempt. Import duty is an additional sales tax
applicable on all imported goods at a rate of 5%, with no exemptions.

When I purchase items I receive a receipt which lists the name of all the items
and their price (including tax), finishing with the total cost of the items,
and the total amounts of sales taxes paid.  The rounding rules for sales tax are
that for a tax rate of n%, a shelf price of p contains (np/100 rounded up to
the nearest 0.05) amount of sales tax.

Write an application that prints out the receipt details for these shopping baskets...
INPUT:
Input 1:
1 book at 12.49
1 music CD at 14.99
1 chocolate bar at 0.85

Output 1:
1 book: 12.49
1 music CD: 16.49
1 chocolate bar: 0.85
Sales Taxes: 1.50
Total: 29.83

Input 2:
1 imported box of chocolates at 10.00
1 imported bottle of perfume at 47.50

Output 2:
1 imported box of chocolates: 10.50
1 imported bottle of perfume: 54.65
Sales Taxes: 7.65
Total: 65.15


Input 3:
1 imported bottle of perfume at 27.99
1 bottle of perfume at 18.99
1 packet of headache pills at 9.75
1 box of imported chocolates at 11.25

Output 3:
1 imported bottle of perfume: 32.19
1 bottle of perfume: 20.89
1 packet of headache pills: 9.75
1 imported box of chocolates: 11.85
Sales Taxes: 6.70
Total: 74.68

## How to run Sales Tax SpringBoot JPA Application (Please Read Full)
1. Clone the project
2. Create Database with name salesTax and update username/password in src/main/resources/appication.properties
3. Insert multiple values in DB such as: id, category and name OR insert value by un-commenting line number 32: addProduct(); in file SalesTaxApplication
4. Run the SalesTaxApplication. Add values in console like this and hit double enter:
	- Sample:
		1 imported bottle of perfume at 27.99
		1 bottle of perfume at 18.99
		1 packet of headache pills at 9.75
		1 box of imported chocolates at 11.25
5. Assumptions: If data value not present in DB then Basic Tax will be apply on the product
6. Sample Input - Output
```
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
```

<video width="820" height="440" controls>
  <source src="SalesTaxApplication.mp4" type="video/mp4">
Your browser does not support the video tag.
</video>