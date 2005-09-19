import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.xy.*;

public class Graph //extends JFrame 
{
	/*@ 
	specification Graph {
		double x, y;		
		void init_ready, drawing_ready;
		String seriesName;
		seriesName -> init_ready{init};
		x, y -> drawing_ready{draw};
		
	}
	@*/

	XYSeries xys;
	ChartFrame frame;
	DefaultTableXYDataset dataset;
	JFreeChart chart;
    	private static Graph instance = null;

    public Graph() {
    	
    }
    
	void init( String seriesName ) {
		if( instance == null ) {
			instance = new Graph();			
		}
		instance.dataset = new DefaultTableXYDataset();
		instance.xys = new XYSeries(seriesName, true, false);
		instance.dataset.addSeries(instance.xys);
		instance.chart = ChartFactory.createXYLineChart(
				"",
				"x", "y", instance.dataset, PlotOrientation.VERTICAL, true, true, false );
		
		instance.frame = new ChartFrame("Graph", instance.chart );

		instance.frame.pack();
		instance.frame.setVisible(true);
	}

	void init() {
		init("Legend");
	}

    public static void draw( double x, double y ) {
	if( instance == null ) {		
		instance = new Graph();
    		instance.init();
	}
	instance.xys.add( x, y );
    }
    
}


