

//major keys that use sharps rather than flats
class SharpMajors {
    public static final String list[] = {"C", "G", "D", "A", "E" ,"B", "F♯"};
    public static final int C = 1 , G = 2, D = 3, A = 4, E = 5, B = 6, Fs = 7;
    
}; 
//minor keys that use sharps
class SharpMinors {
    public static final String list[] = {"A", "E", "B", "F♯", "C♯", "G♯", "D♯"};
    public static final int A = 1, E = 2, B = 3, Fs = 4, Cs = 5, Gs = 6, Ds = 7;
}; 
//major keys that use flats rather than sharps
class FlatMajors {
    public static final String list[] = {"F", " B♭", "A♭", "D♭", "G♭", "C♭"};
    public static final int F = 1, Bb = 2, Eb = 3, Ab = 4, Db = 5, Gb = 6, Cb = 7;
}; 
//minor keys that use flats 
class FlatMinors {
    public static final String list[] = {"D", "G", "C", "F", "B♭", "E♭", "A♭"};
    public static final int D = 1, G = 2, C = 3, F = 4, Bb = 5, Eb = 6, Ab = 7;
}; 

class Number {

    public static final int six = 1, sixFour = 2, seven = 3, sixFive = 4, fourThree = 5, fourTwo = 6;
};
class Notes {
    public static final int A = 1, B = 4, C = 7, D = 10, E = 13, F = 16, G = 19, flat = 1, sharp = 2;
};

// accidental boolean true = sharp, false = flat
public class Chord {
    private int major = -1;
    private int minor = -1;
    private boolean accidental = true;
    private int romanNumeral = -1;
    private int number = -1;
    private int notes[] = null;



    // public Chord(int major, int minor, boolean accidental, int romanNumeral, int notes[]) {
    //     this.accidental = accidental;
    //     this.romanNumeral = romanNumeral;
    //     this.major = major;
    //     this.minor = minor;
    //     this.notes = notes;
    // }   

    Chord(){};

    public Chord major(int m) {
        this.major = m;
        return this;
    }

    public Chord minor(int m) {
        this.minor = m;
        return this;
    }

    public Chord accidental(boolean b) {
        this.accidental = b;
        return this;
    }

    public Chord romanNumeral(int r) {
        this.romanNumeral = r;
        return this;
    }

    public Chord number(int n) {
        this.number = n;
        return this;
    }

    public Chord notes(int n[]) {
        this.notes = n;
        return this;
    }

    public Chord buildChord(Chord startingChord){
        return this;
    };

    public Chord buildChord() throws Exception {
        if (notes == null && (major == -1 || minor == -1)) {
            throw new Exception("Chord with no notes with no major or minor!");
        } else if (notes == null) {
            this.notes = new int[4];
            this.notes[0] = Notes.A + Notes.sharp;
            this.notes[1] = Notes.C + Notes.flat;
            this.notes[2] = Notes.B + Notes.sharp;
            this.notes[3] = Notes.E;
        }

        return this;
    }

    public int getMajor() {
        return major;
    }
    public int getMinor() {
        return minor;
    }
    public boolean getAccidental() {
        return accidental;
    }
}
