import java.awt.event.*;

import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.category.*;

public class MultiSeriesBarChart {
    /*@ specification MultiSeriesBarChart {      
        alias xs;
        alias ys;
        void init_ready, drawing_ready;
        
        //NB! seriesNames if defined must match ys
        String[] seriesNames;
        String domainAxisLabel, rangeAxisLabel;
        domainAxisLabel = "Category";
        rangeAxisLabel = "Value";
        
        domainAxisLabel, rangeAxisLabel -> init_ready{initTitles};
        ys.length -> seriesNames {setSeriesNames};
        
        xs, ys, seriesNames, init_ready -> drawing_ready{draw};
    }@*/

    DefaultCategoryDataset dataset;
    ChartFrame frame;
    boolean isInitialized;
    JFreeChart chart;
    
    MultiSeriesBarChart() {
    }

    public void initTitles(String domainAxisLabel, String rangeAxisLabel) {
        if ( !isInitialized ) {
            init(domainAxisLabel, rangeAxisLabel);
        }
        
    }

    public String[] setSeriesNames( int length ) {

        String[] series = new String[length];
        for ( int i = 0; i < length; i++ ) {
            series[i] = "Series" + i;
        }
        return series;
    }

    private void init(String domainAxisLabel, String rangeAxisLabel) {
        dataset = new DefaultCategoryDataset();

        chart = ChartFactory.createBarChart( null, // chart title
                domainAxisLabel,
                rangeAxisLabel,
                dataset, // data
                PlotOrientation.VERTICAL, 
                true, // include legend
                false, // tooltips?
                false // URLs?
                );
        
        frame = new ChartFrame( "Bar Chart", chart );

        frame.addWindowListener( new WindowAdapter() {

            @Override
            public void windowClosing( WindowEvent e ) {
                System.out.println( "Chart frame closed - terminating program" );
                frame.dispose();
                ee.ioc.cs.vsle.api.ProgramContext.terminate();
            }
        } );

        frame.pack();
        frame.setVisible( true );
        isInitialized = true;
    }

    public void draw( Object[] xs, Object[] ys, String[] seriesNames ) {

        String category = "";
        boolean isXsingle = false;//xs.length != ys.length && xs.length == 1;
        if(xs.length != ys.length) {
            if(xs.length == 1) {
                isXsingle = true;
                category = xs[0].toString();
            } else {
                //if category can be single one or for each given series
                throw new RuntimeException("Incorrect category!");
            }
        }
        
        for ( int i = 0; i < ys.length; i++ ) {
            if(!isXsingle)
                category = xs[i].toString();
            dataset.addValue( (Number)ys[i], seriesNames[i], category );
        }
    }
}
