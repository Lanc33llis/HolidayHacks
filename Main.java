import javax.swing.*;
import javax.imageio.ImageIO;
import javax.sound.midi.*;
import java.awt.BorderLayout;
import java.io.*;
import java.awt.image.*;

public class Main extends JFrame {
    public static void println(java.lang.Object s) {
        System.out.println(s);
    }

    public static void print(java.lang.Object s) {
        System.out.print(s);
    }

    Main() {
        JFrame frame = new JFrame("Music Theory");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);

        JMenuBar menuBar = new JMenuBar();
        JMenu mFile = new JMenu("File");
        JMenu mHelp = new JMenu("Help");
        menuBar.add(mFile);
        menuBar.add(mHelp);

        JPanel content = new JPanel();

        BufferedImage musicSheet = null;
        try {
            musicSheet = ImageIO.read(new File("music part.png"));
        } catch (IOException e) {
            try {
                musicSheet = ImageIO.read(this.getClass().getResource("music part.png"));
            } catch (IOException b) {println(b);};
        }

        JLabel picLabel = new JLabel(new ImageIcon(musicSheet));

        content.add(picLabel);

        frame.add(menuBar, BorderLayout.NORTH);
        frame.add(content, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        //Creates window GUI
        // javax.swing.SwingUtilities.invokeLater(new Runnable() {
        //     public void run() {
        //         createAndShowGUI();
        //     }
        // });

        new Main();

        // int timeSignature;
        // Chord chords[];
        //for each chord, all information required:
        //each chord needs roman numeral, may or may not have a number, can be diminished or half-diminished in rare cases, starting notes, bass, tenor, alto, soprano, length


        

        // Java "builders" aka a chord is built with the variables functions rather than the constructor with the buildChord() function finalizing the chord from input
        Chord myChord = new Chord().major(SharpMajors.A).minor(SharpMinors.Cs).number(Number.fourTwo);
        try {
            myChord.buildChord();
        } catch (Exception e) {
            println(e);
        }

        Chord a = new Chord().major(SharpMajors.A).minor(SharpMinors.Cs).build(myChord);
        //midi devices
        Sequencer seq;
        Synthesizer synth;
        Receiver receiver;
        Transmitter transmitter;

        //midi initialization
        try {
            seq = MidiSystem.getSequencer();
            seq.open();
            synth = MidiSystem.getSynthesizer();
            synth.open();
            receiver = synth.getReceiver();
            transmitter = seq.getTransmitter();
            transmitter.setReceiver(receiver);
        } catch (MidiUnavailableException e) {
            println(e.getMessage());
            return;
        }

        
        // synth.loadAllInstruments(synth.getDefaultSoundbank());
        
        //play midi file
        try {
            File myMidiFile = new File("scale_dsharp_minor.mid");
            
            //sequence for sequencer
            Sequence mySeq = MidiSystem.getSequence(myMidiFile);
            // println(synth.getAvailableInstruments().length);
            // synth.getChannels()[9].programChange(synth.getAvailableInstruments()[105].getPatch().getProgram());
            seq.setSequence(mySeq);

            //starts the music
            // seq.start();
        } catch (Exception e) {
            println("bad file");
            return;
        }

        
        //let program wait for midi file to end
        try {
            long length = seq.getMicrosecondLength();
            Thread.sleep(length / 1000 + 1);
        } catch (InterruptedException e) {
            return;
        }

        seq.close();
    }
}