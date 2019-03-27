package Practica2;

import java.util.ArrayList;

public class Forward {

	private int cambio;
	private int numMonedas;
	private int[] monedas;

	public Forward(int cambio, int[] monedas) {
		this.cambio = cambio;
		this.monedas = monedas;
		this.numMonedas = monedas.length;
	}

	public Cambio resolver() {

		ArrayList<Cambio> cambios = new ArrayList<Cambio>();
		Cambio nuevo = null;
		int pos = 0, etapa;

		cambios.add(new Cambio(-1, cambio, 0, 0, null));

		while (pos < cambios.size()) {
			Cambio actual = cambios.get(pos);
			etapa = actual.getEtapa() + 1;

			if (etapa < numMonedas) {
				int max = actual.getRestante() / monedas[etapa];
				for (int n = 0; n <= max; n++) {
					int nuevoRestante = actual.getRestante() - monedas[etapa] * n;
					if (nuevoRestante >= 0) {
						nuevo = new Cambio(etapa, nuevoRestante, n, actual.getNumMonedas() + n, actual);
						if (!cambios.contains(nuevo)) {
							cambios.add(nuevo);
						} else {
							Cambio previo = cambios.get(cambios.indexOf(nuevo));
							if (esMejor(nuevo, previo)) {
								previo.setEtapa(nuevo.getEtapa());
								previo.setPongo(nuevo.getPongo());
								previo.setVieneDe(nuevo.getVieneDe());
							}
						}
					}

				}
			}

			pos++;
		}

		return mejorCambio(cambios);
	}

	private boolean esMejor(Cambio nuevo, Cambio previo) {

		if (nuevo.getRestante() < previo.getRestante()) {
			return true;
		} else if (nuevo.getRestante() == previo.getRestante() && nuevo.getNumMonedas() < previo.getNumMonedas()) {
			return true;
		}

		return false;
	}

	private Cambio mejorCambio(ArrayList<Cambio> cambios) {

		Cambio mejor = cambios.get(0);

		for (int i = 0; i < cambios.size(); i++) {
			if (esMejor(cambios.get(i), mejor)) {
				mejor = cambios.get(i);
			}
		}

		return mejor;
	}
}
