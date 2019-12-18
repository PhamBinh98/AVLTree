import java.security.AlgorithmConstraints;
import java.util.*;

public class AvlTree <T extends  Comparable<T>> implements Set<T> {
       private AVLNode<T> root = null;
       AVLNode<T> getRoot() {
        return root;
       }
     private int size = 0;

    @Override
    public boolean contains(Object o) {
        @SuppressWarnings("unchecked")
                T t = (T) o;
        AVLNode<T> closest = find(t);
        return closest != null && closest.data.compareTo(t) == 0;
    }
    AVLNode<T> find(T data) {
        if (root == null) return null;
        return findNode(root, data);
    }
    // Найти node, значение которого (data) равно Т или близко к Т
    private AVLNode<T> findNode(AVLNode<T> start, T data) {
        int comparison = data.compareTo(start.data);
        if (comparison == 0 ) {
            return start;
        }
        else if (comparison < 0) {
            if (start.left == null) return start;
            return findNode(start.left, data);
        }
        else {
            if (start.right == null) return start;
            return findNode(start.right, data);
        }
    }

    @Override
    public boolean add(T t) {
        if (contains(t)) return false;
        root = insertNode(root, t);
        size ++;
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean remove(Object o) {
        @SuppressWarnings("unchecked")
                T t = (T) o;
        if (!contains(o)) return false;
        root = removeNode(root, t);
        size--;
        return true;
    }
    // Добавлять node в дерево и осуществовать баланс
    private AVLNode<T> insertNode(AVLNode<T> currentNode, T dataToInsert) {
        if (currentNode == null) {
            return new AVLNode<>(dataToInsert);
        }
        if (dataToInsert.compareTo(currentNode.data) < 0) {
            currentNode.left = insertNode(currentNode.left, dataToInsert);
        } else {
            currentNode.right = insertNode(currentNode.right,dataToInsert);
        }
        currentNode = balanceTree(currentNode, dataToInsert); //осуществовать баланс
        currentNode.height = calculateTreeHeight(currentNode); // вычислить рост нового узла
        return currentNode;
    }

    private int height(AVLNode<T> currentNode) {
        if (currentNode == null) {
            return -1; // определение пустого роста
        }
        return currentNode.height;
    }

    //вычислить роста currentNode
    private  int calculateTreeHeight(AVLNode<T> currentNode) {
        return Math.max(height(currentNode.left),height(currentNode.right)) + 1;
    }

    // С помошью этого методы мы получим целое число, из которого мы узнали различие между сынами
    private int getBalanceValue(AVLNode<T> currentNode) {
        if (currentNode == null) {
            return 0;
        }
        return height(currentNode.left) - height(currentNode.right);
    }

    // проверка правой разбалансировки
    private boolean isRightHeavy(AVLNode<T> currentNode) {
        return getBalanceValue(currentNode) < -1;
    }
    // проверка левой разбалансировки
    private boolean isLeftHeavy(AVLNode<T> currentNode) {
        return getBalanceValue(currentNode) > 1;
    }

    // Расстановка балансов при добавления
     private AVLNode<T> balanceTree(AVLNode<T> currentNode, T dataToInsert) {
        // Тип разбалансировки - это right - right
         if (isRightHeavy(currentNode) && dataToInsert.compareTo(currentNode.right.data) > 0) {
             return leftRotate(currentNode);
         }
         // Тип разбалансировки - это left - left
         if (isLeftHeavy(currentNode) && dataToInsert.compareTo(currentNode.left.data) < 0) {
             return rightRotate(currentNode);
         }
         // Тип  разбалансировки - это right - left
         if (isRightHeavy(currentNode) && dataToInsert.compareTo(currentNode.right.data) < 0) {
             currentNode.right = rightRotate(currentNode.right);
             return leftRotate(currentNode);
         }
         // Тип  разбалансировки - это left - right
         if (isLeftHeavy(currentNode) && dataToInsert.compareTo(currentNode.left.data) > 0) {
             currentNode.left = leftRotate(currentNode.left);
             return rightRotate(currentNode);
         }
         return currentNode;
     }
     //Расстановка балансов при удалении
    private AVLNode<T> balanceTree(AVLNode<T> current) {
        int balance = getBalanceValue(current);
        // левая разбалансировка
        if (balance > 1) {
            if (getBalanceValue(current.left) < 0) {
                current.left = leftRotate(current.left);
            }
            return rightRotate(current);
        }
        //правая разбалансировка
        if (balance < -1) {
            if (getBalanceValue(current.right) > 0) {
                current.right = rightRotate(current.right);
            }
            return leftRotate(current);
        }
        return current;
    }
    //Метод правого поворота вокруг currentNode
    private AVLNode<T> rightRotate(AVLNode<T> currentNode) {
        AVLNode<T> newRootNode = currentNode.left;
       currentNode.left = newRootNode.right;
       newRootNode.right = currentNode;
       newRootNode.height = calculateTreeHeight(newRootNode);
       currentNode.height = calculateTreeHeight(currentNode);
       return newRootNode;
    }
    // Метод левого поворота вокруг currentNode
    private AVLNode<T> leftRotate(AVLNode<T> currentNode) {
        AVLNode<T> newRootNode = currentNode.right;
        currentNode.right = newRootNode.left;
        newRootNode.left = currentNode;
        newRootNode.height = calculateTreeHeight(newRootNode);
        currentNode.height = calculateTreeHeight(currentNode);
        return newRootNode;
    }
    private AVLNode<T> getMaxNode(AVLNode<T> currentNode) {
        while(currentNode.right != null) {
            currentNode = currentNode.right;
        }
        return currentNode;
    }
    private AVLNode<T> removeNode(AVLNode<T> current, T dataToRemove) {
        if (current == null) {
            return null;
        }
        AVLNode<T> leftChild = current.left;
        AVLNode<T> rightChild = current.right;
        if (dataToRemove.compareTo(current.data) == 0) {
            if (leftChild == null && rightChild == null) {
                return null;
            } else if (leftChild == null) {
                return rightChild;
            } else if (rightChild == null) {
                return leftChild;
            } else {
                AVLNode<T> successor = getMaxNode(leftChild);
                current.data = successor.data;
                current.left = removeNode(current.left, successor.data);
            }
        } else if (dataToRemove.compareTo(current.data) < 0) {
            current.left = removeNode(leftChild, dataToRemove);
        } else {
            current.right = removeNode(rightChild, dataToRemove);
        }
        current.height = calculateTreeHeight(current);
        return balanceTree(current);
    }

    public class AvlTreeIterator implements Iterator {
        private AVLNode<T> current = null;
        private int location = 0;
        private List<AVLNode<T>> list;
        private AvlTreeIterator() {
            list = new ArrayList<>();
            addToList(root);
        }
        private void addToList(AVLNode<T> node) {
            if (node != null) {
                addToList(node.left);
                list.add(node);
                addToList(node.right);
            }
        }
       private AVLNode<T> findNext() {
            return list.get(location++);
       }

        @Override
        public boolean hasNext() {
            return location <list.size();
        }

        @Override
        public Object next() {
            current = findNext();
            if (current == null) throw new NoSuchElementException();
            return current.data;
        }

        @Override
        public void remove() {
            AvlTree.this.remove(current.data);
            list.remove(current);
            location--;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new AvlTreeIterator();
            }

    @Override
    public Object[] toArray() {
        Object[] r = new Object[size()];
        Iterator<T> it = this.iterator();
        for (int i = 0; i < size(); i ++) {
            if (it.hasNext()) {
                r[i] = it.next();
            }
        }
        return r;
    }

    @SuppressWarnings("uncheked")
    @Override
    public <T1> T1[] toArray(T1[] a) {
        T1[] r = a.length >= size() ? a :
                (T1[]) java.lang.reflect.Array
                        .newInstance(a.getClass().getComponentType(), this.size());
        Iterator<T> it = iterator();
        for (int i = 0; i < r.length; i++){
            if (!it.hasNext()) {
                r[i] = null;
                return r;
            }
            r[i] = (T1) it.next();
        }
        return r;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for(Object e : c) {
            if (!contains(e))
                return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean b = false;
        for ( T e : c) {
            if (this.add(e)) b = true;
        }
        return b;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean b = false;
        Iterator<T> it = this.iterator();
        while (it.hasNext()) {
            if (!c.contains(it.next())) {
                it.remove();
                b = true;
            }
        }
        return b;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean b = false;
        for (Object e : c) {
            if (this.contains(e)) {
                this.remove(e);
                b = true;
            }
        }
        return b;
    }

    @Override
    public void clear() {
        Iterator<T> iterator = this.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj instanceof Set) {
            Set<?> o = (AvlTree<?>) obj;
            return o.size() == this.size() && o.containsAll(this) && this.containsAll(o);
        }
        return false;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "AVLTree is empty";
        } else {
            StringBuilder result = new StringBuilder("This is AVLTree. It contains elements:");
            for (T t : this){
                result.append(" ").append(t);
            }
            return result.toString();
        }
    }
}