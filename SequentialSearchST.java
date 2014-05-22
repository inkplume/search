import java.util.Iterator;

public class SequentialSearchST<Key, Value> implements Iterable<Key>
{
	/* INSTANCE VARIABLES */
	private int N;
	private Node head;

	/* INNER CLASS */
	private class Node
	{
		Key key;
		Value val;
		Node next;
		public Node(Key key, Value val, Node next)
		{ this.key = key; this.val = val; this.next = next; }
	}
	private class SequentialIterator implements Iterator<Key>
	{
		private Node it;
		public SequentialIterator(Node head) { it = head; }
		public boolean hasNext() { return it != null;}
		public Key next() { Key key = it.key; it = it.next; return key; }
		public void remove() {}
	}

	/* CONSTRUCTORS */
	public SequentialSearchST() {}

	/* INSTANCE METHODS */
	public Iterator<Key> iterator()
	{ return new SequentialIterator(head); }
	public int size() { return N; }
	public Value get(Key key)
	{
		for (Node it= head; it != null; it = it.next)
			if (key.equals(it.key)) return it.val;
		return null;
	}
	public void put(Key key, Value val)
	{
		if (val == null) { delete(key); return; }
		for (Node it = head; it != null; it = it.next)
			if (key.equals(it.key)) {it.val = val; return; }
		head = new Node(key, val, head);
		N++;
	}
	public void delete(Key key)
	{
		for (Node it = head, preit = null; it != null; it = it.next) {
			if (key.equals(it.key)) {
				if (it == head) head = head.next;
				else preit.next = it.next;
				it = null;
				preit = null;
				return;
			}
			else preit = it;
		}
	}

	
	
	/* MAIN METHODS */
	public static void main(String[] args)
	{
		SequentialSearchST<String, Integer> st;
		st = new SequentialSearchST<String, Integer>();
		String[] s = {"S","E","A","R","C","H","E","X","A","M","P","L","E"};
		for (int i = 0; i < s.length; i++)
			st.put(s[i], i);
		for (Iterator<String> it = st.iterator(); it.hasNext();) {
			String x = it.next();
			System.out.println(x + " " + st.get(x));
		}
		st.delete((st.iterator()).next());
		for (Iterator<String> it = st.iterator(); it.hasNext();) {
			String x = it.next();
			System.out.println(x + " " + st.get(x));
		}
	}
}