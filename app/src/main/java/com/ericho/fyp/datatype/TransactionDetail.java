package com.ericho.fyp.datatype;

import com.google.gson.annotations.SerializedName;

public class TransactionDetail {
	@SerializedName("product_name")
	public String productName;
	@SerializedName("product_price")
	public int Price;
	@SerializedName("product_description")
	public String Description;
	@SerializedName("product_quantity")
	public double Quantity;
	@SerializedName("actual_quantity")
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
