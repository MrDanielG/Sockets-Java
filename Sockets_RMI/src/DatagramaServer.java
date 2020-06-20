
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class DatagramaServer implements Runnable {

	Thread udp;
	DatagramPacket datapacket, returnpacket;
	int port = 2018;
	int len = 1024;

	public DatagramaServer() {
		Servidores.iniciaUDP("ECO-Server started... ");

		udp = new Thread(this);
		udp.start();
	}

	public static void main(String args[]) {
	}

	@Override
	public void run() {
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
					Servidores.iniciaUDP(retstring);

					// Direcci�n del cliente byte[]
					// System.out.println( datapacket.getAddress().getAddress().toString() );

					datasocket.send(returnpacket);

				} catch (IOException e) {
					System.err.println(e);
				}
			}

			datasocket.close();
			Servidores.iniciaUDP("Mensaje vacio.. Conexion terminada.");
			System.out.print("Mensaje vacio.. Conexion terminada.");
		} catch (SocketException se) {
			System.err.println(se);
		}
	}
}