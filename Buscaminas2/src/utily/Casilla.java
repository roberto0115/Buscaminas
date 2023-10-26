/**
 * 
 */
package utily;

/**
 * @author Usuario
 *
 */
public class Casilla {
   
	
	private boolean tieneBomba = false;
	private boolean click = false;
	private int numBombas = 0;
	private int numvisitado = 0;
	
	public void setNumBombas(int numBombas) {
		this.numBombas = numBombas;
	}
	
	public void setClick(boolean click) {
		this.click = click;
	}
	
	public void incrementarVisitados() {
		this.numvisitado++;
	}
	public int getNumVisitado() {
		return this.numvisitado;
	}
	public boolean getClick() {
		return click;
	}
	public Casilla(boolean tienebomba) {
		this.tieneBomba = tienebomba;
		this.numvisitado = 0;
	}
	
	
	public boolean getTieneBomba() {
		return tieneBomba;
		
	
	}
	public int getBombas() {
		return numBombas;
}
	public int TIeneBOmbas() {
		return numBombas;
	}
	public int click() {
	   setClick(true);
		if(!tieneBomba) {
			//casilla vacia: no hace nada
			return 0;
		}else {
			//casilla conBomba: explota y acaba el juego
			return -1;
		}
			

   }
	@Override
	public String toString() {
		if(click) {
			//return.Integer.tostring(numBombas);
		return ""+numBombas;
		}else {
		return "X";

		}
	}
}
