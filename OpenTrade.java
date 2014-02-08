/* PLEASE DON'T MAKE ANY CHANGES TO THIS FILE */
/* CONTACT KIRILL SYDYKOV INSTEAD */

/* Opens a new "child" window for trading in CatanGame */

/* *** CODE OF EACH RESOURCE: ***
            ******
            0 = Clay
            1 = Lumber
            2 = Ore
            3 = Sheep
            4 = Wheat
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OpenTrade implements ActionListener, Runnable
{
    private JFrame theFrame;
    private Container main;
    
    private JButton buttonClay, buttonLumber, buttonOre, buttonSheep,
                    buttonWheat,
                    finishTrade, cancelTrade,
                    plusTrade1, minusTrade1, plusTrade2, minusTrade2;
    /* PANELS */
    private JPanel JP_NORTH, JP_SOUTH, JP_WEST, JP_EAST, JP_CENTER;
    private JTextArea[] LEFT_MES = new JTextArea[5];
    private JTextArea[] RIGHT_MES = new JTextArea[5];
    private JTextArea[] LEFT_DELTA = new JTextArea[5];
    private JTextArea[] RIGHT_DELTA = new JTextArea[5];
    private JLabel playerQty, playerOneName, playerTwoName;
    private CatanGame master;
    
    /* Qty of each resource of trading players */
    /*
    private int playerOneClay, playerOneLumber, playerOneOre,
                playerOneSheep, playerOneWheat;
    private int playerTwoClay, playerTwoLumber, playerTwoOre,
                playerTwoSheep, playerTwoWheat;
    */            
    private int[] playerOneResources = new int[5];
    private int[] playerTwoResources = new int[5];
                
    private int[] playerOneNewRes = new int[5];
    private int[] playerTwoNewRes = new int[5];
    
    private String[] resName = {"Clay", "Lumber", "Ore", "Sheep", "Wheat"};
    private String tradeLog = " to trade: \n";
    private int step = 0; /* stage of trading */
    private int curRes = -1; /* which resource to trade */
    private Player playerOne, playerTwo;
    
    
    /* CONSTRUCTOR */
    public OpenTrade(Player one, Player two, CatanGame theGame)
    {
        master = theGame;
        playerOne = one;
        playerTwo = two;
        
        playerOneResources[0] = one.getClay();
        playerOneResources[1] = one.getLumber();
        playerOneResources[2] = one.getOre();
        playerOneResources[3] = one.getSheep();
        playerOneResources[4] = one.getWheat();
        
        playerTwoResources[0] = two.getClay();
        playerTwoResources[1] = two.getLumber();
        playerTwoResources[2] = two.getOre();
        playerTwoResources[3] = two.getSheep();
        playerTwoResources[4] = two.getWheat();
        
        for (int index = 0; index < 5; index++)
        {
            playerOneNewRes[index] = playerOneResources[index];
            playerTwoNewRes[index] = playerTwoResources[index];
        }
        
        playerOne.hideHand();
        master.toggleButtons(false);
    }
    
    /* INTERFACE BUILD */
    public void run()
    {
        int JTA_width = 2; /* JTextArea width */
        int JTA_height = 1; /* ... height */
    
        theFrame = new JFrame("Trade Dialog");
        theFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        theFrame.addWindowListener( new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                JFrame frame = (JFrame)e.getSource();

                int result = JOptionPane.showConfirmDialog(
                    frame,
                    "Are you sure you want to exit?",
                    "Exit Trading",
                    JOptionPane.YES_NO_OPTION);

                if (result == JOptionPane.YES_OPTION)
                {
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    master.toggleButtons(true);
                }
            }
        });
        
        main = theFrame.getContentPane();
        main.setLayout(new BorderLayout());
        
    /* *** CENTER *** */
        JLabel TradSymbol = new JLabel("≤∆≥");
        TradSymbol.setHorizontalAlignment(SwingConstants.CENTER);

        JP_CENTER = new JPanel();
        JPanel ctrAux1 = new JPanel();
        JPanel ctrAux2 = new JPanel();
        JPanel ctrAux3 = new JPanel();
        JPanel ctrAux4 = new JPanel();
        JPanel listsAux1 = new JPanel();
        JPanel listsAux2 = new JPanel();
        
        JP_CENTER.setLayout(new BorderLayout());
        ctrAux1.setLayout(new GridLayout(5,1));
        ctrAux2.setLayout(new GridLayout(5,1));
        ctrAux3.setLayout(new GridLayout(5,1));
        ctrAux4.setLayout(new GridLayout(5,1));
        listsAux1.setLayout(new BorderLayout());
        listsAux2.setLayout(new BorderLayout());
        
        for (int index = 0; index < 5; index++)
        {
            LEFT_MES[index] = new JTextArea(resName[index] + tradeLog,
                                            JTA_height, JTA_width);
            RIGHT_MES[index] = new JTextArea(resName[index] + tradeLog,
                                            JTA_height, JTA_width);
            LEFT_DELTA[index] = new JTextArea(" 0", JTA_height, JTA_width);
            RIGHT_DELTA[index] = new JTextArea(" 0", JTA_height, JTA_width);
            
            LEFT_MES[index].setEditable(false);
            RIGHT_MES[index].setEditable(false);
            LEFT_DELTA[index].setEditable(false);
            RIGHT_DELTA[index].setEditable(false);
            
            ctrAux1.add(LEFT_MES[index]);
            ctrAux2.add(RIGHT_MES[index]);
            ctrAux3.add(LEFT_DELTA[index]);
            ctrAux4.add(RIGHT_DELTA[index]);
        }
        
        playerOneName = new JLabel("\"" + playerOne.getName() + "\"");
        playerTwoName = new JLabel("\"" + playerTwo.getName() + "\"");
        playerOneName.setHorizontalAlignment(SwingConstants.CENTER);
        playerTwoName.setHorizontalAlignment(SwingConstants.CENTER);
        
        listsAux1.add(ctrAux1, BorderLayout.WEST);
        listsAux1.add(ctrAux3, BorderLayout.EAST);
        listsAux1.add(new JLabel(" "), BorderLayout.CENTER);
        listsAux1.add(playerOneName, BorderLayout.SOUTH);
        listsAux2.add(ctrAux2, BorderLayout.WEST);
        listsAux2.add(ctrAux4, BorderLayout.EAST);
        listsAux2.add(new JLabel(" "), BorderLayout.CENTER);
        listsAux2.add(playerTwoName, BorderLayout.SOUTH);
        
        JP_CENTER.add(TradSymbol, BorderLayout.CENTER);
        JP_CENTER.add(listsAux1, BorderLayout.WEST);
        JP_CENTER.add(listsAux2, BorderLayout.EAST);
        
        
        
        main.add(JP_CENTER, BorderLayout.CENTER);
        
    /* *** TOP *** */
        JP_NORTH = new JPanel();
        JPanel topAux1 = new JPanel();
        JPanel topAux2 = new JPanel();
        
        JP_NORTH.setLayout(new BorderLayout());
        topAux1.setLayout(new GridLayout(1, 5));
        topAux2.setLayout(new GridLayout(1, 1));
        
        playerQty = new JLabel("  ");
        playerQty.setHorizontalAlignment(SwingConstants.CENTER);
        buttonClay = new JButton ("Clay");
        buttonLumber = new JButton ("Lumber");
        buttonOre = new JButton ("Ore");
        buttonSheep = new JButton ("Sheep");
        buttonWheat = new JButton ("Wheat");
        
        topAux1.add(buttonClay);
        topAux1.add(buttonLumber);
        topAux1.add(buttonOre);
        topAux1.add(buttonSheep);
        topAux1.add(buttonWheat);
        
        topAux2.add(playerQty);
        
        JP_NORTH.add(topAux1, BorderLayout.NORTH);
        JP_NORTH.add(topAux2, BorderLayout.SOUTH);
        
        main.add(JP_NORTH, BorderLayout.NORTH);
        
    /* BOTTOM */
        JP_SOUTH = new JPanel();
        JP_SOUTH.setLayout(new GridLayout(1, 2));
        //JPanel botAux1 = new JPanel();
        //JPanel botAux2 = new JPanel();
        
        //JP_SOUTH.setLayout(new BorderLayout());
        //botAux1.setLayout(new GridLayout(1, 2));
        //botAux2.setLayout(new FlowLayout());
        
        //playerOneName = new JLabel("\"" + playerOne.getName() + "\"");
        //playerTwoName = new JLabel("\"" + playerTwo.getName() + "\"");
        //playerOneName.setHorizontalAlignment(SwingConstants.CENTER);
        //playerTwoName.setHorizontalAlignment(SwingConstants.CENTER);
        //botAux1.add(playerOneName);
        //botAux1.add(playerTwoName);
        
        finishTrade = new JButton (" Offer  ");
        cancelTrade = new JButton (" Cancel ");
        //botAux2.add(finishTrade);
        //botAux2.add(cancelTrade);
        
        //JP_SOUTH.add(botAux1, BorderLayout.NORTH);
        //JP_SOUTH.add(botAux2, BorderLayout.SOUTH);
        
        JP_SOUTH.add(finishTrade);
        JP_SOUTH.add(cancelTrade);
        
        main.add(JP_SOUTH, BorderLayout.SOUTH);
        
    /* LEFT */
        JP_WEST = new JPanel();
        JP_WEST.setLayout(new GridLayout(4, 1));
        
        JLabel auxLeft = new JLabel("\n \n");
        plusTrade1 = new JButton("+");
        minusTrade1 = new JButton("-");
        
        JP_WEST.add(auxLeft);
        JP_WEST.add(plusTrade1);
        JP_WEST.add(minusTrade1);
        
        main.add(JP_WEST, BorderLayout.WEST);
        
    /* RIGHT */
        JP_EAST = new JPanel();
        JP_EAST.setLayout(new GridLayout(4, 1));
        
        JLabel auxRight = new JLabel("\n \n \n");
        plusTrade2 = new JButton("+");
        minusTrade2 = new JButton("-");
        
        plusTrade2.setEnabled(false);
        minusTrade2.setEnabled(false);
        
        JP_EAST.add(auxRight);
        JP_EAST.add(plusTrade2);
        JP_EAST.add(minusTrade2);
                
        main.add(JP_EAST, BorderLayout.EAST);
        
        /* actionListener activation */
        cancelTrade.addActionListener(this);
        finishTrade.addActionListener(this);
        buttonClay.addActionListener(this);
        buttonLumber.addActionListener(this);
        buttonOre.addActionListener(this);
        buttonSheep.addActionListener(this);
        buttonWheat.addActionListener(this);
        plusTrade1.addActionListener(this);
        minusTrade1.addActionListener(this);
        plusTrade2.addActionListener(this);
        minusTrade2.addActionListener(this);
        
        theFrame.pack();
        theFrame.setVisible(true);
        theFrame.setLocationRelativeTo(null);
        theFrame.setMinimumSize(theFrame.getMinimumSize());
    }


    public boolean terminate()
    {
        boolean terminated = false;
        int n = JOptionPane.showConfirmDialog(theFrame,
                        "Your trading will be terminated. \n" +
                        "If you open a trade, you are allowed \n" +
                        "to interact only with the trading window", "",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.WARNING_MESSAGE);
        theFrame.toFront();
        if (n == JOptionPane.OK_OPTION)
        {
            terminated = true;
            theFrame.dispose();
        }
        if (n == JOptionPane.NO_OPTION)
        {}
        if (n == JOptionPane.CLOSED_OPTION)
        {}
        return terminated;
    }
    
    public void toFront()
    {
        theFrame.toFront();
    }

    /* actionPerformed */
    public void actionPerformed(ActionEvent evt)
    {
        if (evt.getSource() == cancelTrade)
        {
            theFrame.dispose();
            master.toggleButtons(true);
        }
    /* OFFER - EXCHANGE mechanics */        
        if (evt.getSource() == finishTrade)
        {
            if (step == 0)
            {
                playerQty.setText(" ");
                step = 1;
                curRes = -1;
                plusTrade1.setEnabled(false);
                minusTrade1.setEnabled(false);
                plusTrade2.setEnabled(true);
                minusTrade2.setEnabled(true);
                
                JOptionPane.showMessageDialog(theFrame,
                        "Please call \"" + playerTwo.getName() + 
                        "\" to the computer", "", JOptionPane.WARNING_MESSAGE);
            }
            else if (step == 1)
            {
                playerQty.setText(" ");
                step = 2;
                curRes = -1;
                plusTrade2.setEnabled(false);
                minusTrade2.setEnabled(false);
                buttonClay.setEnabled(false);
                buttonLumber.setEnabled(false);
                buttonOre.setEnabled(false);
                buttonSheep.setEnabled(false);
                buttonWheat.setEnabled(false);
                finishTrade.setText("Exchange");
                JOptionPane.showMessageDialog(theFrame,
                        "Please call \"" + playerOne.getName() + 
                        "\" to the computer\n to review your offer",
                        "", JOptionPane.WARNING_MESSAGE);
            }
            else if (step == 2)
            {
                int n = JOptionPane.showConfirmDialog(theFrame,
                    "Are you sure you want to proceed?",
                    "Warning", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
                if (n == JOptionPane.OK_OPTION)
                {
                    int[] finalResOne = new int[5];
                    int[] finalResTwo = new int[5];
                    
                    for(int index = 0; index < 5; index++)
                    {
                        finalResOne[index] = playerOneNewRes[index] +
                                             playerTwoResources[index] -
                                             playerTwoNewRes[index];
                                             
                        finalResTwo[index] = playerTwoNewRes[index] +
                                             playerOneResources[index] -
                                             playerOneNewRes[index];
                    }
                        
                    
                    playerOne.updateResources(finalResOne[0],
                                              finalResOne[1],
                                              finalResOne[2],
                                              finalResOne[3],
                                              finalResOne[4]);
                                              
                    playerTwo.updateResources(finalResTwo[0],
                                              finalResTwo[1],
                                              finalResTwo[2],
                                              finalResTwo[3],
                                              finalResTwo[4]);
                    
                    playerOne.displayResourceHand();
                    master.toggleButtons(true);
                    theFrame.dispose();
                }
                if (n == JOptionPane.NO_OPTION)
                {}
                if (n == JOptionPane.CLOSED_OPTION)
                {}
            }
        }
        
        if (evt.getSource() == buttonClay)
        {
            if (step == 0)
                playerQty.setText("Clay available: " + playerOneResources[0]);
            else
                playerQty.setText("Clay available: " + playerTwoResources[0]);
            curRes = 0;
            /* buttonClay.setFocusable(true); */ 
        }
        
        
        /* *** RESOURCE BUTTONS *** */    
        if (evt.getSource() == buttonLumber)
        {
            if (step == 0)
                playerQty.setText("Lumber available: " +
                                    playerOneResources[1]);
            else
                playerQty.setText("Lumber available: " +
                                    playerTwoResources[1]);
            curRes = 1;
        }

        if (evt.getSource() == buttonOre)
        {
            if (step == 0)
                playerQty.setText("Ore available: " +
                                    playerOneResources[2]);
            else
                playerQty.setText("Ore available: " +
                                    playerTwoResources[2]);
            curRes = 2;
        }

        if (evt.getSource() == buttonSheep)
        {
            if (step == 0)
                playerQty.setText("Sheep available: " +
                                    playerOneResources[3]);
            else
                playerQty.setText("Sheep available: " +
                                    playerTwoResources[3]);
            curRes = 3;
        }

        if (evt.getSource() == buttonWheat)
        {
            if (step == 0)
                playerQty.setText("Wheat available: " + 
                                    playerOneResources[4]);
            else
                playerQty.setText("Wheat available: " + 
                                    playerTwoResources[4]);
            curRes = 4;
        }
        
        
        /* *** PLUS & MINUS FUNCTIONALITY *** */
        if (evt.getSource() == plusTrade1)
        {
            if (curRes >= 0)
            {
                if (playerOneNewRes[curRes] > 0)
                {
                    int delta;
                    playerOneNewRes[curRes]--;
                    delta = playerOneResources[curRes] -
                            playerOneNewRes[curRes];
                    LEFT_DELTA[curRes].setText(" " + delta);
                }
                else
                    JOptionPane.showMessageDialog(theFrame,
                            "You don't have enough resources", "Warning",
                            JOptionPane.WARNING_MESSAGE);
            }
            else
                JOptionPane.showMessageDialog(theFrame,
                            "Please select a resource first", "Info",
                            JOptionPane.INFORMATION_MESSAGE);
        }
        
        
        if (evt.getSource() == minusTrade1)
        {
            if (curRes >= 0)
            {
                if (playerOneNewRes[curRes] < playerOneResources[curRes])
                {
                    int delta;
                    playerOneNewRes[curRes]++;
                    delta = playerOneResources[curRes] -
                            playerOneNewRes[curRes];
                    LEFT_DELTA[curRes].setText(" " + delta);
                }
                else
                    JOptionPane.showMessageDialog(theFrame,
                            "You have already emptied your offer", "Warning",
                            JOptionPane.WARNING_MESSAGE);
            }
            else
                JOptionPane.showMessageDialog(theFrame,
                            "Please select a resource first", "Info",
                            JOptionPane.INFORMATION_MESSAGE);
        }
        
        if (evt.getSource() == plusTrade2)
        {
            if (curRes >= 0)
            {
                if (playerTwoNewRes[curRes] > 0)
                {
                    int delta;
                    playerTwoNewRes[curRes]--;
                    delta = playerTwoResources[curRes] -
                            playerTwoNewRes[curRes];
                    RIGHT_DELTA[curRes].setText(" " + delta);
                }
                else
                    JOptionPane.showMessageDialog(theFrame,
                            "You don't have enough resources", "Warning",
                            JOptionPane.WARNING_MESSAGE);
            }
            else
                JOptionPane.showMessageDialog(theFrame,
                            "Please select a resource first", "Info",
                            JOptionPane.INFORMATION_MESSAGE);
        }
        
        if (evt.getSource() == minusTrade2)
        {
            if (curRes >= 0)
            {
                if (playerTwoNewRes[curRes] < playerTwoResources[curRes])
                {
                    int delta;
                    playerTwoNewRes[curRes]++;
                    delta = playerTwoResources[curRes] -
                            playerTwoNewRes[curRes];
                    RIGHT_DELTA[curRes].setText(" " + delta);
                }
                else
                    JOptionPane.showMessageDialog(theFrame,
                            "You have already emptied your offer", "Warning",
                            JOptionPane.WARNING_MESSAGE);
            }
            else
                JOptionPane.showMessageDialog(theFrame,
                            "Please select a resource first", "Info",
                            JOptionPane.INFORMATION_MESSAGE);
        }
    }
}