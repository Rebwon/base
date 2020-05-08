package ko.maeng.base.java.ref;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class SimpleCache {
	private Map map = new HashMap();

	public void put(String key, Object value) {
		map.put(key, new WeakReference<>(value));
	}

	public Object get(String key) {
		Object obj = map.get(key);
		return ((WeakReference) obj).get();
	}
}
