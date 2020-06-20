import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class ClienteDialogUDP extends JFrame {

	private JPanel contentPane;
	JTextPane Client;
	private JTextField textMsj;
	private JTextField nickname;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienteDialogUDP frame = new ClienteDialogUDP();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ClienteDialogUDP() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 360, 373);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Client = new JTextPane();
		Client.setBounds(20, 41, 317, 250);
		contentPane.add(Client);

		textMsj = new JTextField();
		textMsj.setColumns(10);
		textMsj.setBounds(20, 302, 215, 22);
		contentPane.add(textMsj);

		JButton btnSend = new JButton("Enviar");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String output = "[" + nickname.getText() + "]\n" + textMsj.getText() + "\n";
				IntermedioUDP.enviarMns(output);
			}
		});
		btnSend.setBounds(247, 301, 90, 24);
		contentPane.add(btnSend);

		JLabel lblNewLabel = new JLabel("Nombre de Usuario:");
		lblNewLabel.setBounds(20, 13, 134, 16);
		contentPane.add(lblNewLabel);

		nickname = new JTextField();
		nickname.setColumns(10);
		nickname.setBounds(143, 7, 194, 22);
		contentPane.add(nickname);
	}

	public void mensaje(String msj) {
		String aux = Client.getText();
		aux += "\n" + msj;
		Client.setText(" ");
		Client.setText(aux);
	}
}
