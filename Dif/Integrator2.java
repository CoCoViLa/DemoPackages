//Integrator element that implements Runge-Kutta second-order approximation method
//Author Andres Ojamaa
class Integrator2 {
    /*@ 
	specification Integrator2 {
		double T, in, out;
		double init, myState, myNext;

		double initStateType, stateType, nextStateType, oldState, oldIn, nextOld, nextIn;

		initStateType = 1;

		alias (double) initstate = (initStateType, init, init, init);
		alias (double) state = (stateType, myState, oldState, oldIn);
		alias (double) nextstate = (nextStateType, myNext, nextOld, nextIn);
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
		
		double step = in / t;

		if (type == 0.0) {
			nextstate[0] = 1.0;
			nextstate[1] = oldstate + 0.5 * ( oldin + step );
			nextstate[2] = Double.NaN;
			nextstate[3] = Double.NaN;
		} else {
			nextstate[0] = 0.0;
			nextstate[1] = curstate + step;
			nextstate[2] = curstate;
			nextstate[3] = step;
		}
 		return nextstate;
	}
}

