//Posted here for Michael. Don't modify this.

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Roulette
{
 private static int numSlotsLeft;
 private static boolean bulletFired; 
 private static int nameIndex;
 private static int namesSize;
 private static boolean hasPulledTrigger;

 public static void main(String[] args)
 {
  int numSlotsInit;
  String decision;
  bulletFired = false;
  nameIndex = 0;
  System.out.println("Welcome to Russian Roulette!");

  while(true)
  {
   Scanner input1 = new Scanner(System.in);
   System.out.println("How many slots would you like to start with?");
   numSlotsInit = Integer.praseInt(input1.nextInt());
   if (numSlotsInit > 1)
    break;
   System.out.println("There must be more than one slot to play this game." + "\n");
  }

  numSlotsLeft = numSlotsInit;
  ArrayList <String> names = new ArrayList<String>();

  while(true)
  {
   Scanner input0 = new Scanner(System.in);
   System.out.println("\n" + "Player " + (nameIndex+1) +" Please enter your name. Type -done if there are no more players");
   String name = input0.nextLine();
   if (name.equals("-done") && names.size() > 1 || names.size() >= numSlotsInit - 1)
   {
    System.out.println("Game Setup Complete. Starting Game." + "\n");
    break;
   }
   else if (name.equals("-done"))
    System.out.println("There are not enough players, please enter another name" + "\n");
   if (names.size() < numSlotsInit)
   {
    names.add(name);
    nameIndex++;
   }
  }

  Collections.shuffle(names);
  namesSize = names.size();
  nameIndex = 0;
  hasPulledTrigger = false;

  while(bulletFired == false)
  {
   System.out.println("It is currently " + names.get(nameIndex) + "'s turn.");
   Scanner input2 = new Scanner(System.in);
   System.out.println("There are currently " + numSlotsLeft + " slots left in the chamber. Type -pull to pull the trigger type -pass to pass the pistol");
   decision = input2.nextLine();
   if (decision.equals("-pull"))
   {
    pullTrigger();
   }
   else if (decision.equals("-pass") && hasPulledTrigger == true)
   {
    passTrigger();
   }
   else if(decision.equals("-pass") && hasPulledTrigger == false)
   {
    System.out.println("You need to pull the trigger at least once before you can pass it!" + "\n");
   }
   else
   {
    System.out.println("Command not recognized, please type again" + "\n");
   }
  }

  System.out.println(names.get(nameIndex) + " shot him or herself!" + "\n");
  System.out.println("Game Over");
  System.exit(0);
  
 }

 public static void pullTrigger()
 {
  int bulletLocation = (int)(Math.random()*numSlotsLeft);
  int fireSlot = (int)(Math.random()*numSlotsLeft);
  System.out.println("You have pulled the trigger..." + "\n");
  if (bulletLocation != fireSlot)
  {
   System.out.println("And Survived!" + "\n");
  }
  if (bulletLocation == fireSlot)
  {
   System.out.println("BANG");
   bulletFired = true;
  }
  numSlotsLeft--;
  hasPulledTrigger = true;
 }

 public static void passTrigger()
 {
  System.out.println("You have passed the trigger." + "\n");
  nameIndex++;
  if (nameIndex >= namesSize)
   nameIndex = 0;
  hasPulledTrigger = false;
 }
}
