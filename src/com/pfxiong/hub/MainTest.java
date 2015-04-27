package com.pfxiong.hub;


public class MainTest {
	public static void main(String[] args) {
		//接收器
		SignalReceiver receiver = new SignalReceiver();
		//发送器
		SignalSender sender = new SignalSender();
		//消息订阅receiver的receiver方法，订阅sender的SINGAL_HELLO信号  (Integer x)->{receiver.receiver(x);}
		Hub.subscribe(sender,SignalSender.SINGAL_HELLO, (String x)->{receiver.receiver(x);});
		sender.sayHello();
	}
}
