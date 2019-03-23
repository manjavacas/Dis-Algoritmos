package Practica2;

import java.util.ArrayList;

public class CambioForward {

	private int cambio;
	private ArrayList<Moneda> monedas;

	public CambioForward(int cambio, ArrayList<Moneda> monedas) {
		this.cambio = cambio;
		this.monedas = monedas;
	}

	// Version forward con lista de adyacentes
	public ArrayList<Cambio> getSolucion(int cambio, ArrayList<Moneda> monedas) {

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

	// Comprueba si un cambio ya ha sido incluido en la lista de cambios generados
	private boolean contiene(ArrayList<Cambio> cambios, Cambio nuevo) {
		for (int i = 0; i < cambios.size(); i++) {
			if (cambios.get(i).equals(nuevo)) {
				return true;
			}
		}
		return false;
	}

	// Comprueba si el cambio c1 es mejor que el cambio c2
	private boolean esMejor(Cambio c1, Cambio c2) {
		return c1.getNumMonedas() < c2.getNumMonedas();
	}

	// Devuelve el mejor cambio de la solucion obtenida
	private Cambio mejorCambio(ArrayList<Cambio> solucion) {

		Cambio mejor = solucion.get(0);

		for (int i = 0; i < solucion.size(); i++) {
			// Mejor cambio cuanto mayor sea la cantidad devuelta
			if (solucion.get(i).getAcumulado() > mejor.getAcumulado()) {
				mejor = solucion.get(i);
				// Mejor cuantas menos monedas se utilicen
			} else if (solucion.get(i).getAcumulado() == mejor.getAcumulado()) {
				if (esMejor(solucion.get(i), mejor))
					mejor = solucion.get(i);
			}
		}

		return mejor;
	}

	// Muestra la secuencia de acciones para la version forward
	public ArrayList<Cambio> accionesForward(ArrayList<Cambio> solucion) {

		ArrayList<Cambio> acciones = new ArrayList<Cambio>();
		Cambio actual = mejorCambio(solucion);

		while (actual != null) {
			acciones.add(actual);
			actual = actual.getVieneDe();
		}
		return acciones;
	}

	public int getCambio() {
		return cambio;
	}

	public void setCambio(int cambio) {
		this.cambio = cambio;
	}

	public ArrayList<Moneda> getMonedas() {
		return monedas;
	}

	public void setMonedas(ArrayList<Moneda> monedas) {
		this.monedas = monedas;
	}

}