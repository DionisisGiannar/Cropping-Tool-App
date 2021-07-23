package CroppingPack1;
//kounelaki
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class CroppingApp {
	
	public static void main(String[] args) {
		/*Scanner input = new Scanner(System.in);
		
		String imgPath = Path();
		
		Coordinates coord = new Coordinates();
		try {
			BufferedImage origImage = ImageIO.read(new File(imgPath));
			
			System.out.println("Original Image Dimension: "+origImage.getWidth()+"x"+origImage.getHeight());
			
			System.out.print("Coord X: ");
			coord.setX(input.nextInt());
			System.out.print("\nCoord Y: ");
			coord.setY(input.nextInt());
			System.out.print("\nWidth: ");
			coord.setWidth(input.nextInt());
			System.out.print("\nHeight: ");
			coord.setHeight(input.nextInt());
		 	
			input.close();
			
				
			if( coord.getWidth() >= origImage.getWidth() || coord.getHeight() >= origImage.getHeight() ) { // new width, height >= old width, height
				System.out.println("The image cannot be cropped you entered the same or bigger Width, Height.");
			
				
			} else if( coord.getX() > origImage.getWidth() || coord.getY() >= origImage.getHeight() ) { //coordinates > width, Height
				System.out.println("The image cannot be cropped you entered the bigger coordinates than old Width, Height.");
			
			} else { // correct data 
				BufferedImage subImage = origImage.getSubimage(coord.getX(), coord.getY(), coord.getWidth(), coord.getHeight());
				
				System.out.println("Cropped Image Dimension: "+subImage.getWidth()+"x"+subImage.getHeight());

				File outputfile = new File("D:\\dio\\SXOLH\\Croppedimages.jpg");
				ImageIO.write(subImage, "jpg", outputfile);
				
				System.out.println("Image cropped successfully: "+outputfile.getPath());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		new CropAppFrame();
	}
	
	/**
	 * @return String with the path of the file
	 */
	private static String Path() {
		Scanner input = new Scanner(System.in);
		System.out.print("/nGive the path of the image you want to crop : ");
		String path = input.nextLine();
		input.close();
		
		return path;
		
	}
}
