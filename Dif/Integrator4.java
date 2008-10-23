//Integrator element that implements Runge-Kutta fourth-order approximation method
class Integrator4 {
    /*@ 
	specification Integrator4 {
		double T, in, out;
		double init, myState, myNext, k;

		double initStateType, stateType, nextStateType, oldState, oldIn, nextOld, nextIn;

		initStateType = 1;

		alias (double) initstate = (initStateType, init, init, init, init);
		alias (double) state = (stateType, myState, oldState, oldIn, k);
		alias (double) nextstate = (nextStateType, myNext, nextOld, nextIn, k);
		alias (double) finalstate;

		out = myState;

		state, state.length, in, T -> nextstate {step};
	}
	@*/

	public double[] step(double[] state, int stateSize, double in, double t) {
		double[] nextstate = new double[stateSize];
		double type = state[0];
		double curstate = state[1];
		double oldstate = state[2];
		double oldin = state[3];
		double k = state[4];
		
		double step = in / t;

		if ( type == 0.0 ) {
			nextstate[0] = 1.0;
			//y1 = y0 + 1/6(k1 + 2*k2 + 2*k3 + k4)
			nextstate[1] = oldstate + (k+2.0*oldin + step)/6.0;
			nextstate[2] = Double.NaN;
			nextstate[3] = Double.NaN;
			nextstate[4] = Double.NaN;
		} else if ( type == 1.0 ) {
			nextstate[0] = 2.0;
			nextstate[1] = curstate + step/2.0;//y0+k1/2
			nextstate[2] = curstate;//y0
			nextstate[3] = step;//k1
			nextstate[4] = Double.NaN;
		} else if ( type == 2.0 ) {
			nextstate[0] = 3.0;
			nextstate[1] = oldstate + step/2.0;//y0+k2/2
			nextstate[2] = oldstate;//y0
			nextstate[3] = step;//k2
			nextstate[4] = oldin;//k1
		} else if ( type == 3.0 ) {
			nextstate[0] = 0.0;
			nextstate[1] = oldstate + step;//y0+k3
			nextstate[2] = oldstate;//y0
			nextstate[3] = step;//k3
			nextstate[4] = k+2.0*oldin;//k1+2*k2
		}
//		System.out.println( type + " " + nextstate[1] );
 		return nextstate;
	}
}



