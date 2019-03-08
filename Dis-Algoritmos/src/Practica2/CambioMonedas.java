package Practica2;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Antonio Manjavacas, Ruben Marquez
 * 
 *         DEVOLUCION DE CAMBIO
 * 
 *         Se tienen k monedas: m1,m2,...,mn. Devolver un cambio C con el menor
 *         numero de monedas posible. En caso de no ser posible, devolver la
 *         mayor cantidad sin pasarse.
 * 
 *         Representar el grafo multietapico empleado y realizar implementacion
 *         backward, forward y matricial.
 * 
 **/

public class CambioMonedas {

	public static void main(String[] args) {

		ArrayList<Moneda> solucion = new ArrayList<Moneda>();
		ArrayList<Moneda> actual = new ArrayList<Moneda>();
		ArrayList<Moneda> monedas = new ArrayList<Moneda>();

		// Lectura de datos
		Scanner leer = new Scanner(System.in);

		System.out.println("Introducir cambio a devolver: ");
		int cambio = leer.nextInt();

		System.out.println("Introducir valores de monedas (separados por comas):");
		String valores = leer.next();
		leer.close();

		String[] tokens = valores.split(",");
		for (int n = 0; n < tokens.length; n++) {
			monedas.add(new Moneda(Integer.parseInt(tokens[n])));
		}

		System.out.println("\nIntroducido cambio = " + cambio + " y monedas = " + monedas + "\n");

		// Mostrar solucion
		solucion = backtrackingCambio(cambio, monedas, solucion, actual);
		System.out.println("\nMejor solucion: " + solucion);
	}

	// Solucion backtracking al problema
	private static ArrayList<Moneda> backtrackingCambio(int cambio, ArrayList<Moneda> monedas,
			ArrayList<Moneda> solucion, ArrayList<Moneda> actual) {

		Moneda m = null;
		if (esSolucion(actual, cambio) && esMejor(actual, solucion)) {
			solucion = new ArrayList<Moneda>(actual);
			System.out.println("Solucion: " + solucion);
		} else {
			for (int i = 0; i < monedas.size(); i++) {
				m = monedas.get(i);
				if (suma(actual) + m.getValor() <= cambio) {
					actual.add(m);
					solucion = backtrackingCambio(cambio, monedas, solucion, actual);
					actual.remove(m);
				}
			}
		}
		return solucion;
	}

	// Devuelve la suma de los valores de una lista de monedas
	private static int suma(ArrayList<Moneda> monedas) {
		int suma = 0;
		for (int i = 0; i < monedas.size(); i++) {
			suma += monedas.get(i).getValor();
		}
		return suma;
	}

	// Comprueba si una lista de monedas es solucion
	private static boolean esSolucion(ArrayList<Moneda> monedas, int max) {
		return suma(monedas) == max;
	}

	// Comprueba si la solucion s1 es mejor que la solucion s2
	private static boolean esMejor(ArrayList<Moneda> s1, ArrayList<Moneda> s2) {
		// Una solucion es mejor que otra si emplea un menor numero de monedas
		// El primer caso siempre es mejor --> s2.size == 0
		return s1.size() < s2.size() || s2.size() == 0;
	}

}