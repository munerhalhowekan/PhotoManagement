public class BST<K extends Comparable<K>, V> {
    private class Node {
        K key;
        V value;
        Node left, right;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = right = null;
        }
    }

    private Node root;

    public BST() {
        root = null;
    }

    public void insert(K key, V value) {
        root = insertRec(root, key, value);
    }

    private Node insertRec(Node root, K key, V value) {
        if (root == null) {
            return new Node(key, value);
        }
        int cmp = key.compareTo(root.key);
        if (cmp < 0) {
            root.left = insertRec(root.left, key, value);
        } else if (cmp > 0) {
            root.right = insertRec(root.right, key, value);
        } else {
            root.value = value;
        }
        return root;
    }

    public V search(K key) {
        Node node = searchRec(root, key);
        return node != null ? node.value : null;
    }

    private Node searchRec(Node root, K key) {
        if (root == null || root.key.equals(key)) {
            return root;
        }
        int cmp = key.compareTo(root.key);
        if (cmp < 0) {
            return searchRec(root.left, key);
        } else {
            return searchRec(root.right, key);
        }
    }

    public void delete(K key) {
        root = deleteRec(root, key);
    }

    private Node deleteRec(Node root, K key) {
        if (root == null) {
            return null;
        }
        int cmp = key.compareTo(root.key);
        if (cmp < 0) {
            root.left = deleteRec(root.left, key);
        } else if (cmp > 0) {
            root.right = deleteRec(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            Node minNode = findMin(root.right);
            root.key = minNode.key;
            root.value = minNode.value;
            root.right = deleteRec(root.right, minNode.key);
        }
        return root;
    }

    private Node findMin(Node root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }
}