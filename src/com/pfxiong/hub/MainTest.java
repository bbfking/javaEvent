package com.pfxiong.hub;


public class MainTest {
	public static void main(String[] args) {
		//������
		SignalReceiver receiver = new SignalReceiver();
		//������
		SignalSender sender = new SignalSender();
		//��Ϣ����receiver��receiver����������sender��SINGAL_HELLO�ź�  (Integer x)->{receiver.receiver(x);}
		Hub.subscribe(sender,SignalSender.SINGAL_HELLO, (String x)->{receiver.receiver(x);});
		sender.sayHello();
	}
}
