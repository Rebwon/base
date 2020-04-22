package ko.maeng.oop.ladder.domain;

import ko.maeng.oop.ladder.core.NatualNumber;

public class Row {
	private Node[] nodes;

	Row(NatualNumber noOfPerson) {
		nodes = new Node[noOfPerson.getNumber()];
		for(int i=0; i<nodes.length; i++){
			nodes[i] = Node.createCenterNode();
		}
	}

	void drawLine(NatualNumber startPosition){
		int startIndex = startPosition.toArrayIndex();
		if(isOverNoOfPersons(startIndex)) {
			throw new IllegalArgumentException(
				String.format("시작점은 %d보다 미만이어야 한다. 현재 값은 : %d", nodes.length - 1, startPosition));
		}

		if(nodes[startIndex].isLeftDirection()) {
			throw new IllegalArgumentException();
		}

		nodes[startIndex + 1].changeLeft();
		nodes[startIndex].changeRight();
	}

	private boolean isOverNoOfPersons(int startIndex) {
		return startIndex >= nodes.length - 1;
	}

	Marker move(Marker marker) {
		return nodes[marker.toArrayIndex()].move(marker);
	}

	public Node[] getNodes() {
		return nodes;
	}
}
