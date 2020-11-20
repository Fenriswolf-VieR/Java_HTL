import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class quadratische_gui extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JButton btnGleichungAusrechnen;
	private JLabel lblX;
	private JLabel lblX_1;
	private JLabel label;
	private JLabel lblX_2;
	private JLabel lblX_3;
	private JTextField txtLsung;
	private JTextField txtLsung_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					quadratische_gui frame = new quadratische_gui();
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
	public quadratische_gui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField.setBounds(254, 21, 65, 41);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_1.setBounds(27, 21, 65, 41);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_2.setBounds(147, 21, 65, 41);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		btnGleichungAusrechnen = new JButton("Gleichung ausrechnen");
		btnGleichungAusrechnen.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnGleichungAusrechnen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String s1=textField_1.getText();
		    	String s2=textField_2.getText();
		    	String s3=textField.getText();       	
		    	
		    	 double a=Double.parseDouble(s1);  
		    	 double b=Double.parseDouble(s2);
		         double c=Double.parseDouble(s3);  
		         
		         double[] result = Quadratische_Gleichungen.quadr_gleichung(a,b,c);
		         double X1 = result[0];
		         double X2 = result[1];
		         txtLsung.setText(" "+X1);
		         getContentPane().add(txtLsung);
		         txtLsung_1.setText(" "+X2);
		         getContentPane().add(txtLsung_1);
			}
		});
		btnGleichungAusrechnen.setBounds(27, 73, 341, 50);
		contentPane.add(btnGleichungAusrechnen);
		
		lblX = new JLabel("x\u00B2 +");
		lblX.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblX.setBounds(102, 21, 72, 41);
		contentPane.add(lblX);
		
		lblX_1 = new JLabel("x +");
		lblX_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblX_1.setBounds(222, 21, 44, 41);
		contentPane.add(lblX_1);
		
		label = new JLabel(" = 0");
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label.setBounds(324, 21, 57, 41);
		contentPane.add(label);
		
		lblX_2 = new JLabel("X1 =");
		lblX_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblX_2.setBounds(121, 151, 65, 32);
		contentPane.add(lblX_2);
		
		lblX_3 = new JLabel("X2 = ");
		lblX_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblX_3.setBounds(121, 194, 48, 32);
		contentPane.add(lblX_3);
		
		txtLsung = new JTextField();
		txtLsung.setText("L\u00F6sung 1");
		txtLsung.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtLsung.setBounds(172, 151, 106, 32);
		contentPane.add(txtLsung);
		txtLsung.setColumns(10);
		
		txtLsung_1 = new JTextField();
		txtLsung_1.setText("L\u00F6sung 2");
		txtLsung_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtLsung_1.setBounds(172, 194, 106, 32);
		contentPane.add(txtLsung_1);
		txtLsung_1.setColumns(10);
	}
	
	public void windowClosing(WindowEvent e) {
        dispose();
        System.exit(0);
}
	
	
}
