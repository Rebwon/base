package ko.maeng.base.java.ref;

import static org.junit.Assert.*;

import org.junit.Test;

public class SimpleCacheTest {

	@Test
	public void testCache() {
		String key = "Key";
		String value = "" + Math.random();

		SimpleCache cache = new SimpleCache();
		cache.put(key, value);

		value = null;

		assertNotNull(cache.get(key));
		System.gc();
		assertNull(cache.get(key));
	}
}