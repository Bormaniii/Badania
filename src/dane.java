import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class dane {
	static int nr = 1;

	
	public dane() {
		Font czcionka = new Font("Serif", Font.BOLD, 44);
		Font czcionka1 = new Font("Serif", Font.BOLD, 34);
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBackground(Color.WHITE);
		JButton zatwierdz = new JButton("Zatwierdź");
		zatwierdz.setPreferredSize(new Dimension(200, 75));
		zatwierdz.setFont(czcionka1);
		JLabel wiek = new JLabel("Wybierz, proszę swój wiek:");
		wiek.setFont(czcionka);
		
		
		SpinnerModel wiek_wyb = new SpinnerNumberModel(20, 7, 99, 1);
		JSpinner spinner = new JSpinner(wiek_wyb);
		spinner.setFont(czcionka);
		
		JLabel plec = new JLabel("Wskaż, proszę swoją płeć:");
		plec.setFont(czcionka);
		
		JRadioButton kobieta = new JRadioButton("Kobieta");
		kobieta.setFont(czcionka);
		JRadioButton mezczyzna = new JRadioButton("Mężczyzna");
		mezczyzna.setFont(czcionka);
		ButtonGroup group = new ButtonGroup();
		group.add(mezczyzna);
		group.add(kobieta);
	
		JPanel od_wieku = new JPanel(new GridLayout(2,1));
		od_wieku.add(wiek);
		od_wieku.add(spinner);
		od_wieku.setVisible(true);
		
		JPanel od_plci = new JPanel(new GridLayout(2,1));
		JPanel od_plci1 = new JPanel(new GridLayout(1,2));
		od_plci.add(plec);
		od_plci1.add(kobieta);
		od_plci1.add(mezczyzna);
		od_plci.add(od_plci1);
		od_plci.setVisible(true);
		od_plci1.setVisible(true);
		
		GridBagConstraints ogranicznik = new GridBagConstraints();
		ogranicznik.insets = new Insets(10, 10, 10, 10);
		ogranicznik.gridy = 0;
		ogranicznik.gridx = 0;
		ogranicznik.weightx = 1;
		ogranicznik.weighty = 1;
		panel.add(od_wieku, ogranicznik);
		ogranicznik.gridy = 1;
		panel.add(od_plci, ogranicznik);
		ogranicznik.gridx = 2;
		ogranicznik.gridy = 2;
		panel.add(zatwierdz, ogranicznik);
		panel.setVisible(true);
		
		Main.badania.add(panel);
		
		zatwierdz.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//sprawdza czy zaznaczone
				if((kobieta.isSelected()) || (mezczyzna.isSelected())) {
					nr = 1;
					try {
						wczytaj_nr();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println(nr);
					Integer wiek1 = (Integer)spinner.getValue();
					String plec1;
					if(kobieta.isSelected()) {
						plec1 = "K";					
					}else {
						plec1 = "M";
					}
					

					panel.setVisible(false);
					Main.badania.remove(panel);
					test test = new test(nr, wiek1, plec1);
				}else {
					JOptionPane.showMessageDialog(null, "Nie zaznaczyłeś żadnej płci.", "BLAD", JOptionPane.INFORMATION_MESSAGE);
				}
			}			
		});
		}
	
	void wczytaj_nr () throws FileNotFoundException {
		if(wybor.wrobion) {
			File wrobieni = new File("Wrobieni.txt");
			if(wrobieni.exists()) {
				Scanner in = new Scanner(wrobieni);
				if(in.hasNextLine()) {
					nr = 1;
					while(in.hasNextLine() && in.nextLine()!="") 
						nr++;				
				}
				in.close();
			}
		}else {
			File niewrobieni = new File("Niewrobieni.txt");
			if(niewrobieni.exists()) {
				Scanner in = new Scanner(niewrobieni);
				if(in.hasNextLine()) {
					nr = 1;
					while(in.hasNextLine() && in.nextLine()!="") 
						nr++;				
				}
				in.close();
			}
		}
	}
}
