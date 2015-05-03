package com.pfxiong.hub;

public class SignalReceiver {
	public SignalReceiver() {
	}

	public void receiver(String signal) {
		System.out.println("Receiver: " + signal);
	}
	public void receiver(Integer signal) {
		System.out.println("Receiver: " + (signal + 10));
	}
	
	public void receiver() {
		System.out.println("Receiver: runnable");
	}
}
