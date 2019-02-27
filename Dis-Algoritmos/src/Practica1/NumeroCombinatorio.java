package Practica1;

public class NumeroCombinatorio {

	public static void main(String[] args) {

		int n = 50, k = 20;

		System.out.println(combinatorioIterativo(n, k));
		System.out.println(combinatorioRecursivo(n, k));
		// System.out.println(combinatorioPila(n, k));
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
		return 1;
	}

}
