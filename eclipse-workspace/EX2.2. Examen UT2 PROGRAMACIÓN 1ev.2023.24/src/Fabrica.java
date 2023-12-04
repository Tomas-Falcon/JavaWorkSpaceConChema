
public class Fabrica {
	
	private int id;
	private String localidad, telefono;
	//la clase de la fabrica
	public Fabrica(int id, String localidad, String telefono) {
	this.id = id;
	this.localidad = localidad;
	this.telefono  = telefono;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the localidad
	 */
	public String getLocalidad() {
		return localidad;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}
}
