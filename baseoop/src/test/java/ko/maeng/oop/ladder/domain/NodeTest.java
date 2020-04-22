package ko.maeng.oop.ladder.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NodeTest {
	@Test
	public void center() {
		Node node = Node.createCenterNode();
		assertEquals(Node.createCenterNode(), node);
	}

	@Test
	public void changeRight() {
		Node node = Node.createCenterNode();
		node.changeRight();
		assertEquals(Node.createRightNode(), node);
	}

	@Test
	public void changeLeft() {
		Node node = Node.createCenterNode();
		node.changeLeft();
		assertEquals(Node.createLeftNode(), node);
	}

	@Test
	public void moveRight() {
		Node node = Node.createRightNode();
		Marker marker = node.move(new Marker(3));
		assertEquals(new Marker(4), marker);
	}

	@Test
	public void moveLeft() {
		Node node = Node.createLeftNode();
		Marker marker = node.move(new Marker(3));
		assertEquals(new Marker(2), marker);
	}

	@Test
	public void moveCenter() {
		Node node = Node.createCenterNode();
		assertEquals(new Marker(3), node.move(new Marker(3)));
	}
}