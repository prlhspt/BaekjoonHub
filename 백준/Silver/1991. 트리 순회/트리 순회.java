import java.util.Scanner;

public class Main {

    static class Node {
        private int key;
        Node left, right;

        public Node(int item) {
            this.key = item;
            left = right = null;
        }
    }

    static class binaryTree {
        Node root;

        public binaryTree() {
            root = null;
        }

        public void addNode(int key, int lKey, int rKey) {
            if (root == null) {
                root = new Node(key);

                if (lKey != -1) root.left = new Node(lKey);
                if (rKey != -1) root.right = new Node(rKey);

            } else {
                search(root, key, lKey, rKey);
            }
        }

        public void search(Node root, int key, int lKey, int rKey) {
            if (root == null) {
                return;
            } else if (root.key == key) {
                if (lKey != -1) root.left = new Node(lKey);
                if (rKey != -1) root.right = new Node(rKey);
            } else {
                search(root.left, key, lKey, rKey);
                search(root.right, key, lKey, rKey);
            }
        }

        void printPostorder(Node node) {
            if (node == null) {
                return;
            }
            printPostorder(node.left);
            printPostorder(node.right);
            System.out.print((char) (node.key + 'A' - 1));
        }

        void printInorder(Node node) {
            if (node == null) {
                return;
            }
            printInorder(node.left);
            System.out.print((char) (node.key + 'A' - 1));
            printInorder(node.right);
        }

        void printPreOrder(Node node) {
            if (node == null) {
                return;
            }
            System.out.print((char) (node.key + 'A' - 1));
            printPreOrder(node.left);
            printPreOrder(node.right);
        }

        void printPostorder() {
            printPostorder(root);
        }

        void printInorder() {
            printInorder(root);
        }

        void printPreorder() {
            printPreOrder(root);
        }
    }


    /*
7
A B C
B D .
C E F
E . .
F . G
D . .
G . .
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        binaryTree tree = new binaryTree();

        int n = sc.nextInt();
        sc.nextLine();

        int x;
        int y;
        int z;

        for (int a = 1; a < n; a++) {
            x = y = z = -1;

            String[] ss = sc.nextLine().split(" ");

            if (!ss[0].equals(".")) {
                x = (int) ss[0].charAt(0) - (int) 'A' + 1;
            }

            if (!ss[1].equals(".")) {
                y = (int) ss[1].charAt(0) - (int) 'A' + 1;
            }

            if (!ss[2].equals(".")) {
                z = (int) ss[2].charAt(0) - (int) 'A' + 1;
            }

            tree.addNode(x, y, z);
        }
        tree.printPreorder();
        System.out.println();
        tree.printInorder();
        System.out.println();
        tree.printPostorder();

    }
}
