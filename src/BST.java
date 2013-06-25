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
        System.out.println(size);
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
    /*public void remove(int key) {
        if (contains(key)) {
            root = remove(root, key);
            size--;
        }
    }

    private Node remove(Node x, int key) {
        if (!isEmpty()) {
            if (key < x.key) x.left = remove(x.left, key);
            else if (key > x.key) x.right = remove(x.right, key);
            else {
                if (x.left == null && x.right == null) {

                }








                if(x.left == null) {
                    if (x.right != null) {
                        // Replace with leftmost node in the right subtree
                        Node temp = min(x.right);

                        //Remove leftmost node in the right subtree
                        //remove(x.right, temp.key);



                        x.key = temp.key;
                        x.val = temp.val;
                    }
                } else {
                    //Replace with rightmost node in the left subtree
                    Node temp = max(x.left);

                    //Remove rightmost node in the left subtree
                    //remove(x.left, temp.key);
                    
                    x.key = temp.key;
                    x.val = temp.val;
                }
            }
        }
        return x;
    }*/


    public void remove(int key) {
        if (contains(key)) {
            root = remove(root, key);
            size--;
            System.out.println(size);
        }
    }

    private Node remove(Node x, int key) {
        if (x == null) return null;
        if (key < x.key) x.left = remove(x.left, key);
        else if (key > x.key) x.right = remove(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node temp = x;
            x = max(temp.left);
            x.right = temp.right;
            x.left = removeMax(temp.left);
        }
        //x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void removeMin() {
        root = removeMin(root);
    }

    private Node removeMin(Node x) {
        if (x.left == null) return x.right;
        x.left = removeMin(x.left);
        //x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void removeMax() {
        root = removeMax(root);
    }

    private Node removeMax(Node x) {
        if (x.right == null) return x.left;
        x.right = removeMax(x.right);
        //x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    private Node min(Node x) {
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }

    private Node max(Node x) {
        while (x.right != null) {
            x = x.right;
        }
        return x;
    }


    public PreorderIterator preorder() {
        return new PreorderIterator(root);
    }

    public LevelorderIterator levelorder() {
        return new LevelorderIterator(root);
    }
}
