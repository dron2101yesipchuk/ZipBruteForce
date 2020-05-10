import net.lingala.zip4j.exception.ZipException;

public class Main {

    public static void main(String[] args) {
        PasswordFinder finder = new PasswordFinder();

        try {
            finder.findZipPassword("/Users/dron/IdeaProjects/ZipBruteForce/src/lab10archive.zip");
        } catch (ZipException e) {
            e.printStackTrace();
        }

        //finder.findPassword("test");
    }
}
