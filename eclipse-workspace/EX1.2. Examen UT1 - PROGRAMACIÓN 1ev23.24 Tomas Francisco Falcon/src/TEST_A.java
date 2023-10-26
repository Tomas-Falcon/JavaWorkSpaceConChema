import java.io.IOException;

public class TEST_A {

	public static void main(String[] args) throws IOException {
		long milis_inicio = System.currentTimeMillis();
		int numA = Integer.parseInt(args[0]);
		int numB = Integer.parseInt(args[1]);
		
		String comando = "Multiplicar"; 
		
		ProcessBuilder pa = new ProcessBuilder("java", comando, String.valueOf(numA)); //hacemos los ProcessBuilder
		ProcessBuilder pb = new ProcessBuilder("java", comando, String.valueOf(numB));
		
		
		String arg [] = {String.valueOf(numA)};
		TABLAMULTIPLICAR.main(arg); 
		

		String arg2 [] = {String.valueOf(numB)};
		TABLAMULTIPLICAR.main(arg2);
		
		Process p1 = pa.start();
		Process p2 = pb.start();
		
		long milis_fin= System.currentTimeMillis();
		 float tiempo_ejec= (milis_fin-milis_inicio)/1000;
		 System.out.printf("El tiempo de ejecución es: %.2f segundos en el proceso de TEST_A",tiempo_ejec );

	}

}

