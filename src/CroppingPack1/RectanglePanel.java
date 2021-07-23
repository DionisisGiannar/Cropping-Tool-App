package CroppingPack1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class RectanglePanel extends JPanel implements ActionListener, MouseListener, MouseMotionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Coordinates coord = new Coordinates();
	int x1, y1, x2, y2;
	
	RectanglePanel() {
		this.setVisible(true);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	public void setStartPoint(int x, int y) {
        this.x1 = x;
        this.y1 = y;
    }
	public void setEndPoint(int x, int y) {
        this.x2 = (x);
        this.y2 = (y);
    }
	
	//Mouse listeners

    public void mousePressed(MouseEvent e) {
        setStartPoint(e.getX(), e.getY());
        System.out.println("Mouse pressed at ("+e.getX()+","+e.getY()+")");
    }

    public void mouseDragged(MouseEvent e) {
        setEndPoint(e.getX(), e.getY());
        
        
    }

    public void mouseReleased(MouseEvent e) {
        setEndPoint(e.getX(), e.getY());
        
        System.out.println("Mouse released at ("+e.getX()+","+e.getY()+")");
        setCoordinates(x1, y1, x2, y2);
    }
    
    public void setCoordinates(int x1, int y1, int x2, int y2) {
		coord = new Coordinates(x1, y1, x2-x1 ,y2-y1);
        System.out.println("X: "+coord.getX()+"\nY: "+coord.getY()+"\nWidth: "+coord.getWidth()+"\nHeight: "+coord.getHeight());
        SwingUtilities.updateComponentTreeUI(this);
	}
	
	public void paint(Graphics g) {
       super.repaint();
        g.setColor(Color.RED);
        g.drawRect(coord.getX(), coord.getY(), coord.getWidth(), coord.getHeight());
        
        repaint();
       
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
	public void actionPerformed(ActionEvent e) {
		 

	}
	
}
