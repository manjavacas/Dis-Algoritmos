package Practica1;

import java.util.Scanner;

/**
 * @author Antonio Manjavacas, Ruben Marquez
 *
 *         CALCULAR NUMERO COMBINATORIO
 *
 *         Calcular el numero combinatorio C(n,k).
 *
 **/

public class Practica1 {

	static Scanner leer = new Scanner(System.in);

	public static void main(String [] args) {

		int res = -1, n, k;
		long t0 = -1, t1 = -1;

		System.out.println("Introducir valor de n: ");
		n = leer.nextInt();
		System.out.println("Introducir valor de k: ");
		k = leer.nextInt();
		leer.close();


		if (n >= k) {

			NumeroCombinatorio numComb = new NumeroCombinatorio(n,k);

			// Combinatorio iterativo
			t0 = System.nanoTime();
			res = numComb.combinatorioIterativo();
			t1 = System.nanoTime();
			System.out.println(
					"* Combinatorio iterativo (" + n + "," + k + ") = " + res + ".\tTiempo: " + (t1 - t0) + " ns.");

			// Combinatorio recursivo
			t0 = System.nanoTime();
			res = numComb.combinatorioRecursivo();
			t1 = System.nanoTime();
			System.out.println(
					"* Combinatorio recursivo (" + n + "," + k + ") = " + res + ".\tTiempo: " + (t1 - t0) + " ns.");

			// Combinatorio con pilas
			t0 = System.nanoTime();
			res = numComb.combinatorioPilas();
			t1 = System.nanoTime();
			System.out.println(
					"* Combinatorio con pilas (" + n + "," + k + ") = " + res + ".\tTiempo: " + (t1 - t0) + " ns.");
		} else {
			System.out.println("n debe ser mayor o igual que k");
		}
	}
}
