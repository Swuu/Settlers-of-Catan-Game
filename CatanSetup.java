import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;
public class CatanSetup implements ActionListener
{
	private boolean done;
    private int players = 0;
	private String[] playerNames;
	private boolean nameSet = false;
	private JFrame frame = new JFrame("Settlers of Catan");
	private JFrame frame3 = new JFrame("Error");
	private JRadioButton button2 = new JRadioButton("2 Players");
	private JRadioButton button3 = new JRadioButton("3 Players");
	private JRadioButton button4 = new JRadioButton("4 Players");
	private ButtonGroup group = new ButtonGroup();
	private JButton confirmButton = new JButton("OK");
	private JButton confirmButton2 = new JButton("OK");
	private JButton confirmButton3 = new JButton("OK");
	private JTextField textField = new JTextField();
	private JLabel errorMessage = new JLabel();
	private JFrame frame2 = new JFrame();
	private JLabel nameText = new JLabel("Please enter your name: ");
	private int globalI = 0;

	public CatanSetup()
	{ 
		done = false;
        frame.setSize(250,350);
		frame.setLayout(new GridLayout(5, 1, 5, 5));
		frame.setLocationRelativeTo(null);
		frame.add(new JLabel("Please pick the number of players: "));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		button2.setSelected(true);
		confirmButton.addActionListener(this);
		group.add(button2);
		group.add(button3);
		group.add(button4);
		frame.add(button2);
		frame.add(button3);
		frame.add(button4);
		frame.setResizable(false);
		frame.add(confirmButton);
		frame.setVisible(true);
		frame3.setLayout(new GridLayout(2,1,5,5));
		frame3.add(errorMessage);
		frame3.setSize(200,100);
		frame3.add(confirmButton3);
		frame2.setSize(300, 200);
		frame2.setLayout(new GridLayout(3, 1, 4, 4));
		frame2.add(nameText);
		frame2.add(textField);
		frame2.add(confirmButton2);
		confirmButton2.addActionListener(this);
		confirmButton3.addActionListener(this);
	}

	public void actionPerformed(ActionEvent evt)
	{
		if( evt.getSource() == confirmButton )
		{
			if( button2.isSelected())
				players = 2;
			if( button3.isSelected())
				players = 3;
			if( button4.isSelected())
				players = 4;
			playerNames = new String[this.getNumOfPlayers()];
			for (int i = 0; i < playerNames.length; i++) {
				playerNames[i] = "";
			}
			frame.setVisible(false);
			this.setNames();
		}
		if ( evt.getSource() == confirmButton2 ) {
			while(globalI < playerNames.length)
			{
				boolean hasDuplicate = false;
				for (int i2 = 0; i2 < globalI; i2++)
				{
					if (textField.getText().equals(playerNames[i2]))
					{
						hasDuplicate = true;
						errorMessage.setText("Please enter a unique name!");
						frame3.setVisible(true);
						break;
					}
				}
				if(hasDuplicate)
					break;
				if( textField.getText().equals(""))
				{
					errorMessage.setText("Please enter a name!");
					frame3.setVisible(true);
					break;
				}
				else {
					playerNames[globalI] = textField.getText();
					globalI++;
					textField.setText("");
					if (playerNames[playerNames.length - 1].equals(""))
						this.setNames();
					else
                    {
						frame2.setVisible(false);
                        System.out.println("Setup finished");
                        done = true;
					}
                    break;
				}
			}
		}
		if ( evt.getSource() == confirmButton3 ) {frame3.setVisible(false);}
	}

	public int getNumOfPlayers() { return players; }

	public void setNames()
	{
		frame2.setVisible(true);
		for( int i = 0; i < playerNames.length; i++ ) {
			if (playerNames[i] == null || playerNames[i].equals("")) {
				frame2.setTitle("Player " + (i+1));
				break;
			}
		}
	}
	public String getName(int player)
	{
		return playerNames[player];
	}
    
    public boolean isDone()
    {
        return done;
    }

}
