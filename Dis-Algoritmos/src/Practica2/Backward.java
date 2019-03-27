package Practica2;

import java.util.ArrayList;

public class Backward {

	private int cambio;
	private int numMonedas;
	private int[] monedas;

	public Backward(int cambio, int[] monedas) {
		this.cambio = cambio;
		this.monedas = monedas;
		this.numMonedas = monedas.length;
	}

	public Cambio resolver() {

		Cambio primer = new Cambio(-1, cambio, 0, 0, null);

		return backward(primer, new ArrayList<Cambio>());
	}

	private Cambio backward(Cambio actual, ArrayList<Cambio> cambios) {
		Cambio valor = null;

		if (cambios.contains(actual)) {
			valor = cambios.get(cambios.indexOf(actual));
		} else {
			int etapa = actual.getEtapa() + 1;
			if (etapa < numMonedas) {
				int max = actual.getRestante() / monedas[etapa];
				for (int n = 0; n <= max; n++) {
					int nuevoRestante = actual.getRestante() - monedas[etapa] * n;
					int nuevoNumMonedas = actual.getNumMonedas() + n;
					if (nuevoRestante >= 0) {
						Cambio nuevo = new Cambio(etapa, 0, 0, nuevoNumMonedas, null);
						valor = backward(nuevo, cambios);
						if (esMejor(valor, actual)) {
							actual.setRestante(valor.getRestante() - monedas[valor.getEtapa()] * n);
							actual.setPongo(n);
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

	private boolean esMejor(Cambio nuevo, Cambio viejo) {
		if (nuevo.getRestante() < viejo.getRestante())
			return true;
		else if (nuevo.getRestante() == viejo.getRestante() && nuevo.getNumMonedas() < viejo.getNumMonedas())
			return true;
		return false;
	}
}
