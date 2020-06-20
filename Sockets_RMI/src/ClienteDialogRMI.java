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

public class ClienteDialogRMI extends JFrame {

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
					ClienteDialogRMI frame = new ClienteDialogRMI();
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
	public ClienteDialogRMI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 343, 368);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Client = new JTextPane();
		Client.setBounds(20, 41, 300, 250);
		contentPane.add(Client);
		
		textMsj = new JTextField();
		textMsj.setColumns(10);
		textMsj.setBounds(20, 302, 200, 22);
		contentPane.add(textMsj);
		
		JButton btnSend = new JButton("Enviar");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String output = "["+nickname.getText()+"]\n"+textMsj.getText() + "\n";
				IntermedioRMI.enviarMns(output);
			}
		});
		btnSend.setBounds(232, 303, 90, 21);
		contentPane.add(btnSend);
		
		JLabel lblNewLabel = new JLabel("Nombre de Usuario:");
		lblNewLabel.setBounds(20, 14, 161, 16);
		contentPane.add(lblNewLabel);
		
		nickname = new JTextField();
		nickname.setColumns(10);
		nickname.setBounds(143, 7, 177, 22);
		contentPane.add(nickname);
	}
	
	public void mensaje(String msj) {
		String aux = Client.getText();
		aux += "\n"+msj;
		Client.setText(" ");
		Client.setText(aux);
	}
}
