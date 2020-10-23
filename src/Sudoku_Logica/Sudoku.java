package Sudoku_Logica;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

public class Sudoku {
	//Atributos
	private Celda[][] tablero;
	
	private int cantFilas;
	private int cantFilasSubcuadrante;
	private long Hora;
	private boolean estadoinicialcorrecto;
	
	//Constructor
	public Sudoku() {
		
		Hora=System.currentTimeMillis() ;
		cantFilas=9;
		cantFilasSubcuadrante=3;
		tablero=new Celda[cantFilas][cantFilas];
		
		for (int i=0;i<cantFilas;i++) {
			for (int j=0;j<cantFilas;j++) {
				tablero[i][j]=new Celda(i,j); 
			}
		}
		estadoinicialcorrecto=this.ingresarestadoinicial("archivodetexto/EstadoInicial.txt") && estadoinicialcorrecto;
	}
	
	//Metodos
	public void accionar(Celda c, int num) {
		accionar2(c,num);
		this.actualizar();
	}
	
	public void actualizar() {
		//Para cada celda del tablero, inserta el mismo valor que tenia antes, para ver si un nuevo valor ingresado al tablero creo un conflicto
		for (int i=0;i<cantFilas;i++) {
			for (int j=0;j<cantFilas;j++) {
				Celda c=tablero[i][j];
				if (c!=null && c.getValor()!=0) {
					this.accionar2(c,c.getValor());
				}
			}
		}
	}
	
	private void accionar2(Celda c, int num) {
		int i=c.getfila();
		int j=c.getcolumna();
		c.setValor(num);
		boolean cumple=noesta(i,j,num);
		c.setcorrecto(cumple);
	}
	
	public boolean gano() {
		boolean estado=true;
		for (int i=0;i<cantFilas && estado;i++) {
			for(int j=0;j<cantFilas && estado;j++) {
				if (!tablero[i][j].getcorrecto()) { 
					estado=false;
				}
			}
		}
		return estado;
	}
	
	private boolean noesta(int i, int j, int num) {
		return (this.noestaenfila(i, j, num) && this.noestaencolumna(i, j, num) && this.noestaencuadrante(i, j, num));
	}
	
	public boolean getestadoinicialcorrecto() { return estadoinicialcorrecto; }
	
	public int getcantFilas() { return cantFilas; }
	public Celda getCelda(int i, int j) { return tablero[i][j]; }
	
	private boolean noestaenfila(int i, int j, int num) {
		boolean cumple=true;
		for (int col=0;col<cantFilas && cumple;col++) {
			if (col!=j && tablero[i][col]!=null && tablero[i][col].getValor()!=0) {
				cumple=(tablero[i][col].getValor()!=num);
			}
		}
		return cumple;
	}
	private boolean noestaencolumna(int i, int j, int num) {
		boolean cumple=true;
		for (int fila=0;fila<cantFilas && cumple;fila++) {
			if (fila!=i && tablero[fila][j]!=null && tablero[fila][j].getValor()!=0) {
				cumple=(tablero[fila][j].getValor()!=num);
			}
		}
		return cumple;
	}
	private boolean noestaencuadrante(int fila, int columna, int num) {
		boolean cumple=true; int a=0, b=0;
		
		//Dado el numero de fila y columna de la celda, recorrera el cuadrante sabiendo su posicion superior izquierda, con "a" su n° de fila y "b" su n° de columna
		a=((int) (fila/cantFilasSubcuadrante)) *cantFilasSubcuadrante; 
		b=((int) (columna/cantFilasSubcuadrante)) *cantFilasSubcuadrante;

		for (int i=0;i<cantFilasSubcuadrante && cumple;i++) {
			for (int j=0;j<cantFilasSubcuadrante && cumple;j++) {
				if (((a+i)!=fila && (b+j)!=columna) && tablero[a+i][b+j]!=null && tablero[a+i][b+j].getValor()!=0) {
					cumple=(tablero[a+i][b+j].getValor()!=num);
				}
			}
		}
		return cumple;
	}
	
	public long getHora() { return Hora; }
	public void reiniciarHora() { Hora=System.currentTimeMillis(); }
	
	public String getTiempo() {
		long TiempoPaso = System.currentTimeMillis() - Hora;
		
		long SegundosPaso = TiempoPaso / 1000;
		long SegundosMostrar = SegundosPaso % 60;
		
		long MinutosPaso= SegundosPaso / 60;
		long MinutosMostrar = MinutosPaso % 60;
		
		long HorasMostrar= MinutosPaso / 60;
		
		String hms=String.format("%02d:%02d:%02d",HorasMostrar,MinutosMostrar,SegundosMostrar);
		return (hms);
	}

	
	
	public boolean ingresarestadoinicial(String nombrearchivo) {
		//Inicializa el tablero con un estado incial, devuelve un mensaje si ocurrio algun error, sino returna null;
		InputStream in = Sudoku.class.getClassLoader().getResourceAsStream(nombrearchivo);
		if (in!=null) {
			InputStreamReader inr= new InputStreamReader(in);
			int punteroJ, punteroI=0;
			
			try {
				BufferedReader Buffer= new BufferedReader(inr);
				while (punteroI<this.getcantFilas() && Buffer.ready()) {
					punteroJ=0;
					String linea=Buffer.readLine();
					linea=linea.replace(" ",""); //Borro los espacios en blanco
					char[] arreglo=linea.toCharArray();
					if (arreglo.length!=cantFilas) { return false; }
					while (punteroJ<arreglo.length && punteroJ<this.getcantFilas()) {
						try {
							int num=Integer.parseInt(Character.toString(arreglo[punteroJ]));
							if (num<10 && num>0) { 
								this.accionar(tablero[punteroI][punteroJ], num); 
							}
						} catch (NumberFormatException exception) {
							//ERROR NumberFormatException, el valor se reemplazo por un cero(es decir no hace nada porque la celda tiene incializada el valor 0)
						}
						punteroJ++;
					}
					punteroI++;
				}
				if ((punteroI!=this.getcantFilas()) || Buffer.ready()) { return false; }
				
				Buffer.close();
			} catch (IOException e) { estadoinicialcorrecto=false; }
			
			this.estadoinicialcorrecto=this.gano();
			this.editarestadoinicial();
			return true;
		}
		else { return false; }
	}
	
	private void editarestadoinicial() {
		//Elimina aleatoriamente elementos del Tablero
		Random rand= new Random();
		int cantelemsborrar,puntero,cont;
		for (int i=0;i<cantFilas;i++) {
			cont=0;
			cantelemsborrar=rand.nextInt(cantFilas);
			while (cont<cantelemsborrar) {
				puntero=rand.nextInt(cantFilas);
				tablero[i][puntero]= new Celda(i,puntero);
				cont++;
			}
		}
	}
}

