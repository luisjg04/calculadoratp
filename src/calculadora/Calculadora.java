package calculadora;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JButton;

public class Calculadora extends JFrame {
	
	private static final int MAX_DIGITS = 30;
	private static final String[] SUPPORTED_OPS = {"*", "/", "+", "-", "0", "="};
	
	private JTextField expression;
	private String lastOp;
	private String firstNum;
	
	
	
	public Calculadora() {
		super("Calculadora");
		
		lastOp = "";
		firstNum = "";
		
		initGUI();
	}
	
	private double exec(String a, String b, String op) {
		double lh = Double.parseDouble(a);
		double rh = Double.parseDouble(b);
		double res = 0;
		
		switch (op) {
		case "+":
			res += lh+rh;
			break;
		case "*":
			res += lh*rh;
			break;
		case "-":
			res += lh-rh;
			break;
		case "/":
			res += lh/rh;
			break;
		}
		
		return res;
	}
	
	
	
	private JButton createButton(String text) {
		JButton btn = new JButton(text);
		
		btn.addActionListener((e) -> {
			String currExpression = expression.getText();
			expression.setText(currExpression+e.getActionCommand()); // e.getActionCommand() me devuelve el texto del boton
		});
		
		return btn;
	}
	
	private JButton createOpButton(String text) {
		JButton btn = new JButton(text);
		
		btn.addActionListener((e) -> {
			if (lastOp == "") {
				firstNum = expression.getText();
				expression.setText(expression.getText() + e.getActionCommand());
				lastOp = e.getActionCommand();
			} else { // Comprobar si es un num o op
				String lastNum = expression.getText().split(Pattern.quote(lastOp))[1];
				double res = this.exec(firstNum, lastNum, lastOp);
				firstNum = ""+res;
				
				if(e.getActionCommand() == "=") {
					expression.setText(""+res);
					lastOp = "";
				} else {
					expression.setText(""+res+e.getActionCommand());
					lastOp = e.getActionCommand();
				}
			}
			
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
	
		
		JButton eraseBtn = new JButton("<-");
		eraseBtn.setToolTipText("Erase");
		eraseBtn.addActionListener((e) -> expression.setText(""));
		
		
		/*expression.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				e.getKeyChar();
				eraseBtn.getActionListeners()[0].actionPerformed(new ActionEvent(e, 0, "C"));
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});*/
		
		
		JPanel display = new JPanel();
		display.add(expression);
		display.add(eraseBtn);
		
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
		
		
		
		for (String op : SUPPORTED_OPS) {
			if (op == "0") operations.add(this.createButton(op));
			else operations.add(this.createOpButton(op));
		}
		
		
		buttonPanel.add(numbers);
		buttonPanel.add(operations);
		
		
		
		mainPanel.add(display, BorderLayout.PAGE_START);
		mainPanel.add(buttonPanel, BorderLayout.CENTER);
		
		
		
		this.setLocation(200, 200);
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
