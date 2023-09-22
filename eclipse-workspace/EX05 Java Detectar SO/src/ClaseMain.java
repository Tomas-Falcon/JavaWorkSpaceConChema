/**
 * 
 */

/**
 * 
 */
final class ClaseMain {
	
	private static String nombreSO = System.getProperty("os.name").toLowerCase();
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

	}
	

	public static boolean esWindows() {
		return (nombreSO.indexOf("win") >= 0);
	}

	public static boolean esLinux() {
		return (nombreSO.indexOf("nix") >= 0 || nombreSO.indexOf("nux") >= 0 || nombreSO.indexOf("aix") >= 0);
	}

}
