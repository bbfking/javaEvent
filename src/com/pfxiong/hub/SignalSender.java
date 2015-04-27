package com.pfxiong.hub;

import java.util.function.Predicate;

/**
 * –≈∫≈∑¢ÀÕ’ﬂ
 * @author bbfking
 *
 */
public class SignalSender {
	public final static String SINGAL_HELLO = "SINGAL_HELLO";
	public final static String SINGAL_RUNNABLE = "SINGAL_RUNNABLE";
	public final static Predicate<String> prd = (String x)->{return x.length() == 0;};
	SignalSender(){
		
	}
	public void sayHello(){
		System.out.println("sender : " + "HELLO");
		Hub.sender(this, SINGAL_HELLO, "HELLO");
		System.out.println("sender over");
	}
	public void sender(){
		sayHello();
	}
}
