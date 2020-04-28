package ko.maeng.oop.ladder.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import ko.maeng.oop.ladder.core.NatualNumber;

public class RandomLadderCreatorTest {
	@Test
	void generateStartPositions() {
		RandomLadderCreator creator = new RandomLadderCreator(LadderSize.create(3, 4));
		Position[] positions = creator.generateStartPositions();
		for (int i = 0; i < positions.length; i++) {
			System.out.println(String.format("position : %s", positions[i]));
		}
	}

	@Test
	void isExisted() {
		NatualNumber[] startPositions = {new NatualNumber(2), new NatualNumber(3)};
		assertTrue(RandomLadderCreator.isExisted(startPositions, new NatualNumber(2)));
		assertFalse(RandomLadderCreator.isExisted(startPositions, new NatualNumber(4)));
	}
}
