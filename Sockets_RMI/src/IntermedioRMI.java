import java.util.ArrayList;
import java.util.List;

public class IntermedioRMI {
	static RMIClient clienteRMI;
	static ClienteDialogRMI msj1;

	static List<ClienteDialogRMI> listaClientes;

	public IntermedioRMI(RMIClient clienteRMI) {
		System.out.println("Creado Inter RMI");
		IntermedioRMI.clienteRMI = clienteRMI;
		listaClientes = new ArrayList<>();
	}

	public void crearDialogo() {
		ClienteDialogRMI msj1 = new ClienteDialogRMI();
		msj1.setVisible(true);
		listaClientes.add(msj1);
	}

	public static void enviarMns(String mensaje) {
		String recibido = clienteRMI.mandarMsj(mensaje);
		for (ClienteDialogRMI clienteDialogo : listaClientes) {
			clienteDialogo.mensaje(recibido);
		}

	}
}
