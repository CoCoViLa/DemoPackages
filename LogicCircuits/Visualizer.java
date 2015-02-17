import javax.swing.*;
import java.awt.*;
import java.util.*;

class Visualizer extends JFrame {
	/*@
	specification Visualizer {
	    int in1, in2, in3;
		void done;
		in1, in2, in3 -> done {draw};
	}@*/

	
	int offset = 30;
	public static Visualizer test = new Visualizer();
	public static ArrayList a = new ArrayList();
	JPanel mainPanel;

	Visualizer() {
		super();
		mainPanel = new Drawing();
		getContentPane().add(mainPanel);
	}
	
	class  Drawing extends JPanel {
		Drawing(){
			super();
			setBackground(Color.white);
		}
		protected void paintComponent(Graphics g1) {
			Graphics2D g = (Graphics2D)g1;
			g.setColor(Color.white);
			g.fillRect(0, 0, 1024, 768);
			g.setColor(Color.black);
			g.drawString("1",17, 35);
			g.drawString("0",17, 72);
			g.drawString("1",17, 89);
			g.drawString("0",17, 117);
			g.drawString("1",17, 134);
			g.drawString("0",17, 165);
			g.setStroke(new BasicStroke(2));
			g.drawLine(30, 0, 30, 300);
			g.drawLine(0, 180, 1024, 180);
			g.setColor(Color.blue);
			for (int i = 0; i<a.size()/3; i++) {
				int x = ((Integer)a.get(i*3)).intValue();
				g.drawLine(offset + i*40, 75-40*x, offset + i*40+40, 75-40*x);
				if (i>=1) {
					if (((Integer)a.get(i*3-3)).intValue() != x) {
						g.drawLine(offset + i*40, 75, offset + i*40, 35);
					}
				}
				int y = ((Integer)a.get(i*3+1)).intValue();

				g.drawLine(offset + i*40, 120-40*y, offset + i*40+40, 120-40*y);
				if (i>=1) {
					System.out.println(a+" i="+i+" y="+y+ " hetke:"+(Integer)a.get(i*3+1)+" vana:"+(Integer)a.get(i*3-2));
					if (((Integer)a.get(i*3-2)).intValue()!= y) {
						System.out.println("drawing");
						g.drawLine(offset + i*40, 120, offset + i*40, 80);
					}
				}
				int z = ((Integer)a.get(i*3+2)).intValue();
				g.drawLine(offset + i*40, 165-40*z,  offset + i*40+40, 165-40*z);
				if (i>=1) {
					if (((Integer)a.get(i*3-1)).intValue()!= z) {
						g.drawLine(offset + i*40, 165, offset + i*40, 125);
					}
				}
			}
		}
	}

	public static void draw(int in1, int in2, int in3) {
		a.add(new Integer(in1));
		a.add(new Integer(in2));
		a.add(new Integer(in3));
		test.setTitle("Visualizer");
		test.setSize(Math.max(400, a.size()*15), 300);
		test.setVisible(true);
		test.repaint();
		//Graphcs g = new Graphics();
	}
}


