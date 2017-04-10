package com.ericho.fyp.datatype;

public class User {
	public static String user_name;
	public static String user_address;
	public static String user_contact_phone_number;
	public static int cid;
	static{
		user_name="";
		user_address="";
		user_contact_phone_number="";
		cid=0;
	}
	public static String getUserName(){
		
		return user_name;
	}
	public static void setUserName(String a){
		user_name=a;
	}
}
