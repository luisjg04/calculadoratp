package calculadora;

import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			Calculadora c = new Calculadora();
		});

	}

}
