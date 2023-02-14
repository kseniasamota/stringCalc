import java.io.IOException;
import java.lang.invoke.StringConcatFactory;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        System.out.println(calc(input));
    }

    public static String calc(String input) throws IOException {
        Converter converter = new Converter();
        String answer = new String();
        char[] charInput = input.toCharArray();
        int firstNumber;
        int secondNumber;
        Character currentSign = ' ';
        int isCorrectSign = 0;
        String[] component = input.split(" ");

        if(component.length > 3 || component.length <= 1)
            throw new IOException("Cтрока не является математической операцией");



        for(int i = 0; i < charInput.length; i++){
            if(charInput[i] == '+' || charInput[i] == '-' || charInput[i] == '*' || charInput[i] == '/'){
                currentSign = charInput[i];
                isCorrectSign++;
            }
        }

        if(isCorrectSign != 1){
            throw new IOException("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)!");
        }

        if(currentSign == '+'){
            component = input.split(" \\+ ");
        } else if (currentSign == '-') {
            component = input.split(" - ");
        } else if (currentSign == '*') {
            component = input.split(" \\* ");
        } else if (currentSign == '/') {
            component = input.split(" / ");
        }


        if(converter.isRoman(component[0]) && converter.isRoman(component[1])){
            firstNumber = Integer.parseInt(component[0]);
            secondNumber = Integer.parseInt(component[1]);

            if(inArrange(firstNumber) && inArrange(secondNumber)){
                switch (currentSign) {
                    case ('+') -> {
                        return Integer.toString(firstNumber + secondNumber);
                    }
                    case ('-') -> {
                        return Integer.toString(firstNumber - secondNumber);
                    }
                    case ('*') -> {
                        return Integer.toString(firstNumber * secondNumber);
                    }
                    case ('/') -> {
                        try {
                            return Integer.toString(firstNumber / secondNumber);
                        } catch (ArithmeticException | InputMismatchException e) {
                            System.err.println("Exception : " + e);
                            System.err.println("Результатом операции могут быть только целые числа!");
                        }
                    }
                    default -> throw new IllegalArgumentException("Неверный знак операции");
                }
            } else {
                throw new IOException("Числа не находятся в диапазоне от 1 до 10!");
            }
        } else {
            throw new IOException("используются одновременно разные системы счисления");
        }

        return answer;
    }

    public static boolean inArrange(int currentNumber){
        if(currentNumber >= 1 && currentNumber <= 10)
            return true;
        else
            return false;
    }
}

class Converter{
    private HashMap<Character, Integer> romanMap = new HashMap<>();

    public Converter(){
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);
    }

    public boolean isRoman(String number){
        if(romanMap.containsKey(number.charAt(0))){
            return false;
        } else {
            return true;
        }
    }
}