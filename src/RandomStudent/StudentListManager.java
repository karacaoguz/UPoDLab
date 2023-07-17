package RandomStudent;

public class StudentListManager {

    public String[] removeStudentFromArray(String[] studentArray, int index) {
        if (studentArray == null || index < 0 || index >= studentArray.length) {
            return studentArray;
        }
        String[] newStudentArray = new String[studentArray.length - 1];
        int j = 0;
        for (int i = 0; i < newStudentArray.length; i++) {
            if (i == index) {
                j++;
            }
            newStudentArray[i] = studentArray[j++];
        }

        return newStudentArray;
    }
}
