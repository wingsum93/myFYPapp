package com.ericho.datatype;

import java.util.ArrayList;

public class Farm {
	public static String farmNmae;
	public static String paymentAccountNumber;
	public static String address;
	public static String farmCode;
	//List product
	public static ArrayList<FarmVegetable> myFarmProduct;
	public static void clearVegetableProducts(){
		myFarmProduct.clear();
	}
	static{
		farmNmae="";
		address="";
		farmCode="";
		myFarmProduct=new ArrayList<FarmVegetable>();
	}
	public static void setAllQuantityToZero() {
		for(int i=0;i < myFarmProduct.size();i++){
			myFarmProduct.get(i).setQuantity(0);
		}
		
		
	}
	public static String getVegetableUnitByPId(int inp){
		for(int i=0;i < myFarmProduct.size();i++){
			if(Integer.parseInt(myFarmProduct.get(i).getVegetableID()) == inp ){
				return myFarmProduct.get(i).getVegetableUnit();
			};
			
		}
		return"";
	}
}
