class NumGenerator {
	/*@ specification NumGenerator {
		int  out;
		double initstate, state, nextstate, finalstate;
		state -> out{gen};
		initstate = 0;
		nextstate = out;
	}
	@*/

	int gen(double val) {
		return (val==0) ? 1 : 0;
	}
}
