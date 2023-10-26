package utily;

import java.util.Random;

public class Tablero {
   
	private Casilla[][] tablero = new Casilla[10][10];
	private int numeroBombas = 3;
	
	public Tablero(int tamaño) {
		tablero = new Casilla[tamaño][tamaño];
		numeroBombas = Math.max((tamaño*tamaño)/8, 3);
		rellenartablero();
		System.out.println();
		_imprimirtablero();
	}	
	public int getNumeroBombas() {
		return numeroBombas;
	}
	
	public int clickOn(int fila, int columna) {
		int resultado = tablero[fila][columna].click();
		int bombas = buscarbombas(fila,columna);
		tablero[fila][columna].setNumBombas(bombas);
		return resultado;
	}
	
	public int getbombas(int fila, int columna) {
		return tablero[fila][columna].getBombas();
	}
	public boolean setermino() {
		boolean acabo = true;
		for(int i = 0; i<tablero.length; i++) {
            for(int j = 0; j<tablero.length; j++) {
				if(!tablero[i][j].getTieneBomba()) {
					if(!tablero[i][j].getClick()) {
						acabo = false;
						break;
					}
				}
			}
		}
		return acabo;
		
	}
	
	
	
	public int buscarbombas(int fila, int columna) {
		
		// caso en el que la casilla este rodeada
		if(fila>0 && fila<tablero.length-1) {
			if(columna>0 && columna<tablero.length-1 ) {
				return buscarBombascasillacentral(fila,columna);
			}else if(columna == tablero.length-1){
				//caso de que la casilla este en lado derecho.
				return buscarBombascasilladerecho(fila,columna);
			}else if(columna == 0){
				//caso en el que la casilla este en el lado izquierdo (columna == 0)
				return buscarBombascasillaizquierdo(fila,columna);
			}
		}else if(fila == 0) {
			if(columna>0 && columna<tablero.length-1 ) {
				//arriba
				return buscarBombascasillarriba(fila,columna);
			}else if(columna == tablero.length-1){
				return esquinaArribaDerecha(fila,columna);
			
				//caso de que la casilla esquina arriba derecha
			}else {
				return esquinaArribaIzquierda(fila,columna);
				//caso en el que la casilla abajo izquierda
			}
		}else {
			if(columna>0 && columna<tablero.length-1 ) {
				//abajo
				return buscarBombascasillabajo(fila,columna);
			}else if(columna == tablero.length-1){
				return esquinaAbajoDerecha(fila,columna);
				//caso de que la casilla esquina abajo derecha
			}else {
			
				return esquinaAbajoIzquierda(fila,columna);
				//caso en el que la casilla abajo izquierda
			}
		}
	return -1;
	}
	
	public int buscarBombascasillarriba(int fila, int columna) {
		int bombasAlrededor = 0;
		for (int i = fila  ; i <= fila +1; i++) { //recorrer filas
			for (int j =columna - 1; j<= columna + 1; j++) { //recorrer columnas
				if(i == fila && j == columna) {
					
				}else{
					if(tablero[i][j].getTieneBomba()) {
						bombasAlrededor ++;
					}
				}
			   }
			}
	
		return bombasAlrededor;
	}
	public int buscarBombascasillabajo(int fila, int columna) {
		int bombasAlrededor = 0;
		for (int i = fila -1; i <= fila ; i++) { //recorrer filas
			for (int j =columna - 1; j<= columna + 1; j++) { //recorrer columnas
				if(i == fila && j == columna) {
					
				}else{
					if(tablero[i][j].getTieneBomba()) {
						bombasAlrededor ++;
					}
				}
			   }
			}
	
		return bombasAlrededor;
	}
	public int buscarBombascasillacentral(int fila, int columna) {
		int bombasAlrededor = 0;
		for (int i = fila -1; i <= fila +1; i++) { //recorrer filas
			for (int j =columna - 1; j<= columna + 1; j++) { //recorrer columnas
				if(i == fila && j == columna) {
					
				}else{
					if(tablero[i][j].getTieneBomba()) {
//						bombasAlrededor = bombasAlrededor +1;
//						bombasAlrededor += 1;
						bombasAlrededor ++;
					}
					
					
				}
			   }
			}
		return bombasAlrededor;
	}

