package com.ericho.datatype;

import java.util.ArrayList;
import java.util.Date;

public class Transaction {
	public String transactionDate;
	public String status;
	public double total;
	public String consumer;
	public ArrayList<TransactionDetail> theDetail;
	public int tid;
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
