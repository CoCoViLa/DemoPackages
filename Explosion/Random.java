
public class Random {
	
	/*@
	specification Random {
		double base, rndmax, x, y, height, width, angle;
		
		alias (double) rndloc = (x, y);
		alias (double) rnddim = (width, height, angle);
		
		base, rndmax -> rndloc {genrndloc};
		base, rndmax -> rnddim {genrnddim}; 
	}
	@*/
	
	public double[] genrndloc(double min, double max) {
		return new double[] {
				min + max * Math.random(),
				min + max * Math.random() };
	}

	public double[] genrnddim(double min, double max) {
		return new double[] {
				min + max * Math.random(),
				min + max * Math.random(),
				min + max * Math.random() };
	}

}

