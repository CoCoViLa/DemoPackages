class Motor {
	/*@ specification Motor {
		double[] axial;
		-> axial {calc};
	}
	@*/
	int a;
	double[] calc() {
		double[] nT = new double[2];
		if (a < 30) {
			a++;
		} else
			a = 1;
			nT[0] = a*10;
		nT[1] = 30;
		return nT;
	}
};