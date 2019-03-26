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

/**
 *
 * ********************************* COMBINATORIO ITERATIVO ********************************
 *
 * 		La complejidad viene dada por el calculo del factorial
 * 		Sea la complejidad de factorial(n):
 * 			| T(n) = 1, si n <= 1
 * 			| T(n) = T(n-1) + 1, si n > 1
 * 		Por expansion de recurrencia:
 * 			| T(n) = 1 + T(n-1) = 1 + 1 + T(n-2) = ... = k + T(n-k) si n > k
 * 		Para k = n - 1 tenemos:
 * 			| T(n) = n-1 + T(n-n+1) = n-1 + T(1) = n
 * 		Por tanto:
 * 			| T(n,k) = T(n) + T(k) + T(n-k) + c <=> 3*T(n) + c = 3n + c € O(n)
 *
 * ********************************* COMBINATORIO RECURSIVO ********************************
 *
 * 		Sea la complejidad del algoritmo recursivo:
 * 			| T(n) = 2*T(n-1), si n > o
 * 			| T(n) = 1, si n = 0
 * 		Por expansion de recurrencia:
 * 			| T(n) = 2 * T(n-1) + 1 = 2 * (2*T(n-2) + 1) + 1 = ... = 2^k * T(n-k) + (2^k-1)
 * 		Para k = n - 1 tenemos:
 * 			| T(n) = 2^(n-1) * T(1) + 2^(n-1) - 1 € O(2^n)
 *
 * ********************************* COMBINATORIO CON PILAS ********************************
 *
 * 		Se estima una complejidad completamente intratable.
 *
 * **/

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
