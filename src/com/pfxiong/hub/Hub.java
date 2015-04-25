package com.pfxiong.hub;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public class Hub {
	private static Map<Object,Map<String,Consumer<String>>> classMaps = new ConcurrentHashMap<>();
	/**
	 * @param signalObj �����ĵ�ʵ������
	 * @param consumer ���ĵķ���
	 */
	public static void subscribe(Object signalObj, String signal,Consumer<String> consumer){
		Map<String,Consumer<String>> map = classMaps.get(signalObj);
		if(map == null){
			map = new ConcurrentHashMap<>();
			classMaps.put(signalObj, map);
		}
		map.put(signal, consumer);
	}
	/**
	 * �����ź�
	 * @param obj
	 * @param singal
	 * @param content
	 */
	public static void sender(Object obj, String singal,String content){
		Thread a = new Thread(()->{
			for(Object key : classMaps.keySet()){
				if(key.equals(obj.getClass()) || key.equals(obj)){
					Map<String,Consumer<String>> value = classMaps.get(key);
					if(value == null) continue;
					Consumer<String> consumer = value.get(singal);
					if(consumer == null) continue;
					Thread b = new Thread(()->{consumer.accept(content);});
					b.start();
				}
			}
		});
		a.start();
	}
}
