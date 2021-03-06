package app;

import java.net.*;
import java.io.*;

// Daniel Garnica Sanchez - Sockets UDP

public class DatagramaClient {

	public static void main(String[] args) {
		String hostname;
		int port = 2018;
		int len = 1024;

		DatagramPacket sPacket, rPacket;

		if (args.length > 0)
			hostname = args[0];
		else
			hostname = "localhost";

		try {
			InetAddress ia = InetAddress.getByName(hostname);
			DatagramSocket datasocket = new DatagramSocket();
			BufferedReader stdinp = new BufferedReader(new InputStreamReader(System.in));

			String echoline = " ";
			String retstring;

			while (!echoline.isEmpty()) {
				try {
					System.out.print("Operacion: ");
					echoline = stdinp.readLine();
					if (echoline.equals("done"))
						break;

					byte buffer[] = new byte[echoline.length()];
					buffer = echoline.getBytes();
					sPacket = new DatagramPacket(buffer, buffer.length, ia, port);
					datasocket.send(sPacket);

					byte rbuffer[] = new byte[len];
					rPacket = new DatagramPacket(rbuffer, rbuffer.length);
					datasocket.receive(rPacket);

					retstring = new String(rPacket.getData(), 0, rPacket.getLength());
					System.out.println("Mensaje del Servidor: " + retstring);

				} catch (IOException e) {
					System.err.println(e);
				}
			}

			datasocket.close();
			System.out.print("Mensaje vacio.. Conexion terminada.");
		} catch (UnknownHostException e) {
			System.err.println(e);
		} catch (SocketException se) {
			System.err.println(se);
		}
	}
}
