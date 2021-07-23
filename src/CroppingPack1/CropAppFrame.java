package CroppingPack1;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.Label;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SwingConstants;


public class CropAppFrame extends JFrame implements ActionListener{ //Creates a Window Frame for the App

	private static final long serialVersionUID = 1L;

	final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // Screen Size
	ImageLabel imgLabel;


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
		 
		 JPanel srchFileBtnPanel = new JPanel();
		 srchFileBtnPanel.setForeground(Color.WHITE);
		 srchFileBtnPanel.setBackground(new Color(0,0,0,0));
		 srchFileBtnPanel.setBounds(920, 500, 93, 35);
		 
		 getContentPane().add(srchFileBtnPanel);
		 
		 JButton srchFileButton = new JButton("Search");
		 
		 
		 srchFileBtnPanel.add(srchFileButton);
		 
		 JLabel txtLabel = new JLabel("Browse for an Image");
		 txtLabel.setHorizontalAlignment(SwingConstants.CENTER);
		 txtLabel.setText("Browse for an Image");
		 txtLabel.setFont(new Font("MathJax_Typewriter", Font.PLAIN, 16));
		 txtLabel.setForeground(Color.BLACK);
		 
		 txtLabel.setBounds(871, 473, 193, 21);
		 getContentPane().add(txtLabel);

		//image Label
		imgLabel = new ImageLabel();
		imgLabel.setSize(1835, 934);
		imgLabel.setLocation(73, 69);
		
		
		getContentPane().add(imgLabel);
		
		//Dimensions Label
		WidthHeightLabel wHLabel = new WidthHeightLabel(imgLabel);
		wHLabel.setVisible(false);
		wHLabel.setBounds(73, 10, 1773, 53);
		
		getContentPane().add(wHLabel);
		repaint();
		
		//File Search Button
		
		
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		imgLabel.fileName = getFileName();
		checkFileName();
	}
	
	public void checkFileName() {
		
	}
	
	/**
	 * Opens the file Browser
	 * @return the file name the  user choose
	 */
	public String getFileName() {
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(null);
		File file = chooser.getSelectedFile();
		String filename = file.getName();
		System.out.println("You have selected: " + filename);
		//chooseFileButton.setVisible(false);
		
		return filename;
	}
}