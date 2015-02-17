import ee.ioc.cs.vsle.api.*;

/*
 * This Process should work with any chosen approximation method (Euler, RK2, RK4)
 */
public class Process {

	/*@ specification Process {
		int time, time_s;
		double T;
		long delay;
		delay = 1;
		boolean debug;
		void done;
		boolean repaintImmediately;

		alias initstate  = (*.initstate);
		alias state      = (*.state);	
		alias nextstate  = (*.nextstate);
		alias finalstate = (*.finalstate);

		alias (double) allT = (*.T);

		allT.length, T -> allT{setT};

		alias draw = (*.drawing_ready);
		alias (boolean) repaint = (*.repaintImmediately);
		repaint.length, repaintImmediately -> repaint{setRepaint};

		alias processEnded = (*.paintAll);
		[ state ->  nextstate, draw], initstate, time, delay, debug, repaintImmediately -> done, processEnded{proc_run};

		-> done;
	}@*/	

	public boolean[] setRepaint( int len, boolean t ) {
		boolean[] tmp = new boolean[len];

		for( int i = 0; i < len; i++ ) {
			tmp[i] = t;
		}

		return tmp;
	}

	public double[] setT( int len, double t ) {
		double[] tmp = new double[len];

		for( int i = 0; i < len; i++ ) {
			tmp[i] = t;
		}

		return tmp;
	}

	public void proc_run( Subtask st, Object initst, int time, long delay,
            boolean debug, boolean repaintImmediately ) {

        if ( debug )
            System.out.println( "time value is: " + time );

        Object[] in = new Object[] { initst };
        for ( int i = 1; i <= time; i++ ) {
            Object[] out = st.run( in );
            in = out;

            if ( delay > 0 && repaintImmediately )
                try {
                    Thread.sleep( delay );
                } catch ( Exception e ) {
                }
        }
    }
}

