package üys;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Window.Type;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

public class DuruşKayıp extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_baslangic;
	private JTextField txt_makinetipi;
	private JLabel jlb_durusnedeni;
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

private Map<String, String> makineMap = new HashMap<>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DuruşKayıp frame = new DuruşKayıp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DuruşKayıp() {
		setResizable(false);
		setTitle("Duruş / Kayıp");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 760);
		setTitle("Duruş / Kayıp");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		jlb_logo.setBackground(new Color(255, 255, 255));
		
		JLabel jlv_duruskayip = new JLabel("DURUŞ/KAYIP");
		jlv_duruskayip.setFont(new Font("Tahoma", Font.BOLD, 20));
		jlv_duruskayip.setForeground(new Color(255, 255, 255));
		jlv_duruskayip.setBounds(50, 18, 200, 30);
		panelUstMenu.add(jlv_duruskayip);
		
		JLabel jlb_ana = new JLabel("Ana Sayfa");
		jlb_ana.setFont(new Font("Tahoma", Font.BOLD, 13));
		jlb_ana.setForeground(new Color(255, 255, 255));
		jlb_ana.setBounds(250, 22, 75, 20);
		panelUstMenu.add(jlb_ana);
		
		JLabel jlb_makine = new JLabel("Makine Girişi");
		jlb_makine.setFont(new Font("Tahoma", Font.BOLD, 13));
		jlb_makine.setForeground(new Color(255, 255, 255));
		jlb_makine.setBounds(360, 22, 95, 20);
		panelUstMenu.add(jlb_makine);
		
		JLabel jlb_siparis = new JLabel("Sipariş");
		jlb_siparis.setFont(new Font("Tahoma", Font.BOLD, 13));
		jlb_siparis.setForeground(new Color(255, 255, 255));
		jlb_siparis.setBounds(490, 22, 60, 20);
		panelUstMenu.add(jlb_siparis);
		
		JLabel jlb_plan = new JLabel("Planlama");
		jlb_plan.setFont(new Font("Tahoma", Font.BOLD, 13));
		jlb_plan.setForeground(new Color(255, 255, 255));
		jlb_plan.setBounds(590, 22, 75, 20);
		panelUstMenu.add(jlb_plan);
		
		JLabel jlb_rapor = new JLabel("Rapor");
		jlb_rapor.setFont(new Font("Tahoma", Font.BOLD, 13));
		jlb_rapor.setForeground(new Color(255, 255, 255));
		jlb_rapor.setBounds(700, 22, 55, 20);
		panelUstMenu.add(jlb_rapor);
		
		JButton btn_cikis = new JButton("ÇIKIŞ");
		btn_cikis.setForeground(new Color(0, 0, 0));
		btn_cikis.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btn_cikis.setBounds(950, 18, 70, 30);
		panelUstMenu.add(btn_cikis);
		
		JLabel jlb_kullanici = new JLabel("Ad Soyad");
		jlb_kullanici.setFont(new Font("Tahoma", Font.PLAIN, 12));
		jlb_kullanici.setForeground(new Color(255, 255, 255));
		jlb_kullanici.setBounds(860, 24, 90, 20);
		panelUstMenu.add(jlb_kullanici);
		
		JPanel panelForm = new JPanel();
		panelForm.setBounds(20, 100, 1040, 390);
		contentPane.add(panelForm);
		panelForm.setLayout(null);
		
		JLabel jlb_durusform = new JLabel("Duruş/Kayıp Girişi");
		jlb_durusform.setFont(new Font("Tahoma", Font.BOLD, 15));
		jlb_durusform.setBounds(20, 15, 250, 30);
		panelForm.add(jlb_durusform);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 55, 940, 2);
		panelForm.add(separator);
		
		JLabel jlb_makinetipi = new JLabel("Makine Tipi");
		jlb_makinetipi.setFont(new Font("Tahoma", Font.PLAIN, 12));
		jlb_makinetipi.setBounds(480, 75, 120, 20);
		panelForm.add(jlb_makinetipi);
		
		makineMap = new HashMap<>();

		makineMap.put("DLM-01", "Dolum Makinesi");
		makineMap.put("DLM-02", "Dolum Makinesi");

		makineMap.put("KST-01", "Karıştırma Makinesi");
		makineMap.put("KST-02", "Karıştırma Makinesi");

		makineMap.put("PKT-01", "Paketleme Makinesi");
		makineMap.put("PKT-02", "Paketleme Makinesi");
		
		cmb_makinekodu = new JComboBox<>();
		cmb_makinekodu.setModel(new DefaultComboBoxModel<>(new String[] {
		    "DLM-01", "DLM-02", "KST-01", "KST-02", "PKT-01", "PKT-02"
		}));
		cmb_makinekodu.setBounds(20, 100, 430, 30);
		panelForm.add(cmb_makinekodu);
		cmb_makinekodu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String secilenKod = cmb_makinekodu.getSelectedItem().toString();
				txt_makinetipi.setText(makineMap.get(secilenKod));
			}
		});
		
		JLabel jlb_durusturu = new JLabel("Duruş Türü");
		jlb_durusturu.setFont(new Font("Tahoma", Font.PLAIN, 12));
		jlb_durusturu.setBounds(20, 145, 120, 20);
		panelForm.add(jlb_durusturu);
		
		cmb_durusturu = new JComboBox<>();
		cmb_durusturu.setModel(new DefaultComboBoxModel<>(new String[] {
		    "Planlı", "Plansız"
		    
		
		}));
		makineMap.put("DLM-01", "Dolum Makinesi");
		makineMap.put("KRS-01", "Karıştırma Makinesi");
		makineMap.put("PKT-01", "Paketleme Makinesi");
		makineMap.put("DLM-02", "Dolum Makinesi");
		makineMap.put("KRS-02", "Karıştırma Makinesi");
		makineMap.put("PKT-02", "Paketleme Makinesi");
		cmb_durusturu.setBounds(20, 170, 430, 30);
		panelForm.add(cmb_durusturu);
		
		JLabel jlb_baslangic = new JLabel("Başlangıç Zamanı");
		jlb_baslangic.setFont(new Font("Tahoma", Font.PLAIN, 12));
		jlb_baslangic.setBounds(20, 215, 140, 20);
		panelForm.add(jlb_baslangic);
		
		txt_baslangic = new JTextField();
		txt_baslangic.setBounds(20, 240, 430, 30);
		panelForm.add(txt_baslangic);
		txt_baslangic.setColumns(10);
		
		JLabel jlb_makinekodu = new JLabel("Makine Kodu");
		jlb_makinekodu.setFont(new Font("Tahoma", Font.PLAIN, 12));
		jlb_makinekodu.setBounds(20, 75, 120, 20);
		panelForm.add(jlb_makinekodu);
		
		txt_makinetipi = new JTextField();
		txt_makinetipi.setEditable(false);
		txt_makinetipi.setBounds(480, 100, 430, 30);
		panelForm.add(txt_makinetipi);
		txt_makinetipi.setColumns(10);
		
		jlb_durusnedeni = new JLabel("Duruş Nedeni");
		jlb_durusnedeni.setFont(new Font("Tahoma", Font.PLAIN, 12));
		jlb_durusnedeni.setBounds(480, 145, 120, 20);
		panelForm.add(jlb_durusnedeni);
		
		cmb_durusnedeni = new JComboBox<>();
		cmb_durusnedeni.setModel(new DefaultComboBoxModel<>(new String[] {
		    "Arıza", "Bakım", "Ham Madde Eksik", "Setup"
		}));
		cmb_durusnedeni.setBounds(480, 170, 430, 30);
		panelForm.add(cmb_durusnedeni);
		
		JLabel jlb_bitiszamani = new JLabel("Bitiş Zamanı");
		jlb_bitiszamani.setFont(new Font("Tahoma", Font.PLAIN, 12));
		jlb_bitiszamani.setBounds(480, 215, 120, 20);
		panelForm.add(jlb_bitiszamani);
		
		txt_bitis = new JTextField();
		txt_bitis.setBounds(480, 240, 430, 30);
		panelForm.add(txt_bitis);
		txt_bitis.setColumns(10);
		
		JLabel jlb_aciklama = new JLabel("Açıklama:");
		jlb_aciklama.setFont(new Font("Tahoma", Font.PLAIN, 12));
		jlb_aciklama.setBounds(20, 280, 90, 20);
		panelForm.add(jlb_aciklama);
		
		txt_aciklama = new JTextField();
		txt_aciklama.setBounds(105, 275, 805, 35);
		panelForm.add(txt_aciklama);
		txt_aciklama.setColumns(10);
		
		btn_kaydet = new JButton("Kaydet");
		btn_kaydet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String makineKodu = cmb_makinekodu.getSelectedItem().toString();
				String makineTipi = txt_makinetipi.getText();
				String baslangic = txt_baslangic.getText();
				String bitis = txt_bitis.getText();
				String durusTuru = cmb_durusturu.getSelectedItem().toString();
				String durusNedeni = cmb_durusnedeni.getSelectedItem().toString();
				String aciklama = txt_aciklama.getText();
				String sure = "40 dk";
				if (txt_makinetipi.getText().trim().isEmpty() ||
					    txt_baslangic.getText().trim().isEmpty() ||
					    txt_bitis.getText().trim().isEmpty() ||
					    txt_aciklama.getText().trim().isEmpty()) {

						JOptionPane.showMessageDialog(null, "Lütfen tüm alanları doldurun!");
						return;
					}

				if (duzenlemeModu && secilenSatir != -1) {
					model.setValueAt(makineTipi, secilenSatir, 0);
					model.setValueAt(makineKodu, secilenSatir, 1);
					model.setValueAt(baslangic, secilenSatir, 2);
					model.setValueAt(bitis, secilenSatir, 3);
					model.setValueAt(sure, secilenSatir, 4);
					model.setValueAt(durusTuru, secilenSatir, 5);
					model.setValueAt(durusNedeni, secilenSatir, 6);
					model.setValueAt(aciklama, secilenSatir, 7);

					formTemizle();
				} else {
					model.addRow(new Object[] {
							
						makineTipi,
						makineKodu,
						baslangic,
						bitis,
						sure,
						durusTuru,
						durusNedeni,
						aciklama,
						"Düzenle"
					});
					formTemizle();
				}
			}
		});
	
		btn_kaydet.setForeground(new Color(255, 255, 255));
		btn_kaydet.setBackground(new Color(52, 152, 219));
		btn_kaydet.setBounds(20, 335, 120, 35);
		panelForm.add(btn_kaydet);
		btn_kaydet.setFocusPainted(false);
		btn_kaydet.setContentAreaFilled(false);
		btn_kaydet.setOpaque(true);
		btn_kaydet.setBorderPainted(false);
		
		
		JButton btn_temizle = new JButton("Temizle");
		btn_temizle.setBackground(new Color(220, 220, 220));
		btn_temizle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formTemizle();
			}
		});
			
		btn_temizle.setBounds(150, 335, 120, 35);
		panelForm.add(btn_temizle);
		btn_temizle.setFocusPainted(false);
		btn_temizle.setContentAreaFilled(false);
		btn_temizle.setOpaque(true);
		btn_temizle.setBorderPainted(false);
		
		JPanel panelTablo = new JPanel();
		panelTablo.setBounds(20, 510, 1040, 190);
		contentPane.add(panelTablo);
		panelTablo.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 1020, 170);
		panelTablo.add(scrollPane);
		
		table = new JTable();

		model = new DefaultTableModel();
		table.setModel(model);

		model.addColumn("Makine Tipi");
		model.addColumn("Makine Kodu");
		model.addColumn("Başlangıç");
		model.addColumn("Bitiş");
		model.addColumn("Süre");
		model.addColumn("Duruş Türü");
		model.addColumn("Duruş Nedeni");
		model.addColumn("Açıklama");
		model.addColumn("İşlem");
		scrollPane.setViewportView(table);
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
				}
			}
		});}
	private void formTemizle() {
		cmb_makinekodu.setSelectedIndex(0);
		cmb_durusturu.setSelectedIndex(0);
		cmb_durusnedeni.setSelectedIndex(0);

		String secilenKod = cmb_makinekodu.getSelectedItem().toString();
		txt_makinetipi.setText(makineMap.get(secilenKod));

		txt_baslangic.setText("");
		txt_bitis.setText("");
		txt_aciklama.setText("");

		secilenSatir = -1;
		duzenlemeModu = false;
		btn_kaydet.setText("Kaydet");
		table.clearSelection();
	}
}
