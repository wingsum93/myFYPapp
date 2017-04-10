package com.ericho.myapi;

public class Web {
	public static String getAddress(){
		return address;
	}

	static private String address = "192.168.0.100:8080";
	
	public static void setAddress(String input){
		address=input;
	}
	// url to create new product
	public static final String url_create_product = "http://"+Web.getAddress()+"/fyp/create_product.php";
	public static final String url_all_products = "http://"+Web.getAddress()+"/fyp/get_all_products.php";
	public static final String url_product_detials = "http://"+Web.getAddress()+"/fyp/get_product_details.php";
	public static final String url_update_product = "http://"+Web.getAddress()+"/fyp/update_product.php";
	public static final String url_delete_product = "http://"+Web.getAddress()+"/fyp/delete_product.php";
	// url for fyp project
	public static final String url_get_farm_all_product = "http://"+Web.getAddress()+"/fyp/get_farm_all_product.php";
	// a url for sending vegetabkle order ... post fid, cid, total... + how many product
	public static final String url_receive_order = "http://"+Web.getAddress()+"/fyp/receive_order.php";
	public static final String url_get_all_farM = "http://"+Web.getAddress()+"/fyp/test.php";
	public static final String farm_admin = "http://"+Web.getAddress()+"/fyp/farm_admin.php";

	public static String getAddress222() {
		return "http://" + address + "/fyp/";
	}
}
