/* PLEASE DON'T MAKE ANY CHANGES TO THIS FILE */

/* Opens a new "child" window for trading in CatanGame */

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
    private static JTextArea LEFT, RIGHT;
    private JLabel playerQty;
    
    /* Qty of each resource of trading players */
    private int playerOneClay, playerOneLumber, playerOneOre,
                playerOneSheep, playerOneWheat;
    private int playerTwoClay, playerTwoLumber, playerTwoOre,
                playerTwoSheep, playerTwoWheat;
    
    private int step = 0; /* stage of trading */
    
    public OpenTrade(Player one, Player two)
    {
        playerOneClay = one.getClay();
        playerOneLumber = one.getLumber();
        playerOneOre = one.getOre();
        playerOneSheep = one.getSheep();
        playerOneWheat = one.getWheat();
        
        playerTwoClay = two.getClay();
        playerTwoLumber = two.getLumber();
        playerTwoOre = two.getOre();
        playerTwoSheep = two.getSheep();
        playerTwoWheat = two.getWheat();
        
        System.out.println("Player One -> Wheat -> " + playerOneWheat);
    }
    
    public void run()
    {
        int JTA_width = 15; /* JTextArea width */
        int JTA_height = 6; /* ... height */
    
        theFrame = new JFrame("Trade Dialog");
        theFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        main = theFrame.getContentPane();
        main.setLayout(new BorderLayout());
        
        /* CENTER */
        JLabel TradSymbol = new JLabel("≤∆≥");
        TradSymbol.setHorizontalAlignment(SwingConstants.CENTER);

        JP_CENTER = new JPanel();
        JP_CENTER.setLayout(new BorderLayout());
        LEFT = new JTextArea(JTA_height, JTA_width);
        RIGHT = new JTextArea(JTA_height, JTA_width);
        LEFT.setEditable(false);
        RIGHT.setEditable(false);
        
        JP_CENTER.add(TradSymbol, BorderLayout.CENTER);
        JP_CENTER.add(LEFT, BorderLayout.WEST);
        JP_CENTER.add(RIGHT, BorderLayout.EAST);
        
        main.add(JP_CENTER, BorderLayout.CENTER);
        
        /* TOP */
        JP_NORTH = new JPanel();
        JPanel topAux1 = new JPanel();
        JPanel topAux2 = new JPanel();
        
        JP_NORTH.setLayout(new BorderLayout());
        topAux1.setLayout(new GridLayout(1, 5));
        topAux2.setLayout(new GridLayout(1, 1));
        
        playerQty = new JLabel("  ");
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
        
        finishTrade = new JButton ("Exchange");
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
        
        JP_EAST.add(aux);
        JP_EAST.add(plusTrade2);
        JP_EAST.add(minusTrade2);
        JP_EAST.add(aux);
        
        main.add(JP_EAST, BorderLayout.EAST);
        
        /* actionListener activation */
        cancelTrade.addActionListener(this);
        
        theFrame.pack();
        theFrame.setVisible(true);
    }
    /* MAIN */
    /*public static void main(String[] args)
    {
        DrawingCanvas tempo = new DrawingCanvas();
        Player one = new Player(1, "asd", tempo, LEFT);
        Player two = new Player(2, "dsa", tempo, RIGHT);
        
        OpenTrade asd = new OpenTrade(one, two);
        SwingUtilities.invokeLater(asd);
    }
    */
    /* actionPerformed */
    public void actionPerformed(ActionEvent evt)
    {
        if (evt.getSource() == cancelTrade)
            theFrame.dispose();
            
        if (evt.getSource() == buttonClay)
        {
            playerQty.setText("" + playerOneClay);
            buttonClay.setFocusable(true);
        }
            
        if (evt.getSource() == buttonLumber)
        {}

        if (evt.getSource() == buttonOre)
        {}

        if (evt.getSource() == buttonSheep)
        {}

        if (evt.getSource() == buttonWheat)
        {}
    }
}