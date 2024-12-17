import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame implements ActionListener {
	public JTextArea textArea = new JTextArea();
	private GridLayout layout = new GridLayout(3, 1);
	private JPanel panel = new JPanel(layout);
	private JLabel label = new JLabel("What would you like to do");
	private JTextField textField = new JTextField();
	private JButton button = new JButton("Execute");
	private JScrollPane scrollPane = new JScrollPane(textArea);
	
	
	public GUI() {
		buildWindow();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String s = textField.getText();
		Game.processCommand(s);
		textField.setText("");
	}

	private void buildWindow() {
		setTitle("Explorer");
		setLayout(new BorderLayout());
		button.addActionListener(this);
		panel.add(label);
		panel.add(textField);
		panel.add(button);		
		textArea.setEditable(false);		
		// add(textArea, BorderLayout.CENTER);
		add(scrollPane, BorderLayout.CENTER);
		add(panel, BorderLayout.SOUTH);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		setLocationRelativeTo(null); // Center window
		setVisible(true); // Make window appear
	}

	public JButton getButton() {
		return this.button;
	}
}