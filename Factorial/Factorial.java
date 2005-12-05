class Factorial {
    /*@ specification Factorial {
	long n, nn, f;
	[nn->f],n->f,(java.lang.Exception){fact};
	nn=f;
	->f;
    }@*/

	long fact( Subtask s, long n ) throws Exception {

		if( n == 0 ) {
			return 1;
		} 
		Object[] in = new Object[1];
   		in[0] = n - 1;
    		Object[] out = s.run(in);
	
		long f = n * fact( s, ( (Long) out[0]).longValue() );
	
		System.out.println( "n: " + n + " f: " + f );
		return f;
	}
	
}

