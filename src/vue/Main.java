package vue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;



class Main {
	public static void main(String[] args) {
		
	try {
            
		//UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		//UIManager.setLookAndFeel("com.sun.java.swing.plaf.metal.MetalLookAndFeel");
		//UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		//UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
		//UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
		UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		
	} 
    catch (UnsupportedLookAndFeelException e) {
       // handle exception
    }
    catch (ClassNotFoundException e) {
       // handle exception
    }
    catch (InstantiationException e) {
       // handle exception
    }
    catch (IllegalAccessException e) {
       // handle exception
    }
		Splasher J = new Splasher();
		J.start();
		FenetrePrincipale fenetre = new FenetrePrincipale();
		fenetre.setVisible(true);
	}
}