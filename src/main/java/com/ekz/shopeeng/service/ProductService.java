package com.ekz.shopeeng.service;

import java.util.List;

import com.ekz.shopeeng.entity.Product;

public interface ProductService {

	List<Product> findProductList();

	Product findProductById(int productId);

	Product save(Product product);
}
