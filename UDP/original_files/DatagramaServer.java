package app.std;

import java.net.*;
import java.io.*;

public class DatagramaServer {
	public static void main(String args[]) {
		DatagramPacket datapacket, returnpacket;

		int port = 2018;
		int len = 1024;

		try {
			System.out.println("ECO-Server started... ");
			DatagramSocket datasocket = new DatagramSocket(port);
			byte buf[] = new byte[len];
			String retstring = " ";

			while (!retstring.isEmpty()) {
				try {
					datapacket = new DatagramPacket(buf, buf.length);
					datasocket.receive(datapacket);

					returnpacket = new DatagramPacket(datapacket.getData(), datapacket.getLength(),
							datapacket.getAddress(), datapacket.getPort());

					byte rbuffer[] = new byte[len];
					retstring = new String(returnpacket.getData(), 0, returnpacket.getLength());
					System.out.println(retstring);

					// Direcci�n del cliente byte[]
					// System.out.println( datapacket.getAddress().getAddress().toString() );

					datasocket.send(returnpacket);

				} catch (IOException e) {
					System.err.println(e);
				}
			}

			datasocket.close();
			System.out.print("Mensaje vacio.. Conexion terminada.");
		} catch (SocketException se) {
			System.err.println(se);
		}
	}
}