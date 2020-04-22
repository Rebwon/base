package ko.maeng.oop.ladder.domain;

import java.util.Objects;

public class Node {
	private enum Direction {
		LEFT,
		CENTER,
		RIGHT
	}

	private Direction direction;

	Node(Direction direction) {
		this.direction = direction;
	}

	void changeRight() {
		this.direction = Direction.RIGHT;
	}

	void changeLeft() {
		this.direction = Direction.LEFT;
	}

	Marker move(Marker marker) {
		if(isRightDirection()) {
			return marker.moveRight();
		}
		if(isLeftDirection()) {
			return marker.moveLeft();
		}
		return marker;
	}

	boolean isLeftDirection() {
		return this.direction == Direction.LEFT;
	}

	boolean isRightDirection() {
		return this.direction == Direction.RIGHT;
	}

	static Node createCenterNode() {
		return new Node(Direction.CENTER);
	}

	static Node createRightNode() {
		return new Node(Direction.RIGHT);
	}

	static Node createLeftNode() {
		return new Node(Direction.LEFT);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Node node = (Node)o;
		return direction == node.direction;
	}

	@Override
	public int hashCode() {
		return Objects.hash(direction);
	}
}
