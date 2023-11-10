import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;



public class ClaseMain {

	public static void main(String[] args) {
		ASIGNATURA aceso_datos = new ASIGNATURA("Aceso a datos","Chema", "Nuñes", "chemaNuñes@sasr.com", 4); //se crean los objetos de asignaturas
		ASIGNATURA iniciativa_emprendedora = new ASIGNATURA("Iniciativa emprendedora","Alberto", "doe", "alberto@sasr.com", 2);
		ASIGNATURA interfaz = new ASIGNATURA("Interfaz","Alejandro", "sr.Corbata", "alejandro@sasr.com", 4);
		
		ASIGNATURA asignaturas [] = {aceso_datos,interfaz,iniciativa_emprendedora}; //array de ASIGNATURAS
		
		escribir(asignaturas);//se pasa el arrar de asignaturas
		leer(asignaturas);
	}
	
	public static void escribir(ASIGNATURA asignaturas []) {
		ObjectOutputStream salida = null;
		
		try {
			salida = new ObjectOutputStream(new FileOutputStream("asignaturas.txt"));
		
			for(ASIGNATURA asignatura : asignaturas) { //cada valor de las asignaturas se almacena en asignatura
				try {
					//salida.writeBytes("Objeto ASIGNATURA, el objeto no se va a ver pero abajo tambien se escriben los campos de ese objeto (Con sus campos)\n");
					salida.writeObject(asignatura);//se escribe el objeto como tal, aunque no hace falta ponerlo, lo dejo comentado
					salida.writeObject("\n-"+asignatura.getNombreAsignatura()+"\n");
					salida.writeObject("-"+asignatura.getNombreProfesor()+"\n");
					salida.writeObject("-"+asignatura.getApellidoProfesor()+"\n");
					salida.writeObject("-"+asignatura.getEmailProfesor()+"\n");
					salida.writeObject("-"+asignatura.getNumeroHorasSemana()+" horas semanales \n");
					salida.writeObject("\n#-----------------------------------------------------------------#\n");	
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			salida.close();//se deja de escribir
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
	}
	public static void leer(ASIGNATURA asignaturas []) {//se imprimen todas las asignaturas (se calcula con el for each en base a la cantidad de asignaturas original a la hora de escribir)
		ObjectInputStream entrada;
		try {
			entrada = new ObjectInputStream(new FileInputStream("asignaturas.txt"));
			for(ASIGNATURA asignatura : asignaturas) {
			
			ASIGNATURA obj0 = (ASIGNATURA) entrada.readObject();
			String obj1 = (String) entrada.readObject();
			String obj2 = (String) entrada.readObject();
			String obj3 = (String) entrada.readObject();
			String obj4 = (String) entrada.readObject();
			String obj5 = (String) entrada.readObject();
			String obj6 = (String) entrada.readObject();
			
			System.out.println(obj0+"\n"+obj1+"\n"+obj2+"\n"+obj3+"\n"+obj4+"\n"+obj5+"\n"+obj6+"\n");
			}
			entrada.close();
		}  catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
