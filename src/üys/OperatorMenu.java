package üys;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class OperatorMenu extends JFrame{
	
	JPanel contentPane = new JPanel();
	
	public OperatorMenu() {
				//ana ekran
				setTitle("Operatör Menü");
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setBounds(100, 100, 900, 600);
				contentPane.setBackground(new Color(142, 155, 213));
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				setContentPane(contentPane);
				contentPane.setLayout(null);
				
				//üst mavi panel
				JPanel headerPanel = new JPanel();
				headerPanel.setBackground(new Color(63, 81, 181));
				headerPanel.setBounds(0, 0, 900, 60);
				contentPane.add(headerPanel);
				headerPanel.setLayout(null);
				
				JLabel lblTitle = new JLabel("Hoşgeldiniz, [Operatör]");
				lblTitle.setBackground(new Color(255, 255, 255));
				lblTitle.setBounds(0, 11, 900, 40);
				lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
				lblTitle.setForeground(new Color(255, 255, 255));
				lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
				headerPanel.add(lblTitle);
				
				ImageIcon raporIcon = new ImageIcon("RaporlarIcon.png");//button 1
				Image image1 = raporIcon.getImage();
				Image newimg1 = image1.getScaledInstance(125, 100,  java.awt.Image.SCALE_SMOOTH); 
				raporIcon = new ImageIcon(newimg1);
				JButton btnRapor = new JButton(raporIcon);
				btnRapor.setFont(new Font("Tahoma", Font.BOLD, 14));
				btnRapor.setForeground(new Color(255, 255, 255));
				btnRapor.setText("Raporlar");
				btnRapor.setBorderPainted(false);
				btnRapor.setBackground(new Color(63, 81, 181));
				btnRapor.setVerticalTextPosition(SwingConstants.BOTTOM);
				btnRapor.setHorizontalTextPosition(SwingConstants.CENTER);
				btnRapor.setBounds(50, 200, 175, 150);
				contentPane.add(btnRapor);
				/*btnRapor.addActionListener(e -> 
				Raporlar r = new Raporlar();
				r.setVisible(true);
				this.dispose(););*/
				
				ImageIcon makineIcon = new ImageIcon("MakineGirişIcon.png");//button 2
				Image image2 = makineIcon.getImage();
				Image newimg2 = image2.getScaledInstance(125, 100,  java.awt.Image.SCALE_SMOOTH); 
				makineIcon = new ImageIcon(newimg2);
				JButton btnMakine = new JButton(makineIcon);
				btnMakine.setVerticalTextPosition(SwingConstants.BOTTOM);
				btnMakine.setText("Makine Girişi");
				btnMakine.setHorizontalTextPosition(SwingConstants.CENTER);
				btnMakine.setForeground(Color.WHITE);
				btnMakine.setFont(new Font("Tahoma", Font.BOLD, 14));
				btnMakine.setBorderPainted(false);
				btnMakine.setBackground(new Color(63, 81, 181));
				btnMakine.setBounds(255, 200, 175, 150);
				contentPane.add(btnMakine);
				/*btnMakine.addActionListener(e -> 
				MakineGirisEkrani m = new MakineGirisEkrani();
				r.setVisible(true);
				this.dispose(););*/
				
				ImageIcon planlamaIcon = new ImageIcon("PlanlamaIcon.png");//button 3
				Image image3 = planlamaIcon.getImage();
				Image newimg3 = image3.getScaledInstance(125, 100,  java.awt.Image.SCALE_SMOOTH); 
				planlamaIcon = new ImageIcon(newimg3);
				JButton btnPlanlama = new JButton(planlamaIcon);
				btnPlanlama.setVerticalTextPosition(SwingConstants.BOTTOM);
				btnPlanlama.setText("Planlama");
				btnPlanlama.setHorizontalTextPosition(SwingConstants.CENTER);
				btnPlanlama.setForeground(Color.WHITE);
				btnPlanlama.setFont(new Font("Tahoma", Font.BOLD, 14));
				btnPlanlama.setBorderPainted(false);
				btnPlanlama.setBackground(new Color(63, 81, 181));
				btnPlanlama.setBounds(460, 200, 175, 150);
				contentPane.add(btnPlanlama);
				/*btnPlanlama.addActionListener(e -> 
				Planlama p = new Planlama();
				p.setVisible(true);
				this.dispose(););*/
				
				ImageIcon durusIcon = new ImageIcon("DurusKayıpIcon.png");//button 4
				Image image4 = durusIcon.getImage();
				Image newimg4 = image4.getScaledInstance(125, 100,  java.awt.Image.SCALE_SMOOTH); 
				durusIcon = new ImageIcon(newimg4);
				JButton btnDurusKayip = new JButton(durusIcon);
				btnDurusKayip.setVerticalTextPosition(SwingConstants.BOTTOM);
				btnDurusKayip.setText("<html><center>Duruş/Kayıp<br>Yönetimi</center></html>");
				btnDurusKayip.setHorizontalTextPosition(SwingConstants.CENTER);
				btnDurusKayip.setForeground(Color.WHITE);
				btnDurusKayip.setFont(new Font("Tahoma", Font.BOLD, 14));
				btnDurusKayip.setBorderPainted(false);
				btnDurusKayip.setBackground(new Color(63, 81, 181));
				btnDurusKayip.setBounds(665, 200, 175, 150);
				contentPane.add(btnDurusKayip);
				/*btnDurusKayip.addActionListener(e -> 
				DuruşKayıp d = new DuruşKayıp();
				d.setVisible(true);
				this.dispose(););*/
		
	}
}
