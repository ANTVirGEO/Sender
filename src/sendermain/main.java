package sendermain;

public class main {

    public static void main(String[] args) {
        FILEIO f = new FILEIO();
        EMAIL e = new EMAIL();
        e.Sender(f.Emails());
    }
}
