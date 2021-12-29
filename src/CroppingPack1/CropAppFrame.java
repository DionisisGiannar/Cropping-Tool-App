package CroppingPack1;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JSlider;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.FlowLayout;


public class CropAppFrame extends JFrame implements ActionListener, ChangeListener{ //Creates a Window Frame for the App

	private static final long serialVersionUID = 1L;

	final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // Screen Size
	List<String> typeFiles = Arrays.asList(".jpg", ".jpeg", ".png", ".tif", ".tiff");
	ImageLabel imgLabel;
	JButton srchFileButton;
	JButton btnSaveAs, btnNewImage;
	JLabel txtLabel;
	WidthHeightLabel wHLabel;
	
	String filePath, folderPath;
	
	private JSlider sldrWidth;
	private JLabel lblNumbWidth;
	private JPanel pnlControls;
	private JLabel lblTxtHeight;
	private JLabel lblNumbHeight;
	private JSlider sldrHeight;
	private JLabel lblTxtX;
	private JLabel lblNumbX;
	private JSlider sldrX;
	private JSlider sldrY;
	private JLabel lblTxtY;
	private JLabel lblNumbY;
	JPanel pnlButtons;
	
	/**
	 * constructor
	 */
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
		
		//File Search Button
		srchFileButton = new JButton("Search");
		srchFileButton.setBounds(920, 500, 90, 30);
		srchFileButton.setFont(new Font("MathJax_Typewriter", Font.BOLD, 14));
		srchFileButton.setActionCommand("searchFile");
		srchFileButton.addActionListener(this);
		//text label
		txtLabel = new JLabel("Browse for an Image");
		txtLabel.setHorizontalAlignment(SwingConstants.CENTER);
		txtLabel.setText("Browse for an Image");
		txtLabel.setFont(new Font("MathJax_Typewriter", Font.PLAIN, 16));
		txtLabel.setForeground(Color.BLACK);	
		txtLabel.setBounds(851, 477, 225, 21);
		
		getContentPane().add(txtLabel);
		getContentPane().add(srchFileButton);
		
		//Buttons
		pnlButtons = new JPanel();
		pnlButtons.setBounds(783, 978, 399, 37);
		pnlButtons.setBackground(new Color(0,0,0,0) );
		pnlButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		//New Image Button
		btnNewImage = new JButton("New Image");
		pnlButtons.add(btnNewImage);
		btnNewImage.setActionCommand("new");
		btnNewImage.addActionListener(this);
		//save as button
		btnSaveAs = new JButton("Save as");
		pnlButtons.add(btnSaveAs);
		btnSaveAs.setActionCommand("saveAs");
		btnSaveAs.addActionListener(this);
		//crop image button
		JButton btnCrop = new JButton("Crop The Image");
		btnCrop.setActionCommand("crop");
		pnlButtons.add(btnCrop);
		btnCrop.addActionListener(this);
		
		//Control Panel
		pnlControls = new JPanel();
		pnlControls.setBorder(BorderFactory.createLineBorder(Color.black));
		pnlControls.setBounds(12, 66, 200, 909);
		pnlControls.setBackground(new Color(0,0,0,0));
		
		pnlControls.setLayout(null);
		
		//SLIDER SIZE
		sldrWidth = new JSlider(0,1920,0);
		sldrWidth.setBackground(new Color(0,0,0,0));
		sldrWidth.setBounds(12, 59, 176, 16);
        // setChangeListener
		sldrWidth.addChangeListener(this);
		
		pnlControls.add(sldrWidth);
		
		
		JLabel lblTxtWidth = new JLabel("Width:");
		lblTxtWidth.setFont(new Font("MathJax_Typewriter", Font.PLAIN, 14));
		lblTxtWidth.setBounds(12, 24, 51, 23);
		pnlControls.add(lblTxtWidth);
		
		lblNumbWidth = new JLabel();
		lblNumbWidth.setBackground(new Color(0,0,0,0));
		lblNumbWidth.setFont(new Font("C059", Font.PLAIN, 15));
		lblNumbWidth.setBounds(60, 24, 70, 22);
		
		// set the text of label
		//lblNumbWidth.setText(""+ sldrWidth.getValue());
		pnlControls.add(lblNumbWidth);
		
		//Slider for Height
		sldrHeight = new JSlider(0, 1920, 0);
		sldrHeight.setBackground(new Color(0, 0, 0, 0));
		sldrHeight.setBounds(12, 122, 176, 16);
		sldrHeight.addChangeListener(this);
		
		pnlControls.add(sldrHeight);
		
		lblTxtHeight = new JLabel("Height:");
		lblTxtHeight.setFont(new Font("MathJax_Typewriter", Font.PLAIN, 14));
		lblTxtHeight.setBounds(12, 87, 59, 23);
		pnlControls.add(lblTxtHeight);
		
