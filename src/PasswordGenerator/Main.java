package PasswordGenerator;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Şifre uzunluğunu giriniz: (Minimum sayı: 4) ");
            int passwordLength = scanner.nextInt();

            if(passwordLength >= 4){

                System.out.println("Büyük harf kullanılsın mı? (E / H)");
                boolean includeUpperCase = scanner.next().equalsIgnoreCase("E");

                System.out.println("Küçük harf kullanılsın mı? (E / H)");
                boolean includeLowerCase = scanner.next().equalsIgnoreCase("E");

                System.out.println("Rakam kullanılsın mı? (E / H)");
                boolean includeNumber = scanner.next().equalsIgnoreCase("E");

                System.out.println("Özel karakter kullanılsın mı? (E / H)");
                boolean includeChar = scanner.next().equalsIgnoreCase("E");

                String password = generateValidPassword(passwordLength, includeUpperCase, includeLowerCase, includeNumber, includeChar);
                System.out.println("Oluşturulan şifre: " + password);

                System.out.println("Şifrenizi kaydetmek ister misiniz? (E / H)");
                boolean savePassword = scanner.next().equalsIgnoreCase("E");

                if(savePassword) {
                    savePasswordToFile(password);
                }
                System.out.println("Çıkış için q ya basın, tekrar oluşturmak için herhangi bir tuşa basın");
                String choice = scanner.next();
                if(choice.equalsIgnoreCase("Q")){
                    break;
                }
            }
            else {
                System.out.println("Lütfen geçerli bir şifre uzunluğu giriniz");
                System.out.println();
            }
        }
    }

    private static String generateValidPassword(int passwordLength, boolean includeUpperCase, boolean includeLowerCase, boolean includeNumber, boolean includeChar) {
        String password;
        do {
            password = generatePassword(passwordLength, includeUpperCase, includeLowerCase, includeNumber, includeChar);
        }while(!isValidPassword(password, passwordLength, includeUpperCase, includeLowerCase, includeNumber, includeChar));
        return password;
    }

    private static void savePasswordToFile(String password) {
        try {
            FileWriter fileWriter = new FileWriter("password.txt", true);
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String date = now.format(dateTimeFormatter);
            fileWriter.write("Şifre : " + password + " Tarih ve saat: " + date + "\n");
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String generatePassword(int passwordLength, boolean includeUpperCase, boolean includeLowerCase, boolean includeNumber, boolean includeChar) {
        String upperCaseChars = "ABCDEFGHIKLMNOPQRSTUWXYZ";
        String lowerCaseChars = "abcdefghijklmnopqrstuwyz";
        String numberChars = "123456789";
        String specialChars = "!@#$?";

        String password = "";
        String chars = "";

        if(includeUpperCase) {
            chars += upperCaseChars;
        }
        if(includeLowerCase) {
            chars += lowerCaseChars;
        }
        if(includeNumber) {
            chars += numberChars;
        }
        if(includeChar) {
            chars += specialChars;
        }
        Random random = new Random();
        for(int i = 0; i < passwordLength; i++) {
            int randomIndex = random.nextInt(chars.length());
            password += chars.charAt(randomIndex);
        }
        return password;
    }

    private static boolean isValidPassword(String password, int passwordLength, boolean includeUpperCase, boolean includeLowerCase, boolean includeNumber, boolean includeChar) {
        if(passwordLength != password.length()) {
            return false;
        }
        if(includeUpperCase && !containsUpperCase(password)) {
            return false;
        }
        if(includeLowerCase && !containsLowerCase(password)) {
            return false;
        }
        if(includeNumber && !containsNumber(password)) {
            return false;
        }
        if(includeChar && !containsSpecialChar(password)) {
            return false;
        }
        return true;
    }

    private static boolean containsSpecialChar(String password) {
        String specialChars = "!@#$?";
        for(char c : password.toCharArray()) {
            if(specialChars.indexOf(c) != -1) {
                return true;
            }
        }
        return false;
    }

    private static boolean containsNumber(String password) {
        for(char c : password.toCharArray()) {
            if(Character.isDigit(c)){
                return true;
            }
        }
        return false;
    }

    private static boolean containsLowerCase(String password) {
        for(char c : password.toCharArray()) {
            if(Character.isUpperCase(c)) {
                return true;
            }
        }
        return false;
    }

    private static boolean containsUpperCase(String password) {
        for(char c : password.toCharArray()) {
            if(Character.isUpperCase(c)){
                return true;
            }
        }
        return false;
    }
}
