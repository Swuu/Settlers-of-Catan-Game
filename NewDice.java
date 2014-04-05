/*Imports Random number package*/
import java.util.*;
/*Imports Drawing package*/
import objectdraw.*;

/*Not sure why we need to import java.awt but we need to*/
import java.awt.*;

public class NewDice{
    
    public boolean dice1 = true;
    
    public int space_betweeen_dice = 10;
    
    /*length of dice*/
    public int square_length = 30;
    public int square_width = square_length;
    
    /*Size of the three rectangles in square*/
    public int rect_length = square_length;
    public int rect_width = square_length/3;
    
    /*Initialize circle diameter*/
    public int circle_diameter = (rect_width*2)/5;
    
    public int space = (rect_width-circle_diameter)/2;
    /*constructor for second dice*/
    
    /*Initialize a space on canvas to draw/place dice1 for testing*/
    public int dice1_starting_x_position = 50;
    public int dice1_starting_y_position = 50;
    
    /*Staring x and y position for dice 2*/
    public int dice2_starting_x_position = dice1_starting_x_position+square_length+space;
    public int dice2_starting_y_position = dice1_starting_y_position;
    
    /*create new drawing canvas*/
    public DrawingCanvas canvas/* = new DrawingCanvas(500, 500)*/;
    
    /*create theif to check*/
    public static Theif theif;

    NewDice(boolean firstDice){
        /*this will be checked when placing the dice on canvas
         so that dice one and dice two will not overlap*/
        if(!firstDice)
            dice1 = false;
    }
    
    /*default constructor*/
    NewDice(){}
    
    public static void main(String args[]){
        
        /*creates dice 1 and 2*/
        NewDice d = new NewDice();
        NewDice d2 = new NewDice(false);
        
        /*roll dice 1 and 2*/
        int rolled1 = d.roll_Dice();
        int rolled2 = d2.roll_Dice();
        
        /*draw results of rolled dice 1 and 2*/
        d.draw_dice(rolled1);
        d2.draw_dice(rolled2);
    }
        
    private int roll_Dice(){
        Random generator = new Random();
        int dice = 0;
        
        /*gets random number from 1 t0 6*/
        dice = generator.nextInt(6)+1;
        
        /*stores the number in output*/
        int output = dice;

	/*check if dice is equal to theif*/
	if (theif.theifnum == output)
		theif.isBlocked=true;
        
        return output;
    }
        
    private void draw_dice(int number){
        /*Draws dice's square body*/
        square_dice();
        
        /*Switch case to determine what dice to draw*/
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
    
    private void square_dice(){
        int x_position;
        int y_position;
        if(!dice1){
            x_position= dice2_starting_x_position;
            y_position= dice2_starting_y_position;
        }
        else{
            x_position=dice1_starting_x_position;
            y_position=dice1_starting_y_position;
        }
        
        /*This is neccesary to make sure the dice has an outer frame when drawn*/
        FramedRect square = new FramedRect(x_position,y_position,square_length,square_width,canvas);
    }
    
    private void one_circle(boolean draw_at_top, boolean draw_at_middle, boolean draw_at_bottom){
        
        if(draw_at_top)
            one_template(0,space);
        
        if(draw_at_middle)
            one_template(0,(1*rect_width)+space);
        
        if(draw_at_bottom)
            one_template(0,(2*rect_width)+space);
    }
    
    private void two_circles(boolean draw_at_top, boolean draw_at_middle, boolean draw_at_bottom){
        if(draw_at_top){
            two_template(0,space);
        }
        
        if(draw_at_middle){
            two_template(0,(1*rect_width)+space);
        }
        
        if(draw_at_bottom){
            two_template(0,(2*rect_width)+space);
        }
    }
    
    /*put in a method to avoid repetitive code*/
    private void one_template(int extraX,int extraY){
        int x_position;
        int y_position;
        
        if(!dice1){
            x_position= dice2_starting_x_position;
            y_position= dice2_starting_y_position;
        }
        else{
            x_position=dice1_starting_x_position;
            y_position=dice1_starting_y_position;
        }
        
        /*draws frame for rectangle top rectngle in dice image*/
        FramedRect main = new FramedRect(x_position+extraX,y_position+extraY,rect_length,rect_width,canvas);
        
        /*draws circle in framed rectangle*/
        FilledOval center_circle = new FilledOval
        (x_position+(rect_length/3.0)+space,y_position+extraY, circle_diameter,circle_diameter,canvas);
    }
    
    /*put in a method to avoid repetitive code*/
    private void two_template(int extraX, int extraY){
        int x_position;
        int y_position;
        
        if(!dice1){
            x_position= dice2_starting_x_position;
            y_position= dice2_starting_y_position;
        }
        else{
            x_position=dice1_starting_x_position;
            y_position=dice1_starting_y_position;
        }
        
        /*draws frame for rectangle that is one third of dice image*/
        FramedRect main = new FramedRect(x_position+extraX,y_position+extraY,rect_length,rect_width,canvas);
        
        /*draws left circle in framed rectangle*/
        FilledOval left_circle = new FilledOval
        (x_position+space,y_position+space, circle_diameter,circle_diameter,canvas);
        
        /*draws right circle in framed rectangle*/
        FilledOval right_circle = new FilledOval
        (x_position+space+(rect_length/3.0)*2,y_position+space, circle_diameter,circle_diameter,canvas);
    }
}
