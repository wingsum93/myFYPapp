package com.ericho.datatype;

public class FarmVegetable {
	public int vegetablePictureCode;
	public String vegetableName;
	public int vegetablePrice;
	public String vegetableID;
	public String vegetableDescription;
	public String VegetableUnit;
	public double quantity;
	public int img_int;
	public FarmVegetable(){
		this.vegetableName="";
		this.vegetablePrice=0;
		this.vegetableID="";
		this.quantity=0;
		this.vegetablePictureCode=0;
		this.VegetableUnit="";
		this.img_int=0;
	}
	
	public String getVegetableName(){
		return this.vegetableName;
	}
	public void setVegetableName(String pri){
		this.vegetableName=pri;
	}
	public int getVegetablePrice(){
		return this.vegetablePrice;
	}
	public void setVegetablePrice(int pri){
		this.vegetablePrice=pri;
	}
	public int getVegetblePictureCode(){// this method is not use
		return this.vegetablePictureCode;
	}
	public double getQuantity(){
		return this.quantity;
	}
	public void setQuantity(double i){
		this.quantity = i;
	}
	public void setQuantity(String string){
		this.quantity = Integer.parseInt(string);
	}
	public void setVegetableID(String pri){
		this.vegetableID=pri;
	}
	public String getVegetableID(){
		return this.vegetableID;
	}
	public void setVegetableUnit(String string) {
		this.VegetableUnit = string;
	}
	public String getVegetableUnit() {
		return this.VegetableUnit;
	}
	public int getImageCode(){
		return this.img_int;
	}
	public void setImageCode(int ss){
	 this.img_int = ss;
	}
}
