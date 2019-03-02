package Practica1;

import java.util.Stack;

public class NumeroCombinatorio {

	public static void main(String[] args) {

		// Debe cumplirse que n >= k
		int n = 6, k = 2;

		System.out.println(combinatorioIterativo(n, k));
		System.out.println(combinatorioRecursivo(n, k));
		System.out.println(combinatorioPila(n, k));
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
		return combinatorio(n, k);
	}

	private static int combinatorio(int n, int k) {
		if (k == 0) {
			return 1;
		} else if (n == 0) {
			return 0;
		} else {
			return combinatorio(n - 1, k - 1) + combinatorio(n - 1, k);
		}
	}

	// Calcular numero combinatorio mediante pilas
	private static int combinatorioPila(int n, int k) {
		Stack<Integer> pilaN = new Stack<Integer>();
		Stack<Integer> pilaK = new Stack<Integer>();
		Stack<Integer> pilaL = new Stack<Integer>();
		Stack<Integer> pilaSol = new Stack<Integer>();

		pilaN.push(n);
		pilaK.push(k);
		pilaL.push(1);
		pilaSol.push(0);

		return combinatorio(pilaN, pilaK, pilaL, pilaSol);
	}

	private static int combinatorio(Stack<Integer> pilaN, Stack<Integer> pilaK, Stack<Integer> pilaL,
			Stack<Integer> pilaRes) {

		int sol = -1;
		while (!pilaN.empty() || !pilaK.empty()) {
			while (pilaN.peek() > 0 && pilaK.peek() > 0) {
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
				
				if (pilaN.peek() == 0) {
					pilaRes.push(1);
				} else if (pilaK.peek() == 0) {
					pilaRes.push(0);
				} else {
					pilaRes.push(0);
				}
			}

			pilaN.pop();
			pilaL.pop();
			sol = pilaRes.pop();

			if (!pilaN.empty()) {
				pilaL.push(pilaL.pop() + 1);
				pilaRes.push(pilaRes.pop() + sol);
			}

			if (sol == 0)
				sol = 1;
			return sol;
		}

		return 0;
	}

}
