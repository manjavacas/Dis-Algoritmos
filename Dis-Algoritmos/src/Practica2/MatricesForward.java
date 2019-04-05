package Practica2;

/**
 * Cambio de monedas forward con matrices
 * 
 * @author Ruben.Marquez, Antonio.Manjavacas
 */

public class MatricesForward {

	private int cambio;
	private int numMonedas;
	private int[] monedas;

	public MatricesForward(int cambio, int[] monedas) {
		this.cambio = cambio;
		this.monedas = monedas;
		this.numMonedas = monedas.length;
	}

	public int[] resolver() {
		int[][] matrizValor = new int[cambio + 1][numMonedas]; // Matriz con el numero de monedas acumuladas
		int[][] matrizRuta = new int[cambio + 1][numMonedas]; // Matriz con el numero de monedas a introducir

		// Inicializacion de las dos matrices
		for (int f = 0; f < matrizValor.length; f++)
			for (int c = 0; c < matrizValor[0].length; c++) {
				matrizValor[f][c] = 9999; // Inicializar a valor alto
				matrizRuta[f][c] = -1;
			}

		return obtenerSolucion(forwardMatriz(matrizValor, matrizRuta));
	}

	private int[][] forwardMatriz(int[][] matrizValor, int[][] matrizRuta) {
		int maximo = cambio / monedas[0];
		// Inicializacion de la primera columna
		for (int meto = 0; meto <= maximo; meto++) {
			matrizValor[cambio - monedas[0] * meto][0] = meto;
			matrizRuta[cambio - monedas[0] * meto][0] = meto;
		}

		for (int monedaActual = 1; monedaActual < numMonedas; monedaActual++) {
			for (int restante = matrizValor.length - 1; restante >= 0; restante--) {
				int max = restante / monedas[monedaActual];
				for (int p = 0; p <= max; p++) {
					int nuevoRestante = restante - monedas[monedaActual] * p;
					// Si el nuevo cambio es mejor se sustituye
					if (matrizValor[nuevoRestante][monedaActual] >= matrizValor[restante][monedaActual - 1] + p) {
						matrizValor[nuevoRestante][monedaActual] = matrizValor[restante][monedaActual - 1] + p;
						matrizRuta[nuevoRestante][monedaActual] = p;
					}

				}
			}
		}

		return matrizRuta;
	}

	private int[] obtenerSolucion(int[][] sol) {

		int[] solucion = new int[monedas.length];
		boolean noSol = true;

		for (int i = 0; i < sol.length && noSol; i++) {
			noSol = false;
			int fila = i;
			for (int col = sol[0].length - 1; col >= 0 && !noSol; col--) {
				if (sol[fila][col] == -1)
					noSol = true;
				solucion[col] = sol[fila][col];
				fila += sol[fila][col] * monedas[col];
			}

		}

		return solucion;
	}
}
