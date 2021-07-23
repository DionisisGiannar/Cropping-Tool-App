package CroppingPack1;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;

public class ImageDraw {
    
    public void drawScaledImage(Image image, Component canvas, Graphics g) {
        int imgWidth = image.getWidth(null);
        int imgHeight = image.getHeight(null);
        double imgAspect = (double) imgHeight / imgWidth;
 
        int canvWidth = canvas.getWidth();
        int canvHeight = canvas.getHeight();
        double canvAspect = (double) canvHeight / canvWidth;
 
        int x1 = 0; // top left X position
        int y1 = 0; // top left Y position
        int x2 = 0; // bottom right X position
        int y2 = 0; // bottom right Y position
         
        if (imgWidth < canvWidth && imgHeight < canvHeight) {
            // when the image is smaller than the canvas
            x1 = (canvWidth - imgWidth)  / 2;
            y1 = (canvHeight - imgHeight) / 2;
            x2 = imgWidth + x1;
            y2 = imgHeight + y1;
             
        } else { 
            if (canvAspect > imgAspect) { 
                y1 = canvHeight;
                // keep image aspect ratio
                canvHeight = (int) (canvWidth * imgAspect);
                y1 = (y1 - canvHeight) / 2;
            } else { 
                x1 = canvWidth;
                // keep image aspect ratio
                canvWidth = (int) (canvHeight / imgAspect);
                x1 = (x1 - canvWidth) / 2;
            }
            x2 = canvWidth + x1;
            y2 = canvHeight + y1;
        }
 
        g.drawImage(image, x1, y1, x2, y2, 0, 0, imgWidth, imgHeight, null);
    }
}