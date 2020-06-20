//package spell;

/**
 * Este codigo define los m�todos o servicios del servidor.
 */

import java.rmi.*;

public interface MyInterface extends Remote {
	// Definici�n del m�todo remoto
	public String cuentaCaracteres(String input) throws RemoteException;

	public String msjServer(String input) throws RemoteException;
}