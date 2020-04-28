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

	public static boolean isExisted(NatualNumber[] startPositions, NatualNumber randomPosition) {
		for(NatualNumber each : startPositions) {
			if(randomPosition.equals(each)) {
				return true;
			}
		}
		return false;
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

		int i = 0;
		do {
			NatualNumber randomPosition = randInt(1, totalPositions.getNumber());
			if(ladderSize.isMultipleOfPerson(randomPosition)) {
				continue;
			}
			if(isExisted(startPositions, randomPosition)) {
				continue;
			}
			if(isExisted(startPositions, new NatualNumber(randomPosition.getNumber() + 1))) {
				continue;
			}
			if(randomPosition.equals(new NatualNumber(1))) {
				startPositions[i] = randomPosition;
				System.out.println(String.format("random position : %s", startPositions[i]));
				i++;
			} else {
				if(isExisted(startPositions, new NatualNumber(randomPosition.toArrayIndex()))) {
					continue;
				}
				startPositions[i] = randomPosition;
				System.out.println(String.format("random position : %s", startPositions[i]));
				i++;
			}
		} while(i < countOfLine);

		return startPositions;
	}
}
