package com.example.myfirstapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.os.Vibrator;
import android.content.*;

public class MainActivity extends Activity {

	public final static String fromVal = "com.example.myfirstapp.MESSAGEfrom";
	public final static String toVal = "com.example.myfirstapp.MESSAGEto";
	public final static String valueVal = "com.example.myfirstapp.MESSAGEval";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void sendMessage(View view){
		Intent intent = new Intent(this, DisplayMessageActivity.class);
		
		//get values for from, to and value
		EditText from = (EditText) findViewById(R.id.fromUnit);
		String msgFrom = from.getText().toString();
		intent.putExtra(fromVal, msgFrom);
		
		EditText to = (EditText) findViewById(R.id.toUnit);
		String msgTo = to.getText().toString();
		intent.putExtra(toVal, msgTo);
		
		EditText value = (EditText) findViewById(R.id.value);
		String msgVal = value.getText().toString();
		intent.putExtra(valueVal, msgVal);
		
		//Vibrator notify = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
		//long i = 100;
		//notify.vibrate(i);
		
		startActivity(intent);
	}
	

}
