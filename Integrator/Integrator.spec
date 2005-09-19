	int time;
	void done_print_initstate, done_print_finalstate;
	String initstate_name, finalstate_name;

	time = 50;

	initstate_name = "initstate";
	finalstate_name = "finalstate";
	
	alias initstate = (*.initstate);
	alias state 	 = (*.state);	
	alias nextstate  = (*.nextstate);
	alias finalstate = (*.finalstate);
	
	initstate_name, initstate -> done_print_initstate  {print_state};

    	[ state ->  nextstate], initstate, time -> finalstate {proc_run};
	
    	finalstate_name, finalstate -> done_print_finalstate  {print_state};	


