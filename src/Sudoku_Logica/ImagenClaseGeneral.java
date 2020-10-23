package Sudoku_Logica;

import javax.swing.ImageIcon;

abstract class ImagenClaseGeneral {
	//Atributos
	protected ImageIcon im;
	protected String[] imagenes;
	//Constructor
	public ImagenClaseGeneral() {
		im= new ImageIcon();
	}
	//Metodos
	public void actualizar(int indice) {
		if (indice<=imagenes.length) {
				ImageIcon imagen= new ImageIcon(this.getClass().getResource(imagenes[indice-1]));
				im.setImage(imagen.getImage());
		}
	}
	
	public ImageIcon getim() { return im; }
	public void setim(ImageIcon im) { this.im=im; }
	
	public String[] getimagenes() { return imagenes; }
	public void setimagenes(String[] imagenes) { this.imagenes=imagenes; }
}
