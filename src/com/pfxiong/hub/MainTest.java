package com.pfxiong.hub;

import java.util.function.Consumer;



public class MainTest {
	public static void main(String[] args) {
		//������
		SignalReceiver receiver = new SignalReceiver();
		//������
		SignalSender sender = new SignalSender();
		//��Ϣ����receiver��receiver����������sender��SINGAL_HELLO�ź�  (Integer x)->{receiver.receiver(x);}
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
