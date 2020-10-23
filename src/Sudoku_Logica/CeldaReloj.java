package Sudoku_Logica;

public class CeldaReloj {
	//Atributos
	private ImagenReloj imagen;
	private int valor;
	//Constructor
	public CeldaReloj() {
		valor=0;
		imagen=new ImagenReloj();
	}
	//Metodos
	public void setValor(int valor) {
		this.valor=valor;
		imagen.actualizar(valor);
	}
	public int getvalor() { return valor; }
	
	public void setimagen(ImagenReloj imagen) { this.imagen=imagen; }
	public ImagenReloj getimagen() { return imagen; }
}
