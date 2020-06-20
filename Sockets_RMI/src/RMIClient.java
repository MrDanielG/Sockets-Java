//package spell;

/**
 * Este codigo realiza lo siguiente:
 * 
 * 1. Crea un objeto falso o recipiente y una referencia al objeto remoto a trav�s de
 *    la interfaz de nombre "MyInterfaceRMI".
 * 2. Por medio de esta referencia se accede a los m�todos (o servicios) del servidor.
 */

import java.rmi.*;
import java.io.*;

public class RMIClient {
	public RMIClient() {

	}

	public static void main(String argv[]) {
		// try {
		// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//
		// // Creaci�n del objeto recipiete
		// MyInterface objetoAsociado = (MyInterface) Naming.lookup("MyInterfaceRMI");
		//
		// System.out.println("Escribe una frase: ");
		// String input = br.readLine();
		//
		// // Uso del m�todo remoto
		// System.out.println(objetoAsociado.cuentaCaracteres(input));
		// } catch (Exception e) {
		// System.out.println("Excepcion ocurrida: " + e.getMessage());
		// }
	}

	public String mandarMsj(String input) {
		String res = "";
		try {
			MyInterface objetoAsociado = (MyInterface) Naming.lookup("MyInterfaceRMI");
			res = objetoAsociado.msjServer(input);
		} catch (Exception e) {
			System.out.println("Excepcion ocurrida: " + e.getMessage());
		}
		return res;
	}
}