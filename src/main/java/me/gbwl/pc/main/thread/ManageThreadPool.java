package me.gbwl.pc.main.thread;

import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ManageThreadPool {
	private ThreadPoolExecutor pullPostDetailPool;

	private ManageThreadPool() {
		this.pullPostDetailPool = new ThreadPoolExecutor(1, 1, 1L,
				TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(),
				new SimpleThreadFactory("pullPostDetailPool"));
	}

	public static ManageThreadPool getInstance() {
		return ManageThreadPoolTool.manageThreadPool;
	}

	public Future<?> submitKVThread(Thread thread) {
		return this.pullPostDetailPool.submit(thread);
	}

	private static class ManageThreadPoolTool {
		private static ManageThreadPool manageThreadPool = new ManageThreadPool();
	}

	class SimpleThreadFactory implements ThreadFactory {
		private int count = 0;
		private String name;

		public SimpleThreadFactory(String name) {
			this.name = name;
		}

		public Thread newThread(Runnable r) {
			this.count += 1;
			if (this.count == 2147483647) {
				this.count = 1;
			}
			return new Thread(r, this.name + "-" + this.count);
		}
	}
}