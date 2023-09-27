/**
 * 
 */
import java.util.*;
import java.io.*;
/**
 * 
 */
final class ClaseMain {
	
	private static String nombreSO = System.getProperty("os.name").toLowerCase();
	private static int contador = 1;
	private static ArrayList <String> textos = new ArrayList<String>(1);
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String x = "";
		if (esWindows() == true) {
			x = "Estamos en Windows";
			System.out.println(x);
		}else if (esLinux() == true) {
			x = "Estamos en Linux";
			System.out.println(x);
		}
		
		solicitarTexto();
	}
	

	public static boolean esWindows() {
		return (nombreSO.indexOf("win") >= 0);
	}

	public static boolean esLinux() {
		return (nombreSO.indexOf("nix") >= 0 || nombreSO.indexOf("nux") >= 0 || nombreSO.indexOf("aix") >= 0);
	}
	
	public static void solicitarTexto() {
		System.out.println("Ingrese el texto que quiera guardar:\nPara terminar de escribir, escriba \"exit\".\n");
		texto();
	}
	
	public static void texto() {
		
		while(true) {
			Scanner sc = new Scanner(System.in);
			String texto = sc.nextLine();
			textos.add(texto = comprobarExits(texto));
		}
		
	}

	public static void nombre(){
		System.out.println("Ingrese el nombre del archivo");
		Scanner sc = new Scanner(System.in);
		String nombre = sc.nextLine();
		System.out.println("Diga el formato del archivo como ejemplo \".txt\"");
		String terminacion = sc.nextLine();
		nombre = nombre + terminacion;
		exitOkey(nombre);
	}
	
	public static String comprobarExits(String x) {
			if(x.equalsIgnoreCase("exit")) {
				nombre();
				}else {
					return x;
				}
			String a = "No entra en el IF";
			return a;
	}
	
	public static void exitOkey(String x) {
		String rutaE = System.getProperty("user.home");
		System.out.println("Tenindo en cuenta que la ruta empieza con: \"" + rutaE + "\" ");
		System.out.println("Ingrese la ruta en la que quiere guardar el archivo: ");
		Scanner sc = new Scanner(System.in);
		String ruta = sc.nextLine();
		File ruta1 = new File(rutaE + "\\" + ruta);
		if (ruta1.exists() == false){
			ruta1.mkdir();
		}
		ruta = rutaE + "\\" + ruta + "\\" + x;
		rutaAescribitr(ruta);
	}
	
	private static void rutaAescribitr(String ruta){
		System.out.println("El fichero se va a crear en: " + ruta);
		File ficherin  = new File(ruta);
		if (ficherin.exists() != true){
			try {
				ficherin.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FileWriter fichero =null;
		PrintWriter escritor = null;
		try {
			fichero = new FileWriter(ficherin);
			escritor= new PrintWriter(fichero);
			for(String entreLineas : textos) {
				escritor.println(entreLineas);
			}
 		}catch (IOException ioe) {
 			ioe.printStackTrace();
 		}finally {
 			if (fichero!=null) {
 				try {
 					fichero.close();
 				}catch(IOException ioe) {
 					System.out.println("se produjo un error imprevisto, adios!");
 				}
 			}
 		}
		
	System.out.println("El fichero fue creado con exito!");
	}
	
	

}