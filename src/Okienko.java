import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Okienko extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton bLoadFile, bSaveFile, bInfo, bQuit, bShowPic;
	private JLabel lPictureName, lPictureSize, lPictureFormat, lPictureFormatToSave;
	private ButtonGroup bgFileFormat;
	private JRadioButton rbJPG, rbPNG, rbBMP;
	private File myLoadedFile, mySavedFile;
	private String fileName, fileFormat;
	ShowPicture myPic;

	public Okienko() {
		setSize(530, 180); // okienko
		setTitle("Convert pictures");
		setLayout(null);

		bLoadFile = new JButton("Load picture...");
		bLoadFile.setBounds(10, 10, 120, 30);
		bLoadFile.addActionListener(this);
		add(bLoadFile);

		bSaveFile = new JButton("Save picture as...");
		bSaveFile.setBounds(135, 10, 120, 30);
		bSaveFile.addActionListener(this);
		add(bSaveFile);

		bInfo = new JButton("Info");
		bInfo.setBounds(260, 10, 120, 30);
		bInfo.addActionListener(this);
		add(bInfo);

		bQuit = new JButton("Quit");
		bQuit.setBounds(385, 10, 120, 30);
		bQuit.addActionListener(this);
		add(bQuit);

		lPictureName = new JLabel("File: ");
		lPictureName.setBounds(10, 50, 500, 20);
		add(lPictureName);

		lPictureSize = new JLabel("Size: ");
		lPictureSize.setBounds(10, 70, 500, 20);
		add(lPictureSize);

		lPictureFormat = new JLabel("Format: ");
		lPictureFormat.setBounds(10, 90, 500, 20);
		add(lPictureFormat);

		lPictureFormatToSave = new JLabel("Format 2 write: ");
		lPictureFormatToSave.setBounds(10, 110, 500, 20);
		add(lPictureFormatToSave);

		bgFileFormat = new ButtonGroup();
		rbJPG = new JRadioButton("JPG", true);
		rbPNG = new JRadioButton("PNG", false);
		rbBMP = new JRadioButton("BMP", false);
		rbJPG.setActionCommand("jpg");
		rbPNG.setActionCommand("png");
		rbBMP.setActionCommand("bmp");
		rbJPG.setBounds(100, 110, 60, 20);
		rbPNG.setBounds(160, 110, 60, 20);
		rbBMP.setBounds(220, 110, 60, 20);
		bgFileFormat.add(rbJPG);
		bgFileFormat.add(rbPNG);
		bgFileFormat.add(rbBMP);
		add(rbJPG);
		add(rbPNG);
		add(rbBMP);
		rbJPG.addActionListener(this);
		rbPNG.addActionListener(this);
		rbBMP.addActionListener(this);

		bShowPic = new JButton("Show picture");
		bShowPic.setBounds(300, 110, 205, 20);
		bShowPic.addActionListener(this);
		add(bShowPic);
		myPic = new ShowPicture();

	}

	public static void main(String[] args) {

		Okienko okno = new Okienko();
		okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		okno.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == bQuit) {
			dispose();
		} else if (source == bLoadFile) {
			loadTheFile();
		} else if (source == bSaveFile) {
			saveTheFile();
		} else if (source == bShowPic) {
			myPic.showPic();
		} else if (source == bInfo) {
			JOptionPane.showMessageDialog(null, "Convert pictures\n\nPNG/JPG/BMP");
		}

	}

	private void loadTheFile() {
		JFileChooser loadFile = new JFileChooser();
		if (loadFile.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			myLoadedFile = loadFile.getSelectedFile();
			myPic.loadPicture(myLoadedFile);
			if (myPic.getSuccess() == true) {
				fileName = myLoadedFile.getAbsolutePath();
				getFileFormat();
				updateLabels();
			}
		}
	}

	private void saveTheFile() {
		if (myPic.getSuccess() == true) {
			JFileChooser saveFile = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG File", "png");
			saveFile.setFileFilter(filter);
			filter = new FileNameExtensionFilter("JPG File", "jpg");
			saveFile.addChoosableFileFilter(filter);
			filter = new FileNameExtensionFilter("BMP File", "bmp");
			saveFile.addChoosableFileFilter(filter);

			if (saveFile.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
				mySavedFile = saveFile.getSelectedFile();
				JOptionPane.showMessageDialog(null, "Saving file " + mySavedFile.getAbsolutePath());
				if (bgFileFormat.getSelection().getActionCommand() == "jpg") {
					myPic.saveFile(mySavedFile, "jpg");
				} else if (bgFileFormat.getSelection().getActionCommand() == "png") {
					myPic.saveFile(mySavedFile, "png");
				} else if (bgFileFormat.getSelection().getActionCommand() == "bmp") {
					myPic.saveFile(mySavedFile, "bmp");
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "Nie ma czego zapisaæ!");
		}
	}

	private void updateLabels() {
		lPictureName.setText("File: " + fileName);
		lPictureSize.setText("Size: " + myPic.getWidth() + " x " + myPic.getHeight());
		lPictureFormat.setText("Format: " + fileFormat);
	}

	public void getFileFormat() {
		fileName.toLowerCase();
		if (fileName.endsWith(".png")) {
			fileFormat = "PNG";
		} else if (fileName.endsWith(".gif")) {
			fileFormat = "GIF";
		} else if (fileName.endsWith(".tiff")) {
			fileFormat = "TIFF";
		} else if (fileName.endsWith(".jpg")) {
			fileFormat = "JPEG";
		} else if (fileName.endsWith(".jpeg")) {
			fileFormat = "JPEG";
		} else if (fileName.endsWith(".bmp")) {
			fileFormat = "BMP";
		} else {
			fileFormat = "UNKNOWN";
		}

	}

}
