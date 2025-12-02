import java.util.LinkedList;
import java.util.Queue;

public class RedBlackTree {
    private RBNode root;
    private final RBNode NIL;

    public RedBlackTree() {
        this.NIL = new RBNode(null, Color.BLACK);
        NIL.setLeft(NIL);
        NIL.setRight(NIL);
        NIL.setParent(NIL);
        this.root = NIL;
    }

    public void insert(int data) {
        RBNode newNode = new RBNode(data);
        newNode.setLeft(NIL);
        newNode.setRight(NIL);

        RBNode parent = null;
        RBNode current = this.root;

        while (current != NIL) {
            parent = current;
            if (newNode.getKey() < current.getKey()) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }

        newNode.setParent(parent);
        if (parent == null) {
            this.root = newNode;
        } else if (newNode.getKey() < parent.getKey()) {
            parent.setLeft(newNode);
        } else {
            parent.setRight(newNode);
        }

        fixInsert(newNode);
    }

    private void fixInsert(RBNode z) {
        //пока родитель красный
        while (z.getParent() != null && z.getParent().getColor() == Color.RED) {
            //если родитель - это левый ребёнок
            if (z.getParent() == z.getParent().getParent().getLeft()) {
                RBNode y = z.getParent().getParent().getRight();
                //если дядя красный
                //перекрашиваем родителя, дядю и дедушку
                //поднимаем z вверх
                if (y.getColor() == Color.RED) {
                    z.getParent().setColor(Color.BLACK);
                    y.setColor(Color.BLACK);
                    z.getParent().getParent().setColor(Color.RED);
                    z = z.getParent().getParent();
                } else {
                    //если дядя чёрный и z - это правый ребёнок
                    //производится левый поворот, чтобы дядя стал черным и z - это левый ребенок
                    if (z == z.getParent().getRight()) {
                        z = z.getParent();
                        rotateLeft(z);
                    }
                    //дядя чёрный и z - это левый ребёнок
                    //перекрашиваем родителя и дедушку
                    //производится правый поворот для балансировки
                    z.getParent().setColor(Color.BLACK);
                    z.getParent().getParent().setColor(Color.RED);
                    rotateRight(z.getParent().getParent());
                }
            } else {
                //дядя - это левый ребенок
                RBNode y = z.getParent().getParent().getLeft();
                //если дядя красный, то перекрашиваем
                if (y.getColor() == Color.RED) {
                    z.getParent().setColor(Color.BLACK);
                    y.setColor(Color.BLACK);
                    z.getParent().getParent().setColor(Color.RED);
                    z = z.getParent().getParent();
                } else {
                    //z - это левый ребёнок
                    if (z == z.getParent().getLeft()) {
                        z = z.getParent();
                        rotateRight(z);
                    }
                    //симметрично делаем левый поворот
                    z.getParent().setColor(Color.BLACK);
                    z.getParent().getParent().setColor(Color.RED);
                    rotateLeft(z.getParent().getParent());
                }
            }
        }
        //корень всегда черный
        this.root.setColor(Color.BLACK);
    }

    private void rotateLeft(RBNode x) {
        RBNode y = x.getRight();
        //перемещаем левое поддерево y направо от x
        x.setRight(y.getLeft());
        if (y.getLeft() != NIL) {
            y.getLeft().setParent(x);
        }
        //устанавливаем родителя y вместо x
        y.setParent(x.getParent());
        //перепривязываем y вместо x
        if (x.getParent() == null) {
            this.root = y;
        } else if (x == x.getParent().getLeft()) {
            x.getParent().setLeft(y);
        } else {
            x.getParent().setRight(y);
        }
        //x становится левым ребенком y
        y.setLeft(x);
        x.setParent(y);
    }

    private void rotateRight(RBNode y) {
        RBNode x = y.getLeft();
        y.setLeft(x.getRight());
        if (x.getRight() != NIL) {
            x.getRight().setParent(y);
        }
        x.setParent(y.getParent());
        if (y.getParent() == null) {
            this.root = x;
        } else if (y == y.getParent().getRight()) {
            y.getParent().setRight(x);
        } else {
            y.getParent().setLeft(x);
        }
        x.setRight(y);
        y.setParent(x);
    }

    private void transplant(RBNode u, RBNode v) {
        if (u.getParent() == null) {
            this.root = v;
        } else if (u == u.getParent().getLeft()) {
            u.getParent().setLeft(v);
        } else {
            u.getParent().setRight(v);
        }
        v.setParent(u.getParent());
    }

    public void delete(int data) {
        RBNode z = search(this.root, data);
        if (z == NIL) {
            System.out.println("Value not found in the tree.");
            return;
        }

        RBNode y = z;
        Color yOriginalColor = y.getColor();
        RBNode x;

        if (z.getLeft() == NIL) {
            x = z.getRight();
            transplant(z, z.getRight());
        } else if (z.getRight() == NIL) {
            x = z.getLeft();
            transplant(z, z.getLeft());
        } else {
            y = minimum(z.getRight());
            yOriginalColor = y.getColor();
            x = y.getRight();
            if (y.getParent() == z) {
                x.setParent(y);
            } else {
                transplant(y, y.getRight());
                y.setRight(z.getRight());
                y.getRight().setParent(y);
            }
            transplant(z, y);
            y.setLeft(z.getLeft());
            y.getLeft().setParent(y);
            y.setColor(z.getColor());
        }

        //если удалили чёрный узел, значит нарушается баланс чёрных высот, вызывается fixDelete(x)
        if (yOriginalColor == Color.BLACK) {
            fixDelete(x);
        }
    }

