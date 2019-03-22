package Practica2;

import java.util.ArrayList;

/**
 * @author Antonio Manjavacas, Ruben Marquez
 *
 *         DEVOLUCION DE CAMBIO
 *
 *         Se tienen k monedas: m1,m2,...,mn. Devolver un cambio C con el menor
 *         numero de monedas posible. En caso de no ser posible, devolver la
 *         mayor cantidad sin pasarse.
 *
 *
 *         (SE ASUME UN NUMERO ILIMITADO DE MONEDAS)
 *
 *         Representar el grafo multietapico empleado y realizar implementacion
 *         backward, forward y matricial.
 *
 **/

public class Practica2 {

	public static void main(String[] args) {

		ArrayList<Moneda> monedas = new ArrayList<Moneda>();

		// Datos de entrada
		int cambio = 13;
		monedas.add(new Moneda(1));
		monedas.add(new Moneda(2));
		monedas.add(new Moneda(4));
		monedas.add(new Moneda(6));

		System.out.println("\nIntroducido cambio = " + cambio + " y monedas (valor, cantidad) = " + monedas + "\n");

		// Version forward
		forward(cambio, monedas);
	}

	private static void forward(int cambio, ArrayList<Moneda> monedas) {

		ArrayList<Cambio> solucion = new ArrayList<Cambio>();
		ArrayList<Cambio> acciones = new ArrayList<Cambio>();
		CambioForward forward = new CambioForward(cambio, monedas);
		
		solucion = forward.getSolucion(cambio, monedas);
		acciones = forward.accionesForward(solucion);

		System.out.println("**************** FORWARD ****************");
		System.out.println("\nSecuencia de acciones forward: ");
		for (int i = 0; i < acciones.size(); i++)
			System.out.println(acciones.get(i));

	}

}
