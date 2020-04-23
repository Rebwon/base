package ko.maeng.oop.ladder.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ko.maeng.oop.ladder.core.NatualNumber;

class LadderGameTest {

	@Test
	public void runWhenMultiRows() {
		// 1 -1 0 0
		// 0 1 -1 0
		// 0 0 1 -1
		LadderGame ladderGame = new LadderGame(new NatualNumber(3), new NatualNumber(4));
		ladderGame.drawLine(new NatualNumber(1), new NatualNumber(1));
		ladderGame.drawLine(new NatualNumber(2), new NatualNumber(2));
		ladderGame.drawLine(new NatualNumber(3), new NatualNumber(3));

		assertEquals(new Marker(4), ladderGame.run(new Marker(1)));
		assertEquals(new Marker(1), ladderGame.run(new Marker(2)));
		assertEquals(new Marker(2), ladderGame.run(new Marker(3)));
		assertEquals(new Marker(3), ladderGame.run(new Marker(4)));
	}
}