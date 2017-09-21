import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.SwingConstants;
import java.awt.Insets;
import java.awt.Window;

import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JRadioButton;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JSlider;
import javax.swing.JToggleButton;

public class SelectionScreen extends JFrame {

	private JPanel contentPane;
	private JLabel lblSelectAnOption;
	public static JFrame frame;

	static String[][] matrixSeat = new String [20][6];
	private final ButtonGroup Category = new ButtonGroup();
	static JTextField txtNumChildren;
	static JLabel lblNumberOfSeats;
	private final ButtonGroup BusinessSeating = new ButtonGroup();
	static boolean businessSeat = false;
	static int numSeats = 0;
	static int ticketsLeft = 0;
	static int seatsLeft = 120;
	static String type = "";
	static int groupNum = 65;

	int satisIndex = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectionScreen.frame = new SelectionScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	void GenerateSatisfaction() {


		ArrayList<String> seats = new ArrayList<String>();
		ArrayList<String> groups = new ArrayList<String>();
		String seat = "";

		if( seatsLeft >= 110){
			for( int row = 0; row < 20; row++){
				for(int col = 0; col < 6; col++){
					if(matrixSeat[row][col] != null){
						seats.add(matrixSeat[row][col]);
					}
				}
			}
		}
		else{
			String seats2[] = new String [seatsLeft];
			int item = 0;
			for( int row = 0; row < 20; row++){
				for(int col = 0; col < 6; col++){
					if(matrixSeat[row][col] != null){
						seats2[item] = matrixSeat[row][col];
						item += 1;
						
					}
				}
			}
			//get ten random seats from the array
			Random r = new Random();
			int Low = 0;
			int High = 10;
			
			for( int x = 0; x < 10; ++x){
				int Result = r.nextInt(High-Low) + Low;
				seats.add(seats2[Result]);
			}
		}

		System.out.println(seats);
		for(int i=0; i < seats.size(); ++i){

			seat = seats.get(i);

			if(seat.charAt(0) == 'F'){
				if(seat.charAt(2) == '3' || seat.charAt(2) == '4'){
					satisIndex += 5;
				}else{
					satisIndex -= 5;
				}

			}else if(seat.charAt(0) == 'T'){
				if(seat.length() == 5) {
					if(seat.charAt(3) == '1' || seat.charAt(3) == '6'){
						satisIndex += 5;
					}else{
						satisIndex -= 5;
					}
					//group is together
					if(seat.charAt(3) == '1' && matrixSeat[Character.getNumericValue(seat.charAt(1)+ seat.charAt(2))-1][5].charAt(4) == seat.charAt(4)){
						satisIndex += 10;
					} else if(seat.charAt(3) == '6' && matrixSeat[Character.getNumericValue(seat.charAt(1)+ seat.charAt(2))-1][0].charAt(4) == seat.charAt(4)){
						satisIndex += 10;
					}else {
						//group not together
						satisIndex -= 10;
					}
				}else {
					if(seat.charAt(2) == '1' || seat.charAt(2) == '6'){
						satisIndex += 5;
					}else{
						satisIndex -= 5;
					}
					//group is together
					if(seat.charAt(2) == '1' && matrixSeat[Character.getNumericValue(seat.charAt(1))-1][5].charAt(3) == seat.charAt(3)){
						satisIndex += 10;
					} else if(seat.charAt(2) == '6' && matrixSeat[Character.getNumericValue(seat.charAt(1))-1][0].charAt(3) == seat.charAt(3)){
						satisIndex += 10;
					}else {
						//group not together
						satisIndex -= 10;
					}
				}
			}else{
				if(seat.charAt(1) == '1' || seat.charAt(1) == '2'){
					satisIndex += 5;
				}else{
					satisIndex -= 5;
				}

			}
		}
		JOptionPane.showMessageDialog(contentPane,
				"Satisfaction Index: " + Integer.toString(satisIndex),
				"Report",
				JOptionPane.PLAIN_MESSAGE);
	}

