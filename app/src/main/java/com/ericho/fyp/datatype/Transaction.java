package com.ericho.fyp.datatype;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Transaction {
	@SerializedName("date")
	public String transactionDate;
	@SerializedName("status")
	public String status;
	@SerializedName("total")
	public double total;
	@SerializedName("name")
	public String consumer;
	@SerializedName("detail")
	public ArrayList<TransactionDetail> theDetail;
	@SerializedName("tid")
	public int tid;
	@SerializedName("final_total")
	public Double final_total;
	
	public Transaction(){
		consumer="";
		total=0;
		status="";
		transactionDate= "";
		theDetail = new ArrayList<TransactionDetail>();
		tid=0;
		final_total=(double) 0;
	}
}
