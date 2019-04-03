package Practica2;

/*
 * Clase que lleva a cabo la resolucion del cambio de monedas mediante programacion dinamica con una lista mediante el metodo backward
 */

import java.util.ArrayList;

public class Backward {

	private int cambio; // Cambio a devolver
	private int numMonedas; // Numero de monedas que podemos utilizar
	private int[] monedas; // Vector de monedas, con el valor de las monedas que podemos utilizar

	// Constructor
	public Backward(int cambio, int[] monedas) {
		this.cambio = cambio;
		this.monedas = monedas;
		this.numMonedas = monedas.length;
	}

	// Metodo que devuelve un objeto cambio con el cambio mas optimo conseguido en
	// la primera etapa
	public Cambio resolver() {
		Cambio primero = new Cambio(-1, cambio, 0, 0, 0, null);
		return backward(primero, new ArrayList<Cambio>());
	}

	// Matodo recursivo que devuelve el mejor cambio mediante programacion dinamica
	// con backward
	private Cambio backward(Cambio actual, ArrayList<Cambio> cambios) {
		Cambio valor = null;

		// Si el cambio actual ya existe en la lista, se devuelve directamente
		if (cambios.contains(actual)) {
			valor = cambios.get(cambios.indexOf(actual));
		} else {
			int etapa = actual.getEtapa() + 1; // Actualizamos a la etapa siguiente
			if (etapa < numMonedas) { // Mientras nos queden monedas por comprobar seguimos
				int max = actual.getRestante() / monedas[etapa]; // Obtiene el maximo de monedas puedo meter de la
																	// moneda actual con el cambio restante actual
				for (int n = 0; n <= max; n++) { // Comprobamos todas las combinaciones de monedas a meter
					int nuevoRestante = actual.getRestante() - monedas[etapa] * n; // Obtenemos el nuevo cambio restante
					if (nuevoRestante >= 0) {
						// Obtenemos el mejor cambio siguiente
						Cambio nuevo = new Cambio(etapa, nuevoRestante, n, 0, 0, null);
						valor = backward(nuevo, cambios);
						if (esMejor(valor, actual)) { // Si el nuevo cambio mejora al actual, actualizamos los datos del
														// actual
							actual.setNumMonedas(valor.getNumMonedas() + valor.getPongo());
							actual.setValor(valor.getValor() + valor.getPongo() * monedas[valor.getEtapa()]);
							actual.setVieneDe(valor);
						}
					}
				}
			}

			valor = actual;
			cambios.add(actual);
		}

		return valor;
	}

	// Metodo que comprueba si un nuevo cambio es mejor que su anterior
	private boolean esMejor(Cambio nuevo, Cambio viejo) {
		// Es mejor si el valor de cambio acumulado del viejo es igual a 0
		if (viejo.getValor() == 0)
			return true;
		// Es mejor si el valor de cambio acumulado del viejo es menor que el nuevo
		// cambio que se genera
		else if (nuevo.getValor() + nuevo.getPongo() * monedas[nuevo.getEtapa()] > viejo.getValor())
			return true;
		// Es mejor si el valor de cambio acumulado del viejo es igual que el nuevo
		// cambio que se genera y si el numero de monedas acumuladas del nuevo cambio es
		// menor que el viejo
		else if (nuevo.getValor() + nuevo.getPongo() * monedas[nuevo.getEtapa()] == viejo.getValor()
				&& nuevo.getNumMonedas() + nuevo.getPongo() < viejo.getNumMonedas())
			return true;
		return false;
	}
}
