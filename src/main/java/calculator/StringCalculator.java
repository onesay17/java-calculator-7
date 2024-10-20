package calculator;

import java.util.Arrays;

public class StringCalculator {

    public int add(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        String[] numbers = splitNumbers(input);
        return sumNumbers(numbers);
    }

    private String[] splitNumbers(String input) {
        String delimiter = ",|:";
        if (input.startsWith("//")) {
            int delimiterIndex = input.indexOf("\\n");
            if (delimiterIndex == -1) {
                throw new IllegalArgumentException("Invalid input");
            }
            delimiter += "|"+input.substring(2, delimiterIndex);
            input = input.substring(delimiterIndex + 2);
        }
        System.out.println("delimiter: " + delimiter + "end");
        return input.split(delimiter);
    }

    private int sumNumbers(String[] numbers) {

        return Arrays.stream(numbers)
                .filter(num -> !num.isEmpty())
                .map(String::trim)
                .mapToInt(this::parseNumber)
                .sum();
    }

    private int parseNumber(String number) {
        int num = Integer.parseInt(number);
        if (num < 0) {
            throw new IllegalArgumentException("Negative numbers are not allowed: " + number);
        }
        return num;
    }
}