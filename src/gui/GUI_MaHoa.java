package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ThuatToan.AES;
import ThuatToan.ThuatToanRSA_v2;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Label;
import java.awt.Window;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class GUI_MaHoa extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_q;
	private JTextField txt_P;
	private JTextField txt_N;
	private JTextField txt_e;
	private JTextField txt_d;
	private ThuatToanRSA_v2 hocMaHoa;
	private ThuatToanRSA_v2 chuKhoa;
	private JEditorPane txt_ThongDiep;
	private JEditorPane txt_MaHoa;
	private JTextField txt_pN;
	private BigInteger chuoimahoa;
	
	
	private JLabel lblTextInput;
	private JTextField txtInputText;
	private JLabel lblKeyInput;
	private JTextField txtInputKey;
	private JButton btnEncrypt;
	private JButton btnDecrypt;
	private JTextArea txtAEncypt;
	private JLabel lblEncrypt;
	private JLabel lblDecrypt;
	private JTextArea txtADecrypt;
	private byte[] encrypt;
	private byte[] decrypt;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_MaHoa frame = new GUI_MaHoa();
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
	public GUI_MaHoa() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 639);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 10, 986, 653);
		contentPane.add(tabbedPane);
				
				JPanel pln_RSA = new JPanel();
				pln_RSA.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
				tabbedPane.addTab("RSA", null, pln_RSA, null);
				pln_RSA.setLayout(null);
				
				JLabel lblNewLabel = new JLabel("q:");
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
				lblNewLabel.setBounds(223, 12, 28, 26);
				pln_RSA.add(lblNewLabel);
				
				JLabel lblP = new JLabel("P:");
				lblP.setFont(new Font("Tahoma", Font.BOLD, 20));
				lblP.setBounds(223, 61, 28, 26);
				pln_RSA.add(lblP);
				
				JLabel lbln = new JLabel("n:");
				lbln.setFont(new Font("Tahoma", Font.BOLD, 20));
				lbln.setBounds(223, 109, 28, 26);
				pln_RSA.add(lbln);
				
				JLabel lblE = new JLabel("e:");
				lblE.setFont(new Font("Tahoma", Font.BOLD, 20));
				lblE.setBounds(223, 161, 28, 26);
				pln_RSA.add(lblE);
				
				JLabel lblD = new JLabel("d:");
				lblD.setFont(new Font("Tahoma", Font.BOLD, 20));
				lblD.setBounds(223, 212, 28, 26);
				pln_RSA.add(lblD);
				
				txt_q = new JTextField();
				txt_q.setFont(new Font("Tahoma", Font.PLAIN, 10));
				txt_q.setBounds(254, 10, 528, 31);
				pln_RSA.add(txt_q);
				txt_q.setColumns(10);
				
				txt_P = new JTextField();
				txt_P.setFont(new Font("Tahoma", Font.PLAIN, 10));
				txt_P.setColumns(10);
				txt_P.setBounds(254, 56, 528, 31);
				pln_RSA.add(txt_P);
				
				txt_N = new JTextField();
				txt_N.setFont(new Font("Tahoma", Font.PLAIN, 10));
				txt_N.setColumns(10);
				txt_N.setBounds(254, 104, 528, 31);
				pln_RSA.add(txt_N);
				
				txt_e = new JTextField();
				txt_e.setFont(new Font("Tahoma", Font.PLAIN, 10));
				txt_e.setColumns(10);
				txt_e.setBounds(254, 156, 528, 31);
				pln_RSA.add(txt_e);
				
				txt_d = new JTextField();
				txt_d.setFont(new Font("Tahoma", Font.PLAIN, 10));
				txt_d.setColumns(10);
				txt_d.setBounds(254, 207, 528, 31);
				pln_RSA.add(txt_d);
				
				JLabel lblThngip = new JLabel("Thông điệp:");
				lblThngip.setFont(new Font("Tahoma", Font.BOLD, 20));
				lblThngip.setBounds(31, 295, 177, 26);
				pln_RSA.add(lblThngip);
				
				JLabel lblMHa = new JLabel("Mã hóa:");
				lblMHa.setFont(new Font("Tahoma", Font.BOLD, 20));
				lblMHa.setBounds(610, 295, 177, 26);
				pln_RSA.add(lblMHa);
				
				JButton btn_maHoa = new JButton("    >>    ");
				btn_maHoa.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					String text = txt_ThongDiep.getText();
					if(text.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Vui lòng nhập thông điệp");
					}else if(chuKhoa == null) {
						JOptionPane.showMessageDialog(null, "Vui lòng nhập khóa");
					}else {
						chuoimahoa = hocMaHoa.maHoa(new BigInteger(text.getBytes(StandardCharsets.UTF_8)), chuKhoa.getN());
						txt_MaHoa.setText(chuoimahoa.toString());
					}
				}});
				btn_maHoa.setFont(new Font("Tahoma", Font.PLAIN, 20));
				btn_maHoa.setBounds(410, 338, 162, 39);
				pln_RSA.add(btn_maHoa);
				
				JButton btn_GiaiMa = new JButton("    <<    ");
				btn_GiaiMa.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					    if(txt_MaHoa.getText().trim().isEmpty()) {
					        JOptionPane.showMessageDialog(null, "Vui lòng nhập thông tin mã hóa");
					    } else if(chuKhoa == null) {
					        JOptionPane.showMessageDialog(null, "Vui lòng sinh khóa trước khi giải mã");
					    } else {
					        try {
					            BigInteger chuoidoi = new BigInteger(txt_MaHoa.getText().trim());
					            BigInteger chuoidagiai = chuKhoa.giaiMa(chuoidoi);
					            String dagiaiChuoi = new String(chuoidagiai.toByteArray(), StandardCharsets.UTF_8);
					            txt_ThongDiep.setText(dagiaiChuoi);
					        } catch (NumberFormatException ex) {
					            JOptionPane.showMessageDialog(null, "Định dạng mã hóa không hợp lệ. Vui lòng kiểm tra lại!");
					        } catch (Exception ex) {
					            JOptionPane.showMessageDialog(null, "Lỗi khi giải mã: " + ex.getMessage());
					        }
					    }
					}

				});
				
				btn_GiaiMa.setFont(new Font("Tahoma", Font.PLAIN, 20));
				btn_GiaiMa.setBounds(410, 387, 162, 39);
				pln_RSA.add(btn_GiaiMa);
				
				JButton btn_Xoa = new JButton("Xóa");
				btn_Xoa.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						txt_ThongDiep.setText("");
						txt_MaHoa.setText("");
					}
				});
				btn_Xoa.setBounds(410, 504, 162, 39);
				pln_RSA.add(btn_Xoa);
				btn_Xoa.setFont(new Font("Tahoma", Font.PLAIN, 20));
																		
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				scrollPane.setBounds(31, 338, 338, 207);
				pln_RSA.add(scrollPane);
				
				txt_ThongDiep = new JEditorPane();
				scrollPane.setViewportView(txt_ThongDiep);
				txt_ThongDiep.setFont(new Font("Tahoma", Font.PLAIN, 15));
				
				JScrollPane scrollPane_1 = new JScrollPane();
				scrollPane_1.setBounds(610, 338, 338, 207);
				pln_RSA.add(scrollPane_1);
				
				txt_MaHoa = new JEditorPane();
				scrollPane_1.setViewportView(txt_MaHoa);
				txt_MaHoa.setFont(new Font("Tahoma", Font.PLAIN, 15));
				
				JButton btn_SinhKhoa = new JButton("Sinh khóa");
				btn_SinhKhoa.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						hocMaHoa = new ThuatToanRSA_v2();
						chuKhoa = new ThuatToanRSA_v2();
						txt_q.setText(hocMaHoa.getQ().toString());
						txt_P.setText(hocMaHoa.getP().toString());
						txt_N.setText(hocMaHoa.getN().toString());
						txt_e.setText(hocMaHoa.getE().toString());
						txt_d.setText(hocMaHoa.getqInv().toString());
						txt_pN.setText(hocMaHoa.getPhiN().toString());
					}
				});
				btn_SinhKhoa.setFont(new Font("Tahoma", Font.PLAIN, 20));
				btn_SinhKhoa.setBounds(410, 455, 162, 39);
				pln_RSA.add(btn_SinhKhoa);
				
				JLabel lblN = new JLabel("pN:");
				lblN.setFont(new Font("Tahoma", Font.BOLD, 20));
				lblN.setBounds(207, 259, 44, 26);
				pln_RSA.add(lblN);
				
				txt_pN = new JTextField();
				txt_pN.setFont(new Font("Tahoma", Font.PLAIN, 10));
				txt_pN.setColumns(10);
				txt_pN.setBounds(254, 248, 528, 31);
				pln_RSA.add(txt_pN);
				
				JPanel pnl_AES = new JPanel();
				
				pnl_AES.add(panelCenter1());
				btnEncrypt.addActionListener(e -> xuLyMAHoa());
				btnDecrypt.addActionListener(e -> xuLyGiaiMa());
				tabbedPane.addTab("AES", null, pnl_AES, null);
				pnl_AES.setLayout(null);
	}
	public JPanel panelCenter1(){	
		JPanel panelCenter = new JPanel();
		panelCenter.setBounds(90, 51, 808, 467);
		java.awt.Dimension dms = new Dimension(100, 30);
		lblTextInput = new JLabel("Thông điệp: ");
		lblTextInput.setFont(new Font("Arial", Font.BOLD, 15));
		txtInputText = new JTextField();
		txtInputText.setPreferredSize(dms);
		lblKeyInput = new JLabel("Khóa:");
		lblKeyInput.setFont(new Font("Arial", Font.BOLD, 15));
		txtInputKey = new JTextField();
		txtInputKey.setPreferredSize(dms);
		lblEncrypt = new JLabel("Văn bản mã hóa:");
		lblEncrypt.setFont(new Font("Arial", Font.BOLD, 15));
		txtAEncypt = new JTextArea(10, 10);
		txtAEncypt.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txtAEncypt.setLineWrap(true);
		lblDecrypt = new JLabel("Văn bản được giải mã:");
		lblDecrypt.setFont(new Font("Arial", Font.BOLD, 15));
		txtADecrypt = new JTextArea(10, 18);
		txtADecrypt.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txtADecrypt.setLineWrap(true);
		btnEncrypt = new JButton("Mã hóa");
		btnEncrypt.setFont(new Font("Arial", Font.BOLD, 15));
		btnDecrypt = new JButton("Giải mã");
		btnDecrypt.setFont(new Font("Arial", Font.BOLD, 15));
		panelCenter.setBackground(new Color(240, 240, 240));
		panelCenter.setLayout(null);

		// text input and key input
		lblTextInput.setBounds(10, 48, 100, 30);
		txtInputText.setBounds(100, 50, 280, 30);
		lblKeyInput.setBounds(420, 50, 100, 30);
		txtInputKey.setBounds(500, 50, 280, 30);
		panelCenter.add(lblTextInput);
		panelCenter.add(txtInputText);
		panelCenter.add(lblKeyInput);
		panelCenter.add(txtInputKey);

		// encrypt and decrypt button
		btnEncrypt.setBounds(260, 100, 120, 30);
		btnDecrypt.setBounds(660, 100, 120, 30);
		panelCenter.add(btnEncrypt);
		panelCenter.add(btnDecrypt);

		// encrypt text
		lblEncrypt.setBounds(20, 150, 150, 30);
		txtAEncypt.setBounds(20, 180, 360, 180);
		panelCenter.add(lblEncrypt);
		panelCenter.add(txtAEncypt);

		// decrypt text
		lblDecrypt.setBounds(420, 150, 173, 30);
		txtADecrypt.setBounds(420, 180, 360, 180);
		panelCenter.add(lblDecrypt);
		panelCenter.add(txtADecrypt);

		
		return panelCenter;

	}
	private void xuLyReset() {
		txtInputText.setText("");
		txtInputKey.setText("");
		txtAEncypt.setText("");
		txtADecrypt.setText("");
		txtInputText.requestFocus();
	}

	// Hàm xử lý khi nhấn nút mã hóa
	private void xuLyMAHoa() {
		// Lấy văn bản rõ và khóa từ frame
		String explainText = txtInputText.getText();
		String k = new String(txtInputKey.getText());

		// Kiem tra khoa
		if (k.equals("")) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập khóa!");
		} else if (k.length() != 16) {
			k = addKey(k);
			// Mã hóa văn bản rõ
			encrypt = AES.encrypt(explainText.getBytes(), k.getBytes());
			String encryptedString = encode(encrypt);
			// Cập nhật văn bản đã mã hóa
			txtAEncypt.setText(encryptedString);

		} else {
			// Mã hóa văn bản rõ
			encrypt = AES.encrypt(explainText.getBytes(), k.getBytes());
			String encryptedString = encode(encrypt);
			// Cập nhật văn bản đã mã hóa
			txtAEncypt.setText(encryptedString);

		}

	}

	// Hàm xử lý khi nhấn nút giải mã
	private void xuLyGiaiMa() {
		// Lấy văn bản đã mã hóa và khóa từ frame
		String encryptedString = txtAEncypt.getText();
		String k = new String(txtInputKey.getText());
		k = addKey(k);
		// Giải mã văn bản đã mã hóa
		// Sử dụng Base64 để giải mã chuỗi đã mã hóa thành mảng byte để giải mã
		byte[] encryptedBytes = decode(encryptedString);
		decrypt = AES.decrypt(encryptedBytes, k.getBytes());
		// Cập nhật văn bản đã giải mã
		txtADecrypt.setText(String.valueOf(new String(decrypt, StandardCharsets.UTF_8)));
	}

	public static String addKey(String key) {
		int length = key.length();
		if (length < 16) {
			for (int i = length; i < 16; i++) {
				key += "a";
			}
		} else if (length > 16) {
			key = key.substring(0, 16);
		}
		return key = key.substring(0, 16);
	}

	// Hàm Base64 để mã hóa và giải mã dữ liệu văn bản sang mảng byte và ngược lại
	public static String encode(byte[] bytes) {
		return Base64.getEncoder().encodeToString(bytes);
	}

	public static byte[] decode(String base64) {
		return Base64.getDecoder().decode(base64);
	}
}
