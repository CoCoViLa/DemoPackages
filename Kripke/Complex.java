class Complex {
    /*@ specification Complex {
    int A;
    int B;
    int X;
    int Y;
    int W;
    int U;
    int V;

[Y->A]->B{sum};
[A->B]->X{sum};
[U->V],Y, X-> A{calc};
->B;

    }@*/
 
public int sum(Subtask sub) {
	Object[] in = new Object[1];
	
	int b = 0;
	try {		 
		for (int i = 1; i < 4;  i++ ){
			in[0] = new Integer(i);
			Object[] out = sub.run(in);
			
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
	return y * x;
}

}

