
public class BST<T> {
    private BSTNode<T> root;

    public T search(String key) {
        BSTNode<T> node = searchNode(root, key);
        return node != null ? node.value : null;
    }

    private BSTNode<T> searchNode(BSTNode<T> node, String key) {
        if (node == null || node.key.equals(key)) return node;
        if (key.compareTo(node.key) < 0) return searchNode(node.left, key);
        return searchNode(node.right, key);
    }

    public void insert(String key, T value) {
        root = insertRec(root, key, value);
    }

    private BSTNode<T> insertRec(BSTNode<T> node, String key, T value) {
        if (node == null) return new BSTNode<>(key, value);
        if (key.compareTo(node.key) < 0) node.left = insertRec(node.left, key, value);
        else if (key.compareTo(node.key) > 0) node.right = insertRec(node.right, key, value);
        else node.value = value; // Update existing
        return node;
    }

    public void delete(String key) {
        root = deleteRec(root, key);
    }

    private BSTNode<T> deleteRec(BSTNode<T> root, String key) {
        if (root == null) return null;
        if (key.compareTo(root.key) < 0) root.left = deleteRec(root.left, key);
        else if (key.compareTo(root.key) > 0) root.right = deleteRec(root.right, key);
        else {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            BSTNode<T> temp = minValueNode(root.right);
            root.key = temp.key;
            root.value = temp.value;
            root.right = deleteRec(root.right, temp.key);
        }
        return root;
    }

    private BSTNode<T> minValueNode(BSTNode<T> node) {
        while (node.left != null) node = node.left;
        return node;
    }
}
