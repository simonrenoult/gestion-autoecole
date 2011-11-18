package vue;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class BoutonGroupe extends JButton implements ButtonModel {

/////////////////////////////CONSTRUCTEUR
	
	BoutonGroupe(String texte){
		super(texte);
	}
	
	
	
	@Override
	public boolean isArmed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPressed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRollover() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setArmed(boolean b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setGroup(ButtonGroup group) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPressed(boolean b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRollover(boolean b) {
		// TODO Auto-generated method stub
		
	}

}
