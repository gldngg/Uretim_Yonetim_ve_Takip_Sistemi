package üys;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class AdminMenu extends JFrame{
	
	private JPanel contentPane;
	private JButton raporlar;
	private JButton makineGirisi;
	private JButton planlama;
	private JButton siparisGirisi;
	private JLabel lblRaporlar;
	private JLabel lblMakineGirisi;
	private JLabel lblPlanlama;
	private JLabel lblSiparisGirisi;
	
	public static void main (String[] arg) {
		AdminMenu menu = new AdminMenu();
		menu.setVisible(true);
	}
	
	public AdminMenu() {
		
		
		setTitle("ÜRETİM YÖNETİM SİSTEMİ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setSize(1100,760);
		setLocationRelativeTo(null);
		
		setResizable(false);
		setMinimumSize(getSize());
		setMaximumSize(getSize());
		
		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(142,155,213));
		setContentPane(contentPane);
		
		JPanel headerPanel = new JPanel();
		headerPanel.setLayout(null);
		headerPanel.setBackground(new Color(63,81,181));
		headerPanel.setBounds(0,0,1100,100);
		contentPane.add(headerPanel);
		
		JLabel lblTitle = new JLabel("Hoşgeldiniz, "+ "Admin!");
		lblTitle.setBounds(0,25,1100,50);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		headerPanel.add(lblTitle);
		
		
		raporlar = new JButton();
		raporlar.setBounds(75,200,200,200);
		ImageIcon raporlarIcon = new ImageIcon(getClass().getResource("raporlar.jpg"));
		raporlar.setIcon(raporlarIcon);
		contentPane.add(raporlar);
		
		makineGirisi = new JButton();
		makineGirisi.setBounds(325,200,200,200);
		ImageIcon makineGirisiIcon = new ImageIcon(getClass().getResource("makineGirisi.jpg"));
		makineGirisi.setIcon(makineGirisiIcon);
		contentPane.add(makineGirisi);
				
		planlama = new JButton();
		planlama.setBounds(575,200,200,200);
		ImageIcon planlamaIcon = new ImageIcon(getClass().getResource("planlama.jpg"));
		planlama.setIcon(planlamaIcon);
		contentPane.add(planlama);
		
		siparisGirisi = new JButton();
		siparisGirisi.setBounds(825,200,200,200);
		ImageIcon siparisGirisiIcon = new ImageIcon(getClass().getResource("siparisGirisi.jpg"));
		siparisGirisi.setIcon(siparisGirisiIcon);
		contentPane.add(siparisGirisi);
		
		lblRaporlar = new JLabel("Raporlar");
		lblRaporlar.setBounds(75,400,200,75);
		lblRaporlar.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblRaporlar.setHorizontalAlignment(SwingConstants.CENTER);
		lblRaporlar.setForeground(new Color(63,81,181));
		contentPane.add(lblRaporlar);
		
		lblMakineGirisi = new JLabel("Makine Girişi");
		lblMakineGirisi.setBounds(325,400,200,75);
		lblMakineGirisi.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblMakineGirisi.setForeground(new Color(63,81,181));
		lblMakineGirisi.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblMakineGirisi);
		
		lblPlanlama = new JLabel("Planlama");
		lblPlanlama.setBounds(575,400,200,75);
		lblPlanlama.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblPlanlama.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlanlama.setForeground(new Color(63,81,181));
		contentPane.add(lblPlanlama);
		
		lblSiparisGirisi = new JLabel("Sipariş Girişi");
		lblSiparisGirisi.setBounds(825,400,200,75);
		lblSiparisGirisi.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblSiparisGirisi.setForeground(new Color(63,81,181));
		lblSiparisGirisi.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblSiparisGirisi);
		
		/*
		raporlar.addActionListener(e -> {
			Raporlar rapor = new Raporlar();
			rapor.setVisible(true);
			this.dispose();
		});*/
		
		makineGirisi.addActionListener(e -> {
			MakineGirisEkrani makine = new MakineGirisEkrani();
			makine.setVisible(true);
			this.dispose();
		});
		
		/*
		planlama.addActionListener(e -> {
			Planlama planlama = new Planlama();
			planlama.setVisible(true);
			this.dispose();
		});
		
		siparisGirisi.addActionListener(e -> {
			SiparisGirisEkrani siparis = new SiparisGirisEkrani();
			siparis.setVisible(true);
			this.dispose();
			
		});*/
		
	}

}
