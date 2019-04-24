package Practica4;

/**
 * Busqueda de patrones en texto
 * 
 * @author Antonio.Manjavacas, Ruben.Marquez
 *
 */

public class BusquedaPatron {

	public static void main(String[] args) {

		String patron = Leer.cadena("Introducir cadena a buscar en el texto: ");
		medirTiempos(patron);
	}

	private static void medirTiempos(String patron) {
		Busquedas b = new Busquedas(patron);

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
