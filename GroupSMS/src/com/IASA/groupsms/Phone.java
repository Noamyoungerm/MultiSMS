package com.IASA.groupsms;
import android.R.string;

public class Phone {
	private string phoneNumber;
	private PhoneType phoneType;
	
	public Phone(string _phoneNumber, PhoneType _phoneType){
		this.phoneNumber = _phoneNumber;
		this.phoneType = _phoneType;
	}
	
	public string getPhoneNumber() {
		return this.phoneNumber;
	}
	
	public void setPhoneNumber(string _phoneNumber) {
		this.phoneNumber = _phoneNumber;
	}
	
	public PhoneType getPhoneType() {
		return this.phoneType;
	}
	
	public void setPhoneType(PhoneType _phoneType){
		this.phoneType = _phoneType;
	}
	
}
