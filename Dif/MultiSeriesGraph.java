import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.xy.*;

class MultiSeriesGraph {
    /*@ specification MultiSeriesGraph {
	double x;
	alias (double) ys;
	void init_ready, drawing_ready, paintAll, done;
	boolean repaintImmediately;
	//NB! seriesNames if defined must match ys
	String[] seriesNames;
	seriesNames -> init_ready{setSeriesName};
	x, ys, repaintImmediately -> drawing_ready{draw};
	paintAll, repaintImmediately ->done{drawAll};
    }@*/

	XYSeriesCollection dataset;
	ChartFrame frame;
	boolean isInitialized;

	MultiSeriesGraph() {
	}

	private void init( int length ) {
		dataset = new XYSeriesCollection();

		for( int i = 0; i < length; i++ ) {
			dataset.addSeries( new XYSeries( ""+i, false, true ) );
		}

		JFreeChart chart = ChartFactory.createXYLineChart("", "x", "y",
                		dataset, PlotOrientation.VERTICAL, true, true, false);
        
        		frame = new ChartFrame("Graph", chart);

		frame.addWindowListener( new WindowAdapter() {

			@Override
			public void windowClosing( WindowEvent e ) {
				System.out.println( "Chart frame closed - terminating program" );
				frame.dispose();
				ee.ioc.cs.vsle.api.ProgramContext.terminate();
			}
		} );

		frame.pack();
		frame.setVisible(true);
		isInitialized = true;
	}

	public void draw( final double x, final double[] ys, final boolean repaintImmediately ) {
    		if( !isInitialized ) {
			init( ys.length );
		}

		for( int i = 0; i < ys.length; i++ ) {
			dataset.getSeries(i).add( x, ys[i], repaintImmediately );	
		}
	}

	public void setSeriesName( String[] names ) {
		if( !isInitialized ) {
			init( names.length );
		}

		for( int i = 0; i < names.length; i++ ) {
			dataset.getSeries(i).setKey( names[i] );
		}
	}

	public void drawAll( boolean repaintImmediately ) {
//		System.out.println( "drawAll" );
		if( !repaintImmediately ) {
			try { 
				dataset.validateObject();
			} catch(Exception e) {
				System.err.println( "DrawAll failed: " + e.getMessage() );
			}
//			for( int i = 0, len = dataset.getSeriesCount(); i < len; i++ ) {
//				dataset.getSeries(i).fireSeriesChanged();	
//			}
		}
	}
}




