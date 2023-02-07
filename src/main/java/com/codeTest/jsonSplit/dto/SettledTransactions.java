package com.codeTest.jsonSplit.dto;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SettledTransactions {
   
    private String txnid;
    private String easepayid;
    private double service_tax;
    private double service_charge;
    private double submerchant_id;
    private String transaction_type;
    private double settlement_amount;
    @Autowired
    private List<Split_transactions> split_transactions;
    private double Transaction_amount;





}
