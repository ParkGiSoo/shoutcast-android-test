package com.cr5315.shoutcast;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	Button startButton, stopButton;
	static Context context;
	boolean isPlaying;
	Intent streamService;
	SharedPreferences prefs;
	//Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context = this;
		startButton = (Button) findViewById(R.id.startButton);
		stopButton = (Button) findViewById(R.id.stopButton);
		prefs = PreferenceManager.getDefaultSharedPreferences(context);
		getPrefs();
		streamService = new Intent(MainActivity.this, StreamService.class);		
		
		startButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startService(streamService);
				startButton.setEnabled(false);
			}
		});
		
		stopButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				stopService(streamService);
				startButton.setEnabled(true);
			}
		});
	}
		
	public void getPrefs() {
			isPlaying = prefs.getBoolean("isPlaying", false);
			if (isPlaying) startButton.setEnabled(false);
	}

	public void onButton1Clicked(View v){
		sendSMS("01043239873","출석체크 했음 메롱");
        //sendSMS("01064223613","출석체크 했음 메롱");
	}

	public void sendSMS(String phoneNum, String message) {

		SmsManager smsManager = SmsManager.getDefault();
		smsManager.sendTextMessage(phoneNum, null, message, null, null);

		Toast.makeText(this,"메시지보냄",Toast.LENGTH_LONG).show();
	}

	public void onButton2Clicked(View v){
		//Toast.makeText(this,"클릭됨",Toast.LENGTH_LONG).show();
		//TextView textView = (TextView) findViewById(R.id.editText);
		EditText et = (EditText)findViewById(R.id.editText);
		String et_text = et.getText().toString();
		//Toast.makeText(this,et_text,Toast.LENGTH_LONG).show();

		sendSMS_Text("01043239873",et_text);
		//sendSMS_Text("01064223613",et_text);
	}

	public void sendSMS_Text(String phoneNum, String message) {

		SmsManager smsManager = SmsManager.getDefault();
		smsManager.sendTextMessage(phoneNum, null, message, null, null);

		Toast.makeText(this,message,Toast.LENGTH_LONG).show();
	}


}
