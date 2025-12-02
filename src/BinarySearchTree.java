import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree {
    private Node root;

    public BinarySearchTree() {
        this.root = null;
    }

    public boolean search(int key) {
        return findNode(root, key);
    }

    private boolean findNode(Node node, int key) {
        if (node == null) {
            return false;
        }

        if (key == node.getKey()) {
            return true;
        } else if (key < node.getKey()) {
            return findNode(node.getLeft(), key);
        } else {
            return findNode(node.getRight(), key);
        }
    }

    public void insert(int key) {
        root = insertNode(root, key);
    }

    private Node insertNode(Node node, int key) {
        if (node == null) {
            return new Node(key);
        }

        if (key < node.getKey()) {
            node.setLeft(insertNode(node.getLeft(), key));
        } else if (key > node.getKey()) {
            node.setRight(insertNode(node.getRight(), key));
        }

        return node;
    }

    public void delete(int key) {
        root = deleteNode(root, key);
    }

    private Node deleteNode(Node node, int key) {
        if (node == null) {
            return null;
        }

        if (key < node.getKey()) {
            node.setLeft(deleteNode(node.getLeft(), key));
        } else if (key > node.getKey()) {
            node.setRight(deleteNode(node.getRight(), key));
        } else {
            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            }

            node.setKey(findMinKey(node.getRight()));
            node.setRight(deleteNode(node.getRight(), node.getKey()));
        }

        return node;
    }

    private int findMinKey(Node node) {
        int minValue = node.getKey();
        while (node.getLeft() != null) {
            minValue = node.getLeft().getKey();
            node = node.getLeft();
        }
        return minValue;
    }

    public int findMax() {
        if (root == null) {
            throw new IllegalStateException("Дерево пустое");
        }
        return findMaxNode(root);
    }

    private int findMaxNode(Node node) {
        if (node.getRight() == null) {
            return node.getKey();
        }
        return findMaxNode(node.getRight());
    }

    public int findMin() {
        if (root == null) {
            throw new IllegalStateException("Дерево пустое");
        }
        return findMinNode(root);
    }

    private int findMinNode(Node node) {
        if (node.getLeft() == null) {
            return node.getKey();
        }
        return findMinNode(node.getLeft());
    }

    //прямой
    public void directTraversal() {
        directNode(root);
        System.out.println();
    }

    private void directNode(Node node) {
        if (node != null) {
            System.out.print(node.getKey() + " ");
            directNode(node.getLeft());
            directNode(node.getRight());
        }
    }

    //центрированный
    public void centeredTraversal() {
        centeredNode(root);
        System.out.println();
    }

    private void centeredNode(Node node) {
        if (node != null) {
            centeredNode(node.getLeft());
            System.out.print(node.getKey() + " ");
            centeredNode(node.getRight());
        }
    }

    //обратный
    public void reverseTraversal() {
        reverseNode(this.root);
        System.out.println();
    }

    private void reverseNode(Node node) {
        if (node != null) {
            reverseNode(node.getLeft());
            reverseNode(node.getRight());
            System.out.print(node.getKey() + " ");
        }
    }

    //в ширину
    public void widthTraversal() {
        if (this.root == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(this.root);

        while (!queue.isEmpty()) {
            Node tmp = queue.poll();
            System.out.print(tmp.getKey() + " ");
            if (tmp.getLeft() != null) {
                queue.add(tmp.getLeft());
            }
            if (tmp.getRight() != null) {
                queue.add(tmp.getRight());
            }
        }
        System.out.println();
    }

    public int height() {
        return height(this.root);
    }

    private int height(Node node) {
        if (node == null) return 0;
        int hLeft = height(node.getLeft());
        int hRight = height(node.getRight());
        return 1 + Math.max(hLeft, hRight);
    }
}
