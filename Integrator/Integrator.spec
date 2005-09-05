	int time, result;
	double step;
	int done_print_initstate, done_print_finalstate,  done;
	String initstate_name, finalstate_name;

	time = 7;
	step = 1.5;
	initstate_name = "initstate";
	finalstate_name = "finalstate";
	
	alias initstate = (*.initstate);
	alias state 	 = (*.state);	
	alias nextstate  = (*.nextstate);
	alias finalstate = (*.finalstate);
	
//	alias initstate = (Clock_0.initstate, Integrator_1.initstate, Integrator_2.initstate);
//	alias state 	 = (Clock_0.state, Integrator_1.state, Integrator_2.state);	
//	alias nextstate  = (Clock_0.nextstate, Integrator_1.nextstate, Integrator_2.nextstate);
//	alias finalstate = (Clock_0.finalstate, Integrator_1.finalstate, Integrator_2.finalstate);
	
	initstate_name, initstate -> done_print_initstate  {print_state};
    [ state ->  nextstate], initstate, time -> finalstate {proc_run};	
    finalstate_name, finalstate -> done_print_finalstate  {print_state};	


