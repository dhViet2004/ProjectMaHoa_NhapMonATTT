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

public class gui_v1 extends JFrame {
	
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
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui_v1 frame = new gui_v1();
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
	public gui_v1() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
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
				
				private BigInteger chuoimahoa;

				public void actionPerformed(ActionEvent e) {
					String text = txt_ThongDiep.getText();
					if(text.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Vui lòng nhập thông điệp");
					}else if(txt_pN.equals("") &&  txt_e.equals("")) {
						JOptionPane.showMessageDialog(null, "Vui lòng nhập khóa");
					}else {
						chuoimahoa = hocMaHoa.maHoa(new BigInteger(text.getBytes(StandardCharsets.UTF_8)), new BigInteger(txt_pN.getText()));
						txt_MaHoa.setText(chuoimahoa.toString());
					}
				}});
				btn_maHoa.setFont(new Font("Tahoma", Font.PLAIN, 20));
				btn_maHoa.setBounds(410, 338, 162, 39);
				pln_RSA.add(btn_maHoa);
				
				JButton btn_GiaiMa = new JButton("    <<    ");
				btn_GiaiMa.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String text1 = txt_MaHoa.getText().trim();;
						if(text1.isEmpty()) {
							JOptionPane.showMessageDialog(null, "Vui lòng nhập thông mã hóa");
						}else if(txt_d.equals("") && txt_N.equals("")) {
							JOptionPane.showMessageDialog(null, "Vui lòng nhập khóa");
						}
						else {
							BigInteger chuoidoi = new BigInteger(text1);
							BigInteger chuoidagiai = chuKhoa.giaiMa(chuoidoi);
							String dagiaiChuoi = new String(chuoidagiai.toByteArray(), StandardCharsets.UTF_8);
							txt_ThongDiep.setText(dagiaiChuoi);
						}
					}
				});
				btn_GiaiMa.setFont(new Font("Tahoma", Font.PLAIN, 20));
				btn_GiaiMa.setBounds(410, 406, 162, 39);
				pln_RSA.add(btn_GiaiMa);
				
				JButton btn_Xoa = new JButton("Xóa");
				btn_Xoa.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						txt_ThongDiep.setText("");
						txt_MaHoa.setText("");
					}
				});
				btn_Xoa.setBounds(410, 555, 162, 39);
				pln_RSA.add(btn_Xoa);
				btn_Xoa.setFont(new Font("Tahoma", Font.PLAIN, 20));
				
				JButton btn_DocFIle = new JButton("Đọc file");
				btn_DocFIle.addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				        JFileChooser fileChooser = new JFileChooser();
				        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				        fileChooser.setDialogTitle("Chọn file .txt để đọc");
				        
				        // Chỉ chấp nhận file đuôi .txt
				        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Text Files (*.txt)", "txt"));
				        
				        int result = fileChooser.showOpenDialog(null);
				        
				        // Kiểm tra nếu người dùng đã chọn file
				        if (result == JFileChooser.APPROVE_OPTION) {
				            File selectedFile = fileChooser.getSelectedFile();
				            
				            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
				                StringBuilder content = new StringBuilder();
				                String line;
				                
				                // Đọc từng dòng từ file và nối vào biến content
				                while ((line = reader.readLine()) != null) {
				                    content.append(line).append("\n");
				                }
				                
				                // Hiển thị nội dung trong JTextArea
				                txt_ThongDiep.setText(content.toString());
				                
				            } catch (IOException ex) {
				                JOptionPane.showMessageDialog(null, "Lỗi khi đọc file: " + ex.getMessage());
				            }
				        }
				    }
				});
				
				btn_DocFIle.setFont(new Font("Tahoma", Font.PLAIN, 20));
				btn_DocFIle.setBounds(31, 555, 162, 39);
				pln_RSA.add(btn_DocFIle);
				
				JButton btn_Doc_RSA = new JButton("Đọc file");
				btn_Doc_RSA.addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				        JFileChooser fileChooser = new JFileChooser();
				        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				        fileChooser.setDialogTitle("Chọn file .rsa để đọc");

				        // Chỉ chấp nhận file đuôi .rsa
				        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("RSA Files (*.rsa)", "rsa"));

				        int result = fileChooser.showOpenDialog(null);

				        // Kiểm tra nếu người dùng đã chọn file
				        if (result == JFileChooser.APPROVE_OPTION) {
				            File selectedFile = fileChooser.getSelectedFile();

				            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
				                StringBuilder content = new StringBuilder();
				                String line;

				                // Đọc từng dòng từ file và nối vào biến content
				                while ((line = reader.readLine()) != null) {
				                    content.append(line).append("\n");
				                }

				                // Hiển thị nội dung trong JTextArea
				                txt_MaHoa.setText(content.toString());

				            } catch (IOException ex) {
				                JOptionPane.showMessageDialog(null, "Lỗi khi đọc file: " + ex.getMessage());
				            }
				        }
				    }
				});
				
								btn_Doc_RSA.setFont(new Font("Tahoma", Font.PLAIN, 20));
								btn_Doc_RSA.setBounds(610, 555, 162, 39);
								pln_RSA.add(btn_Doc_RSA);
								
								JButton btn_GiaiMa_1_3 = new JButton("Xuất file");
								btn_GiaiMa_1_3.addActionListener(new ActionListener() {
								    public void actionPerformed(ActionEvent e) {
								        JFileChooser fileChooser = new JFileChooser();
								        fileChooser.setDialogTitle("Lưu file .txt");
								        
								        // Chỉ cho phép lưu file đuôi .txt
								        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Text Files (*.txt)", "txt"));
								        
								        int userSelection = fileChooser.showSaveDialog(null);
								        
								        // Kiểm tra nếu người dùng chọn lưu file
								        if (userSelection == JFileChooser.APPROVE_OPTION) {
								            File fileToSave = fileChooser.getSelectedFile();
								            
								            // Đảm bảo file có đuôi .txt
								            if (!fileToSave.getName().toLowerCase().endsWith(".txt")) {
								                fileToSave = new File(fileToSave.getAbsolutePath() + ".txt");
								            }
								            
								            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave))) {
								                // Lấy nội dung từ JTextArea và ghi vào file
								                String content = txt_ThongDiep.getText();
								                writer.write(content);
								                
								                JOptionPane.showMessageDialog(null, "File đã được lưu thành công: " + fileToSave.getAbsolutePath());
								                
								            } catch (IOException ex) {
								                JOptionPane.showMessageDialog(null, "Lỗi khi lưu file: " + ex.getMessage());
								            }
								        }
								    }
								});
								
												btn_GiaiMa_1_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
												btn_GiaiMa_1_3.setBounds(207, 555, 162, 39);
												pln_RSA.add(btn_GiaiMa_1_3);
												
												JButton btn_XuatFileRSA = new JButton("Xuất file");
												btn_XuatFileRSA.addActionListener(new ActionListener() {
												    public void actionPerformed(ActionEvent e) {
												        JFileChooser fileChooser = new JFileChooser();
												        fileChooser.setDialogTitle("Lưu file .rsa");
												        
												        // Chỉ cho phép lưu file đuôi .rsa
												        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("RSA Files (*.rsa)", "rsa"));
												        
												        int userSelection = fileChooser.showSaveDialog(null);
												        
												        // Kiểm tra nếu người dùng chọn lưu file
												        if (userSelection == JFileChooser.APPROVE_OPTION) {
												            File fileToSave = fileChooser.getSelectedFile();
												            
												            // Đảm bảo file có đuôi .rsa
												            if (!fileToSave.getName().toLowerCase().endsWith(".rsa")) {
												                fileToSave = new File(fileToSave.getAbsolutePath() + ".rsa");
												            }
												            
												            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave))) {
												                // Lấy nội dung từ JTextArea và ghi vào file
												                String content = txt_MaHoa.getText();
												                writer.write(content);
												                
												                JOptionPane.showMessageDialog(null, "File đã được lưu thành công: " + fileToSave.getAbsolutePath());
												                
												            } catch (IOException ex) {
												                JOptionPane.showMessageDialog(null, "Lỗi khi lưu file: " + ex.getMessage());
												            }
												        }
												    }
												});
												
																btn_XuatFileRSA.setFont(new Font("Tahoma", Font.PLAIN, 20));
																btn_XuatFileRSA.setBounds(786, 555, 162, 39);
																pln_RSA.add(btn_XuatFileRSA);
																
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
																btn_SinhKhoa.setFont(new Font("Tahoma", Font.PLAIN, 15));
																btn_SinhKhoa.setBounds(809, 12, 150, 39);
																pln_RSA.add(btn_SinhKhoa);
																
																JButton btn_napKhoaMo = new JButton("Nạp khóa mở");
																btn_napKhoaMo.addActionListener(new ActionListener() {
																    public void actionPerformed(ActionEvent e) {
																        // Mở hộp thoại để người dùng chọn file
																        JFileChooser fileChooser = new JFileChooser();
																        fileChooser.setDialogTitle("Chọn file khóa công khai");

																        // Chỉ cho phép chọn file .txt
																        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Text Files (*.txt)", "txt"));

																        int userSelection = fileChooser.showOpenDialog(null);

																        // Kiểm tra nếu người dùng chọn file
																        if (userSelection == JFileChooser.APPROVE_OPTION) {
																            File fileToOpen = fileChooser.getSelectedFile();

																            try (BufferedReader reader = new BufferedReader(new FileReader(fileToOpen))) {
																                // Đọc nội dung của file
																                String line;
																                String nValue = "", eValue = "";

																                while ((line = reader.readLine()) != null) {
																                    if (line.startsWith("N:")) {
																                        nValue = line.substring(2).trim(); // Lấy giá trị N
																                    } else if (line.startsWith("e:")) {
																                        eValue = line.substring(2).trim(); // Lấy giá trị e
																                    }
																                }

																                // Cập nhật giá trị N và e vào các text field
																                txt_pN.setText(nValue);
																                txt_e.setText(eValue);

																                JOptionPane.showMessageDialog(null, "Khóa công khai đã được nạp thành công!");
																            } catch (IOException ex) {
																                JOptionPane.showMessageDialog(null, "Lỗi khi đọc file khóa công khai: " + ex.getMessage());
																            }
																        }
																    }
																});
																btn_napKhoaMo.setFont(new Font("Tahoma", Font.PLAIN, 15));
																btn_napKhoaMo.setBounds(809, 68, 150, 39);
																pln_RSA.add(btn_napKhoaMo);
																
																JButton btn_XuatkhoaMo = new JButton("Lưu khóa mở");
																btn_XuatkhoaMo.addActionListener(new ActionListener() {
																    public void actionPerformed(ActionEvent e) {
																        // Lưu khóa công khai
																        JFileChooser fileChooser = new JFileChooser();
																        fileChooser.setDialogTitle("Lưu khóa công khai");

																        // Chỉ cho phép lưu file đuôi .txt
																        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Text Files (*.txt)", "txt"));

																        int userSelection = fileChooser.showSaveDialog(null);

																        // Kiểm tra nếu người dùng chọn lưu file
																        if (userSelection == JFileChooser.APPROVE_OPTION) {
																            File fileToSave = fileChooser.getSelectedFile();

																            // Đảm bảo file có đuôi .txt
																            if (!fileToSave.getName().toLowerCase().endsWith(".txt")) {
																                fileToSave = new File(fileToSave.getAbsolutePath() + ".txt");
																            }

																            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave))) {
																                // Ghi giá trị N và e vào file
																                String publicKey = "N: " + txt_pN.getText() + "\ne: " + txt_e.getText();
																                writer.write(publicKey);
																                JOptionPane.showMessageDialog(null, "Khóa công khai đã được lưu thành công: " + fileToSave.getAbsolutePath());
																            } catch (IOException ex) {
																                JOptionPane.showMessageDialog(null, "Lỗi khi lưu khóa công khai: " + ex.getMessage());
																            }
																        }
																    }
																});
																btn_XuatkhoaMo.setFont(new Font("Tahoma", Font.PLAIN, 15));
																btn_XuatkhoaMo.setBounds(809, 111, 150, 39);
																pln_RSA.add(btn_XuatkhoaMo);
																
																JButton btn_napKhoaKin = new JButton("Nạp khóa kín");
																btn_napKhoaKin.addActionListener(new ActionListener() {
																    public void actionPerformed(ActionEvent e) {
																        // Mở hộp thoại để người dùng chọn file chứa khóa kín
																        JFileChooser fileChooser = new JFileChooser();
																        fileChooser.setDialogTitle("Chọn file chứa khóa kín");
																        int userSelection = fileChooser.showOpenDialog(null);

																        // Kiểm tra nếu người dùng chọn file
																        if (userSelection == JFileChooser.APPROVE_OPTION) {
																            File fileToOpen = fileChooser.getSelectedFile();

																            try (BufferedReader reader = new BufferedReader(new FileReader(fileToOpen))) {
																                String line;
																                String dValue = "";
																                String nValue = "";

																                // Đọc các dòng trong file
																                while ((line = reader.readLine()) != null) {
																                    if (line.startsWith("d:")) {
																                        dValue = line.substring(2).trim();  // Lấy giá trị sau "d:"
																                    } else if (line.startsWith("n:")) {
																                        nValue = line.substring(2).trim();  // Lấy giá trị sau "n:"
																                    }
																                }

																                // Kiểm tra và hiển thị thông báo nếu không tìm thấy giá trị d và n
																                if (!dValue.isEmpty() && !nValue.isEmpty()) {
																                    // Cập nhật các trường txt_d và txt_n
																                    txt_d.setText(dValue);
																                    txt_N.setText(nValue);

																                    // Thông báo nạp thành công
																                    JOptionPane.showMessageDialog(null, "Khóa kín đã được nạp thành công!");
																                } else {
																                    JOptionPane.showMessageDialog(null, "Không tìm thấy khóa kín trong file.");
																                }
																            } catch (IOException ex) {
																                JOptionPane.showMessageDialog(null, "Lỗi khi đọc file khóa kín: " + ex.getMessage());
																            }
																        }
																    }
																});
																btn_napKhoaKin.setFont(new Font("Tahoma", Font.PLAIN, 15));
																btn_napKhoaKin.setBounds(809, 166, 150, 39);
																pln_RSA.add(btn_napKhoaKin);
																
																JButton btn_XuatkhoaKin = new JButton("Lưu khóa kín");
																btn_XuatkhoaKin.addActionListener(new ActionListener() {
																    public void actionPerformed(ActionEvent e) {
																        // Mở hộp thoại để người dùng chọn nơi lưu file
																        JFileChooser fileChooser = new JFileChooser();
																        fileChooser.setDialogTitle("Chọn nơi lưu khóa kín");
																        int userSelection = fileChooser.showSaveDialog(null);

																        // Kiểm tra nếu người dùng chọn file
																        if (userSelection == JFileChooser.APPROVE_OPTION) {
																            File fileToSave = fileChooser.getSelectedFile();

																            // Nếu file không có đuôi .txt, thêm đuôi .txt
																            if (!fileToSave.getAbsolutePath().endsWith(".txt")) {
																                fileToSave = new File(fileToSave.getAbsolutePath() + ".txt");
																            }

																            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave))) {
																                // Lấy giá trị khóa kín từ các trường nhập liệu
																                String dValue = txt_d.getText().trim();
																                String nValue = txt_N.getText().trim();

																                // Ghi các giá trị khóa kín vào file
																                writer.write("d: " + dValue);
																                writer.newLine(); // Xuống dòng
																                writer.write("n: " + nValue);

																                // Thông báo lưu thành công
																                JOptionPane.showMessageDialog(null, "Khóa kín đã được lưu thành công!");
																            } catch (IOException ex) {
																                JOptionPane.showMessageDialog(null, "Lỗi khi lưu khóa kín: " + ex.getMessage());
																            }
																        }
																    }
																});
																btn_XuatkhoaKin.setFont(new Font("Tahoma", Font.PLAIN, 15));
																btn_XuatkhoaKin.setBounds(809, 210, 150, 39);
																pln_RSA.add(btn_XuatkhoaKin);
																
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
	}
}
