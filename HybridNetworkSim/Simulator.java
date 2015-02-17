import java.util.PriorityQueue;

/**
 * Simple discrete event simulation engine.
 * 
 * Example usage:
 * <pre>
 *  static class MyEvent implements Runnable {
 *      private static int seq;
 *      private int mySeq;
 *
 *      public MyEvent() {
 *          mySeq = seq++;
 *      }
 *
 *      public void run() {
 *          System.err.println("Event(" + mySeq + ") at "
 *              + Simulator.getCurSimTime());
 *              if (Simulator.getCurSimTime() &lt; 100) {
 *                  Simulator.scheduleAt(
 *                      Simulator.getCurSimTime() + 1,
 *                      new MyEvent());
 *              }
 *          }
 *      }
 *  }
 *  ...
 *  Simulator.scheduleAt(10, new MyEvent());
 *  Simulator.setSimDelay(10);
 *  Simulator.run()
 * </pre>
 * 
 * @author Andres Ojamaa
 */
public final class Simulator {

    static {
        System.err.println("Simulator class created: " + Simulator.class);
    }

    private static long curSimTime;
    private static long simDelay;
    private static PriorityQueue<SimEvent> eventQueue;

    public static class SimEvent implements Runnable, Comparable<SimEvent> {

        protected long time;
        protected Runnable event;

        public SimEvent(long time, Runnable event) {
            if (time < 0) {
                throw new IllegalArgumentException(
                        "Time has to be non-negative");
            }
            this.time = time;
            this.event = event;
        }

        public void run() {
            if (event != null) {
                event.run();
            }
        }

        public int compareTo(SimEvent ev) {
            if (time < ev.time) {
                return -1;
            } else if (time > ev.time) {
                return 1;
            }
            return 0;
        }

        public long getTime() {
            return time;
        }

        @Override
        public String toString() {
            return "Event(" + super.toString() + ") @ " + getTime();
        }
    }

    private Simulator() {
        // should not be instanciated?
    }

    /**
     * Inserts a new event into the event queue at the specified time.
     * @param time the time of the event
     * @param event the event handler triggered at the specified time
     */
    public static void scheduleAt(long time, Runnable event) {
        schedule(new SimEvent(time, event));
    }

    /**
     * Inserts the specified event into the event queue.
     * Events that have timestamps in the past are discarded.
     * @param event the event to be inserted
     */
    public static void schedule(SimEvent event) {
        if (eventQueue == null) {
            eventQueue = new PriorityQueue<SimEvent>();
        }
        if (event.getTime() < curSimTime) {
            log("Discarded new event from the past: " + event);
        } else {
            if (!eventQueue.offer(event)) {
                log("New event refused by the queue: " + event);
            }
        }
    }

    /**
     * Returns the current simulation time.  Initially the time is 0.
     * @return the current simulation time
     */
    public static long getCurSimTime() {
        return curSimTime;
    }

    /**
     * Returns the simulation time the next event is going to be triggered.
     * @return the time of the next event; -1 if the queue is empty
     */
    public static long getNextEventTime() {
        long nextTime = -1;
        if (eventQueue != null) {
            SimEvent ev = eventQueue.peek();
            if (ev != null) {
                nextTime = ev.getTime();
            }
        }
        return nextTime;
    }

    /**
     * Sets the number of milliseconds the engine waits before
     * incrementing simulation time by one unit.
     * This can be useful for slowing down animations etc.
     * 
     * @param delay the delay value in milliseconds per simulation time unit
     */
    public static void setSimDelay(long delay) {
        if (delay < 0) {
            throw new IllegalArgumentException("Delay has to be non-negative");
        }
        simDelay = delay;
    }

    /**
     * Runs the next event and updates simulation time.
     * This method does not catch exceptions thrown by event handlers.
     * @return true if an event was processed; false if the queue was empty
     */
    public static boolean step() {
        boolean rv = false;
        if (eventQueue != null) {
            SimEvent ev = eventQueue.poll();

            if (ev != null) {
                long dif = ev.getTime() - curSimTime;
                if (dif > 0) {
                    if (simDelay > 0) { 
                        try {
                            Thread.sleep(dif * simDelay);
                        } catch (InterruptedException e) {
                            // This can be ignored as it does not affect the
                            // correctness of the simulation.
                        }
                    }
                    curSimTime = ev.getTime();
                }
                ev.run();
                rv = true;
            }
        }
        return rv;
    }

    /**
     * Runs the main simulation loop until the event queue is empty.
     */
    public static void run() {
        if (eventQueue == null) {
            log("Simulation started with emtpy queue");
        }

        while (true) {
            try {
                if (!step()) {
                    log("Simulation ended");
                    break;
                }
            } catch (Exception e) {
                log("Exception occured while running event\n" + e.toString());
            }
        }
    }

    private static void log(String s) {
        System.err.println("Sim(" + curSimTime + "): " + s);
    }

    public static void reset() {
        curSimTime = 0;
    }
}
