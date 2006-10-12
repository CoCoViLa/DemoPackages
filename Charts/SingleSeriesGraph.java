import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.xy.*;

public class SingleSeriesGraph
{
	/*@ 
	specification SingleSeriesGraph {
		double x, y;		
		void init_ready, drawing_ready;
		String seriesName;
		seriesName -> init_ready{setSeriesName};
		x, y -> drawing_ready{draw};
		
	}
	@*/
	
	SingleSeriesGraph() {
		init();
	}
	
	XYSeries xys;
	ChartFrame frame;
	DefaultTableXYDataset dataset;
	JFreeChart chart;

	private void init() {

		dataset = new DefaultTableXYDataset();
		xys = new XYSeries("Legend", true, false);
		dataset.addSeries(xys);
		chart = ChartFactory.createXYLineChart(
				"",
				"x", "y", dataset, PlotOrientation.VERTICAL, true, true, false );
		
		frame = new ChartFrame("Graph", chart );

		frame.pack();
		frame.setVisible(true);
	}

	public void setSeriesName( String name ) {
		xys.setKey( name );
	}
	
    public void draw( double x, double y ) {
    	
    	try {
    		Thread.sleep(50);
    	} catch(Exception e ) {}
    	xys.add( x, y );
    }
    
}



