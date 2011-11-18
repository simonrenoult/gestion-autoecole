package vue;

import java.awt.Dimension;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;

public class Splasher extends Thread {
	private JWindow window = new JWindow();
	private Dimension dimension = new Dimension(600, 300);
	
	
	/**
	 * Constructeur principal de la classe Splasher
	 * 
	 */
	public Splasher() {
		
		window.setSize(dimension);
		//window.add(new JLabel(new ImageIcon("src/images/splasher.gif")));
		window.add(new JLabel(createImageIcon("/images/splasher.gif","")));
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
	}
	

	private ImageIcon createImageIcon(String path,String description) {
		URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} 
		else {
			System.err.println("Couldn't find file: " + path);
		return null;
		}
	}

	/**
	 *On lance cette methode et on boucle pendant un temps estime. Le jeu se charge en parallele.
	 * 
	 */
	@SuppressWarnings("deprecation")
	public void run() {
		long time = System.currentTimeMillis();
		while (System.currentTimeMillis() -time   < 2000){}
		window.setVisible(false);
		this.stop();
	}
}
