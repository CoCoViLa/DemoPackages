
public class Euler {

	/*@ specification Euler {
		int time;
		void done_print_initstate, done_print_finalstate;
		String initstate_name, finalstate_name;

		initstate_name = "initstate";
		finalstate_name = "finalstate";

		alias (double) initstate  = (*.initstate);
		alias (double) state      = (*.state);	
		alias (double) nextstate  = (*.nextstate);
		alias (double) finalstate = (*.finalstate);

		initstate_name, initstate -> done_print_initstate  {print_state};
		[ state ->  nextstate], initstate, time -> finalstate {proc_run};
		finalstate_name, finalstate -> done_print_finalstate  {print_state};
	}@*/	

	public void print_state(String state_name, double[] st) {		
		System.out.print(state_name + ":");
		for (int i = 0; i < st.length;  i++ ){
			System.out.print("\t" + st[i]);	
		}
		System.out.println();			
	}

	public double[] proc_run(Subtask st, double[] initst, int time) {
		double[]  state, finalst;

		int done = 1;
		System.out.println("time value is: " + time);

		Object[] in = new Object[1];
		in[0] = initst;
		try {		 
			for (int i = 1; i <= time;  i++ ){
				state = (double[])in[0];
//				System.out.println("current time: " + i);
//			     	print_state ("    state", state);	
				Object[] out = st.run(in);
				in = out;
			}		
		}
		catch (Exception e) {
			e.printStackTrace();
			done = 0;
		}
		finalst = (double[])in[0];	
	        return finalst;		 	
	}
}
