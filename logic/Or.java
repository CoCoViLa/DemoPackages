class Or {
	/*@ specification Or {
			int in1, in2, out;
			in1, in2 -> out{calc};
		}
	@*/

	int calc (int in1, int in2) {
		return Math.max(in1, in2);
	}
}
