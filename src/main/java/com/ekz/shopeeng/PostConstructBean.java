package com.ekz.shopeeng;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ekz.shopeeng.entity.Product;
import com.ekz.shopeeng.service.ProductService;
import com.ekz.shopeeng.util.MyUtils;

@Component
public class PostConstructBean {

	@Autowired
	private ProductService productService;
	
	@PostConstruct
    public void init() {
		List<Product> list = productService.findProductList();
		
		if(list.size() == 0){
			for(int i=0;i<5;i++){
				Product prd = new Product();
				prd.setProductName(MyUtils.randomString(5, false));
				prd.setProductDesc(MyUtils.randomString(20, false));
				prd.setProductImage(MyUtils.randomString(15, false));
				prd.setPrice(MyUtils.randomNumber(5000, 100000));
				
				productService.save(prd);
			}
		}
    }
}
