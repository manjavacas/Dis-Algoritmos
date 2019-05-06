package Practica4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Procesar porcentaje de texto
 *
 * @author Ruben.Marquez, Antonio.Manjavacas
 *
 */

public class ProcesadorTexto {

	private File f;
	private int porcentaje;
	private BufferedReader br;

	public ProcesadorTexto(File f, int porcentaje) {
		this.f = f;
		this.porcentaje = porcentaje;
	}

	// Extrae un porcentaje de lineas de un fichero y las devuelve en una cadena
	public String procesar() {
		try {
			String texto = "";
			FileReader fr = new FileReader(f);
			br = new BufferedReader(fr);
			int lineas = (int) (1 / ((double) porcentaje / 100));
			int cont = 0;

			String linea;
			while ((linea = br.readLine()) != null) {
				if ((cont % lineas) == 0)
					texto += linea;
				cont++;
			}

			return texto;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
