package ko.maeng.oop.ladder.domain;

import ko.maeng.oop.ladder.core.NatualNumber;

public class LadderGame {
	private LadderCreator ladderCreator;

    LadderGame(NatualNumber height, NatualNumber nthOfPerson) {
        ladderCreator = new LadderCreator(height, nthOfPerson);
    }

    void drawLine(NatualNumber height, NatualNumber startPosition) {
        ladderCreator.drawLine(height, startPosition);
    }

    Marker run(Marker nthOfPerson) {
        LadderRunner ladderRunner = new LadderRunner(ladderCreator.getLadder());
        return ladderRunner.run(nthOfPerson);
    }
}
