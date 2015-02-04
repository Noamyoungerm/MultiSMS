package com.IASA.groupsms;

import java.util.ArrayList;
import java.util.List;

import com.IASA.groupsms.OneComment;
import com.IASA.groupsms.R;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MessageArrayAdapter extends ArrayAdapter<OneComment> {

	private TextView MessageEdit;
	private List<OneComment> messages = new ArrayList<OneComment>();
	private LinearLayout wrapper;

	@Override
	public void add(OneComment object) {
		messages.add(object);
		super.add(object);
	}

	public MessageArrayAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
	}

	public int getCount() {
		return this.messages.size();
	}
	//sync super
	public OneComment getItem(int index) {
		return this.messages.get(index);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		if (row == null) {
			LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.activity_list_item, parent, false);
		}

		wrapper = (LinearLayout) row.findViewById(R.id.wrapper);

		OneComment coment = getItem(position);

		MessageEdit = (TextView) row.findViewById(R.id.comment);

		MessageEdit.setText(coment.comment);

		MessageEdit.setBackgroundResource(coment.left ? R.drawable.bubble_yellow : R.drawable.bubble_green);
		wrapper.setGravity(coment.left ? Gravity.LEFT : Gravity.RIGHT);

		return row;
	}

	public Bitmap decodeToBitmap(byte[] decodedByte) {
		return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
	}

}
