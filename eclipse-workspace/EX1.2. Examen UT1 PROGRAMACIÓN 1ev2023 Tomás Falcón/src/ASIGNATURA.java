
public class ASIGNATURA implements java.io.Serializable{ //se implementa que sea serializable para que a la hora de escribir el objeto como tal, sino no se puede escribir un objeto como serializable, tambien da problemas si se quiere escribir el objeto en si
	private String nombreAsignatura, nombreProfesor, apellidoProfesor, emailProfesor, numeroHorasSemanaStr;
	ASIGNATURA(String nombreAsignatura, String nombreProfesor, String apellidoProfesor, String emailProfesor, float numeroHorasSemana ){
		this.nombreAsignatura = nombreAsignatura;
		this.nombreProfesor = nombreProfesor;
		this.apellidoProfesor = apellidoProfesor;
		this.emailProfesor = emailProfesor;
		String numeroHorasSemanaStr = String.valueOf(numeroHorasSemana);
		this.numeroHorasSemanaStr = numeroHorasSemanaStr;
	}
	/**
	 * @return the nombreAsignatura
	 */
	public String getNombreAsignatura() {
		return nombreAsignatura;
	}
	/**
	 * @return the nombreProfesor
	 */
	public String getNombreProfesor() {
		return nombreProfesor;
	}
	/**
	 * @return the apellidoProfesor
	 */
	public String getApellidoProfesor() {
		return apellidoProfesor;
	}
	/**
	 * @return the emailProfesor
	 */
	public String getEmailProfesor() {
		return emailProfesor;
	}
	/**
	 * @return the numeroHorasSemana
	 */
	public String getNumeroHorasSemana() {
		return numeroHorasSemanaStr;
	}
}
