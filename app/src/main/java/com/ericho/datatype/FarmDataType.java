package com.ericho.datatype;

public class FarmDataType{
	FarmDataType(String a,int img_res){
		this.name=a;
		this.pictureCode=img_res;
	}
	public int pictureCode;
	public String name;
	public String getName(){return this.name;}
	public int getPictureCode(){return this.pictureCode;}
}
