import javax.swing.*;
import javax.sound.midi.*;
import java.io.*;

public class Main extends JFrame {
    private static void println(java.lang.Object s) {
        System.out.println(s);
    }

    private static void print(java.lang.Object s) {
        System.out.print(s);
    }

    private static void createAndShowGUI() {
        //just testing swing gui creation. We probably want to create a new class for this later rather than do this.

        //Create and set up the window.
        JFrame frame = new JFrame("Music Theory");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Hello World Label
        JLabel label = new JLabel("Hello World");
        frame.getContentPane().add(label);

        //Display the window.
        frame.pack();
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

        // int timeSignature;
        // Chord chords[];
        //for each chord, all information required:
        //each chord needs roman numeral, may or may not have a number, can be diminished or half-diminished in rare cases, starting notes, bass, tenor, alto, soprano, length





        Chord first;
        try{
            first = new Chord().major(SharpMajors.C).buildChord();
        } catch(Exception e) {println(e);}

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
            synth.getChannels()[9].programChange(synth.getAvailableInstruments()[105].getPatch().getProgram());
            seq.setSequence(mySeq);

            seq.start();
        } catch (Exception e) {
            println("bad file");
            return;
        }

        long length = seq.getMicrosecondLength();

        //let program wait for midi file to end
        try {
            Thread.sleep(length / 1000 + 1000);
        } catch (InterruptedException e) {
            return;
        }

        seq.close();
    }
}