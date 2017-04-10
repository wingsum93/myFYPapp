package com.ericho.datatype;

import java.util.ArrayList;

public class TransactionOfFarm {
	public static ArrayList<Transaction> t;
	static{
		t = new ArrayList<Transaction>();
	}
	public static Transaction getTransactionByTID(int inputId){
		int whatPos = 0;
		for(int i=0;i<t.size();i++){
			if(t.get(i).tid==inputId){
				whatPos=i;
			}
		}
		return t.get(whatPos);
		
	}
	public static void flushTheTransaction(){
		t.clear();
	}
}
