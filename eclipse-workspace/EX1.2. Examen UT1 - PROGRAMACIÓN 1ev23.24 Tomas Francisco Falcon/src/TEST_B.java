import java.io.IOException;

public class TEST_B {

	public static void main(String[] args) throws IOException, InterruptedException {
		int numA = Integer.parseInt(args[0]);
		int numB = Integer.parseInt(args[1]);
		String numAStr = numA + "";
		String numBStr = numB + "";
		String comando = "Multiplicar"; 
		
		ProcessBuilder pa = new ProcessBuilder("java", comando, numAStr);
		
		
		String arg [] = {numAStr};
		TABLAMULTIPLICAR.main(arg);
		
		ProcessBuilder pb = new ProcessBuilder("java", comando, numBStr);
		
		Process p1 = pa.start();
		System.out.println("Si el valor es 1, significa que el proceso termino "+ p1.waitFor());
		
		String arg2 [] = {numBStr};
		TABLAMULTIPLICAR.main(arg2);
		
			
		
		Process p2 = pb.start();
		
	
		p2.waitFor();
		
		

	}

}
