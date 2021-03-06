class Factorial {
    
    /*@ specification Factorial {
	    double n, f, arg, val;
	    [arg->val],n->f,(java.lang.Exception){fact};
	    val = arg - 1;
	    n->f;
    }@*/

	double fact( Subtask s, double n ) throws Exception {

        if ( n == 0 ) {
            return 1;
        }
        Object[] in = new Object[] { n };
        Object[] out = s.run( in );

        double f = n * fact( s, ( (Double) out[ 0 ] ).doubleValue() );

        System.out.println( "n: " + n + " f: " + f );
        return f;
    }

}






