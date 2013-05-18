package src;

import java.io.File;

import javax.swing.JPanel;

import org.corebounce.decklight.bouncelets.signal.values.Double;
import org.corebounce.decklight.ports.InDouble;
import org.corebounce.pitchbox.Engine;
import org.corebounce.pitchbox.effects.PitchShift;
import org.corebounce.pitchbox.effects.SoundEffect;
import org.corebounce.pitchbox.utils.TableLayout;

public class NewTester {

	private static final Engine engine = new Engine();
	private static SoundEffect effect;
	private static boolean audioInActive;
	private static File file;
	private static Double ratio = new Double();
	private static final InDouble port = ratio.inDouble;
	private static JPanel pnlControl;
	
	
	/**
	 * @param args
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {

		file = new File("songs/02 Pi.wav");
		audioInActive = false;
		
		Class<SoundEffect> effectClass = null;
	    engine.setAudioInActive(audioInActive);
	    engine.setFile(file);
	    engine.init();
	    effectClass = (Class)PitchShift.class;
	    
	    if (effectClass != null) {
	        try {
	          effect = ((SoundEffect)effectClass.newInstance());
	        } catch (InstantiationException ex) {
	          ex.printStackTrace();
	        } catch (IllegalAccessException ex) {
	          ex.printStackTrace();
	        }
	    }
	    
	    if (effect != null){
	        SoundEffect.SourceMode mode = SoundEffect.SourceMode.AUDIO_IN;
	        if (engine.getFile() != null)
	          mode = engine.isAudioInActive() ? SoundEffect.SourceMode.MIXED : SoundEffect.SourceMode.FILE;
	        effect.initMode(mode);
	        effect.initEngine(engine);
	        	     	        
	        pnlControl = new JPanel();
	        pnlControl.setLayout(new TableLayout(2));
	        
	        effect.initGui(engine, pnlControl);
	        
	        final double result = 100;
	        if (port.getDataType().isInstance(result)){
	        	System.out.println("Trueeeeee");
	        }
	        if (port != null){
	        	System.out.println("NOT NULLLL");
	        }
	        	        
	        if ((engine.isAudioInActive()) || (engine.getFile() != null))          
	          System.out.println("I got here!");
	          engine.commitAndPlay();
	    }
	}

}
