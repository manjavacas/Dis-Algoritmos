package Practica2;

public class Moneda {

	private int valor;

	public Moneda(int valor) {
		this.valor = valor;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "(" + valor + ")";
	}

}
