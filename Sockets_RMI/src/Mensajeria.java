
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JLabel;

public class Mensajeria {

	private JFrame frmWhatsappPirata;
	JScrollPane scrollPane;
	static JTextPane textPane;

	// UDP
	DatagramaServer serverUDP;
	DatagramaClient clienteUDP;
	IntermedioUDP interUDP;
	private final Action action = new SwingAction();

	// RMI
	RMIClient clienteRMI;
	RMIServer serverRMI;
	IntermedioRMI interRMI;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mensajeria window = new Mensajeria();
					window.frmWhatsappPirata.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Mensajeria() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmWhatsappPirata = new JFrame();
		frmWhatsappPirata.setTitle("Buapsapp");
		frmWhatsappPirata.setBounds(100, 100, 367, 531);
		frmWhatsappPirata.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWhatsappPirata.getContentPane().setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 69, 298, 149);
		frmWhatsappPirata.getContentPane().add(scrollPane);

		textPane = new JTextPane();
		scrollPane.setViewportView(textPane);

		JButton btnSend = new JButton("Crear Cliente");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				interUDP.crearDialogo();
			}
		});
		btnSend.setBounds(190, 230, 138, 35);
		frmWhatsappPirata.getContentPane().add(btnSend);

		JButton btnUdp = new JButton("UDP Server");
		btnUdp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				serverUDP = new DatagramaServer();
				clienteUDP = new DatagramaClient();
				interUDP = new IntermedioUDP(clienteUDP);
			}
		});
		btnUdp.setBounds(190, 22, 138, 35);
		frmWhatsappPirata.getContentPane().add(btnUdp);

		JButton btnRMI = new JButton("RMI Server");
		btnRMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clienteRMI = new RMIClient();
				try {
					serverRMI = new RMIServer();
				} catch (RemoteException e1) {
					e1.printStackTrace();
					System.out.println("Error al Crear RMI");
				}
				interRMI = new IntermedioRMI(clienteRMI);
			}
		});
		btnRMI.setBounds(30, 22, 138, 35);
		frmWhatsappPirata.getContentPane().add(btnRMI);

		JButton btnClienteRmi = new JButton("Cliente RMI");
		btnClienteRmi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				interRMI.crearDialogo();
			}
		});
		btnClienteRmi.setBounds(30, 230, 138, 35);
		frmWhatsappPirata.getContentPane().add(btnClienteRmi);

		JLabel lblInstrucciones = new JLabel("Instrucciones:");
		lblInstrucciones.setBounds(30, 277, 298, 24);
		frmWhatsappPirata.getContentPane().add(lblInstrucciones);

		JLabel lblNewLabel = new JLabel("1. Inicializar Servidor");
		lblNewLabel.setBounds(30, 313, 298, 14);
		frmWhatsappPirata.getContentPane().add(lblNewLabel);

		JLabel lblDarClick = new JLabel("2. Crear Clientes");
		lblDarClick.setBounds(30, 356, 325, 24);
		frmWhatsappPirata.getContentPane().add(lblDarClick);

		JLabel lblDarClick_1 = new JLabel("1.1 Dar click en el servidor que se desee utilizar");
		lblDarClick_1.setBounds(57, 339, 298, 14);
		frmWhatsappPirata.getContentPane().add(lblDarClick_1);

		JLabel lblDarClick_1_1 = new JLabel("2.1 Dar click en el cliente deseado");
		lblDarClick_1_1.setBounds(57, 379, 298, 14);
		frmWhatsappPirata.getContentPane().add(lblDarClick_1_1);

		JLabel lblDarClick_1_1_1 = new JLabel("Nota");
		lblDarClick_1_1_1.setBounds(30, 405, 298, 14);
		frmWhatsappPirata.getContentPane().add(lblDarClick_1_1_1);

		JLabel lblDarClick_1_1_1_1 = new JLabel("En caso de usar el Server RMI:");
		lblDarClick_1_1_1_1.setBounds(30, 431, 298, 14);
		frmWhatsappPirata.getContentPane().add(lblDarClick_1_1_1_1);

		JLabel lblDarClick_1_1_1_1_1 = new JLabel("Se debera ejecutar \"rmiregistry\"");
		lblDarClick_1_1_1_1_1.setBounds(40, 451, 298, 14);
		frmWhatsappPirata.getContentPane().add(lblDarClick_1_1_1_1_1);

		JLabel lblDarClick_1_1_1_1_1_1 = new JLabel("en la carpeta /bin del proyecto");
		lblDarClick_1_1_1_1_1_1.setBounds(40, 466, 298, 14);
		frmWhatsappPirata.getContentPane().add(lblDarClick_1_1_1_1_1_1);

	}

	public static void iniciaUDP(String input) {
		String aux = textPane.getText();
		aux += "\n" + input;
		textPane.setText("");
		textPane.setText(aux);
	}

	public static void iniciaRMI(String input) {
		String aux = textPane.getText();
		aux += "\n" + input;
		textPane.setText("");
		textPane.setText(aux);
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
		}
	}
}