		lblNumbHeight = new JLabel();
		lblNumbHeight.setFont(new Font("C059", Font.PLAIN, 15));
		lblNumbHeight.setBackground(new Color(0, 0, 0, 0));
		lblNumbHeight.setBounds(70, 87, 70, 22);
		pnlControls.add(lblNumbHeight);
		
		//Slider for X coordinate
		sldrX = new JSlider(0, 1920, 0);
		sldrX.setBackground(new Color(0, 0, 0, 0));
		sldrX.setBounds(12, 174, 176, 16);
		sldrX.addChangeListener(this);
		pnlControls.add(sldrX);
		
		lblTxtX = new JLabel("X :");
		lblTxtX.setFont(new Font("MathJax_Typewriter", Font.PLAIN, 14));
		lblTxtX.setBounds(12, 150, 27, 23);
		pnlControls.add(lblTxtX);
		
		lblNumbX = new JLabel();
		lblNumbX.setFont(new Font("C059", Font.PLAIN, 15));
		lblNumbX.setBackground(new Color(0, 0, 0, 0));
		lblNumbX.setBounds(39, 150, 70, 22);
		pnlControls.add(lblNumbX);
		
		//Slider for Y coordinate
		sldrY = new JSlider(0, 1920, 0);
		sldrY.setForeground(Color.BLACK);
		sldrY.setBackground(new Color(0, 0, 0, 0));
		sldrY.setBounds(12, 231, 176, 16);
		sldrY.addChangeListener(this);
		pnlControls.add(sldrY);
		
		lblTxtY = new JLabel("Y :");
		lblTxtY.setFont(new Font("MathJax_Typewriter", Font.PLAIN, 14));
		lblTxtY.setBounds(12, 203, 27, 23);
		pnlControls.add(lblTxtY);
		
		lblNumbY = new JLabel();
		lblNumbY.setFont(new Font("C059", Font.PLAIN, 15));
		lblNumbY.setBackground(new Color(0, 0, 0, 0));
		lblNumbY.setBounds(30, 204, 70, 22);
		pnlControls.add(lblNumbY);
		
		
		/**
		 * for design purposes
		 */
		
		//image Label
		imgLabel = new ImageLabel(null);
		imgLabel.setSize(1684, 909);
		imgLabel.setLocation(224, 66);
		//Dimensions Label
		wHLabel = new WidthHeightLabel(imgLabel);
		wHLabel.setVisible(true);
		wHLabel.setBounds(73, 10, 1773, 53);
		getContentPane().add(wHLabel);
		getContentPane().add(imgLabel);
		getContentPane().add(pnlControls);
		
		

		
		
		
		repaint();
	
