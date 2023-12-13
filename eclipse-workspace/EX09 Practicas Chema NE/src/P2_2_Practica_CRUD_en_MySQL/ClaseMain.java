package P2_2_Practica_CRUD_en_MySQL;

public class ClaseMain {

	public static void main(String[] args) {
		Conection conn = new Conection("basesin");
		conn.escribir("Manu", "Melgar", 19, "Rap ingles", "tablin");
	}

}
