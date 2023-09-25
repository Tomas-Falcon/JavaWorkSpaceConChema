/**
 * 
 */
import java.util.*;
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
		nombre = nombre + ".txt";
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
		String ruta = System.getProperty("user.home");
		System.out.println("Tenindo en cuenta que la ruta empieza con: " + ruta);
		System.out.println("Ingrese la ruta en la que quiere guardar el archivo: ");
		Scanner sc = new Scanner(System.in);
		String texto = sc.nextLine();
		texto = ruta +texto + x;
		System.out.println(texto);
		rutaAescribitr(texto);
	}
	
	public static void rutaAescribitr(String ruta){
		if (esWindows() == true) {
			windowsRuta(ruta);
		}else if (esLinux() == true) {
			linuxRuta(ruta);
		}
	}
	
	public static void linuxRuta(String ruta){
		
	}
	
	public static void windowsRuta(String ruta){
		
	}
	

}