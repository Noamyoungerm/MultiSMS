package com.IASA.smsim;
import android.R.string;

public class Phone {
	private String phoneNumber;
	private PhoneType phoneType;
	
	public Phone(String phoneNum, PhoneType _phoneType){
		this.phoneNumber = phoneNum;
		this.phoneType = _phoneType;
	}
	
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	
	public void setPhoneNumber(String _phoneNumber) {
		this.phoneNumber = _phoneNumber;
	}
	
	public PhoneType getPhoneType() {
		return this.phoneType;
	}
	
	public void setPhoneType(PhoneType _phoneType){
		this.phoneType = _phoneType;
	}
	
}