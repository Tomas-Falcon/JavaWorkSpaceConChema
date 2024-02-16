import java.awt.*;
import java.awt.event.*;

public class InterfazAWT extends Frame {

    private Label etiquetaTemperatura, etiquetaCiudad, etiquetaPais, etiquetaHora, etiquetaCondiciones;

    private Label cajaTemperatura, cajaCiudad, cajaPais, cajaHora, cajaCondiciones;

    private double temperaturaCelsius;
    private String nombreCiudad, nombrePais, horaActual, condicionesClima;

    public InterfazAWT() {
        super("Clima");
        setSize(400, 300);
        setLayout(new GridLayout(5, 2));
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        etiquetaTemperatura = new Label("Temperatura Actual:");
        add(etiquetaTemperatura);

        cajaTemperatura = new Label("");
        add(cajaTemperatura);

        etiquetaCiudad = new Label("Nombre de la Ciudad:");
        add(etiquetaCiudad);

        cajaCiudad = new Label("");
        add(cajaCiudad);

        etiquetaPais = new Label("País de Origen:");
        add(etiquetaPais);

        cajaPais = new Label("");
        add(cajaPais);

        etiquetaHora = new Label("Hora Local:");
        add(etiquetaHora);

        cajaHora = new Label("");
        add(cajaHora);

        etiquetaCondiciones = new Label("Condiciones Climáticas:");
        add(etiquetaCondiciones);

        cajaCondiciones = new Label("");
        add(cajaCondiciones);

        setLocation(200, 200);
        setVisible(true);
    }

    public void datos(String descripcion1, double temperatura1, String ciudad1, String pais1, String hora1) {
        condicionesClima = descripcion1;
        temperaturaCelsius = temperatura1;
        nombreCiudad = ciudad1;
        nombrePais = pais1;
        horaActual = hora1;

        cajaTemperatura.setText(temperaturaCelsius + " ºC");
        cajaCiudad.setText(nombreCiudad);
        cajaPais.setText(nombrePais);
        cajaHora.setText(horaActual);
        cajaCondiciones.setText(condicionesClima);
    }

    public static void main(String[] args) {
        // Ejemplo de uso
        InterfazAWT interfaz = new InterfazAWT();
        interfaz.datos("Cielo despejado", 25.5, "Madrid", "España", "14:30");
    }
}
