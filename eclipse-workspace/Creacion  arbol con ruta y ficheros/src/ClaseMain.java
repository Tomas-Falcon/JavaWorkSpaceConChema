
/**
 * 
 */

import java.io.*;
import java.util.ArrayList;

/**
 * 
 */
public class ClaseMain { 
	/*
	 * Utilizamos java.io para todo el tema de los archivos y la escritura
	 * El java.util solo se utiliza el arraylist
	 * Explicacion del programa, el programa crea los directirios "Colegio, Tema1, Tema2, Curso1, Curso2, Curso3" y
	 * los ficheros .txt llamados "Texto1, Texto2, Texto3, Resumen"
	 * En "Resumen" se guardan los file que estan
	 * Programa echo por Tomas Francisco Falcon en conjunto con Oswaldo Diego Manzano (Diego tiene su version del programa)
	 * si ya existiera algun file de los que se crean, no se crea e imprime un mensaje diciendo "El archivo ya existe:" y
	 * lo mismo con los directorios y si no existe, los crea y te avisa que se crearos
	 * 
	 * Hay un problema a la hora de cargar los datos en resumen, los file que estan adentro de Tema3 no se estan cargando
	 * pero todo lo demas si y no encuentro el error a la hora de escribir
	 */

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		creacionDeFiles();
	}

	public static void creacionDeFiles() {
		File colegio = new File("C:\\Users\\Tomas\\Desktop\\Colegio");
		File curso1 = new File("C:\\Users\\Tomas\\Desktop\\Colegio\\Curso1");
		File curso2 = new File("C:\\Users\\Tomas\\Desktop\\Colegio\\Curso2");
		File tema1 = new File("C:\\Users\\Tomas\\Desktop\\Colegio\\Curso1\\Tema1");
		File tema2 = new File("C:\\Users\\Tomas\\Desktop\\Colegio\\Curso1\\Tema2");
		File tema3 = new File("C:\\Users\\Tomas\\Desktop\\Colegio\\Curso1\\Tema3");
		File texto1 = new File("C:\\Users\\Tomas\\Desktop\\Colegio\\Curso1\\Tema3\\Texto1.txt");
		File texto2 = new File("C:\\Users\\Tomas\\Desktop\\Colegio\\Curso1\\Tema3\\Texto2.txt");
		File texto3 = new File("C:\\Users\\Tomas\\Desktop\\Colegio\\Curso1\\Tema3\\Texto3.txt");
		File texto4 = new File("C:\\Users\\Tomas\\Desktop\\Colegio\\Curso1\\Tema3\\Texto4.txt");
		File resumen = new File("C:\\Users\\Tomas\\Desktop\\Colegio\\Curso2\\Resumen.txt");

		createDirectory(colegio);
		createDirectory(curso1);
		createDirectory(curso2);
		createDirectory(tema1);
		createDirectory(tema2);
		createDirectory(tema3);
		createFiles(texto1);
		createFiles(texto2);
		createFiles(texto3);
		createFiles(texto4);
		createFiles(resumen);
		
		String listadoColegio[] = colegio.list();
		String listadoCurso1[] = curso1.list();
		String listadoCurso2[] = curso2.list();
		String listadoTema1[] = tema1.list();
		String listadoTema2[] = tema2.list();
		String listadoTema3[] = tema3.list();

		escribir(resumen,listadoColegio,listadoCurso1,listadoCurso2,listadoTema1,listadoTema2,listadoTema3);

	}

	public static void escribir(File resumen, String l1 [], String l2 [], String l3 [],String l4 [],String l5 [],String l6 []) {
		ArrayList <String> rutas = new ArrayList <> (1);
		FileWriter fw = null;
		PrintWriter escritor = null;
		try {
			int control = 0;
			int b = 0;
			 fw = new FileWriter(resumen);
			escritor = new PrintWriter(fw);
			String espacio = "\n";
			int i = 0;
			while (i < l1.length){
				String a = l1[i];
				rutas.add(a);
				i++;
			}
			rutas.add(espacio);
			control = i;
			i = 0;
			while (i < l2.length){
				String a = l2[i];
				rutas.add(a);
				i++;
			}
			rutas.add(espacio);
			control = control + i;
			i = 0;
			while (i < l3.length){
				String a = l3[i];
				rutas.add(a);
				i++;
			}
			rutas.add(espacio);
			control = control + i;
			i = 0;
			while (i < l4.length){
				String a = l4[i];
				rutas.add(a);
				i++;
			}
			rutas.add(espacio);
			control = control + i;
			i = 0;
			
			while (i < l5.length){
				String a = l5[i];
				rutas.add(a);
				i++;
			}
			rutas.add(espacio);
			control = control + i;
			i = 0;
			
			while (i < l6.length){
				String a = l6[i];
				rutas.add(a);
				i++;
			}
			rutas.add(espacio);
			control = control + i;
			i = 0;
			while (b < control) {
			escritor.println(rutas.get(b));
			b++;}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			if (fw != null)
			  try {

			    fw.close();

			  } catch (IOException ioe) {
				  
			  }

			}
	}

	private static void createDirectory(File oneFile) {
		if (oneFile.exists() == false) {
			if (oneFile.isDirectory() != true) {
				System.out.println("El Directorio fue creado con Exito: " + oneFile.getName());
				oneFile.mkdir();
			} else {
				try {
					oneFile.createNewFile();
					System.out.println("El archivo fue creado con Exito: " + oneFile.getName());
				}catch (Exception e) {
					}
			}
		} else {
			System.out.println("El archivo ya existe: " + oneFile.getName());
		}
	}
	
	private static void createFiles(File oneFile) {
		if (oneFile.exists() == false) {
			try {
				oneFile.createNewFile();
				System.out.println("El archivo fue creado con Exito: " + oneFile.getName());
			}catch (Exception e) {
				}
		} else {
			System.out.println("El archivo ya existe: " + oneFile.getName());
		}
	}

}
