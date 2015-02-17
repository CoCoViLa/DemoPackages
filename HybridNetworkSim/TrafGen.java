import java.util.Random;

public class TrafGen {
    /*@ specification TrafGen {

    int min, max, out, seed;
    void initstate, state, nextstate, finalstate;
    boolean on;
    
    min, max, seed -> initstate {init};

    cocovilaSpecObjectName, state -> out, nextstate {step};

    } @*/

    private Random gen;

    public void init(int min, int max, int seed) {
        this.gen = new Random(seed);
    }

    public int step(String name) {

        boolean on = Boolean.valueOf(
           (String) ProgramContext.getFieldValue(name, "on"));

        return on ? min + gen.nextInt(max - min + 1) : 0;
    }
}
