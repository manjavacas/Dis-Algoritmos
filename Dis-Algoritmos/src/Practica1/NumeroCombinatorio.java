package Practica1;

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

import java.util.Scanner;
import java.util.Stack;

public class NumeroCombinatorio {

	public static void main(String[] args) {

		int res = -1, n, k;
		long t0 = -1, t1 = -1;

		Scanner leer = new Scanner(System.in);
		System.out.println("Introducir valor de n: ");
		n = leer.nextInt();
		System.out.println("Introducir valor de k: ");
		k = leer.nextInt();
		leer.close();

		if (n >= k) {
			// Combinatorio iterativo
			t0 = System.nanoTime();
			res = combinatorioIterativo(n, k);
			t1 = System.nanoTime();
			System.out.println(
					"* Combinatorio iterativo (" + n + "," + k + ") = " + res + ".\tTiempo: " + (t1 - t0) + " ns.");

			// Combinatorio recursivo
			t0 = System.nanoTime();
			res = combinatorioRecursivo(n, k);
			t1 = System.nanoTime();
			System.out.println(
					"* Combinatorio recursivo (" + n + "," + k + ") = " + res + ".\tTiempo: " + (t1 - t0) + " ns.");

			// Combinatorio con pilas
			t0 = System.nanoTime();
			res = combinatorioPilas(n, k);
			t1 = System.nanoTime();
			System.out.println(
					"* Combinatorio con pilas (" + n + "," + k + ") = " + res + ".\tTiempo: " + (t1 - t0) + " ns.");
		} else {
			System.out.println("n debe ser mayor o igual que k");
		}
	}

	// Calcular factorial de un numero
	private static int factorial(int n) {
		if (n == 0) {
			return 1;
		} else {
			return n * factorial(n - 1);
		}
	}

	// Calcular numero combinatorio de forma iterativa
	private static int combinatorioIterativo(int n, int k) {
		return factorial(n) / (factorial(k) * factorial(n - k));
	}

	// Calcular numero combinatorio de forma recursiva
	private static int combinatorioRecursivo(int n, int k) {
		if (k == 0) {
			return 1;
		} else if (n == 0) {
			return 0;
		} else {
			return combinatorioRecursivo(n - 1, k - 1) + combinatorioRecursivo(n - 1, k);
		}
	}

	// Calcular numero combinatorio mediante pilas
	private static int combinatorioPilas(int n, int k) {
		Stack<Integer> pilaN = new Stack<Integer>();
		Stack<Integer> pilaK = new Stack<Integer>();
		Stack<Integer> pilaL = new Stack<Integer>();
		Stack<Integer> pilaSol = new Stack<Integer>();

		pilaN.push(n);
		pilaK.push(k);
		pilaL.push(1);
		pilaSol.push(0);

		return combinatorioPilas(pilaN, pilaK, pilaL, pilaSol);
	}

	private static int combinatorioPilas(Stack<Integer> pilaN, Stack<Integer> pilaK, Stack<Integer> pilaL,
			Stack<Integer> pilaSol) {

		int sol = 0;
		while (!pilaN.empty() && !pilaK.empty()) {
			while (pilaN.peek() > 0 && pilaK.peek() > 0 && pilaL.peek() <= 2) {
				switch (pilaL.peek()) {
				// Primera llamada
				case 1:
					pilaN.push(pilaN.peek() - 1);
					pilaK.push(pilaK.peek() - 1);
					break;
				// Segunda llamada
				case 2:
					pilaN.push(pilaN.peek() - 1);
					pilaK.push(pilaK.peek());
					break;
				}
				pilaL.push(1);

				// Caso base
				if (pilaK.peek() == 0) {
					pilaSol.push(1);
				} else {
					pilaSol.push(0);
				}
			}

			// Retornos
			pilaN.pop();
			pilaK.pop();
			pilaL.pop();
			sol = pilaSol.pop();

			// Siguiente llamada e incremento de la solucion
			if (!pilaN.empty() && !pilaK.empty()) {
				pilaL.push(pilaL.pop() + 1);
				pilaSol.push(pilaSol.pop() + sol);
			}
		}

		// Solucion para k = 0
		if (sol == 0)
			sol = 1;

		return sol;
	}

}
