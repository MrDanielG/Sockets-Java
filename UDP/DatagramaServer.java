package app;

import java.net.*;
import java.nio.ByteBuffer;
import java.io.*;
import java.util.StringTokenizer;

// Daniel Garnica Sanchez - Sockets UDP

public class DatagramaServer {
	public static void main(String args[]) {
		DatagramPacket datapacket, returnpacket;
		double resultado;
		String operacion = null;
		String operandoA = null;
		String operandoB = null;

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

					retstring = new String(datapacket.getData(), 0, datapacket.getLength());
					StringTokenizer st = new StringTokenizer(retstring, " ");

					while (st.hasMoreTokens()) {
						String token = st.nextToken();
						switch (token) {
							case "+":
								operacion = token;
								break;

							case "-":
								operacion = token;
								break;

							case "*":
								operacion = token;
								break;

							case "/":
								operacion = token;
								break;

							case "^":
								operacion = token;
								break;

							default:
								if (operacion == null) {
									operandoA = token;
								} else {
									operandoB = token;
								}
								break;
						}
					}

					System.out.println("Operando A: " + operandoA);
					System.out.println("Operacion: " + operacion);
					System.out.println("Operando B: " + operandoB);

					resultado = operacion(operacion, operandoA, operandoB);
					System.out.println("Resultado: " + resultado);

					operacion = null;

					String res = String.valueOf(resultado);
					byte rbuffer[] = new byte[res.length()];
					rbuffer = res.getBytes();

					returnpacket = new DatagramPacket(rbuffer, rbuffer.length, datapacket.getAddress(),
							datapacket.getPort());

					retstring = new String(returnpacket.getData(), 0, returnpacket.getLength());
					System.out.println("Enviando: " + retstring);
					System.out.println();

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

	public static double operacion(String op, String _a, String _b) {
		double a = Double.parseDouble(_a);
		double b = Double.parseDouble(_b);
		double res = 0;

		switch (op) {
			case "+":
				res = a + b;
				break;

			case "-":
				res = a - b;
				break;

			case "*":
				res = a * b;
				break;

			case "/":
				res = a / b;
				break;

			case "^":
				res = Math.pow(a, b);
				break;

			default:
				System.out.println("Falla en operaciones");
				break;
		}
		return res;
	}

	public static byte[] toByteArray(double value) {
		byte[] bytes = new byte[8];
		ByteBuffer.wrap(bytes).putDouble(value);
		return bytes;
	}

	public static double toDouble(byte[] bytes) {
		return ByteBuffer.wrap(bytes).getDouble();
	}
}