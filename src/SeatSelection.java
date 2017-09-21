import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.Component;
import java.awt.Canvas;
import javax.swing.ButtonGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.JTable;

public class SeatSelection extends JFrame {

	static String tickets = "";

	private JPanel contentPane;

	private JToggleButton[][] seatGrid = new JToggleButton[20][6];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SeatSelection frame = new SeatSelection();
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
	public SeatSelection() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		setBounds(100, 100, 800, 447);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnMainScreen = new JButton("Main Screen");
		btnMainScreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectionScreen.frame.setVisible(true);
				dispose();
			}
		});
		btnMainScreen.setBounds(6, 6, 117, 29);
		contentPane.add(btnMainScreen);

		JLabel lblAisle = new JLabel("Aisle");
		lblAisle.setHorizontalAlignment(SwingConstants.CENTER);
		lblAisle.setBounds(317, 206, 61, 16);
		contentPane.add(lblAisle);

		final JButton btnGetTickets = new JButton("Get Tickets");
		btnGetTickets.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(SelectionScreen.ticketsLeft == 0 ){
					String seats = "";
					for( int row = 0; row < 20; row++){
						for(int col = 0; col < 6; col++){
							if(seatGrid[row][col].isSelected()){
								seats += "Row: " + Integer.toString(row+1) + ",Seat " + Integer.toString(col+1) + "\n";
								if(SelectionScreen.type == "F"){
									tickets += "Family Seat"+"\n" +"Row: " + Integer.toString(row+1) + ",Seat " + Integer.toString(col+1) + "\n\n";
								}else if(SelectionScreen.type == "T"){
									tickets += "Tourist Seat\nRow: " + Integer.toString(row+1) + ",Seat " + Integer.toString(col+1) + "\n\n";
								}else {
									tickets += "Business Seat\nRow: " + Integer.toString(row+1) + ",Seat " + Integer.toString(col+1) + "\n\n";
								}
							}
						}
					}
					int choose = JOptionPane.showConfirmDialog(contentPane,
							"Is this the correct seating ?\n" + seats,
							"Seating Confirmation", JOptionPane.YES_NO_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
					if (choose == JOptionPane.YES_OPTION) {
						for( int row = 0; row < 20; row++){
							for(int col = 0; col < 6; col++){
								if(seatGrid[row][col].isSelected()){
									SelectionScreen.matrixSeat[row][col] = SelectionScreen.type +
											Integer.toString(row+1) + Integer.toString(col+1)  + Character.toString((char)SelectionScreen.groupNum);
								}
							}
						}
						SelectionScreen.groupNum += 1;
						SelectionScreen.seatsLeft -= SelectionScreen.numSeats;
						Tickets ticket = new Tickets();
						ticket.setVisible(true);
						dispose();
					}
					else{
						tickets = "";
					}
				} else {
					JOptionPane.showMessageDialog(contentPane,
							"Not all seats have been selected.",
							"Error",
							JOptionPane.PLAIN_MESSAGE);

				}
			}
		});
		btnGetTickets.setBounds(568, 354, 117, 29);
		contentPane.add(btnGetTickets);

		JLabel lblTicketsToGet = new JLabel("Tickets To Get: ");
		lblTicketsToGet.setBounds(240, 65, 99, 16);
		contentPane.add(lblTicketsToGet);

		final JLabel lblnumTickets = new JLabel();
		lblnumTickets.setHorizontalAlignment(SwingConstants.CENTER);
		lblnumTickets.setBounds(344, 65, 23, 16);
		contentPane.add(lblnumTickets);

		lblnumTickets.setText(Integer.toString(SelectionScreen.ticketsLeft));

		for( int row = 0; row < 20; row++){
			for(int col = 0; col < 6; col++){
				final int x= row;
				final int y = col;
				JToggleButton seat = new JToggleButton();
				contentPane.add(seat);
				seatGrid[row][col] = seat;
				seatGrid[row][col].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if(seatGrid[x][y].isSelected()){
							SelectionScreen.ticketsLeft -=1;
						}else {
							SelectionScreen.ticketsLeft +=1;
						}

						if(SelectionScreen.ticketsLeft == -1){
							seatGrid[x][y].setSelected(false);
							SelectionScreen.ticketsLeft +=1;
						}
						lblnumTickets.setText(Integer.toString(SelectionScreen.ticketsLeft));
					}

				});
			}
		}

		//Read in matrix and fill in already selected seats
		for( int row = 0; row < 20; row++){
			for(int col = 0; col < 6; col++){
				if(SelectionScreen.matrixSeat[row][col] != null){
					seatGrid[row][col].setEnabled(false);
				}
			}
		}

		seatGrid[14][5].setBounds(534, 119, 41, 29);

		seatGrid[15][5].setBounds(568, 119, 41, 29);

		seatGrid[16][5].setBounds(601, 119, 41, 29);

		seatGrid[17][5].setBounds(636, 119, 41, 29);

		seatGrid[18][5].setBounds(671, 119, 41, 29);

		seatGrid[19][5].setBounds(706, 119, 41, 29);

		seatGrid[14][4].setBounds(534, 144, 41, 29);

		seatGrid[15][4].setBounds(568, 144, 41, 29);

		seatGrid[16][4].setBounds(601, 144, 41, 29);

		seatGrid[17][4].setBounds(636, 144, 41, 29);

		seatGrid[18][4].setBounds(671, 144, 41, 29);

		seatGrid[19][4].setBounds(706, 144, 41, 29);

		seatGrid[14][3].setBounds(534, 170, 41, 29);

		seatGrid[15][3].setBounds(568, 170, 41, 29);

		seatGrid[16][3].setBounds(601, 170, 41, 29);

		seatGrid[17][3].setBounds(636, 170, 41, 29);

		seatGrid[18][3].setBounds(671, 170, 41, 29);

		seatGrid[19][3].setBounds(706, 170, 41, 29);

		seatGrid[11][5].setBounds(432, 119, 41, 29);

		seatGrid[12][5].setBounds(466, 119, 41, 29);

		seatGrid[13][5].setBounds(499, 119, 41, 29);

		seatGrid[17][2].setBounds(636, 234, 41, 29);

		seatGrid[18][2].setBounds(671, 234, 41, 29);

		seatGrid[19][2].setBounds(706, 234, 41, 29);

		seatGrid[11][4].setBounds(432, 145, 41, 29);

		seatGrid[12][4].setBounds(466, 145, 41, 29);

		seatGrid[13][4].setBounds(499, 145, 41, 29);

		seatGrid[17][1].setBounds(636, 260, 41, 29);

		seatGrid[18][1].setBounds(671, 260, 41, 29);

		seatGrid[19][1].setBounds(706, 260, 41, 29);

		seatGrid[11][3].setBounds(432, 172, 41, 29);

		seatGrid[12][3].setBounds(466, 172, 41, 29);

		seatGrid[13][3].setBounds(499, 172, 41, 29);

		seatGrid[17][0].setBounds(636, 287, 41, 29);

		seatGrid[18][0].setBounds(671, 287, 41, 29);

		seatGrid[19][0].setBounds(706, 287, 41, 29);

		seatGrid[11][2].setBounds(432, 234, 41, 29);

		seatGrid[12][2].setBounds(466, 234, 41, 29);

		seatGrid[13][2].setBounds(499, 234, 41, 29);

		seatGrid[14][2].setBounds(531, 234, 41, 29);

		seatGrid[15][2].setBounds(566, 234, 41, 29);

		seatGrid[16][2].setBounds(601, 234, 41, 29);

		seatGrid[11][1].setBounds(432, 260, 41, 29);

		seatGrid[12][1].setBounds(466, 260, 41, 29);

		seatGrid[13][1].setBounds(499, 260, 41, 29);

		seatGrid[14][1].setBounds(531, 260, 41, 29);

		seatGrid[15][1].setBounds(566, 260, 41, 29);

		seatGrid[16][1].setBounds(601, 260, 41, 29);

		seatGrid[11][0].setBounds(432, 286, 41, 29);

		seatGrid[12][0].setBounds(466, 286, 41, 29);

		seatGrid[13][0].setBounds(499, 286, 41, 29);

		seatGrid[14][0].setBounds(531, 286, 41, 29);

		seatGrid[15][0].setBounds(566, 286, 41, 29);

		seatGrid[16][0].setBounds(601, 286, 41, 29);

		seatGrid[10][5].setBounds(398, 119, 41, 29);

		seatGrid[10][4].setBounds(398, 145, 41, 29);

		seatGrid[10][3].setBounds(398, 172, 41, 29);

		seatGrid[10][2].setBounds(398, 234, 41, 29);

		seatGrid[10][1].setBounds(398, 260, 41, 29);

		seatGrid[10][0].setBounds(398, 287, 41, 29);

		seatGrid[4][5].setBounds(192, 119, 41, 29);

		seatGrid[5][5].setBounds(226, 119, 41, 29);

		seatGrid[6][5].setBounds(259, 119, 41, 29);

		seatGrid[7][5].setBounds(294, 119, 41, 29);

		seatGrid[8][5].setBounds(329, 119, 41, 29);

		seatGrid[9][5].setBounds(364, 119, 41, 29);

		seatGrid[4][4].setBounds(192, 144, 41, 29);

		seatGrid[5][4].setBounds(226, 144, 41, 29);

		seatGrid[6][4].setBounds(259, 144, 41, 29);

		seatGrid[7][4].setBounds(294, 144, 41, 29);

		seatGrid[8][4].setBounds(329, 144, 41, 29);

		seatGrid[9][4].setBounds(364, 144, 41, 29);

		seatGrid[4][3].setBounds(192, 170, 41, 29);

		seatGrid[5][3].setBounds(226, 170, 41, 29);

		seatGrid[6][3].setBounds(259, 170, 41, 29);

		seatGrid[7][3].setBounds(294, 170, 41, 29);

		seatGrid[8][3].setBounds(329, 170, 41, 29);

		seatGrid[9][3].setBounds(364, 170, 41, 29);

		seatGrid[1][5].setBounds(90, 119, 41, 29);

		seatGrid[2][5].setBounds(124, 119, 41, 29);

		seatGrid[3][5].setBounds(157, 119, 41, 29);

		seatGrid[7][2].setBounds(294, 234, 41, 29);

		seatGrid[8][2].setBounds(329, 234, 41, 29);

		seatGrid[9][2].setBounds(364, 234, 41, 29);

		seatGrid[1][4].setBounds(90, 145, 41, 29);

		seatGrid[2][4].setBounds(124, 145, 41, 29);

		seatGrid[3][4].setBounds(157, 145, 41, 29);

		seatGrid[7][1].setBounds(294, 260, 41, 29);

		seatGrid[8][1].setBounds(329, 260, 41, 29);

		seatGrid[9][1].setBounds(364, 260, 41, 29);

		seatGrid[1][3].setBounds(90, 172, 41, 29);

		seatGrid[2][3].setBounds(124, 172, 41, 29);

		seatGrid[3][3].setBounds(157, 172, 41, 29);

		seatGrid[7][0].setBounds(294, 287, 41, 29);

		seatGrid[8][0].setBounds(329, 287, 41, 29);

		seatGrid[9][0].setBounds(364, 287, 41, 29);

		seatGrid[1][2].setBounds(90, 234, 41, 29);

		seatGrid[2][2].setBounds(124, 234, 41, 29);

		seatGrid[3][2].setBounds(157, 234, 41, 29);

		seatGrid[4][2].setBounds(189, 234, 41, 29);

		seatGrid[5][2].setBounds(224, 234, 41, 29);

		seatGrid[6][2].setBounds(259, 234, 41, 29);

		seatGrid[1][1].setBounds(90, 260, 41, 29);

		seatGrid[2][1].setBounds(124, 260, 41, 29);

		seatGrid[3][1].setBounds(157, 260, 41, 29);

		seatGrid[4][1].setBounds(189, 260, 41, 29);

		seatGrid[5][1].setBounds(224, 260, 41, 29);

		seatGrid[6][1].setBounds(259, 260, 41, 29);

		seatGrid[1][0].setBounds(90, 286, 41, 29);

		seatGrid[2][0].setBounds(124, 286, 41, 29);

		seatGrid[3][0].setBounds(157, 286, 41, 29);

		seatGrid[4][0].setBounds(189, 286, 41, 29);

		seatGrid[5][0].setBounds(224, 286, 41, 29);

		seatGrid[6][0].setBounds(259, 286, 41, 29);

		seatGrid[0][5].setBounds(56, 119, 41, 29);

		seatGrid[0][4].setBounds(56, 145, 41, 29);

		seatGrid[0][3].setBounds(56, 172, 41, 29);

		seatGrid[0][2].setBounds(56, 234, 41, 29);

		seatGrid[0][1].setBounds(56, 260, 41, 29);

		seatGrid[0][0].setBounds(56, 287, 41, 29);

		if(!SelectionScreen.businessSeat){
			seatGrid[0][0].setEnabled(false);
			seatGrid[0][1].setEnabled(false);
			seatGrid[0][2].setEnabled(false);
			seatGrid[0][3].setEnabled(false);
			seatGrid[0][4].setEnabled(false);
			seatGrid[0][5].setEnabled(false);
			seatGrid[1][0].setEnabled(false);
			seatGrid[1][1].setEnabled(false);
			seatGrid[1][2].setEnabled(false);
			seatGrid[1][3].setEnabled(false);
			seatGrid[1][4].setEnabled(false);
			seatGrid[1][5].setEnabled(false);
		}

	}
}
