package Practica3;

/**
 * Calcular el volumen de un cono mediante algoritmos probabilistas
 * 
 * @author Antonio.Manjavacas, Ruben.Marquez
 *
 */

public class CalcularVolumen {

	public static void main(String[] args) {

		double radio = Leer.getDouble("Introducir radio de la base: ");
		double altura = Leer.getDouble("Introducir altura del cono: ");

		Cono cono = new Cono(radio, altura);
		calcularVolumen(cono);
	}

	private static void calcularVolumen(Cono cono) {
		int puntos = Leer.entero("Introducir cantidad de puntos: ");
		VolumenCono vc = new VolumenCono(cono, puntos);
		System.out.println("\t* Volumen por proporciones = " + vc.calcularProporciones() + "\n\t\t - Proporcion = "
				+ vc.getProporcion() + "\n\t\t - Intervalo = [" + vc.getIntervaloP()[0] + ", " + vc.getIntervaloP()[1]
				+ "]\n\t* Volumen por proporciones = " + vc.calcularValorMedio() + "\n\t\t - Intervalo = ["
				+ vc.getIntervaloVM()[0] + ", " + vc.getIntervaloVM()[1] + "]\n\t* Volumen real = "
				+ vc.calcularReal());
	}

}
