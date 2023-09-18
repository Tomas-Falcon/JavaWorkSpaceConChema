
/**
 * 
 */

import java.io.*;

/**
 * 
 */
public class ClaseMain {

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
		FileWriter fw = null;
		PrintWriter escritor = null;
		try {
			int control = 0;
			int b = 0;
			 fw = new FileWriter(resumen);
			escritor = new PrintWriter(fw);
			String resumennStr [];
			int i = 0;
			while (i < l1.length){
				String a = l1[i];
				i++;
			}
			control = i;
			i = 0;
			while (i < l2.length){
				String a = l2[i];
				i++;
			}
			control = control + i;
			i = 0;
			while (i < l3.length){
				String a = l3[i];
				i++;
			}
			control = control + i;
			i = 0;
			while (i < l4.length){
				String a = l4[i];
				i++;
			}
			control = control + i;
			i = 0;
			
			while (i < l5.length){
				String a = l5[i];
				i++;
			}
			control = control + i;
			i = 0;
			
			while (i < l6.length){
				String a = l6[i];
				i++;
			}
			control = control + i;
			i = 0;
			while (b < control) {
			escritor.println(resumennStr [b]);
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
