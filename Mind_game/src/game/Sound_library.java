package game;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound_library {

    private float volume;
    private File file;
    private Clip clip;

    public Sound_library(String startFile, float volume) {
        select(startFile);
        this.volume = volume;
    } 

    public void select(String fileName) {
        file = new File("src//sounds//" + fileName);
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
            setVolume(1.0f);

        } catch (Exception e) {
            System.out.println("Error while trying to load audio file: " + e);
        }
    }
    
    public void play(){
        setVolume(volume);
        clip.setFramePosition(0);
        clip.start();
    }

    public void loop(){
        setVolume(volume);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){
        clip.stop();
    }
    
    public void setVolume(float volume) {
        if (volume < 0f || volume > 1f)
            throw new IllegalArgumentException("Volume not valid: " + volume);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);        
        gainControl.setValue(20f * (float) Math.log10(volume));
    }
    
    public Clip getClip() {
        return clip;
    }


    //test Audio
    
    
   /* public static void main(String[] args) {

        Sound_library audio = new Sound_library("Dragon-roar-sound.wav", 0.4f);
        try {
            audio.play();
            Thread.sleep(audio.getClip().getMicrosecondLength()/1000);
        } catch (Exception e) {    }

    }*/
    
    
}