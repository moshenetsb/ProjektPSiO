package strategiaGUI;

import javax.swing.JFrame;

public class PracownikGUI extends OsobaZarzadzajacaGUI {
	// Konstruktor
	public PracownikGUI(JFrame frame1) {
		frame1.getContentPane().removeAll();
		frame1.repaint();
		GUIcreate(frame1);
	}
	
	// TODO wykorzystać wspólne menu dla osoby zarządzającej + dodać funkcje pracownika
}
