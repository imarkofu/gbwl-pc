package me.gbwl.pc.scheduler;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import me.gbwl.pc.util.DefaultSync;
import me.gbwl.pc.util.Sync;

import org.apache.log4j.Logger;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.scheduler.DuplicateRemovedScheduler;

public class MyScheduler extends DuplicateRemovedScheduler {
	
	private static final Logger logger = Logger.getLogger(MyScheduler.class);
	private String name;
//	public MyScheduler() {
//		super();
//	}
	public MyScheduler(String name) {
		super();
		this.name = name;
	}
	Sync mapSync = new DefaultSync();
	Map<Request, Task> map = new ConcurrentHashMap<Request, Task>();

	public Request poll(Task task) {
		try {
			mapSync.acquire();
			if ((this.map != null) && (this.map.size() > 0)) {
				Set<Request> set = this.map.keySet();
				Iterator<Request> iterator = set.iterator();
				if (iterator.hasNext()) {
					Request request = iterator.next();
					this.map.remove(request);
					logger.info("The remaining number of tasks " + name + this.map.size());
					return request;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			mapSync.release();
		}
		return null;
	}

	public void push(Request request, Task task) {
		pushWhenNoDuplicate(request, task);
	}

	protected void pushWhenNoDuplicate(Request request, Task task) {
		try {
			mapSync.acquire();
			if (map == null) map = new ConcurrentHashMap <Request, Task>();
			if (map.size() > 1000) map.clear();
			this.map.put(request, task);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			mapSync.release();
		}
	}
	
	public int size() {
		try {
			mapSync.acquire();
			if (map == null) map = new ConcurrentHashMap <Request, Task>();
			return this.map.size();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			mapSync.release();
		}
	}
}