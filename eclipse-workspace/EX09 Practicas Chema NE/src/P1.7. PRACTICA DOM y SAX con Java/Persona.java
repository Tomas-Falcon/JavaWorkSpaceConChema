import java.io.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class Persona implements Serializable {
    private String nombre;
    private int edad;

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public static void main(String[] args) {
        // Crear objetos de Persona y almacenarlos en un archivo
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("FichPersona.dat"));
            oos.writeObject(new Persona("Pedro", 53));
            oos.writeObject(new Persona("Juan", 26));
            oos.writeObject(new Persona("Maria", 32));
            oos.writeObject(new Persona("Laura", 19));
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Crear un documento XML usando DOM
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element root = document.createElement("personas");
            document.appendChild(root);

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("FichPersona.dat"));
            Persona persona;

            while (true) {
                try {
                    persona = (Persona) ois.readObject();
                    Element personaElement = document.createElement("persona");
                    root.appendChild(personaElement);
                    crearElemento("nombre", persona.getNombre(), personaElement, document);
                    crearElemento("edad", String.valueOf(persona.getEdad()), personaElement, document);
                } catch (EOFException e) {
                    break;
                }
            }

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(new DOMSource(document), new StreamResult(new File("personas.xml")));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Leer el documento XML creado
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File("personas.xml"));
            document.getDocumentElement().normalize();
            NodeList personas = document.getElementsByTagName("persona");

            for (int i = 0; i < personas.getLength(); i++) {
                Node personaNode = personas.item(i);
                if (personaNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element personaElement = (Element) personaNode;
                    System.out.println("Nombre: " + getNodo("nombre", personaElement));
                    System.out.println("Edad: " + getNodo("edad", personaElement));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void crearElemento(String datoEmple, String valor, Element raiz, Document document) {
        Element elem = document.createElement(datoEmple);
        Text text = document.createTextNode(valor);
        raiz.appendChild(elem);
        elem.appendChild(text);
    }

    private static String getNodo(String etiqueta, Element elem) {
        NodeList nodo = elem.getElementsByTagName(etiqueta).item(0).getChildNodes();
        Node valorNodo = nodo.item(0);
        return valorNodo.getNodeValue();
    }
}
