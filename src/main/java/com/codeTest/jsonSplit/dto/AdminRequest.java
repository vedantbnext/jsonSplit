package com.codeTest.jsonSplit.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@JsonFormat(shape = JsonFormat.Shape.ARRAY)
public class AdminRequest {
   
    String split_payouts;
    String settled_transactions;
}
