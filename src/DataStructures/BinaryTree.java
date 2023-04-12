package DataStructures;

import java.util.LinkedList;
import java.util.Queue;

class Node {
    int data;
    Node left;
    Node right;

    public Node(int data) {
        this.data = data;
    }
}

class BinaryTree {
    Node root;

    public void addNode(int data) {
        Node newNode = new Node(data);

        if (root == null) {
            root = newNode;
        } else {
            Node focusNode = root;
            Node parent;

            while (true) {
                parent = focusNode;

                if (data < focusNode.data) {
                    focusNode = focusNode.left;
                    if (focusNode == null) {
                        parent.left = newNode;
                        return;
                    }
                } else {
                    focusNode = focusNode.right;
                    if (focusNode == null) {
                        parent.right = newNode;
                        return;
                    }
                }
            }
        }
    }

    public void preOrderTraversal(Node focusNode) {
        if (focusNode != null) {
            System.out.print(focusNode.data + " ");
            preOrderTraversal(focusNode.left);
            preOrderTraversal(focusNode.right);
        }
    }
    public void inOrderTraversal(Node focusNode) {
        if (focusNode != null) {
            preOrderTraversal(focusNode.left);
            System.out.print(focusNode.data + " ");
            preOrderTraversal(focusNode.right);
        }
    }
    public void postOrderTraversal(Node focusNode) {
        if (focusNode != null) {
            preOrderTraversal(focusNode.left);
            preOrderTraversal(focusNode.right);
            System.out.print(focusNode.data + " ");
        }
    }

    int height(Node root)
    {
        if (root == null)
            return 0;
        else {
            /* compute  height of each subtree */
            int lheight = height(root.left);
            int rheight = height(root.right);

            /* use the larger one */
            if (lheight > rheight)
                return (lheight + 1);
            else
                return (rheight + 1);
        }
    }

    void levelOrderTraversal()
    {
        int h = height(root);
        int i;
        for (i = 1; i <= h; i++)
            printCurrentLevel(root, i);
    }

    void printCurrentLevel(Node root, int level)
    {
        if (root == null)
            return;
        if (level == 1)
            System.out.print(root.data + " ");
        else if (level > 1) {
            printCurrentLevel(root.left, level - 1);
            printCurrentLevel(root.right, level - 1);
        }
    }

    public static int heightByQueue(Node root)
    {
        int depth = 0;

        Queue<Node> q = new LinkedList<>();

        q.add(root);
        q.add(null);
        while (!q.isEmpty()) {
            Node temp = q.remove();

            if (temp == null) {
                depth++;
            }

            if (temp != null) {
                if (temp.left != null) {
                    q.add(temp.left);
                }
                if (temp.right != null) {
                    q.add(temp.right);
                }
            }else if (!q.isEmpty()) {
                q.add(null);
            }
        }
        return depth;
    }

    public static int heightByQueue2(Node root)
    {

        // Initialising a variable to count the
        // height of tree
        Queue<Node> q = new LinkedList<Node>();
        q.add(root);
        int height = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Node temp = q.poll();
                if (temp.left != null) {
                    q.add(temp.left);
                }
                if (temp.right != null) {
                    q.add(temp.right);
                }
            }
            height++;
        }
        return height;
    }

    public static int findHeight(Node root)
    {
        int height = 0;
        Node current = root;
        while (current != null) {
            if (current.left == null) {
                // If left subtree is null, move to right
                // subtree
                current = current.right;
                height++; // Increment the height of the
                // tree
            }
            else {
                // Find the inorder predecessor of current
                // node
                Node pre = current.left;
                while (pre.right != null
                        && pre.right != current)
                    pre = pre.right;

                if (pre.right == null) {
                    // Make current node the right child of
                    // its inorder predecessor
                    pre.right = current;
                    current = current.left;
                }
                else {
                    // If the right child of the inorder
                    // predecessor already points to the
                    // current node, then we have traversed
                    // the left subtree and its inorder
                    // traversal is complete.
                    pre.right = null;
                    current
                            = current.right; // Move to the
                    // right subtree
                }
            }
        }
        return height;
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        tree.addNode(50);
        tree.addNode(25);
        tree.addNode(75);
        tree.addNode(12);
        tree.addNode(37);
        tree.addNode(43);
        tree.addNode(30);

        tree.levelOrderTraversal();
    }
}