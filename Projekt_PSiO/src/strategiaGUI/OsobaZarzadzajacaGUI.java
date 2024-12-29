package strategiaGUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public abstract class OsobaZarzadzajacaGUI implements GUIstrategia {

	@Override
	public void GUIcreate(JFrame frame1) {
		frame1.setTitle("Nasz Sklep");

		// TODO wygłąd menu dla osoby zarządzającej (współne)
		// jeśli trzeba, to można zmienić rozmiar

	}

}
