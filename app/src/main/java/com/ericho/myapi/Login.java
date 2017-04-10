package com.ericho.myapi;

import java.util.ArrayList;
import java.util.HashMap;

import com.ericho.datatype.User;

public class Login {
	private static HashMap<String, String> data = new HashMap<String, String>();
	static {
		
		data.put("admin","12345");
		data.put("peter","12345");
		data.put("farmer","12345");
		data.put("mary","12345");
	}
	public static boolean checkAuthentication(String id, String pw){
		boolean login_status=false;
		if(id.equals("peter")){
			User.cid=1;
		}else if(id.equals("mary")){
			User.cid=2;
		}
		String pp=data.get(id);
		if(pp!=null){
			if(pp.equals(pw)){
				login_status= true;
			}
		}
		
		return login_status;
	}
	public static boolean checkAuthentication(String id, String pw,String role){
		boolean login_status=false;
		String pp=data.get(id);
		if(pp!=null){
			if(pp.equals(pw)){
				login_status= true;
			}
		}
		
		return login_status;
	}
}
class Account{
	private String id,pw,role;
	Account(String a, String b){
		this.id=a;this.pw=b;
	}
	Account(String a, String b, String c){
		
	}
	public String getId(){return this.id;}
	public String getPw(){return this.pw;}
	public String getRole(){return this.role;}
}
class farmer extends Account{

	farmer(String a, String b) {
		super(a, b);
		
		
	}
	
}