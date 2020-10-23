package Sudoku_Logica;

import javax.swing.ImageIcon;

public class ImagenReloj extends ImagenClaseGeneral {
	//Atributos
	String dospuntos;
	//Constructor
	public ImagenReloj() {
		super();
		dospuntos="/Imagenes/dospuntos.png";
		imagenes= new String[] {"/Imagenes/cero.png","/Imagenes/uno.png","/Imagenes/dos.png","/Imagenes/tres.png","/Imagenes/cuatro.png","/Imagenes/cinco.png","/Imagenes/seis.png","/Imagenes/siete.png","/Imagenes/ocho.png","/Imagenes/nueve.png"};
		ImageIcon imagen= new ImageIcon(this.getClass().getResource(imagenes[0]));
		im.setImage(imagen.getImage());
	}
	//Metodos
	public void actualizar(int indice) {
		if (indice<=imagenes.length) {
			ImageIcon imagen= new ImageIcon(this.getClass().getResource(imagenes[indice]));
			im.setImage(imagen.getImage());
		}
	}
	public void actualizardospuntos() {
		ImageIcon imagen= new ImageIcon(this.getClass().getResource(dospuntos));
		im.setImage(imagen.getImage());
	}
}
