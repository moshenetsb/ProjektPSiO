package strategiaGUI;

import javax.swing.JFrame;

public class KlientGUI implements GUIstrategia {

	// Konstruktor
	public KlientGUI(JFrame frame1) {
		frame1.getContentPane().removeAll();
		frame1.repaint();
		GUIcreate(frame1);
	}

	@Override
	public void GUIcreate(JFrame frame1) {
		// TODO Auto-generated method stub

	}

}
