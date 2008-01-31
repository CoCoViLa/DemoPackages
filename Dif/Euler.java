
public class Euler {

	/*@ specification Euler {
		int time, time_s;
		double T;
		long delay;
		boolean debug;

		void done_print_initstate, done_print_finalstate;
		String initstate_name, finalstate_name;

		initstate_name = "initstate";
		finalstate_name = "finalstate";

		alias (double) initstate  = (*.initstate);
		alias (double) state      = (*.state);	
		alias (double) nextstate  = (*.nextstate);
		alias (double) finalstate = (*.finalstate);

		alias (double) allT = (*.T);

		allT.length, T -> allT{setT};

		alias draw = (*.drawing_ready);
		initstate_name, initstate -> done_print_initstate  {print_state};
		[ state ->  nextstate, draw], initstate, time, delay, debug -> finalstate {proc_run};
		finalstate_name, finalstate -> done_print_finalstate  {print_state};

		->done_print_finalstate;
	}@*/	


	public double[] setT( int len, double t ) {
		double[] tmp = new double[len];

		for( int i = 0; i < len; i++ ) {
			tmp[i] = t;
		}

		return tmp;
	}

	public void print_state(String state_name, double[] st) {		
		System.out.print(state_name + ":");
		for (int i = 0; i < st.length;  i++ ){
			System.out.print("\t" + st[i]);	
		}
		System.out.println();			
	}

	public double[] proc_run(Subtask st, double[] initst, int time, long delay, boolean debug) {
		double[]  state, finalst;

		if( debug )
			System.out.println("time value is: " + time);

		Object[] in = new Object[1];
		in[0] = initst;
		try {		 
			for (int i = 1; i <= time;  i++ ){
				state = (double[])in[0];	
				Object[] out = st.run(in);
				in = out;

    				Thread.sleep(delay);
			}
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finalst = (double[])in[0];	
	        return finalst;		 	
	}
}








