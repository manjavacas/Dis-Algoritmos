package Practica2;

/*
 * Clase Cambio que se utiliza para crear instancias de dicha clase, la cual se utilizara para representar un elemento de la lista
 * y en la cual se almacenará la etapa en la cual nos encontramos (monedaActual de la cual estamos comprobando cuantas podemos meter),
 * el cambio restante (cambio que nos queda por devolver con las monedas que llevamos), el numero de monedas que pongo de la moneda actual,
 * el numero de monedas que llevo acumuladas, el valor del cambio acumulado que llevamos actualmente y una referencia a un objeto Cambio del cual vengo
 */
public class Cambio {

	private int etapa;
	private int restante;
	private int pongo;
	private int numMonedas;
	private int valor;
	private Cambio vieneDe;

	//Constructor
	public Cambio(int etapa, int restante, int pongo, int numMonedas, int valor, Cambio vieneDe) {
		this.etapa = etapa;
		this.restante = restante;
		this.pongo = pongo;
		this.numMonedas = numMonedas;
		this.valor = valor;
		this.vieneDe = vieneDe;
	}

	//Getters y setters
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

	/*
	 * Metodo equals, dos cambios representaran el mismo cambio si la etapa en la cual nos encontramos, el cambio restante y el numero de
	 * monedas actuales que pongo en el momento actual son iguales
	 */
	@Override
	public boolean equals(Object c) {
		return c instanceof Cambio && getEtapa() == ((Cambio) c).getEtapa()
				&& getRestante() == ((Cambio) c).getRestante() && getPongo() == ((Cambio) c).getPongo();
	}

	//Metodo que devuelve una cadena del objeto
	@Override
	public String toString() {
		return "Cambio [etapa=" + etapa + ", restante=" + restante + ", pongo=" + pongo + ", numMonedas=" + numMonedas
				+ ", vieneDe=" + vieneDe + "]";
	}

}