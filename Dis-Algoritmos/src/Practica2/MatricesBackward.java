package Practica2;

public class MatricesBackward {

	private int cambio;
	private int numMonedas;
	private int[] monedas;
	private int[][] matrizValor;
	private int[][] matrizRuta;

	public MatricesBackward(int cambio, int[] monedas) {
		this.cambio = cambio;
		this.monedas = monedas;
		this.numMonedas = monedas.length;
		this.matrizValor = new int[cambio + 1][monedas.length];
		this.matrizRuta = new int[cambio + 1][monedas.length];
	}

	public int[] resolver() {

		int[] solucion = new int[monedas.length];

		for (int f = 0; f < matrizValor.length; f++)
			for (int c = 0; c < numMonedas; c++) {
				matrizValor[f][c] = 99;
				matrizRuta[f][c] = -1;
			}

		backwardMatriz(cambio, 0);

		for(int i = 0; i < monedas.length; i++) {
			solucion[i] = matrizRuta[cambio][i];
			cambio -= matrizRuta[cambio][i]*monedas[i];
		}

		return solucion;
	}

	public int backwardMatriz(int cambioRestante, int monedaActual) {
		if(matrizValor[cambioRestante][monedaActual] == 99) {
			if(monedaActual == numMonedas - 1) {
				int meto = cambioRestante / monedas[monedaActual];
				matrizValor[cambioRestante][monedaActual] = meto;
				matrizRuta[cambioRestante][monedaActual] = meto;
			} else {
				int max = cambioRestante / monedas[monedaActual];
				for(int p = 0; p <= max; p++) {
					int nuevoRestante = cambioRestante - monedas[monedaActual] * p;
					int nuevoValor = backwardMatriz(cambioRestante - monedas[monedaActual] * p, monedaActual + 1) + p;
					if(esMejor(cambioRestante, monedaActual, nuevoRestante, nuevoValor)) {
						matrizValor[cambioRestante][monedaActual] = nuevoValor;
						matrizRuta[cambioRestante][monedaActual] = p;
					}
				}
			}
		}
		return matrizValor[cambioRestante][monedaActual];
	}

	public boolean esMejor(int cambioRestante, int monedaActual, int nuevoRestante, int nuevoValor) {
		int viejoRestante = cambioRestante - matrizRuta[cambioRestante][monedaActual] * monedas[monedaActual];
		if(viejoRestante > nuevoRestante) {
			return true;
		}
		else if(viejoRestante == nuevoRestante && nuevoValor < matrizValor[cambioRestante][monedaActual]) {
			return true;
		}
		return false;
	}
}
