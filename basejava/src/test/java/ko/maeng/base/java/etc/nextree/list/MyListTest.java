package ko.maeng.base.java.etc.nextree.list;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MyListTest {
    private int[] arr = {66, 33, 99, 55, 88, 22, 24, 77};

    @Test
    public void arrayListTest() {
        MyArrayList arrayList = new MyArrayList(arr.length);

        for(int i=0; i<arr.length; i++){
            arrayList.add(arr[i]);
        }

        arrayList.add(18);
        assertThat(arrayList.length).isEqualTo(9);

        arrayList.add(3, 20);
        assertThat(arrayList.get(2)).isEqualTo(20);

        arrayList.remove();
        assertThat(arrayList.get(7)).isEqualTo(24);
    }

    @Test
    public void linkedListTest() {
        MyLinkedList linkedList = new MyLinkedList();
        Node node = null;

        for(int i=0; i<arr.length; i++){
            node = new Node();
            node.setData(arr[i]);
            linkedList.add(node);
        }

        assertThat(linkedList.getSize()).isEqualTo(8);

        linkedList.remove(3);
        linkedList.printList();
    }


}