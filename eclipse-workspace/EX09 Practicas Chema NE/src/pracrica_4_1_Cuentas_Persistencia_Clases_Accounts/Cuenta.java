package pracrica_4_1_Cuentas_Persistencia_Clases_Accounts;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Example Cuenta object that is persisted to disk by the DAO and other example classes.
 */
@DatabaseTable(tableName = "Cuentas")
public class Cuenta {

	// for QueryBuilder to be able to find the fields
	public static final String NAME_FIELD_NAME = "name";
	public static final String PASSWORD_FIELD_NAME = "passwd";

	@DatabaseField(generatedId = true)
	private int id;

	@DatabaseField(columnName = NAME_FIELD_NAME, canBeNull = false)
	private String name;

	@DatabaseField(columnName = PASSWORD_FIELD_NAME)
	private String password;

	Cuenta() {
		// all persisted classes must define a no-arg constructor with at least package visibility
	}

	public Cuenta(String name) {
		this.name = name;
	}

	public Cuenta(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == null || other.getClass() != getClass()) {
			return false;
		}
		return name.equals(((Cuenta) other).name);
	}
}