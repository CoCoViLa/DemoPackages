
public class Process {

	/*@ specification Process {
		double inp, out;
		void res;
		double min, step, max;
		Port port;
		alias draw = (*.drawing_ready);

		[ inp -> out, draw ], min, step, max -> res {proc_run};
	}@*/	

	public void proc_run(Subtask st, double start, double step, double finish) {

		try {		 
			for (double i = start; i <= finish;  i+=step ) {	
				Object[] out = st.run( new Object[]{i});
			}		
		}
		catch (Exception e) {
			e.printStackTrace();
		
		}		 	
	}
}









