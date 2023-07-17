package RandomStudent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
    public String[] readStudentListFromFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);

        Scanner scanner = new Scanner(file);
        int numLines = 0;
        while (scanner.hasNextLine()) {
            numLines++;
            scanner.nextLine();
        }
        String[] studentList = new String[numLines];
        scanner = new Scanner(file);
        for (int i = 0; i < numLines; i++) {
            studentList[i] = scanner.nextLine();
        }
        return studentList;
    }
}
