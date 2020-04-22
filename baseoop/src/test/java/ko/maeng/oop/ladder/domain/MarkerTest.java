package ko.maeng.oop.ladder.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MarkerTest {
	@Test
	public void moveRight() {
		Marker marker = new Marker(3);
		assertEquals(new Marker(4), marker.moveRight());
	}

	@Test
	public void moveLeft() {
		Marker marker = new Marker(3);
		assertEquals(new Marker(2), marker.moveLeft());
	}
}