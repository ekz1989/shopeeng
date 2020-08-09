package com.ekz.shopeeng.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ekz.shopeeng.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

	@Query(value = "SELECT n FROM Product n WHERE n.productId = ?1")
	Product findById(int productId);
}
