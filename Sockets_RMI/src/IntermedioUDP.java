import java.util.ArrayList;
import java.util.List;

public class IntermedioUDP {
	static DatagramaClient clienteUDP;
	static ClienteDialogUDP msj1;

	static List<ClienteDialogUDP> listaClientes;

	public IntermedioUDP(DatagramaClient clienteUDP) {
		IntermedioUDP.clienteUDP = clienteUDP;
		listaClientes = new ArrayList<>();
	}

	public void crearDialogo() {
		ClienteDialogUDP msj1 = new ClienteDialogUDP();
		msj1.setVisible(true);
		listaClientes.add(msj1);
	}

	public static void enviarMns(String mensaje) {
		String recibido = clienteUDP.mandarMns(mensaje);
		for (ClienteDialogUDP clienteDialogo : listaClientes) {
			clienteDialogo.mensaje(recibido);
		}

	}
}
