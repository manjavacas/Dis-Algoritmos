package Practica2;

import java.util.Arrays;

public class CambioMonedas {

	public static void main(String[] args) {

		// Entrada de datos
		int cambio = Leer.entero("Introducir cambio a devolver: ");
		String valores = Leer.cadena("Introducir valores de monedas separados por comas: ");

		String[] tokens = valores.split(",");
		int[] monedas = new int[tokens.length];
		for (int i = 0; i < tokens.length; i++) {
			monedas[i] = Integer.parseInt(tokens[i]);
		}

		Arrays.sort(monedas);

		// Calcular cambio
		forward(cambio, monedas);
		backward(cambio, monedas);
		forwardMatrices(cambio, monedas);
		backwardMatrices(cambio, monedas);
	}

	private static void forward(int cambio, int[] monedas) {
		Forward f = new Forward(cambio, monedas);
		Cambio solucion = f.resolver();
		int[] sol = solucionForward(solucion, monedas);
		System.out.println("****************** FORWARD ******************");
		imprimir(sol, monedas);
	}

	private static int[] solucionForward(Cambio solucion, int[] monedas) {
		Cambio cambio = solucion;
		int[] sol = new int[monedas.length];

		for (int i = sol.length - 1; i >= 0; i--) {
			sol[i] = cambio.getPongo();
			cambio = cambio.getVieneDe();
		}

		return sol;
	}

	private static void backward(int cambio, int[] monedas) {
		Backward b = new Backward(cambio, monedas);
		Cambio solucion = b.resolver();
		int[] sol = solucionBackward(solucion, monedas);
		System.out.println("\n\n****************** BACKWARD ******************");
		imprimir(sol, monedas);
	}

	private static int[] solucionBackward(Cambio solucion, int[] monedas) {
		Cambio cambio = solucion.getVieneDe();
		int[] sol = new int[monedas.length];

		for (int i = 0; i < sol.length; i++) {
			sol[i] = cambio.getPongo();
			cambio = cambio.getVieneDe();
		}

		return sol;
	}

	private static void forwardMatrices(int cambio, int[] monedas) {
		MatricesForward fm = new MatricesForward(cambio, monedas);
		int[] sol = fm.resolver();
		System.out.println("\n\n****************** FORWARD MATRICES ******************");
		imprimir(sol, monedas);
	}

	private static void backwardMatrices(int cambio, int[] monedas) {
		MatricesBackward bm = new MatricesBackward(cambio, monedas);
		int[] sol = bm.resolver(cambio);
		System.out.println("\n\n****************** BACKWARD MATRICES ******************");
		imprimir(sol, monedas);
	}

	private static void imprimir(int[] sol, int[] monedas) {
		for (int i = 0; i < sol.length; i++) {
			System.out.println("\tPoner " + sol[i] + " monedas con valor " + monedas[i]);
		}
	}
}
