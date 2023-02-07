package com.codeTest.jsonSplit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SplitPayout {
	public double paid_out;
    public String bank_name;
    public String payout_date;
    public String account_label;
    public double payout_amount;
    public long account_number;
    public String split_payout_id;
    public String bank_transaction_id;

}