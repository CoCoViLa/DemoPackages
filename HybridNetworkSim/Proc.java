public class Proc {
    /*@ specification Proc {
        int time;
        int T;

        alias initstate  = (*.initstate);
        alias state      = (*.state);
        alias nextstate  = (*.nextstate);
        alias finalstate = (*.finalstate);
        alias done       = (*.done);
        void  end;

        alias (int) allT = (*.T);

        allT.length, T -> allT {setT};

        [ state -> nextstate ], [ finalstate -> done ], initstate, time, initstate.length, state.length, nextstate.length, finalstate.length -> end {proc_run};

        -> end;
    } @*/

	public int[] setT(int len, int t) {
		int[] tmp = new int[len];

		for (int i = 0; i < len; i++) {
			tmp[i] = t;
		}

		return tmp;
	}

    public void proc_run(Subtask st, Subtask fs, Object initst, int time, int il, int sl, int nl, int fl) {
        String name = "TrafGen_2";
        Simulator.scheduleAt(50, new StartEvent(name));
        Simulator.scheduleAt(100, new EndEvent(name));

        System.err.println("U: " + il + ", " + sl + ", " + nl + ", " + fl);

        Object[] in = new Object[] { initst };
        try {
            for (int i = 0; i < time; i++) {
                while (Simulator.getNextEventTime() > -1 && Simulator.getNextEventTime() <= i) {
                    Simulator.step();
                }
                Object[] out = st.run(in);
                in = out;
            }
            fs.run(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Simulator.reset();
    }

    public static class StartEvent implements Runnable {

        private String name;

        public StartEvent(String name) {
            this.name = name;
        }

        public void run() {
            System.err.println("StartEvent @ " + Simulator.getCurSimTime() + " (" + name + ")");
            ProgramContext.setFieldValue(name, "on", "true");
        }

    }
    public static class EndEvent implements Runnable {

        private String name;

        public EndEvent(String name) {
            this.name = name;
        }

        public void run() {
            System.err.println("EndEvent @ " + Simulator.getCurSimTime() + " (" + name + ")");
            ProgramContext.setFieldValue(name, "on", "false");
        }
    }
}
