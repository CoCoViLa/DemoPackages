import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import ee.ioc.cs.vsle.vclass.ClassField;
import ee.ioc.cs.vsle.vclass.ClassPainter;
import ee.ioc.cs.vsle.vclass.GObj;


public class PlacePainter extends ClassPainter {

    private final static Composite composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f);
    
    public void paint(final Graphics2D graphics, final float scale) {
        int numTokens = getNumTokens(vclass);
        int x = vclass.getX();
        int y = vclass.getY();
        int height = vclass.getRealHeight();
        int width = vclass.getRealWidth();

        graphics.setColor(Color.BLACK);

        if (numTokens < 6) {
            int tokenHeight = height / 4;
            int tokenWidth = width / 4;
            int hTokenHeight = tokenHeight / 2;
            int hTokenWidth = tokenWidth / 2;
            int cx = x + width / 2 - hTokenWidth;
            int lx = x + width / 4 - 3 * hTokenWidth / 2;
            int rx = x + 3 * width / 4 - hTokenWidth / 2; 
            int cy = y + height / 2 - hTokenHeight;
            int by = y + 3 * height / 4 - hTokenHeight / 2;
            int ty = y + height / 4 - 3 * hTokenHeight / 2;

            graphics.setComposite(composite);

            switch (numTokens) {
            case 5:
                graphics.fillOval(rx, cy, tokenWidth, tokenHeight);
            case 4:
                graphics.fillOval(lx, cy, tokenWidth, tokenHeight);
            case 3:
                graphics.fillOval(cx, ty, tokenWidth, tokenHeight);
            case 2:
                graphics.fillOval(cx, by, tokenWidth, tokenHeight);
            case 1:
                graphics.fillOval(cx, cy, tokenWidth, tokenHeight);
            default:
            }
        } else {
            String numStr = Integer.toString(numTokens);
            Rectangle bounds = graphics.getFont().getStringBounds(numStr, graphics.getFontRenderContext()).getBounds();

            int posX = x + width / 2 - bounds.width / 2;
            int posY = y + height / 2 - bounds.y / 2;
            graphics.drawString(numStr, posX, posY);
        }
    }
    
    private int getNumTokens(final GObj vclass) {
        int numTokens = 0;
        for (ClassField field : vclass.getFields()) {
            if ("tokens".equals(field.getName())) {
                try {
                    numTokens = Integer.parseInt(field.getValue());
                } catch (NumberFormatException e) {
                    // the scheme description should not be modifiead here
                    // there will be another interface for handling field value 
                    // change events etc.
                    // field.setValue("0");
                }
                break;
            }
        }
        return numTokens;
    }

}
