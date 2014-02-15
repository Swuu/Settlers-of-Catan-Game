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
  private JFrame numFrame = new JFrame("Settlers of Catan");
  private JFrame frame3 = new JFrame("Error");
  //private JRadioButton button2 = new JRadioButton("2 Players");
  private JRadioButton button3 = new JRadioButton("3 Players");
  private JRadioButton button4 = new JRadioButton("4 Players");
  private ButtonGroup group = new ButtonGroup();
  private JButton confirmButton = new JButton("OK");
  private JButton confirmButton2 = new JButton("OK");
  private JButton confirmButton3 = new JButton("OK");
  private JTextField textField = new JTextField();
  private JLabel errorMessage = new JLabel();
  private JFrame nameFrame = new JFrame();
  private JLabel nameText = new JLabel("Please enter your name: ");
  private int globalI = 0;
  
  public CatanSetup()
  { 
    done = false;
    numFrame.setSize(250,200);
    numFrame.setLayout(new GridLayout(5, 1, 5, 5));
    numFrame.setLocationRelativeTo(null);
    numFrame.add(new JLabel("Please pick the number of players: "));
    numFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    textField.addActionListener(this);
    button3.setSelected(true);
    confirmButton.addActionListener(this);
    //group.add(button2);
    group.add(button3);
    group.add(button4);
    //numFrame.add(button2);
    
    numFrame.add(button3);
    numFrame.add(button4);
    numFrame.setResizable(false);
    numFrame.add(confirmButton);
    numFrame.setVisible(true);
    button3.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
          setNumOfPlayers();
      }
    });
    button4.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
          setNumOfPlayers();
      }
    });
    nameFrame.setSize(300, 200);
    nameFrame.setLocationRelativeTo(null);
    nameFrame.setLayout(new GridLayout(3, 1, 4, 4));
    nameFrame.add(nameText);
    nameFrame.add(textField);
    nameFrame.add(confirmButton2);
    confirmButton2.addActionListener(this);
  }
  
  public void actionPerformed(ActionEvent evt)
  {
    if( evt.getSource() == confirmButton )
      setNumOfPlayers();
    if ( evt.getSource() == confirmButton2 || evt.getSource() == textField) {
      while(globalI < playerNames.length)
      {
        boolean hasDuplicate = false;
        for (int i2 = 0; i2 < globalI; i2++)
        {
          if (textField.getText().equals(playerNames[i2]))
          {
            hasDuplicate = true;
            JOptionPane.showMessageDialog(nameFrame,"Please enter a unique name!", "", JOptionPane.WARNING_MESSAGE);
            break;
          }
        }
        if(hasDuplicate)
          break;
        if( textField.getText().equals(""))
        {
          JOptionPane.showMessageDialog(nameFrame,"Please enter a name!", "", JOptionPane.WARNING_MESSAGE);
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
            nameFrame.setVisible(false);
            done = true;
          }
          break;
        }
      }
    }
    if ( evt.getSource() == confirmButton3 ) {frame3.setVisible(false);}
  }
  
  public int getNumOfPlayers() { return players; }
  public void setNumOfPlayers() {
      //if( button2.isSelected())
        //players = 2;
      if( button3.isSelected())
        players = 3;
      if( button4.isSelected())
        players = 4;
      playerNames = new String[this.getNumOfPlayers()];
      for (int i = 0; i < playerNames.length; i++) {
        playerNames[i] = "";
      }
      numFrame.setVisible(false);
      this.setNames();
  }
  
  public void setNames()
  {
    nameFrame.setVisible(true);
    for( int i = 0; i < playerNames.length; i++ ) {
      if (playerNames[i] == null || playerNames[i].equals("")) {
        nameFrame.setTitle("Player " + (i+1));
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
