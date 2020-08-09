package com.ekz.shopeeng.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ekz.shopeeng.ShopeengApplication;
import com.ekz.shopeeng.constant.ResponseCodeConstant;
import com.ekz.shopeeng.entity.Product;
import com.ekz.shopeeng.response.BaseResponse;
import com.ekz.shopeeng.response.ProductDetailResponse;
import com.ekz.shopeeng.response.ProductItem;
import com.ekz.shopeeng.response.ProductListResponse;
import com.ekz.shopeeng.service.ProductService;
import com.ekz.shopeeng.util.MyUtils;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(ShopeengApplication.MAIN_PATH+"/product")
@Tag(name = "Product")
public class ProductController {
	
	@Autowired
	private ProductService service;

	@RequestMapping(value="/list", method = RequestMethod.GET, produces = "application/json")
	public ProductListResponse GetProductList(){
		ProductListResponse response = new ProductListResponse();
		
		List<Product> list = service.findProductList();
		
		if(list != null){
			List<ProductItem> items = new ArrayList<ProductItem>();
			
			for(Product product : list){
				ProductItem item = new ProductItem(product);
				
				items.add(item);
			}
			
			response.setProducts(items);
			response.setStatusSuccess();
		}else{
			response.setStatusCode(ResponseCodeConstant.ERR_PRODUCT_NOT_EXIST);
		}
		
		return response;
	}
	
	@RequestMapping(value="/detail", method = RequestMethod.GET, produces = "application/json")
	public ProductDetailResponse GetProductDetail(
			@RequestParam(value = "productId", required = true) int productId){
		ProductDetailResponse response = new ProductDetailResponse();
		
		Product product = service.findProductById(productId);
		
		if(product != null){
			response.setDetail(new ProductItem(product));
			response.setStatusSuccess();
		}else{
			response.setStatusCode(ResponseCodeConstant.ERR_PRODUCT_NOT_EXIST);
		}
		
		return response;
	}
	
}