	/**
	 * Create the frame.
	 */
	public SelectionScreen() {
		setTitle("CS Airlines");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int choose = JOptionPane.showConfirmDialog(contentPane,
						"Are you sure you want to exit the application ?",
						"Confirm Close", JOptionPane.YES_NO_OPTION,
						JOptionPane.INFORMATION_MESSAGE);
				if (choose == JOptionPane.YES_OPTION) {
					e.getWindow().dispose();
				}
			}
		});


		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblSelectAnOption = new JLabel("Select an Option Below");
		lblSelectAnOption.setFont(new Font("Onyx", Font.PLAIN, 38));
		lblSelectAnOption.setBounds(16, 80, 216, 40);
		contentPane.add(lblSelectAnOption);

		final JRadioButton rdbtnFamily = new JRadioButton("Family");
		Category.add(rdbtnFamily);
		rdbtnFamily.setSelected(true);
		rdbtnFamily.setBounds(52, 132, 141, 23);
		contentPane.add(rdbtnFamily);



		final JRadioButton rdbtnTourist = new JRadioButton("Tourist");
		Category.add(rdbtnTourist);
		rdbtnTourist.setBounds(52, 170, 141, 23);
		contentPane.add(rdbtnTourist);

		final JRadioButton rdbtnBusiness = new JRadioButton("Business");
		Category.add(rdbtnBusiness);
		rdbtnBusiness.setBounds(52, 212, 141, 23);
		contentPane.add(rdbtnBusiness);

		JButton btnBookFlight = new JButton("Book Flight");
		btnBookFlight.setBounds(285, 169, 117, 29);
		contentPane.add(btnBookFlight);

		JLabel lblWelcomeToCs = new JLabel("Bienvenidos a la Aerolínea CS");
		lblWelcomeToCs.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToCs.setFont(new Font("Onyx", Font.PLAIN, 63));
		lblWelcomeToCs.setBounds(6, 6, 438, 62);
		contentPane.add(lblWelcomeToCs);

		final JLabel lblEnterNumberOf = new JLabel("Enter Number of Children");
		lblEnterNumberOf.setBounds(177, 136, 174, 16);
		contentPane.add(lblEnterNumberOf);

		txtNumChildren = new JTextField();
		txtNumChildren.setHorizontalAlignment(SwingConstants.CENTER);
		txtNumChildren.setText("0");
		txtNumChildren.setBounds(361, 131, 29, 26);
		contentPane.add(txtNumChildren);
		txtNumChildren.setColumns(10);

		final JRadioButton rdbtnBusinessSelect = new JRadioButton("Business Select Seat");
		rdbtnBusinessSelect.setEnabled(false);
		rdbtnBusinessSelect.setSelected(true);
		BusinessSeating.add(rdbtnBusinessSelect);
		rdbtnBusinessSelect.setBounds(205, 212, 185, 23);
		contentPane.add(rdbtnBusinessSelect);

		final JRadioButton rdbtnNormalSeat = new JRadioButton("Normal Seat");
		rdbtnNormalSeat.setEnabled(false);
		BusinessSeating.add(rdbtnNormalSeat);
		rdbtnNormalSeat.setBounds(205, 234, 141, 23);
		contentPane.add(rdbtnNormalSeat);

		JButton btnCheckAvailableSeats = new JButton("Check Available Seats");
		btnCheckAvailableSeats.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(frame,
						"Remaining Seats:" + Integer.toString(seatsLeft));
			}
		});
		btnCheckAvailableSeats.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		btnCheckAvailableSeats.setBounds(268, 96, 134, 29);
		contentPane.add(btnCheckAvailableSeats);

		JButton btnFlightStart = new JButton("Flight Start");
		btnFlightStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//				for( int row = 0; row < 20; row++){
				//					for(int col = 0; col < 6; col++){
				//						System.out.print(matrixSeat[row][col]);
				//					}
				//					System.out.println("\n");
				//				}
				GenerateSatisfaction();
			}
		});
		btnFlightStart.setBounds(6, 243, 117, 29);
		contentPane.add(btnFlightStart);

		rdbtnFamily.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				lblEnterNumberOf.setEnabled(true);
				txtNumChildren.setEnabled(true);
				rdbtnBusinessSelect.setEnabled(false);
				rdbtnNormalSeat.setEnabled(false);
			}

		});

		rdbtnBusiness.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				lblEnterNumberOf.setEnabled(false);
				txtNumChildren.setEnabled(false);
				rdbtnBusinessSelect.setEnabled(true);
				rdbtnNormalSeat.setEnabled(true);
			}

		});

		rdbtnTourist.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				lblEnterNumberOf.setEnabled(false);
				txtNumChildren.setEnabled(false);
				rdbtnBusinessSelect.setEnabled(false);
				rdbtnNormalSeat.setEnabled(false);
			}

		});

		btnBookFlight.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(rdbtnBusinessSelect.isSelected() && rdbtnBusiness.isSelected() ){
					businessSeat = true;
				}
				else {
					businessSeat = false;
				}
				if(rdbtnTourist.isSelected()){
					ticketsLeft = 2;
					type = "T";
				} else if (rdbtnFamily.isSelected()){
					ticketsLeft = (2 + Integer.parseInt(txtNumChildren.getText()));
					type = "F";
				} else {
					ticketsLeft = 1;
					type = "B";

				}
				numSeats = ticketsLeft;
				SeatSelection seat = new SeatSelection();
				seat.setVisible(true);
				setVisible(false);
			}
		});


	}
}
