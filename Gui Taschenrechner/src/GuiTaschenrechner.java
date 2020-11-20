import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class GuiTaschenrechner extends JFrame {

	private JLabel labelOperand1;
	private JLabel labelOperand2;
	private JLabel labelOperator;

	private JTextField fieldOperand1;
	private JTextField fieldOperand2;

	private JButton buttonRechnen;

	private String operator;

	public GuiTaschenrechner(String titel, String operator) {
		this.operator = operator;

		setTitle(titel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new FlowLayout());


		setSize(250, 125);
		setResizable(false);

		initComponents();

		add(labelOperand1);
		add(labelOperand2);
		add(fieldOperand1);
		add(labelOperator);
		add(fieldOperand2);
		add(buttonRechnen);

		setLocationRelativeTo(null);
		setVisible(true);


	}

	private void initComponents() {

		labelOperand1 = new JLabel("1. Summand       ");
		labelOperand2 = new JLabel("2. Summand          ");
		labelOperator = new JLabel(operator);

		fieldOperand1 = new JTextField(8);
		fieldOperand2 = new JTextField(8);

		buttonRechnen = new JButton("Rechnen!");
		buttonRechnen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println("ICH WURDE GEKLICKT!");
				int op1 = Integer.parseInt(fieldOperand1.getText());
				int op2 = Integer.parseInt(fieldOperand2.getText());

				int ergebnis;

				if(operator.equals("+")) {
					ergebnis = op1 + op2;
				} 
				else if(operator.equals("-")) {
					ergebnis = op1 - op2;
				}
				else if(operator.equals("/")) {
					ergebnis = op1 / op2;
				}
				else if(operator.equals("*")) {
					ergebnis = op1 * op2;
				}
				else {
					ergebnis = Integer.MAX_VALUE;
				}
				
				JOptionPane.showMessageDialog(GuiTaschenrechner.this, "Ergebnis: " + ergebnis, "BERECHNET!", JOptionPane.INFORMATION_MESSAGE);
				
				fieldOperand1.setText(" ");
				fieldOperand2.setText(" ");
			}	
		});

	}
}
