public class AVLNode {
    private int key;
    private AVLNode left;
    private AVLNode right;
    private int height;

    public AVLNode(int key) {
        this.key = key;
        this.left = null;
        this.right = null;
        this.height = 1;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setLeft(AVLNode left) {
        this.left = left;
    }

    public void setRight(AVLNode right) {
        this.right = right;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getKey() {
        return key;
    }

    public AVLNode getLeft() {
        return this.left;
    }

    public AVLNode getRight() {
        return this.right;
    }

    public int getHeight() {
        return this.height;
    }
}