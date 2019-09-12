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

public class D {

	private static int N, M, score1 = 0, move = 0, score2 = 0;
	private final List<JButton> list = new ArrayList<JButton>();

	String alone, name1, name2, trn;
	private JButton[][] lst = new JButton[1111][1111];
	private List<JTextField> lscore = new ArrayList<JTextField>();


	private JButton getGridButton(int r, int c) {
		int index = r * M + c;
		return list.get(index);
	}

	private JButton createGridButton(int row, int col, board brd, JFrame f, JFrame sc) {
		final JButton b = new JButton("x");
		
		b.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					Icon image = new ImageIcon("bomb.jpeg");
					Icon flag = new ImageIcon("flag.png");
					int lost = brd.click(row, col);
					if (lost > 0)
					{
						if (move % 2 == 0)
							score1 += lost;
						else
							score2 += lost;
						move++;
					}

					lscore.get(0).setText(name1 + ":" + (score1 + ""));

					lscore.get(1).setText(name2 + ":" + (score2 + ""));

					if (move % 2 == 0)
						trn = (name1) + "'s turn";
					else
						trn = (name2) + "'s turn";
					lscore.get(2).setText(trn);
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
						String again;
						if (move % 2 != 0)
						{
							again = JOptionPane.showInputDialog(f,
							(name1 + " won, if you want to play again - type 1, else close window"), null); 
						}
						else
							again = JOptionPane.showInputDialog(f,
							(name2 + " won, if you want to play again - type 1, else close window"), null);
						if (again.equals("1"))
						{
							move = 0;
							score1 = 0;
							score2 = 0;
							f.dispose();
							new D().display();
						}
					}
					if (brd.win() == 1)
					{
						String again;
						if (move % 2 != 0)
						{
							again = JOptionPane.showInputDialog(f,
							(name1 + " won, if you want to play again - type 1, else close window"), null); 
						}
						else
							again = JOptionPane.showInputDialog(f,
							(name2 + " won, if you want to play again - type 1, else close window"), null);if (again.equals("1"))
						{
							move = 0;
							score1 = 0;
							score2 = 0;
							f.dispose();
							new D().display();
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

	private JPanel createGridPanel(board brd, JFrame f, JFrame sc) {
		JPanel p = new JPanel(new GridLayout(N, M));
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++)
			{
				if (i < N)
				{
					JButton gb = createGridButton(i, j, brd, f, sc);
					list.add(gb);
					lst[i][j] = gb;
					p.add(gb);
				}
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
		JFrame sc = new JFrame("Score");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setSize(600, 600);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		sc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sc.pack();
		sc.setSize(601, 100);
		sc.setLocationRelativeTo(null);
		sc.setVisible(true);
		sc.setLocation(dim.width/2-sc.getSize().width/2, dim.height/2 + f.getSize().height/2 - 15);
		JPanel pn = new JPanel(new GridLayout(1, 3));
		JTextField pl1 = new JTextField("");
		JTextField pl2 = new JTextField("");
		JTextField turn = new JTextField("");
		pl1.setEditable(false);
		pl2.setEditable(false);
		turn.setEditable(false);
		pn.add(pl1);
		pn.add(pl2);
		pn.add(turn);
		lscore.add(pl1);
		lscore.add(pl2);
		lscore.add(turn);

		sc.add(pn);

		f.setLocationRelativeTo(null);
		f.setVisible(true);
		int n, m;
		name1 = JOptionPane.showInputDialog(f,
				"What is the name of first player?", null);
		if (name1 == null)
			name1 = "Player 1";
			name2 = JOptionPane.showInputDialog(f,
						"What is the name of second player?", null);
			if (name2 == null)
				name2 = "Player 2";
		n = atoi(JOptionPane.showInputDialog(f,
						"How many rows do you want map to be?", null));
		m = atoi(JOptionPane.showInputDialog(f,
						"How many cols do you want map to be?", null));
		N = n;
		M = m;

		board brd = new board(n, m);
		JPanel p = createGridPanel(brd, f, sc);
		
		f.add(p);

		f.setSize(601, 601);

		// System.out.print(alone + ' ' + name1 + ' ' + name2 + ' ' + n);
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new D().display();
			}
		});
	}
}