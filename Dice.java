import java.util.*;
public class Dice
{
	public int roll()//This rolls two six sided dice
	{
		Random generator = new Random();
		int dice = 0;
		while (dice < 1)
		{
			dice = generator.nextInt(7);
		}
		int output = dice;
		dice = 0;
		while (dice < 1)
		{
			dice = generator.nextInt(7);
		}
		output += dice;
		return output;
	}
	public int roll(int num)//This rolls num six sided dice
	{
		Random generator = new Random();
		int dice = 0;
		int output = 0;
		for (int n = 0; n < num; n++)
		{
			while (dice < 1)
			{
				dice = generator.nextInt(7);
				if (dice > 0)
				{
					output += dice;
					dice = 0;
					break;
				}
			}
		}
		return output;
	}
}
