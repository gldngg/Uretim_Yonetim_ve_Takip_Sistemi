package üys;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Planlama extends JFrame{
	
	private JPanel contentPane;

	public static void main (String[] args) {
		Planlama planlama = new Planlama();
		planlama.setVisible(true);
	}
	
	public Planlama() {
		
		setTitle("ÜRETİM YÖNETİM SİSTEMİ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setSize(1100,760);
		setResizable(false);
		setLocationRelativeTo(null);
		
		setMinimumSize(getSize());
		setMaximumSize(getSize());
		
		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(142,155,213));
		setContentPane(contentPane);
		
		JPanel panelUstMenu = new JPanel();
		panelUstMenu.setBounds(30, 20, 1040, 70);
		panelUstMenu.setBackground(new Color(63, 81, 181));
		panelUstMenu.setLayout(null);
		contentPane.add(panelUstMenu);
		
		JLabel lblLogo = new JLabel("LOGO");
		lblLogo.setBounds(10,18,40,28);
		panelUstMenu.add(lblLogo);
		
		JLabel lblBaslık = new JLabel("Üretim Planlama");
		lblBaslık.setBounds(50, 18, 200, 30);
		lblBaslık.setForeground(Color.WHITE);
		lblBaslık.setFont(new Font("Tahoma", Font.BOLD, 20));
		panelUstMenu.add(lblBaslık);
		
		JButton btnAnaSayfa = new JButton("Ana Sayfa");
		btnAnaSayfa.setBounds(250,20,100,25);
		btnAnaSayfa.setForeground(Color.WHITE);
		btnAnaSayfa.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAnaSayfa.setContentAreaFilled(false);
		btnAnaSayfa.setBorderPainted(false);
		btnAnaSayfa.setFocusPainted(false);
		panelUstMenu.add(btnAnaSayfa);
		
		JButton btnDurus_Kayıp = new JButton("Duruş/Kayıp");
		btnDurus_Kayıp.setBounds(360,20,120,25);
		btnDurus_Kayıp.setForeground(Color.WHITE);
		btnDurus_Kayıp.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnDurus_Kayıp.setContentAreaFilled(false);
		btnDurus_Kayıp.setBorderPainted(false);
		btnDurus_Kayıp.setFocusPainted(false);
		panelUstMenu.add(btnDurus_Kayıp);
		
		JButton btnMakineGirisi = new JButton("Makine Girişi");
		btnMakineGirisi.setBounds(480,20,120,25);
		btnMakineGirisi.setForeground(Color.WHITE);
		btnMakineGirisi.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnMakineGirisi.setContentAreaFilled(false);
		btnMakineGirisi.setBorderPainted(false);
		btnMakineGirisi.setFocusPainted(false);
		panelUstMenu.add(btnMakineGirisi);
		
		JButton btnSiparis = new JButton("Sipariş");
		btnSiparis.setBounds(600,20,100,25);
		btnSiparis.setForeground(Color.WHITE);
		btnSiparis.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSiparis.setContentAreaFilled(false);
		btnSiparis.setBorderPainted(false);
		btnSiparis.setFocusPainted(false);
		panelUstMenu.add(btnSiparis);
		
		JButton btnRapor = new JButton("Rapor");
		btnRapor.setBounds(700,20,80,25);
		btnRapor.setForeground(Color.WHITE);
		btnRapor.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnRapor.setContentAreaFilled(false);
		btnRapor.setBorderPainted(false);
		btnRapor.setFocusPainted(false);
		panelUstMenu.add(btnRapor);
		
		JLabel lblUser = new JLabel("Ad Soyad");
		lblUser.setBounds(840,20,100,25);
		lblUser.setForeground(Color.WHITE);
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelUstMenu.add(lblUser);
		
		JButton btnCikis = new JButton("ÇIKIŞ");
		btnCikis.setBounds(950,18,70,30);
		btnCikis.setForeground(Color.BLACK);
		btnCikis.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelUstMenu.add(btnCikis);
		
		JPanel panelTakvim = new JPanel();
		panelTakvim.setBounds(30,100,1040,250);
		panelTakvim.setBackground(Color.WHITE);
		panelTakvim.setLayout(null);
		contentPane.add(panelTakvim);
		
		JPanel panelIsEmri = new JPanel();
		panelIsEmri.setBounds(30,370,650,300);
		panelIsEmri.setBackground(Color.WHITE);
		panelIsEmri.setLayout(null);
		contentPane.add(panelIsEmri);
		
		JPanel panelKaynakKullanımı = new JPanel();
		panelKaynakKullanımı.setBounds(700,370,370,300);
		panelKaynakKullanımı.setBackground(Color.WHITE);
		panelKaynakKullanımı.setLayout(null);
		contentPane.add(panelKaynakKullanımı);
		
	}
}
