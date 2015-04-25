package com.pfxiong.hub;

public class MainTest {
	public static void main(String[] args) {
		SignalReceiver receiver = new SignalReceiver();
		SignalSender sender = new SignalSender();
		Hub.subscribe(sender,SignalSender.SINGAL_HELLO,receiver::receiver);
		sender.openTheDoor();
	}
}
