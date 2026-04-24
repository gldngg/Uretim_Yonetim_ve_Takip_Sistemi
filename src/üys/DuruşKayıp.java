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
import java.util.HashMap;
import java.util.Map;

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

import database.Veritabani;

public class DuruşKayıp extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField txt_baslangic;
	private JTextField txt_makinetipi;
	private JTextField txt_bitis;
	private JTextField txt_aciklama;

	private JTable table;
	private DefaultTableModel model;

	private JComboBox<String> cmb_makinekodu;
	private JComboBox<String> cmb_durusturu;
	private JComboBox<String> cmb_durusnedeni;

	private int secilenSatir = -1;
	private boolean duzenlemeModu = false;

	private JButton btn_kaydet;
	private JButton btn_temizle;

	private Map<String, String> makineMap = new HashMap<>();

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				DuruşKayıp frame = new DuruşKayıp();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public DuruşKayıp() {
		setTitle("Duruş / Kayıp");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1100, 760);
		setLocationRelativeTo(null);
		setResizable(false);
		setMinimumSize(getSize());
		setMaximumSize(getSize());

		contentPane = new JPanel();
		contentPane.setBackground(new Color(142, 155, 213));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelUstMenu = new JPanel();
		panelUstMenu.setBackground(new Color(63, 81, 181));
		panelUstMenu.setBounds(20, 20, 1040, 70);
		contentPane.add(panelUstMenu);
		panelUstMenu.setLayout(null);

		JLabel jlb_logo = new JLabel("LOGO");
		jlb_logo.setBounds(10, 18, 40, 28);
		panelUstMenu.add(jlb_logo);

		JLabel jlv_duruskayip = new JLabel("DURUŞ/KAYIP");
		jlv_duruskayip.setFont(new Font("Tahoma", Font.BOLD, 20));
		jlv_duruskayip.setForeground(Color.WHITE);
		jlv_duruskayip.setBounds(50, 18, 200, 30);
		panelUstMenu.add(jlv_duruskayip);

		JButton btnAnaSayfa = new JButton("Ana Sayfa");
		btnAnaSayfa.setBounds(250, 20, 110, 30);
		btnAnaSayfa.setForeground(Color.WHITE);
		btnAnaSayfa.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAnaSayfa.setFocusPainted(false);
		btnAnaSayfa.setBorderPainted(false);
		btnAnaSayfa.setContentAreaFilled(false);
		panelUstMenu.add(btnAnaSayfa);

		JButton btnMakine = new JButton("Makine Girişi");
		btnMakine.setBounds(370, 20, 130, 30);
		btnMakine.setForeground(Color.WHITE);
		btnMakine.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnMakine.setFocusPainted(false);
		btnMakine.setBorderPainted(false);
		btnMakine.setContentAreaFilled(false);
		panelUstMenu.add(btnMakine);

		JButton btnSiparis = new JButton("Sipariş");
		btnSiparis.setBounds(510, 20, 90, 30);
		btnSiparis.setForeground(Color.WHITE);
		btnSiparis.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSiparis.setFocusPainted(false);
		btnSiparis.setBorderPainted(false);
		btnSiparis.setContentAreaFilled(false);
		panelUstMenu.add(btnSiparis);

		JButton btnPlanlama = new JButton("Planlama");
		btnPlanlama.setBounds(610, 20, 110, 30);
		btnPlanlama.setForeground(Color.WHITE);
		btnPlanlama.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnPlanlama.setFocusPainted(false);
		btnPlanlama.setBorderPainted(false);
		btnPlanlama.setContentAreaFilled(false);
		panelUstMenu.add(btnPlanlama);

		JButton btnRapor = new JButton("Rapor");
		btnRapor.setBounds(730, 20, 80, 30);
		btnRapor.setForeground(Color.WHITE);
		btnRapor.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnRapor.setFocusPainted(false);
		btnRapor.setBorderPainted(false);
		btnRapor.setContentAreaFilled(false);
		panelUstMenu.add(btnRapor);

		JLabel jlb_kullanici = new JLabel("Ad Soyad");
		jlb_kullanici.setFont(new Font("Tahoma", Font.PLAIN, 12));
		jlb_kullanici.setForeground(Color.WHITE);
		jlb_kullanici.setBounds(860, 24, 90, 20);
		panelUstMenu.add(jlb_kullanici);

		JButton btn_cikis = new JButton("ÇIKIŞ");
		btn_cikis.setForeground(Color.BLACK);
		btn_cikis.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btn_cikis.setBounds(950, 18, 70, 30);
		panelUstMenu.add(btn_cikis);

		btnMakine.addActionListener(e -> {
			new MakineGirisEkrani().setVisible(true);
			dispose();
		});

		JPanel panelForm = new JPanel();
		panelForm.setBounds(20, 100, 1040, 390);
		contentPane.add(panelForm);
		panelForm.setLayout(null);

		JLabel jlb_durusform = new JLabel("Duruş/Kayıp Girişi");
		jlb_durusform.setFont(new Font("Tahoma", Font.BOLD, 15));
		jlb_durusform.setBounds(20, 15, 250, 30);
		panelForm.add(jlb_durusform);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 55, 1040, 2);
		panelForm.add(separator);

		JLabel jlb_makinekodu = new JLabel("Makine Kodu");
		jlb_makinekodu.setFont(new Font("Tahoma", Font.PLAIN, 12));
		jlb_makinekodu.setBounds(20, 75, 120, 20);
		panelForm.add(jlb_makinekodu);

		cmb_makinekodu = new JComboBox<>();
		cmb_makinekodu.setBounds(20, 100, 430, 30);
		panelForm.add(cmb_makinekodu);

		JLabel jlb_makinetipi = new JLabel("Makine Tipi");
		jlb_makinetipi.setFont(new Font("Tahoma", Font.PLAIN, 12));
		jlb_makinetipi.setBounds(480, 75, 120, 20);
		panelForm.add(jlb_makinetipi);

		txt_makinetipi = new JTextField();
		txt_makinetipi.setEditable(false);
		txt_makinetipi.setBounds(480, 100, 430, 30);
		panelForm.add(txt_makinetipi);

		JLabel jlb_durusturu = new JLabel("Duruş Türü");
		jlb_durusturu.setFont(new Font("Tahoma", Font.PLAIN, 12));
		jlb_durusturu.setBounds(20, 145, 120, 20);
		panelForm.add(jlb_durusturu);

		cmb_durusturu = new JComboBox<>();
		cmb_durusturu.setModel(new DefaultComboBoxModel<>(new String[] {
				"Planlı", "Plansız"
		}));
		cmb_durusturu.setBounds(20, 170, 430, 30);
		panelForm.add(cmb_durusturu);

		JLabel jlb_durusnedeni = new JLabel("Duruş Nedeni");
		jlb_durusnedeni.setFont(new Font("Tahoma", Font.PLAIN, 12));
		jlb_durusnedeni.setBounds(480, 145, 120, 20);
		panelForm.add(jlb_durusnedeni);

		cmb_durusnedeni = new JComboBox<>();
		cmb_durusnedeni.setModel(new DefaultComboBoxModel<>(new String[] {
				"Arıza", "Bakım", "Ham Madde Eksik", "Setup"
		}));
		cmb_durusnedeni.setBounds(480, 170, 430, 30);
		panelForm.add(cmb_durusnedeni);

		JLabel jlb_baslangic = new JLabel("Başlangıç Zamanı");
		jlb_baslangic.setFont(new Font("Tahoma", Font.PLAIN, 12));
		jlb_baslangic.setBounds(20, 215, 140, 20);
		panelForm.add(jlb_baslangic);

		txt_baslangic = new JTextField();
		txt_baslangic.setBounds(20, 240, 430, 30);
		panelForm.add(txt_baslangic);

		JLabel jlb_bitiszamani = new JLabel("Bitiş Zamanı");
		jlb_bitiszamani.setFont(new Font("Tahoma", Font.PLAIN, 12));
		jlb_bitiszamani.setBounds(480, 215, 120, 20);
		panelForm.add(jlb_bitiszamani);

		txt_bitis = new JTextField();
		txt_bitis.setBounds(480, 240, 430, 30);
		panelForm.add(txt_bitis);

		JLabel jlb_aciklama = new JLabel("Açıklama:");
		jlb_aciklama.setFont(new Font("Tahoma", Font.PLAIN, 12));
		jlb_aciklama.setBounds(20, 280, 90, 20);
		panelForm.add(jlb_aciklama);

		txt_aciklama = new JTextField();
		txt_aciklama.setBounds(105, 275, 805, 35);
		panelForm.add(txt_aciklama);

		btn_kaydet = new JButton("Kaydet");
		btn_kaydet.setForeground(Color.WHITE);
		btn_kaydet.setBackground(new Color(52, 152, 219));
		btn_kaydet.setBounds(20, 335, 120, 35);
		btn_kaydet.setFocusPainted(false);
		btn_kaydet.setContentAreaFilled(false);
		btn_kaydet.setOpaque(true);
		btn_kaydet.setBorderPainted(false);
		panelForm.add(btn_kaydet);

		btn_temizle = new JButton("Temizle");
		btn_temizle.setBackground(new Color(220, 220, 220));
		btn_temizle.setBounds(150, 335, 120, 35);
		btn_temizle.setFocusPainted(false);
		btn_temizle.setContentAreaFilled(false);
		btn_temizle.setOpaque(true);
		btn_temizle.setBorderPainted(false);
		panelForm.add(btn_temizle);

		JPanel panelTablo = new JPanel();
		panelTablo.setBounds(20, 510, 1040, 190);
		contentPane.add(panelTablo);
		panelTablo.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 1020, 170);
		panelTablo.add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 13));
		table.setRowHeight(28);

		model = new DefaultTableModel();
		model.addColumn("Makine Tipi");
		model.addColumn("Makine Kodu");
		model.addColumn("Başlangıç");
		model.addColumn("Bitiş");
		model.addColumn("Süre");
		model.addColumn("Duruş Türü");
		model.addColumn("Duruş Nedeni");
		model.addColumn("Açıklama");
		model.addColumn("İşlem");

		table.setModel(model);
		scrollPane.setViewportView(table);

		makineleriYukle();
		durusKayiplariYukle();

		cmb_makinekodu.addActionListener(e -> {
			if (cmb_makinekodu.getSelectedItem() != null) {
				String secilenKod = cmb_makinekodu.getSelectedItem().toString();
				txt_makinetipi.setText(makineMap.get(secilenKod));
			}
		});

		btn_kaydet.addActionListener(e -> kaydetVeyaGuncelle());

		btn_temizle.addActionListener(e -> {
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

				if (sutun == 8 && satir != -1) {
					secilenSatir = satir;
					duzenlemeModu = true;

					txt_makinetipi.setText(model.getValueAt(satir, 0).toString());
					cmb_makinekodu.setSelectedItem(model.getValueAt(satir, 1).toString());
					txt_baslangic.setText(model.getValueAt(satir, 2).toString());
					txt_bitis.setText(model.getValueAt(satir, 3).toString());
					cmb_durusturu.setSelectedItem(model.getValueAt(satir, 5).toString());
					cmb_durusnedeni.setSelectedItem(model.getValueAt(satir, 6).toString());
					txt_aciklama.setText(model.getValueAt(satir, 7).toString());

					btn_kaydet.setText("Güncelle");
					btn_temizle.setText("Sil");
				}
			}
		});
	}

	private void makineleriYukle() {
		makineMap.clear();
		cmb_makinekodu.removeAllItems();

		String sql = "SELECT makine_kodu, makine_tipi FROM machines";

		try {
			Connection conn = Veritabani.getInstance().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String kod = rs.getString("makine_kodu");
				String tip = rs.getString("makine_tipi");

				makineMap.put(kod, tip);
				cmb_makinekodu.addItem(kod);
			}

			if (cmb_makinekodu.getItemCount() > 0) {
				cmb_makinekodu.setSelectedIndex(0);
				String ilkKod = cmb_makinekodu.getSelectedItem().toString();
				txt_makinetipi.setText(makineMap.get(ilkKod));
			} else {
				txt_makinetipi.setText("");
				JOptionPane.showMessageDialog(null, "Duruş/Kayıp girebilmek için önce Makine Girişi ekranından makine eklemelisiniz.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Makine bilgileri yüklenemedi.");
		}
	}

	private void durusKayiplariYukle() {
		model.setRowCount(0);

		String sql = "SELECT * FROM downtimes";

		try {
			Connection conn = Veritabani.getInstance().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				model.addRow(new Object[] {
						rs.getString("makine_tipi"),
						rs.getString("makine_kodu"),
						rs.getString("baslangic"),
						rs.getString("bitis"),
						rs.getString("sure"),
						rs.getString("durus_turu"),
						rs.getString("durus_nedeni"),
						rs.getString("aciklama"),
						"Düzenle"
				});
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void kaydetVeyaGuncelle() {
		if (cmb_makinekodu.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(null, "Önce Makine Girişi ekranından makine eklemelisiniz.");
			return;
		}

		String makineKodu = cmb_makinekodu.getSelectedItem().toString();
		String makineTipi = txt_makinetipi.getText().trim();
		String baslangic = txt_baslangic.getText().trim();
		String bitis = txt_bitis.getText().trim();
		String durusTuru = cmb_durusturu.getSelectedItem().toString();
		String durusNedeni = cmb_durusnedeni.getSelectedItem().toString();
		String aciklama = txt_aciklama.getText().trim();
		String sure = "40 dk";

		if (makineTipi.isEmpty() || baslangic.isEmpty() || bitis.isEmpty() || aciklama.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Lütfen tüm alanları doldurun!");
			return;
		}

		if (!duzenlemeModu) {
			String sql = "INSERT INTO downtimes(makine_tipi, makine_kodu, baslangic, bitis, sure, durus_turu, durus_nedeni, aciklama) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

			try {
				Connection conn = Veritabani.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, makineTipi);
				pstmt.setString(2, makineKodu);
				pstmt.setString(3, baslangic);
				pstmt.setString(4, bitis);
				pstmt.setString(5, sure);
				pstmt.setString(6, durusTuru);
				pstmt.setString(7, durusNedeni);
				pstmt.setString(8, aciklama);

				pstmt.executeUpdate();

				JOptionPane.showMessageDialog(null, "Duruş/Kayıp kaydedildi.");
				durusKayiplariYukle();
				formTemizle();

			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Kayıt sırasında hata oluştu.");
			}

		} else {
			String eskiBaslangic = model.getValueAt(secilenSatir, 2).toString();
			String eskiMakineKodu = model.getValueAt(secilenSatir, 1).toString();

			String sql = "UPDATE downtimes SET makine_tipi = ?, makine_kodu = ?, baslangic = ?, bitis = ?, sure = ?, durus_turu = ?, durus_nedeni = ?, aciklama = ? "
					+ "WHERE makine_kodu = ? AND baslangic = ?";

			try {
				Connection conn = Veritabani.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, makineTipi);
				pstmt.setString(2, makineKodu);
				pstmt.setString(3, baslangic);
				pstmt.setString(4, bitis);
				pstmt.setString(5, sure);
				pstmt.setString(6, durusTuru);
				pstmt.setString(7, durusNedeni);
				pstmt.setString(8, aciklama);
				pstmt.setString(9, eskiMakineKodu);
				pstmt.setString(10, eskiBaslangic);

				pstmt.executeUpdate();

				JOptionPane.showMessageDialog(null, "Duruş/Kayıp güncellendi.");
				durusKayiplariYukle();
				formTemizle();

			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Güncelleme sırasında hata oluştu.");
			}
		}
	}

	private void sil() {
		if (secilenSatir == -1) {
			return;
		}

		String makineKodu = model.getValueAt(secilenSatir, 1).toString();
		String baslangic = model.getValueAt(secilenSatir, 2).toString();

		int cevap = JOptionPane.showConfirmDialog(
				null,
				"Bu duruş/kayıp kaydı silinsin mi?",
				"Silme Onayı",
				JOptionPane.YES_NO_OPTION
		);

		if (cevap == JOptionPane.YES_OPTION) {
			String sql = "DELETE FROM downtimes WHERE makine_kodu = ? AND baslangic = ?";

			try {
				Connection conn = Veritabani.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, makineKodu);
				pstmt.setString(2, baslangic);

				pstmt.executeUpdate();

				JOptionPane.showMessageDialog(null, "Kayıt silindi.");
				durusKayiplariYukle();
				formTemizle();

			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Silme sırasında hata oluştu.");
			}
		}
	}

	private void formTemizle() {
		if (cmb_makinekodu.getItemCount() > 0) {
			cmb_makinekodu.setSelectedIndex(0);
			String secilenKod = cmb_makinekodu.getSelectedItem().toString();
			txt_makinetipi.setText(makineMap.get(secilenKod));
		} else {
			txt_makinetipi.setText("");
		}

		cmb_durusturu.setSelectedIndex(0);
		cmb_durusnedeni.setSelectedIndex(0);

		txt_baslangic.setText("");
		txt_bitis.setText("");
		txt_aciklama.setText("");

		secilenSatir = -1;
		duzenlemeModu = false;

		btn_kaydet.setText("Kaydet");
		btn_temizle.setText("Temizle");

		table.clearSelection();
	}
}
