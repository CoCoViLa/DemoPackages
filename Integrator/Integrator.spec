	int time, result;
	double step;
	int done_print_initstate, done_print_finalstate,  done;
	String initstate_name, finalstate_name;

	time = 7;
	step = 1.5;
	initstate_name = "initstate";
	finalstate_name = "finalstate";
	
	//alias initstate = (*.initstate);
	//alias state 	 = (*.state);	
	//alias nextstate  = (*.nextstate);
	//alias finalstate = (*.finalstate);
	
	alias initstate = (Clock_1.initstate, Integrator_0.initstate, Integrator_4.initstate);
	alias state 	 = (Clock_1.state, Integrator_0.state, Integrator_4.state);	
	alias nextstate  = (Clock_1.nextstate, Integrator_0.nextstate, Integrator_4.nextstate);
	alias finalstate = (Clock_1.finalstate, Integrator_0.finalstate, Integrator_4.finalstate);
	
	initstate_name, initstate -> done_print_initstate  {print_state};
    [ state ->  nextstate], initstate, time -> finalstate {proc_run};	
    finalstate_name, finalstate -> done_print_finalstate  {print_state};	