//import org.freehep.j3d.plot.*;
import java.awt.*;
import java.text.*;
import java.util.*;

import javax.swing.*;

public class XYZ3DChart {
    /*@ specification XYZ3DChart {
        Object andmed;
    
        String X_axis_name;
        String Y_axis_name;
        String Z_axis_name;
    
        void done;
        andmed, X_axis_name, Y_axis_name, Z_axis_name -> done{draw};
    
        X_axis_name = "X_axis";
        Y_axis_name = "Y_axis";
        Z_axis_name = "Z_axis";

    }@*/
    //============================================================
    //	andmed -> done { draw };
    //============3DPlot.draw: ================================================ 
    public void drawAll( Object andmed, String X_axis_name, String Y_axis_name,
            String Z_axis_name ) {

        JFrame frame = new JFrame( "XYZ3DChart" );
        frame.setLayout( new BorderLayout() );
        frame.setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
        SurfacePlot surf = new SurfacePlot();
        surf.setData( new XYZ3DBinned2DData(
                (Map<Double, LinkedList<Double>>) andmed ) );
        frame.add( surf, BorderLayout.CENTER );
        frame.setSize( 700, 700 );
        frame.setVisible( true );
        surf.setXAxisLabel( X_axis_name );
        surf.setYAxisLabel( Y_axis_name );
        surf.setZAxisLabel( Z_axis_name );
        System.out.println( "3DPlot.draw:  X_Axis:  " + surf.getXAxisLabel() );
        System.out.println( "3DPlot.draw:  Y_Axis:  " + surf.getYAxisLabel() );
        System.out.println( "3DPlot.draw:  Z_Axis:  " + surf.getZAxisLabel() );
        //	surf.setLogZscaling(true);
        // System.out.println("LogZscaling = " + surf.getLogZscaling() );   
    }

    //============================================================
    class XYZ3DBinned2DData implements Binned2DData {

        private int xBins;
        private int yBins = -1;
        private Rainbow rainbow = new Rainbow();
        private float[][] data;

        private double xMin = 1e20;
        private double xMax = -1e20;
        private double yMin = 1e20;
        private double yMax = -1e20;
        private double zMin = 1e20;
        private double zMax = -1e20;

        //============================================================
        public XYZ3DBinned2DData( Map<Double, LinkedList<Double>> values ) {

            int i, istep;
            int j, jstep;
            int k;
            int MinInd = 0;
            int MaxInd = 0;
            double first, last;
            double YAxisFlag = 1234e27;

            DecimalFormat dF = new DecimalFormat( "0.0000" );

            xBins = values.size() - 1;
            data = new float[xBins][];

            LinkedList<Double> yind = values.get( YAxisFlag );
            first = yind.getFirst();
            last = yind.getLast();
            // System.out.println( "First_Y: " + first + ", Last_Y: " + last );
            if ( first < last ) {
                i = 0;
                istep = 1;
                yMin = first;
                yMax = last;
            } else {
                i = xBins - 1;
                istep = -1;
                yMin = last;
                yMax = first;
            }
            k = 0;
            for ( Double x : values.keySet() ) {
                if ( x != YAxisFlag ) {
                    k++;
                    if ( x < xMin ) {
                        xMin = x;
                        MinInd = k;
                    }
                    if ( x > xMax ) {
                        xMax = x;
                        MaxInd = k;
                    }
                }
            }
            // System.out.println("Min_X Ind: "+MinInd+", Max_X_Ind: "+MaxInd+", k: "+k);

            j = 0;
            for ( Double x : values.keySet() ) {
                if ( x != YAxisFlag ) {
                    LinkedList<Double> ys = values.get( x );
                    if ( yBins == -1 ) {
                        yBins = ys.size();
                    }
                    if ( MaxInd < MinInd )
                        Collections.reverse( ys );

                    data[i] = new float[yBins];
                    // System.err.println( "x: " + i + ", y len: " + ys.size() );
                    for ( Double y : ys ) {
                        data[i][j] = y.floatValue();
                        if ( data[i][j] > zMax )
                            zMax = data[i][j];
                        if ( data[i][j] < zMin )
                            zMin = data[i][j];
                        // System.err.println( "y: " + j );
                        j++;
                    }
                    i = i + istep;
                    j = 0;
                }
            }

            if ( xMin == xMax )
                xMax++;
            if ( yMin == yMax )
                yMax++;
            if ( zMin == zMax )
                zMax++;

            System.out.println( "3DPlot.draw: xMin=" + dF.format( xMin )
                    + ", xMax=" + dF.format( xMax ) + ", xMin_Ind=" + MinInd
                    + ", xMax_Ind=" + MaxInd );
            System.out.println( "3DPlot.draw: yMin=" + dF.format( yMin )
                    + ", yMax=" + dF.format( yMax ) + ", yFirst="
                    + dF.format( first ) + ", yLast=" + dF.format( last ) );
            System.out.println( "3DPlot.draw: zMin=" + dF.format( zMin )
                    + ", zMax=" + dF.format( zMax ) );
        }

        //============================================================
        public int xBins() {
            return xBins;
        }

        //============================================================
        public int yBins() {
            return yBins;
        }

        //============================================================
        public float xMin() {
            return (float) xMin;
        }

        //============================================================
        public float xMax() {
            return (float) xMax;
        }

        //============================================================
        public float yMin() {
            return (float) yMin;
        }

        //============================================================
        public float yMax() {
            return (float) yMax;
        }

        //============================================================
        public float zMin() {
            return (float) zMin;
        }

        //============================================================
        public float zMax() {
            return (float) zMax;
        }

        //============================================================
        public float zAt( int xIndex, int yIndex ) {
            // System.err.println( "xIndex: "+xIndex+", yIndex: "+yIndex+", z: "+data[xIndex][yIndex]);
            return data[xIndex][yIndex];
        }

        //============================================================
        public javax.vecmath.Color3b colorAt( int xIndex, int yIndex ) {
            return rainbow.colorFor( zAt( xIndex, yIndex ) );
        }
    }
}
