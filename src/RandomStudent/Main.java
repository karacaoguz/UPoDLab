package RandomStudent;

import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        FileReader fileReader = new FileReader();
        StudentListManager studentListManager = new StudentListManager();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Kaç öğrenci seçmek istersiniz: ");
        int numStudents = scanner.nextInt();
        scanner.nextLine();

        String[] studentArray = fileReader.readStudentListFromFile("/Users/karaca/IdeaProjects/UPoDLab/src/RandomStudent/students.txt");

        if (studentArray == null || studentArray.length == 0) {
            System.out.println("Öğrenci listesi boş");
            return;
        }
        Random random = new Random();
        System.out.println("Seçilen " + numStudents + " öğrenciler: ");
        for (int i = 0; i < numStudents; i++) {
            int index = random.nextInt(studentArray.length);
            String randomStudent = studentArray[index];
            Gender gender = getGenderByLastCharacter(randomStudent);
            randomStudent = randomStudent.substring(0, randomStudent.length() - 2);
            System.out.println((i + 1) + " - " + randomStudent + gender.getPostFix());
            studentArray = studentListManager.removeStudentFromArray(studentArray, index);


        }
    }

    private static Gender getGenderByLastCharacter(String studentName) {
        char lastCharacter = studentName.charAt(studentName.length() - 1);
        return Character.toLowerCase(lastCharacter) == 'e' ? Gender.MALE : Gender.FEMALE;
    }
}
