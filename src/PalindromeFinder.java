import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PalindromeFinder {

    /**
     * Находит все числа в диапазоне [A, B], удовлетворяющие условиям:
     * - палиндром
     * - сумма цифр - простое число
     * - содержит повторяющиеся цифры
     */
    public static List<Integer> findNumbers(int A, int B) {
        List<Integer> result = new ArrayList<>();
        int start = Math.min(A, B);
        int end = Math.max(A, B);

        for (int num = start; num <= end; num++) {
            if (isPalindrome(num) &&
                    isPrime(digitSum(num)) &&
                    hasRepeatedDigits(num)) {
                result.add(num);
            }
        }

        return result;
    }

    /**
     * Проверяет, является ли число палиндромом
     */
    public static boolean isPalindrome(int num) {
        if (num < 0) return false;

        String str = String.valueOf(num);
        int left = 0;
        int right = str.length() - 1;

        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    /**
     * Вычисляет сумму цифр числа
     */
    public static int digitSum(int num) {
        int sum = 0;
        int temp = Math.abs(num);

        while (temp > 0) {
            sum += temp % 10;
            temp /= 10;
        }

        return sum;
    }

    /**
     * Проверяет, является ли число простым
     */
    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;

        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) return false;
        }

        return true;
    }

    /**
     * Проверяет, содержит ли число повторяющиеся цифры
     */
    public static boolean hasRepeatedDigits(int num) {
        String str = String.valueOf(num);
        Set<Character> digits = new HashSet<>();

        for (char c : str.toCharArray()) {
            if (!digits.add(c)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Дополнительный метод для тестирования отдельного числа
     */
    public static void testNumber(int num) {
        boolean palindrome = isPalindrome(num);
        int sum = digitSum(num);
        boolean primeSum = isPrime(sum);
        boolean repeated = hasRepeatedDigits(num);

        System.out.printf("Число: %d%n", num);
        System.out.printf("Палиндром: %s%n", palindrome ? "Да" : "Нет");
        System.out.printf("Сумма цифр: %d (Простое: %s)%n", sum, primeSum ? "Да" : "Нет");
        System.out.printf("Повторяющиеся цифры: %s%n", repeated ? "Да" : "Нет");
        System.out.printf("Соответствует всем условиям: %s%n",
                (palindrome && primeSum && repeated) ? "Да" : "Нет");
        System.out.println("-".repeat(30));
    }
}