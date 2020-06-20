package app;

import java.io.*;
import java.net.*;

class TCPClient {
    public static void main(String argv[]) throws Exception {
        String frase = " ";
        String fraseModificada;
        Socket clientSocket = new Socket("localhost", 6789);

        InputStreamReader isr1 = new InputStreamReader(System.in);
        BufferedReader inFromUser = new BufferedReader(isr1);

        OutputStream os = clientSocket.getOutputStream();
        DataOutputStream outToServer = new DataOutputStream(os);

        InputStreamReader isr2 = new InputStreamReader(clientSocket.getInputStream());
        BufferedReader inFromServer = new BufferedReader(isr2);

        while (!frase.isEmpty()) {
            System.out.print("Mensaje: ");
            frase = inFromUser.readLine();
            outToServer.writeBytes(frase + '\n');

            fraseModificada = inFromServer.readLine();
            System.out.println("Mensaje del Servidor: " + fraseModificada);
        }

        clientSocket.close();
        System.out.print("Mensaje vacio.. Conexion terminada.");
    }
}
