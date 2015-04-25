package com.pfxiong.hub;

/**
 * –≈∫≈∑¢ÀÕ’ﬂ
 * @author bbfking
 *
 */
public class SignalSender {
	public final static String SINGAL_HELLO = "SINGAL_HELLO";
	SignalSender(){
		
	}
	public void openTheDoor(){
		System.out.println("sender : " + "HELLO");
		Hub.sender(this, SINGAL_HELLO, "HELLO");
		System.out.println("sender over");
	}
	public void sender(){
		openTheDoor();
	}
}
