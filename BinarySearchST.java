public class BinarySearchST<Key extends Comparable<Key>, Value>
{
	/* INSTANCE VARIABLES */
	private int N;
	private Key[] keys;
	private Value[] vals;

	/* CONSTRUCTORS */
	public BinarySearchST(int cap)
	{
		keys = (Key[]) new Comparable[cap];
		vals = (Value[]) new Object[cap];
	}

	/* INSTANCE METHODS */
	public void put(Key key, Value val)
	{
		if (val == null) { delete(key); return; }
		int i = rank(key);
		if (i< N && keys[i].compareTo(key) == 0) { vals[i] = val; return; }
		for (int k = N; k > i; k--) {
			keys[i] = keys[i-1];
			vals[i] = vals[i-1];
		}
		keys[i] = key;
		vals[i] = val;
		N++;
	}
	public Value get(Key key)
	{
		if (isEmpty()) return null;
		int i = rank(key);
		if (i < N && keys[i].compareTo(key) == 0) return vals[i];
		else return null;
	}
	public boolean delete(Key key)
	{
		if (isEmpty()) return false;
		int i = rank(key);
		if (i< N && keys[i].compareTo(key) == 0) {
			for (int k = i; k < N-1; k++) {
				keys[k] = keys[k+1];
				vals[k] = vals[k+1];
			}
			N--;
			return true;
		}
		else return false;
	}
	public boolean contains(Key key)
	{
		int i = rank(key);
		if (i < N && keys[i].compareTo(key) == 0) return true;
		else return false;
	}
	public boolean isEmpty() { return N == 0; }
	public int size() { return N; }
	public Key min() { return keys[0]; }
	public Key max() { return keys[N-1]; }
	public Key floor(Key key)
	{
		if (isEmpty()) return null;
		int i = rank(key);
		if (i < N && keys[i].compareTo(key) == 0) return key;
		else return keys[i-1];
	}
	public Key ceiling(Key key)
	{
		if (isEmpty()) return null;
		int i = rank(key);
		return keys[i];
	}	
	public int rank(Key key)
	{
		int lo = 0, hi = N-1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			int cmp = key.compareTo(keys[mid]);
			if (cmp < 0 ) hi = mid - 1;
			else if (cmp > 0) lo = mid + 1;
			else return mid;
		}
		return lo;
	}
	public Key select(int k) { return keys[k]; }
	public void deleteMin() { delete(min()); }
	public void deleteMax() { if(!isEmpty()) N--; }
	public int size(Key lo, Key hi)
	{
		if (contains(hi)) return rank(hi) - rank(lo) + 1;
		else return rank(hi) - rank(lo);
	}
	Iterable<Key> keys(Key lo, Key hi)
	{
		Queue<Key> q = new Queue<Key>();
		for (int i = rank(lo); i < rank(hi); i++) q.enqueue(keys[i]);
		if (contains(hi)) q.enqueue(keys[rank(hi)]);
		return q;
	}
	Iterable<Key> keys()
	{
		Queue<Key> q = new Queue<Key>();
		for (int i = 0; i < N; i++)
			q.enqueue(keys[i]);
		return q;
	}
}