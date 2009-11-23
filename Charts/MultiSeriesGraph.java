import java.awt.*;
import java.awt.event.*;

import org.jfree.chart.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.*;
import org.jfree.data.xy.*;

class MultiSeriesGraph {
    /*@ specification MultiSeriesGraph {
        double x;
        alias (double) ys;
        void init_ready, drawing_ready, paintAll, done, updated;
        boolean repaintImmediately;
        //NB! seriesNames if defined must match ys
        String[] seriesNames;
        seriesNames -> init_ready{setSeriesName};
        x, ys, repaintImmediately -> drawing_ready, (Exception){draw};
        paintAll, repaintImmediately ->done{drawAll};
        boolean axisAlwaysIncludeZero, showSeparateAxis;
        float lineThickness;
        lineThickness, axisAlwaysIncludeZero, showSeparateAxis -> updated {updateParams};
    }@*/

    XYSeriesCollection dataset;
    ChartFrame frame;
    XYPlot plot;
    boolean isInitialized;

    MultiSeriesGraph() {
    }

    private void init( int length ) {
        dataset = new XYSeriesCollection();

        for ( int i = 0; i < length; i++ ) {
            dataset.addSeries( new XYSeries( "" + i, false, true ) );
        }

        JFreeChart chart = ChartFactory.createXYLineChart( "", "x", "y",
                dataset, PlotOrientation.VERTICAL, true, true, false );

        plot = chart.getXYPlot();

        frame = new ChartFrame( "Graph", chart );
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

    public void updateParams( float lineThickness,
            boolean axisAlwaysIncludeZero, boolean showSeparateAxis ) {

        plot.getRenderer().setStroke( new BasicStroke( lineThickness ) );

        NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
        domainAxis.setAutoRangeIncludesZero( axisAlwaysIncludeZero );

        if ( showSeparateAxis && dataset.getSeriesCount() > 1 ) {
            for ( int i = 0, len = dataset.getSeriesCount(); i < len; i++ ) {
                NumberAxis axis = new NumberAxis( dataset.getSeries( i )
                        .getKey().toString() );
                axis.setAutoRangeIncludesZero( axisAlwaysIncludeZero );
                plot.setRangeAxis( i, axis );
            }
        } else {
            NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
            rangeAxis.setAutoRangeIncludesZero( axisAlwaysIncludeZero );
        }
    }

    public void draw( final double x, final double[] ys,
            final boolean repaintImmediately ) throws Exception {
        if ( !isInitialized ) {
            init( ys.length );
        }

        for ( int i = 0; i < ys.length; i++ ) {
            dataset.getSeries( i ).add( x, ys[i], repaintImmediately );
        }
    }

    public void setSeriesName( String[] names ) {
        if ( !isInitialized ) {
            init( names.length );
        }

        for ( int i = 0; i < names.length; i++ ) {
            dataset.getSeries( i ).setKey( names[i] );
        }
    }

    public void drawAll( boolean repaintImmediately ) {
        if ( !repaintImmediately ) {
            try {
                dataset.validateObject();
            } catch ( Exception e ) {
                System.err.println( "DrawAll failed: " + e.getMessage() );
            }
            //			for( int i = 0, len = dataset.getSeriesCount(); i < len; i++ ) {
            //				dataset.getSeries(i).fireSeriesChanged();	
            //			}
        }
    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main( String[] args ) throws Exception {
        MultiSeriesGraph g = new MultiSeriesGraph();
        g.init( 2 );
        g.setSeriesName( new String[] { "one", "two" } );
        g.updateParams( 2f, false, true );
        g.draw( 1, new double[] { 2, 3 }, true );
        g.draw( 2, new double[] { 4, 5 }, true );
        g.draw( 3, new double[] { 6, 7 }, true );
    }
}
