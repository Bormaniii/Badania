import java.awt.Color;

import javax.swing.*;

/*Program do badania psychologicznego
 * Tworzy on osobne pliki tekstowe, aby rozróżnić badających oraz
 * zapisuje wszystkie ich wybory.
*/
public class Main {
	static JFrame badania;
	static String badany;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

	
	public Main() {
		badania = new JFrame("Badania");
		badania.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		badania.setUndecorated(true);
		badania.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		badania.setBackground(Color.GREEN);
		
		wybor wybor = new wybor();
		
	}
}
