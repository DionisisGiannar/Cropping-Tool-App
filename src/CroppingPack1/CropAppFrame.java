package CroppingPack1;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.Label;
import java.awt.Font;


public class CropAppFrame extends JFrame { //Creates a Window Frame for the App

	private static final long serialVersionUID = 1L;

	final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // Screen Size
	ImageLabel imgLabel = new ImageLabel();


	public CropAppFrame() {
		getContentPane().setBackground(Color.LIGHT_GRAY);
		//this.setVisible(true);
		this.setTitle("Cropping Tool");
		this.setSize(screenSize);
		
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setUndecorated(false);
		getContentPane().setLayout(null);
		getContentPane().setSize(new Dimension(1920, 1080));
		this.setVisible(true);
		
		//image Label
		imgLabel.setSize(1835, 934);
		imgLabel.setLocation(73, 69);
		
		
		getContentPane().add(imgLabel);
		
		WidthHeightLabel wHLabel = new WidthHeightLabel(imgLabel);
		wHLabel.setBounds(73, 10, 1773, 53);
		
		getContentPane().add(wHLabel);
		repaint();
		/*
		RectanglePanel rectPanel = new RectanglePanel();
		rectPanel.setSize(1884, 1007);
		rectPanel.setLocation(10, 11);
		
		getContentPane().add(rectPanel);
		
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		*/
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	
}