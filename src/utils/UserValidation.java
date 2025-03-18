package utils;

public class UserValidation {

    public static boolean isEmailValid(String email) {
        // 1. Должна быть 1 @ и только одна
        int indexAt = email.indexOf('@');
        int lastAt = email.lastIndexOf('@');
        if (indexAt == -1 || indexAt != lastAt) return false;

        // 2. Должна быть точка после собаки
        int dotIndexAfterAt = email.indexOf('.', indexAt + 1);
        if (dotIndexAfterAt == -1) return false;

        // 3. после последней точки есть 2 или более символов
        int lastDotIndex = email.lastIndexOf('.');
        if (lastDotIndex >= email.length() - 2) return false;

        // 4. Допустимые символы: алфавит, цифры, '-', '_', '@', '.'
        /*
        Проверяю все символы в строке. Проверяю текущий символ.
        Если нахожу хоть один неправильный сразу возвращаю false.
         */
        // String.toCharArray() - который возвращает массив чаров -> char[]

        for (char ch : email.toCharArray()) {

            // проверка символов на правильность
            boolean isPass = Character.isAlphabetic(ch)
                    || Character.isDigit(ch)
                    || ch == '-'
                    || ch == '_'
                    || ch == '.'
                    || ch == '@';
            // Если символ не подХодит (isPass = false) возвращаю false
            if (!isPass) return false; // делай что-то, если переменная false
        }

        // 5. До собаки должен быть хотя бы 1 символ. То есть индекс собаки не равен 0
        if (indexAt == 0) return false;
        // 6. 1 символ должна быть буква. Символ с индексом 0 должна быть буква
        if (!Character.isLetter(email.charAt(0))) return false;

        // Все проверки пройдены, email подходит
        return true;
    }

    public static boolean isPasswordValid(String password) {
        boolean isEight = false;
        boolean hasLower = false;
        boolean hasUpper = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char ch : password.toCharArray()) {
            if (Character.isDigit(ch)) hasDigit = true;
            if (Character.isLowerCase(ch)) hasLower = true;
            if (Character.isUpperCase(ch)) hasUpper = true;
            if (password.length() >= 8) isEight = true;
            if( ch == '!' || ch == '@' || ch == '?' || ch == '№'
                    || ch == '%' || ch == '*' || ch == '(' || ch == ')'
                    || ch == '[' || ch == ']' || ch == ','
                    || ch == '.' || ch == '-') hasSpecial = true;
        }
        if (isEight && hasDigit && hasLower && hasUpper && hasSpecial) return true;
        return false;
    }

}
