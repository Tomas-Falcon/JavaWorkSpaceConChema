package P1_7_PraxticaDomYSaxConJava;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;
import org.xml.sax.Attributes;
import java.io.IOException;

@SuppressWarnings("deprecation")
public class Main {
    public static void main(String[] args) throws SAXException, IOException {
        XMLReader lector = XMLReaderFactory.createXMLReader();
        GestionContenido gestor = new GestionContenido();
        lector.setContentHandler(gestor);
        InputSource ficheroXML = new InputSource("PELICULAS.xml");
        lector.parse(ficheroXML);
    }
}

class GestionContenido extends DefaultHandler {
    public GestionContenido() {
        super();
    }

    @Override
    public void startDocument() {
        System.out.println("Comienzo del documento");
    }

    @Override
    public void endDocument() {
        System.out.println("Fin del documento");
    }

    @Override
    public void startElement(String uri, String nombre, String nombreC, Attributes atts) {
        System.out.println("\tPrincipio del Elemento: " + nombre);
    }

    @Override
    public void endElement(String uri, String nombre, String nombreC) {
        System.out.println("\tFin del Elemento: " + nombre);
    }

    @Override
    public void characters(char[] ch, int inicio, int longitud) throws SAXException {
        String car = new String(ch, inicio, longitud);
        // Quita los saltos de línea y tabulaciones:
        car = car.replaceAll("[\t\n]","");
        if (!car.isEmpty()) {
            System.out.println("\tCaracteres: " + car);
        }
    }
}
