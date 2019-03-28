package Practica2;

import java.util.Arrays;

public class CambioMonedas {

	public static void main(String[] args) {

		int cambio = 800;
		int[] monedas = {15,37,3,1};
		Arrays.sort(monedas);

		forward(cambio, monedas);
		backward(cambio, monedas);
	}

	private static void forward(int cambio, int[] monedas) {
		Forward f = new Forward(cambio, monedas);
		Cambio solucion = f.resolver();
		int[] sol = solucionF(solucion, monedas);
		System.out.println("****************** FORWARD ******************");
		imprimir(sol, monedas);
	}

	private static int[] solucionF(Cambio solucion, int[] monedas) {

		Cambio cambio = solucion;
		int[] sol = new int[monedas.length];

		for(int i = sol.length - 1; i >= 0; i--) {
			sol[i] = cambio.getPongo();
			cambio = cambio.getVieneDe();
		}

		return sol;
	}

	private static void imprimir(int[] sol, int[] monedas) {

		for(int i = 0; i < sol.length; i++) {
			System.out.println("\tPoner " + sol[i] + " monedas con valor " + monedas[i]);
		}
	}

	private static void backward(int cambio, int[] monedas) {
		Backward b = new Backward(cambio, monedas);
		Cambio solucion = b.resolver();
		int[] sol = solucionB(solucion, monedas);
		System.out.println("\n\n****************** BACKWARD ******************");
		imprimir(sol, monedas);
	}

	private static int[] solucionB(Cambio solucion, int[] monedas) {

		Cambio cambio = solucion.getVieneDe();
		int[] sol = new int[monedas.length];

		for(int i = 0; i < sol.length; i++) {
			sol[i] = cambio.getPongo();
			cambio = cambio.getVieneDe();
		}

		return sol;
	}
}
