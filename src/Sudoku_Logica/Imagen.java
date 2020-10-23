package Sudoku_Logica;
import javax.swing.ImageIcon;

public class Imagen extends ImagenClaseGeneral{
	//Atributos
	protected String[] imagenes_error;
	
	//Constructor
	public Imagen() {
		super();
		imagenes= new String[] {"/Imagenes/uno.png","/Imagenes/dos.png","/Imagenes/tres.png","/Imagenes/cuatro.png","/Imagenes/cinco.png","/Imagenes/seis.png","/Imagenes/siete.png","/Imagenes/ocho.png","/Imagenes/nueve.png"};
		imagenes_error= new String[] {"/Imagenes/uno_error.png","/Imagenes/dos_error.png","/Imagenes/tres_error.png","/Imagenes/cuatro_error.png","/Imagenes/cinco_error.png","/Imagenes/seis_error.png","/Imagenes/siete_error.png","/Imagenes/ocho_error.png","/Imagenes/nueve_error.png"};
	}
	//Metodos
	public void actualizar_error(int indice) {
		if (indice<=imagenes_error.length) {
			ImageIcon imagen= new ImageIcon(this.getClass().getResource(imagenes_error[indice-1]));
			im.setImage(imagen.getImage());
		}
	}
	
	public String[] getimagenes_error() { return imagenes_error; }
	public void setimagenes_error(String[] imagenes_error) { this.imagenes_error=imagenes_error; }
}
