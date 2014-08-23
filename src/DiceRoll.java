import java.util.Random;
import objectdraw.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DiceRoll implements Runnable, ActionListener, KeyListener
{
    private static Random generator = new Random();
    private static int scoreOne, scoreTwo;
    private JFrame frame;
    private JDrawingCanvas cnv = new JDrawingCanvas(300, 150);
    private FramedRoundedRect LEFT, RIGHT;
    private JButton CLOSE;
    private CatanGame game;    
    private JLabel dice1;
    private JLabel dice2;

    public DiceRoll(CatanGame aGame, JLabel dice1, JLabel dice2)
    {
    	game = aGame;
        this.dice1 = dice1;
        this.dice2 = dice2;
    }

    public int getScore ()
    {
	return (scoreOne + scoreTwo);
    }

    public void run()
    {
        frame = new JFrame();
        Container main = frame.getContentPane();
        
        frame.setContentPane(main);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        main.setLayout(new BorderLayout());
        
        main.add(cnv, BorderLayout.CENTER);
        showDices(scoreOne, scoreTwo);
        
        CLOSE = new JButton("OK");
        CLOSE.addActionListener(this);
        CLOSE.addKeyListener(this);
        main.add(CLOSE, BorderLayout.SOUTH);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
        
    /*public static void main (String[] args)
    {
        //System.out.print("\n " + scoreOne + " " + scoreTwo + "\n" );
        DiceRoll code = new DiceRoll();
        SwingUtilities.invokeLater(code);
    }*/
    
    private void showDices(int one, int two)
    {
        double x1 = 25;
        double y1 = 25;
        double width = 100;
        double height = 100;
        double arcWidth = 10;
        double arcHeight = 10;
        double delta = 50;
        double x2 = x1 + width + delta;
        
        LEFT = new FramedRoundedRect(x1, y1, width, height,
                                        arcWidth, arcHeight, cnv);
        
        RIGHT = new FramedRoundedRect(x2, y1, width, height,
                                        arcWidth, arcHeight, cnv);
        
        dicePlacement(one, 1);
        dicePlacement(two, 2);
    }   
                                     
    private void dicePlacement(int pts, int whichDice)
    {
        FramedRoundedRect placement;
        FilledOval dot1, dot2, dot3, dot4, dot5, dot6;
        double x, y, delta, diam, adjust;
        if (whichDice == 1)
            placement = LEFT;
        else
            placement = RIGHT;
        
        x = placement.getLocation().getX();
        y = placement.getLocation().getY();
        adjust = placement.getWidth() / 12;
        //System.out.print("ADJUSTMENT: " + adjust + "\n");
        diam = adjust * 2;
        delta = diam + adjust * 2;
        x = x + adjust;
        y = y + adjust;
        
        if (pts == 1)
        {
            dot1 = new FilledOval(x + delta, y + delta, diam, diam, cnv);
        }  
        else if (pts == 2)
        {
            dot1 = new FilledOval(x, y, diam, diam, cnv);
            dot2 = new FilledOval(x + 2*delta, y + 2*delta, diam, diam, cnv);
        }
        
        else if (pts == 3)
        {
            dot1 = new FilledOval(x, y, diam, diam, cnv);
            dot2 = new FilledOval(x + delta, y + delta, diam, diam, cnv);
            dot3 = new FilledOval(x + 2*delta, y + 2*delta, diam, diam,
                                                                        cnv);
        }
        
        else if (pts == 4)
        {
            dot1 = new FilledOval(x, y, diam, diam, cnv);
            dot2 = new FilledOval(x, y + 2*delta, diam, diam, cnv);
            dot3 = new FilledOval(x + 2*delta, y, diam, diam, cnv);
            dot4 = new FilledOval(x + 2*delta, y + 2*delta, diam, diam, cnv);
        }
        
        else if (pts == 5)
        {
            dot1 = new FilledOval(x, y, diam, diam, cnv);
            dot2 = new FilledOval(x + 2*delta, y, diam, diam, cnv);
            dot3 = new FilledOval(x + delta, y + delta, diam, diam, cnv);
            dot4 = new FilledOval(x, y + 2*delta, diam, diam, cnv);
            dot5 = new FilledOval(x + 2*delta, y + 2*delta, diam, diam,
                                                                        cnv);
        }
        
        else if (pts == 6)
        {
            dot1 = new FilledOval(x, y, diam, diam, cnv);
            dot2 = new FilledOval(x, y + delta, diam, diam, cnv);
            dot3 = new FilledOval(x, y + 2*delta, diam, diam, cnv);
            dot4 = new FilledOval(x + 2*delta, y, diam, diam, cnv);
            dot5 = new FilledOval(x + 2*delta, y + delta, diam, diam, cnv);
            dot6 = new FilledOval(x + 2*delta, y + 2*delta, diam, diam, cnv);
        }
    }
    
    public void actionPerformed(ActionEvent evt)
    {
        if (evt.getSource() == CLOSE)
            frame.dispose();
    }
    
    public void keyPressed(KeyEvent key)
    {
        if (key.getKeyCode() == KeyEvent.VK_ENTER)
            frame.dispose();
    }
    public void keyReleased(KeyEvent key) {}
    public void keyTyped(KeyEvent key) {}

	public void roll()
	{
		scoreOne = generator.nextInt(6) + 1;
        scoreTwo = generator.nextInt(6) + 1;
        game.currentPlayer().hasRolled(true);
        updateDice(dice1, scoreOne);
        updateDice(dice2, scoreTwo);
		
	}

	private void updateDice(JLabel dice, int diceNum)
	{
		switch(diceNum) {
			case 1: dice.setIcon(new ImageIcon("res/image/roll1.png"));
					System.out.println("Rolled " + diceNum);
					break;
			case 2: dice.setIcon(new ImageIcon("res/image/roll2.png"));
					System.out.println("Rolled " + diceNum);
					break;
			case 3: dice.setIcon(new ImageIcon("res/image/roll3.png"));
					System.out.println("Rolled " + diceNum);
					break;
			case 4: dice.setIcon(new ImageIcon("res/image/roll4.png"));
					System.out.println("Rolled " + diceNum);
					break;
			case 5: dice.setIcon(new ImageIcon("res/image/roll5.png"));
					break;
			case 6: dice.setIcon(new ImageIcon("res/image/roll6.png"));
					break;
		}
		dice.repaint();
		dice.validate();
		
	}
}
        
