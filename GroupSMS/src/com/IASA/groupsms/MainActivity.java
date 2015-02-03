package com.IASA.groupsms;

import android.R.string;
import android.support.v7.app.ActionBarActivity;
import android.telephony.SmsManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;


public class MainActivity extends ActionBarActivity {

	Button send;
	EditText phoneNum, msg;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        msg = (EditText) findViewById(R.id.messageTV);
        phoneNum = (EditText) findViewById(R.id.phoneNumTV);
        send = (Button) findViewById(R.id.bSend);
        send.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				
				SmsManager smsManager = SmsManager.getDefault();
				smsManager.sendTextMessage(phoneNum.getText().toString(), null, msg.getText().toString(), null, null);
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
