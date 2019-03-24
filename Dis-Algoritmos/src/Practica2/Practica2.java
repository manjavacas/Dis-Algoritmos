package Practica2;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Antonio Manjavacas, Ruben Marquez
 *
 *         DEVOLUCION DE CAMBIO
 *
 *         Se tienen k monedas: m1,m2,...,mn. Devolver un cambio C con el menor
 *         numero de monedas posible. En caso de no ser posible, devolver la
 *         mayor cantidad sin pasarse.
 *
 **/

public class Practica2 {

	static Scanner leer = new Scanner(System.in);

	public static void main(String[] args) {

		// Datos de entrada
		System.out.println("Introducir cambio a devolver: ");
		int cambio = leer.nextInt();
		ArrayList<Moneda> monedas = introducirMonedas();

		// Datos de prueba
		//	monedas.add(new Moneda(1));
		//	monedas.add(new Moneda(2));
		//	monedas.add(new Moneda(4));
		//	monedas.add(new Moneda(6));

		System.out.println("\nIntroducido cambio = " + cambio + " y monedas = " + monedas + "\n");

		forward(cambio, monedas);
		forwardMatrices(cambio, monedas);
	}

	// Entrada de los valores de las monedas
	private static ArrayList<Moneda> introducirMonedas() {

		ArrayList<Moneda> monedas = new ArrayList<Moneda>();

		System.out.println("Introducir los valores de las monedas separados por comas: ");
		String valores = leer.next();

		String[] tokens = valores.split(",");
		for (int i = 0; i < tokens.length; i++) {
			Moneda m = new Moneda(Integer.parseInt(tokens[i]));
			monedas.add(m);
		}

		return monedas;
	}

	// Version forward
	private static void forward(int cambio, ArrayList<Moneda> monedas) {

		ArrayList<Cambio> solucion = new ArrayList<Cambio>();
		ArrayList<Cambio> acciones = new ArrayList<Cambio>();
		CambioForward forward = new CambioForward(cambio, monedas);

		solucion = forward.getSolucion();
		acciones = forward.accionesForward(solucion);

		System.out.println("\n********* FORWARD **********\nSecuencia de acciones forward:");
		for (int i = 0; i < acciones.size(); i++)
			System.out.println(acciones.get(i));

	}
	
	// Version forward con matrices
	private static void forwardMatrices(int cambio, ArrayList<Moneda> monedas) {
		
		int[] solucion = new int[monedas.size()];
		CambioMatricesForward mforward = new CambioMatricesForward(cambio, monedas);
		
		solucion = mforward.getSolucion();
		System.out.println("\n********* FORWARD MATRICES **********\n");
		for (int i = 0; i < solucion.length; i++)
			System.out.print(solucion[i]);
		
	}

}
