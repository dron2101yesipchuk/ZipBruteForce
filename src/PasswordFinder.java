import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import java.time.Duration;
import java.time.LocalDateTime;

public class PasswordFinder {

    private char[] ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789,._+-()".toCharArray();

    public void findPassword(String password) {

        LocalDateTime startTime = LocalDateTime.now();

        CombinationsManager combinationsManager = new CombinationsManager(ALPHABET, 1);
        String combination = combinationsManager.getCombination();

        while (!password.equals(combination)) {
            combination = combinationsManager.getCombination();
            combinationsManager.nextCombination();
        }

        printResult(combination, startTime, LocalDateTime.now());
    }

    public void findZipPassword(String zipFilePath) throws ZipException {
        // Get unzip file path by removing .zip from the zipped file name
        String unZipFilePath = zipFilePath.substring(0, zipFilePath.lastIndexOf("."));
        ZipFile zipFile = openZipFile(zipFilePath);

        if (zipFile != null) {
            LocalDateTime startTime = LocalDateTime.now();

            CombinationsManager combinationsManager = new CombinationsManager(ALPHABET, 1);
            String combination = "";

            while (true) {
                combination = combinationsManager.getCombination();
                try {
                    zipFile.setPassword(combination.toCharArray());
                    zipFile.extractAll(unZipFilePath);// trows ZipException if password is incorrect
                    break;
                } catch (ZipException e) {
                    //Password is incorrect
                    //continue;
                }

                combinationsManager.nextCombination();//generate next password
            }

            printResult(combination, startTime, LocalDateTime.now());
        }
    }

    private ZipFile openZipFile(String path) throws ZipException {
        String unZipFilePath = path.substring(0, path.lastIndexOf("."));
        ZipFile zipFile = null;
        zipFile = new ZipFile(path);
        return zipFile;
    }

    private void printResult(String password, LocalDateTime startTime, LocalDateTime endTime) {
        System.out.print("Password: " + password + ". Time to find: ");

        String time = Duration.between(startTime, endTime).toString();
        time = time.replace("PT", "");
        time = time.replace("M", "m ");
        time = time.replace("S", "s ");
        System.out.println(time);
    }

}
