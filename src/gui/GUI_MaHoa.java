package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ThuatToan.ThuatToanRSA_v2;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
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
				tabbedPane.addTab("AES", null, pnl_AES, null);
				pnl_AES.setLayout(null);
				
				JLabel lblNewLabel_1 = new JLabel("Thông điệp:");
				lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
				lblNewLabel_1.setBounds(49, 24, 126, 38);
				pnl_AES.add(lblNewLabel_1);
				
				JEditorPane editorPane = new JEditorPane();
				editorPane.setFont(new Font("Tahoma", Font.PLAIN, 15));
				editorPane.setBounds(185, 24, 240, 188);
				pnl_AES.add(editorPane);
				
				JEditorPane editorPane_1 = new JEditorPane();
				editorPane_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
				editorPane_1.setBounds(689, 24, 240, 188);
				pnl_AES.add(editorPane_1);
				
				JLabel lblNewLabel_1_1 = new JLabel("Khóa:");
				lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
				lblNewLabel_1_1.setBounds(552, 24, 126, 38);
				pnl_AES.add(lblNewLabel_1_1);
				
				JButton btnNewButton = new JButton("Mã hóa");
				btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
				btnNewButton.setBounds(310, 248, 116, 47);
				pnl_AES.add(btnNewButton);
				
				JButton btnGiiM = new JButton("Giải mã");
				btnGiiM.setFont(new Font("Tahoma", Font.PLAIN, 20));
				btnGiiM.setBounds(813, 248, 116, 47);
				pnl_AES.add(btnGiiM);
				
				JEditorPane editorPane_2 = new JEditorPane();
				editorPane_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
				editorPane_2.setBounds(71, 361, 357, 190);
				pnl_AES.add(editorPane_2);
				
				JEditorPane editorPane_2_1 = new JEditorPane();
				editorPane_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
				editorPane_2_1.setBounds(572, 361, 357, 190);
				pnl_AES.add(editorPane_2_1);
				
				JLabel lblNewLabel_1_2 = new JLabel("Văn bản được mã hóa:");
				lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 20));
				lblNewLabel_1_2.setBounds(71, 313, 228, 38);
				pnl_AES.add(lblNewLabel_1_2);
				
				JLabel lblNewLabel_1_3 = new JLabel("Văn bản được giải mã:");
				lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 20));
				lblNewLabel_1_3.setBounds(574, 313, 231, 38);
				pnl_AES.add(lblNewLabel_1_3);
	}
}
