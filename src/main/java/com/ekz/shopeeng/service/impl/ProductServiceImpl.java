package com.ekz.shopeeng.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ekz.shopeeng.entity.Product;
import com.ekz.shopeeng.repo.ProductRepository;
import com.ekz.shopeeng.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepo;
	
	@Override
	public List<Product> findProductList() {
		return (List<Product>) productRepo.findAll();
	}

	@Override
	public Product findProductById(int productId) {
		return productRepo.findById(productId);
	}

	@Override
	public Product save(Product product) {
		return productRepo.save(product);
	}

}
