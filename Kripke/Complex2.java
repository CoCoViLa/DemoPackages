class Complex2 {
    /*@ specification Complex2 {
//	1 (n+2) given U_0, Z_n
//	long A,B,X,Y,Z,V,U;
//	[Y, V -> A] -> B {sum};
//	[A -> B], V, Z -> X {sum};
//	X, Y, U -> A {calc};
//	alias (long) bu = (B,U);
//	alias (long) zy = (Z,Y);

//	2 (n+2) given U_0, Z_n
//	long A,B,X,Y,Z,V,U;
//	[Y, V -> A] -> B {sum};
//	[A -> B, Z], V -> X {sum};
//	X, Y, U -> A {calc};
//	alias (long) bu = (B, U);
//	alias (long) zy = (Z, Y);

//	3 (n+2) given Z_0, U_n
//	long A,B,X,Y,Z,V,U;
//	[Y, V -> A, U] -> B {sum};
//	[A -> B], V -> X, U{sum};
//	X, Y, Z -> A {calc};
//	alias bu = (B, Z);
//	alias zy = (U, A);

//	4 (n+2) given U_0, Z_n
	long A,B,X,Y,Z,V,U;
	[Y, V -> A] -> B {sum};
	[A -> B, Z, V] -> X {sum};
	X, Y, U -> A {calc};
	alias (long) bu = (B, U);
	alias (long) zy = (Z, Y);
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
