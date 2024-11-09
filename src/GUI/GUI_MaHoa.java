package GUI;

import javax.swing.JFrame;

public class GUI_MaHoa extends JFrame{
	public GUI_MaHoa() {
		setSize(800, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
	}
	public static void main (String[] agrs) {
		GUI_MaHoa gmh = new GUI_MaHoa();
		gmh.setVisible(true);
	}
}
