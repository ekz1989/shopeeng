package com.ekz.shopeeng.schedule;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ekz.shopeeng.constant.TransactionStatusConstant;
import com.ekz.shopeeng.entity.Transaction;
import com.ekz.shopeeng.service.TransactionService;

@Component
@EnableScheduling
public class PaymentSchedule {
	
	@Autowired
	private TransactionService tranService;
	
	@Scheduled(cron="0 */5 * * * *")
	public void getPaymentProcess(){
		paymentProcess(new Date());
	}
	
	public void paymentProcess(Date runAt){
		System.out.println("Payment process run at "+runAt.toString());
		
		List<Transaction> list = tranService.findTransactionByStatus(TransactionStatusConstant.WAITING_FOR_CONFIRM);
		
		if(list.size() > 0){
			for(Transaction trans : list){
				trans.setStatus(TransactionStatusConstant.ON_PROCESS);
				trans.setUpdateDate(new Date());
				
				tranService.save(trans);
			}
		}
	}
}
