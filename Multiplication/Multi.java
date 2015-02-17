import java.util.Random;
import ee.ioc.cs.vsle.api.*;

class Multi {
    /*@ specification Multi {
        int x, y, value;
        const String tableId = "multiplication";
//        -> x {random};
//        -> y {random};
//        tableId, x, y -> value {test};
        tableId, x, y -> value {@table};

//set the goal on the scheme        
//        x, y -> value;
    }@*/
    
    int test( String id, int x, int y ) {
        try {
            return (Integer)ProgramContext.queryTable( id, x, y );
        } catch(Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
 
    int random() {
        return 1 + new Random().nextInt( 9 );
    }
}












