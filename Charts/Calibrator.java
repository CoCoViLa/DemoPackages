import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.event.*;

public class Calibrator {
    /*@ specification Calibrator {
        alias (double) in;
        alias (double) out;
        double min, max, step, sum, dflt;
        min = 0.0;
        max = 100.0;
        sum = -1;
        dflt = 0.0;
        step = 0.1;
        in, out.length, min, max, step, sum, dflt -> out, (Exception) {calibrate};
    }@*/
    
    public double[] calibrate(final double[] in, final int length, 
            final double min, final double max, final double step, final double sum, final double _default) throws Exception {
        final double[][] weightsContainer = new double[1][];
        
        if(in == null 
                || length == 0 
                || ( in.length > 0 && in.length != length) )
            throw new RuntimeException( "something is wrong here" );
        
        SwingUtilities.invokeAndWait( new Runnable() {
            public void run() {
                weightsContainer[0] = new _CalibratorDialog( in, length, min, max, step, sum, _default ).getValues();    
            }
        } );
        return weightsContainer[0];
    }
    
    private class _CalibratorDialog extends JDialog {
        
        private final double min, step, sum;
        private final int maxInt;
        private double[] values_orig;
        private double[] values;
        private List<JSlider> sliders = new ArrayList<JSlider>();
        private List<JLabel> labels = new ArrayList<JLabel>();
        
        private JButton jbOk = new JButton("OK");
        private JButton jbCancel = new JButton("Cancel");
        private JButton jbReset = new JButton("Reset");
        private DecimalFormat format = new DecimalFormat("#.##");
        boolean isGuiUpdating = false;
        
        private ActionListener lst = new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                if(e.getSource() == jbOk) {
                    setVisible( false );
                    dispose();
                } else if(e.getSource() == jbCancel) {
                    dispose();
                } else if(e.getSource() == jbReset) {
                    values = values_orig.clone();
                    updateSliders();
                }
            }
        };
        
        private _CalibratorDialog(double[] values, int length, 
                double min, double max, double step, double sum, double _default) {
            super((JFrame)null, "Calibrate");

            if(values.length==0) {
                this.values = new double[length];
                Arrays.fill( this.values, _default );
            } else
                this.values = values.clone();
            
            this.values_orig = this.values.clone();
            this.min = min;
            this.step = step;
            this.sum = sum;
            
            maxInt = (int)((max - min)/step);
            
            setContentPane( buildMainPanel() );
            pack();
            setModal( true );
            setResizable( false );
            setLocationByPlatform( true );
            setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
            setVisible( true );
        }
        
        private JPanel buildMainPanel() {
            JPanel jpMain = new JPanel(new BorderLayout());
            JPanel jpButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
            jpButtons.add( jbOk );
            jpButtons.add( jbReset );
            jpButtons.add( jbCancel );
            jbOk.addActionListener( lst );
            jbCancel.addActionListener( lst );
            jbReset.addActionListener( lst );
            
            jpMain.add( jpButtons, BorderLayout.SOUTH );
            
            JPanel jpSlidersFlow = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JPanel jpSliders = new JPanel();
            jpSliders.setLayout( new BoxLayout( jpSliders, BoxLayout.X_AXIS ) );
            
            for(int i = 0; i < values.length; i++) {
                jpSliders.add( addSliderPanel());
            }
            jpSlidersFlow.add( jpSliders );
            jpMain.add( jpSlidersFlow, BorderLayout.CENTER );
            updateSliders();
            return jpMain;
        }
        
        private JPanel addSliderPanel() {
            final JSlider jslider = new JSlider(JSlider.VERTICAL, 0, maxInt, 0);
            sliders.add( jslider );
            
            JPanel jpSingleSliderPane = new JPanel();
            jpSingleSliderPane.setLayout( new BoxLayout( jpSingleSliderPane, BoxLayout.Y_AXIS ) );
            JPanel flowTop = new JPanel(new FlowLayout(FlowLayout.CENTER));
            flowTop.add( jslider );
            jpSingleSliderPane.add( flowTop );
            final JLabel jlSlider = new JLabel( "[" + formatValue( jslider.getValue() ) + "]" );
            JPanel flowBottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
            jlSlider.setHorizontalAlignment( JLabel.CENTER );
            labels.add( jlSlider );
            flowBottom.add( jlSlider );
            jpSingleSliderPane.add( flowBottom );
            
            jslider.addChangeListener( new ChangeListener() {
                
                public void stateChanged( ChangeEvent e ) {
                    double value = calcValue(jslider.getValue());
                    jlSlider.setText( "[" + format.format( calcValue(jslider.getValue())) + "]" );
                    if(isGuiUpdating)
                        return;
                    values[sliders.indexOf( jslider )] = value;
                    checkAdjustments();
                }
            });
            
            return jpSingleSliderPane;
        }
        
        private double calcValue(int currentValue) {
            return min + currentValue * step;
        }
        
        private String formatValue(int currentValue) {
            return format.format( calcValue(currentValue));
        }
        
        private void updateSliders() {
            isGuiUpdating = true;
            for ( int i = 0; i < sliders.size(); i++ ) {
                sliders.get( i ).setValue( (int) (( values[i] - min )/step) );
            }
            isGuiUpdating = false;
            checkAdjustments();
        }
        
        private void checkAdjustments() {
            double _sum = 0;
            for(JSlider slider : sliders ) {
                _sum += calcValue(slider.getValue());
            }

            Color c;
            boolean enabled;
            if( sum != -1 && _sum > sum) {
                c = Color.red;
                enabled = false;
            } else {
                c = Color.black;
                enabled = true;
            }
            
            for ( JLabel lbl : labels ) {
                lbl.setForeground( c );
            }
            
            jbOk.setEnabled( enabled );
        }
        
        private double[] getValues() {
            return values;
        }
        
        @Override
        public void dispose() {
            super.dispose();
            
            jbOk.removeActionListener( lst );
            jbCancel.removeActionListener( lst );
            jbReset.removeActionListener( lst );
            lst = null;
        }
    }
    
    public static void main( String[] args ) throws Exception {
        double[] in = new double[] { 40, 30, 20, 50 };
        (new Calibrator()).calibrate( in, in.length, 1.5, 60.5, 0.01, -1, 20.0 );
    }
}








