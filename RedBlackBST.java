public class RedBlackBST<Key extends Comparable<Key>, Value>
{
	private static final boolean RED   = true;
	private static final boolean BLACK = false;
	private Node root;

	private class Node
	{
		private Key key;
		private Value val;
		private Node left, right;
		private int N;
		private boolean color;

		public Node(Key key, Value val, int N, boolean color)
		{
			this.key = key;
			this.val = val;
			this.N = N;
			this.color = color;
		}
	}

	private boolean isRed(Node x)
	{
		if (x == null) return BLACK;
		return x.color == RED;
	}
	private int size(Node x)
	{
		if (x == null) return 0;
		return x.N;
	}
	private Node rotateLeft(Node h)
	{
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = h.color;
		h.color = RED;
		x.N = h.N;
		h.N = size(h.left) + size(h.right) + 1;
		return x;
	}
	private Node rotateRight(Node h)
	{
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = h.color;
		h.color = RED;
		x.N = h.N;
		h.N = size(h.left) + size(h.right) + 1;
		return x;
	}
	private void flipColors(Node h)
	{
		h.color       = RED;
		h.left.color  = BLACK;
		h.right.color = BLACK;
	}

	public void put(Key key, Value val)
	{
		root = put(root, key, val);
		/* Root must be always BLACK */
		root.color = BLACK;
	}
	private Node put(Node h, Key key, Value val)
	{
		/* Recurse */
		if (h == null) return new Node(key, val, 1, RED);
		int cmp = key.compareTo(h.key);
		if (cmp < 0) h.left = put(h.left, key, val);
		else (cmp > 0) h.right = put(h.right, key, val);
		else h.val = val;
		/* Checking colors to ensure this RedBlackBST is the same as the 2-3 node tree */
		if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
		if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
		if (isRed(h,left) && isRed(h.right)) flipColors(h);
		/* Dealing the size of current node */
		h.N = size(h.left) + size(h.right) + 1;
		return h;
	}
}