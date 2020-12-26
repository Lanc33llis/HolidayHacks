
enum sharpMajors {C, G, D, A, E, B, Fs}; //major keys that use sharps rather than flats
enum sharpMinors {A, E, B, Fs, Cs, Gs, Ds}; //minor keys that use sharps
enum flatMajors {F, Bb, Eb, Ab, Db, Gb, Cb}; //major keys that use flats rather than sharps
enum flatMinors {D, G, C, F, Bb, Eb, Ab}; //minor keys that use flats 
enum number {six, sixFour, seven, sixFive, fourThree, fourTwo};
enum notes {
    A(1), B(4), C(7), D(10), E(13), F(16), G(19), flat(1), sharp(2);
    private int numVal;

    notes(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }


};



// accidental boolean true = sharp, false = flat
public class Chord {
    private int major;
    private int minor;
    private boolean accidental;
    private int romanNumeral;
    private int number;
    private int notes[];



    public Chord(int major, int minor, boolean accidental, int romanNumeral) {
        this.major = major;
        this.minor = minor;
        this.accidental = accidental;
        this.romanNumeral = romanNumeral;
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
