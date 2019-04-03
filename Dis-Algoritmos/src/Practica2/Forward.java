package Practica2;

/*
 * Clase que lleva a cabo la resolucion del cambio de monedas mediante programacion dinamica con una lista mediante el metodo forward
 */
import java.util.ArrayList;

public class Forward {

	private int cambio; //Cambio a devolver
	private int numMonedas; //Numero de monedas que podemos utilizar
	private int[] monedas; //Vector de monedas, con el valor de las monedas que podemos utilizar

	//Constructor
	public Forward(int cambio, int[] monedas) {
		this.cambio = cambio;
		this.monedas = monedas;
		this.numMonedas = monedas.length;
	}

	//Metodo que devuelve un objeto cambio con el cambio mas obtimo conseguido en la ultima etapa
	public Cambio resolver() {

		ArrayList<Cambio> cambios = new ArrayList<Cambio>();
		Cambio nuevo = null;
		int pos = 0, etapa;

		//Añado el primer cambio, el cual tendra todos los valores inicializados a 0, la etapa a 1 y el cambio a devolver como el cambio restante
		cambios.add(new Cambio(-1, cambio, 0, 0, 0, null));

		//Se lleva a cabo el algoritmo hasta que hayamos recorrido todos los elementos de la lista
		while (pos < cambios.size()) {
			Cambio actual = cambios.get(pos); //Guardamos el objeto cambio que vamos a expandir
			etapa = actual.getEtapa() + 1; //Actualizamos la etapa a la siguiente

			//Mientras nos queden monedas por comprobar seguimos
			if (etapa < numMonedas) {
				int max = actual.getRestante() / monedas[etapa]; //Obtiene el maximo de monedas puedo meter de la moneda actual con el cambio restante actual
				for (int n = 0; n <= max; n++) { //Comprobamos todas las combinaciones de monedas a meter
					int nuevoRestante = actual.getRestante() - monedas[etapa] * n; //Obtenemos el nuevo cambio restante
					if (nuevoRestante >= 0) {
						//Obtenemos los valores del nuevo cambio generado si metemos tantas monedas de dicha moneda
						nuevo = new Cambio(etapa, nuevoRestante, n, actual.getNumMonedas() + n,
								actual.getValor() + monedas[etapa] * n, actual);
						//Si el cambio generado aun no se ha generado se introduce en la lista
						if (!cambios.contains(nuevo)) {
							cambios.add(nuevo);
						} else { //Si ya se ha generado se comprueba si es mejor que el cambio que habia antes y se actualiza en caso de serlo
							Cambio previo = cambios.get(cambios.indexOf(nuevo));
							if (esMejor(nuevo, previo)) {
								previo.setRestante(nuevo.getRestante());
								previo.setValor(nuevo.getValor());
								previo.setPongo(nuevo.getPongo());
								previo.setVieneDe(nuevo.getVieneDe());
							}
						}
					}

				}
			}

			pos++;
		}

		//Obtiene el mejor cambio de la lista posible en la ultima etapa
		return mejorCambio(cambios);
	}

	//Metodo que comprueba si un nuevo cambio es mejor que su anterior
	private boolean esMejor(Cambio nuevo, Cambio previo) {

		if (nuevo.getValor() > previo.getValor()) { //Es mejor si el valor de cambio acumulado es mayor que el anterior
			return true;
		}
		//Es mejor si los valores acumulados son iguales pero el numero de monedas que utilizamos es menor
		else if (nuevo.getValor() == previo.getValor() && nuevo.getNumMonedas() < previo.getNumMonedas()) {
			return true;
		}
		return false;
	}

	//Metodo que devuelve el mejor cambio en la ultima etapa de la lista de cambios
	private Cambio mejorCambio(ArrayList<Cambio> cambios) {

		Cambio mejor = cambios.get(0);

		for (int i = 0; i < cambios.size(); i++) {
			//Si es mejor cambio y se encuentra en la ultima etapa
			if (esMejor(cambios.get(i), mejor) && cambios.get(i).getEtapa() == monedas.length - 1) {
				mejor = cambios.get(i);
			}
		}

		return mejor;
	}
}
