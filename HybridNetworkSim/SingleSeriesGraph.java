import java.util.*;
import javax.swing.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.xy.*;

public class SingleSeriesGraph
{
    /*@
    specification SingleSeriesGraph {
        int x, y;
        void initstate, state, nextstate, finalstate, done;

        -> initstate {init};

        String seriesName;
        x, y -> nextstate {addPoint};
        finalstate, seriesName -> done {draw};
    }
    @*/

    ArrayList<Double> xs, ys;

    public void init() {
        return;
    }

    public void draw(String seriesName) {

        if (xs == null || ys == null || xs.size() < 2 || ys.size() < 2
                || xs.size() != ys.size()) {

            System.err.println(
                "SingleSeriesGraph: at least two points needed for a graph");
            return;
        }

        final DefaultXYDataset dataset = new DefaultXYDataset();

        // xs and xy must have the same length
        double[][] data = new double[2][];
        data[0] = new double[xs.size()];
        data[1] = new double[ys.size()];
        for (int i = 0; i < xs.size(); i++) {
            data[0][i] = xs.get(i).doubleValue();
            data[1][i] = ys.get(i).doubleValue();
        }

        dataset.addSeries(seriesName, data);

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFreeChart chart = ChartFactory.createXYLineChart(
                    "",
                    "x", "y", dataset, PlotOrientation.VERTICAL, true, true, false);

                ChartFrame frame = new ChartFrame("Graph", chart);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

    public void addPoint(double x, double y) {
        if (xs == null) {
            xs = new ArrayList<Double>();
        }
        if (ys == null) {
            ys = new ArrayList<Double>();
        }

        xs.add(Double.valueOf(x));
        ys.add(Double.valueOf(y));
    }

}

