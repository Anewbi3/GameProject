import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame implements ActionListener {
	public static void main(String[] args) {
		new GUI();
	}
	
	public GUI() {
		buildWindow();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
	}

	private void buildWindow() {
		setTitle("Explorer");
		setLayout(new GridLayout(3, 3, 10, 10)); // Specifies 3x3 grid layout
		
		add(new JTextArea());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		setLocationRelativeTo(null); // Center window
		setVisible(true); // Make window appear
	}
}