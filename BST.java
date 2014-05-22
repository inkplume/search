/*************************************************
 * This is a recursive edition
 *************************************************/
public class BST<Key extends Comparable<Key>, Value>
{
	/* INSTANCE VARIABLES */
	private Node root;
	private class Node
	{
		private Key key;
		private Value val;
		private Node left, right;
		private int N;

		public Node(Key key, Value val, int N)
		{ this.key = key; this.val = val; this.N = N; }
	}

	/* CONSTRUCTORS */
	public BST() {}

	/* INSTANCE METHODS */	
	public void put(Key key, Value val) { root = put(root, key, val); }
	public Value get(Key key) { return get(root, key); }
	public void delete(Key key) { root = delete(root, key); }
	public boolean contains(Key key) { return contains(root, key); }
	public boolean isEmpty() { return size() == 0;}
	public int size() { return size(root); }
	public Key min() { return min(root).key; }
	public Key max() { return max(root).key; }
	public Key floor(Key key)
	{
		Node x = floor(root, key);
		if (x == null) return null;
		return x.key;
	}
	public Key ceiling(Key key)
	{
		Node x = ceiling(root, key);
		if (x == null) return null;
		return x.key;
	}
	public int rank(Key key) { return rank(root, key); }
	public Key select(int k) { return select(root, k).key; }
	public void deleteMin() { root = deleteMin(root); }
	public void deleteMax() { root = deleteMax(root); }
	public int size(Key lo, Key hi)
	{
		if (hi.compareTo(lo) < 0) return 0;
		else if (contains(hi)) return rank(hi) - rank(lo) + 1;
		else return rank(hi) - rank(lo);
	}
	public Iterable<Key> keys(Key lo, Key hi)
	{
		Queue<Key> queue = new Queue<Key>();
		keys(root, queue, lo, hi);
		return queue;
	}
	public Iterable<Key> keys() { return keys(min(), max()); } 

	/* HELPER METHODS */
	private Node put(Node cur, Key key, Value val)
	{
		if (cur == null) return new Node(key, val, 1);
		int cmp = key.compareTo(cur.key);
		if (cmp < 0) cur.left = put(cur.left, key, val);
		else if (cmp > 0) cur.right = put(cur.right, key, val);
		else cur.val = val;
		cur.N = size(cur.left) + size(cur.right) + 1;
		return cur;
	}
	private Value get(Node cur, Key key)
	{
		if (cur == null) return null;
		int cmp = key.compareTo(cur.key);
		if (cmp < 0) return get(cur.left, key);
		else if (cmp > 0) return get(cur.right, key);
		else return cur.val;
	}
	private Node delete(Node cur, Key key)
	{
		if (cur == null) return null;
		int cmp = key.compareTo(cur.key);
		if (cmp < 0) cur.left = delete(cur.left, key);
		else if (cmp > 0) cur.right = delete(cur.right, key);
		else {
			if (cur.right == null) return cur.left;
			if (cur.left == null) return cur.right;
			Node t = cur;
			cur = min(t.right);
			cur.right = deleteMin(t.right);
			cur.left = t.left;			
		}
		cur.N = 1 + size(cur.left) + size(cur.right);
		return cur;
	}
	private boolean contains(Node cur, Key key)
	{
		if (cur == null) return false;
		int cmp = key.compareTo(cur.key);
		if (cmp > 0) return contains(cur.right, key);
		else if (cmp < 0) return contains(cur.left, key);
		else return true;
	}
	private int size(Node node)
	{
		if (node == null) return 0;
		return node.N;
	}
	private Node min(Node cur)
	{
		if (cur.left == null) return cur;
		return min(cur.left);
	}
	private Node max(Node cur)
	{
		if (cur.right == null) return cur;
		return max(cur.right);
	}
	private Node floor(Node cur, Key key)
	{
		if (cur == null) return null;
		int cmp = key.compareTo(cur.key);
		if (cmp == 0) return cur;
		if (cmp < 0) return floor(cur.left, key);
		Node t = floor(cur.right, key);
		if (t != null) return t;
		return cur;
	}
	private Node ceiling(Node cur, Key key)
	{
		if (cur == null) return null;
		int cmp = key.compareTo(cur.key);
		if (cmp == 0) return cur;
		if (cmp > 0) return ceiling(cur.right, key);
		Node t = ceiling(cur.left, key);
		if (t != null) return t;
		return cur;
	}
	private int rank(Node cur, Key key)
	{
		if (cur == null) return 0;
		int cmp = key.compareTo(cur.key);
		if (cmp == 0) return size(cur.left);
		if (cmp < 0) return rank(cur.left, key);
		return 1 + size(cur.left) + rank(cur.right, key);
	}
	private Node select(Node cur, int k)
	{
		if (cur == null) return null;
		int t = size(cur.left);
		if (t == k) return cur;
		if (t > k) return select(cur.left, k);
		return select(cur.right, k-t-1);
	}
	private Node deleteMin(Node cur)
	{
		if (cur.left == null) return cur.right;
		cur.left = deleteMin(cur.left);
		cur.N = 1 + size(cur.left) + size(cur.right);
		return cur;
	}
	private Node deleteMax(Node cur)
	{
		if (cur.right == null) return cur.left;
		cur.right = deleteMax(cur.right);
		cur.N = 1 + size(cur.left) + size(cur.right);
		return cur;
	}
	private void keys(Node cur, Queue<Key> queue, Key lo, Key hi)
	{
		if (cur == null) return;
		int cmplo = lo.compareTo(cur.key);
		int cmphi = hi.compareTo(cur.key);
		if (cmplo < 0) keys(cur.left, queue, lo, hi);
		if (cmplo <= 0 && cmphi >= 0) queue.enqueue(cur.key);
		if (cmphi > 0) keys(cur.right, queue, lo ,hi);
	}
}