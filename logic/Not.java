class Not {
	/*@ specification Not {
			int in, out;
			in  -> out{calc};
		}
	@*/

	int calc (int in) {
		return 1 - in;
	}
}
