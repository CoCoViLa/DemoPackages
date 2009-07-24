class Complex2 {
    /*@ specification Complex2 {
	long A,B,X,Y,Z,U;

	[Y->A]->B{sum};
	[A->B], Z->X{sum};
	X, Y, U -> A{calc};

	alias (long) bu = (B,U);
	alias (long) zy = (Z,Y);
    }@*/
 
public long sum(Subtask sub, Object... args) {
	Object[] in = new Object[1];
	
	long b = 0;
	try {		 
		for (int i = 1; i < 4;  i++ ){
			in[0] = new Long(i);
			Object[] out = sub.run(in);
			
			b += ((Long)out[0]).longValue();
		}		
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	
	System.out.println( "b: " + b );
	return b;
}

public long calc( long x, long y, long z ) {
	return x * y * z;
}

}










