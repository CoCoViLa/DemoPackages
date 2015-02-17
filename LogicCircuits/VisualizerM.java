import javax.swing.*;
import java.awt.*;
import java.util.*;

class VisualizerM extends JFrame {
	/*@
	specification VisualizerM {
	    alias (int) inputs;
	    void drawing_ready;
	    inputs -> drawing_ready {draw};
	}@*/

	
	int offset = 30;
	int step = 0;
	public ArrayList<Integer> a = new ArrayList<Integer>();
	JPanel mainPanel;

	VisualizerM() {
		super();
		setResizable( false );
		mainPanel = new Drawing();
		getContentPane().add(mainPanel);
	}
	
	class  Drawing extends JPanel {
		Drawing(){
			super();
			setBackground(Color.white);
		}
		@Override
        protected void paintComponent(Graphics g1) {
		    if (a.size() == 0) return;
			Graphics2D g = (Graphics2D)g1;
			g.setColor(Color.white);
			g.fillRect(0, 0, Math.max(400, a.size()/step*40+offset), step*75+70);
			g.setColor(Color.black);
			int one = 45;
			int zero = 75;
			for ( int i = 1; i <= step; i++ ) {
			    g.drawString("1",17, zero*i-one);
                g.drawString("0",17, zero*i);
            }
			g.setStroke(new BasicStroke(2));
			g.drawLine(30, 0, 30, step*75+70);
			g.drawLine(0, step*zero+10, Math.max(400, a.size()/step*40+offset), step*zero+10);
			
			for (int i = 0; i<a.size()/step; i++) {
			    g.setColor(Color.black);
			    g.drawString(""+i, offset + i*40+15, step*zero+23);
			    g.setColor(Color.blue);
			    for(int j = 0; j < step; j++) {
			        int k = i*step+j;
			        int x = a.get(k);
			        g.drawLine(offset + i*40, zero*(j+1)-one*x, offset + i*40+40, zero*(j+1)-one*x);
			        if (i>=1) {
			            if (a.get(k-step) != x) {
			                g.drawLine(offset + i*40, zero*(j+1), offset + i*40, zero*(j+1)-one);
			            }
			        }
			    }
			}
		}
	}

	public void draw(int[] inps) {
	    for ( int in : inps ) {
            a.add( in );
        }
	    step = inps.length;
		setTitle("Visualizer");
		setSize(Math.max(400, a.size()/step*40+offset), step*75+70);
		setVisible(true);
		repaint();
	}
}
