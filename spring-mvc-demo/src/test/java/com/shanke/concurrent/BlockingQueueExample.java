package com.shanke.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.junit.Test;

public class BlockingQueueExample {
	@SuppressWarnings("rawtypes")
	@Test
	public void test() throws InterruptedException {
		BlockingQueue queue = new ArrayBlockingQueue(1024);

		Producer producer = new Producer(queue);
		Consumer consumer = new Consumer(queue);

		new Thread(producer).start();
		new Thread(consumer).start();

		Thread.sleep(4000);
	}

	@Test
	public void test1() throws InterruptedException {
		BlockingQueue<String> queue = new ArrayBlockingQueue<String>(2);
		queue.put("1");
		queue.offer("2");
		boolean offer = queue.offer("3");
		System.out.println(offer);
	}
}
