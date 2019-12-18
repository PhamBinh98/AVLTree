import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class AvlTreeTest {
private Set<Integer> avlTree = new AvlTree<>();

private void insert() {
    avlTree.add(1);
    avlTree.add(2);
    avlTree.add(3);
    avlTree.add(4);
    avlTree.add(5);
    avlTree.add(6);
    avlTree.add(7);
    avlTree.add(8);
    avlTree.add(9);
    avlTree.add(10);
}
    @Test
    void contains() {
    insert();
    for (int i = 0; i < 10; i++)
        assertTrue(avlTree.contains(i + 1));
    }

    @Test
    void add() {
    insert();
    for (int i = 0; i < 10; i++)
        assertTrue(avlTree.contains(i + 1));
    }

    @Test
    void size() {
    assertEquals(0, avlTree.size());
    insert();
    assertEquals(10, avlTree.size());
    }

    @Test
    void isEmpty() {
    assertTrue(avlTree.isEmpty());
    insert();
    assertFalse(avlTree.isEmpty());
    }

    @Test
    void remove() {
    insert();
    avlTree.remove(5);
    avlTree.remove(7);
    assertFalse(avlTree.contains(5));
    assertFalse(avlTree.contains(7));
    }

    @Test
    void iterator() {
    Set<Integer> tree = new AvlTree<>();
    tree.add(5);
    tree.add(1);
    tree.add(2);
    tree.add(7);
    tree.add(9);
    tree.add(10);
    tree.add(8);
    tree.add(4);
    tree.add(3);
    tree.add(6);
        Iterator<Integer> it = tree.iterator();
        for (int i = 1; i < 11; i++) {
            assertEquals(i, (int) it.next());
        }
        Iterator<Integer> int2 = tree.iterator();
        while (int2.hasNext()) {
            int2.next();
            int2.remove();
        }
        assertEquals(0, tree.size());
    }

    @Test
    void toArray() {
    Set<String> tree = new AvlTree<>();
    tree.add("don");
    tree.add("doc");
    tree.add("se");
    tree.add("giup");
    tree.add("ban");
    tree.add("chin");
    tree.add("chan");
    tree.add("hon");
    tree.add("nghin");
    tree.add("lan");
    String[] strings = new String[10];
    strings[0] = "ban";
    strings[1] = "chan";
    strings[2] = "chin";
    strings[3] = "doc";
    strings[4] = "don";
    strings[5] = "giup";
    strings[6] = "hon";
    strings[7] = "lan";
    strings[8] = "nghin";
    strings[9] = "se";
    assertTrue(Arrays.equals(tree.toArray(), strings));
    }

    @Test
    void containsAll() {
    insert();
    List<Integer> list = new ArrayList<>();
    list.add(3);
    list.add(4);
    list.add(5);
    list.add(6);
    assertTrue(avlTree.containsAll(list));
    list.add(23);
    assertFalse(avlTree.containsAll(list));
    }

    @Test
    void addAll() {
    insert();
    List<Integer> list = new ArrayList<>();
    list.add(3);
    list.add(4);
    list.add(5);
    list.add(6);
    assertFalse(avlTree.addAll(list));
    list.add(12);
    list.add(13);
    list.add(14);
    list.add(15);
    assertTrue(avlTree.addAll(list));
    for (int i = 12; i < 16; i++) {
        assertTrue(avlTree.contains(i));
    }
}

    @Test
    void retainAll() {
        insert();
        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        avlTree.retainAll(list);
        assertTrue(avlTree.containsAll(list));
        assertFalse(avlTree.contains(1));
        assertFalse(avlTree.contains(2));
        assertFalse(avlTree.contains(7));
        assertFalse(avlTree.contains(8));
        assertFalse(avlTree.contains(9));
        assertFalse(avlTree.contains(10));

    }

    @Test
    void removeAll() {
    insert();
    List<Integer> list = new ArrayList<>();
    list.add(1);
    list.add(2);
    list.add(3);
    list.add(4);
    avlTree.removeAll(list);
    assertFalse(avlTree.containsAll(list ));
    }

    @Test
    void clear() {
    insert();
    avlTree.clear();
    assertTrue(avlTree.isEmpty());
    }

}