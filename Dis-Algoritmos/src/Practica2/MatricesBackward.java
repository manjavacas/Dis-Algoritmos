package Practica2;

public class MatricesBackward {
	private int[] monedas;
	private int[][] matrizMonedas;

	public MatricesBackward(int cambio, int[] monedas) {
		matrizMonedas = new int[cambio + 1][monedas.length];
		inicializar(matrizMonedas);
		this.monedas = monedas;
	}

	public void inicializar(int[][] matriz) {
		for(int i = 0; i < matriz.length; i++) {
			for(int j = 0; j < matriz[0].length; j++) {
				matriz[i][j] = 0;
			}
		}
	}

	public int[] resolver(int cambio) {
		int[] solucion = new int[monedas.length];
		matrizBackward(cambio, 0);
		for(int i = 0; i < monedas.length; i++) {
			solucion[i] = matrizMonedas[cambio][i];
			cambio -= matrizMonedas[cambio][i]*monedas[i];
		}
		return solucion;
	}

	public int matrizBackward(int cambioRestante, int moneda) {
		if(matrizMonedas[cambioRestante][moneda] == 0) {
			if(moneda == matrizMonedas[0].length - 1) {
				matrizMonedas[cambioRestante][moneda] = cambioRestante / monedas[moneda];
			} else {
				for(int quito = 0; cambioRestante >= monedas[moneda]*quito; quito++) {
					int valor = matrizBackward(cambioRestante - monedas[moneda]*quito, moneda+1);
					if(esMejor(valor, quito, cambioRestante, moneda)) {
						matrizMonedas[cambioRestante][moneda] = quito;
					}
				}
			}
		}
		return matrizMonedas[cambioRestante][moneda];
	}

	public boolean esMejor(int nuevo, int quito, int cambioRestante, int moneda) {
		int acumuladoViejo = 0, acumuladoNuevo = 0;
		int numMonedasViejo = 0, numMonedasNuevo = 0;
		int aux = cambioRestante;
		for(int i = moneda; i < monedas.length; i++) {
			numMonedasViejo += matrizMonedas[aux][i];
			acumuladoViejo += matrizMonedas[aux][i]*monedas[i];
			aux -= matrizMonedas[aux][i]*monedas[i];
		}
		acumuladoNuevo += quito*monedas[moneda] + nuevo*monedas[moneda+1];
		int aux2 = cambioRestante - quito*monedas[moneda];
		aux2 -= nuevo*monedas[moneda+1];
		numMonedasNuevo += quito + nuevo;
		for(int i = moneda + 2; i < monedas.length; i++) {
			numMonedasNuevo += matrizMonedas[aux2][i];
			acumuladoNuevo += matrizMonedas[aux2][i]*monedas[i];
			aux2 -= matrizMonedas[aux2][i]*monedas[i];
		}

		if(acumuladoNuevo > acumuladoViejo) {
			return true;
		} else if(acumuladoNuevo == acumuladoViejo && numMonedasNuevo < numMonedasViejo) {
			return true;
		}
		return false;
	}
}
