package Practica2;

/*
 * Clase que lleva a cabo la resolucion del cambio de monedas mediante programacion dinamica con matrices mediante el metodo forward
 */

public class MatricesForward {

	private int cambio; // Cambio a devolver
	private int numMonedas; // Numero de monedas que podemos utilizar
	private int[] monedas; // Vector de monedas, con el valor de las monedas que podemos utilizar

	// Constructor
	public MatricesForward(int cambio, int[] monedas) {
		this.cambio = cambio;
		this.monedas = monedas;
		this.numMonedas = monedas.length;
	}

	// Metodo que devuelve un vector con la solucion optima del numero de monedas a
	// meter de cada moneda
	public int[] resolver() {
		int[][] matrizValor = new int[cambio + 1][numMonedas]; // Matriz que contiene el numero de monedas acumuladas
		int[][] matrizRuta = new int[cambio + 1][numMonedas]; // Matriz que contiene el numero de monedas que es mejor
																// meter de cada tipo de moneda y utilizada para
																// realizar la ruta optima

		// Inicializamos las dos matrices la de valor a un valor alto y la de ruta a un
		// valor negativo
		for (int f = 0; f < matrizValor.length; f++)
			for (int c = 0; c < matrizValor[0].length; c++) {
				matrizValor[f][c] = 999;
				matrizRuta[f][c] = -1;
			}

		// Devolvemos y construimos la solucion optima
		return obtenerSolucion(forwardMatriz(matrizValor, matrizRuta));
	}

	// Metodo que devuelve la matrices de rutas construida
	private int[][] forwardMatriz(int[][] matrizValor, int[][] matrizRuta) {
		// Obtiene el maximo de monedas que puedo meter del primer tipo de moneda con el
		// cambio a devolver
		int maximo = cambio / monedas[0];
		// Contruimos las dos matrices del primer tipo de moneda
		for (int meto = 0; meto <= maximo; meto++) {
			matrizValor[cambio - monedas[0] * meto][0] = meto;
			matrizRuta[cambio - monedas[0] * meto][0] = meto;
		}

		// Construimos el resto de las matrices con los demas tipo de monedas
		for (int monedaActual = 1; monedaActual < numMonedas; monedaActual++) {
			// Comprobamos todas las posibles combinaciones de todos los posibles cambios
			// restantes
			for (int restante = matrizValor.length - 1; restante >= 0; restante--) {
				int max = restante / monedas[monedaActual]; // Obtiene el maximo de monedas que puedo meter de la moneda
															// actual con el cambio restante actual
				// Comprobamos todas las posibles combinaciones de todos los numero de monedas
				// que podemos meter de la moneda actual con el cambio actual
				for (int p = 0; p <= max; p++) {
					int nuevoRestante = restante - monedas[monedaActual] * p; // Obtenemos el nuevo cambio restante que
																				// se genera con el numero de monedas
																				// que meto de la moneda actual con el
																				// cambio restante actual
					// Comprbamos si el nuevo cambio que se generaria es mejor que el anterior, si
					// es asi actualizamos la matriz con los nuevos datos
					if (matrizValor[nuevoRestante][monedaActual] >= matrizValor[restante][monedaActual - 1] + p) {
						matrizValor[nuevoRestante][monedaActual] = matrizValor[restante][monedaActual - 1] + p;
						matrizRuta[nuevoRestante][monedaActual] = p;
					}

				}
			}
		}

		return matrizRuta;
	}

	// Metodo que devuelve la solucion optima construida a partir de la matriz de
	// rutas
	private int[] obtenerSolucion(int[][] sol) {

		int[] solucion = new int[monedas.length];
		boolean noSol = true;

		// Comprueba todas las posibles soluciones generadas a partir de aquella que nos
		// da la solucion con un cambio igual al cambio pedido
		for (int i = 0; i < sol.length && noSol; i++) {
			noSol = false;
			int fila = i;
			// Construimos el vector solucion
			for (int col = sol[0].length - 1; col >= 0 && !noSol; col--) {
				// Si alguno de los elementos del vector solucion es un -1, quiere decir que
				// dicha solucion no es correcta y para dicho cambio no existiria solucion por
				// lo que habria que buscar otro vector de solucion para un cambio menor
				if (sol[fila][col] == -1)
					noSol = true;
				solucion[col] = sol[fila][col];
				fila += sol[fila][col] * monedas[col];
			}

		}

		return solucion;
	}
}
