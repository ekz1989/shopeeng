package com.ekz.shopeeng.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ekz.shopeeng.entity.UserCart;

@Repository
public interface UserCartRepository extends CrudRepository<UserCart, Integer> {

	@Query(value = "SELECT n FROM UserCart n WHERE n.userId = ?1 AND n.transactionId = 0")
	List<UserCart> findUserCart(int userId);

	@Query(value = "SELECT n FROM UserCart n WHERE n.userId = ?1 AND n.transactionId <> 0")
	List<UserCart> findPaidUserCart(int userId);

	@Query(value = "SELECT n FROM UserCart n WHERE n.userId = ?1 AND n.product.productId = ?2 AND n.transactionId = 0")
	UserCart findExistingProduct(int userId, int productId);
	
	@Query(value = "SELECT n FROM UserCart n WHERE n.transactionId = ?1")
	List<UserCart> findUserCartByTransactionId(int transactionId);

	@Query(value = "SELECT n FROM UserCart n WHERE n.cartId = ?1")
	UserCart findCartItemById(int cartId);

}
