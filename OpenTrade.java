/* PLEASE DON'T MAKE ANY CHANGES TO THIS FILE */

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
    private JTextArea LEFT_MES, RIGHT_MES;
    private JTextArea[] LEFT_DELTA = new JTextArea[5];
    private JTextArea[] RIGHT_DELTA = new JTextArea[5];
    private JLabel playerQty;
    
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
    
/*  private String[] resName = {"Clay", "Lumber", "Ore", "Sheep", "Wheat"}; */
    private String tradeLog = "Clay to trade: \n" + "Lumber to trade: \n" +
                                "Ore to trade: \n" + "Sheep to trade: \n" +
                                "Wheat to trade: \n";
    private int step = 0; /* stage of trading */
    private int curRes; /* which resource to trade */
    private Player playerOne, playerTwo;
    
    
    /* CONSTRUCTOR */
    public OpenTrade(Player one, Player two)
    {
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
    }
    
    /* INTERFACE BUILD */
    public void run()
    {
        int JTA_width = 10; /* JTextArea width */
        int JTA_height = 6; /* ... height */
    
        theFrame = new JFrame("Trade Dialog");
        theFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
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
        
        JP_CENTER.setLayout(new BorderLayout());
        ctrAux1.setLayout(new BorderLayout());
        ctrAux2.setLayout(new BorderLayout());
        ctrAux3.setLayout(new GridLayout(5,1));
        ctrAux4.setLayout(new GridLayout(5,1));
        
        LEFT_MES = new JTextArea(tradeLog, JTA_height, JTA_width);
        RIGHT_MES = new JTextArea(tradeLog, JTA_height, JTA_width);
        LEFT_MES.setEditable(false);
        RIGHT_MES.setEditable(false);
        ctrAux1.add(LEFT_MES, BorderLayout.WEST);
        ctrAux2.add(RIGHT_MES, BorderLayout.WEST);
        
        for (int index = 0; index < 5; index++)
        {
            LEFT_DELTA[index] = new JTextArea("0", 0, 2);
            RIGHT_DELTA[index] = new JTextArea("0", 0, 2);
            
            LEFT_DELTA[index].setEditable(false);
            RIGHT_DELTA[index].setEditable(false);
            ctrAux3.add(LEFT_DELTA[index]);
            ctrAux4.add(RIGHT_DELTA[index]);
        }
        ctrAux1.add(ctrAux3, BorderLayout.EAST);
        ctrAux2.add(ctrAux4, BorderLayout.EAST);
        JP_CENTER.add(TradSymbol, BorderLayout.CENTER);
        JP_CENTER.add(ctrAux1, BorderLayout.WEST);
        JP_CENTER.add(ctrAux2, BorderLayout.EAST);
        
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
        JP_SOUTH.setLayout(new FlowLayout());
        
        finishTrade = new JButton ("Offer");
        cancelTrade = new JButton ("Cancel");
        
        JP_SOUTH.add(finishTrade);
        JP_SOUTH.add(cancelTrade);
        
        main.add(JP_SOUTH, BorderLayout.SOUTH);
        
    /* LEFT */
        JP_WEST = new JPanel();
        JP_WEST.setLayout(new GridLayout(4, 1));
        
        JPanel aux = new JPanel();
        plusTrade1 = new JButton("+");
        minusTrade1 = new JButton("-");
        
        JP_WEST.add(aux);
        JP_WEST.add(plusTrade1);
        JP_WEST.add(minusTrade1);
        JP_WEST.add(aux);
        
        main.add(JP_WEST, BorderLayout.WEST);
        
    /* RIGHT */
        JP_EAST = new JPanel();
        JP_EAST.setLayout(new GridLayout(4, 1));
        
        plusTrade2 = new JButton("+");
        minusTrade2 = new JButton("-");
        
        plusTrade2.setEnabled(false);
        minusTrade2.setEnabled(false);
        
        JP_EAST.add(aux);
        JP_EAST.add(plusTrade2);
        JP_EAST.add(minusTrade2);
        JP_EAST.add(aux);
        
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
        
        theFrame.pack();
        theFrame.setVisible(true);
    }


    /* actionPerformed */
    public void actionPerformed(ActionEvent evt)
    {
        if (evt.getSource() == cancelTrade)
            theFrame.dispose();
            
        if (evt.getSource() == finishTrade)
        {
            if (step == 0)
            {
                JOptionPane.showMessageDialog(theFrame,
                        "Please call '" + playerTwo.getName() + 
                        "' to the computer");
                step = 1;
                plusTrade1.setEnabled(false);
                minusTrade1.setEnabled(false);
                plusTrade2.setEnabled(true);
                minusTrade2.setEnabled(true);
            }
        }
            else
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
                                    playerOneResources[1]);
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
            int delta;
            
            if (playerOneNewRes[curRes] > 0)
            {
                playerOneNewRes[curRes]--;
                delta = playerOneResources[curRes] - playerOneNewRes[curRes];
                LEFT_DELTA[curRes].setText("" + delta);
            }
            else
                JOptionPane.showMessageDialog(theFrame,
                        "You don't have enough resources", "Warning",
                        JOptionPane.WARNING_MESSAGE);
                
        }
    }
}