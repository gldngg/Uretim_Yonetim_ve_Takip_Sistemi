package üys;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

import database.Session;
import database.veriTabani;

public class MakineGirisEkrani extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private JTextField txtMakineKodu;
	private JTextField txtKapasite;
	private JTextField txtBakimPeriyodu;

	private JTable table;
	private DefaultTableModel model;

	private int secilenSatir = -1;
	private boolean duzenlemeModu = false;

	private JComboBox<String> cmbMakineTipi;
	private JComboBox<String> cmbBolum;
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

	public MakineGirisEkrani() {
		setTitle("Makine Girişi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1100, 760);
		setLocationRelativeTo(null);
		setResizable(false);

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

		JLabel lblBaslik = new JLabel("MAKİNE GİRİŞİ");
		lblBaslik.setBounds(50, 18, 200, 30);
		lblBaslik.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblBaslik.setForeground(Color.WHITE);
		panelUstMenu.add(lblBaslik);

		JButton btnAnaSayfa = new JButton("Ana Sayfa");
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

		btnDurusKayip.addActionListener(e -> {
			new DuruşKayıp().setVisible(true);
			dispose();
		});

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

		JLabel lblKullanici = new JLabel(Session.aktifKullanici);
		lblKullanici.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblKullanici.setForeground(Color.WHITE);
		lblKullanici.setBounds(830, 24, 130, 20);
		panelUstMenu.add(lblKullanici);

		JButton btnCikis = new JButton("ÇIKIŞ");
		btnCikis.setBounds(950, 18, 70, 30);
		panelUstMenu.add(btnCikis);

		btnCikis.addActionListener(e -> {
		    Session.aktifKullanici = "";   
		    new GirisEkrani().setVisible(true); 
		    dispose(); 
		});

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

		JLabel lblMakineKodu = new JLabel("Makine Kodu:");
		lblMakineKodu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMakineKodu.setBounds(380, 75, 120, 20);
		panelForm.add(lblMakineKodu);

		txtMakineKodu = new JTextField();
		txtMakineKodu.setBounds(380, 100, 270, 30);
		panelForm.add(txtMakineKodu);

		JLabel lblKapasite = new JLabel("Çalışma Kapasitesi:");
		lblKapasite.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblKapasite.setBounds(735, 75, 150, 20);
		panelForm.add(lblKapasite);

		txtKapasite = new JTextField();
		txtKapasite.setBounds(735, 100, 270, 30);
		panelForm.add(txtKapasite);

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

		JLabel lblLokasyon = new JLabel("Lokasyon:");
		lblLokasyon.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLokasyon.setBounds(380, 150, 120, 20);
		panelForm.add(lblLokasyon);

		cmbLokasyon = new JComboBox<>();
		cmbLokasyon.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cmbLokasyon.setModel(new DefaultComboBoxModel<>(new String[] {
				"Hat 1",
				"Hat 2",
				"Hat 3"
		}));
		cmbLokasyon.setBounds(380, 175, 270, 30);
		panelForm.add(cmbLokasyon);

		JLabel lblBakimPeriyodu = new JLabel("Bakım Periyodu:");
		lblBakimPeriyodu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBakimPeriyodu.setBounds(735, 150, 150, 20);
		panelForm.add(lblBakimPeriyodu);

		txtBakimPeriyodu = new JTextField();
		txtBakimPeriyodu.setBounds(735, 175, 270, 30);
		panelForm.add(txtBakimPeriyodu);

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
				"Bakım Periyodu",
				"Lokasyon",
				"İşlem"
		});

		table.setModel(model);
		scrollPane.setViewportView(table);

		btnKaydet.addActionListener(e -> kaydetVeyaGuncelle());

		btnTemizle.addActionListener(e -> {
			if (duzenlemeModu && secilenSatir != -1) {
				sil();
			} else {
				formTemizle();
			}
		});

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int satir = table.getSelectedRow();
				int sutun = table.getSelectedColumn();

				if (satir != -1 && sutun == 6) {
					secilenSatir = satir;
					duzenlemeModu = true;

					cmbMakineTipi.setSelectedItem(model.getValueAt(satir, 0).toString());
					txtMakineKodu.setText(model.getValueAt(satir, 1).toString());
					cmbBolum.setSelectedItem(model.getValueAt(satir, 2).toString());
					txtKapasite.setText(model.getValueAt(satir, 3).toString());
					txtBakimPeriyodu.setText(model.getValueAt(satir, 4).toString());
					cmbLokasyon.setSelectedItem(model.getValueAt(satir, 5).toString());

					btnKaydet.setText("Güncelle");
					btnTemizle.setText("Sil");
				}
			}
		});

		makineleriYukle();
	}

	private void kaydetVeyaGuncelle() {
		String makineTipi = cmbMakineTipi.getSelectedItem().toString();
		String makineKodu = txtMakineKodu.getText().trim();
		String bolum = cmbBolum.getSelectedItem().toString();
		String kapasite = txtKapasite.getText().trim();
		String bakimPeriyodu = txtBakimPeriyodu.getText().trim();
		String lokasyon = cmbLokasyon.getSelectedItem().toString();

		if (makineKodu.isEmpty() || kapasite.isEmpty() || bakimPeriyodu.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Lütfen boş alan bırakmayın.");
			return;
		}

		if (!duzenlemeModu) {
			String sql = "INSERT INTO machines(makine_tipi, makine_kodu, bolum, kapasite, bakim_periyodu, lokasyon) "
					+ "VALUES (?, ?, ?, ?, ?, ?)";

			try {
				Connection conn = veriTabani.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, makineTipi);
				pstmt.setString(2, makineKodu);
				pstmt.setString(3, bolum);
				pstmt.setString(4, kapasite);
				pstmt.setString(5, bakimPeriyodu);
				pstmt.setString(6, lokasyon);

				pstmt.executeUpdate();

				JOptionPane.showMessageDialog(null, "Makine kaydedildi.");
				makineleriYukle();
				formTemizle();

			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Kayıt hatası: " + e.getMessage());
			}

		} else {
			String eskiMakineKodu = model.getValueAt(secilenSatir, 1).toString();

			String sql = "UPDATE machines SET makine_tipi = ?, makine_kodu = ?, bolum = ?, kapasite = ?, "
					+ "bakim_periyodu = ?, lokasyon = ? WHERE makine_kodu = ?";

			try {
				Connection conn = veriTabani.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, makineTipi);
				pstmt.setString(2, makineKodu);
				pstmt.setString(3, bolum);
				pstmt.setString(4, kapasite);
				pstmt.setString(5, bakimPeriyodu);
				pstmt.setString(6, lokasyon);
				pstmt.setString(7, eskiMakineKodu);

				pstmt.executeUpdate();

				JOptionPane.showMessageDialog(null, "Makine güncellendi.");
				makineleriYukle();
				formTemizle();

			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Güncelleme hatası: " + e.getMessage());
			}
		}
	}

	private void makineleriYukle() {
		model.setRowCount(0);

		String sql = "SELECT * FROM machines";

		try {
			Connection conn = veriTabani.getInstance().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				model.addRow(new Object[] {
						rs.getString("makine_tipi"),
						rs.getString("makine_kodu"),
						rs.getString("bolum"),
						rs.getString("kapasite"),
						rs.getString("bakim_periyodu"),
						rs.getString("lokasyon"),
						"Düzenle"
				});
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void sil() {
		if (secilenSatir == -1) {
			return;
		}

		String makineKodu = model.getValueAt(secilenSatir, 1).toString();

		int cevap = JOptionPane.showConfirmDialog(
				null,
				"Bu makine kaydı silinsin mi?",
				"Silme Onayı",
				JOptionPane.YES_NO_OPTION
		);

		if (cevap == JOptionPane.YES_OPTION) {
			String sql = "DELETE FROM machines WHERE makine_kodu = ?";

			try {
				Connection conn = veriTabani.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, makineKodu);
				pstmt.executeUpdate();

				JOptionPane.showMessageDialog(null, "Makine silindi.");
				makineleriYukle();
				formTemizle();

			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Silme hatası: " + e.getMessage());
			}
		}
	}

	private void formTemizle() {
		cmbMakineTipi.setSelectedIndex(0);
		txtMakineKodu.setText("");
		cmbBolum.setSelectedIndex(0);
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
