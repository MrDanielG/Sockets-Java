

import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class DatagramaClient{
	
	public String hostname;
	int port;
	int len;
	
	DatagramPacket sPacket, rPacket;
	DatagramSocket datasocket;
	
	public DatagramaClient() {
		hostname = "localhost";
		port = 2018;
		len = 1024;
		
		try {
			datasocket = new DatagramSocket();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String mandarMns(String output) {

		String retstring = null;
		
		try{
			InetAddress ia = InetAddress.getByName(hostname);
//			BufferedReader stdinp = new BufferedReader(new InputStreamReader(System.in));
			
			String echoline = output;
			
				try{
					
					byte buffer[] = new byte[echoline.length()];
					buffer = echoline.getBytes();
					sPacket = new DatagramPacket( buffer, buffer.length, ia, port );
					datasocket.send(sPacket);
					
					System.out.println("puerto: "+ sPacket.getPort());
					
					byte rbuffer[] = new byte[len];
					rPacket = new DatagramPacket( rbuffer, rbuffer.length );
					datasocket.receive( rPacket );
					
					retstring = new String( rPacket.getData(), 0, rPacket.getLength() );
					
//					System.out.println("Mensaje del Servidor: " + retstring );
					
				}catch( IOException e )
				{
					System.err.println( e );
				}
				
//			if ( !echoline.isEmpty() ) {
//				datasocket.close();
//				System.out.print( "Mensaje vacío.. Conexion terminada." );
//				ClienteDialog.recibirMesg( "Mensaje vacío.. Conexion terminada." );
//			}
			
		}catch( UnknownHostException e )
		{
			System.err.println( e );
		}
		return retstring;
	}
	

	public static void main(String[] args) {
		
	}
}
