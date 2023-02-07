package com.codeTest.jsonSplit.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.codeTest.jsonSplit.dto.AdminRequest;
import com.codeTest.jsonSplit.dto.SettledTransactions;
import com.codeTest.jsonSplit.dto.SplitPayout;

import jakarta.servlet.http.HttpServletRequest;

import com.codeTest.jsonSplit.dto.Split_transactions;


@RestController
@RequestMapping("json")
public class jsonController {
	List<SplitPayout> splitPayoutslist = new ArrayList<>();
    Map<String,SplitPayout> payoutMap = new HashMap<>();
        @PostMapping("/transactions")
	public SettledTransactions[] transactionController() {
		String splittransactiondata = "[{\"txnid\":\"EB9_188473_1675209979073\",\"easepayid\":\"E230201S2Q5CU2\",\"service_tax\":0,\"service_charge\":0,\"submerchant_id\":null,\"transaction_type\":\"CreditCard\",\"settlement_amount\":1522.13,\"split_transactions\":[{\"amount\":22.13,\"service_tax\":0,\"account_label\":\"bnxt_label_1\",\"service_charge\":0,\"split_payout_percentage\":1.45},{\"amount\":1500,\"service_tax\":0,\"account_label\":\"B_188473_90822_1675209978\",\"service_charge\":0,\"split_payout_percentage\":98.55}],\"transaction_amount\":1522.13}]";
        String splitpayoutdata="[{\"paid_out\":1,\"bank_name\":\"INDUSINDBANK\",\"payout_date\":\"2023-02-0121:21:42.157149\",\"account_label\":\"bnxt_label_1\",\"payout_amount\":66333.87,\"account_number\":\"201003344973\",\"split_payout_id\":\"SPLJFPUJOA\",\"bank_transaction_id\":\"YESB30320289857\"},{\"paid_out\":1,\"bank_name\":\"PAYTMPAYMENTSBANKLTD\",\"payout_date\":\"2023-02-0121:21:48.192444\",\"account_label\":\"B_188473_90822_1675209978\",\"payout_amount\":1500,\"account_number\":\"917872408420\",\"split_payout_id\":\"SP8D235E9Q\",\"bank_transaction_id\":\"YESB30320290048\"},{\"paid_out\":1,\"bank_name\":\"PAYTMPAYMENTSBANKLTD\",\"payout_date\":\"2023-02-0121:21:48.192444\",\"account_label\":\"B_188473_90822_1675209979\",\"payout_amount\":1500,\"account_number\":\"917872408420\",\"split_payout_id\":\"SP8D235E9Q\",\"bank_transaction_id\":\"YESB30320290048\"}]";
	    // splittransactiondata=request.getSettled_transactions();
		// splitpayoutdata=request.getSplit_payouts();
		
		List<SettledTransactions> splitTransactionslist=new ArrayList<>();
		ObjectMapper objectMapper = new ObjectMapper(); 
		SplitPayout[] payouts;
		SettledTransactions[] transactions;
        
		try {
			payouts = objectMapper.readValue(splitpayoutdata, SplitPayout[].class);
			transactions = objectMapper.readValue(splittransactiondata, SettledTransactions[].class);

		} catch (JsonProcessingException e) {

			e.printStackTrace();
			payouts=null;
			transactions=null;
		}
		// splitTransactionslist=Arrays.asList(transactions);
		splitPayoutslist = Arrays.asList(payouts);
		
		
        for (SplitPayout payout : payouts) {
            payoutMap.put(payout.account_label, payout);
		}
		for(int i=0;i<transactions[0].getSplit_transactions().size();i++)
		{
			if(payoutMap.containsKey(transactions[0].getSplit_transactions().get(i).getAccount_label()))
			{
				 transactions[0].getSplit_transactions().get(i).setPaid_out(payoutMap.get(transactions[0].getSplit_transactions().get(i).getAccount_label()).getPaid_out());
				transactions[0].getSplit_transactions().get(i).setBank_name(payoutMap.get(transactions[0].getSplit_transactions().get(i).getAccount_label()).getBank_name());
				transactions[0].getSplit_transactions().get(i).setPayout_date(payoutMap.get(transactions[0].getSplit_transactions().get(i).getAccount_label()).getPayout_date());
				transactions[0].getSplit_transactions().get(i).setPayout_amount(payoutMap.get(transactions[0].getSplit_transactions().get(i).getAccount_label()).getPayout_amount());
				transactions[0].getSplit_transactions().get(i).setAccount_number(payoutMap.get(transactions[0].getSplit_transactions().get(i).getAccount_label()).getAccount_number());
				transactions[0].getSplit_transactions().get(i).setSplit_payout_id(payoutMap.get(transactions[0].getSplit_transactions().get(i).getAccount_label()).getSplit_payout_id());
				transactions[0].getSplit_transactions().get(i).setBank_transaction_id(payoutMap.get(transactions[0].getSplit_transactions().get(i).getAccount_label()).getBank_transaction_id());
				 payoutMap.remove(transactions[0].getSplit_transactions().get(i).getAccount_label());
			}
		

		}
		
		System.out.println(payoutMap);
	    
        // for (SettledTransactions transaction : transactions) {
        //     if (payoutMap.containsKey(transaction.getSplit_transactions().getAccount_label())) {
		// 		transaction.getSplit_transactions().setPaid_out((payoutMap.get(transaction.getSplit_transactions().getAccount_label())).getPaid_out());
        //         transaction.getSplit_transactions().setAccount_number((payoutMap.get(transaction.getSplit_transactions().getAccount_label())).getAccount_number());
		// 		transaction.getSplit_transactions().setBank_name((payoutMap.get(transaction.getSplit_transactions().getAccount_label())).getBank_name());
        //         transaction.getSplit_transactions().setBank_transaction_id((payoutMap.get(transaction.getSplit_transactions().getAccount_label())).getBank_transaction_id());
        //         transaction.getSplit_transactions().setPayout_amount((payoutMap.get(transaction.getSplit_transactions().getAccount_label())).getPayout_amount());
        //         transaction.getSplit_transactions().setPayout_date((payoutMap.get(transaction.getSplit_transactions().getAccount_label())).getPayout_date());
        //         transaction.getSplit_transactions().setSplit_payout_id((payoutMap.get(transaction.getSplit_transactions().getAccount_label())).getSplit_payout_id());
        //         transaction.getSplit_transactions().setPaid_out((payoutMap.get(transaction.getSplit_transactions().getAccount_label())).getPaid_out());
		// 		payoutMap.remove(transaction.getSplit_transactions().getAccount_label());
        //     }
		
        // }
		
		return transactions;
	}
	@GetMapping("/payouts")
	List<SplitPayout> payoutController(){

		List<SplitPayout> newList=new ArrayList<>();
		 
		newList.addAll(splitPayoutslist);
		int count=0;
		System.out.println(payoutMap);
		for(int i=0;i<splitPayoutslist.size();i++)
		{ if(payoutMap.containsKey(splitPayoutslist.get(i).getAccount_label())==false){
		   newList.remove(i-count);
		}
		}
		return newList;
	}
}

    

  