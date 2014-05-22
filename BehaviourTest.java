public class BehaviourTest
{
	public static void main(String[] args)
	{
		SequentialSearchST<String, Integer> st;
		st = new SequentialSearchST<String, Integer>();
		for (int i = 0; !StdIn.isEmpty(); i++) {
			String key = StdIn.readString();
			st.put(key, i);
		}
		for (String s : st)
			StdOut.println(s + " " + st.get(s));
	}
}