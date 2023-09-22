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
		Scanner sc = new Scanner(System.in);
		String texto1 = sc.nextLine();
		textos.add(texto1);
		contador++;
		comprobarExits();
	}
	
	public static void comprobarExits() {
		for (String x : textos) {
			if(x.indexOf("exit") <= 0 || x.indexOf("Exit") <= 0) {
				exitOkey();
			}
		}
		
	}
	
	public static void exitOkey() {
		System.out.println("Ingrese la ruta en la que quiere guardar el archivo: ");
	}

}
