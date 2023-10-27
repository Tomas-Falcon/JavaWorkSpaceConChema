import java.io.RandomAccessFile;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class ClaseMain {
    private RandomAccessFile raf; // Para acceder a archivos de acceso aleatorio
    private FileOutputStream fos; // Para escribir en el archivo "personSecuencial"
    private FileInputStream fis; // Para leer desde el archivo "personSecuencial"

    public static void main(String[] args) {
        ClaseMain arrancar = new ClaseMain();
        arrancar.run();
    }

    private void writePersonRandomAccess(long num, Person person) throws IOException {
        this.raf.seek(num * Person.SIZE); // Posiciona el puntero en el archivo en la ubicación correcta.
        byte[] record = person.toBytes(); // Convierte la persona en un arreglo de bytes.
        this.raf.write(record); // Escribe el arreglo de bytes en el archivo.
    }

    private Person readPersonRandomAccess(long num) throws IOException {
        this.raf.seek(num * Person.SIZE); // Posiciona el puntero en el archivo en la ubicación correcta.
        byte[] record = new byte[Person.SIZE]; // Crea un arreglo para almacenar los datos leídos.
        this.raf.read(record); // Lee los datos desde el archivo.
        return Person.fromBytes(record); // Convierte los datos leídos en una instancia de Persona.
    }

    private void writePersonSequential(Person person) throws IOException {
        byte[] record = person.toBytes(); // Convierte la persona en un arreglo de bytes.
        this.fos.write(record); // Escribe el arreglo de bytes en el archivo.
    }

    private Person readPersonSequential() throws IOException {
        byte[] record = new byte[Person.SIZE]; // Crea un arreglo para almacenar los datos leídos.
        int bytesRead = this.fis.read(record); // Lee los datos desde el archivo.
        if (bytesRead == Person.SIZE) {
            return Person.fromBytes(record); // Convierte los datos leídos en una instancia de Persona.
        } else {
            return null; // Fin del archivo
        }
    }

    public void run() {
        try {
            this.raf = new RandomAccessFile("peopleRandomAccess.dat", "rw"); // Abre un archivo de acceso aleatorio en modo lectura/escritura.
            this.fos = new FileOutputStream("peopleSequential.dat"); // Abre FileOutputStream para escribir en "personSecuencial"
            this.fis = new FileInputStream("peopleSequential.dat"); // Abre FileInputStream para leer desde "personSecuencial"

            Person p1 = new Person(4671, "Juan", 40, true);
            Person p2 = new Person(1819, "Dani", 20, false);
            Person p3 = new Person(7823, "Maria", 18, true);
            Person p4 = new Person(8984, "Diego", 22, false);
            Person p5 = new Person(2984, "Tomas", 21, false);
            Person p6 = new Person(1234, "Manu", 19, false);

            // Almacenar en Ficheros de Acceso Aleatorio
            this.writePersonRandomAccess(0, p1);
            this.writePersonRandomAccess(1, p2);
            this.writePersonRandomAccess(2, p3);
            this.writePersonRandomAccess(3, p4);
            this.writePersonRandomAccess(4, p5);
            this.writePersonRandomAccess(5, p6);

            // Almacenar en Ficheros de Acceso Secuencial
            this.writePersonSequential(p1);
            this.writePersonSequential(p2);
            this.writePersonSequential(p3);
            this.writePersonSequential(p4);
            this.writePersonSequential(p5);
            this.writePersonSequential(p6);

            // Leer desde Ficheros de Acceso Aleatorio
            Person p1Leer = this.readPersonRandomAccess(0);
            Person p2Leer = this.readPersonRandomAccess(1);
            Person p3Leer = this.readPersonRandomAccess(2);
            Person p4Leer = this.readPersonRandomAccess(3);
            Person p5Leer = this.readPersonRandomAccess(4);
            Person p6Leer = this.readPersonRandomAccess(5);

            // Leer desde Ficheros de Acceso Secuencial
            this.fis = new FileInputStream("peopleSequential.dat");
            Person p1LeerSeq = this.readPersonSequential();
            Person p2LeerSeq = this.readPersonSequential();
            Person p3LeerSeq = this.readPersonSequential();
            Person p4LeerSeq = this.readPersonSequential();
            Person p5LeerSeq = this.readPersonSequential();
            Person p6LeerSeq = this.readPersonSequential();

            // Imprimir los objetos leídos
            System.out.println("Ficheros de Acceso Aleatorio:");
            System.out.println("p1 = " + p1Leer);
            System.out.println("p2 = " + p2Leer);
            System.out.println("p3 = " + p3Leer);
            System.out.println("p4 = " + p4Leer);
            System.out.println("p5 = " + p5Leer);
            System.out.println("p6 = " + p6Leer);

            System.out.println("Ficheros de Acceso Secuencial:");
            System.out.println("p1Seq = " + p1LeerSeq);
            System.out.println("p2Seq = " + p2LeerSeq);
            System.out.println("p3Seq = " + p3LeerSeq);
            System.out.println("p4Seq = " + p4LeerSeq);
            System.out.println("p5Seq = " + p5LeerSeq);
            System.out.println("p6Seq = " + p6LeerSeq);

        } catch (Exception e) {
            System.out.println("------ERROR------");
            e.printStackTrace();
        }
    }
}
