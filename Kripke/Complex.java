class Complex {
    /*@ specification Complex {
	long A;
	long B;
	long X;
	long Y;
	long Z;

	[Y->A]->B{sum};
	[A->B]->X{sum};
	X, Y, Z -> A{calc};
    }@*/
 
public long sum(Subtask sub) {
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






