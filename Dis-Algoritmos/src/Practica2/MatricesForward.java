package Practica2;

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
		int[] solucion = new int[numMonedas];
		int[][] ruta = new int[cambio + 1][numMonedas + 1];
		int[][] valores = new int[cambio + 1][numMonedas + 1];

		for (int f = 0; f < valores.length; f++)
			for (int c = 0; c < valores[0].length; c++)
				valores[f][c] = -1;

		forwardMatriz(valores, ruta);
		solucion = matriz(valores, ruta);

		System.out.println("\n******** VALORES *******");
		for (int i = 0; i < valores.length; i++) {
			for (int j = 0; j < valores[0].length; j++) {
				if (valores[i][j] == -1)
					System.out.print("X   ");
				else
					System.out.print(valores[i][j] + "   ");
			}
			System.out.println();
		}

		System.out.println("\n******** RUTA *******");
		for (int i = 0; i < ruta.length; i++) {
			for (int j = 0; j < ruta[0].length; j++) {
				if (ruta[i][j] == -1)
					System.out.print("X   ");
				else
					System.out.print(ruta[i][j] + "   ");
			}
			System.out.println();
		}

		return solucion;
	}

	private void forwardMatriz(int[][] valores, int[][] ruta) {

		valores[valores.length - 1][0] = 0;

		for (int colMoneda = 0; colMoneda < valores[0].length - 1; colMoneda++) {
			for (int fil = 0; fil < valores.length; fil++) {
				if (valores[fil][colMoneda] >= 0) {
					int nuevaCol = colMoneda + 1;
					int max = cambio / monedas[colMoneda];
					for (int p = 0; p <= max; p++) {
						int nuevaFil = fil - monedas[colMoneda] * p;
						if (nuevaFil >= 0) {
							if (valores[nuevaFil][nuevaCol] < valores[fil][colMoneda] + monedas[colMoneda] * p) {
								valores[nuevaFil][nuevaCol] = valores[fil][colMoneda] + monedas[colMoneda] * p;
								ruta[nuevaFil][nuevaCol] = p;
							}
						}
					}
				}
			}
		}
	}

	private int[] matriz(int[][] valores, int[][] ruta) {
		int filaMejor = filaMejor(valores[0].length - 1, valores, ruta);
		int[] sol = new int[numMonedas];

		for (int col = valores[0].length - 1; col > 0; col--) {
			sol[col - 1] = ruta[filaMejor][col];
			if (sol[col - 1] == 1)
				filaMejor = filaMejor + monedas[col - 1];
		}
		return sol;
	}

	private int filaMejor(int col, int[][] valores, int[][] ruta) {
		int filaMejor = 0;
		for (int fil = 1; fil < valores.length; fil++)
			if (valores[fil][col] > valores[filaMejor][col])
				filaMejor = fil;
		return filaMejor;
	}

}
