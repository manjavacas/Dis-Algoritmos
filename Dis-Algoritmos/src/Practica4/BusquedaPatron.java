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
		
		medirTiempos(patron, f);
	}

	private static void medirTiempos(String patron, File f) {
		Busquedas b = new Busquedas(patron, f);

		int ocurrencias = -1;
		long t0, t1;

		// Naive (fuerza bruta)
		t0 = System.nanoTime();
		ocurrencias = b.naive();
		t1 = System.nanoTime();
		System.out.println("\n[NAIVE] " + ocurrencias + " ocurrencias. Ejecutado en " + (t1 - t0) + " ns");

		// Karp-Rabin
		t0 = System.nanoTime();
		ocurrencias = b.karpRabin();
		t1 = System.nanoTime();
		System.out.println("\n[KARP-RABIN] " + ocurrencias + " ocurrencias. Ejecutado en " + (t1 - t0) + " ns");

		// Shift-Or
		t0 = System.nanoTime();
		ocurrencias = b.shiftOr();
		t1 = System.nanoTime();
		System.out.println("\n[SHIFT-OR] " + ocurrencias + " ocurrencias. Ejecutado en " + (t1 - t0) + " ns");

	}

}
