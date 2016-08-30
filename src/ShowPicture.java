import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class ShowPicture extends JFrame {
	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	private int width, height;
	private boolean success;
	

	public ShowPicture() {
		success= false;
		width = 0;
		height = 0;
	}
	

	public void loadPicture(File fileToShow) {
		
		try {
			image = ImageIO.read(fileToShow);
			success = true;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Nie mo¿na odczytaæ pliku!");
			success = false;
		}

		if (success == true)	{
			width = image.getWidth();
			height = image.getHeight();
			
			JLabel pic = new JLabel(new ImageIcon(image));
			JPanel showPic = new JPanel();
			showPic.add(pic);
			
			JFrame ramka = new JFrame();
			ramka.add(showPic);
	  		ramka.pack();
	  		ramka.setVisible(true);	
		}
		else {	
			width = 0;
			height = 0;
		}
		
	}
	
	
	public void showPic()	{
		if (success == true)	{
			JLabel pic = new JLabel(new ImageIcon(image));
			JPanel showPic = new JPanel();
			showPic.add(pic);
			
			JFrame ramka = new JFrame();
			ramka.add(showPic);
	  		ramka.pack();
	  		ramka.setVisible(true);	
		}
	}
	
	public void saveFile(File mySavedFile, String type)	{
		try {
			if (type == "jpg" ) {
				ImageIO.write(image , "jpg" , mySavedFile);
			} else if (type == "png" ) {
				ImageIO.write(image , "png" , mySavedFile);
			} else if (type == "bmp" ) {
				ImageIO.write(image , "bmp" , mySavedFile);
			}
			JOptionPane.showMessageDialog(null, "Zapisuje plik!\n" + "Typ pliku: " + type);
		}
		catch (Exception e)	{
			JOptionPane.showMessageDialog(null, "Nie mo¿na zapisaæ pliku!\n" + "Typ pliku: " + type);
		}
		
		
	}
	
	public int getWidth()	{ return width; }
	public int getHeight()	{ return height;}
	public BufferedImage getImage()	{ return image; }
	public boolean getSuccess()	{ return success; }
	
	
}