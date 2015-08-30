package com.example.myfirstapp;

import java.text.DecimalFormat;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.speech.tts.*;

public class DisplayMessageActivity extends Activity {

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        // Make sure we're running on Honeycomb or higher to use ActionBar APIs
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        
        Intent intent = getIntent();	//start app
        String from = intent.getStringExtra(MainActivity.fromVal);
        String to = intent.getStringExtra(MainActivity.toVal);
        String value = intent.getStringExtra(MainActivity.valueVal);
        
     // Create the text view
        TextView textView = new TextView(this);
        textView.setTextSize(40);

        String text = ("Conversion Complete. " + value + " " + from + " is equal to " + convert(from, to, value) + " " + to + ".");
        textView.setText(text);
        
        // Set the text view as the activity layout
        setContentView(textView);
    }
    
    public String convert(String from, String to, String value){
    	String conversion = "";
    	
    	//convert values to integers
    	double value1 = StringToDouble(value);
    	
    	//get conversion factor
    	double factor = getFactor(from,to);
    	
    	//send off to be converted
    	conversion = doConvert(value1, factor);
    	return conversion;
    }
    
    public double getFactor(String from, String to){
    	double factor = 0;
    	
    	//check factor to use
    	if (from.equals("USD") && to.equals("GBP")){
    		factor = 0.63;
    	} else
    	if (from.equals("GBP") && to.equals("USD")){
    		factor = 1.60;
    	} else
    	if (from.equals("EUR") && to.equals("USD")){
    		factor = 1.35;
    	} else
    	if (from.equals("USD") && to.equals("EUR")){
    		factor = 0.74;
    	} else
    	if (from.equals("EUR") && to.equals("GBP")){
    		factor = 0.84;
    	} else
    	if (from.equals("GBP") && to.equals("EUR")){
    		factor = 1.18;
    	} else {
    		Context context = getApplicationContext();
    		CharSequence text = "An error has occurred. Please check you entered a supported currency!";
    		int duration = Toast.LENGTH_LONG;
    		Toast toast = Toast.makeText(context, text, duration);
    		toast.show();
    	}
    	
    	return factor;
    }
    
    public String doConvert(double value, double factor){
    	String conversion = "";
    	double result = 0;
    	
    	result = factor * value;
    	DecimalFormat df = new DecimalFormat("#.##");
    	
    	//convert result to string and return
    	conversion = "" + df.format(result);
    	return conversion;
    }

    private double StringToDouble(String number){ 
        try{ 
           return Double.parseDouble(number.trim()); 
         } catch (Exception e){ 
           return 0;            
         }       
      } 
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
