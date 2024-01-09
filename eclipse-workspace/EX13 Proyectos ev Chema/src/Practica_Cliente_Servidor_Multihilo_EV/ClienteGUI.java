package Practica_Cliente_Servidor_Multihilo_EV;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClienteGUI extends JFrame {

    private Socket socket;
    private BufferedReader entrada;
    private PrintWriter salida;

    private JTextArea areaChat;
    private JTextField campoMensaje;

    public ClienteGUI() {
        setTitle("Cliente GUI");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        areaChat = new JTextArea();
        areaChat.setEditable(false);

        campoMensaje = new JTextField();
        JButton botonEnviar = new JButton("Enviar");

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(new JScrollPane(areaChat), BorderLayout.CENTER);
        panel.add(campoMensaje, BorderLayout.SOUTH);
        panel.add(botonEnviar, BorderLayout.EAST);

        add(panel);

        campoMensaje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enviarMensaje();
            }
        });

        botonEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enviarMensaje();
            }
        });

        conectarAServidor();
    }

    private void conectarAServidor() {
        try {
            socket = new Socket("localhost", 12345);
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            salida = new PrintWriter(socket.getOutputStream(), true);

            // Hilo para recibir mensajes del servidor
            Thread hiloRecepcion = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String mensajeServidor;
                        while ((mensajeServidor = entrada.readLine()) != null) {
                            mostrarMensaje(mensajeServidor);

                            // Verificar si el mensaje indica que el cliente se desconectó
                            if (mensajeServidor.equalsIgnoreCase("adios")) {
                                cerrarInterfaz();
                                break;
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            hiloRecepcion.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cerrarInterfaz() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Cierra la aplicación por completo
                System.exit(0);
            }
        });
    }


    private void enviarMensaje() {
        String mensaje = campoMensaje.getText();
        if (!mensaje.isEmpty()) {
            salida.println(mensaje);
            campoMensaje.setText("");
        }
    }

    private void mostrarMensaje(String mensaje) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                areaChat.append(mensaje + "\n");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClienteGUI().setVisible(true);
            }
        });
    }
}
