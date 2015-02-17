import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.*;
import java.awt.*;
import java.io.*;

import javax.imageio.*;

import ee.ioc.cs.vsle.vclass.ClassField;
import ee.ioc.cs.vsle.vclass.ClassPainter;


public class ExplosionPainter extends ClassPainter {

	private BufferedImage explosion = null;
	private BufferedImage cloud = null;
	
	@Override
	public void paint(Graphics2D graphics, float scale) {
	    
		ClassField[] f = new ClassField[5];
		int i = 0;
        boolean drawOriginal = false;
		for (ClassField field : vclass.getFields()) {
			f[i++] = field;
			if (field.getValue() == null) {
				//System.err.println(f[i].getName() + " undefined");
                drawOriginal = true;
				break;
			}
		}
        
        if (drawOriginal) {
            vclass.drawClassGraphics(graphics, scale);
            return;
        }
        
		double x = vclass.getX() + Double.valueOf(f[0].getValue());
		double y = vclass.getY() + Double.valueOf(f[1].getValue());
		double width = Double.valueOf(f[2].getValue());
		double height = Double.valueOf(f[3].getValue());
		double angle = Double.valueOf(f[4].getValue());
		
		if( getExplosion() != null ) {
			Image tmp = explosion.getScaledInstance( vclass.getWidth()*3, vclass.getHeight()*3, java.awt.Image.SCALE_FAST );
			graphics.drawImage(tmp, vclass.getX() - vclass.getWidth(), vclass.getY() - vclass.getHeight(), null );
		}
		
		if( getCloud() != null ) {
			graphics.translate(vclass.getX(), vclass.getY());
			graphics.rotate(angle);
			graphics.translate(-vclass.getX(), -vclass.getY());
			Image tmp = cloud.getScaledInstance( (int)width, (int)height, java.awt.Image.SCALE_FAST );
			graphics.drawImage(tmp, (int)x, (int)y, null );
			graphics.translate(vclass.getX(), vclass.getY());
			graphics.rotate(-angle);
			graphics.translate(-vclass.getX(), -vclass.getY());
		} else {
			Ellipse2D.Double ellipse = new Ellipse2D.Double();
			ellipse.x = x;
			ellipse.y = y;
			ellipse.width = width;
			ellipse.height = height;

			graphics.translate(vclass.getX(), vclass.getY());
			graphics.rotate(angle);
			graphics.translate(-vclass.getX(), -vclass.getY());

			graphics.setColor(Color.RED);
			graphics.fill(ellipse);

			graphics.translate(vclass.getX(), vclass.getY());
			graphics.rotate(-angle);
			graphics.translate(-vclass.getX(), -vclass.getY());
		}
	}

	private BufferedImage getExplosion() {
		
		if( explosion == null ) {
			InputStream stream = ExplosionPainter.this.getClass().getResourceAsStream("explosion.gif");
			if( stream != null ) {
				try {
					explosion = ImageIO.read(stream);
				} catch (IOException e) {
					System.err.println( "Unable to load explosion" );
				}
			} else {
				System.err.println( "Unable to load explosion, stream null" );
			}
		}
		
		return explosion;
	}
	
	private BufferedImage getCloud() {
		
		if( cloud == null ) {
			InputStream stream = ExplosionPainter.this.getClass().getResourceAsStream("cloud.gif");
			if( stream != null ) {
				try {
					cloud = ImageIO.read(stream);
				} catch (IOException e) {
					System.err.println( "Unable to load cloud" );
				}
			} else {
				System.err.println( "Unable to load cloud, stream null" );
			}
		}
		
		return cloud;
	}
}