	public int buscarBombascasilladerecho(int fila, int columna) {
		int bombasAlrededor = 0;
		for (int i = fila -1; i <= fila +1; i++) { //recorrer filas
			for (int j =columna - 1; j<= columna; j++) { //recorrer columnas
				if(i == fila && j == columna) {
					
				}else{
					if(tablero[i][j].getTieneBomba()) {
						bombasAlrededor ++;
					}
				}
			   }
			}
		return bombasAlrededor;
	}
	
	
	public int buscarBombascasillaizquierdo(int fila, int columna) {
		int bombasAlrededor = 0;
		for (int i = fila -1; i <= fila +1; i++) { //recorrer filas
			for (int j =columna; j<= columna +1; j++) { //recorrer columnas
				if(i == fila && j == columna) {
					
				}else{
					if(tablero[i][j].getTieneBomba()) {
						bombasAlrededor ++;
					}
				}
			   }
			}
		return bombasAlrededor;
	}
	
	public int esquinaArribaDerecha(int fila, int columna) {
		int bombasAlrededor = 0;
		for (int i = fila ; i <= fila + 1; i++) { //recorrer filas
			for (int j =columna - 1; j<= columna ; j++) { //recorrer columnas
				if(i == fila && j == columna) {
					
				}else{
					if(tablero[i][j].getTieneBomba()) {
						bombasAlrededor ++;
					}
				}
			   }
			}
	
		return bombasAlrededor;
	}
	public int esquinaAbajoDerecha(int fila, int columna) {
		int bombasAlrededor = 0;
		for (int i = fila -1; i <= fila ; i++) { //recorrer filas
			for (int j =columna - 1; j<= columna ; j++) { //recorrer columnas
				if(i == fila && j == columna) {
					
				}else{
					if(tablero[i][j].getTieneBomba()) {
						bombasAlrededor ++;
					}
				}
			   }
			}
	
		return bombasAlrededor;
	}
	public int esquinaArribaIzquierda(int fila, int columna) {
		int bombasAlrededor = 0;
		for (int i = fila; i <= fila +1; i++) { //recorrer filas
			for (int j =columna; j<= columna +1; j++) { //recorrer columnas
				if(i == fila && j == columna) {
					
				}else{
					if(tablero[i][j].getTieneBomba()) {
						bombasAlrededor ++;
					}
				}
			   }
			}
	
		return bombasAlrededor;
	}
	
	public int esquinaAbajoIzquierda(int fila, int columna) {
		int bombasAlrededor = 0;
		for (int i = fila -1; i <= fila; i++) { //recorrer filas
			for (int j =columna ; j<= columna +1; j++) { //recorrer columnas
				if(i == fila && j == columna) {
					
				}else{
					if(tablero[i][j].getTieneBomba()) {
						bombasAlrededor ++;
					}
				}
			   }
			}
	
		return bombasAlrededor;
	}
	
	
	
	
	
	public void rellenartablero() {
		for (int i = 0; i<tablero.length; i++) {
			for (int j = 0; j<tablero[i].length; j++) {
				   tablero[i][j] = new Casilla(false);
			   }
			}
		
		
		for(int i = 0; i < numeroBombas; i++) {
			
		int fila;
		int columna;
			do{
			Random num = new Random();
			 fila = num.nextInt(tablero.length);
			 columna = num.nextInt(tablero.length);
			}while(tablero[fila][columna].getTieneBomba());
			tablero[fila][columna] = new Casilla(true);
		}
		
	
	}
	
	
	
	public void imprimirtablero() {
		for (int i = 0; i<tablero.length; i++) {
			
			for (int j = 0; j<tablero[i].length; j++) {
				  System.out.print(tablero[i][j]+"\t");
				  
			   }
			System.out.println();
			}
	}
	
	public void _imprimirtablero() {
		for (int i = 0; i<tablero.length; i++) {
			for (int j = 0; j<tablero[i].length; j++) {
				if(tablero[i][j].getTieneBomba()){
					System.out.print(1+"\t");
				}else
					System.out.print(0+"\t");
				}
				System.out.println();
				  
			   
			}
		}
	
	
	@Override
	public String toString() {
		return "";
		
	}
	public Casilla[][] getTablero(){
		return this.tablero;
	}
}
