package OrderArray;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Random rnd = new Random();
        int[] nums = new int[5];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = rnd.nextInt(10) + 1;
        }
        System.out.println("Dizideki sayılar: " + Arrays.toString(nums));

        Scanner scanner = new Scanner(System.in);
        while (!isSorted(nums)) {
            System.out.println("Hangi iki index'in yerini " +
                    "değiştirmek istiyorsunuz");
            int i = scanner.nextInt();
            int j = scanner.nextInt();
            swap(nums, i, j);
            System.out.println(Arrays.toString(nums));
        }

        System.out.println("Tebrikler diziyi sıraladınız.");
        System.out.println("Sıralı dizi: " + Arrays.toString(nums));
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }
}

// 5 7 1 5 9
// 1 7 5 5 9
// 1 5 5 7 9