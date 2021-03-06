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

	// Volumen valor medio
	private double[] intervaloVM;

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

			do {
				x0 = Math.random() * r;
				y0 = Math.random() * r;
			} while (Math.pow(x0, 2) + Math.pow(y0, 2) > Math.pow(r, 2));

			z0 = Math.random() * h;

			z = h - h * Math.sqrt(Math.pow(x0, 2) + Math.pow(y0, 2)) / r;

			if (z0 <= z) {
				dentro++;
			}
		}

		intervaloProp((double) dentro / puntos);
		return ((double) dentro / puntos) * (Math.PI * Math.pow(r, 2) * h);
	}

	private void intervaloProp(double p) {
		double r = cono.getRadio();
		double h = cono.getAltura();
		proporcion = p;
		intervaloP = new double[2];
		intervaloP[0] = (p - ESTADISTICO * Math.sqrt(p * (1 - p) / puntos)) * (Math.PI * Math.pow(r, 2) * h);
		intervaloP[1] = (p + ESTADISTICO * Math.sqrt(p * (1 - p) / puntos)) * (Math.PI * Math.pow(r, 2) * h);
	}

	public double[] getIntervaloP() {
		return this.intervaloP;
	}

	public double getProporcion() {
		return this.proporcion;
	}

	public double calcularValorMedio() {

		double[] valores = new double[puntos];
		double suma = 0.0;
		double r = cono.getRadio();
		double h = cono.getAltura();
		double x, y, z;

		for (int i = 0; i < puntos; i++) {

			do {
				x = Math.random() * r;
				y = Math.random() * r;
			} while (Math.pow(x, 2) + Math.pow(y, 2) > Math.pow(r, 2));

			z = h - h * Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)) / r;

			valores[i] = Math.PI * Math.pow(r, 2) * z; // Volumen del cilindro
			suma += valores[i];
		}

		intervaloValorMedio(valores);

		return suma / puntos;
	}

	public void intervaloValorMedio(double[] valores) {
		double media = media(valores);
		double cuasiVar = cuasiVar(valores, media);
		intervaloVM = new double[2];
		intervaloVM[0] = media - ESTADISTICO * cuasiVar / Math.sqrt(valores.length);
		intervaloVM[1] = media + ESTADISTICO * cuasiVar / Math.sqrt(valores.length);
	}

	public double media(double valores[]) {
		double media = 0;
		for (int i = 0; i < valores.length; i++) {
			media = media + valores[i];
		}
		return media / valores.length;
	}

	public double cuasiVar(double[] valores, double media) {
		double s = 0;
		for (int i = 0; i < valores.length; i++) {
			s = s + Math.pow(valores[i] - media, 2);
		}
		return Math.sqrt(s / (valores.length - 1));
	}

	public double[] getIntervaloVM() {
		return this.intervaloVM;
	}

	public double calcularReal() {
		return Math.PI * Math.pow(cono.getRadio(), 2) * cono.getAltura() / 3;
	}

}
