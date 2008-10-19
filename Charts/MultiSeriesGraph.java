import java.util.*;

import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.xy.*;

class MultiSeriesGraph {
    /*@ specification MultiSeriesGraph {
	double x;
	alias (double) ys;
	void init_ready, drawing_ready;
	//NB! seriesNames if defined must match ys
	String[] seriesNames;
	seriesNames -> init_ready{setSeriesName};
	x, ys -> drawing_ready{draw};
    }@*/

	ArrayList<XYSeries> data = new ArrayList<XYSeries>();

	boolean isInitialized;

	MultiSeriesGraph() {
	}

	private void init( int length ) {
		XYSeriesCollection dataset = new XYSeriesCollection();

		for( int i = 0; i < length; i++ ) {
			XYSeries ser;
			data.add( ser = new XYSeries( ""+i, true, false ) );
			dataset.addSeries( ser );
		}

		JFreeChart chart = ChartFactory.createXYLineChart("", "x", "y",
                		dataset, PlotOrientation.VERTICAL, true, true, false);
        
        		ChartFrame frame = new ChartFrame("Graph", chart);

		frame.pack();
		frame.setVisible(true);
		isInitialized = true;
	}

	public void draw( double x, double[] ys ) {
    		if( !isInitialized ) {
			init( ys.length );
		}

		for( int i = 0; i < ys.length; i++ ) {
			data.get(i).add( x, ys[i] );	
		}
	}

	public void setSeriesName( String[] names ) {
		if( !isInitialized ) {
			init( names.length );
		}

		for( int i = 0; i < names.length; i++ ) {
			data.get(i).setKey( names[i] );
		}
	}

}






