package üys;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MakineGirisEkrani extends JFrame {
	
	

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtKapasite;
	private JTextField txtBakimPeriyodu;
	private JTable table;
	private DefaultTableModel model;

	private int secilenSatir = -1;
	private boolean duzenlemeModu = false;

	private JComboBox<String> cmbMakineTipi;
	private JComboBox<String> cmbMakineKodu;
	private JComboBox<String> cmbBolum;
	private JComboBox<String> cmbDurum;
	private JComboBox<String> cmbLokasyon;

	private JButton btnKaydet;
	private JButton btnTemizle;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				MakineGirisEkrani frame = new MakineGirisEkrani();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public  MakineGirisEkrani() {
		setResizable(false);
		setTitle("Makine Girişi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 700);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(142, 155, 213));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);


		JPanel panelUstMenu = new JPanel();
		panelUstMenu.setBounds(20, 20, 1040, 70);
		panelUstMenu.setBackground(new Color(63, 81, 181));
		panelUstMenu.setLayout(null);
		contentPane.add(panelUstMenu);
		
		JLabel jlb_logo = new JLabel("LOGO");
		jlb_logo.setBounds(10, 18, 40, 28);
		panelUstMenu.add(jlb_logo);
		jlb_logo.setBackground(new Color(255, 255, 255));

		JLabel lblBaslik = new JLabel("MAKİNE GİRİŞİ");
		lblBaslik.setBounds(50, 18, 200, 30);
		lblBaslik.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblBaslik.setForeground(Color.WHITE);
		panelUstMenu.add(lblBaslik);

		JButton btnAnaSayfa = new JButton("Ana Sayfa");
		btnAnaSayfa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAnaSayfa.setContentAreaFilled(false);
		btnAnaSayfa.setBorderPainted(false);
		btnAnaSayfa.setFocusPainted(false);
		btnAnaSayfa.setForeground(Color.WHITE);
		btnAnaSayfa.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAnaSayfa.setBounds(250, 20, 100, 25);
		panelUstMenu.add(btnAnaSayfa);

		JButton btnDurusKayip = new JButton("Duruş/Kayıp");
		btnDurusKayip.setContentAreaFilled(false);
		btnDurusKayip.setBorderPainted(false);
		btnDurusKayip.setFocusPainted(false);
		btnDurusKayip.setForeground(Color.WHITE);
		btnDurusKayip.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnDurusKayip.setBounds(360, 20, 120, 25);
		panelUstMenu.add(btnDurusKayip);

		JButton btnSiparis = new JButton("Sipariş");
		btnSiparis.setContentAreaFilled(false);
		btnSiparis.setBorderPainted(false);
		btnSiparis.setFocusPainted(false);
		btnSiparis.setForeground(Color.WHITE);
		btnSiparis.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSiparis.setBounds(490, 20, 90, 25);
		panelUstMenu.add(btnSiparis);

		JButton btnPlanlama = new JButton("Planlama");
		btnPlanlama.setContentAreaFilled(false);
		btnPlanlama.setBorderPainted(false);
		btnPlanlama.setFocusPainted(false);
		btnPlanlama.setForeground(Color.WHITE);
		btnPlanlama.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnPlanlama.setBounds(590, 20, 100, 25);
		panelUstMenu.add(btnPlanlama);

		JButton btnRapor = new JButton("Rapor");
		btnRapor.setContentAreaFilled(false);
		btnRapor.setBorderPainted(false);
		btnRapor.setFocusPainted(false);
		btnRapor.setForeground(Color.WHITE);
		btnRapor.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnRapor.setBounds(700, 20, 80, 25);
		panelUstMenu.add(btnRapor);

		JLabel lblKullanici = new JLabel("Ad Soyad");
		lblKullanici.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblKullanici.setForeground(Color.WHITE);
		lblKullanici.setBounds(860, 24, 90, 20);
		panelUstMenu.add(lblKullanici);

		JButton btnCikis = new JButton("ÇIKIŞ");
		btnCikis.setForeground(Color.BLACK);
		btnCikis.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCikis.setBounds(950, 18, 70, 30);
		panelUstMenu.add(btnCikis);

	
		JPanel panelForm = new JPanel();
		panelForm.setBounds(20, 100, 1040, 320);
		panelForm.setBackground(Color.WHITE);
		panelForm.setLayout(null);
		contentPane.add(panelForm);

		JPanel panelBaslik = new JPanel();
		panelBaslik.setBackground(new Color(245, 245, 245));
		panelBaslik.setBounds(0, 0, 1040, 55);
		panelBaslik.setLayout(null);
		panelForm.add(panelBaslik);

		JLabel lblFormBaslik = new JLabel("Makine Girişi");
		lblFormBaslik.setForeground(new Color(60, 60, 60));
		lblFormBaslik.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblFormBaslik.setBounds(20, 12, 200, 30);
		panelBaslik.add(lblFormBaslik);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 55, 1040, 2);
		panelForm.add(separator);

		JLabel lblMakineTipi = new JLabel("Makine Tipi:");
		lblMakineTipi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMakineTipi.setBounds(25, 75, 120, 20);
		panelForm.add(lblMakineTipi);

		JLabel lblMakineKodu = new JLabel("Makine Kodu:");
		lblMakineKodu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMakineKodu.setBounds(380, 75, 120, 20);
		panelForm.add(lblMakineKodu);

		JLabel lblKapasite = new JLabel("Çalışma Kapasitesi:");
		lblKapasite.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblKapasite.setBounds(735, 75, 150, 20);
		panelForm.add(lblKapasite);

		cmbMakineTipi = new JComboBox<>();
		cmbMakineTipi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cmbMakineTipi.setModel(new DefaultComboBoxModel<>(new String[] {
				"Karıştırma Makinesi",
				"Dolum Makinesi",
				"Kapak Kapatma Makinesi",
				"Etiketleme Makinesi",
				"Paketleme Makinesi"
		}));
		cmbMakineTipi.setBounds(25, 100, 270, 30);
		panelForm.add(cmbMakineTipi);

		cmbMakineKodu = new JComboBox<>();
		cmbMakineKodu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cmbMakineKodu.setModel(new DefaultComboBoxModel<>(new String[] {
				"KRS-01",
				"DLM-01",
				"KPK-01",
				"ETK-01",
				"PKT-01"
		}));
		cmbMakineKodu.setBounds(380, 100, 270, 30);
		panelForm.add(cmbMakineKodu);

		txtKapasite = new JTextField();
		txtKapasite.setBounds(735, 100, 270, 30);
		panelForm.add(txtKapasite);
		txtKapasite.setColumns(10);

		JLabel lblBolum = new JLabel("Bölüm:");
		lblBolum.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBolum.setBounds(25, 150, 120, 20);
		panelForm.add(lblBolum);

		cmbBolum = new JComboBox<>();
		cmbBolum.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cmbBolum.setModel(new DefaultComboBoxModel<>(new String[] {
				"Karışım Bölümü",
				"Dolum Bölümü",
				"Kapak Kapatma Bölümü",
				"Etiketleme Bölümü",
				"Paketleme Bölümü",
				"Depo",
				"Bakım Atölyesi"
		}));
		cmbBolum.setBounds(25, 175, 270, 30);
		panelForm.add(cmbBolum);

		JLabel lblDurum = new JLabel("Durum:");
		lblDurum.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDurum.setBounds(380, 150, 120, 20);
		panelForm.add(lblDurum);

		cmbDurum = new JComboBox<>();
		cmbDurum.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cmbDurum.setModel(new DefaultComboBoxModel<>(new String[] {
				"Aktif",
				"Bakımda",
				"Arızalı",
				"Pasif"
		}));
		cmbDurum.setBounds(380, 175, 270, 30);
		panelForm.add(cmbDurum);

		JLabel lblLokasyon = new JLabel("Lokasyon:");
		lblLokasyon.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLokasyon.setBounds(735, 150, 120, 20);
		panelForm.add(lblLokasyon);

		cmbLokasyon = new JComboBox<>();
		cmbLokasyon.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cmbLokasyon.setModel(new DefaultComboBoxModel<>(new String[] {
				"Hat 1",
				"Hat 2",
				"Hat 3"
		}));
		cmbLokasyon.setBounds(735, 175, 270, 30);
		panelForm.add(cmbLokasyon);

		JLabel lblBakimPeriyodu = new JLabel("Bakım Periyodu:");
		lblBakimPeriyodu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBakimPeriyodu.setBounds(25, 225, 150, 20);
		panelForm.add(lblBakimPeriyodu);

		txtBakimPeriyodu = new JTextField();
		txtBakimPeriyodu.setBounds(25, 250, 270, 30);
		panelForm.add(txtBakimPeriyodu);
		txtBakimPeriyodu.setColumns(10);

		btnKaydet = new JButton("Kaydet");
		btnKaydet.setBounds(410, 250, 120, 35);
		btnKaydet.setBackground(new Color(52, 152, 219));
		btnKaydet.setForeground(Color.WHITE);
		btnKaydet.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnKaydet.setFocusPainted(false);
		btnKaydet.setBorderPainted(false);
		btnKaydet.setContentAreaFilled(false);
		btnKaydet.setOpaque(true);
		panelForm.add(btnKaydet);

		btnTemizle = new JButton("Temizle");
		btnTemizle.setForeground(Color.BLACK);
		btnTemizle.setBackground(new Color(220, 220, 220));
		btnTemizle.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnTemizle.setBounds(570, 250, 120, 35);
		btnTemizle.setFocusPainted(false);
		btnTemizle.setBorderPainted(false);
		btnTemizle.setContentAreaFilled(false);
		btnTemizle.setOpaque(true);
		panelForm.add(btnTemizle);

	
		JPanel panelTablo = new JPanel();
		panelTablo.setBounds(20, 440, 1040, 190);
		panelTablo.setBackground(Color.WHITE);
		panelTablo.setLayout(null);
		contentPane.add(panelTablo);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 1020, 170);
		panelTablo.add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 13));
		table.setRowHeight(28);
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 13));
		table.getTableHeader().setBackground(new Color(230, 235, 250));
		table.setBackground(Color.WHITE);

		model = new DefaultTableModel();
		model.setColumnIdentifiers(new String[] {
				"Makine Tipi",
				"Makine Kodu",
				"Bölüm",
				"Çalışma Kapasitesi",
				"Durum",
				"Bakım Periyodu",
				"Lokasyon",
				"İşlem"
		});

		table.setModel(model);
		scrollPane.setViewportView(table);


		btnKaydet.addActionListener(e -> {
			String makineTipi = cmbMakineTipi.getSelectedItem().toString();
			String makineKodu = cmbMakineKodu.getSelectedItem().toString();
			String bolum = cmbBolum.getSelectedItem().toString();
			String durum = cmbDurum.getSelectedItem().toString();
			String lokasyon = cmbLokasyon.getSelectedItem().toString();
			String calismaKapasitesi = txtKapasite.getText().trim();
			String bakimPeriyodu = txtBakimPeriyodu.getText().trim();

			if (calismaKapasitesi.isEmpty() || bakimPeriyodu.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Lütfen boş alan bırakmayın.");
				return;
			}

			if (!duzenlemeModu) {
				if (makineKoduVarMi(makineKodu)) {
					JOptionPane.showMessageDialog(null, "Bu makine kodu zaten kayıtlı.");
					return;
				}

				model.addRow(new Object[] {
						makineTipi,
						makineKodu,
						bolum,
						calismaKapasitesi,
						durum,
						bakimPeriyodu,
						lokasyon,
						"Düzenle"
				});
			} else {
				for (int i = 0; i < model.getRowCount(); i++) {
					if (i != secilenSatir && model.getValueAt(i, 1).toString().equals(makineKodu)) {
						JOptionPane.showMessageDialog(null, "Bu makine kodu zaten kayıtlı.");
						return;
					}
				}

				model.setValueAt(makineTipi, secilenSatir, 0);
				model.setValueAt(makineKodu, secilenSatir, 1);
				model.setValueAt(bolum, secilenSatir, 2);
				model.setValueAt(calismaKapasitesi, secilenSatir, 3);
				model.setValueAt(durum, secilenSatir, 4);
				model.setValueAt(bakimPeriyodu, secilenSatir, 5);
				model.setValueAt(lokasyon, secilenSatir, 6);
				model.setValueAt("Düzenle", secilenSatir, 7);
			}

			formTemizle();
		});


		btnTemizle.addActionListener(e -> {
			if (duzenlemeModu && secilenSatir != -1) {
				model.removeRow(secilenSatir);
			}
			formTemizle();
		});


		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int satir = table.getSelectedRow();
				int sutun = table.getSelectedColumn();

				if (satir != -1 && sutun == 7) {
					secilenSatir = satir;
					duzenlemeModu = true;

					cmbMakineTipi.setSelectedItem(model.getValueAt(satir, 0).toString());
					cmbMakineKodu.setSelectedItem(model.getValueAt(satir, 1).toString());
					cmbBolum.setSelectedItem(model.getValueAt(satir, 2).toString());
					txtKapasite.setText(model.getValueAt(satir, 3).toString());
					cmbDurum.setSelectedItem(model.getValueAt(satir, 4).toString());
					txtBakimPeriyodu.setText(model.getValueAt(satir, 5).toString());
					cmbLokasyon.setSelectedItem(model.getValueAt(satir, 6).toString());

					btnKaydet.setText("Güncelle");
					btnTemizle.setText("Sil");
				}
			}
		});
	}

	private boolean makineKoduVarMi(String makineKodu) {
		for (int i = 0; i < model.getRowCount(); i++) {
			if (model.getValueAt(i, 1).toString().equals(makineKodu)) {
				return true;
			}
		}
		return false;
	}

	private void formTemizle() {
		cmbMakineTipi.setSelectedIndex(0);
		cmbMakineKodu.setSelectedIndex(0);
		cmbBolum.setSelectedIndex(0);
		cmbDurum.setSelectedIndex(0);
		cmbLokasyon.setSelectedIndex(0);

		txtKapasite.setText("");
		txtBakimPeriyodu.setText("");

		secilenSatir = -1;
		duzenlemeModu = false;

		btnKaydet.setText("Kaydet");
		btnTemizle.setText("Temizle");
		table.clearSelection();
	}
}