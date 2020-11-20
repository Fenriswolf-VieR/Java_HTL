import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class complex_gui extends JFrame {

	private JPanel contentPane;
	private JTextField txt_re;
	private JTextField txt_im;
	private complex c1,c2;
	boolean isKart;
	enum operation {NOP, ADD, SUB, MUL, DIV}; //neuer Datentyp
	private operation lastOP;
	private final ButtonGroup buttonGroup_vec_kart = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					complex_gui frame = new complex_gui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	 
	private void get_Value() {
		if (isKart) {
            double re = Double.parseDouble(txt_re.getText());
            double im = Double.parseDouble(txt_im.getText());
            c2.set(re, im);
        } else {
            double val = Double.parseDouble(txt_re.getText());
            double arg = Double.parseDouble(txt_im.getText());
            c2.setVal(val, arg);
        }
	}
	
	private void set_Value() {
		txt_re.setText(String.valueOf(c1.getRe()));
		txt_im.setText(String.valueOf(c1.getIm()));
	}
	
	private void opFunc() {
		get_Value();
		switch(lastOP) {
		case ADD:
			c1.add2(c2);
			break;
		case SUB:
			c1.sub2(c2);
			break;
		case MUL:
			c1.mul2(c2);
			break;
		case DIV:
			c1.div2(c2);
			break;
		case NOP:
			break;
		default:
			c1 = new complex(c2);
			break;
		}
		set_Value();
	}

	/**
	 * Create the frame.
	 */
	public complex_gui() {
		c1 = new complex();
		c2 = new complex();
		//isKart = rdbtnKart.isSelected();
		lastOP= operation.NOP;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(27, 86, 279, 86);
		contentPane.add(panel);
		
		JButton btn_plus = new JButton("+");
		btn_plus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opFunc();
				lastOP = operation.ADD;
			}
		});
		panel.add(btn_plus);
		
		JButton btn_minus = new JButton("-");
		btn_minus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opFunc();
				lastOP = operation.SUB;
			}
		});
		panel.add(btn_minus);
		
		JButton btn_mal = new JButton("*");
		btn_mal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opFunc();
				lastOP = operation.MUL;
			}
		});
		panel.add(btn_mal);
		
		JButton btn_dividiert = new JButton("/");
		btn_dividiert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opFunc();
				lastOP = operation.DIV;
			}
		});
		panel.add(btn_dividiert);
		
		JButton btn_calculate = new JButton("=");
		btn_calculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opFunc();
				lastOP = operation.NOP;
			}
		});
		panel.add(btn_calculate);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(27, 11, 296, 63);
		contentPane.add(panel_1);
		
		txt_re = new JTextField();
		txt_re.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				super.focusGained(e);
				txt_re.selectAll();
			}
		});
		txt_re.setText("0");
		panel_1.add(txt_re);
		txt_re.setColumns(10);
		
		JLabel lbl_re_jm = new JLabel("+");
		panel_1.add(lbl_re_jm);
		
		txt_im = new JTextField();
		txt_im.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				super.focusGained(e);
				txt_im.selectAll();
			}
		});
		txt_im.setText("0");
		panel_1.add(txt_im);
		txt_im.setColumns(10);
		
		JLabel lbl_j = new JLabel("j");
		panel_1.add(lbl_j);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(333, 11, 91, 75);
		contentPane.add(panel_2);
		
		JRadioButton rdbtnKart = new JRadioButton("karthesisch");
		rdbtnKart.setSelected(true);
		rdbtnKart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				get_Value();
				c1 = new complex(c2);
				isKart = true;
				lbl_re_jm.setText("+");
				lbl_j.setText("j");
				set_Value();
			}
		});
		buttonGroup_vec_kart.add(rdbtnKart);
		panel_2.add(rdbtnKart);
		
		JRadioButton rdbtnVektor = new JRadioButton("vektor");
		
		rdbtnVektor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c1 = new complex(c2);
				isKart = false;
				lbl_re_jm.setText("<");
				lbl_j.setText("°");
				set_Value();
			}
		});
		buttonGroup_vec_kart.add(rdbtnVektor);
		panel_2.add(rdbtnVektor);
	}
}
