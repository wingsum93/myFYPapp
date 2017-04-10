package com.ericho.datatype;

public class TransactionDetail {
	public String productName;
	public int Price;
	public String Description;
	public double Quantity;
	public double actual_quantity;
	public String unit;
	public TransactionDetail(){
		productName="";
		Price=0;
		Quantity=0;
		Description="";
		actual_quantity=0d;
		unit="";
	}
}
