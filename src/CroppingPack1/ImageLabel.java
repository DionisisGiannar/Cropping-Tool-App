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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.swing.BorderFactory;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.lang.Math;



public class ImageLabel extends JLabel implements ActionListener, MouseListener, MouseMotionListener{

	private static final long serialVersionUID = 1L;
	
	final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();// screenSize
	List<String> typeFiles = Arrays.asList(".jpg", ".jpeg", ".png", ".tif", ".tiff");

	ImageDraw drawImg; // initialize object that draws the image
	BufferedImage origImage;
	ImageIcon icon; // initialize the icon
	Image image; // initialize the image
	Image croppedImage; //initialize the cropped Image
	Coordinates coord = new Coordinates(0,0,0,0); //Object for the Coordinates
	Coordinates correctCoord = new Coordinates();
	boolean isRepainted = false;
	int x1, y1, x2, y2, xA, yA, xB, yB, width, height, croppX1, croppY1,croppX2, croppY2, croppW, croppH;// Local Coordinates
	
	String folderPath;
	String imagePath;
	
	/**
	 * Create the panel.
	 */
	public ImageLabel(String filePath) {
		imagePath = filePath;
		try {
			origImage = ImageIO.read(new File(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		icon = new ImageIcon(filePath);
		
		image = icon.getImage();
		this.setVisible(true);
		
		// set coordinates for the Label
		this.setSize(screenSize);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {}
	
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
        repaint();
    }

    public void mouseDragged(MouseEvent e) {
        setEndPoint(e.getX(), e.getY());
        setLocalCoordinates(x1, y1, x2, y2);
        repaint();
        
    }
    public void mouseReleased(MouseEvent e) {
        setEndPoint(e.getX(), e.getY());
        setLocalCoordinates(x1, y1, x2, y2);
        System.out.println("Imgx1: "+drawImg.x1+", Imgy1:"+drawImg.y1+", Imgx2: "+drawImg.x2+", Imgy2:"+drawImg.y2+", ImgWidth: "+drawImg.imgWidth+", ImgHeight: "+drawImg.imgHeight);
        System.out.println("IconWidth: "+icon.getIconWidth()+", IconHeight: "+icon.getIconHeight());
		
        System.out.println("x1: "+x1+", y1: "+y1+", x2: "+x2+", y2: "+y2);
        System.out.println("xA: "+xA+", yA:"+yA+", xB: "+xB+", yB:"+yB+", width: "+width+", height: "+height);

        repaint();
    }
	@Override
	public void mouseMoved(MouseEvent e) {}
	@Override
	public void mouseClicked(MouseEvent e) {
		setLocalCoordinates(0,0,0,0);
		repaint();
	}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
		
	/**
	 * Paint the image and the Rectangles that shows the crop part
	 */
	public void paintComponent(Graphics g) {
		//super.paintComponent(g);
		//Image 
		//g.drawImage(image, 0, 0, this);
		drawImg = new ImageDraw();//(Resize the image based on the canvas) that is given (this)
		drawImg.drawScaledImage(origImage, this, g);
        
		//Rectangles   
        g.setColor(new Color(128, 128, 128, 80));
        g.drawRect(xA, yA, width, height);
        g.fillRect(xA, yA, width, height);
       
        if(!isRepainted) {//check if the image is up to date
        	repaint();
        	isRepainted=true;
        }
    	//repaint();
        
	}
	/**
     * Sets the Coordinates of the Rectangle/Cropped image
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    public void setLocalCoordinates(int x1, int y1, int x2, int y2) {
    	//checking all the possible directions so we dont have width or height under zero
    	if(x2>x1 && y2>y1) {    	
    		xA = x1; yA = y1; xB = x2; yB = y2; width = x2-x1; height = y2-y1;
    	
    	} else if(x2<x1 && y2>y1) {
    		xA = x2; yA = y1; xB = x1; yB = y2; width = x1-x2; height = y2-y1;
        
    	} else if( x2>x1 && y2<y1) {
        	xA = x1; yA = y2; xB = x2; yB = y1; width = x2-x1; height = y1-y2;

    	}else if( x2<x1 && y2<y1) {        
    		xA = x2; yA = y2; xB = x1; yB = y1; width = x1-x2; height = y1-y2;
    	}
    	if(x1==0 && y1==0 && x2==0 && y2==0) {
    		xA =0; yA=0;xB=0;yB=0;width=0;height=0;
    	}
    	correctLocalCoordinates();
    	setCoordinates(xA,yA,width,height);

    	repaint();
        //SwingUtilities.updateComponentTreeUI(this);
	}
	
	public void correctLocalCoordinates() {
		if(xA<drawImg.x1) {
    		this.xA = drawImg.x1;
    	}
    	if(yA<drawImg.y1) {
    		this.yA = drawImg.y1;
    	}
    	if(xB>drawImg.x2) {
    		this.xB = drawImg.x2;
    	}
    	if (yB>drawImg.y2) {
    		this.yB = drawImg.y2;
    	}
    	if(xA<=0) {
    		xA=0;
    	}
    	if(yA<=0) {
    		yA=0;
    	}
    	if(xB<=0) {
    		xB=0;
    	}
    	if(yB<=0) {
    		yB=0;
    	}
    	this.width = xB - xA;
    	this.height = yB - yA;
    	if(width<=0) {
    		width=0;
    	}
    	if(height<=0) {
    		height=0;
    	}
    	
    	setCoordinates(xA,yA,width,height);
    	
	}
	public void setCoordinates(int x, int y, int w,int h){
		
		coord.setX(x);
		coord.setY(y);
		coord.setWidth(w);
		coord.setHeight(h);
	
	}
	
	public void setCroppCoordinates(){
		/**
		 * diagonal = squareRoot( (width)^2 + ( heigt)^2)
		 */
		
		System.out.println("getX: "+coord.getX()+", getY: "+coord.getY());
		if (drawImg.imgWidth < drawImg.canvWidth && drawImg.imgHeight < drawImg.canvHeight) {
            // when the image is smaller than the canvas
             croppX1 = coord.getX()-drawImg.x1;
             croppY1 = coord.getY()-drawImg.y1;
             croppW = coord.getWidth();
             croppH = coord.getHeight();
        } else { 
        	//calculating the percent that the Diagonal of the image grows
        	float origImgAspect = (float) icon.getIconHeight() / icon.getIconWidth();
        	int scaledHeight = drawImg.y2 - drawImg.y1;
        	int scaledWidth = drawImg.x2 -drawImg.x1;

        	float origDiag = (float) Math.sqrt(Math.pow(icon.getIconHeight(),2) + Math.pow(icon.getIconWidth(),2));
        	float scaledDiag = (float) Math.sqrt(Math.pow(scaledWidth, 2) + Math.pow(scaledHeight, 2));
        	
        	float percentDiagon = origDiag / scaledDiag;
        	
        	// correct the rectangle coordinates and dimensions 
        	if(origImgAspect<1) {
        		float x;
        		x = (float) (( coord.getX()-drawImg.x1 ) / percentDiagon);
        		croppX1 = (int) (x);
        		x = (float) (( coord.getY()-drawImg.y1 ) / percentDiagon);
            	croppY1 =  (int) (x);
            	x = (float) (coord.getWidth() / percentDiagon); 
            	croppW = (int) (x);
            	x = (float) (coord.getHeight() / percentDiagon);
            	croppH = (int) (x);
        		
        	}else if(origImgAspect>1) {
        		float x;
        		x = (float) (( coord.getX()-drawImg.x1 )  * percentDiagon);
        		croppX1 = (int) (x);
        		x = (float) (( coord.getY()-drawImg.y1 )  * percentDiagon);
            	croppY1 =  (int) (x);
            	x = (float) (coord.getWidth() * percentDiagon); 
            	croppW = (int) (x);
            	x = (float) (coord.getHeight() * percentDiagon);
            	croppH = (int) (x);
        	}
        }
		
		
	}
	
	/*
	 * display cropped image without save 
	 */
	public void displayCropped() {
		setCoordinates(xA,yA,width,height);
		setCroppCoordinates();

		System.out.println("XXX: " +croppX1 + "YYY: " +croppY1);
	
		origImage = origImage.getSubimage(croppX1, croppY1, croppW, croppH);
		
		System.out.println("Cropped Image Dimension: "+origImage.getWidth()+"x"+origImage.getHeight());
		
		repaint();
	}
	
	public void CroppTheImage() {
		setCoordinates(xA,yA,width,height);
		setCroppCoordinates();

		System.out.println("XXX: " +croppX1 + "YYY: " +croppY1);
		BufferedImage subImage;
		if(croppX1<=0 && croppY2<=0 && croppW<=0 && croppH<=0) {
			subImage = origImage;

		}else {
			subImage = origImage.getSubimage(croppX1, croppY1, croppW, croppH);

		}
		
		System.out.println("Cropped Image Dimension: "+subImage.getWidth()+"x"+subImage.getHeight());
		
		File outputfile = new File("");
		
		for(String tf : typeFiles) {
			if(folderPath.contains(tf)) {
				folderPath = folderPath.replace(tf,".jpg");
				outputfile = new File(folderPath);
				folderPath = outputfile.getPath();
				break;
			}else {
				folderPath = folderPath + ".jpg";
				outputfile = new File(folderPath);
				break;
			}

		}
		
		try {
			ImageIO.write(subImage, "jpg", outputfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Image cropped successfully: "+outputfile.getPath());
	}
	
	
	
}
