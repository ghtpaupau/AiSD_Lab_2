import java.util.LinkedList;
import java.util.Queue;

public class AVLTree {
    private AVLNode root;

    public AVLTree() {
        this.root = null;
    }

    public boolean search(int key) {
        return findNode(root, key);
    }

    private boolean findNode(AVLNode node, int key) {
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

    private AVLNode insertNode(AVLNode node, int key) {
        if (node == null) {
            return new AVLNode(key);
        }

        if (key < node.getKey()) {
            node.setLeft(insertNode(node.getLeft(), key));
        } else if (key > node.getKey()) {
            node.setRight(insertNode(node.getRight(), key));
        } else {
            return node;
        }

        updateHeight(node);
        return balance(node);
    }

    public void delete(int key) {
        root = deleteNode(root, key);
    }

    private AVLNode deleteNode(AVLNode node, int key) {
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
            } else {
                node.setKey(findMinKey(node.getRight()));
                node.setRight(deleteNode(node.getRight(), node.getKey()));
            }
        }

        updateHeight(node);
        return balance(node);
    }

    private AVLNode balance(AVLNode node) {
        int balanceFactor = getBalanceFactor(node);

        //Left Left
        if (balanceFactor > 1 && getBalanceFactor(node.getLeft()) >= 0) {
            return rightRotate(node);
        }

        //Left Right
        if (balanceFactor > 1 && getBalanceFactor(node.getLeft()) < 0) {
            node.setLeft(leftRotate(node.getLeft()));
            return rightRotate(node);
        }

        //Right Right
        if (balanceFactor < -1 && getBalanceFactor(node.getRight()) <= 0) {
            return leftRotate(node);
        }

        //Right Left
        if (balanceFactor < -1 && getBalanceFactor(node.getRight()) > 0) {
            node.setRight(rightRotate(node.getRight()));
            return leftRotate(node);
        }

        return node;
    }

    private AVLNode leftRotate(AVLNode node) {
        AVLNode tmp1 = node.getRight();
        AVLNode tmp2 = tmp1.getLeft();

        tmp1.setLeft(node);
        node.setRight(tmp2);

        updateHeight(node);
        updateHeight(tmp1);

        return tmp1;
    }

    private AVLNode rightRotate(AVLNode node) {
        AVLNode tmp1 = node.getLeft();
        AVLNode tmp2 = tmp1.getRight();

        tmp1.setRight(node);
        node.setLeft(tmp2);

        updateHeight(node);
        updateHeight(tmp1);

        return tmp1;
    }

    private int getHeight(AVLNode node) {
        return (node == null) ? 0 : node.getHeight();
    }

    private void updateHeight(AVLNode node) {
        if (node != null) {
            node.setHeight(1 + Math.max(getHeight(node.getLeft()), getHeight(node.getRight())));
        }
    }

    private int getBalanceFactor(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.getLeft()) - getHeight(node.getRight());
    }

    private int findMinKey(AVLNode node) {
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

    private int findMaxNode(AVLNode node) {
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

    private int findMinNode(AVLNode node) {
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

    private void directNode(AVLNode node) {
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

    private void centeredNode(AVLNode node) {
        if (node != null) {
            centeredNode(node.getLeft());
            System.out.print(node.getKey() + " ");
            centeredNode(node.getRight());
        }
    }

    //обратный
    public void reverseTraversal() {
        reverseNode(root);
        System.out.println();
    }

    private void reverseNode(AVLNode node) {
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

        Queue<AVLNode> queue = new LinkedList<>();
        queue.add(this.root);

        while (!queue.isEmpty()) {
            AVLNode tmp = queue.poll();
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

    private int height(AVLNode node) {
        if (node == null) return 0;
        int hLeft = height(node.getLeft());
        int hRight = height(node.getRight());
        return 1 + Math.max(hLeft, hRight);
    }
}