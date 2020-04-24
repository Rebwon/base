package ko.maeng.oop.ladder.domain;

import java.util.Random;

import ko.maeng.oop.ladder.core.NatualNumber;

public class RandomLadderCreator {
	private static final double DEFAULT_LINE_RATIO = 0.3;
	private LadderSize ladderSize;
	private Row[] rows;

	RandomLadderCreator(LadderSize ladderSize) {
		NatualNumber height = ladderSize.getHeight();
		NatualNumber nthOfPerson = ladderSize.getNthOfPerson();
		this.ladderSize = ladderSize;

		rows = new Row[height.getNumber()];

		for(int i = 0; i<height.getNumber(); i++) {
			rows[i] = new Row(nthOfPerson);
		}
	}

	void drawLine(NatualNumber height, NatualNumber startPosition) {
		if(isOverHeight(height)) {
			throw new IllegalArgumentException();
		}
		rows[height.toArrayIndex()].drawLine(startPosition);
	}

	Row[] getLadder() {
		return this.rows;
	}

	Position[] toPositions(NatualNumber[] positions) {
		Position[] startPositions = new Position[positions.length];
		for (int i = 0; i < positions.length; i++) {
			startPositions[i] = ladderSize.getPosition(positions[i]);
		}
		return startPositions;
	}

	Position[] generateStartPositions() {
		NatualNumber[] numbers = generateRandomPositions();
		return toPositions(numbers);
	}

	NatualNumber randInt(int min, int max) {
		Random random = new Random();
		return new NatualNumber(random.nextInt((max - min) + 1) + 1);
	}

	private boolean isOverHeight(NatualNumber height) {
		return height.toArrayIndex() > rows.length - 1;
	}

	private NatualNumber[] generateRandomPositions() {
		NatualNumber totalPositions = ladderSize.getTotalPosition();
		int countOfLine = ladderSize.getCountOfLine(DEFAULT_LINE_RATIO);
		NatualNumber[] startPositions = new NatualNumber[countOfLine];
		for (int i = 0; i < startPositions.length; i++) {
			startPositions[i] = randInt(1, totalPositions.getNumber());
			System.out.println(String.format("random position : %s", startPositions[i]));
		}
		return startPositions;
	}
}
