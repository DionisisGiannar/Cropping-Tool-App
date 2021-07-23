package CroppingPack1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;


public class ImageLabel extends JLabel implements ActionListener, MouseListener, MouseMotionListener{

	private static final long serialVersionUID = 1L;
	
	final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();// screenSize
	
	ImageIcon icon; // initialize the icon
	Image image; // initialize the image
	Image croppedImage; //initialize the cropped Image
	
	Coordinates coord = new Coordinates(); //Object for the Coordinates
	
	int x1, y1, x2, y2;// Local Coordinates
	
	/**
	 * Create the panel.
	 */
	public ImageLabel() {
		icon = new ImageIcon("test-Images\\images.jpg");
		
		image = icon.getImage();
		this.setVisible(true);
		
		// set coordinates for the Label
		this.setSize(screenSize);
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);

	}
	/**
	 * Sets the starting Coordinates
	 * @param x
	 * @param y
	 */
	public void setStartPoint(int x, int y) {
        this.x1 = x;
        this.y1 = y;
    }
	/**
	 * Sets the ending Coordinates
	 * @param x
	 * @param y
	 */
	public void setEndPoint(int x, int y) {
        this.x2 = (x);
        this.y2 = (y);
    }
	
	
    /*
     *Mouse listeners 
     */
	public void mousePressed(MouseEvent e) {
        setStartPoint(e.getX(), e.getY());
        System.out.println("Mouse pressed at ("+e.getX()+","+e.getY()+")");
    }

    public void mouseDragged(MouseEvent e) {
        setEndPoint(e.getX(), e.getY());
        repaint();
        
    }
    public void mouseReleased(MouseEvent e) {
        setEndPoint(e.getX(), e.getY());
        
        System.out.println("Mouse released at ("+e.getX()+","+e.getY()+")");
        setCoordinates(x1, y1, x2, y2);
    }
	@Override
	public void mouseMoved(MouseEvent e) {}
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void actionPerformed(ActionEvent e) {}
	
    /**
     * Sets the Coordinates of the Rectangle/Cropped image
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    public void setCoordinates(int x1, int y1, int x2, int y2) {
    	//checking all the possible directions so we dont have width or height under zero
    	if(x2>x1 && y2>y1) {
    		coord = new Coordinates(x1, y1, x2-x1 ,y2-y1);
    	} else if(x2<x1 && y2>y1) {
        	coord = new Coordinates(x2,y1,x1-x2,y2-y1);
        } else if( x2>x1 && y2<y1) {
        	coord = new Coordinates(x1,y2,x2-x1,y1-y2);
        }else if( x2<x1 && y2<y1) {
        	coord = new Coordinates(x2,y2,x1-x2,y1-y2);
        }//////
    	System.out.println("X: "+coord.getX()+"\nY: "+coord.getY()+"\nWidth: "+coord.getWidth()+"\nHeight: "+coord.getHeight());
        SwingUtilities.updateComponentTreeUI(this);
	}
	/**
	 * Paint the image and the Rectangles that shows the crop part
	 */
	public void paintComponent(Graphics g) {
		//Image 
		ImageDraw drawImg = new ImageDraw();//(Resize the image based on the canvas) that is given (this)
		drawImg.drawScaledImage(icon.getImage(), this, g);
        
		//Rectangles   
        g.setColor(new Color(128, 128, 128, 80));
        g.drawRect(coord.getX(), coord.getY(), coord.getWidth(), coord.getHeight());
        g.fillRect(coord.getX(), coord.getY(), coord.getWidth(), coord.getHeight());
       
            
        repaint();
	}
	
	public void CoppTheImage() {
		croppedImage = createImage(new FilteredImageSource(image.getSource(),
				new CropImageFilter(coord.getX(), coord.getY(), coord.getWidth(), coord.getHeight())));
	}
	
	
	
}
