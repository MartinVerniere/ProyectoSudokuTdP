package Sudoku_Logica;

public class Reloj {
	//Atributos
	protected CeldaReloj[] digitos;
	//Constructor
	public Reloj() {
		digitos= new CeldaReloj[8];
		for (int i=0;i<digitos.length;i++) {
			digitos[i]= new CeldaReloj();
			if ((i==2) || (i==5)) { digitos[i].getimagen().actualizardospuntos(); }
		}
	}
	//Metodos
	public void actualizar(int digito, int indice) { digitos[indice].setValor(digito); }
	public CeldaReloj getcelda(int indice) { return digitos[indice]; }
	
	public void setdigitos(CeldaReloj[] digitos) { this.digitos=digitos;}
	public CeldaReloj[] getdigitos() { return digitos; }

}
