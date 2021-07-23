package CroppingPack1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class WidthHeightLabel extends JLabel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	ImageLabel imgLbl;
	
	public WidthHeightLabel(ImageLabel imgLabel) {
		imgLbl = imgLabel;
		setHorizontalAlignment(SwingConstants.CENTER);
		this.setForeground(Color.BLACK);
		this.setFont(new Font("C059", Font.PLAIN, 15));
		
		this.setBounds(73, 10, 123, 53);
		this.setBackground(new Color(179, 179, 179, 100));
		
		this.setText(imgLbl.coord.getWidth()+"x"+imgLbl.coord.getWidth());
		
		imgLbl.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {
				setTheText();
			}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			
		});

	}
	
	private void setTheText() {
		
		this.setText(imgLbl.coord.getWidth()+"x"+imgLbl.coord.getHeight());
		}
	}
