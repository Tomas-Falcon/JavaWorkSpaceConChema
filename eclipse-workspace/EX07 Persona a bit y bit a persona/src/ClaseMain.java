import java.io.RandomAccessFile;
import java.io.IOException;

public class ClaseMain {
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
			Person p1 = new Person(4671, "Juan", 40, false);
			Person p2 = new Person(1819, "Pedro", 63, true);
			Person p3 = new Person(7823, "Maria", 18, false);
			Person p4 = new Person(8984, "Susi", 24, true);
			this.writePerson(0, p1);
			this.writePerson(1, p2);
			this.writePerson(4, p3);
			Person p;
			p = this.readPerson(0);
			System.out.println("p = " + p);
			p = this.readPerson(1);
			System.out.println("p = " + p);
			p = this.readPerson(4);
			System.out.println("p = " + p);
			this.writePerson(3, p4);
			p = this.readPerson(3);
			System.out.println("p = " + p);
			this.writePerson(1, p1);
			p = this.readPerson(1);
			System.out.println("p = " + p);
		} catch (Exception e) {
			System.out.println("------ERROR------");
		}
	}
}