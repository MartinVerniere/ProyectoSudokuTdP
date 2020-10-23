package GUI_Sudoku;

import javax.swing.JLabel;

import Sudoku_Logica.Celda;

@SuppressWarnings("serial")
public class MiLabel extends JLabel {
	//Atributos
	protected Celda c;
	//Constructor
	public MiLabel(Celda cel) {
		super();
		c=cel;
	}
	//Metodos
	public void setcelda(Celda cel) { c=cel; }
	public Celda getcelda() { return c; }
}