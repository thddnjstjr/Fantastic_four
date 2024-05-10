package gomoku.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainMenu extends JFrame implements ActionListener{

	private JLabel menu;
	private JButton button;
	private Background background;
	
	public MainMenu() {
		initData();
		setInitLayout();
		addEventListener();
	}
	public void initData() {
		menu = new JLabel(new ImageIcon("images/ghost.gif"));
		button = new JButton("시작 하기");
	}
	public void setInitLayout() {
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		setSize(1000,1000);
		setContentPane(menu);
		setTitle("오목게임");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(button);
		button.setBounds(400, 650, 100, 50);
	}
	public void addEventListener() {
		button.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton selectedButton = (JButton) e.getSource();
		if (selectedButton.getText().equals("시작 하기")) {
			setVisible(false);
			setContentPane(new Background());
		}
	}
}
