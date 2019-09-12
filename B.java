import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class B {
	private int N = 9, M = 9;

	private final List<JButton> list = new ArrayList<JButton>();

	private JButton[][] lst = new JButton[1111][1111];

	private JButton getGridButton(int r, int c) {
		int index = r * M + c;
		return list.get(index);
	}

	private JButton createGridButton(int row, int col, board brd, JFrame f) {
		final JButton b = new JButton("x");
		
		b.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					Icon image = new ImageIcon("bomb.jpeg");
					Icon flag = new ImageIcon("flag.png");
					int lost = brd.click(row, col);
					
					for (int i = 0; i < N; i++)
					{
						for (int j = 0; j < M; j++)
						{
							list.get(i * M + j).setText(brd.get_elem(i, j));
						}
					}
					if (lost == -1)
					{
						for (int i = 0; i < N; i++)
						{
							for (int j = 0; j < M; j++)
							{
								if (atoi(brd.get_elem(i, j)) < 0)
									list.get(i * M + j).setIcon(image);
							}
						}	
						String again = JOptionPane.showInputDialog(f,
							"You lost, if you want to play again - type 1, else close window", null); 
						if (again.equals("1"))
						{
							f.dispose();
							new B().display();
						}
					}
					if (brd.win() == 1)
					{
						String again = JOptionPane.showInputDialog(f,
							"You won, if you want to play again - type 1, else close window", null); 
						if (again.equals("1"))
						{
							f.dispose();
							new B().display();
						}
					}
				}
				if (e.getButton() == MouseEvent.BUTTON3) {
					brd.clickf(row, col);
					for (int i = 0; i < N; i++)
					{
						for (int j = 0; j < M; j++)
						{
							list.get(i * M + j).setText(brd.get_elem(i, j));
						}
					}
					//list.get(row * M + col).setIcon(flag);
				}
			}
		});
		return b;
	}

	private JPanel createGridPanel(board brd, JFrame f) {
		JPanel p = new JPanel(new GridLayout(N, M));
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++)
			{
				JButton gb = createGridButton(i, j, brd, f);
				list.add(gb);
				lst[i][j] = gb;
				p.add(gb);
			}
		}
		return p;
	}
	public int atoi(String str) {
		if (str == null || str.length() < 1)
			return 0;
 
	// trim white spaces
		str = str.trim();
 
		char flag = '+';
 
	// check negative or positive
		int i = 0;
		if (str.charAt(0) == '-') {
			flag = '-';
			i++;
		} else if (str.charAt(0) == '+') {
			i++;
		}
		// use double to store result
		double result = 0;
	 
		// calculate value
		while (str.length() > i && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
			result = result * 10 + (str.charAt(i) - '0');
			i++;
		}
	 
		if (flag == '-')
			result = -result;
	 
		// handle max and min
		if (result > Integer.MAX_VALUE)
			return Integer.MAX_VALUE;
		
		if (result < Integer.MIN_VALUE)
			return Integer.MIN_VALUE;
	
		return (int) result;
	}
	private void display() {
		JFrame f = new JFrame("GridButton");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setSize(1000, 1000);
		
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		int n, m;
		n = 9;
		m = 9;
		
		board brd = new board(n, m);
		JPanel p = createGridPanel(brd, f);
		
		f.add(p);

		f.setSize(1001, 1001);
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new B().display();
			}
		});
	}
}