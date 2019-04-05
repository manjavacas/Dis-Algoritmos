package Practica1;

/**
 * Numero combinatorio: factorial, iterativo, pilas
 * 
 * @author Antonio.Manjavacas, Ruben.Marquez
 *         
 **/

import java.util.Stack;

public class NumeroCombinatorio {
	private int n;
	private int k;

	public NumeroCombinatorio(int n, int k) {
		this.n = n;
		this.k = k;
	}

	private int factorial(int n) {
		if (n == 0) {
			return 1;
		} else {
			return n * factorial(n - 1);
		}
	}

	/************ Combinatorio iterativo ************/
	public int combinatorioIterativo() {
		return factorial(n) / (factorial(k) * factorial(n - k));
	}

	/************ Combinatorio recursivo ************/
	public int combinatorioRecursivo() {
		return combinatorioRecursivo(n, k);
	}

	public int combinatorioRecursivo(int n, int k) {
		if (k == 0) {
			return 1;
		} else if (n == 0) {
			return 0;
		} else {
			return combinatorioRecursivo(n - 1, k - 1) + combinatorioRecursivo(n - 1, k);
		}
	}

	/************ Combinatorio con pilas ************/
	public int combinatorioPilas() {
		Stack<Integer> pilaN = new Stack<Integer>();
		Stack<Integer> pilaK = new Stack<Integer>();
		Stack<Integer> pilaL = new Stack<Integer>();
		Stack<Integer> pilaSol = new Stack<Integer>();

		pilaN.push(n);
		pilaK.push(k);
		pilaL.push(1);
		pilaSol.push(0);

		if (k == 0)
			return 1;
		else
			return combinatorioPilas(pilaN, pilaK, pilaL, pilaSol);
	}

	private int combinatorioPilas(Stack<Integer> pilaN, Stack<Integer> pilaK, Stack<Integer> pilaL,
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
			if (!pilaN.empty()) {
				pilaL.push(pilaL.pop() + 1);
				pilaSol.push(pilaSol.pop() + sol);
			}
		}

		return sol;
	}

}
