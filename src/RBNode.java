public class RBNode {
    private Integer key;
    private Color color;
    private RBNode left;
    private RBNode right;
    private RBNode parent;

    public RBNode(Integer key) {
        this.key = key;
        this.color = Color.RED;
    }

    public RBNode(Integer key, Color color) {
        this.key = key;
        this.color = color;
    }

    public Integer getKey() {
        return this.key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public RBNode getLeft() {
        return this.left;
    }

    public void setLeft(RBNode left) {
        this.left = left;
    }

    public RBNode getRight() {
        return right;
    }

    public void setRight(RBNode right) {
        this.right = right;
    }

    public RBNode getParent() {
        return parent;
    }

    public void setParent(RBNode parent) {
        this.parent = parent;
    }
}