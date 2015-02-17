class Simple {
    /*@ specification Simple {
    int A;
    int B;
    int X;
    int Y;

[Y->A]->B{sum};
[A->B]->X{sum};
Y, X -> A{calc};
->B;

    }@*/

public int sum(Subtask sub) {
	Object[] in = new Object[1];
	
	int b = 0;
	try {		 
		for (int i = 1; i < 4;  i++ ){
			in[0] = new Integer(i);
			Object[] out = sub.run(in);
			System.out.println( "out[0]: " + out[0] );
			b += ((Integer)out[0]).intValue();
		}		
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	
	System.out.println( "b: " + b );
	return b;
}

public int calc( int y, int x ) {
	System.out.println( "calc: " + (y * x) + " y: " + y + " x: " + x);
	return y * x;
}

}





