class NumGenStL {
	/*@ specification NumGenStL {
		int val, out;
		val-> out{gen};
	}
	@*/

	//private int val = 0;

	public int gen(int val) {
	    	val = 1 - val;
	    	return val;
	}
}
