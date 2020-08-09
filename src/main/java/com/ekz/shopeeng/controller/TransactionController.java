package com.ekz.shopeeng.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ekz.shopeeng.ShopeengApplication;
import com.ekz.shopeeng.constant.ResponseCodeConstant;
import com.ekz.shopeeng.constant.TransactionStatusConstant;
import com.ekz.shopeeng.constant.enu.PaymentMethod;
import com.ekz.shopeeng.entity.Transaction;
import com.ekz.shopeeng.entity.UserCart;
import com.ekz.shopeeng.request.CheckoutRequest;
import com.ekz.shopeeng.request.PaymentRequest;
import com.ekz.shopeeng.response.BaseResponse;
import com.ekz.shopeeng.response.CheckoutResponse;
import com.ekz.shopeeng.response.TransactionDetailResponse;
import com.ekz.shopeeng.response.TransactionItem;
import com.ekz.shopeeng.response.TransactionListResponse;
import com.ekz.shopeeng.service.TransactionService;
import com.ekz.shopeeng.service.UserCartService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(ShopeengApplication.MAIN_PATH+"/transaction")
@Tag(name = "Transaction")
public class TransactionController {
	
	@Autowired
	private TransactionService service;
	
	@Autowired
	private UserCartService cartService;

	@RequestMapping(value="/list", method = RequestMethod.GET, produces = "application/json")
	public TransactionListResponse GetTransactionList(@RequestHeader(value = "userId") int userId){
		TransactionListResponse response = new TransactionListResponse();
		
		List<Transaction> list = service.findUserTransactionList(userId);
		
		if(list != null){
			List<TransactionItem> items = new ArrayList<TransactionItem>();
			
			for(Transaction transaction : list){
				TransactionItem item = new TransactionItem(transaction);
				
				items.add(item);
			}
			
			response.setTransactions(items);
			response.setStatusSuccess();
		}else{
			response.setStatusCode(ResponseCodeConstant.ERR_TRANS_NOT_EXIST);
		}
		
		return response;
	}
	
	@RequestMapping(value="/detail", method = RequestMethod.GET, produces = "application/json")
	public TransactionDetailResponse GetTransactionDetail(
			@RequestParam(value = "transactionId", required = true) int transactionId){
		TransactionDetailResponse response = new TransactionDetailResponse();
		
		Transaction transaction = service.findTransactionById(transactionId);
		
		if(transaction != null){
			response.setDetail(new TransactionItem(transaction));
			response.setStatusSuccess();
		}else{
			response.setStatusCode(ResponseCodeConstant.ERR_TRANS_NOT_EXIST);
		}
		
		return response;
	}
	
	@RequestMapping(value="/checkout", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public CheckoutResponse TransactionCheckout(@RequestHeader(value = "userId") int userId,
			@RequestBody CheckoutRequest request){
		CheckoutResponse response = new CheckoutResponse();
		
		List<UserCart> cartList = cartService.findUserCart(userId);
		
		if(cartList.size() > 0){
			int amountTotal = 0;
			
			for(UserCart cart : cartList){
				int itemPrice = cart.getProduct().getPrice() * cart.getQuantity();
				amountTotal += itemPrice;
			}
			
			Transaction transaction = new Transaction();
			transaction.setUserId(userId);
			transaction.setAmount(amountTotal);
			transaction.setFee(request.getFee());
			transaction.setStatus(TransactionStatusConstant.PENDING_PAYMENT);
			
			try{
				transaction = service.save(transaction);
				
				int transactionId = transaction.getTransactionId();
				
				for(UserCart cart : cartList){
					cart.setTransactionId(transactionId);
					
					cartService.save(cart);
				}

				response.setTransactionId(transactionId);
				response.setStatusSuccess();
			}catch(Exception e){
				response.setStatusCode(ResponseCodeConstant.SYSTEM_ERROR);
			}
		}else{
			response.setStatusCode(ResponseCodeConstant.ERR_TRANS_CART_EMPTY);
		}
		
		return response;
	}
	
	@RequestMapping(value="/payment", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public BaseResponse TransactionPayment(@RequestHeader(value = "userId") int userId,
			@RequestBody PaymentRequest request){
		BaseResponse response = new BaseResponse();
		
		Transaction transaction = service.findTransactionById(request.getTransactionId());
		
		if(transaction != null){
			boolean paymentMethodExist = false;
			
			for(PaymentMethod pm : PaymentMethod.values()){
				if(request.getPaymentMethod().trim().equals(pm.getCode())){
					paymentMethodExist = true;
					break;
				}
			}
			
			if(paymentMethodExist){
				Transaction existingToken = service.findTransactionByToken(request.getToken());
				
				if(existingToken == null){
					int pendingPayment = transaction.getAmount() + transaction.getFee();
					
					if(pendingPayment == request.getAmount()){
						transaction.setPaymentMethod(request.getPaymentMethod());
						transaction.setToken(request.getToken());
						transaction.setStatus(TransactionStatusConstant.WAITING_FOR_CONFIRM);
						transaction.setUpdateDate(new Date());
						
						transaction = service.save(transaction);
						response.setStatusSuccess();
					}else{
						response.setStatusCode(ResponseCodeConstant.ERR_TRANS_AMOUNT_INVALID);
					}
				}else{
					response.setStatusCode(ResponseCodeConstant.ERR_TRANS_TOKEN_EXIST);
				}
			}else{
				response.setStatusCode(ResponseCodeConstant.ERR_TRANS_PAYMENT_METHOD_INVALID);
			}
		}else{
			response.setStatusCode(ResponseCodeConstant.ERR_TRANS_NOT_EXIST);
		}
		
		return response;
	}
	
}
