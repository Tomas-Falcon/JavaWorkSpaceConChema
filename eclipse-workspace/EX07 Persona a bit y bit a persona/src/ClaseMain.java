import java.io.RandomAccessFile;
import java.io.IOException;

public class ClaseMain {
	// para acceder a los espacios de memoria
	private RandomAccessFile raf;

	public static void main(String[] args) {
		ClaseMain arrancar = new ClaseMain();
		arrancar.run();
	}

	private void writePerson(long num, Person person) throws IOException {
		this.raf.seek(num * Person.SIZE);
		byte[] record = person.toBytes();
		this.raf.write(record);
	}

	private Person readPerson(long num) throws IOException {
		this.raf.seek(num * Person.SIZE);
		byte[] record = new byte[Person.SIZE];
		this.raf.read(record);
		return Person.fromBytes(record);
	}

	public void run() {
		try {
			this.raf = new RandomAccessFile("people.dat", "rw");
			Person p1 = new Person(4671, "Juan", 40, true);
			Person p2 = new Person(1819, "Dani", 20, false);
			Person p3 = new Person(7823, "Maria", 18, true);
			Person p4 = new Person(8984, "Diego", 22, false);
			Person p5 = new Person(2984, "Tomas", 21, false);
			Person p6 = new Person(1234, "Manu", 19, false);
			
			this.writePerson(0, p1);
			this.writePerson(1, p2);
			this.writePerson(2, p3);
			this.writePerson(3, p4);
			this.writePerson(4, p5);
			this.writePerson(5, p6);
			
			Person p;
			
			p = this.readPerson(0);
			System.out.println("p = " + p);
			
			p = this.readPerson(1);
			System.out.println("p = " + p);
			
			p = this.readPerson(2);
			System.out.println("p = " + p);
			
			p = this.readPerson(3);
			System.out.println("p = " + p);
			
			p = this.readPerson(4);
			System.out.println("p = " + p);
			
			p = this.readPerson(5);
			System.out.println("p = " + p);
			
		} catch (Exception e) {
			System.out.println("------ERROR------");
		}
	}

}