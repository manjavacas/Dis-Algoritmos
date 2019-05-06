package Practica4;

import java.util.ArrayList;

/**
 * Metodos de busqueda: Naive, Karp-Rabin, Shift-OR
 *
 * @author Antonio.Manjavacas, Ruben.Marquez
 *
 */

public class Busquedas {

	private String patron;
	private String texto;

	public Busquedas(String patron, String texto) {
		this.patron = patron;
		this.texto = texto;
	}

	public int naive() {
		int ocurrencias = 0;

		// ArrayList<Integer> ocurrencias = new ArrayList<Integer>();

		if (patron.length() > 0 && texto.length() >= patron.length()) {
			int t = 0; // desplazamiento en el texto
			int p = 0; // desplazamiento en el patron

			while (texto.length() - t >= patron.length()) {
				if (texto.charAt(t) == patron.charAt(p)) { // busqueda
					int T = t + 1;
					int P = 1;

					while (P < patron.length() && texto.charAt(T) == patron.charAt(P)) {
						T++;
						P++;
					}

					if (P == patron.length())
						ocurrencias++;
					// ocurrencias.add(t);
				}
				t++;
			}
		}

		return ocurrencias;
	}

	public int karpRabin() {
		ArrayList<Integer> ocurrencias = new ArrayList<Integer>();

		if (patron.length() > 0 && texto.length() >= patron.length()) {
			KarpRabin(ocurrencias);
		}

		return ocurrencias.size();
	}

	private void KarpRabin(ArrayList<Integer> ocurrencias) {
		int m = patron.length();

		for (int n = 0; n <= texto.length() - m; n++) {
			String aux = texto.substring(n, n + m);

			if (aux.hashCode() == patron.hashCode() && aux.equals(patron))
				ocurrencias.add(n);
		}
	}

	public int shiftOr() {
		ArrayList<Integer> ocurrencias = new ArrayList<Integer>();

		if (patron.length() > 0 && texto.length() >= patron.length()) {
			String patronInvertido = invertir();
			ArrayList<Character> alfabeto = new ArrayList<Character>();
			// guardada los caracteres para localizarlos en alfabetoPatron
			ArrayList<int[]> alfabetoPatron = new ArrayList<int[]>(); // lista de bits de caracteres
			bitsPatron(patronInvertido, alfabeto, alfabetoPatron); // preproceso
			ocurrencias = ShiftOr(alfabeto, alfabetoPatron);
		}

		return ocurrencias.size();
	}

	public String invertir() {
		String inv = "";

		for (int n = patron.length() - 1; n >= 0; n--)
			inv = inv + patron.charAt(n);

		return inv;
	}

	private static void bitsPatron(String inv, ArrayList<Character> alfabeto, ArrayList<int[]> alfabetoPatron) {
		// extrae la lista de los bits de los caracteres. Preproceso
		for (int n = 0; n < inv.length(); n++) {
			if (!alfabeto.contains(inv.charAt(n))) {
				alfabeto.add(inv.charAt(n));
				int[] bits = new int[inv.length()];

				for (int p = 0; p < inv.length(); p++) {
					if (inv.charAt(p) == inv.charAt(n))
						bits[p] = 0;
					else
						bits[p] = 1;
				}

				alfabetoPatron.add(bits);
			}
		}
	}

	public ArrayList<Integer> ShiftOr(ArrayList<Character> alfabeto, ArrayList<int[]> alfabetoPatron) {
		ArrayList<Integer> ocurrencias = new ArrayList<Integer>();
		int[] estado = new int[patron.length()];

		for (int e = 0; e < estado.length; e++)
			estado[e] = 1;

		Boolean coincide = false;

		for (int n = 0; n < texto.length(); n++) {
			coincide = OR(texto.charAt(n), estado, coincide, alfabeto, alfabetoPatron);

			if (estado[0] == 0)
				ocurrencias.add(n - patron.length() + 1);
		}

		return ocurrencias;
	}

	private boolean OR(char c, int[] estado, Boolean coincide, ArrayList<Character> alfabeto,
			ArrayList<int[]> alfabetoPatron) {
		if (coincide) { // desplazamiento a la izqda y cero a la dcha
			for (int n = 0; n < estado.length - 1; n++)
				estado[n] = estado[n + 1];
		}

		estado[estado.length - 1] = 0;
		int[] aux = new int[estado.length];

		if (alfabeto.contains(c)) {
			aux = alfabetoPatron.get(alfabeto.indexOf(c));
			coincide = true;
		} else {

			for (int n = 0; n < aux.length; n++)
				aux[n] = 1;
			coincide = false;
		}

		for (int n = 0; n < aux.length; n++) {
			if (aux[n] == 0 && estado[n] == 0)
				estado[n] = 0;
			else
				estado[n] = 1;
		}

		return coincide;
	}

}
