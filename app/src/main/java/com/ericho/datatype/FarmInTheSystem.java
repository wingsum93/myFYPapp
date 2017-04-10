package com.ericho.datatype;

import java.util.ArrayList;

import com.ericho.FinalYearProject.R;

public class FarmInTheSystem {
	public static ArrayList<FarmDataType> farmlist = new ArrayList<FarmDataType>();
	static{
		
		farmlist.add(new FarmDataType("heart farm",R.drawable.healthy));
		farmlist.add(new FarmDataType("sweet farm",R.drawable.banana));
	}
	public static ArrayList<FarmDataType> getFarmList(){
		
		return farmlist;
	}
	public static FarmDataType getFarmObject(int input){
		return farmlist.get(input);
	}
}
