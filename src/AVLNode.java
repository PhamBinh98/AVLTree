public class AVLNode<T extends Comparable<T>> {
    T data;
    int height = 0;
    AVLNode<T> left = null;
    AVLNode<T> right = null;
    AVLNode(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "AVLNode(data=" + this.data + ")";
    }
}
