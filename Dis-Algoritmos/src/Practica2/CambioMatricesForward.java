package Practica2;

import java.util.ArrayList;

public class CambioMatricesForward {

	private int cambio;
	private int numMonedas;
	private int[][] monedas;

	public CambioMatricesForward(int cambio, ArrayList<Moneda> monedas) {
		this.cambio = cambio;
		this.numMonedas = monedas.size();
		this.monedas = matriz(monedas);
	}

	// Convierte una lista de monedas en una matriz de enteros
	private int[][] matriz(ArrayList<Moneda> listaMonedas) {
		
		int[][] arrayMonedas = new int[2][numMonedas];
		for (int i = 0; i < numMonedas; i++) {
			arrayMonedas[0][i] = listaMonedas.get(i).getValor();
		}

		return arrayMonedas;
	}

	// Solucion matricial forward
	public int[] getSolucion() {

		int[] solucion = new int[numMonedas];

		// Inicializar matriz ruta
		int[][] ruta = new int[cambio + 1][numMonedas + 1];

		// Inicializar matriz de valores
		int[][] valores = new int[cambio + 1][numMonedas + 1];
		for (int f = 0; f < valores.length; f++)
			for (int c = 0; c < valores[0].length; c++)
				valores[f][c] = -1;

		calcularSolucion(valores, ruta);
		solucion = componerSolucion(ruta, valores);

		return solucion;
	}

	// Resolucion forward con matrices
	private void calcularSolucion(int[][] valores, int[][] ruta) {

		valores[valores.length - 1][0] = 0;

		// Recorrer matriz de valores
		for (int colMoneda = 0; colMoneda < valores[0].length - 1; colMoneda++) {
			for (int fila = 0; fila < valores.length; fila++) {
				if (valores[fila][colMoneda] >= 0) {
					int nuevaCol = colMoneda + 1;
					// Introducir o no nueva moneda
					for (int p = 0; p <= 1; p++) {
						int nuevaFila = fila - monedas[0][colMoneda] * p;
						if (nuevaFila >= 0) {
							// Si es mejor se sustituye
							if (valores[nuevaFila][nuevaCol] < valores[fila][colMoneda] + monedas[1][colMoneda] * p) {
								valores[nuevaFila][nuevaCol] = valores[fila][colMoneda] + monedas[1][colMoneda] * p;
								// Registrar la ruta
								ruta[nuevaFila][nuevaCol] = p;
							}
						}

					}
				}
			}
		}
	}

	// Obtener solucion a partir de la ruta definida
	private int[] componerSolucion(int[][] ruta, int[][] valores) {

		int mejorFila = mejorFila(valores[0].length - 1, valores);
		int solucion[] = new int[numMonedas];

		for (int col = valores[0].length - 1; col > 0; col--) {
			solucion[col - 1] = ruta[mejorFila][col];
			if (solucion[col - 1] == 1) {
				mejorFila = mejorFila + monedas[0][col - 1];
			}
		}

		return solucion;
	}

	// Devuelve el mayor valor de la ultima columna
	private int mejorFila(int colUltima, int[][] valores) {
		int filaMejor = 0;
		for (int f = 1; f < valores.length; f++)
			if (valores[f][colUltima] > valores[filaMejor][colUltima])
				filaMejor = f;
		return filaMejor;
	}
}