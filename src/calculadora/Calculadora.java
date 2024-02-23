package calculadora;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JButton;

public class Calculadora extends JFrame {
	
	private static int MAX_DIGITS = 30;
	public Calculadora() {
		super("Calculadora");
		initGUI();
	}
	
	private JTextField expression;
	
	private JButton createButton(String text) {
		JButton btn = new JButton(text);
		
		btn.addActionListener((e) -> {
			String currExpression = expression.getText();
			expression.setText(currExpression+btn.getText());
		});
		
		return btn;
	}
	
	public void initGUI() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);
		
		// Display
		expression = new JTextField(MAX_DIGITS);
		expression.setFont(new Font("SansSerif", Font.BOLD, 20));
		expression.setEnabled(false);
		
		
		JButton erasebtn = new JButton("<-");
		erasebtn.setToolTipText("Erase");
		
		JPanel display = new JPanel();
		display.add(expression);
		display.add(erasebtn);
		
		// Button Panel
		JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
		
		JPanel numbers = new JPanel(new GridLayout(3, 3));
		for (int i = 3; i > 0; i--) {
			for (int j = 3; j>0; j--) {
				numbers.add(createButton(""+(3*i-(j-1))));
			}
		}
		
		JPanel operations = new JPanel(new GridLayout(3, 2));
		
		buttonPanel.add(numbers);
		buttonPanel.add(operations);
		
		
		mainPanel.add(display);
		mainPanel.add(buttonPanel);
		
		this.setLocation(200, 200);
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
