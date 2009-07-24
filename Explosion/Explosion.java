
public class Explosion {
	/*@
	specification Explosion {
		double x, y, width, height, angle;
		
		alias (double) dim = (width, height, angle);
		alias (double) loc = (x, y);

		void done;
		x, y, width, height, angle -> done {print}; 
	}
	@*/
	
	public void print(double x, double y, double widht, double height,
			double angle) {
		System.err.println("Explosion: " + x);
	}
}
