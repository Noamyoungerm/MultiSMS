package com.IASA.smsim;
import android.R.string;

public class Phone {
	private String phoneNumber, userName;
	private PhoneType phoneType;
	
	public Phone(String phoneNum, PhoneType _phoneType){
		this.phoneNumber = phoneNum;
		this.phoneType = _phoneType;
	}
	
	public Phone(String userName, String phoneNum, PhoneType _phoneType){
		this.phoneNumber = phoneNum;
		this.phoneType = _phoneType;
		this.userName = userName;
	}
	
	public Phone(){
		
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
	
	public String getUserName() {
		return this.userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
}