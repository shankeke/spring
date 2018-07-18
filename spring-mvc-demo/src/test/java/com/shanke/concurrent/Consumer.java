package com.shanke.concurrent;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

	@SuppressWarnings("rawtypes")
	protected BlockingQueue queue = null;

	@SuppressWarnings("rawtypes")
	public Consumer(BlockingQueue queue) {
		this.queue = queue;
	}

	public void run() {
		try {
			System.out.println(queue.take());
			System.out.println(queue.peek());
			System.out.println(queue.poll());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
