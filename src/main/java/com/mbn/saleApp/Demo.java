package com.mbn.saleApp;

import com.mbn.pojo.Product;
import com.mbn.services.ProductService;
import com.mbn.services.Utils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Demo {
    public static void main(String[] args) throws ParseException {
//        List<Product> products = ProductService.getProducts("iPhone", 1);
//        List<Product> products = ProductService.getProducts(new BigDecimal(17000000), new BigDecimal(28000000), 1);
//        List<Product> products = ProductService.getProducts(1, 1);
        Map<String, String> params = new HashMap<>();
//        params.put("keyword", "iPhone");

//        params.put("fromPrice", "17000000");
//        params.put("toPrice", "28000000");

//        params.put("categoryId", "1");

//        List<Product> products = ProductService.getProducts(params, 1);
//        products.forEach(product -> System.out.printf("%d - %s - %.1f\n", product.getId(), product.getName(), product.getPrice()));

//        Product p = new Product();
//        p.setName("Xiaomi 11T");
//        p.setDescription("128GB");
//        p.setPrice(new BigDecimal(11000000));
//        p.setImage(null);
//        p.setCreatedDate(Utils.F.parse("2020-12-10"));
//        p.setActive(true);
//
//        System.out.println(ProductService.addProduct(p, 1));

//        System.out.println(ProductService.deleteProduct(22));

        Product p = new Product();
        p.setName("Xiaomi 12 Series");
        p.setDescription("256GB");
        p.setPrice(new BigDecimal(20000000));
        p.setImage(null);
        p.setCreatedDate(Utils.F.parse("2020-11-12"));
        p.setActive(true);

        System.out.println(ProductService.updateProduct(23, p, 1));
    }
}
