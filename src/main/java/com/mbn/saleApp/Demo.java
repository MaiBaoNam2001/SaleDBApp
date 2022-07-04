package com.mbn.saleApp;

import com.mbn.pojo.Product;
import com.mbn.services.ProductService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Demo {
    public static void main(String[] args) {
//        List<Product> products = ProductService.getProducts("iPhone", 1);
//        List<Product> products = ProductService.getProducts(new BigDecimal(17000000), new BigDecimal(28000000), 1);
//        List<Product> products = ProductService.getProducts(1, 1);
        Map<String, String> params = new HashMap<>();
//        params.put("keyword", "iPhone");

//        params.put("fromPrice", "17000000");
//        params.put("toPrice", "28000000");

        params.put("categoryId", "1");

        List<Product> products = ProductService.getProducts(params, 2);
        products.forEach(product -> System.out.printf("%d - %s - %.1f\n", product.getId(), product.getName(), product.getPrice()));
    }
}
