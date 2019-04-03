package Practica2;

public class MatricesBackward {

	private int cambio;
	private int numMonedas;
	private int[] monedas;
	private int[][] matrizCambio;
	private int[][] matrizMonedas;
	private int[][] matrizRuta;

	public MatricesBackward(int cambio, int[] monedas) {

		this.cambio = cambio;
		this.numMonedas = monedas.length;
		this.monedas = monedas;
		this.matrizCambio = new int[cambio + 1][numMonedas];
		this.matrizMonedas = new int[cambio + 1][numMonedas];
		this.matrizRuta = new int[cambio + 1][numMonedas];
		inicializar();
	}

	public void inicializar() {

		for (int i = 0; i < matrizCambio.length; i++) {
			for (int j = 0; j < numMonedas; j++) {
				matrizCambio[i][j] = -1;
				matrizMonedas[i][j] = Integer.MAX_VALUE;
				matrizRuta[i][j] = 0;
			}
		}
	}

	public int[] resolver(int cambio) {

		int[] solucion = new int[monedas.length];
		matrizBackward(cambio, 0);

		for (int i = 0; i < monedas.length; i++) {
			solucion[i] = matrizRuta[cambio][i];
			cambio -= matrizRuta[cambio][i] * monedas[i];
		}

		return solucion;
	}

	public int matrizBackward(int cambioRestante, int moneda) {

		if (matrizRuta[cambioRestante][moneda] == 0) {
			if (moneda == numMonedas - 1) {
				matrizRuta[cambioRestante][moneda] = cambioRestante / monedas[moneda];
				matrizMonedas[cambioRestante][moneda] = cambioRestante / monedas[moneda];
				matrizCambio[cambioRestante][moneda] = (cambioRestante / monedas[moneda]) * monedas[moneda];

			} else {
				int max = cambioRestante / monedas[moneda];

				for (int meto = 0; meto <= max; meto++) {
					int nuevoCambio = matrizBackward(cambioRestante - monedas[moneda] * meto, moneda + 1)
							+ monedas[moneda] * meto;
					int nuevasMonedas = matrizMonedas[cambioRestante - monedas[moneda] * meto][moneda + 1] + meto;

					if (nuevoCambio <= cambio) {
						if (nuevoCambio > matrizCambio[cambioRestante][moneda]
								|| (nuevoCambio == matrizCambio[cambioRestante][moneda]
										&& nuevasMonedas < matrizMonedas[cambioRestante][moneda])) {
							matrizRuta[cambioRestante][moneda] = meto;
							matrizMonedas[cambioRestante][moneda] = nuevasMonedas;
							matrizCambio[cambioRestante][moneda] = nuevoCambio;
						}
					}
				}

			}
		}

		return matrizCambio[cambioRestante][moneda];
	}
}
