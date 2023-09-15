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
		File texto1 = new File("C:\\Users\\Tomas\\Desktop\\Colegio\\Curso1\\Texto1.txt");
		File texto2 = new File("C:\\Users\\Tomas\\Desktop\\Colegio\\Curso1\\Texto2.txt");
		File texto3 = new File("C:\\Users\\Tomas\\Desktop\\Colegio\\Curso1\\Texto3.txt");
		File texto4 = new File("C:\\Users\\Tomas\\Desktop\\Colegio\\Curso1\\Texto4.txt");
		File resumen = new File("C:\\Users\\Tomas\\Desktop\\Colegio\\Curso1\\Tema2\\Resumen.txt");
		
		directorCrear(colegio);
		directorCrear(curso1);
		directorCrear(curso2);
		directorCrear(tema1);
		directorCrear(tema2);
		directorCrear(tema3);
		directorCrear(texto1);
		directorCrear(texto2);
		directorCrear(texto3);
		directorCrear(texto4);
		directorCrear(resumen);
		
				
			
	}
	
	private static void directorCrear(File chequeo) {
		if (siExiste(chequeo) == false && chequeo.isDirectory() == true){
			chequeo.mkdir();
			System.out.println("El directorio no existe, se creara y se llamara: "+ chequeo.getName());
			}else if (chequeo.isDirectory() == true) {
				System.out.println("El directorio ya existia y se llama: "+ chequeo.getName());
			}
		if (siExiste(chequeo) == false && chequeo.isFile() == true){
				try {
						System.out.println("El fichero no existe, se creara y se llamara: "+ chequeo.getName());
						chequeo.createNewFile();
					}
				 catch (IOException e) {
					e.printStackTrace();
					System.out.println("No se pudo crear el fichero debido al error \""+e+"\"");
				}
		}else if (chequeo.isFile() == true) {
				System.out.println("El fichero ya existia y se llama: "+ chequeo.getName());
			}
	}
	
	public static boolean siExiste(File chequeo) {
		return chequeo.exists();
	}
	
}
