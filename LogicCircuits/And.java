class And {
    /*@ specification And {
    	int in1, in2, out;
    	in1, in2 -> out{calc};
    }@*/

    int calc( int x, int y ) {
        return Math.min( x, y );
    }
}
