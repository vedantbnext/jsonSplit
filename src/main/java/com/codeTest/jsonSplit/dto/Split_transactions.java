package com.codeTest.jsonSplit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Split_transactions {
     public String account_label;
    public double amount;
    public double service_tax;
    public double service_charge;
    public double split_payout_percentage;
    public double paid_out;
    public String bank_name;
    public String payout_date;
    public double payout_amount;
    public long account_number;
    public String split_payout_id;
    public String bank_transaction_id;

}
