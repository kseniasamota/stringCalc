import java.lang.invoke.StringConcatFactory;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        System.out.println(calc(input));
    }

    public static String calc(String input){
        String answer = new String();
        char[] charInput = input.toCharArray();
        int firstNumber;
        int secondNumber;
        Character currentSign = ' ';

        for(int i = 0; i < charInput.length; i++){
            if(charInput[i] == '+' || charInput[i] == '-' || charInput[i] == '*' || charInput[i] == '/'){
                currentSign = charInput[i];
            }
        }

        String[] component = input.split(" - ");

        firstNumber = Integer.parseInt(component[0]);
        secondNumber= Integer.parseInt(component[1]);

        answer = Integer.toString(firstNumber - secondNumber);



        return answer;
    }
}