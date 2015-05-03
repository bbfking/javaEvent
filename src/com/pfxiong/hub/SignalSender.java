package com.pfxiong.hub;

import java.util.function.Predicate;

/**
 * –≈∫≈∑¢ÀÕ’ﬂ
 * @author bbfking
 *
 */
public class SignalSender {
	public final static String SINGAL_HELLO = "SINGAL_HELLO";
	public final static Predicate<String> prd = (String x)->{return x.length() == 0;};
	SignalSender(){
		
	}
	public void sayHello(Integer i){
		System.out.println("sender : " + "HELLO"+i);
		Hub.sender(this, SINGAL_HELLO);
		Hub.sender(this, SINGAL_HELLO,"HELLO"+i);
		Hub.sender(this, SINGAL_HELLO,i);
	}
	public void senderSINGAL_HELLO(Integer i){
		sayHello(i);
	}
}