		SwingUtilities.updateComponentTreeUI(this);
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("searchFile")) {
			filePath = getFilePath();
			checkFileName();
		} 
		if(e.getActionCommand().equals("saveAs")) {
			imgLabel.folderPath = getFolderPath();
			this.folderPath = imgLabel.folderPath;
			imgLabel.CroppTheImage();
			
			setCroppedImg();
		}
		if(e.getActionCommand().equals("new")) {
			imgLabel.setLocalCoordinates(0,0,0,0);
			setSlidersValue();
			removeImageAndDimLabel();
			filePath = getFilePath();
			setImageAndDimLabel(filePath);
		}
		if(e.getActionCommand().equals("crop")) {
			imgLabel.displayCropped();
			imgLabel.setLocalCoordinates(0,0,0,0);
			setSlidersValue();
		}
	}		
	/*
	 * Pop Up to display the cropped Image
	 */
	public void setCroppedImg() {
		int n = JOptionPane.showConfirmDialog(
			    this,
			    "Would you like to display the cropped image?",
			    "Question",
			    JOptionPane.YES_NO_OPTION);
		//displayCroppedImgSubFrame saveSubFrame = new displayCroppedImgSubFrame(this);
		if(n == JOptionPane.YES_OPTION){
			imgLabel.setLocalCoordinates(0,0,0,0);
			setSlidersValue();
			removeImageAndDimLabel();
			setImageAndDimLabel(imgLabel.folderPath);
		}
		
	}
	
	
	
	/**
	 * Checks if the filePath Exists.
	 * If it exists disables the components that needs and creates the Image Label.
	 * Else Shows message to try again.
	 */
	public void checkFileName() {
		
			for(String tF :typeFiles) {
				if(filePath.contains(tF)) {
					srchFileButton.setVisible(false);
					srchFileButton.setEnabled(false);
					
					txtLabel.setVisible(false);
					txtLabel.setEnabled(false);
					
					setImageAndDimLabel(filePath);
					
					break;
				} else {
					txtLabel.setText("Image Not Found, try again.");
					imgLabel.drawImg.drawScaledImage(imgLabel.image, imgLabel, imgLabel.getGraphics());
				}
			}
			
			//setImageAndDimLabel();
			//imgLabel.drawImg.drawScaledImage(imgLabel.image, imgLabel, imgLabel.getGraphics());
	}
	
	/**
	 * sets the Image and Dimensions Components and Controls
	 * @param flPath
	 */
	public void setImageAndDimLabel(String flPath) {
		//image Label
		imgLabel = new ImageLabel(flPath);
		imgLabel.setSize(1684, 909);
		imgLabel.setLocation(224, 66);
		
		//extra mouse listeners for the slider
		imgLabel.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent e) {
				setSlidersValue();
				
			}
			@Override
			public void mouseMoved(MouseEvent e) {}
		});
		imgLabel.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setSlidersValue();

			}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			
		});
		

		getContentPane().add(imgLabel);
		
		//Dimensions Label
		wHLabel = new WidthHeightLabel(imgLabel);
		wHLabel.setVisible(true);
		wHLabel.setBounds(73, 10, 1773, 53);
		System.out.println("//////xA="+imgLabel.xA);
		
		getContentPane().add(pnlControls);
		getContentPane().add(pnlButtons);
		getContentPane().add(wHLabel);
		repaint();
	}
	
	/**
	 * sets all the sliders value
	 */
	public void setSlidersValue() {
		setSlidersMaxMin();
		//width
		sldrWidth.setValue(imgLabel.coord.getWidth());
		//height
		sldrHeight.setValue(imgLabel.coord.getHeight());
		//X
		sldrX.setValue(imgLabel.coord.getX());
		//Y
		sldrY.setValue(imgLabel.coord.getY());
		
	}
	public void setSlidersMaxMin() {
		//width
		sldrWidth.setMaximum(imgLabel.drawImg.x2-imgLabel.xA);
		//height
		sldrHeight.setMaximum(imgLabel.drawImg.y2-imgLabel.yA);
		//X
		sldrX.setMaximum(imgLabel.drawImg.x2-imgLabel.width);
		sldrX.setMinimum(imgLabel.drawImg.x1);
		//Y
		sldrY.setMaximum(imgLabel.drawImg.y2-imgLabel.height);
		sldrY.setMinimum(imgLabel.drawImg.y1);
	}
	
	 /*
	  * if JSlider value is changed
	  */
    public void stateChanged(ChangeEvent e) {
        //for Width components
    	lblNumbWidth.setText(""+sldrWidth.getValue());
        imgLabel.width = sldrWidth.getValue();
        //for Height components
        lblNumbHeight.setText(""+sldrHeight.getValue());
        imgLabel.height = sldrHeight.getValue();
        
        //for X coordinate Component
        lblNumbX.setText(""+ (sldrX.getValue() - imgLabel.drawImg.x1) );
        imgLabel.xA = sldrX.getValue();
        
        //for Y coordinate Component
        lblNumbY.setText(""+ (sldrY.getValue() - imgLabel.drawImg.y1) );
        imgLabel.yA = sldrY.getValue();
        
        //Dimensions update 
        wHLabel.setTheText(sldrWidth.getValue(),sldrHeight.getValue());
        
        setSlidersMaxMin();
        
        //repaints
        imgLabel.repaint();
        lblNumbWidth.repaint();
        lblNumbHeight.repaint();
        repaint();
        
        
    }
    
	/**
	 * removes the Image and Dimensions Components
	 */
	public void removeImageAndDimLabel(){
		this.imgLabel.setVisible(false);
		this.imgLabel.setEnabled(false);
		this.wHLabel.setVisible(false);
		this.wHLabel.setEnabled(false);
		this.getContentPane().remove(imgLabel);
		this.getContentPane().remove(wHLabel);
	}
	
	/**
	 * Opens the file Browser
	 * @return the file name the  user choose
	 */
	public String getFilePath() {
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(null);
		File file = chooser.getSelectedFile();
		String filename;
		String filePath=null; 
		if(file==null) {
			filePath = imgLabel.imagePath;
		} else {
			filename = file.getName();
			filePath = file.getPath();
			System.out.println("\nYou have selected: " + filename +"\nPath: "+ filePath +"\n");
			//chooseFileButton.setVisible(false);
		}
		
		
		return filePath;
	}
	
	/**
	 * Opens folder Browser
	 * @return the folder path the user choose
	 */
	public String getFolderPath() {
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(null);
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		File folder = chooser.getSelectedFile();
		String folderName = folder.getName();
		String foldrPath = folder.getPath();
		System.out.println("\nYou have selected: " + folderName+"\nPath: "+ foldrPath +"\n");
		//chooseFileButton.setVisible(false);
		
		return foldrPath;
	}
}