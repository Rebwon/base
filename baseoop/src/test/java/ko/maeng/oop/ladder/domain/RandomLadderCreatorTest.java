package ko.maeng.oop.ladder.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class RandomLadderCreatorTest {
	@Test
	void generateStartPositions() {
		RandomLadderCreator creator = new RandomLadderCreator(LadderSize.create(3, 4));
		Position[] positions = creator.generateStartPositions();
		for (int i = 0; i < positions.length; i++) {
			System.out.println(String.format("position : %s", positions[i]));
		}
	}
}
