import java.util.*;

import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.xy.*;

class DoubleSeriesGraph {
    /*@ specification DoubleSeriesGraph {
	double x1,y1,x2,y2;
	String seriesName1, seriesName2;
	void init_ready1, drawing_ready1, init_ready2, drawing_ready2;
	seriesName1 -> init_ready1{setSeries1Name};
	seriesName2 -> init_ready2{setSeries2Name};
	x1, y1 -> drawing_ready1{draw1};
	x2, y2 -> drawing_ready2{draw2};
    }@*/

	DefaultXYDataset dataset = new DefaultXYDataset();
		
	JFreeChart chart = ChartFactory.createXYLineChart("", "x", "y",
			dataset, PlotOrientation.VERTICAL, true, true, false);
		
	ChartFrame frame = new ChartFrame("Graph", chart);
	
	
	String series1 = "series1";
	String series2 = "series2";
	
	TreeMap<Double, Double> data1 = new TreeMap<Double, Double>();
	TreeMap<Double, Double> data2 = new TreeMap<Double, Double>();

	DoubleSeriesGraph() {
		init();
	}

	private void init() {
		frame.pack();
		frame.setVisible(true);
	}

	public void setSeries1Name( String name ) {
		series1 = name;
	}

	public void setSeries2Name( String name ) {
		series2 = name;
	}

public void draw1( double x, double y ) {
    	
    	try {
    		//Thread.sleep(25);
    	} catch(Exception e ) {}
		double[][] d = new double[2][];
		data1.put( x, y );
			
		d[0] = getPrimDoubleArray( data1.keySet().toArray( new Double[data1.keySet().size()] ) ); 
		d[1] = getPrimDoubleArray( data1.values().toArray( new Double[data1.values().size()] ) ); 
		
		dataset.removeSeries(series1);
		dataset.addSeries(series1, d);
}

public void draw2( double x, double y ) {
    	
    	try {
    		//Thread.sleep(25);
    	} catch(Exception e ) {}
		double[][] d = new double[2][];
		data2.put( x, y );
			
		d[0] = getPrimDoubleArray( data2.keySet().toArray( new Double[data2.keySet().size()] ) ); 
		d[1] = getPrimDoubleArray( data2.values().toArray( new Double[data2.values().size()] ) ); 
		
		dataset.removeSeries(series2);
		dataset.addSeries(series2, d);
}

	static double[] getPrimDoubleArray( Double[] data ) {
		
		double[] ret = new double[data.length];
		
		for (int i = 0; i < data.length; i++) {
			ret[i] = data[i];
		}
		
		return ret;
	}
}


