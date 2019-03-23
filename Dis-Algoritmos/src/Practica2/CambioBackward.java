package Practica2;

import java.util.ArrayList;

public class CambioBackward {

	// Version backward con lista de adyacentes
	private ArrayList<Cambio> getSolucion(int cambio, ArrayList<Moneda> monedas) {
		ArrayList<Cambio> cambios = new ArrayList<Cambio>();
		Cambio solucion = backward(cambio, monedas, cambios, new Cambio(null));
		return null;
	}

	private Cambio backward(int cambio, ArrayList<Moneda> monedas, ArrayList<Cambio> cambios, Cambio actual) {
		Cambio siguiente = null;
		if (cambios.contains(actual)) {
			siguiente = cambios.get(cambios.indexOf(actual));
		} else {
			ArrayList<Cambio> adyacentes = getAdyacentes(actual, cambio, monedas);
			for (int i = 0; i < adyacentes.size(); i++) {
				Cambio nuevo = adyacentes.get(i);
				siguiente = backward(cambio, monedas, cambios, nuevo);
			}
		}
		return siguiente;
	}

	// Devuelve los cambios posibles a partir de un cambio dado
	private ArrayList<Cambio> getAdyacentes(Cambio actual, int cambio, ArrayList<Moneda> monedas) {

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
}
