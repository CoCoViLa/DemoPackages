import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.xy.*;
import javax.swing.*;

public class Graph
{
	/*@ 
	specification Graph {
		double x, y;		
		void init_ready, drawing_ready;
		String seriesName;
		seriesName -> init_ready{setSeriesName};
		x, y -> drawing_ready{draw};
	}
	@*/

	public int getDelay( String name, double x, double y ) {
		try {
			return Integer.valueOf( (String) ProgramContext.getFieldValue( name, "delay" ) );
		} catch(Exception e) { System.err.println( e ); }
		return 50;
	}

	XYSeries xys;
	ChartFrame frame;

	JFreeChart chart;
	boolean isInitialized;

	private void init() {
		XYSeriesCollection dataset = new XYSeriesCollection();

		xys = new XYSeries("Legend", false, true);
		dataset.addSeries(xys);
		chart = ChartFactory.createXYLineChart(
				"",
				"x", "y", dataset, PlotOrientation.VERTICAL, false, true, false );
		
		frame = new ChartFrame("Graph", chart );

		frame.setLocationByPlatform( true );

		frame.pack();
		frame.setVisible(true);
		isInitialized = true;
	}

    public void setSeriesName( String name ) {
	if( !isInitialized ) {
		init();
	}
	xys.setKey( name );
    }
	
    public void draw( final double x, final double y ) {
    	if( !isInitialized ) {
		init();
	}

	SwingUtilities.invokeLater( new Runnable() {
			public void run() {
				xys.add( x, y );	
			}
		} );
    }
    
}

























