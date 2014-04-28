class NumGenerator {
	/*@ specification NumGenerator {
			int  out;
			-> out{gen};
		}
	@*/
	int val = 0;
	int gen() {

		return val;
	}
}
