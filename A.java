import java.util.Scanner;
public class A
{
	public static void kettik()
	{
		Scanner reader = new Scanner(System.in);
		int ans = -1;
		int a, b, n, m;
		n = 9;
		m = 9;
		board brd = new board(n, m);
		
		brd.print_board();
		
		while (1 == 1) 
		{
			System.out.println("Enter your move (row[1-9] column[1-9]): ");
			a = reader.nextInt();
			b = reader.nextInt();
			a--;
			b--;
			ans = brd.click(a, b);
			if (ans == -1)
			{
            	System.out.print("You lose, want to play again?\n");
				break;
			}
			//System.out.print(first + '\n' + second + '\n');
			if (brd.win() == 1)
			{
				System.out.print("You win, congrats\n");
				break ;
			}
		}
	}
	public static void main(String[] args)
	{

		Scanner reader = new Scanner(System.in);
		kettik();
		while (1 == 1)
		{
			String again = reader.nextLine();
			if (again.equals("yes"))
				kettik();
			else
				break ;
		}
	}
}