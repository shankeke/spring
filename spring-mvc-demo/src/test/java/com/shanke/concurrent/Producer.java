package com.shanke.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Producer implements Runnable {

	@SuppressWarnings("rawtypes")
	protected BlockingQueue queue = null;

	@SuppressWarnings("rawtypes")
	public Producer(BlockingQueue queue) {
		this.queue = queue;
	}

	@SuppressWarnings("unchecked")
	public void run() {
		try {
			queue.put("1");
			Thread.sleep(1000);
			queue.add("2");
			Thread.sleep(1000);
			queue.offer("3");
			Thread.sleep(1000);
			queue.offer("4", 10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
