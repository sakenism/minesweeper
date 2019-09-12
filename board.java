
public class board implements Interface
{
	int x, y, bcnt, rndx, rndy;
	int[][] brd = new int[111][111];
	int[][] open = new int[111][111];
	int[][] flag = new int[111][111];
	public board(int a, int b)
	{
		this.x = a;
		this.y = b;
		this.bcnt = (a * b) / 5;
		for (int i = 0; i < this.x; i++)
		{
			for (int j = 0; j < this.y; j++)
			{
				brd[i][j] = 0;
				open[i][j] = 0;
				flag[i][j] = 0;
			}
		}
		for (int i = 0; i < bcnt; i++)
		{
			while (1 == 1)
			{
				rndx = (int) (Math.random() * (this.x));
				rndy = (int) (Math.random() * (this.y));
				if(brd[rndx][rndy] != -1)
					break;
			}
			brd[rndx][rndy] = -1;
			flag[rndx][rndy] = -1;
		}
		for (int i = 0; i < this.x; i++)
		{
			for (int j = 0; j < this.y; j++)
			{
				if (brd[i][j] != -1)
				{
					if (i > 0 && brd[i - 1][j] == -1)
						brd[i][j]++;
					if (j > 0 && brd[i][j - 1] == -1)
						brd[i][j]++;
					if (i > 0 && j > 0 && brd[i - 1][j - 1] == -1)
						brd[i][j]++;
					if (j < y - 1 && brd[i][j + 1] == -1)
						brd[i][j]++;
					if (i < x - 1 && brd[i + 1][j] == -1)
						brd[i][j]++;
					if (i > 0 && j < y - 1 && brd[i - 1][j + 1] == -1)
						brd[i][j]++;
					if (i < x - 1 && j < y - 1 && brd[i + 1][j + 1] == -1)
						brd[i][j]++;
					if (i < x - 1 && j > 0 && brd[i + 1][j - 1] == -1)
						brd[i][j]++;
				}
			}
		}
	}

	public int win()
	{
		int cnt = 0;
		for (int i = 0; i < this.x; i++)
			for (int j = 0; j < this.y; j++)
				if (open[i][j] == 0)
					cnt++;
		if (cnt == bcnt)
			return (1);
		return (0);
	}

	public int dfs(int a, int b, int c)
	{
		if (a < 0 || b < 0 || a >= x || b >= y)
			return (0);
		if (brd[a][b] == -1 || open[a][b] == 1)
			return (0);
		open[a][b] = 1;
		if (brd[a][b] == 0)
			return 1 + (dfs(a + 1, b, 1) + dfs(a - 1, b, 1) + dfs(a, b + 1, 1) + 
		dfs(a, b - 1, 1) + dfs(a + 1, b + 1, 1) + dfs(a + 1, b - 1, 1) + 
		dfs(a - 1, b + 1, 1) + dfs(a - 1, b - 1, 1));
		return (1);
	}

	public void print_board()
	{
		for (int i = 0; i < this.x; i++)
		{
			for (int j = 0; j < this.y; j++)
			{
				if (open[i][j] == 0)
					System.out.print('x');
				else    
					System.out.print(brd[i][j]);
				System.out.print('\t');
			}
			System.out.print('\n');
		}
		System.out.print('\n');
	}
	public String get_elem(int nx, int ny)
	{
		if (open[nx][ny] == 0)
			return ("x");
		else 
		{
			if (flag[nx][ny] != -2)
				return (brd[nx][ny] + "");
			else
				return ((-2) + "");
		}
		
	}

	public void clickf(int a, int b)
	{
		if (flag[a][b] == -2)
			flag[a][b] = brd[a][b];
		else
			flag[a][b] = -2;
	}

	public int click(int a, int b)
	{
		if (a < 0 || b < 0 || a >= x || b >= y)
		{
			System.out.print("Give me correct coordinates\n");
			return (0);
		}
		if (open[a][b] == 1)
		{
			System.out.print("It was already open, try again\n");
			return (0);
		}
		if (brd[a][b] == -1)
		{
			for (int i = 0; i < this.x; i++)
			{
				for (int j = 0; j < this.y; j++)
				{
					System.out.print(brd[i][j]);
					open[i][j] = 1;
					System.out.print('\t');
				}
				System.out.print('\n');
			}
			System.out.print('\n');
			return (-1);
		}
		else
		{
			int score = 0;
			if (brd[a][b] > 0)
			{
				open[a][b] = 1;
				score = 1;
			}
			else if (brd[a][b] == 0)
				score = dfs(a, b, 0);
			print_board();
			return (score);
		}
	}
}