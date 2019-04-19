package Practica3;

/**
 * Calcula volumen cono por valor medio y proporciones
 * 
 * @author Antonio.Manjavacas, Ruben.Marquez
 *
 */

public class VolumenCono {

	private static final double ESTADISTICO = 1.96;

	private Cono cono;
	private int puntos;

	// Volumen proporciones
	private double proporcion;
	private double[] intervaloP;

	public VolumenCono(Cono cono, int puntos) {
		this.cono = cono;
		this.puntos = puntos;
	}

	public double calcularProporciones() {
		double h = cono.getAltura();
		double r = cono.getRadio();
		double x0, y0, z0, z;
		int dentro = 0;

		for (int i = 0; i < puntos; i++) {

			x0 = Math.random() * r;
			y0 = Math.random() * Math.sqrt(Math.pow(r, 2) - Math.pow(x0, 2));
			z0 = Math.random() * h;

			z = h - h * Math.sqrt(Math.pow(x0, 2) + Math.pow(y0, 2)) / r;

			if (z0 <= z) {
				dentro++;
			}
		}

		intervaloProp((double) dentro / puntos);
		return (double) dentro / puntos * (Math.PI * Math.pow(r, 2) * h);
	}

	private void intervaloProp(double p) {
		proporcion = p;
		intervaloP = new double[2];
		intervaloP[0] = p - ESTADISTICO * Math.sqrt(p * (1 - p) / puntos);
		intervaloP[1] = p + ESTADISTICO * Math.sqrt(p * (1 - p) / puntos);
	}

	public double[] getIntervaloP() {
		return this.intervaloP;
	}

	public double getProporcion() {
		return this.proporcion;
	}

	public double calcularValorMedio() {
		
		// xxxxxxx

		return 0;
	}

	public double calcularReal() {
		return Math.PI * Math.pow(cono.getRadio(), 2) * cono.getAltura() / 3;
	}

}
