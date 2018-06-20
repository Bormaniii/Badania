import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;

public class wybor {
	static boolean wrobion = false;

	public wybor() {
		Font czcionka = new Font("Serif", Font.BOLD, 66);
		
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBackground(Color.WHITE);
		JButton wrobiony = new JButton("P#1");
		wrobiony.setFont(czcionka);
		JButton niewrobiony = new JButton("P#2");
		niewrobiony.setFont(czcionka);
		
		//dodaje opcje wybrania, kto podchodzi do badania
		GridBagConstraints ogranicznik = new GridBagConstraints();
		ogranicznik.insets = new Insets(10, 10, 10, 10);
		ogranicznik.gridy = 0;
		ogranicznik.gridx = 0;
		ogranicznik.weightx = 1;
		ogranicznik.weighty = 1;
		panel.add(wrobiony, ogranicznik);
		
		// druga opcja
		ogranicznik.gridx = 1;
		panel.add(niewrobiony, ogranicznik);
		panel.setVisible(true);
		
		Main.badania.add(panel);
		Main.badania.setVisible(true);
		
		

		wrobiony.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				wrobion = true;
				panel.setVisible(false);
				Main.badania.remove(panel);
				dane dane = new dane();
			}			
		});

		niewrobiony.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				wrobion = false;
				panel.setVisible(false);
				Main.badania.remove(panel);
				dane dane = new dane();
			}			
		});
		
	}
}
