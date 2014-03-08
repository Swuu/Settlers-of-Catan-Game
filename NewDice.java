/*Imports Random number package*/
import java.util.*;
/*Imports Drawing package*/
import objectdraw.*;

/*Not sure why we need to import java.awt but we need to*/
import java.awt.*;

public class NewDice{
    
    /*Initialize a space on canvas to draw die for testing*/
    int x_position = 50;
    int y_position = 50;
    
    /*Size of squares*/
    int square_length = 40;
    
    /*Size of rectangles in square*/
    int rect_length = 30;
    int rect_width = 10;
    
    /*Initialize circle diameter*/
    int circle_diameter = 10;
    
    /*space between circles and edges of dice*/
    int space = 5;
    
    public static void main(String args[]){
        System.out.println("We are in main!");
        Dice d = new Dice();
        int rolled1 = d.roll_Dice1();
        int rolled2 = d.roll_Dice2();
        d.draw_dice(rolled1);
        d.draw_dice(rolled2);
    }
        
    public int roll_Dice1(){
            
        /*Let's the user pick a random number when rollling dice*/
        Random generator = new Random();
        int dice = 0;
            
        /*Makes sure the number is between 1 and 6*/
        dice = generator.nextInt(6)+1;
            
        /*stores the number in output*/
        int output = dice;
            
        /*calls draw dice*/
        draw_dice();
            
        return output;
    }
        
    public int roll_Dice2(){
        Random generator = new Random();
        int dice = 0;
            
        for(int i =0; i<20; i++){
            dice = generator.nextInt(6)+1;
                
            /*Print out the random numbers rolled by dice*/
            System.out.println(dice);
        }
        int output = dice;
            
        //draw_dice();
        return output;
    }
        
    public void draw_dice(int number){
        square_dice();
        switch(number){
            case 1:
                one_circle(false,true,false);
                break;
            case 2:
                two_circles(false,true,false);
                break;
            case 3:
                one_circle(true,true,true);
                break;
            case 4:
                two_circles(true,false,true);
                break;
            case 5:
                two_circles(true,false,true);
                one_circle(false,true,false);
                break;
            case 6:
                two_circles(true, true, true);
                break;
        }
    }
    
    public void square_dice(){
        /*Draws dice's square body*/
        DrawRect square = new DrawRect(x_position,y_position,square_length,square_length,canvas);
    }
    
    public void one_circle(boolean draw_at_top, boolean draw_at_middle, boolean draw_at_bottom){
        DrawRect main = new FilledRect(x_position,y_position,rect_length,rect_width,canvas);
        FilledOval center_circle = new FilledOval
            (x_position+space+(rect_length/3.0),y_position+space, circle_diameter,circle_diameter,canvas);
        
    
    }
    
    public void two_circles(boolean draw_at_top, boolean draw_at_middle, boolean draw_at_bottom){
    
    }
}
