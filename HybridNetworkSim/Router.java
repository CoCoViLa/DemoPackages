public class Router {
    /*@ specification Router {

    alias (int) in;
    int initstate, state, nextstate, finalstate;
    int speed, limit, drop;

    in, state, speed, limit -> nextstate {step};
    in, state, speed, limit -> drop {calcDrop};

    } @*/

    public int sum(int[] a) {
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
        }
        return sum;
    }
    
    public int step(int[] in, int state, int speed, int limit) {
        // packets are queued for at least one step
        return Math.min(limit, sum(in) + Math.max(0, state - speed));
    }

    public int calcDrop(int[] in, int state, int speed, int limit) {
        return Math.max(0, sum(in) + Math.max(0, state - speed) - limit);
    }
}
