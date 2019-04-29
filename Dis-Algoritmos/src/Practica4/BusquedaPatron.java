package Practica4;

import java.io.File;

/**
 * Busqueda de patrones en texto
 * 
 * @author Antonio.Manjavacas, Ruben.Marquez
 *
 */

public class BusquedaPatron {

	private static final String FICHERO = "src/Practica4/quijote1.txt";

	public static void main(String[] args) {

		// String file = Leer.cadena("Introducir ubicacion del fichero: ");
		File f = new File(FICHERO);
		System.out.println("* Introducido fichero " + f.getName() + " de longitud = " + f.length());

		String patron = Leer.cadena("* Introducir cadena a buscar en el texto: ");
		int porcentaje = Leer.entero("* Introducir porcentaje de texto a procesar: ");

		medirTiempos(patron, f, porcentaje);
	}

	private static void medirTiempos(String patron, File f, int porcentaje) {

		// Extraer texto a procesar
		ProcesadorTexto pt = new ProcesadorTexto(f, porcentaje);
		String texto = pt.procesar();

		// Busqueda
		Busquedas b = new Busquedas(patron, texto);
		int ocurrencias = -1;
		long t0, t1;

		// Naive (fuerza bruta)
		t0 = System.nanoTime();
		ocurrencias = b.naive();
		t1 = System.nanoTime();
		System.out.println("\n[NAIVE] " + ocurrencias + " ocurrencias. Procesado " + porcentaje
				+ "% del texto. Ejecutado en " + (t1 - t0) + " ns");

		// Karp-Rabin
		t0 = System.nanoTime();
		ocurrencias = b.karpRabin();
		t1 = System.nanoTime();
		System.out.println("\n[KARP-RABIN] " + ocurrencias + " ocurrencias. Procesado " + porcentaje
				+ "% del texto. Ejecutado en " + (t1 - t0) + " ns");

		// Shift-Or
		t0 = System.nanoTime();
		ocurrencias = b.shiftOr();
		t1 = System.nanoTime();
		System.out.println("\n[SHIFT-OR] " + ocurrencias + " ocurrencias. Procesado " + porcentaje
				+ "% del texto. Ejecutado en " + (t1 - t0) + " ns");

	}

}
