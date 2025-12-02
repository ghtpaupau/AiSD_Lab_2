import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        /*
        BinarySearchTree bst = new BinarySearchTree();

        // Вставка элементов
        int[] elements = {50, 30, 70, 20, 40, 60, 80};
        System.out.print("Вставляем элементы: ");
        for (int elem : elements) {
            System.out.print(elem + " ");
            bst.insert(elem);
        }
        System.out.println("\n");

        // Обходы
        System.out.println("Прямой обход (Pre-order):");
        bst.directTraversal();

        System.out.println("Центрированный обход (In-order):");
        bst.centeredTraversal();

        System.out.println("Обратный обход (Post-order):");
        bst.reverseTraversal();

        System.out.println("Обход в ширину (Level-order):");
        bst.widthTraversal();

        // Поиск
        System.out.println("\nПоиск элементов:");
        System.out.println("Поиск 40: " + bst.search(40));
        System.out.println("Поиск 100: " + bst.search(100));

        // Минимум и максимум
        System.out.println("\nМинимальный элемент: " + bst.findMin());
        System.out.println("Максимальный элемент: " + bst.findMax());

        // Удаление
        System.out.println("\nУдаляем элемент 30");
        bst.delete(30);
        System.out.println("Центрированный обход после удаления:");
        bst.centeredTraversal();

        System.out.println("\nУдаляем элемент 50 (корень)");
        bst.delete(50);
        System.out.println("Центрированный обход после удаления корня:");
        bst.centeredTraversal();

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();


        RedBlackTree rbt = new RedBlackTree();
        // --- ВСТАВКА ---
        System.out.println("================================");
        rbt.insert(20);
        rbt.insert(40);
        System.out.println(rbt.findMax());

        rbt.delete(10);
        System.out.println("================================");

        System.out.println();
        System.out.println();
        System.out.println();
        int[] element = {50, 30, 70, 20, 40, 60, 80};
        System.out.print("Вставляем элементы: ");
        for (int elem : element) {
            System.out.print(elem + " ");
            rbt.insert(elem);
        }
        System.out.println("\n");

        // --- ОБХОДЫ ---
        System.out.println("Прямой обход (Pre-order):");
        rbt.directTraversal();

        System.out.println("Центрированный обход (In-order):");
        rbt.centeredTraversal();

        System.out.println("Обратный обход (Post-order):");
        rbt.reverseTraversal();

        System.out.println("Обход в ширину (Level-order):");
        rbt.widthTraversal();

        // --- ПОИСК ---
        System.out.println("\nПоиск элементов:");
        System.out.println("Поиск 40: " + (rbt.search(40)));
        System.out.println("Поиск 100: " + (rbt.search(100)));

        // --- МИНИМУМ И МАКСИМУМ ---
        System.out.println("\nМинимальный элемент: " + rbt.findMin());
        System.out.println("Максимальный элемент: " + rbt.findMax());

        // --- УДАЛЕНИЕ ---
        System.out.println("\nУдаляем элемент 30");
        rbt.delete(30);
        System.out.println("Центрированный обход после удаления 30:");
        rbt.centeredTraversal();

        System.out.println("\nУдаляем элемент 50 (корень)");
        rbt.delete(50);
        System.out.println("Центрированный обход после удаления корня 50:");
        rbt.centeredTraversal();

        // --- ДОПОЛНИТЕЛЬНЫЕ ПРОВЕРКИ ---
        System.out.println("\nУдаляем элемент 20 (лист)");
        rbt.delete(20);
        rbt.centeredTraversal();

        System.out.println("\nУдаляем элемент 70 (узел с одним/двумя детьми)");
        rbt.delete(70);
        rbt.centeredTraversal();

        System.out.println("\nИтоговый обход в ширину:");
        rbt.widthTraversal();

         */

        //для 4 задания
        /*
        Random random = new Random();
        //int counter = 0;
        for (int n = 1000; n < 31000; n += 1000) {
            long sumHeights = 0;
            for (int i = 0; i < 25; ++i) {
                BinarySearchTree bst = new BinarySearchTree();
                HashSet<Integer> set = new HashSet<>();
                while (set.size() < n) {
                    int key = random.nextInt(Integer.MAX_VALUE);
                    if (set.add(key)) {
                        bst.insert(key);
                    }
                }

                int height = bst.height();
                sumHeights += height;
            }
            System.out.println(n + ", " +  "average height = " + (1.0) * sumHeights / 25);
        }

         */

        //для 5 задания
        //AVL tree
        /*
        Random random = new Random();
        for (int n = 1000; n < 31000; n += 1000) {
            long sumHeights = 0;
            for (int i = 0; i < 25; ++i) {
                AVLTree avlTree = new AVLTree();
                HashSet<Integer> set = new HashSet<>();
                while (set.size() < n) {
                    int key = random.nextInt(Integer.MAX_VALUE);
                    if (set.add(key)) {
                        avlTree.insert(key);
                    }
                }

                int height = avlTree.height();
                sumHeights += height;
            }
            System.out.println(n + ", " +  "average height = " + (1.0) * sumHeights / 25);
        }

         */

        //RB tree
        /*
        Random random = new Random();
        for (int n = 1000; n < 31000; n += 1000) {
            long sumHeights = 0;
            for (int i = 0; i < 25; ++i) {
                RedBlackTree redBlackTree = new RedBlackTree();
                HashSet<Integer> set = new HashSet<>();
                while (set.size() < n) {
                    int key = random.nextInt(Integer.MAX_VALUE);
                    if (set.add(key)) {
                        redBlackTree.insert(key);
                    }
                }

                int height = redBlackTree.height();
                sumHeights += height;
            }
            System.out.println(n + ", " +  "average height = " + (1.0) * sumHeights / 25);
        }

         */

        //для 6 задания
        //AVL tree
        /*
        for (int n = 1000; n < 31000; n += 1000) {
            long sumHeights = 0;
            for (int i = 0; i < 25; ++i) {
                RedBlackTree redBlackTree = new RedBlackTree();

                for (int key = 0; key < n; ++key) {
                    redBlackTree.insert(key);
                }

                int height = redBlackTree.height();
                sumHeights += height;
            }
            System.out.println(n + ", " +  "average height = " + (1.0) * sumHeights / 25);
        }

         */

    }
}
