package com.ericho.FinalYearProject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainScreenActivity extends Activity implements OnClickListener{
// a class old to show all product	
	Button btnViewProducts;
	Button btnNewProduct;
	Button btnChAd;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_screen);
		
		// Buttons
		btnViewProducts = (Button) findViewById(R.id.btnViewProducts);
		btnNewProduct = (Button) findViewById(R.id.btnCreateProduct);
		btnChAd = (Button) findViewById(R.id.btnChangeServerAd);
		// view products click event
		btnViewProducts.setOnClickListener(this);
		
		// new products click event
		btnNewProduct.setOnClickListener(this);
		
		// change server address click event
		btnChAd.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent i;
		switch(v.getId()){
		case R.id.btnViewProducts:
			i = new Intent(getApplicationContext(), AllProductsActivity.class);
			startActivity(i);
			break;
		case R.id.btnCreateProduct:
			i = new Intent(getApplicationContext(), NewProductActivity.class);
			startActivity(i);
			break;
		case R.id.btnChangeServerAd:
			i = new Intent(getApplicationContext(), ChangeAddressActivity.class);
			startActivity(i);
			break;
		}
	}
}
