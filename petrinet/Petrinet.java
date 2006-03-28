import ee.ioc.cs.vsle.vclass.ISchemeDaemon;
import ee.ioc.cs.vsle.vclass.Scheme;

/**
 * Simple scheme daemon.
 * @author andrex
 */
public class Petrinet implements ISchemeDaemon {

    private boolean running = true;
    private Scheme scheme;
    
    public void setScheme(Scheme scheme) {
        this.scheme = scheme;
    }

    public void stop() {
        running = false;
        System.err.println("Set running = false");
    }

    public void run() {
        while (running) {
            System.err.println(scheme);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.err.println("Thread interrupted");
            }
        }
    }
}
