public class BSTiter<Key extends Comparable<Key>, Value>
{
	private Node root;
	private class Node
	{
		private Key key;
		private Value val;
		private Node left, right;
		private int N;

		private Node(Key key, Value val, int N)
		{ this.key = key; this.val = val; this.N = N; }
	}

	public BSTiter() {}

	public void put(Key key, Value val)
	{
		Node cur = root, pre = null;
		int cmp = 0;
		while (cur != null) {
			cmp = key.compareTo(cur.key);
			if (cmp < 0) { (cur.N)++; pre = cur; cur = cur.right; }
			else if (cmp > 0) { (cur.N)++; pre = cur; cur = cur.right; }
			else { cur.val = val; return; }
		}
		cur = new Node(key, val, 1);
		if (cmp < 0) pre.left = cur;
		else pre.right = cur;
	}
	public Value get(Key key)
	{
		Node cur = root;
		int cmp = 0;
		while (cur != null) {
			cmp = key.compareTo(cur.key);
			if (cmp < 0) cur = cur.left;
			else if (cmp > 0) cur = cur.right;
			else return cur.val;
		}
		return null;
	}
	public int size() { return size(root); }

	private int size(Node node)
	{
		if (node == null) return 0;
		return node.N;
	}
}