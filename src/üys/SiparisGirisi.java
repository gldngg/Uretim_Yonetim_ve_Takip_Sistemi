package üys;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.UIManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SiparisGirisi extends JFrame{
	private JPanel contentPane;
	private JTextField txtSiparisAdi, txtTarih, txtSiparisKodu, txtMusteri, textUrun;
	private JSpinner spinnerMiktar;
	private JTable table;
	private JLabel lblDurum; 
	private int seciliSiparisId = -1;
	private JList<String> siparisListesi;
	private DefaultListModel<String> listeModeli;
	
	public SiparisGirisi() {
		
		setTitle("Sipariş Girişi");//ekran
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(142, 155, 213));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel headerPanel = new JPanel();// üst menu
		headerPanel.setBackground(new Color(63, 81, 181));
		headerPanel.setBounds(0, 0, 1100, 60);
		contentPane.add(headerPanel);
		headerPanel.setLayout(null);
		
		JLabel lblBaslik = new JLabel("SİPARİŞ GİRİŞİ");
		lblBaslik.setBounds(50, 15, 200, 30);
		lblBaslik.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblBaslik.setForeground(Color.WHITE);
		headerPanel.add(lblBaslik);

		JButton btnAnaSayfa = new JButton("Ana Sayfa");
		btnAnaSayfa.setContentAreaFilled(false);
		btnAnaSayfa.setBorderPainted(false);
		btnAnaSayfa.setFocusPainted(false);
		btnAnaSayfa.setForeground(Color.WHITE);
		btnAnaSayfa.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAnaSayfa.setBounds(250, 20, 100, 25);
		headerPanel.add(btnAnaSayfa);

		JButton btnDurusKayip = new JButton("Duruş/Kayıp");
		btnDurusKayip.setContentAreaFilled(false);
		btnDurusKayip.setBorderPainted(false);
		btnDurusKayip.setFocusPainted(false);
		btnDurusKayip.setForeground(Color.WHITE);
		btnDurusKayip.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnDurusKayip.setBounds(360, 20, 120, 25);
		headerPanel.add(btnDurusKayip);
		
		JButton btnSiparis = new JButton("Makine");
		btnSiparis.setContentAreaFilled(false);
		btnSiparis.setBorderPainted(false);
		btnSiparis.setFocusPainted(false);
		btnSiparis.setForeground(Color.WHITE);
		btnSiparis.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSiparis.setBounds(490, 20, 90, 25);
		headerPanel.add(btnSiparis);

		JButton btnPlanlama = new JButton("Planlama");
		btnPlanlama.setContentAreaFilled(false);
		btnPlanlama.setBorderPainted(false);
		btnPlanlama.setFocusPainted(false);
		btnPlanlama.setForeground(Color.WHITE);
		btnPlanlama.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnPlanlama.setBounds(590, 20, 100, 25);
		headerPanel.add(btnPlanlama);

		JButton btnRapor = new JButton("Rapor");
		btnRapor.setContentAreaFilled(false);
		btnRapor.setBorderPainted(false);
		btnRapor.setFocusPainted(false);
		btnRapor.setForeground(Color.WHITE);
		btnRapor.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnRapor.setBounds(700, 20, 80, 25);
		headerPanel.add(btnRapor);

		JLabel lblKullanici = new JLabel("Ad Soyad");
		lblKullanici.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblKullanici.setForeground(Color.WHITE);
		lblKullanici.setBounds(860, 20, 90, 20);
		headerPanel.add(lblKullanici);

		JButton btnCikis = new JButton("ÇIKIŞ");
		btnCikis.setForeground(Color.BLACK);
		btnCikis.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCikis.setBounds(950, 15, 70, 30);
		headerPanel.add(btnCikis);

		JPanel panelYan = new JPanel();// yan menu
		panelYan.setBackground(Color.LIGHT_GRAY);
		panelYan.setBounds(0, 60, 225, 600);
		panelYan.setLayout(null);
		contentPane.add(panelYan);
		
		listeModeli = new DefaultListModel<>();

		siparisListesi = new JList<>(listeModeli);
		siparisListesi.setFixedCellHeight(40); 
		siparisListesi.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		siparisListesi.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		siparisListesi.addListSelectionListener(e -> {
			lblDurum.setText("Durum :");
		    if (!e.getValueIsAdjusting()) {
		        String secilenSatir = siparisListesi.getSelectedValue();
		        if(secilenSatir != null) {
		        	String secilenSiparisKodu = secilenSatir.split(" - ")[0];
		        	formDoldur(secilenSiparisKodu);
		        }
		    }
		});
		
		JScrollPane scrollPane = new JScrollPane(siparisListesi);
		scrollPane.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0), 2, true), new TitledBorder(null, "Sipari\u015F Listesi", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0))));
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBounds(10, 50, 205, 300);
		scrollPane.setPreferredSize(new Dimension(200, 300));
		panelYan.add(scrollPane);
		
		JButton btnYeniSiparis = new JButton("+ Yeni Sipariş");
		btnYeniSiparis.setBorderPainted(false);
		btnYeniSiparis.setForeground(new Color(255, 255, 255));
		btnYeniSiparis.setBackground(new Color(0, 128, 0));
		btnYeniSiparis.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnYeniSiparis.setBounds(10, 10, 205, 30);
		panelYan.add(btnYeniSiparis);
		btnYeniSiparis.addActionListener(e-> {
			lblDurum.setText("Durum :");
			formuTemizle();
			});

		JPanel panelAna = new JPanel();//ana panel
		panelAna.setBackground(Color.LIGHT_GRAY);
		panelAna.setBounds(250, 70, 815, 580);
		panelAna.setLayout(null);
		contentPane.add(panelAna);
		
		JLabel lblSiparisDetaylari = new JLabel("Sipariş Detayları");
		lblSiparisDetaylari.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSiparisDetaylari.setBounds(10, 5, 200, 30);
		panelAna.add(lblSiparisDetaylari);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(10, 40, 795, 1);
		panelAna.add(separator);
		
		JLabel lblSiparisAdi = new JLabel("Sipariş Adı");
		lblSiparisAdi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSiparisAdi.setBounds(10, 45, 200, 25);
		panelAna.add(lblSiparisAdi);
		txtSiparisAdi = new JTextField();
		txtSiparisAdi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtSiparisAdi.setBorder(new CompoundBorder(new LineBorder(Color.BLACK, 2, true), new EmptyBorder(2, 5, 2, 2)));
		txtSiparisAdi.setBounds(10, 75, 385, 30);
		panelAna.add(txtSiparisAdi);
		txtSiparisAdi.setColumns(10);
		
		JLabel lblMusteri = new JLabel("Müşteri");
		lblMusteri.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMusteri.setBounds(10, 115, 200, 25);
		panelAna.add(lblMusteri);
		txtMusteri = new JTextField();
		txtMusteri.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtMusteri.setColumns(10);
		txtMusteri.setBorder(new CompoundBorder(new LineBorder(Color.BLACK, 2, true), new EmptyBorder(2, 5, 2, 2)));
		txtMusteri.setBounds(10, 144, 385, 30);
		panelAna.add(txtMusteri);
		
		JLabel lblMiktar = new JLabel("Miktar");
		lblMiktar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMiktar.setBounds(10, 185, 200, 25);
		panelAna.add(lblMiktar);
		spinnerMiktar = new JSpinner();
		spinnerMiktar.setBorder(new CompoundBorder(UIManager.getBorder("ComboBox.border"), new EmptyBorder(2, 2, 2, 2)));
		spinnerMiktar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		spinnerMiktar.setBounds(10, 215, 385, 30);
		panelAna.add(spinnerMiktar);
		
		JLabel lblSiparisKodu = new JLabel("Sipariş Kodu");
		lblSiparisKodu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSiparisKodu.setBounds(420, 45, 200, 25);
		panelAna.add(lblSiparisKodu);
		txtSiparisKodu = new JTextField();
		txtSiparisKodu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtSiparisKodu.setColumns(10);
		txtSiparisKodu.setBorder(new CompoundBorder(new LineBorder(Color.BLACK, 2, true), new EmptyBorder(2, 5, 2, 2)));
		txtSiparisKodu.setBounds(420, 75, 385, 30);
		panelAna.add(txtSiparisKodu);
		
		JLabel lblUrunAdi = new JLabel("Ürün Adı");
		lblUrunAdi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUrunAdi.setBounds(420, 115, 200, 25);
		panelAna.add(lblUrunAdi);
		textUrun = new JTextField();
		textUrun.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textUrun.setColumns(10);
		textUrun.setBorder(new CompoundBorder(new LineBorder(Color.BLACK, 2, true), new EmptyBorder(2, 5, 2, 2)));
		textUrun.setBounds(420, 144, 385, 30);
		panelAna.add(textUrun);
		
		JLabel lblTarihi = new JLabel("Termin Tarihi");
		lblTarihi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTarihi.setBounds(420, 185, 200, 25);
		panelAna.add(lblTarihi);
		txtTarih = new JTextField();
		txtTarih.setText("gg.aa.yyyy");
		txtTarih.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTarih.setColumns(10);
		txtTarih.setBorder(new CompoundBorder(new LineBorder(Color.BLACK, 2, true), new EmptyBorder(2, 5, 2, 2)));
		txtTarih.setBounds(420, 215, 385, 30);
		panelAna.add(txtTarih);
		
		lblDurum = new JLabel("Durum");
		lblDurum.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDurum.setBounds(10, 255, 795, 25);
		panelAna.add(lblDurum);
		
		String[] kolonIsimleri = {"İş Emri No", "Görev", "Makine"};
		DefaultTableModel tabloModeli = new DefaultTableModel(kolonIsimleri, 0);
		table = new JTable(tabloModeli);
		JScrollPane tableScroll = new JScrollPane(table);
		tableScroll.setBounds(10, 290, 795, 230);
		panelAna.add(tableScroll);
		
		JButton btnYeniIsEmri = new JButton("+ Yeni İş Emri");
		btnYeniIsEmri.setForeground(Color.WHITE);
		btnYeniIsEmri.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnYeniIsEmri.setBorderPainted(false);
		btnYeniIsEmri.setBackground(new Color(0, 128, 0));
		btnYeniIsEmri.setBounds(10, 530, 205, 30);
		panelAna.add(btnYeniIsEmri);
		btnYeniIsEmri.addActionListener(e -> {
			lblDurum.setText("Durum :");
		    DefaultTableModel model = (DefaultTableModel) table.getModel();
		    model.addRow(new Object[]{"İE-" + (model.getRowCount() + 101), "Görev Girin", "Makine Seçin"});
		});
		
		JButton btnKaydet = new JButton("Kaydet");
		btnKaydet.setForeground(Color.WHITE);
		btnKaydet.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnKaydet.setBorderPainted(false);
		btnKaydet.setBackground(new Color(0, 128, 0));
		btnKaydet.setBounds(595, 530, 100, 30);
		panelAna.add(btnKaydet);
		btnKaydet.addActionListener(e -> {
			if (table.isEditing()) {
		        table.getCellEditor().stopCellEditing();
		    }
			if (seciliSiparisId == -1) {
				int id = siparisKaydet();
				if (id != -1) {
					Connection conn = Veritabani.getInstance().getConnection();
				    isEmriKaydet(conn, id); 
				    lblDurum.setText("Durum : Sipariş Kaydedildi.");
				    formuTemizle();
					listeyiYenile();
				}				
			}else {
				siparisGuncelle(seciliSiparisId);
				listeyiYenile();
				lblDurum.setText("Durum : Sipariş başarıyla güncellendi.");
				lblDurum.paintImmediately(lblDurum.getVisibleRect());
			}

		});
		
		JButton btnTemizle = new JButton("Temizle");
		btnTemizle.setForeground(new Color(0, 0, 0));
		btnTemizle.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnTemizle.setBorderPainted(false);
		btnTemizle.setBackground(new Color(255, 255, 255));
		btnTemizle.setBounds(705, 530, 100, 30);
		panelAna.add(btnTemizle);
		btnTemizle.addActionListener(e-> formuTemizle());
		
		listeyiYenile();
	}
	
	public int siparisKaydet() {
		if (txtSiparisAdi.getText().trim().isEmpty()) {
			lblDurum.setText("Durum: Sipariş Adı boş bırakılamaz.");
	        return -1;
		}
		if (txtSiparisKodu.getText().trim().isEmpty()) {
			lblDurum.setText("Durum: Sipariş Kodu boş bırakılamaz.");
	        return -1;
		}
		if (txtMusteri.getText().trim().isEmpty()) {
			lblDurum.setText("Durum: Müşteri boş bırakılamaz.");
	        return -1;
		}
		if (textUrun.getText().trim().isEmpty()) {
			lblDurum.setText("Durum: Ürün Adı boş bırakılamaz.");
	        return -1;
		}
	    String sql = "INSERT INTO siparisler(siparis_adi, siparis_kodu, musteri, urun_adi, miktar, termin_tarihi) VALUES(?,?,?,?,?,?)";
	    try (Connection conn = Veritabani.getInstance().getConnection();
	        PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
	        
	        pstmt.setString(1, txtSiparisAdi.getText());
	        pstmt.setString(2, txtSiparisKodu.getText());
	        pstmt.setString(3, txtMusteri.getText());
	        pstmt.setString(4, textUrun.getText());
	        pstmt.setInt(5, ((int)spinnerMiktar.getValue()));
	        pstmt.setString(6, txtTarih.getText());
	        
	        pstmt.executeUpdate();
	        
	        try (Statement stmt = conn.createStatement();
	        	 ResultSet rsId = stmt.executeQuery("SELECT last_insert_rowid()")) {
	        	    if (rsId.next()) {
	        	        int generatedId = rsId.getInt(1);	        	        
	        	        return generatedId;
	        	    }
	        }
	        
	    }catch(SQLException e) {
	    	if (e.getMessage().contains("UNIQUE constraint failed")) {
	    		lblDurum.setText("Durum : Bu Kod kullanımda.");
	    	}
	    }catch (Exception e) {
	    	
	        e.printStackTrace();
	    }
	    return -1;
	}
	
	public void isEmriKaydet(Connection conn, int siparisId) {
	    DefaultTableModel model = (DefaultTableModel) table.getModel();
	    String sql = "INSERT INTO is_emirleri(siparis_id, gorev, makine) VALUES(?,?,?)";
	    
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        
	        for (int i = 0; i < model.getRowCount(); i++) {
	        	Object gorevObj = model.getValueAt(i, 1);
	            Object makineObj = model.getValueAt(i, 2);
	            if (gorevObj == null || gorevObj.toString().trim().isEmpty() || gorevObj.toString().equals("Görev Girin")) {
	                continue;
	            }
	            String gorev = gorevObj.toString();
	            String makine;
	            if (makineObj != null && !makineObj.toString().equals("Makine Seçin")) {
	                makine = makineObj.toString();
	            } else {
	                makine = "Belirtilmedi";
	            }
	            pstmt.setInt(1, siparisId);
	            pstmt.setString(2, gorev);
	            pstmt.setString(3, makine);
	            pstmt.executeUpdate();
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        lblDurum.setText("Hata: İş emirleri kaydedilemedi!");
	    }
	}
	
	public void formDoldur(String kod) {
		if (table.isEditing()) {
	        table.getCellEditor().stopCellEditing();
	    }
		String sqlSiparis = "SELECT * FROM siparisler WHERE siparis_kodu = ?";
	    String sqlIsEmirleri = "SELECT * FROM is_emirleri WHERE siparis_id = ?";
	    DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
	    tableModel.setRowCount(0);
	    try (Connection conn = Veritabani.getInstance().getConnection();
	            PreparedStatement pstmtSiparis = conn.prepareStatement(sqlSiparis)) {
	           
	           pstmtSiparis.setString(1, kod);
	           ResultSet rsSiparis = pstmtSiparis.executeQuery();
	           
	           if (rsSiparis.next()) {
	               int siparisId = rsSiparis.getInt("id");
	               this.seciliSiparisId = siparisId;
	               txtSiparisAdi.setText(rsSiparis.getString("siparis_adi"));
	               txtSiparisKodu.setText(rsSiparis.getString("siparis_kodu"));
	               txtMusteri.setText(rsSiparis.getString("musteri"));
	               textUrun.setText(rsSiparis.getString("urun_adi"));
	               spinnerMiktar.setValue(rsSiparis.getInt("miktar"));
	               txtTarih.setText(rsSiparis.getString("termin_tarihi"));

	               try (PreparedStatement pstmtIsEmri = conn.prepareStatement(sqlIsEmirleri)) {
	                   pstmtIsEmri.setInt(1, siparisId);
	                   ResultSet rsIsEmri = pstmtIsEmri.executeQuery();
	                   
	                   while (rsIsEmri.next()) {	                       
	                       tableModel.addRow(new Object[] {
	                           "İE-10" + (tableModel.getRowCount()+1),
	                           rsIsEmri.getString("gorev"),
	                           rsIsEmri.getString("makine")
	                       });
	                   }
	               }
	           } else {
	               lblDurum.setText("Durum : Sipariş Bulunamadı.");
	           }
	           
	       } catch (SQLException e) {
	           e.printStackTrace();
	           lblDurum.setText("Durum : Veriler yüklenirken bir hata oluştu.");
	       }
	}

	public void siparisGuncelle(int mevcutSiparisId) {
		if(txtSiparisAdi.getText().trim().isEmpty()) {
			lblDurum.setText("Durum: Sipariş Adı boş bırakılamaz.");
	        return;
		}
		String sql = "UPDATE siparisler SET siparis_adi=?, miktar=?, termin_tarihi=? WHERE id=?";
	    String sqlIsEmirleri = "DELETE FROM is_emirleri WHERE siparis_id = ?";
	    try (Connection conn = Veritabani.getInstance().getConnection()) {
	    	conn.setAutoCommit(false);
	    	
	        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
	        pstmt.setString(1, txtSiparisAdi.getText());
	        pstmt.setInt(2, (int) spinnerMiktar.getValue());
	        pstmt.setString(3, txtTarih.getText());
	        pstmt.setInt(4, mevcutSiparisId);
	        pstmt.executeUpdate();
	        }
	        
	        try (PreparedStatement pstmtSil = conn.prepareStatement(sqlIsEmirleri)) {
	            pstmtSil.setInt(1, mevcutSiparisId);
	            pstmtSil.executeUpdate();
	        }
	        isEmriKaydet(conn, mevcutSiparisId);
	        conn.commit();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        lblDurum.setText("Durum : Güncelleme yapılamadı.");
	    }
	}
	
	private void formuTemizle() {
		if (table.isEditing()) {
	        table.getCellEditor().stopCellEditing();
	    }
	    seciliSiparisId = -1;
	    txtSiparisAdi.setText("");
	    txtMusteri.setText("");
	    textUrun.setText("");
	    txtSiparisKodu.setText("");
	    spinnerMiktar.setValue(0);
	    txtTarih.setText("gg.aa.yyyy");
	    ((DefaultTableModel) table.getModel()).setRowCount(0);
	}
	
	private void listeyiYenile() {
	    DefaultListModel<String> model = (DefaultListModel<String>) siparisListesi.getModel();
	    model.removeAllElements();

	    String sql = "SELECT siparis_kodu, siparis_adi FROM siparisler ORDER BY id DESC";

	    try (Connection conn = Veritabani.getInstance().getConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {

	        while (rs.next()) {
	            model.addElement(rs.getString("siparis_kodu") + " - " + rs.getString("siparis_adi"));
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        lblDurum.setText("Hata: Liste güncellenemedi!");
	    }
	}
}
