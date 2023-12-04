
public class Empleado {
	
	private int id, idFabrica;
	private  String nombre, apellido, CodigoEmpleado;
	//la clase empleado
	public Empleado(int id, String nombre, String apellido, String CodigoEmpleado, int idFabrica) {
		this.id = id;
		this.idFabrica = idFabrica;
		this.nombre = nombre;
		this.apellido = apellido;
		this.CodigoEmpleado = CodigoEmpleado;
	
	}

	/**
	 * @return the id
	 */
	public int getID() {
		return id;
	}

	/**
	 * @return the idFabrica
	 */
	public int getIdFabrica() {
		return idFabrica;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * @return the codigoEmpleado
	 */
	public String getCodigoEmpleado() {
		return CodigoEmpleado;
	}
}
