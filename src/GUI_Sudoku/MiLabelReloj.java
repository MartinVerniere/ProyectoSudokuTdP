package GUI_Sudoku;

import javax.swing.JLabel;

import Sudoku_Logica.CeldaReloj;

@SuppressWarnings("serial")
public class MiLabelReloj extends JLabel {
	//Atributos
	protected CeldaReloj c;
	//Constructor
	public MiLabelReloj(CeldaReloj cel) {
		super();
		c=cel;
	}
	//Metodos
	public void setcelda(CeldaReloj cel) { c=cel; }
	public CeldaReloj getcelda() { return c; }
}