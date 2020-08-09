package com.ekz.shopeeng.controller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ekz.shopeeng.ShopeengApplication;
import com.ekz.shopeeng.constant.ResponseCodeConstant;
import com.ekz.shopeeng.entity.Product;
import com.ekz.shopeeng.entity.User;
import com.ekz.shopeeng.entity.UserCart;
import com.ekz.shopeeng.request.CartUpdateRequest;
import com.ekz.shopeeng.request.LoginRequest;
import com.ekz.shopeeng.request.RegisterRequest;
import com.ekz.shopeeng.response.BaseResponse;
import com.ekz.shopeeng.response.LoginResponse;
import com.ekz.shopeeng.response.UserCartItem;
import com.ekz.shopeeng.response.UserCartListResponse;
import com.ekz.shopeeng.service.ProductService;
import com.ekz.shopeeng.service.UserCartService;
import com.ekz.shopeeng.service.UserService;
import com.ekz.shopeeng.util.MyUtils;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(ShopeengApplication.MAIN_PATH+"/user")
@Tag(name = "User")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private UserCartService cartService;
	
	@Autowired
	private ProductService productService;

	@RequestMapping(value="/login", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public LoginResponse UserLogin(@RequestBody LoginRequest request) throws NoSuchAlgorithmException{
		LoginResponse response = new LoginResponse();
		
		String email = request.getEmail();
		String password = MyUtils.getMD5(request.getPassword());
		
		User user = service.userLogin(email, password);
		
		if(user != null){
			response.setUserId(user.getUserId());
			response.setName(user.getName());
			response.setStatusSuccess();
		}else{
			response.setStatusCode(ResponseCodeConstant.ERR_LOGIN);
		}
		
		return response;
	}

	@RequestMapping(value="/register", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public BaseResponse UserRegister(@RequestBody RegisterRequest request) throws NoSuchAlgorithmException{
		BaseResponse response = new BaseResponse();
		
		User user = new User();
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setPassword(MyUtils.getMD5(request.getPassword()));
		
		try{
			service.userRegister(user);
			response.setStatusSuccess();
		}catch(DataIntegrityViolationException e){
			response.setStatusCode(ResponseCodeConstant.ERR_REG_EMAIL_EXIST);
			System.out.println(e.getMessage());
		}
		
		return response;
	}

	@RequestMapping(value="/cart", method = RequestMethod.GET, produces = "application/json")
	public UserCartListResponse UserCart(@RequestHeader(value = "userId") int userId){
		UserCartListResponse response = new UserCartListResponse();
		
		List<UserCart> list = cartService.findUserCart(userId);
		
		if(list != null){
			List<UserCartItem> items = new ArrayList<UserCartItem>();
			
			for(UserCart cart : list){
				UserCartItem item = new UserCartItem(cart);
				
				items.add(item);
			}
			
			response.setItems(items);
		}
		
		response.setStatusSuccess();
		
		return response;
	}
	
	@RequestMapping(value="/cart/update", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public BaseResponse UserCartInsert(@RequestHeader(value = "userId") int userId,
			@RequestBody CartUpdateRequest request){
		BaseResponse response = new BaseResponse();
		
		Product product = productService.findProductById(request.getProductId());
		
		if(product != null){
			UserCart cart = cartService.findExistingProduct(userId, product.getProductId());
			
			if(cart == null){
				cart = new UserCart();
				cart.setProduct(product);
				cart.setUserId(userId);
			}
			
			cart.addQuantity(request.getQuantity());
			cart.setUpdateDate(new Date());
			
			cartService.save(cart);

			response.setStatusSuccess();
		}else{
			response.setStatusCode(ResponseCodeConstant.ERR_PRODUCT_NOT_EXIST);
		}
		
		return response;
	}
	
	
}
