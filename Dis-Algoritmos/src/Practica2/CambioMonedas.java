package Practica2;

import java.util.ArrayList;

/**
 * @author Antonio Manjavacas, Ruben Marquez
 * 
 *         DEVOLUCION DE CAMBIO
 * 
 *         Se tienen k monedas: m1,m2,...,mn. Devolver un cambio C con el menor
 *         numero de monedas posible. En caso de no ser posible, devolver la
 *         mayor cantidad sin pasarse.
 *         
 *         (SE ASUME UN NUMERO ILIMITADO DE MONEDAS)
 * 
 *         Representar el grafo multietapico empleado y realizar implementacion
 *         backward, forward y matricial.
 * 
 **/

public class CambioMonedas {

	public static void main(String[] args) throws CloneNotSupportedException {

		ArrayList<Moneda> monedas = new ArrayList<Moneda>();
		ArrayList<Cambio> solucion = new ArrayList<Cambio>();

		// Datos de entrada
		int cambio = 13;
		monedas.add(new Moneda(1));
		monedas.add(new Moneda(2));
		monedas.add(new Moneda(4));
		monedas.add(new Moneda(6));

		System.out.println("\nIntroducido cambio = " + cambio + " y monedas (valor, cantidad) = " + monedas + "\n");

		// Version forward
		solucion = forward(cambio, monedas);
		System.out.println("**************** FORWARD ****************");
		System.out.println("\nSecuencia de acciones: ");
		accionesForward(solucion);
	}

	// Muestra la secuencia de acciones para la version forward
	private static void accionesForward(ArrayList<Cambio> solucion) {

		Cambio actual = mejorCambio(solucion);

		while (actual != null) {
			System.out.println(actual);
			actual = actual.getVieneDe();
		}

	}

	// Devuelve el mejor cambio de la solucion obtenida
	private static Cambio mejorCambio(ArrayList<Cambio> solucion) {

		Cambio mejor = solucion.get(0);

		for (int i = 0; i < solucion.size(); i++) {
			// Mejor cambio cuanto mayor sea la cantidad devuelta
			if (solucion.get(i).getAcumulado() > mejor.getAcumulado()) {
				mejor = solucion.get(i);
				// En caso de devolver la misma cantidad, mejor cuantas menos monedas se
				// utilicen
			} else if (solucion.get(i).getAcumulado() == mejor.getAcumulado()) {
				if (esMejor(solucion.get(i), mejor))
					mejor = solucion.get(i);
			}
		}

		return mejor;
	}

	// Version forward con lista de adyacentes
	private static ArrayList<Cambio> forward(int cambio, ArrayList<Moneda> monedas) {

		ArrayList<Cambio> cambios = new ArrayList<Cambio>();
		cambios.add(new Cambio(null));

		int pos = 0;

		while (pos < cambios.size()) {

			// Obtener cambio y generar sus adyacentes
			Cambio actual = cambios.get(pos);
			ArrayList<Cambio> adyacentes = getAdyacentes(actual, cambio, monedas);

			// Para cada adyacente, comprobar si ya ha sido creado
			for (int i = 0; i < adyacentes.size(); i++) {
				Cambio nuevo = adyacentes.get(i);

				// Si no ha sido creado, se incluye en la lista
				if (!contiene(cambios, nuevo)) {
					cambios.add(nuevo);
					// Si ya ha sido creado, se comprueba si es mejor
				} else {
					Cambio previo = cambios.get(cambios.indexOf(nuevo));
					// Si es mejor, se actualiza el previo
					if (esMejor(nuevo, previo)) {
						previo.setMonedas(nuevo.getMonedas());
						previo.setVieneDe(nuevo.getVieneDe());
					}
				}
			}

			pos++;
		}

		return cambios;

	}

	// Comprueba si un cambio ya ha sido incluido en la lista de cambios generados
	private static boolean contiene(ArrayList<Cambio> cambios, Cambio nuevo) {
		for (int i = 0; i < cambios.size(); i++) {
			if (cambios.get(i).equals(nuevo)) {
				return true;
			}
		}
		return false;
	}

	// Devuelve los cambios posibles a partir de un cambio dado
	private static ArrayList<Cambio> getAdyacentes(Cambio actual, int cambio, ArrayList<Moneda> monedas) {

		ArrayList<Cambio> adyacentes = new ArrayList<Cambio>();

		for (int i = 0; i < monedas.size(); i++) {
			Moneda m = monedas.get(i);
			// Si lo acumulado mas la moneda no excede el cambio
			if (actual.getAcumulado() + m.getValor() <= cambio) {
				// Se crea el adyacente y se incluye en la lista
				Cambio nuevo = new Cambio(actual);
				nuevo.getMonedas().addAll(actual.getMonedas());
				nuevo.getMonedas().add(m);
				adyacentes.add(nuevo);
			}

		}
		return adyacentes;
	}

	// Comprueba si el cambio c1 es mejor que el cambio c2
	private static boolean esMejor(Cambio c1, Cambio c2) {
		// Un cambio es mejor que otro si emplea un menor numero de monedas
		return c1.getMonedas().size() < c2.getMonedas().size();
	}

}