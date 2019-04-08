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
		Cono cono = new Cono(radio, altura);
		volumenValorMedio(cono);
		volumenProporciones(cono);
		volumenReal(cono);

	}

	private void volumenValorMedio(Cono cono) {
		ValorMedio vm = new ValorMedio(cono);
		vm.calcular();
	}

	private void volumenProporciones(Cono cono) {
		VolProporciones vp = new VolProporciones(cono);
		vp.calcular();
	}

	private void volumenReal(Cono cono) {
		VolumenReal vr = new VolumenReal(cono);
		vr.calcular();
	}

}
