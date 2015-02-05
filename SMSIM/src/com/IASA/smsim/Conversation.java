package com.IASA.smsim;

import com.IASA.smsim.MessageArrayAdapter;
import com.IASA.smsim.OneComment;
import com.IASA.smsim.Phone;
import com.IASA.smsim.PhoneType;

import android.support.v7.app.ActionBarActivity;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.ListView;

import java.util.*;
public class Conversation extends ActionBarActivity {

	public static ListView messagesLV;
	public static com.IASA.smsim.MessageArrayAdapter adapter;
	public static List<Phone> groupMembers;
	private EditText editMessageET;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// Setting up vars
		messagesLV = (ListView) findViewById(R.id.messagesLV);
		// The adapter (I think) feeds into the list view. When we edit the
		// adapter, we edit the list view (which is less dynamic)
		adapter = new MessageArrayAdapter(getApplicationContext(),
				R.layout.activity_list_item); // Creating adapter
		groupMembers = new ArrayList<Phone>();
		//groupMembers.add(new Phone("+972502573295", PhoneType.DUMBPHONE)); //Youngerman
		//groupMembers.add(new Phone("+972544875883", PhoneType.SMARTPHONE)); //Youngerman
		//groupMembers.add(new Phone("+972507754263", PhoneType.DUMBPHONE)); //Dani
		//groupMembers.add(new Phone("+972548015485", PhoneType.DUMBPHONE)); //Matan
		//groupMembers.add(new Phone("+972548340611", PhoneType.SMARTPHONE)); //Ofek
		//groupMembers.add(new Phone("+972538645645", PhoneType.SMARTPHONE)); //Tom
		//groupMembers.add(new Phone("+972547333072", PhoneType.SMARTPHONE)); //Lior
		messagesLV.setAdapter(adapter); // Linking adapter with List View
										// (forever - no need for repetitive
										// syncing)

		// Sending message on enter press
		editMessageET = (EditText) findViewById(R.id.editMessageET);
		editMessageET.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// If the event is a key-down event on the "enter" button
				if ((event.getAction() == KeyEvent.ACTION_DOWN)
						&& (keyCode == KeyEvent.KEYCODE_ENTER)) {
					// Perform action on key press
					send(editMessageET.getText().toString(), null);
					update(editMessageET.getText().toString(), false);
					editMessageET.setText("");
					return true;
				}
				return false;
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
	
	public static void send(String message, String num2Skip) {
		SmsManager smsManager = SmsManager.getDefault();
		for (Phone p : groupMembers) {
			if (p.getPhoneNumber() == num2Skip || p.getPhoneNumber() == "+972548015485" )
				continue;
			smsManager.sendTextMessage(p.getPhoneNumber(), null, message, null, null);
		}
	}
	
	public static void sendTo(String message, String number) {
		SmsManager smsManager = SmsManager.getDefault();
		smsManager.sendTextMessage(number, null, message, null, null);
	}
	
	public static void update(String str, boolean left) {
		adapter.add(new OneComment(left, str));
	}
}
