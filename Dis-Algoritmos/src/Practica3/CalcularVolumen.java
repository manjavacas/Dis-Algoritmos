package Practica3;

/**
 * Calcular el volumen de un CONO mediante algoritmos probabilistas
 * 
 * @author Antonio.Manjavacas, Ruben.Marquez
 *
 */

public class CalcularVolumen {

	public void main(String[] args) {

		int radio = Leer.entero("Introducir radio de la base: ");
		int altura = Leer.entero("Introducir altura del cono: ");

		volumenValorMedio(radio, altura);
		volumenProporciones(radio, altura);
		volumenReal(radio, altura);

	}

	private void volumenValorMedio(int radio, int altura) {
		ValorMedio vm = new ValorMedio(radio, altura);
		vm.calcular();
	}

	private void volumenProporciones(int radio, int altura) {
		VolProporciones vp = new VolProporciones(radio, altura);
		vp.calcular();
	}

	private void volumenReal(int radio, int altura) {
		VolumenReal vr = new VolumenReal(radio, altura);
		vr.calcular();
	}

}
