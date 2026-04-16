package kayit;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class KayitEkrani extends JFrame {

	private JPanel contentPane;
	private JTextField usernameInput;
	private JPasswordField passwordInput;
	private JPasswordField passwordRepeatInput;
	private JRadioButton roleAdmin;
    private JRadioButton roleOperator;
    private JLabel messageBox;
    private ButtonGroup roleGroup;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KayitEkrani frame = new KayitEkrani();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public KayitEkrani() {
		//ana ekran
		setTitle("Kayıt Ol");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
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
		
		JLabel lblTitle = new JLabel("Kayıt Ol");
		lblTitle.setBackground(new Color(255, 255, 255));
		lblTitle.setBounds(0, 11, 900, 40);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitle.setForeground(new Color(255, 255, 255));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		headerPanel.add(lblTitle);
		
		//kayıt formu
		JPanel formPanel = new JPanel();
		formPanel.setBackground(Color.WHITE);
		formPanel.setBounds(60, 80, 780, 430);
		contentPane.add(formPanel);
		formPanel.setLayout(null);
		
		JLabel lblUser = new JLabel("Kullanıcı Adı :");//kullanıcı adı
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblUser.setBounds(20, 15, 150, 60);
		formPanel.add(lblUser);
		
		usernameInput = new JTextField();
		usernameInput.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0), 2), new EmptyBorder(2, 5, 2, 2)));
		usernameInput.setBackground(new Color(255, 255, 255));
		usernameInput.setFont(new Font("Tahoma", Font.PLAIN, 20));
		usernameInput.setBounds(250, 15, 500, 60);
		formPanel.add(usernameInput);
		usernameInput.setColumns(20);
		
		JLabel lblPass = new JLabel("Şifre :");//şifre
		lblPass.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblPass.setBounds(20, 80, 150, 60);
		formPanel.add(lblPass);
		
		passwordInput = new JPasswordField();
		passwordInput.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0), 2), new EmptyBorder(2, 5, 2, 2)));
		passwordInput.setFont(new Font("Tahoma", Font.PLAIN, 20));
		passwordInput.setBounds(250, 80, 500, 60);
		formPanel.add(passwordInput);
		
		JLabel lblPassRep = new JLabel("Şifre Tekrarı :");//şifre tekrar
		lblPassRep.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblPassRep.setBounds(20, 145, 150, 60);
		formPanel.add(lblPassRep);
		
		passwordRepeatInput = new JPasswordField();
		passwordRepeatInput.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0), 2), new EmptyBorder(2, 5, 2, 2)));
		passwordRepeatInput.setFont(new Font("Tahoma", Font.PLAIN, 20));
		passwordRepeatInput.setBounds(250, 145, 500, 60);
		formPanel.add(passwordRepeatInput);
		
		roleAdmin = new JRadioButton("Admin");//rol
		roleAdmin.setMargin(new Insets(2, 5, 2, 2));
		roleAdmin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		roleAdmin.setBackground(Color.WHITE);
		roleAdmin.setBounds(250, 210, 150, 60);
		formPanel.add(roleAdmin);
		
		roleOperator = new JRadioButton("Operatör");
		roleOperator.setMargin(new Insets(2, 5, 2, 2));
		roleOperator.setFont(new Font("Tahoma", Font.PLAIN, 20));
		roleOperator.setBackground(Color.WHITE);
		roleOperator.setBounds(420, 210, 150, 60);
		formPanel.add(roleOperator);
		
		roleGroup = new ButtonGroup();
		roleGroup.add(roleAdmin);      
		roleGroup.add(roleOperator);
		
		JButton btnRegister = new JButton("KAYIT OL");//butonlar
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRegister.setBackground(new Color(63, 81, 181));
		btnRegister.setForeground(new Color(255, 255, 255));
		btnRegister.setBounds(250, 275, 150, 60);
		formPanel.add(btnRegister);
		
		JButton btnLogin = new JButton("GİRİŞ EKRANINA DÖN");
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLogin.setBackground(new Color(63, 81, 181));
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setBounds(420, 275, 330, 60);
		formPanel.add(btnLogin);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(0, 0, 0));
        separator.setBounds(20, 340, 740, 5);
        formPanel.add(separator);

        messageBox = new JLabel("Durum :");//mesaj kutusu
        messageBox.setFont(new Font("Tahoma", Font.PLAIN, 22));
        messageBox.setBounds(20, 350, 740, 60);
        formPanel.add(messageBox);
		
		btnRegister.addActionListener(e -> kayitIslemi());
        
		btnLogin.addActionListener(e -> goToLogin());
	}
	
	private void kayitIslemi() {
		Connection conn = Veritabani.getInstance().getConnection();
        String username = usernameInput.getText();
        String password = new String(passwordInput.getPassword());
        String passwordRepeat = new String(passwordRepeatInput.getPassword());
        String role = null ;
        if(roleAdmin.isSelected()) {
        	role = "Admin";
        }else if(roleOperator.isSelected()) {
        	role = "Operator";
        }
        try {
            if (username.isEmpty() || password.isEmpty() || role.isEmpty()) {
                throw new IllegalArgumentException("Boşlukları doldurunuz!");
            }
            if (!password.equals(passwordRepeat)) {
                throw new IllegalArgumentException("Şifreyi kontrol ediniz!");
            }
            password = Veritabani.getInstance().hashPassword(password);
            try {
				Veritabani.getInstance();
				username = Veritabani.sifrele(username);
				Veritabani.getInstance();
				role = Veritabani.sifrele(role);
			} catch (Exception e) {
				e.printStackTrace();
			}
            
            String sql = "INSERT INTO users(username, password, role) VALUES(?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, username);
                pstmt.setString(2, password);
                pstmt.setString(3, role);
                pstmt.executeUpdate();
                messageBox.setText("Durum: Kullanıcı başarıyla kaydedildi.");


                Timer timer = new Timer(2000, (ActionEvent e) -> {
                    messageBox.setText("Durum: Giriş ekranına dönülüyor.");
                    Timer timer2 = new Timer(2000, (ActionEvent f) -> goToLogin());
                    timer2.setRepeats(false);
                    timer2.start();
                });
                timer.setRepeats(false);
                timer.start();

            } catch (SQLException e) {
                if (e.getMessage().contains("UNIQUE constraint failed")) {
                    messageBox.setText("Durum: Kullanıcı adı kullanımda!");
                } else {
                    messageBox.setText("Durum: Bir hata oluştu!");
                }
            }
        } catch (IllegalArgumentException e) {
            messageBox.setText("Durum: " + e.getMessage());
        }
    }

   private void goToLogin() {
	   GirisEkrani giris = new GirisEkrani();
	   giris.setVisible(true);
	   this.dispose();
    }
	
}
