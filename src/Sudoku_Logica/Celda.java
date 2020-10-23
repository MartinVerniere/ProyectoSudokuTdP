package Sudoku_Logica;

public class Celda {
	//Atributos
	private int valor, fila, columna;
	private Imagen image;
	private boolean correcto; // Si es correcto, significa que no existe otro elemento igual en la misma fila, columna, o cuadrante del tablero
	//Constructor
	public Celda(int i, int j) {
		valor=0;
		fila=i; columna=j;
		image= new Imagen();
		correcto=false;
	}
	//Metodos
	public void setfila(int f) { fila=f;}
	public int getfila() { return fila;}
	
	public void setcolumna(int c) { columna=c; }
	public int getcolumna() { return columna; }
	
	public int getcantElems() { return image.getimagenes().length; }
	
	public void setValor(int n) { valor=n; }
	
	public int getValor() { return valor; }
	
	public void setimage(Imagen image) { this.image=image; }
	public Imagen getimage() { return image; }
	
	public void setcorrecto(boolean estado) { 
		this.correcto=estado;
		if (estado) { image.actualizar(this.valor);	}
		else { image.actualizar_error(this.valor);	}
	} 
	public boolean getcorrecto() { return this.correcto; } 
	

}
