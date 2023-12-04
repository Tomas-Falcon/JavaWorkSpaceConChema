
public class ClaseMain {
	public static void main(String[] args) {
		//se instancia la clase de conector
		ClaseConector conector = new ClaseConector();
		//se crean los empleados en su clase
		Empleado e1 = new Empleado(0, "Pepe", "Garcia", "AAD2121", 0);
		Empleado e2 = new Empleado(1, "Pepardo", "Gratilde", "aSdj7", 0);
		Empleado e3 = new Empleado(2, "Pepa", "Gaza", "asd3", 1);
		Empleado e4 = new Empleado(3, "Pepardo", "Gratilde", "aSdj7", 0);
		Empleado e5 = new Empleado(4, "Luna", "Tiza", "asd3", 1);
		
		//se instancia y crean loas fabricas
		Fabrica f0 = new Fabrica(0, "Getafe", "+3466334334");
		Fabrica f1 = new Fabrica(1, "Chascomus", "+3466334344");
		Fabrica f2 = new Fabrica(2, "quito", "+4343434343");

		
		//se utiliza la clase conector para accioanr la bbdd 
		conector.crearBBDD();
		conector.insertarFabricas(f0,f1, f2);
		conector.insertarEmpleado(e2,e4,e3,e1,e5);
		conector.mostrarDatosDeEmpleado();
		
		conector.borrar();
		
	}
}
