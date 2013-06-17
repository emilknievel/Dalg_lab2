public class BST {
    /* Root of BST */
    private Node root;
    /* Number of nodes in BST */
    private int size;

    public BST() {
        this.root = null;
        this.size = 0;
    }

    /**
     * Is the BST empty?
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Return root of BST
     */
    public Node getRoot() {
        return root;
    }

    /**
     * Return number of key-value pairs in BST
     */
    public int size() {
        return size;
    }

    /**
     * Does there exist a key-value pair with given key?
     */
    public boolean contains(int key) {
        return find(key) != null;
    }

    /**
     * Return value associated with the given key, or null if no such key exists
     */
    public String find(int key) {
        return find(root, key);
    }

    /**
     * Return value associated with the given key, or null if no such key exists
     * in subtree rooted at x
     */
    private String find(Node x, int key) {
        if (x == null) {
            return null;
        }
        if (key < x.key) {
            return find(x.left, key);
        } else if (key > x.key) {
            return find(x.right, key);
        } else {
            return x.val;
        }
    }

    /**
     * Insert key-value pair into BST. If key already exists, update with new
     * value
     */
    public void insert(int key, String val) {
        if (val == null) {
            remove(key);
            return;
        }

        root = insert(root, key, val);
        size++;
    }

    /**
     * Insert key-value pair into subtree rooted at x. If key already exists,
     * update with new value.
     */
    private Node insert(Node x, int key, String val) {
        if (x == null) {
            return new Node(key, val);
        }
        if (key < x.key) {
            x.left = insert(x.left, key, val);
        } else if (key > x.key) {
            x.right = insert(x.right, key, val);
        } else {
            x.val = val;
        }

        return x;
    }

    /**
     * Remove node with given key from BST
     */
    public void remove(int key) {
        if (!isEmpty()) {
            if (root.key == key) {
                if (root.left != null) {
                    Node temp = max(root.left);
                    remove(temp.key, root.left, root);
                    root.key = temp.key;
                    root.val = temp.val;
                } else if (root.right != null) {
                    Node temp = min(root.right);
                    remove(temp.key, root.right, root);
                    root.key = temp.key;
                    root.val = temp.val;
                } else {
                    root = null;
                    size--;
                }
            } else {
                remove(key, root, null);
            }
        }
    }

    /**
     * Tries to remove node.
     * Returns true if a node is removed, otherwise false.
     */
    private boolean remove(int key, Node child, Node parent) {
        if (key < child.key)
            return child.left != null && remove(key, child.left, child);
        else if (key > child.key)
            return child.right != null && remove(key, child.right, child);
        else {
            if (child.left != null && child.right != null) {
                child.key = max(child.left).key;
                child.val = max(child.left).val;
                remove(child.key, child.left, child);
            } else if (parent.left == child) {
                if (child.left != null) {
                    parent.left = child.left;
                } else {
                    parent.left = child.right;
                }
                size--;

            } else if (parent.right == child) {
                if (child.left != null) {
                    parent.right = child.left;
                } else {
                    parent.right = child.right;
                }
                size--;
            }
            return true;
        }
    }


    public PreorderIterator preorder() {
        return new PreorderIterator(root);
    }

    public LevelorderIterator levelorder() {
        return new LevelorderIterator(root);
    }


    // ===================================================================
    // Helper functions
    // ===================================================================

    private Node min(Node n) {
        if (n.left == null) return n;
        return min(n.left);
    }

    private Node max(Node n) {
        if (n.right == null) return n;
        return max(n.right);
    }
}
