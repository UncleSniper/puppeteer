package org.unclesniper.puppeteer.util;

import java.util.Map;
import java.util.IdentityHashMap;

public final class Listeners<ListenerT, ErrorT extends Throwable> {

	public interface Action<ListenerT, ErrorT extends Throwable> {

		void fire(ListenerT listener) throws ErrorT;

	}

	private final Map<ListenerT, ListenerT> listeners = new IdentityHashMap<ListenerT, ListenerT>();

	private Map<ListenerT, ListenerT> cache;

	public Listeners() {}

	public Iterable<ListenerT> getListeners() {
		if(cache == null)
			cache = new IdentityHashMap<ListenerT, ListenerT>(listeners);
		return cache.keySet();
	}

	public boolean addListener(ListenerT listener) {
		if(listener == null)
			return false;
		if(listeners.containsKey(listener))
			return false;
		listeners.put(listener, listener);
		cache = null;
		return true;
	}

	public boolean removeListener(ListenerT listener) {
		if(listener == null)
			return false;
		if(listeners.remove(listener) == null)
			return false;
		cache = null;
		return true;
	}

	public void fire(Action<ListenerT, ErrorT> action) throws ErrorT {
		for(ListenerT listener : getListeners())
			action.fire(listener);
	}

}
