package com.pfxiong.hub;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public class Hub{
	private static  Map<Object,Map<String,Consumer<?>>> classMaps = new ConcurrentHashMap<>();
	private static Map<Object,Map<String,Runnable>> runnableMaps = new ConcurrentHashMap<>();
	/**
	 * @param signalObj �����ĵ�ʵ������
	 * @param consumer ���ĵķ���
	 */
	public static void subscribe(Object signalObj, String signal,Consumer<?> consumer){
		Map<String,Consumer<?>> map = classMaps.get(signalObj);
		if(map == null){
			map = new ConcurrentHashMap<>();
			classMaps.put(signalObj, map);
		}
		map.put(signal, consumer);
	}
	/**
	 * @param signalObj �����ĵ�ʵ������
	 * @param runnable ���ĵĶ����޲η���
	 */
	public static void subscribe(Object signalObj, String signal, Runnable runnable){
		Map<String,Runnable> map = runnableMaps.get(signalObj);
		if(map == null){
			map = new ConcurrentHashMap<>();
			runnableMaps.put(signalObj, map);
		}
		map.put(signal, runnable);
	}
	/**
	 * �����ź�
	 * @param obj �������ʵ��
	 * @param singal �źŵ�����
	 * @param content �źŵ�����
	 */
	public static void sender(Object obj, String signal,Object content){
		Thread a = new Thread(()->{
			for(Object key : classMaps.keySet()){
				if(key.equals(obj.getClass()) || key.equals(obj)){
					Map<String,Consumer<?>> value = classMaps.get(key);
					if(value == null) continue;
					Consumer<?> consumer = value.get(signal);
					if(consumer == null) continue;
					try {
						Consumer consumerObject = (Consumer)consumer; 
						Thread b = new Thread(()->{consumerObject.accept(content);});
						b.start();
					} catch (ClassCastException e) {
						continue;
					}
				}
			}
		});
		a.start();
	}
	/**
	 * �����ź�
	 * @param obj �������ʵ��
	 * @param singal �źŵ�����
	 */
	public static void sender(Object obj, String singal){
		Thread a = new Thread(()->{
			for(Object key : classMaps.keySet()){
				if(key.equals(obj.getClass()) || key.equals(obj)){
					Map<String,Runnable> value = runnableMaps.get(key);
					if(value == null) continue;
					Runnable runnable = value.get(singal);
					if(runnable == null) continue;
					Thread b = new Thread(runnable);
					b.start();
				}
			}
		});
		a.start();
	}
}
