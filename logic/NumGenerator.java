class NumGenerator {
	/*@ specification NumGenerator {
			int  out;
			-> out{gen};
		}
	@*/

	int val = 0;
	int gen() {
		if (val==0) 
			val = 1;
		else 
			val = 0;
		return val;
	}
}
