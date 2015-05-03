package com.pfxiong.hub;

import java.util.function.Consumer;



public class MainTest {
	public static void main(String[] args) {
		//接收器
		SignalReceiver receiver = new SignalReceiver();
		//发送器
		SignalSender sender = new SignalSender();
		//消息订阅receiver的receiver方法，订阅sender的SINGAL_HELLO信号  (Integer x)->{receiver.receiver(x);}
		Runnable runnable = ()->{receiver.receiver();};
		Consumer<String> consumer1 = (x)->{receiver.receiver(x);};
		Consumer<Integer> consumer2 = (x)->{receiver.receiver(x);};
		Hub.subscribe(sender,SignalSender.SINGAL_HELLO,consumer1);
		Hub.subscribe(sender,SignalSender.SINGAL_HELLO,runnable);
		Hub.subscribe(sender,SignalSender.SINGAL_HELLO,consumer2);
		
		for(int i=0;i<10;i++){
			sender.sayHello(i);
			if(i == 7){
				Hub.removeSubscribe(sender,SignalSender.SINGAL_HELLO,consumer1);
				Hub.removeSubscribe(sender,SignalSender.SINGAL_HELLO,runnable);
				Hub.removeSubscribe(sender,SignalSender.SINGAL_HELLO,consumer2);
			}
		}
		
	}
}
