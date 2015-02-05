package com.IASA.smsim;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.SmsMessage;
import android.widget.*;
import android.os.Bundle;

public class ReceiveSMS extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();
		SmsMessage[] receivedMsgs = null;
		String str = "";
		if (bundle != null) {
			Object[] pdus = (Object[]) bundle.get("pdus");
			receivedMsgs = new SmsMessage[pdus.length];
			for (int i = 0; i < receivedMsgs.length; i++) {
				receivedMsgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
				str += receivedMsgs[i].getMessageBody().toString();
			}
			String str2 = receivedMsgs[0].getOriginatingAddress() + ":" + str;
			// Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
			// Conversation.update(str2, true);
			Phone sender = null;
			for (Phone p : Conversation.groupMembers) {
				if (p.getPhoneNumber() == receivedMsgs[0].getOriginatingAddress()) {
					sender = p;
					break;
				}
			}
			//if (sender != null) {
			//receivedMessage(str, sender);
			//}
			Conversation.send(str, receivedMsgs[0].getOriginatingAddress());
			Conversation.update(str, true);

		}
	}

	public void receivedMessage(String message, Phone sender) {
		if (message.charAt(0) == '!') {// start checking commands
			if (message.toLowerCase().startsWith("!list")) {
				// list
				// assumption: sent from nokia
				// return list
				String str = "";
				Phone temp = null;
				for (int i = 0; temp != null; i++) {
					temp = Conversation.groupMembers.get(i);
					str += temp.getUserName() + ":" + temp.getPhoneNumber()
							+ "\n";
				}
				Conversation.sendTo(str, sender.getPhoneNumber().toString());
			}
			/*
			 * else if ((message.length() >= 6) && (message[1] == 't' ||
			 * message[1] == 'T')) { if ((message[2] == 'o' || message[2] ==
			 * 'O') && (message[3] == 'p' || message[3] == 'P') && (message[4]
			 * == 'i' || message[4] == 'I') && (message[5] == 'c' || message[5]
			 * == 'C')) { // topic if (message.length == 6) { // return topic //
			 * assumption: sent from nokia // //send(conversation.topic,sender);
			 * } else { if (nokia) { // rebrodcast**** } // change topic //
			 * //conversation.topic=message.substring(6); } } else if
			 * ((message[2] == 'c' || message[2] == 'C') && (message[3] == 'o'
			 * || message[3] == 'O') && (message[4] == 'd' || message[4] == 'D')
			 * && (message[5] == 'e' || message[5] == 'E')) { // tcode if
			 * (message.length == 6) { // regular message**** } else if
			 * (message.length < 12) { // change shortcode**** //
			 * //conversation.shortcode=message.substring(6) //
			 * //conversation=conversations.first(); //
			 * //while(conversation!=null) // //{ // //
			 * if(conversation.shortcode==shortcode){ // //
			 * if(curconversation.shortcode==shortcode) // // break; // //
			 * rebrodcast curconversation.shortcode; // // } // //
			 * node=node.next; // //} // //if(node==null) // //{ // //
			 * curconversation.shortcode=shortcode; // // if(nokia){ // //
			 * rebrodcast; // // } // //} } else { // regular message**** } }
			 * else { // regular message**** } }
			 */else if (message.toLowerCase().startsWith("!add")) {
				if (sender.getPhoneType() == PhoneType.DUMBPHONE) {
					Conversation.send(message, sender.getPhoneNumber());
				}
				String[] s = message.substring(5).split(",");
				Phone p;
				if (s[2] == "y" || s[2] == "Y") {
					p = new Phone(s[0], s[1], PhoneType.SMARTPHONE);
				} else {
					p = new Phone(s[0], s[1], PhoneType.DUMBPHONE);
				}

				Conversation.groupMembers.add(p);
			} else if (message.toLowerCase().startsWith("!quit")) {
				// quit
				if (sender.getPhoneType() == PhoneType.DUMBPHONE) {
					Conversation.send(message, sender.getPhoneNumber());
				}
				Phone temp = null;
				for (int i = 0; temp != null; i++) {
					temp = Conversation.groupMembers.get(i);
					if (sender.getPhoneNumber() == temp.getPhoneNumber()) {
						Conversation.groupMembers.remove(i);
						break;
					}
				}
			} else if (message.toLowerCase().startsWith("!help")) {
				// help
				String str = "!list        Prints all the conversation contancts";
				str += "\n!add	(name,num,y\n) Adds contact to the conversation";
				str += "\n!quit	Quit conversation\n!help	Get all keywards";
				str += "\n!requestnum		(name) Returns phone number of applicable name";
				Conversation.sendTo(str, sender.getPhoneNumber());
			} else if (message.toLowerCase().startsWith("!requestnum")) {
				Phone temp = null;
				for (int i = 0; temp != null; i++) {
					temp = Conversation.groupMembers.get(i);
					if (temp.getUserName() == message.substring(12)) {
						Conversation.sendTo(
								temp.getUserName() + ": "
										+ temp.getPhoneNumber(),
								sender.getPhoneNumber());
						break;
					}
				}
				if (temp == null) {
					Conversation.sendTo("No user in this group with that name",
							sender.getPhoneNumber());
				}
				// requestnum
				// assumption: sent from nokia
				// send back num if user exists
				// else regular message
				// //node=list.first();
				// //while(node!=null)
				// //{
				// // if(node.name==message.substring(11)){
				// // return node.num;
				// // break;
				// // }
				// // node=node.next;
				// //}
				// //if(node==null)
				// //regular message****
			}/*
			 * else if ((message.length > 11) && (message[1] == 'c' ||
			 * message[1] == 'C') && (message[2] == 'h' || message[2] == 'H') &&
			 * (message[3] == 'a' || message[3] == 'A') && (message[4] == 'n' ||
			 * message[4] == 'N') && (message[5] == 'g' || message[5] == 'G') &&
			 * (message[6] == 'e' || message[6] == 'E') && (message[7] == 'n' ||
			 * message[7] == 'N') && (message[8] == 'a' || message[8] == 'A') &&
			 * (message[9] == 'm' || message[9] == 'M') && (message[10] == 'e'
			 * || message[10] == 'E')) { // changename if (nokia) { //
			 * rebrodcast**** } // change user's name // //node=list.first(); //
			 * //while(node!=null) // //{ // // if(node.info==sender) // //
			 * node.name=name; // // node=node.next; // //} }
			 */
			else {
				Conversation.send(message, sender.getPhoneNumber());
				Conversation.update(message, true);
			}
		} else {
			Conversation.send(message, sender.getPhoneNumber());
			Conversation.update(message, true);
		}
	}

	private void sendTo(String str, String string) {
		// TODO Auto-generated method stub

	}
}