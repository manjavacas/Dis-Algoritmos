package Practica2;

public class Cambio {

	private int etapa;
	private int restante;
	private int pongo;
	private int numMonedas;
	private int valor;
	private Cambio vieneDe;

	public Cambio(int etapa) {
		this.etapa = etapa;
		this.restante = 0;
		this.pongo = 0;
		this.numMonedas = 0;
		this.vieneDe = null;
	}

	public Cambio(int etapa, int restante, int pongo, int numMonedas, Cambio vieneDe) {
		this.etapa = etapa;
		this.restante = restante;
		this.pongo = pongo;
		this.numMonedas = numMonedas;
		this.vieneDe = vieneDe;
	}

	public Cambio(int etapa, int restante, int pongo, int numMonedas, int valor, Cambio vieneDe) {
		this.etapa = etapa;
		this.restante = restante;
		this.pongo = pongo;
		this.numMonedas = numMonedas;
		this.valor = valor;
		this.vieneDe = vieneDe;
	}

	public int getEtapa() {
		return etapa;
	}

	public void setEtapa(int etapa) {
		this.etapa = etapa;
	}

	public int getRestante() {
		return restante;
	}

	public void setRestante(int restante) {
		this.restante = restante;
	}

	public int getPongo() {
		return pongo;
	}

	public void setPongo(int pongo) {
		this.pongo = pongo;
	}

	public int getNumMonedas() {
		return numMonedas;
	}

	public void setNumMonedas(int numMonedas) {
		this.numMonedas = numMonedas;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public Cambio getVieneDe() {
		return vieneDe;
	}

	public void setVieneDe(Cambio vieneDe) {
		this.vieneDe = vieneDe;
	}

	@Override
	public boolean equals(Object c) {
		return c instanceof Cambio && getEtapa() == ((Cambio) c).getEtapa()
				&& getRestante() == ((Cambio) c).getRestante() && getPongo() == ((Cambio) c).getPongo();
	}

	@Override
	public String toString() {
		return "Cambio [etapa=" + etapa + ", restante=" + restante + ", pongo=" + pongo + ", numMonedas=" + numMonedas
				+ ", vieneDe=" + vieneDe + "]";
	}



}