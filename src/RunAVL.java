public class RunAVL {

    public static void main(String[] args) {
        AvlTree<Integer> avlTree = new AvlTree<>();
        avlTree.add(40);
        avlTree.add(41);
        avlTree.add(42);
        avlTree.add(43);
        avlTree.add(44);
        avlTree.add(45);
        System.out.println(avlTree.toString());
    }
}
