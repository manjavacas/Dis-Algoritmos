package Practica2;

public class MatricesForwardPrueba {

	private int cambio;
	private int numMonedas;
	private int[] monedas;

	public MatricesForwardPrueba(int cambio, int[] monedas) {
		this.cambio = cambio;
		this.monedas = monedas;
		this.numMonedas = monedas.length;
	}

	public int[][] resolver() {
		int[][] matrizValor = new int[cambio + 1][numMonedas];
		int[][] matrizRuta = new int[cambio + 1][numMonedas];

		for (int f = 0; f < matrizValor.length; f++)
			for (int c = 0; c < matrizValor[0].length; c++) {
				matrizValor[f][c] = 99;
				matrizRuta[f][c] = -1;
			}

		return forwardMatriz(matrizValor, matrizRuta);
	}

	public int[][] forwardMatriz(int[][] matrizValor, int[][] matrizRuta) {
		int maximo = cambio / monedas[0];
		for (int meto = 0; meto <= maximo; meto++) {
			matrizValor[cambio - monedas[0] * meto][0] = meto;
			matrizRuta[cambio - monedas[0] * meto][0] = meto;
		}

		for (int monedaActual = 1; monedaActual < numMonedas; monedaActual++) {
			for (int restante = matrizValor.length - 1; restante >= 0; restante--) {
				int max = restante / monedas[monedaActual];
				for (int p = 0; p <= max; p++) {
					int nuevoRestante = restante - monedas[monedaActual] * p;
					if (matrizValor[nuevoRestante][monedaActual] >= matrizValor[restante][monedaActual - 1] + p) {
						matrizValor[nuevoRestante][monedaActual] = matrizValor[restante][monedaActual - 1] + p;
						matrizRuta[nuevoRestante][monedaActual] = p;
					}
				}
			}
		}

		return matrizRuta;
	}
}
