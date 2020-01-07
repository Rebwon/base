package ko.maeng.base.java.tutorials.collection;

import ko.maeng.base.java8.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;

public class LinkedListTest {
    private LinkedList<Product> linkedList;

    @Before
    public void setup() {
        linkedList = new LinkedList<>();
        linkedList.add(new Product(20, "MacBook"));
        linkedList.add(new Product(10, "GalaxyS9"));
        linkedList.add(new Product(15, "iPhone11"));
    }

    @Test
    public void add() {
        assertThat(linkedList).hasSize(3);
    }

    @Test
    public void addFirst() {
        linkedList.addFirst(new Product(20, "iPhone7"));
        assertThat(linkedList).hasSize(4);
    }

    @Test
    public void addLast() {
        linkedList.addLast(new Product(20, "iPhone7"));
        assertThat(linkedList).hasSize(4);
    }

    @Test
    public void removeFirst() {
        linkedList.removeFirst();
        assertThat(linkedList.getFirst().getName()).isEqualTo("GalaxyS9");
    }

    @Test
    public void removeLast() {
        linkedList.removeLast();
        assertThat(linkedList.getLast().getName()).isEqualTo("GalaxyS9");
    }
}
