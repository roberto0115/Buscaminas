/**
 * 
 */
package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import utily.Tablero;

/**
 * @author Usuario
 *
 */
public class Buscaminas {
	
	private Tablero tablero = null;
    private boolean ejecutando = true;
	
    public Buscaminas () {
		tablero = new Tablero(3);
	}
	public Buscaminas (int numero) {
		tablero = new Tablero(numero);
	}
	
	
	
	public int clickOn(int fila, int columna) {
		int result = tablero.clickOn(fila, columna);
		if(result < 0) {
			return result;
		}
		return tablero.getbombas(fila, columna);
	}
	public boolean setermino() {
		return tablero.setermino();
	}
	public int getInt(String cadena) {
		System.out.println(cadena);
		BufferedReader kbd = new BufferedReader(new InputStreamReader(System.in));
        try {
			return Integer.parseInt(kbd.readLine());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return 0;
	}
	public Tablero getTablero() {
		return this.tablero;
	}
	
	
	
}


