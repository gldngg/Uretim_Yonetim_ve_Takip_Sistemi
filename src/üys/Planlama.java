package üys;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import üys.Session;
import üys.Veritabani;

public class Planlama extends JFrame {

	private JPanel contentPane;
	private String takvimModu = "HAFTA"; 
	private LocalDate seciliTarih = LocalDate.now();

	private LocalDate haftaBasi;
	private JLabel lblHaftaAraligi;

	private JTable isEmriTable;
	private DefaultTableModel isEmriModel;

	private JProgressBar pbMakine;
	private JProgressBar pbIsci;
	private JPanel piePanel;
	private int pieStokta    = 50;
	private int pieSipariste = 30;
	private int pieEksik     = 20;

	private JTable table;
	private javax.swing.table.DefaultTableModel model;

	private static final DateTimeFormatter TR_FMT =
			DateTimeFormatter.ofPattern("dd.MM.yyyy");

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(() -> {
			Planlama planlama = new Planlama();
			planlama.setVisible(true);
		});
	}

	public Planlama() {
		
		haftaBasi = LocalDate.now().with(DayOfWeek.MONDAY);

		setTitle("ÜRETİM YÖNETİM SİSTEMİ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setSize(1100, 760);
		setResizable(false);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(142, 155, 213));
		setContentPane(contentPane);

		JPanel panelUstMenu = new JPanel();
		panelUstMenu.setBounds(30, 20, 1040, 70);
		panelUstMenu.setBackground(new Color(63, 81, 181));
		panelUstMenu.setLayout(null);
		contentPane.add(panelUstMenu);

		JLabel lblLogo = new JLabel("LOGO");
		lblLogo.setBounds(10, 18, 40, 28);
		lblLogo.setForeground(Color.WHITE);
		panelUstMenu.add(lblLogo);

		JLabel lblBaslık = new JLabel("Üretim Planlama");
		lblBaslık.setBounds(50, 18, 200, 30);
		lblBaslık.setForeground(Color.WHITE);
		lblBaslık.setFont(new Font("Tahoma", Font.BOLD, 20));
		panelUstMenu.add(lblBaslık);

		JButton btnAnaSayfa = new JButton("Ana Sayfa");
		btnAnaSayfa.setBounds(250, 20, 100, 25);
		btnAnaSayfa.setForeground(Color.WHITE);
		btnAnaSayfa.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAnaSayfa.setContentAreaFilled(false);
		btnAnaSayfa.setBorderPainted(false);
		btnAnaSayfa.setFocusPainted(false);
		panelUstMenu.add(btnAnaSayfa);
		
		btnAnaSayfa.addActionListener(e -> { 
			new AdminMenu().setVisible(true); 
			dispose(); 
		});
		
		
		JButton btnDurus_Kayıp = new JButton("Duruş/Kayıp");
		btnDurus_Kayıp.setBounds(360, 20, 120, 25);
		btnDurus_Kayıp.setForeground(Color.WHITE);
		btnDurus_Kayıp.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnDurus_Kayıp.setContentAreaFilled(false);
		btnDurus_Kayıp.setBorderPainted(false);
		btnDurus_Kayıp.setFocusPainted(false);
		panelUstMenu.add(btnDurus_Kayıp);
		
		btnDurus_Kayıp.addActionListener(e -> {
			new DuruşKayıp().setVisible(true); 
			dispose(); 	
		});

		JButton btnMakineGirisi = new JButton("Makine Girişi");
		btnMakineGirisi.setBounds(480, 20, 120, 25);
		btnMakineGirisi.setForeground(Color.WHITE);
		btnMakineGirisi.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnMakineGirisi.setContentAreaFilled(false);
		btnMakineGirisi.setBorderPainted(false);
		btnMakineGirisi.setFocusPainted(false);
		panelUstMenu.add(btnMakineGirisi);
		
		btnMakineGirisi.addActionListener(e -> {
			new MakineGirisEkrani().setVisible(true);
			dispose();
		});

		JButton btnSiparis = new JButton("Sipariş");
		btnSiparis.setBounds(600, 20, 100, 25);
		btnSiparis.setForeground(Color.WHITE);
		btnSiparis.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSiparis.setContentAreaFilled(false);
		btnSiparis.setBorderPainted(false);
		btnSiparis.setFocusPainted(false);
		panelUstMenu.add(btnSiparis);
		
		btnSiparis.addActionListener(e -> {
			new SiparisGirisi().setVisible(true);
			dispose(); 
		});

		JButton btnRapor = new JButton("Rapor");
		btnRapor.setBounds(700, 20, 80, 25);
		btnRapor.setForeground(Color.WHITE);
		btnRapor.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnRapor.setContentAreaFilled(false);
		btnRapor.setBorderPainted(false);
		btnRapor.setFocusPainted(false);
		panelUstMenu.add(btnRapor);

		
		JLabel lblUser = new JLabel(Session.aktifKullanici);
		lblUser.setBounds(830, 24, 130, 20);
		lblUser.setForeground(Color.WHITE);
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelUstMenu.add(lblUser);

		JButton btnCikis = new JButton("ÇIKIŞ");
		btnCikis.setBounds(950, 18, 70, 30);
		btnCikis.setForeground(Color.BLACK);
		btnCikis.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelUstMenu.add(btnCikis);
		
		btnCikis.addActionListener(e -> {
			Session.aktifKullanici = "";
			new GirisEkrani().setVisible(true);
			dispose();
		});

		
		JPanel panelTakvim = new JPanel();
		panelTakvim.setBounds(30, 100, 1040, 250);
		panelTakvim.setBackground(Color.WHITE);
		panelTakvim.setLayout(new java.awt.BorderLayout());
		contentPane.add(panelTakvim);

		JPanel panelTakvimBaslik = new JPanel(null);
		panelTakvimBaslik.setBackground(new Color(245, 245, 245));
		panelTakvimBaslik.setPreferredSize(new java.awt.Dimension(1040, 42));
		
		
		JLabel lblTakvimBaslik = new JLabel("Üretim Takvimi");
		lblTakvimBaslik.setBounds(12, 8, 200, 26);
		lblTakvimBaslik.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelTakvimBaslik.add(lblTakvimBaslik);
		
		JButton btnGun = new JButton("Gün");
		btnGun.setBounds(550, 8, 60, 26);
		panelTakvimBaslik.add(btnGun);

		JButton btnHafta = new JButton("Hafta");
		btnHafta.setBounds(615, 8, 70, 26);
		panelTakvimBaslik.add(btnHafta);

		JButton btnAy = new JButton("Ay");
		btnAy.setBounds(690, 8, 60, 26);
		panelTakvimBaslik.add(btnAy);
		
		
		btnGun.addActionListener(e -> {
		    takvimModu = "GUN";
		    tabloyuAyarla();
		    takvimGuncelle();
		});

		btnHafta.addActionListener(e -> {
		    takvimModu = "HAFTA";
		    tabloyuAyarla();
		    takvimGuncelle();
		});

		btnAy.addActionListener(e -> {
		    takvimModu = "AY";
		    tabloyuAyarla();
		    takvimGuncelle();
		});
		

		JButton btnOnceki = new JButton("◀");
		btnOnceki.setBounds(790, 12, 43, 20);
		btnOnceki.setBackground(new Color(63, 81, 181));
		btnOnceki.setForeground(Color.WHITE);
		btnOnceki.setFocusPainted(false);
		btnOnceki.setBorderPainted(false);
		btnOnceki.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelTakvimBaslik.add(btnOnceki);

		lblHaftaAraligi = new JLabel(haftaEtiketi(), SwingConstants.CENTER);
		lblHaftaAraligi.setBounds(830, 8, 160, 26);
		lblHaftaAraligi.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panelTakvimBaslik.add(lblHaftaAraligi);

		JButton btnSonraki = new JButton("▶");
		btnSonraki.setBounds(992, 12, 43, 20);
		btnSonraki.setBackground(new Color(63, 81, 181));
		btnSonraki.setForeground(Color.WHITE);
		btnSonraki.setFocusPainted(false);
		btnSonraki.setBorderPainted(false);
		btnSonraki.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelTakvimBaslik.add(btnSonraki);

		panelTakvim.add(panelTakvimBaslik, java.awt.BorderLayout.NORTH);

		btnOnceki.addActionListener(e -> {
		    switch (takvimModu) {
		        case "GUN":   seciliTarih = seciliTarih.minusDays(1);   break;
		        case "HAFTA": seciliTarih = seciliTarih.minusWeeks(1);  break;
		        case "AY":    seciliTarih = seciliTarih.minusMonths(1); break;
		    }
		    tabloyuAyarla();
		    takvimGuncelle();
		});

		btnSonraki.addActionListener(e -> {
		    switch (takvimModu) {
		        case "GUN":   seciliTarih = seciliTarih.plusDays(1);   break;
		        case "HAFTA": seciliTarih = seciliTarih.plusWeeks(1);  break;
		        case "AY":    seciliTarih = seciliTarih.plusMonths(1); break;
		    }
		    tabloyuAyarla();
		    takvimGuncelle();
		});

				
		String[] columns = {"Pzt", "Sal", "Çar", "Per", "Cum", "Cmt", "Paz"};
		model = new DefaultTableModel(columns, 8) {
			@Override public boolean isCellEditable(int r, int c) { return false; }
		};
		
		table = new JTable(model);

		table.setRowHeight(26);
		table.setGridColor(new Color(230, 230, 230));
		table.setFillsViewportHeight(true);
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 12));
		table.getTableHeader().setBackground(new Color(230, 235, 250));
		table.getTableHeader().setReorderingAllowed(false);

		for (int i = 0; i < table.getColumnCount(); i++)
			table.getColumnModel().getColumn(i).setCellRenderer(new TakvimHucresiRenderer());

		JScrollPane takvimScroll = new JScrollPane(table);
		panelTakvim.add(takvimScroll, java.awt.BorderLayout.CENTER);

		
		JPanel panelIsEmri = new JPanel();
		panelIsEmri.setBounds(30, 370, 650, 300);
		panelIsEmri.setBackground(Color.WHITE);
		panelIsEmri.setLayout(null);
		contentPane.add(panelIsEmri);

		JPanel panelBaslik = new JPanel();
		panelBaslik.setBackground(new Color(245, 245, 245));
		panelBaslik.setBounds(0, 0, 650, 45);
		panelBaslik.setLayout(null);
		panelIsEmri.add(panelBaslik);

		JLabel lblFormBaslik = new JLabel("İş Emri Detayları");
		lblFormBaslik.setBounds(20, 10, 250, 25);
		lblFormBaslik.setForeground(new Color(60, 60, 60));
		lblFormBaslik.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelBaslik.add(lblFormBaslik);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 45, 650, 1);
		panelIsEmri.add(separator);

		
		isEmriModel = new DefaultTableModel(
			    new String[]{"İş Emri", "Ürün", "Başlangıç Tarihi", "Bitiş Tarihi", "Durum"}, 0) {
			    @Override public boolean isCellEditable(int r, int c) { return false; }
			};

		isEmriTable = new JTable(isEmriModel);
		isEmriTable.getTableHeader().setReorderingAllowed(false);
		isEmriTable.setFont(new Font("Tahoma", Font.PLAIN, 12));
		isEmriTable.setRowHeight(28);
		isEmriTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 12));
		isEmriTable.getTableHeader().setBackground(new Color(230, 235, 250));

		
		isEmriTable.getColumnModel().getColumn(4).setCellRenderer(new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable t, Object value,
					boolean sel, boolean focus, int row, int col) {
				JLabel lbl = (JLabel) super.getTableCellRendererComponent(t, value, sel, focus, row, col);
				lbl.setHorizontalAlignment(JLabel.CENTER);
				lbl.setFont(new Font("Tahoma", Font.BOLD, 11));
				lbl.setOpaque(true);
				String v = (value != null) ? value.toString() : "";
				switch (v) {
					case "Devam Ediyor": lbl.setBackground(new Color( 40,167, 69)); lbl.setForeground(Color.WHITE); break;
					case "Planlandı":    lbl.setBackground(new Color(255,152,  0)); lbl.setForeground(Color.WHITE); break;
					case "Tamamlandı":   lbl.setBackground(new Color(108,117,125)); lbl.setForeground(Color.WHITE); break;
					default:             lbl.setBackground(Color.WHITE);            lbl.setForeground(Color.BLACK);
				}
				return lbl;
			}
		});

		int[] widths = {80, 100, 180, 110, 90};
		for (int i = 0; i < widths.length; i++)
			isEmriTable.getColumnModel().getColumn(i).setPreferredWidth(widths[i]);

		JScrollPane isEmriScroll = new JScrollPane(isEmriTable);
		isEmriScroll.setBounds(10, 55, 630, 235);
		panelIsEmri.add(isEmriScroll);

		
		JPanel panelKaynakKullanımı = new JPanel();
		panelKaynakKullanımı.setBounds(700, 370, 370, 300);
		panelKaynakKullanımı.setBackground(Color.WHITE);
		panelKaynakKullanımı.setLayout(null);
		contentPane.add(panelKaynakKullanımı);

		JPanel kaynakBaslik = new JPanel(null);
		kaynakBaslik.setBackground(new Color(245, 245, 245));
		kaynakBaslik.setBounds(0, 0, 370, 45);
		panelKaynakKullanımı.add(kaynakBaslik);

		JLabel lblKaynakBaslik = new JLabel("Kaynak Kullanımı");
		lblKaynakBaslik.setBounds(15, 10, 220, 25);
		lblKaynakBaslik.setFont(new Font("Tahoma", Font.BOLD, 16));
		kaynakBaslik.add(lblKaynakBaslik);

		JSeparator sepKaynak = new JSeparator();
		sepKaynak.setBounds(0, 45, 370, 1);
		panelKaynakKullanımı.add(sepKaynak);

		JLabel lblMakineLbl = new JLabel("Makine Kullanımı");
		lblMakineLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMakineLbl.setBounds(15, 55, 200, 18);
		panelKaynakKullanımı.add(lblMakineLbl);

		pbMakine = new JProgressBar(0, 100);
		pbMakine.setBounds(15, 75, 265, 18);
		pbMakine.setStringPainted(true);
		pbMakine.setForeground(new Color(40, 167, 69));
		pbMakine.setFont(new Font("Tahoma", Font.BOLD, 10));
		panelKaynakKullanımı.add(pbMakine);

		JLabel lblIsciLbl = new JLabel("İşçi Kullanımı");
		lblIsciLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblIsciLbl.setBounds(15, 103, 200, 18);
		panelKaynakKullanımı.add(lblIsciLbl);

		pbIsci = new JProgressBar(0, 100);
		pbIsci.setBounds(15, 123, 265, 18);
		pbIsci.setStringPainted(true);
		pbIsci.setForeground(new Color(0, 123, 255));
		pbIsci.setFont(new Font("Tahoma", Font.BOLD, 10));
		panelKaynakKullanımı.add(pbIsci);

		piePanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				int total = pieStokta + pieSipariste + pieEksik;
				if (total == 0) total = 1;

				Color[]  renkler  = { new Color(0, 123, 255), new Color(255, 152, 0), new Color(220, 53, 69) };
				int[]    degerler = { pieStokta, pieSipariste, pieEksik };
				String[] etiket   = { "Stokta", "Siparişte", "Eksik" };

				int startAngle = 0;
				int cx = 15, cy = 10, diameter = 100;
				for (int i = 0; i < 3; i++) {
					int arc = (int) Math.round(360.0 * degerler[i] / total);
					g2.setColor(renkler[i]);
					g2.fillArc(cx, cy, diameter, diameter, startAngle, arc);
					startAngle += arc;
				}
				g2.setColor(Color.WHITE);
				g2.drawOval(cx, cy, diameter, diameter);

				for (int i = 0; i < 3; i++) {
					g2.setColor(renkler[i]);
					g2.fillRect(140, 20 + i * 28, 14, 14);
					g2.setColor(Color.BLACK);
					g2.setFont(new Font("Tahoma", Font.PLAIN, 12));
					g2.drawString(etiket[i], 160, 32 + i * 28);
				}
			}
		};
		piePanel.setBackground(Color.WHITE);
		piePanel.setBounds(15, 152, 340, 135);
		panelKaynakKullanımı.add(piePanel);

		tabloyuAyarla();
		verileriYukle();
	}

	public class TakvimHucresiRenderer extends DefaultTableCellRenderer {
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value,
				boolean isSelected, boolean hasFocus, int row, int column) {

			Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			setHorizontalAlignment(JLabel.CENTER);
			setFont(new Font("Tahoma", Font.BOLD, 11));

			if (value != null && !value.toString().isEmpty()) {
				String gorev = value.toString();
				if      (gorev.contains("Montaj"))                          { c.setBackground(new Color(220,  53,  69)); c.setForeground(Color.WHITE); } // Kırmızı
				else if (gorev.contains("CNC") || gorev.contains("Kesim")) { c.setBackground(new Color( 40, 167,  69)); c.setForeground(Color.WHITE); } // Yeşil
				else if (gorev.contains("Paketleme"))                       { c.setBackground(new Color(255, 152,   0)); c.setForeground(Color.WHITE); } // Turuncu
				else if (gorev.contains("Kalite"))                          { c.setBackground(new Color(  0, 123, 255)); c.setForeground(Color.WHITE); } // Mavi
				else if (gorev.contains("Dolum"))                           { c.setBackground(new Color(156,  39, 176)); c.setForeground(Color.WHITE); } // Mor
				else if (gorev.contains("Etiket"))                          { c.setBackground(new Color(  0, 150, 136)); c.setForeground(Color.WHITE); } 
				else if (gorev.contains("Kapak"))                           { c.setBackground(new Color(103,  58, 183)); c.setForeground(Color.WHITE); } // Koyu mor
				else if (gorev.contains("Karışım"))                         { c.setBackground(new Color(255,  87,  34)); c.setForeground(Color.WHITE); } // Koyu turuncu
				else                                                         { c.setBackground(new Color( 63,  81, 181)); c.setForeground(Color.WHITE); } // İndigo
			} else {
				c.setBackground(Color.WHITE);
				c.setForeground(Color.BLACK);
			}
			return c;
		}
	}
	
	private String haftaEtiketi() {
		LocalDate bitisTarihi = haftaBasi.plusDays(6);
		return haftaBasi.format(TR_FMT) + " – " + bitisTarihi.format(TR_FMT);
	}
	
	private void tabloyuAyarla() {
		
		for (int r = 0; r < model.getRowCount(); r++) {
	        for (int c = 0; c < model.getColumnCount(); c++) {
	            model.setValueAt("", r, c);
	        }
	    }

	    if ("GUN".equals(takvimModu)) {

	        model.setColumnCount(1);
	        model.setColumnIdentifiers(new String[]{"Gün"});
	        model.setRowCount(10);

	        lblHaftaAraligi.setText(seciliTarih.format(TR_FMT));
	    }

	    else if ("HAFTA".equals(takvimModu)) {

	        model.setColumnCount(7);
	        model.setColumnIdentifiers(new String[]{"Pzt","Sal","Çar","Per","Cum","Cmt","Paz"});
	        model.setRowCount(8);

	        haftaBasi = seciliTarih.with(DayOfWeek.MONDAY);
	        lblHaftaAraligi.setText(
	            haftaBasi.format(TR_FMT) + " - " +
	            haftaBasi.plusDays(6).format(TR_FMT)
	        );
	    }

	    else if ("AY".equals(takvimModu)) {

	        model.setColumnCount(7);
	        model.setColumnIdentifiers(new String[]{"Pzt","Sal","Çar","Per","Cum","Cmt","Paz"});
	        model.setRowCount(6);

	        lblHaftaAraligi.setText(
	        	seciliTarih.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault()) + " " +
	        	seciliTarih.getYear()
	        );
	    }
	    
	    table.getTableHeader().resizeAndRepaint();
	    
	    for (int i = 0; i < table.getColumnCount(); i++) {
	        table.getColumnModel().getColumn(i).setCellRenderer(new TakvimHucresiRenderer());
	    }
	    table.repaint();
	}


	private void takvimGuncelle() {

	    for (int r = 0; r < model.getRowCount(); r++)
	        for (int c = 0; c < model.getColumnCount(); c++)
	            model.setValueAt("", r, c);

	    String sql = "SELECT s.termin_tarihi, ie.gorev FROM siparisler s " +
	                 "JOIN is_emirleri ie ON ie.siparis_id = s.id";

	    try (Connection conn = Veritabani.getInstance().getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {

	            String tarihStr = rs.getString("termin_tarihi");
	            String gorev = rs.getString("gorev");

	            if (tarihStr == null || tarihStr.trim().isEmpty()) { continue; }

	            LocalDate tarih;
	            try {
	                tarih = LocalDate.parse(tarihStr, TR_FMT);
	            } catch (java.time.format.DateTimeParseException e) {
	                System.err.println("Geçersiz tarih formatı: " + tarihStr);
	                continue; 
	            }

	            
	            if ("GUN".equals(takvimModu)) {

	                if (tarih.equals(seciliTarih)) {
	                    for (int r = 0; r < model.getRowCount(); r++) {
	                    	Object val = model.getValueAt(r, 0);
	                    	if (val == null || val.toString().isEmpty()) {
	                            model.setValueAt(gorev, r, 0);
	                            break;
	                        }
	                    }
	                }
	            }

	            
	            else if ("HAFTA".equals(takvimModu)) {

	            	 int dayOfWeek = tarih.getDayOfWeek().getValue() - 1; 

		                if (tarih.isAfter(haftaBasi.minusDays(1)) && tarih.isBefore(haftaBasi.plusWeeks(1))) {
		                    for (int r = 0; r < model.getRowCount(); r++) {
		                        Object currentValue = model.getValueAt(r, dayOfWeek);
		                        if (currentValue == null || currentValue.toString().isEmpty()) {
		                            model.setValueAt(gorev, r, dayOfWeek);
		                            break;
		                        } else if (r == model.getRowCount() - 1) { 
		                            if (model.getRowCount() < 12) { 
		                                model.addRow(new String[model.getColumnCount()]);
		                                model.setValueAt(gorev, model.getRowCount() - 1, dayOfWeek);
		                                break;
		                            }
		                        }
		                    }
		                }
	            }

	            
	            else if ("AY".equals(takvimModu)) {
	            	if (tarih.getMonth() == seciliTarih.getMonth()
	                        && tarih.getYear() == seciliTarih.getYear()) {

	                    LocalDate firstDayOfMonth = seciliTarih.withDayOfMonth(1);
	                    int offset = firstDayOfMonth.getDayOfWeek().getValue() - 1; 

	                    int dayOfMonth = tarih.getDayOfMonth();
	                    int colIndex = (dayOfMonth - 1 + offset) % 7;
	                    int rowIndex = (dayOfMonth - 1 + offset) / 7;

	                    if (rowIndex < model.getRowCount() && colIndex < model.getColumnCount()) {
	                        Object currentVal = model.getValueAt(rowIndex, colIndex);
	                        String cellContent = (currentVal != null && !currentVal.toString().isEmpty()) ?
	                                currentVal.toString() + ", " + gorev : gorev;
	                        model.setValueAt(cellContent, rowIndex, colIndex);
	                    }
	                }
	            }
	            

	                
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	
	private void verileriYukle() {
		takvimGuncelle();
		isEmriModel.setRowCount(0);

		String sql = "SELECT s.siparis_kodu, s.urun_adi, s.termin_tarihi, ie.gorev " +
				     "FROM siparisler s " +
				     "JOIN is_emirleri ie ON ie.siparis_id = s.id " +
				     "ORDER BY s.id, ie.id";

		int toplamIsEmri = 0;
		int tamamlandi   = 0;
		int devamEdiyor  = 0;
		int counter      = 101;

		try (Connection conn = Veritabani.getInstance().getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
			    String sipKodu   = rs.getString("siparis_kodu");
			    String urunAdi   = rs.getString("urun_adi");
			    String terminStr = rs.getString("termin_tarihi");
			    String gorev     = rs.getString("gorev");

			    toplamIsEmri++;

			    String durum = "Planlandı";

			    try {
			        if (terminStr != null && !terminStr.trim().isEmpty()) {
			            LocalDate termin = LocalDate.parse(terminStr, TR_FMT);
			            LocalDate bugun  = LocalDate.now();

			            if (termin.isBefore(bugun)) {
			                durum = "Tamamlandı";
			                tamamlandi++;
			            } else if (termin.isEqual(bugun)) {
			                durum = "Devam Ediyor";
			                devamEdiyor++;
			            }
			        }
			    } catch (java.time.format.DateTimeParseException e) {
			        System.err.println("Termin tarihi format hatası: " + terminStr);
			        durum = "Geçersiz Tarih";
			    }

			    isEmriModel.addRow(new Object[]{
			        "İE-" + counter++,
			        sipKodu,
			        urunAdi + " – " + gorev,
			        terminStr != null ? terminStr : "-",
			        durum
			    });
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		int toplamMakine  = makineAdediGetir();
		int kullanilanMak = Math.min(toplamIsEmri - tamamlandi, toplamMakine);
		int makineYzdsi   = (toplamMakine > 0) ? (int) Math.round(kullanilanMak * 100.0 / toplamMakine) : 0;
		makineYzdsi = Math.min(makineYzdsi, 100);
		int isciYzdsi     = (toplamIsEmri > 0) ? (int) Math.round((devamEdiyor * 100.0 / toplamIsEmri) * 0.8 + 20) : 0; 
		isciYzdsi = Math.min(isciYzdsi, 100);
		
		pbMakine.setValue(makineYzdsi);
		pbMakine.setString(makineYzdsi + "%");
		pbIsci.setValue(isciYzdsi);
		pbIsci.setString(isciYzdsi + "%");

		
		if (toplamIsEmri > 0) {
			pieStokta    = (int) Math.round(tamamlandi * 100.0 / toplamIsEmri);
			int s_remaining = toplamIsEmri - tamamlandi;
			pieSipariste = (int) Math.round((s_remaining - devamEdiyor) * 100.0 / toplamIsEmri); // planlandı
			pieEksik     = (int) Math.round(devamEdiyor * 100.0 / toplamIsEmri);
			
			int currentTotal = pieStokta + pieSipariste + pieEksik;
            if (currentTotal != 100) {
                pieSipariste += (100 - currentTotal);
            }
            
		} else {
			pieStokta = 0; pieSipariste = 0; pieEksik = 100;
		}
		piePanel.repaint(); 
	}

	
	private int makineAdediGetir() {
		int machineCount = 0;
		try (Connection conn = Veritabani.getInstance().getConnection();
			 Statement  stmt = conn.createStatement();
			 ResultSet  rs   = stmt.executeQuery("SELECT COUNT(*) FROM machines")) {
			if (rs.next()) {
				machineCount = rs.getInt(1);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Math.max(machineCount, 1);
	}
}
