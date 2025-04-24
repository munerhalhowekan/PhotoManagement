
public class BSTNode<T> {
    String key;
    T value;
    BSTNode<T> left, right;

    BSTNode(String key, T value) {
        this.key = key;
        this.value = value;
    }
}
