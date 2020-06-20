//package spell;

/**
 * Este codigo realiza lo siguiente:
 * 
 * 1. Utiliza una interfaz remota para publicar el m�todo remoto.
 * 2. Implementa a la interfaz (myInterface.java) en la clase RMIServer.
 * 3. Registra la interfaz en el registro RMI de JAVA (se vincula con cualquier nombre p.e. "MyInterfazRMI").
 * 
 *    NOTA: el programa rmiregistry debe ejecutarce de forma independientemente, pero dentro de la carpeta 
 *    que contiene a RMIServer.class.
 */

import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.server.*;

public class RMIServer extends UnicastRemoteObject implements MyInterface, Runnable {
	Thread rmiThread;

	public RMIServer() throws RemoteException {
		rmiThread = new Thread(this);
		rmiThread.start();
	}

	public static void main(String argv[]) {
		// try {
		// // Creaci�n del objeto remoto o esqueleto
		// RMIServer objetoRemoto = new RMIServer();
		// Naming.rebind("MyInterfaceRMI", objetoRemoto);
		// } catch (Exception e) {
		// System.out.println("Excepcion ocurrida: " + e.getMessage());
		// }
	}

	// Implementaci�n del m�todo remoto
	@Override
	public String cuentaCaracteres(String input) throws RemoteException {
		System.out.println("Se recibe " + input + "  en el servidor!!");
		String respuesta;
		respuesta = "Has escrito " + input.length() + "  caracteres!!";
		return respuesta;
	}

	@Override
	public String msjServer(String input) throws RemoteException {
		System.out.println("Se recibe " + input + "  en el servidor!!");
		return input;
	}

	@Override
	public void run() {
		// System.out.println("Servidor remoto en ejecucion!!");
		// Mensajeria.iniciaRMI("Servidor remoto en ejecucion!!");

		// Creacion del objeto remoto o esqueleto
		RMIServer objetoRemoto;
		try {
			objetoRemoto = new RMIServer();
			Naming.rebind("MyInterfaceRMI", objetoRemoto);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}
}