package exercises;

import java.util.Scanner;
import java.util.Stack;

public class PostFixExpressions {

    static Scanner sc = new Scanner(System.in);
    static String operators = "+-*/";
    static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args){

        while (true){
            String input = sc.nextLine();
            System.out.println(input);
            if (input != null) {

                if (operators.contains(input)){
                    doMath(input);
                }else if (input.equals(".")) {
                    System.out.println(stack.pop());
                } else if (input.equals("print")){
                    printStack(stack3);
                }
                else {
                    addtoStack(input);
                }
            }


        }
    }

    private static void doMath(String input) {

        if (input.equals("+"))
            stack.push(stack.pop()+stack.pop());
        else if (input.equals("-"))
            stack.push(stack.pop()-stack.pop());
        else if (input.equals("*"))
            stack.push(stack.pop()*stack.pop());
        else
            stack.push(stack.pop()/stack.pop());
        System.out.println("MaAth");
    }

    private static void addtoStack(String input) {

        stack.push(Integer.parseInt(input));
    }

    private static void printStack(Stack<Integer> sc){
        for (Integer i:
             sc) {
            System.out.print(i.toString() + " ");
        }
        if (sc.size() == 0){
            System.out.println("stack is empty FFS BITCH!!!" );
        }
    }
}
