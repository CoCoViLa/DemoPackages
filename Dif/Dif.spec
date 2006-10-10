	int time;
	void done_print_initstate, done_print_finalstate;
	String initstate_name, finalstate_name;

	time = 100;

	initstate_name = "initstate";
	finalstate_name = "finalstate";
	
	alias (double) initstate  = (*.initstate);
	alias (double) state      = (*.state);	
	alias (double) nextstate  = (*.nextstate);
	alias (double) finalstate = (*.finalstate);
	
	initstate_name, initstate -> done_print_initstate  {print_state};

    	[ state ->  nextstate], initstate, time -> finalstate {proc_run};
	
    	finalstate_name, finalstate -> done_print_finalstate  {print_state};	