    //балансировка после удаления
    private void fixDelete(RBNode x) {
        //пока x чёрный и не является коренем
        while (x != this.root && x.getColor() == Color.BLACK) {
            if (x == x.getParent().getLeft()) {
                RBNode w = x.getParent().getRight();
                //если брат красный
                //перекрашиваем и выполняем поворот
                if (w.getColor() == Color.RED) {
                    w.setColor(Color.BLACK);
                    x.getParent().setColor(Color.RED);
                    rotateLeft(x.getParent());
                    w = x.getParent().getRight();
                }
                //если брат и оба его ребёнка чёрные
                if (w.getLeft().getColor() == Color.BLACK && w.getRight().getColor() == Color.BLACK) {
                    w.setColor(Color.RED);
                    x = x.getParent();
                } else {
                    //если брат чёрный, левый ребёнок красный, правый ребенок чёрный
                    if (w.getRight().getColor() == Color.BLACK) {
                        w.getLeft().setColor(Color.BLACK);
                        w.setColor(Color.RED);
                        rotateRight(w);
                        w = x.getParent().getRight();
                    }
                    //если брат чёрный, правый ребёнок красный
                    w.setColor(x.getParent().getColor());
                    x.getParent().setColor(Color.BLACK);
                    w.getRight().setColor(Color.BLACK);
                    rotateLeft(x.getParent());
                    x = this.root;
                }
            } else {
                //если брат - это левый ребёнок
                RBNode w = x.getParent().getLeft();
                if (w.getColor() == Color.RED) {
                    w.setColor(Color.BLACK);
                    x.getParent().setColor(Color.RED);
                    rotateRight(x.getParent());
                    w = x.getParent().getLeft();
                }
                if (w.getRight().getColor() == Color.BLACK && w.getLeft().getColor() == Color.BLACK) {
                    w.setColor(Color.RED);
                    x = x.getParent();
                } else {
                    if (w.getLeft().getColor() == Color.BLACK) {
                        w.getRight().setColor(Color.BLACK);
                        w.setColor(Color.RED);
                        rotateLeft(w);
                        w = x.getParent().getLeft();
                    }
                    w.setColor(x.getParent().getColor());
                    x.getParent().setColor(Color.BLACK);
                    w.getLeft().setColor(Color.BLACK);
                    rotateRight(x.getParent());
                    x = this.root;
                }
            }
        }
        //восстанавливаем, "корень чёрный"
        x.setColor(Color.BLACK);
    }

    private RBNode search(RBNode node, int key) {
        if (node == NIL || key == node.getKey()) {
            return node;
        }
        if (key < node.getKey()) {
            return search(node.getLeft(), key);
        }
        return search(node.getRight(), key);
    }

    public boolean search(int key) {
        return search(this.root, key) != NIL;
    }

    private RBNode minimum(RBNode node) {
        while (node.getLeft() != NIL) {
            node = node.getLeft();
        }
        return node;
    }

    public int findMax() {
        if (this.root == NIL) {
            throw new IllegalStateException("Дерево пустое");
        }
        return findMaxNode(this.root);
    }

    private int findMaxNode(RBNode node) {
        if (node.getRight() == NIL) {
            return node.getKey();
        }
        return findMaxNode(node.getRight());
    }

    public int findMin() {
        if (this.root == NIL) {
            throw new IllegalStateException("Дерево пустое");
        }
        return findMinNode(this.root);
    }

    private int findMinNode(RBNode node) {
        if (node.getLeft() == NIL) {
            return node.getKey();
        }
        return findMinNode(node.getLeft());
    }

    //прямой
    public void directTraversal() {
        directNode(root);
        System.out.println();
    }

    private void directNode(RBNode node) {
        if (node != NIL) {
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

    private void centeredNode(RBNode node) {
        if (node != NIL) {
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

    private void reverseNode(RBNode node) {
        if (node != NIL) {
            reverseNode(node.getLeft());
            reverseNode(node.getRight());
            System.out.print(node.getKey() + " ");
        }
    }

    //в ширину
    public void widthTraversal() {
        if (this.root == NIL) {
            return;
        }

        Queue<RBNode> queue = new LinkedList<>();
        queue.add(this.root);

        while (!queue.isEmpty()) {
            RBNode tmp = queue.poll();
            System.out.print(tmp.getKey() + " ");
            if (tmp.getLeft() != NIL) {
                queue.add(tmp.getLeft());
            }
            if (tmp.getRight() != NIL) {
                queue.add(tmp.getRight());
            }
        }
        System.out.println();
    }

    public int height() {
        return height(this.root);
    }

    private int height(RBNode node) {
        if (node == NIL) return 0;
        int hLeft = height(node.getLeft());
        int hRight = height(node.getRight());
        return 1 + Math.max(hLeft, hRight);
    }
}