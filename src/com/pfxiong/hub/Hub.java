package com.pfxiong.hub;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.Consumer;

public class Hub{
	@SuppressWarnings("rawtypes")
	private static  Map<Object,Map<String,Set<Consumer>>> classMaps = new ConcurrentHashMap<>();
	private static Map<Object,Map<String,Set<Runnable>>> runnableMaps = new ConcurrentHashMap<>();
	/**
	 * @param <T>
	 * @param signalObj 被订阅的实体或对象
	 * @param consumer 订阅的方法
	 */
	@SuppressWarnings("rawtypes")
	public static <T> void subscribe(Object signalObj, String signal,Consumer<T> consumer){
		subscribe(signalObj,signal,consumer,classMaps);
	}
	/**
	 * @param signalObj 被订阅的实体或对象
	 * @param runnable 订阅的对象无参方法
	 */
	public static void subscribe(Object signalObj, String signal, Runnable runnable){
		subscribe(signalObj,signal,runnable,runnableMaps);
	}
	
	/**
	 * 写入订阅map
	 * @param signalObj
	 * @param signal
	 * @param method
	 * @param signalMap
	 */
	private static <T> void subscribe(Object signalObj, String signal,T method, Map<Object,Map<String,Set<T>>> signalMap){
		Map<String,Set<T>> map = signalMap.get(signalObj);
		if(map == null){
			map = new ConcurrentHashMap<>();
			signalMap.put(signalObj, map);
		}
		Set<T> set = map.get(signal);
		if(set == null){
			set = new CopyOnWriteArraySet<>();
			map.put(signal, set);
		}
		set.add(method);
	}
	/**
	 * 移除订阅有参方法
	 * @param <T>
	 * @param signalObj
	 * @param signal
	 * @param consumer
	 */
	public static <T> void removeSubscribe(Object signalObj, String signal,Consumer<T> consumer){
		removeSubscribe(signalObj,signal,consumer,classMaps);
	}
	/**
	 * 移除无参方法
	 * @param signalObj
	 * @param signal
	 * @param runnable
	 */
	public static void removeSubscribe(Object signalObj, String signal, Runnable runnable){
		removeSubscribe(signalObj,signal,runnable,runnableMaps);
	}
	private static <T> void removeSubscribe(Object signalObj, String signal,T method, Map<Object,Map<String,Set<T>>> signalMap){
		Map<String,Set<T>> map = signalMap.get(signalObj);
		if(map == null){
			return;
		}
		Set<T> set = map.get(signal);
		if(set == null || set.contains(method)){
			return;
		}
		set.remove(method);
		if(set.isEmpty()){
			map.remove(signal);
		}
		if(map.isEmpty()){
			signalMap.remove(signalObj);
		}
	}
	/**
	 * 发送信号
	 * @param obj 对象或者实例
	 * @param singal 信号的名称
	 * @param content 信号的内容
	 */
	@SuppressWarnings("rawtypes")
	public static void sender(Object obj, String signal, Object content) {
		Thread senderThread = new Thread(() -> {
			for (Object key : classMaps.keySet()) {
				if (key.equals(obj.getClass()) || key.equals(obj)) {
					Map<String, Set<Consumer>> value = classMaps.get(key);
					if (value == null)
						continue;
					Set<Consumer> consumers = value.get(signal);
					if (consumers == null)
						continue;
					for(Consumer consumer : consumers){
						Thread runThread = new Thread(() -> {
							try {
								consumer.accept(content);
							} catch (ClassCastException e) {
							}
						});
						runThread.start();
					}
				}
			}
		});
		senderThread.start();
	}
	/**
	 * 发送信号
	 * @param obj 对象或者实例
	 * @param singal 信号的名称
	 */
	public static void sender(Object obj, String singal){
		Thread senderThread = new Thread(()->{
			for(Object key : runnableMaps.keySet()){
				if(key.equals(obj.getClass()) || key.equals(obj)){
					Map<String,Set<Runnable>> value = runnableMaps.get(key);
					if(value == null) continue;
					Set<Runnable> runnables = value.get(singal);
					if(runnables == null) continue;
					for(Runnable runnable : runnables){
						Thread runThread = new Thread(runnable);
						runThread.start();
					}
				}
			}
		});
		senderThread.start();
	}
}
