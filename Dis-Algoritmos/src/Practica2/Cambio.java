package Practica2;

import java.util.ArrayList;

public class Cambio {

	private ArrayList<Moneda> monedas;
	private Cambio vieneDe;

	public Cambio() {
		this.monedas = new ArrayList<Moneda>();
		this.vieneDe = null;
	}

	public Cambio(Cambio vieneDe) {
		this.monedas = new ArrayList<Moneda>();
		this.vieneDe = vieneDe;
	}

	public ArrayList<Moneda> getMonedas() {
		return monedas;
	}

	public void setMonedas(ArrayList<Moneda> monedas) {
		this.monedas = monedas;
	}

	public Cambio getVieneDe() {
		return vieneDe;
	}

	public void setVieneDe(Cambio vieneDe) {
		this.vieneDe = vieneDe;
	}

	public int getNumMonedas() {
		return monedas.size();
	}

	// Obtener valor acumulado
	public int getAcumulado() {
		int acumulado = 0;

		for (int i = 0; i < monedas.size(); i++) {
			acumulado += monedas.get(i).getValor();
		}
		return acumulado;
	}

	@Override
	public String toString() {
		if (vieneDe == null) {
			return "Cambio [monedas=" + monedas + ", vieneDe=null, acumulado=" + getAcumulado() + "]";
		} else {
			return "Cambio [monedas=" + monedas + ", vieneDe=" + vieneDe.getMonedas() + ", acumulado=" + getAcumulado()
					+ "]";
		}
	}

	@Override
	// Dos cambios son iguales si acumulan el mismo valor
	public boolean equals(Object cambio) {
		return (cambio instanceof Cambio) && this.getAcumulado() == ((Cambio) cambio).getAcumulado();
	}

}
