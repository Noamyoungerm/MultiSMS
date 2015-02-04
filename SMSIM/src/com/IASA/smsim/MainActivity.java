package com.IASA.smsim;

import com.IASA.smsim.MessageArrayAdapter;
import com.IASA.smsim.OneComment;
import com.IASA.smsim.Phone;
import com.IASA.smsim.PhoneType;

import android.support.v7.app.ActionBarActivity;
import android.telephony.SmsManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.ListView;

import java.util.*;
public class MainActivity extends ActionBarActivity {

	private com.IASA.smsim.MessageArrayAdapter adapter;
	private ListView messagesLV;
	private EditText editMessageET;
	private List<Phone> groupMembers;

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
		groupMembers.add(new Phone("+972-50-257-3295", PhoneType.DUMBPHONE));
		groupMembers.add(new Phone("+972-54-487-5883", PhoneType.SMARTPHONE));
		
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
					SmsManager smsManager = SmsManager.getDefault();
					for (Phone p : groupMembers) {
						smsManager.sendTextMessage(p.getPhoneNumber(), null, editMessageET.getText().toString(), null, null);
					}
					adapter.add(new OneComment(false, editMessageET.getText().toString()));
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
}
