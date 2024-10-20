package calculator;

import camp.nextstep.edu.missionutils.Console;

public class Application {

    public static void main(String[] args) {
        String input = "";
        try {
            input = Console.readLine();
        } catch (java.util.NoSuchElementException e) {
            input = "";
        }
        StringCalculator stringCalculator = new StringCalculator();
        int result = stringCalculator.add(input);
        System.out.println("결과 : " + result);
    }
}
