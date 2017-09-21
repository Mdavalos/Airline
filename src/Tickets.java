import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Color;

public class Tickets extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tickets frame = new Tickets();
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
	public Tickets() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTickets_1 = new JLabel("Tickets");
		lblTickets_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTickets_1.setBounds(190, 6, 61, 16);
		contentPane.add(lblTickets_1);

		JButton btnMainScreen = new JButton("Main Screen");
		btnMainScreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectionScreen.frame.setVisible(true);
				SeatSelection.tickets = "";
				dispose();
			}
		});
		btnMainScreen.setBounds(6, 1, 117, 29);
		contentPane.add(btnMainScreen);

		JTextArea Tickets = new JTextArea();
		Tickets.setBackground(Color.LIGHT_GRAY);
		Tickets.setForeground(Color.BLACK);
		Tickets.setBounds(6, 28, 438, 244);
		contentPane.add(Tickets);
		Tickets.setText(SeatSelection.tickets);
	}
}
