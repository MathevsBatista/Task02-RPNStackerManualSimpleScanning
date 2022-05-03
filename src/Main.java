import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import stacker.rpn.lexer.Token;
import stacker.rpn.lexer.TokenType;

public class Main {

    private static boolean isInteger(String str) {
        return str != null && str.matches("[0-9]*");
    }

    public static void main(String[] args) throws Exception {
        Stack<String> stack = new Stack<String>();
        List<Token> tokens = new ArrayList<>();
        String dir = System.getProperty("user.dir");
        // Caso esteja usando outro arquivo, apenas alterar o nome dele e colocar ele no mesmo diretório deste código
        Scanner texto = new Scanner(new FileReader(dir + "/src/Calc1.stk"));
        
        while(texto.hasNextLine()){
            String current = texto.nextLine();

            if (isInteger(current)) {
                Token token = new Token(TokenType.NUM, current);
                tokens.add(token);
            } else if (current.equals("+")) {
                Token token = new Token(TokenType.PLUS, current);
                tokens.add(token);
            } else if (current.equals("-")) {
                Token token = new Token(TokenType.MINUS, current);
                tokens.add(token);
            } else if (current.equals("/")) {
                Token token = new Token(TokenType.SLASH, current);
                tokens.add(token);
            } else if (current.equals("*")) {
                Token token = new Token(TokenType.STAR, current);
                tokens.add(token);
            } else {
                throw new Exception();
            }
        }


        while(!tokens.isEmpty()){
            String current = tokens.remove(0).lexeme;
            switch(current){
                case "+":
                case "-":
                case "/":
                case "*":
                    int n1, n2;
                    n1 = Integer.parseInt(stack.pop());
                    n2 = Integer.parseInt(stack.pop());
                    switch(current){
                        case "+":
                            stack.push(Integer.toString(n2 + n1));
                        break;
                        case "-":
                            stack.push(Integer.toString(n2 - n1));
                        break;
                        case "/":
                            stack.push(Integer.toString(n2 / n1));
                        break;
                        case "*":
                            stack.push(Integer.toString(n2 * n1));
                        break;
                    }
                break;
                default:
                    stack.push(current);
                break;
            }
        }

        System.out.println(stack.pop());
    }
}
