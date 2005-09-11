import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

class Graph extends JFrame {
	/*@ 
	specification Graph {

		double x, y;		
		void drawing_ready;
		x, y -> drawing_ready{draw};
		

	}
	@*/

	public static ArrayList values = new ArrayList();
    	public static Graph instance = new Graph();

	public Graph()
        {
        	getContentPane().setLayout( new BorderLayout() );
        	JScrollPane scrPane = new JScrollPane( new Drawing() );
        	getContentPane().add( scrPane, BorderLayout.CENTER );
       }

	public static void draw( double x, double y )
        {
		Point p = new Point( Math.round( (float)x*100), Math.round( (float)y*100) );
        	values.add( p );
		//System.err.println( p + " x " + x + " y " + y );
        	instance.setTitle("Graph");
        	instance.setSize(500, 300);
        	instance.setVisible(true);
        	instance.repaint();
    	}	

	class Drawing
        extends JPanel
    {
        Point zero = new Point( 0, 0 );
        Dimension area = new Dimension( 0, 0 );

        Drawing()
        {
            super();
            setBackground( Color.white );
        }

        protected void paintComponent( Graphics g1 )
        {
            Graphics2D g = ( Graphics2D ) g1;
            
            area.setSize( getSize() );
            zero.move( area.width / 20, area.height / 2 );
            
            g.setColor( Color.white );
            g.fillRect( 0, 0, 1280, 800 );
            g.setColor( Color.black );
            g.setStroke( new BasicStroke( 2 ) );
            g.drawLine( zero.x, 0, zero.x, area.height );
            g.drawLine( 0, zero.y, area.width, zero.y );
            g.drawString( "0", zero.x - 8, zero.y + 12 );
            g.drawString( "x", area.width - 8, zero.y + 12 );
            g.drawString( "y", zero.x - 8, 12 );
            g.setColor( Color.blue );
            g.setStroke( new BasicStroke( 1 ) );

            for ( int i = 1; i < values.size(); i++ )
            {
                Point p1 = ( Point ) values.get( i - 1 );
                Point p2 = ( Point ) values.get( i );
            
                g.drawLine( getX( p1.x ), getY( p1.y ), getX( p2.x ), getY( p2.y ) );
            }
        }
        
        int getX( int x )
        {
            return zero.x + x;
        }
    
        int getY( int y )
        {
            return zero.y - y * 2;
        }
    }
}












