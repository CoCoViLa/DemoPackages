import java.util.*;

import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.xy.*;

class DoubleSeriesGraph {
    /*@ specification DoubleSeriesGraph {
	double x1,y1,x2,y2;
	String seriesName1, seriesName2;
	void init_ready1, init_ready2;
	void drawing_ready;
	seriesName1 -> init_ready1{setSeries1Name};
	seriesName2 -> init_ready2{setSeries2Name};
	x1, y1, x2, y2 -> drawing_ready{draw};
    }@*/

	String series1 = "series1";
	String series2 = "series2";
	XYSeries ser1;
	XYSeries ser2;
	
	boolean isInitialized;
	
	DoubleSeriesGraph() {
    }

    private void init() {
        ser1 = new XYSeries( series1, true, false );
        ser2 = new XYSeries( series2, true, false );
        
		XYSeriesCollection dataset = new XYSeriesCollection();
		
        dataset.addSeries( ser1 );
        dataset.addSeries( ser2 );
        
        JFreeChart chart = ChartFactory.createXYLineChart("", "x", "y",
                dataset, PlotOrientation.VERTICAL, true, true, false);
        
        ChartFrame frame = new ChartFrame("Graph", chart);
        
        frame.pack();
        frame.setVisible( true );
        isInitialized = true;
    }

    public void setSeries1Name( String name ) {
        series1 = name;
    }

    public void setSeries2Name( String name ) {
        series2 = name;
    }

    public void draw( double x1, double y1, double x2, double y2 ) {
        if ( !isInitialized ) {
            init();
        }
        try {
            // Thread.sleep(25);
        } catch ( Exception e ) {
        }
        
        ser1.add( x1, y1 );
        ser2.add( x2, y2);
    }

}








