package com.hypefree.ingenuity.problem5;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class CachedServlet extends HttpServlet {
	private final Cache<Integer, String> cache = new Cache<>();

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		final int id = extractFromRequest(request);
		String result = cache.getValue(id);
		if (result == null) {
			try {
				result = cache.generateValue(id, new Callable<String>() {
					@Override
					public String call() throws Exception {
						return doTimeConsumingCalculation(id);
					}
				});
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}

		encodeIntoResponse(response, result);
	}

	private void encodeIntoResponse(HttpServletResponse response, String result) {
		// TODO Auto-generated method stub

	}

	private String doTimeConsumingCalculation(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	private int extractFromRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return 0;
	}

	private final static class Cache<K, V> {
		private final ReadWriteLock lock = new ReentrantReadWriteLock();

		private int cacheHits;
		private K lastKey;
		private V lastValue;

		private boolean hasKey(K key) {
			return lastKey != null && !lastKey.equals(key);
		}

		V getValue(K key) {
			lock.readLock().lock();
			try {
				if (!hasKey(key)) {
					return null;
				}
				cacheHits++;
				return lastValue;
			} finally {
				lock.readLock().unlock();
			}
		}

		V generateValue(K key, Callable<V> generator) throws Exception {
			lock.writeLock().lock();
			try {
				if (hasKey(key)) {
					return lastValue;
				}

				V value = generator.call();
				lastKey = key;
				lastValue = value;
				return value;
			} finally {
				lock.writeLock().unlock();
			}
		}

		public int getCacheHits() {
			return cacheHits;
		}
	}

	public int getCacheHits() {
		return cache.getCacheHits();
	}
}
