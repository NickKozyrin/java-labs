import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class PasswordValidator {

    public static class ValidationResult {
        private final boolean valid;
        private final List<String> errors;
        private final boolean lengthValid;
        private final boolean hasUppercase;
        private final boolean hasLowercase;
        private final boolean hasDigit;
        private final boolean hasSpecialChar;
        private final boolean hasNoSpaces;
        private final boolean hasValidRepeats;
        private final boolean hasValidSequences;

        public ValidationResult(boolean valid, List<String> errors,
                                boolean lengthValid, boolean hasUppercase,
                                boolean hasLowercase, boolean hasDigit,
                                boolean hasSpecialChar, boolean hasNoSpaces,
                                boolean hasValidRepeats, boolean hasValidSequences) {
            this.valid = valid;
            this.errors = errors;
            this.lengthValid = lengthValid;
            this.hasUppercase = hasUppercase;
            this.hasLowercase = hasLowercase;
            this.hasDigit = hasDigit;
            this.hasSpecialChar = hasSpecialChar;
            this.hasNoSpaces = hasNoSpaces;
            this.hasValidRepeats = hasValidRepeats;
            this.hasValidSequences = hasValidSequences;
        }

        public boolean isValid() { return valid; }
        public List<String> getErrors() { return errors; }
        public boolean isLengthValid() { return lengthValid; }
        public boolean hasUppercase() { return hasUppercase; }
        public boolean hasLowercase() { return hasLowercase; }
        public boolean hasDigit() { return hasDigit; }
        public boolean hasSpecialChar() { return hasSpecialChar; }
        public boolean hasNoSpaces() { return hasNoSpaces; }
        public boolean hasValidRepeats() { return hasValidRepeats; }
        public boolean hasValidSequences() { return hasValidSequences; }
    }

    public static ValidationResult validate(String password) {
        List<String> errors = new ArrayList<>();

        // 1. Проверка длины
        boolean lengthValid = password.length() >= 8 && password.length() <= 20;
        if (!lengthValid) {
            errors.add("Длина пароля должна быть от 8 до 20 символов");
        }

        // 2. Проверка заглавных букв
        boolean hasUppercase = Pattern.compile("[A-ZА-Я]").matcher(password).find();
        if (!hasUppercase) {
            errors.add("Пароль должен содержать хотя бы одну заглавную букву");
        }

        // 3. Проверка строчных букв
        boolean hasLowercase = Pattern.compile("[a-zа-я]").matcher(password).find();
        if (!hasLowercase) {
            errors.add("Пароль должен содержать хотя бы одну строчную букву");
        }

        // 4. Проверка цифр
        boolean hasDigit = Pattern.compile("\\d").matcher(password).find();
        if (!hasDigit) {
            errors.add("Пароль должен содержать хотя бы одну цифру");
        }

        // 5. Проверка специальных символов
        boolean hasSpecialChar = Pattern.compile("[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]").matcher(password).find();
        if (!hasSpecialChar) {
            errors.add("Пароль должен содержать хотя бы один специальный символ");
        }

        // 6. Проверка пробелов
        boolean hasNoSpaces = !password.contains(" ");
        if (!hasNoSpaces) {
            errors.add("Пароль не должен содержать пробелы");
        }

        // 7. Проверка повторяющихся символов (не более 2 подряд)
        boolean hasValidRepeats = true;
        for (int i = 0; i < password.length() - 2; i++) {
            if (password.charAt(i) == password.charAt(i + 1) &&
                    password.charAt(i) == password.charAt(i + 2)) {
                hasValidRepeats = false;
                errors.add("Пароль содержит более двух одинаковых символов подряд");
                break;
            }
        }

        // 8. Проверка последовательностей одной категории (не более 3 подряд)
        boolean hasValidSequences = true;
        for (int i = 0; i < password.length() - 3; i++) {
            String sequence = password.substring(i, i + 4);
            if (isSameCategorySequence(sequence)) {
                hasValidSequences = false;
                errors.add("Пароль содержит более трех символов одной категории подряд");
                break;
            }
        }

        boolean isValid = lengthValid && hasUppercase && hasLowercase &&
                hasDigit && hasSpecialChar && hasNoSpaces &&
                hasValidRepeats && hasValidSequences;

        return new ValidationResult(isValid, errors, lengthValid, hasUppercase,
                hasLowercase, hasDigit, hasSpecialChar,
                hasNoSpaces, hasValidRepeats, hasValidSequences);
    }

    private static boolean isSameCategorySequence(String sequence) {
        // Проверяем, что все 4 символа принадлежат к одной категории
        return (isAllLetters(sequence) || isAllDigits(sequence) || isAllSpecial(sequence));
    }

    private static boolean isAllLetters(String str) {
        return Pattern.compile("^[A-Za-zА-Яа-я]{4}$").matcher(str).matches();
    }

    private static boolean isAllDigits(String str) {
        return Pattern.compile("^\\d{4}$").matcher(str).matches();
    }

    private static boolean isAllSpecial(String str) {
        return Pattern.compile("^[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]{4}$").matcher(str).matches();
    }
}