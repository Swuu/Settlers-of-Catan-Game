/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package musicsound;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 *
 * @author Raghav
 */

public class MusicSound {
    
    String[] soundsArray = new String[1000];
    URL url;
    AudioInputStream audioIn;
    Clip clip;
    File file;
    BufferedReader bufRdr;
    
 

    public MusicSound(){
        //Reads from sounds.csv file (which is like a excel file) with all the names of the sounds in it
        //Puts values into a soundsarray, saves us from hardcoding a huge array with all values in code.
        try{
           String line = null;	
           int row = 0;	 	
 
           file = new File("sounds.csv");
           bufRdr  = new BufferedReader(new FileReader(file));
 
           //read each line of text file
           while((line = bufRdr.readLine()) != null && row < 1000){	
              StringTokenizer st = new StringTokenizer(line,",");
              while (st.hasMoreTokens())
                 st.nextToken();
           }
           soundsArray = line.split(",");
           }
           catch(Exception e){
              e.printStackTrace();
           }
    }
    //while constructor gets the location of sound, load_Sound gets the URL.
    public void load_Sound(int musicfile){
        //Opens the audio inpuit stream.
        try{
            url = this.getClass().getClassLoader().getResource(soundsArray[musicfile]);
            audioIn = AudioSystem.getAudioInputStream(url);
        }
        catch (UnsupportedAudioFileException | IOException e) {
         e.printStackTrace();
        }
        play_Sound(audioIn);
    }
    //This method plays the sound.
    public void play_Sound(AudioInputStream audioIn){
       try{
            // Get a sound clip resource.
            clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            clip.start();
       }
       catch (LineUnavailableException | IOException e){
         e.printStackTrace();
        }
    }
}
