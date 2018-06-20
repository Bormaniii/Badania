import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class test {
	static ImageIcon icon;
	static int przyklady_img = 1;
	static int przyklady = 0;
	private String[] odpowiedzi = new String[8];  //ilosc przykladow
	static JLabel[][] dane_przykl = new JLabel[2][4];
	private boolean koniec = false;
	

	
	public test(int nr, int wiek, String plec) {
		// komponenty
		Font czcionka = new Font("Serif", Font.BOLD, 44);
		JPanel panel = new JPanel(new GridBagLayout());
		JPanel zast = new JPanel(new BorderLayout());
		panel.setBackground(Color.WHITE);
		zast.setBackground(Color.WHITE);
		JLabel op1 = new JLabel();
		JLabel op2 = new JLabel();
		JLabel przykl = new JLabel();
		JButton bezpieczna = new JButton("Wybierz");
		bezpieczna.setBackground(null);
		bezpieczna.setFont(czcionka);
		bezpieczna.setPreferredSize(new Dimension(200, 75));
		JButton ryzykowna = new JButton("Wybierz");
		ryzykowna.setBackground(null);
		ryzykowna.setPreferredSize(new Dimension(200, 75));
		ryzykowna.setFont(czcionka);
		JButton zatwierdz = new JButton("Zatwierdz");
		zatwierdz.setFont(czcionka);
		JPanel pane_op_1 = new JPanel(new GridBagLayout());
		pane_op_1.setVisible(true);
		JPanel pane_op_2 = new JPanel(new GridBagLayout());
		pane_op_2.setVisible(true);

		
		for(int i=0;i<2;i++) {
			for(int j=0;j<4;j++) {
				dane_przykl[i][j] = new JLabel();
				dane_przykl[i][j].setFont(czcionka);
			}
		}		
		
		dane_do_przykladow();
		
		//dodawanie komponentow + zmiana polozenia ich
		GridBagConstraints ogranicznik = new GridBagConstraints();
		ogranicznik.insets = new Insets(0, 50, 0, 50);		
		ogranicznik.gridy = 0;
		ogranicznik.gridx = 0;
		ogranicznik.weightx = 1;
		ogranicznik.weighty = 1;
		pane_op_1.add(dane_przykl[0][0], ogranicznik);
		pane_op_2.add(dane_przykl[1][0], ogranicznik);
		ogranicznik.gridx = 1;
		pane_op_1.add(dane_przykl[0][1], ogranicznik);
		pane_op_2.add(dane_przykl[1][1], ogranicznik);
		ogranicznik.gridy = 1;
		pane_op_1.add(dane_przykl[0][3], ogranicznik);
		pane_op_2.add(dane_przykl[1][3], ogranicznik);
		ogranicznik.gridx = 0;
		pane_op_1.add(dane_przykl[0][2], ogranicznik);
		pane_op_2.add(dane_przykl[1][2], ogranicznik);
		

		ogranicznik.insets = new Insets(10, 10, 10, 10);		
		ogranicznik.gridy = 0;
		obraz("OPCJA 1", 271, 67);
		op1.setIcon(icon);
		panel.add(op1, ogranicznik);
		ogranicznik.gridx = 2;
		obraz("OPCJA 2", 271, 67);
		op2.setIcon(icon);
		panel.add(op2, ogranicznik);
		ogranicznik.gridy = 1;
		ogranicznik.gridx = 0;
		panel.add(pane_op_1, ogranicznik);
		ogranicznik.gridy = 2;
		panel.add(bezpieczna, ogranicznik);
		ogranicznik.gridx = 2;
		panel.add(ryzykowna, ogranicznik);
		ogranicznik.gridy = 1;
		panel.add(pane_op_2, ogranicznik);
		ogranicznik.gridx = 1;
		ogranicznik.gridy = 3;
		zast.add(panel, BorderLayout.CENTER);
		zast.add(zatwierdz, BorderLayout.SOUTH);
		
		zast.setVisible(true);
		panel.setVisible(true);
		Main.badania.add(zast);
		
		// info
		JOptionPane.showMessageDialog(null, "Za chwilę przystąpisz do badania dotyczącego decyzji ekonomicznych.\n" + 
				"Wyobraź sobie, że operujesz realnymi pieniędzmi, a każde podjęte działanie ma wpływ na kwotę,\n" + 
				"którą możesz zarobić. Klikając przycisk \"OK\" wyrażasz zgodę na udział w eksperymencie.", "WITAJ!", JOptionPane.INFORMATION_MESSAGE);
		

		bezpieczna.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				bezpieczna.setBackground(Color.GREEN);
				ryzykowna.setBackground(null);
				
			}			
		});
		

		ryzykowna.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ryzykowna.setBackground(Color.GREEN);
				bezpieczna.setBackground(null);
			}			
		});
		

		zatwierdz.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if((ryzykowna.getBackground()==Color.GREEN) || (bezpieczna.getBackground()==Color.GREEN)) {
					System.out.println(przyklady);
					if(ryzykowna.getBackground()==Color.GREEN)
						odpowiedzi[przyklady] = "RYZYKOWNIE";
					else
						odpowiedzi[przyklady] = "BEZPIECZNIE";
					
					ryzykowna.setBackground(null);
					bezpieczna.setBackground(null);
					przyklady++;
					przyklady_img++;
					
					//zakonczenie testu
					if((przyklady<8) && (!koniec)) {			
					dane_do_przykladow();
					}else {
						koniec = true;
						try {
							zapis(wiek, plec);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						// info
						JOptionPane.showMessageDialog(null, "Test zostal zakonczony", "Informacje", JOptionPane.INFORMATION_MESSAGE);
					}
				}else {
					// info
					JOptionPane.showMessageDialog(null, "Nie zostala wybrana zadna opcja", "Informacje", JOptionPane.INFORMATION_MESSAGE);
				}	
					if(koniec) {
						System.exit(0);
					}
				
			}			
		});
	}
	
	//wczytanie obrazu
	private void obraz(String obraz, int szer, int wys) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(obraz+".png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(szer, wys, Image.SCALE_SMOOTH);
		icon = new ImageIcon(dimg);
	}
	
	private void zapis(int wiek, String plec) throws IOException {
		if(wybor.wrobion) {
			File wrobieni = new File("Wrobieni.txt");
			FileWriter fw = new FileWriter(wrobieni, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.println(dane.nr+". "+wiek+" "+plec+".  1."+odpowiedzi[0]+" 2."+odpowiedzi[1]+" 3."+odpowiedzi[2]+" 4."+odpowiedzi[3]+" 5."+odpowiedzi[4]+" 6."+odpowiedzi[5]+" 7."+odpowiedzi[6]+" 8."+odpowiedzi[7]);
			pw.close();
			fw.close();
		}else {
			File niewrobieni = new File("Niewrobieni.txt");
			FileWriter fw = new FileWriter(niewrobieni, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.println(dane.nr+". "+wiek+" "+plec+".  1."+odpowiedzi[0]+" 2."+odpowiedzi[1]+" 3."+odpowiedzi[2]+" 4."+odpowiedzi[3]+" 5."+odpowiedzi[4]+" 6."+odpowiedzi[5]+" 7."+odpowiedzi[6]+" 8."+odpowiedzi[7]);
			pw.close();
			fw.close();
			
		}
	}
	
	void dane_do_przykladow() {
		switch (przyklady){
		case 0:
			dan("12","88","555","70","99","1","1","401");
		break;
		case 1:
			dan("10","90","100","4","99","1","5","145");
		break;	
		case 2:
			dan("10","90","113","7","98","2","4","54");
		break;
		case 3:
			dan("4","96","283","4,5","95","5","2","102");
		break;	
		case 4:
			dan("6","94","99","1","99","1","3","203");
		break;
		case 5:
			dan("3","97","490","10","97","3","2","102");
		break;	
		case 6:
			dan("5","95","5002","258","97","3","1,1","131,1");
		break;
		case 7:
			dan("6","94","3000","190","99","1","0,1","130,1");
		break;	
		}
	}
	
	void dan(String pier_proc, String drug_proc, String pierw_kwot, String drug_kwot, String pierw_proc1, String drug_proc1, String pierw_kwot1, String drug_kwot1) {
		
		
		String wygr = "% szans<br/> na wygranie:</html>";
		String przeg = "% szans<br/> na utratę:</html>";
		dane_przykl[0][0].setText("<html>"+pier_proc+wygr);
		dane_przykl[0][1].setText("<html>"+drug_proc+przeg);
		dane_przykl[0][2].setText(pierw_kwot+"zl");
		dane_przykl[0][3].setText(drug_kwot+"zl");
		dane_przykl[1][0].setText("<html>"+pierw_proc1+wygr);
		dane_przykl[1][1].setText("<html>"+drug_proc1+wygr);
		dane_przykl[1][2].setText(pierw_kwot1+"zl");
		dane_przykl[1][3].setText(drug_kwot1+"zl");	
	}
}

