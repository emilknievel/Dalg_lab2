import java.util.Iterator;

public class LevelorderIterator implements Iterator<Node> {
    protected Queue<Node> q;

    public LevelorderIterator(Node tree) {
	    q = new Queue<Node>();

        if (tree != null) {
            q.enqueue(tree);
        }
    }

    public void remove() {
        // Leave empty
    }

    public Node next() {
        Node temp = q.dequeue();

        if (temp.left != null) {
            q.enqueue(temp.left);
        }
        if (temp.right != null) {
            q.enqueue(temp.right);
        }

        return temp;
    }

    public boolean hasNext() {
        return !q.isEmpty();
    }
}
