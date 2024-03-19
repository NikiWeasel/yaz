import java.util.ArrayList;
import java.util.Objects;

public class Main {

    //isDigitsOnly позволяет убедиться есть ли только числа.
    static boolean isDigitsOnly(String e){//CONST
        return "0123456789".contains(e);
    }
    //isLettersOnly позволяет убедиться есть ли только буквы.
    static boolean isLettersOnly(String e){//IDENTIFIER
        e = e.toLowerCase();
        return "abcdefghijklmnopqrstuvwxyz".contains(e);
    }
    //isOperator позволяет убедиться есть ли только буквы.
    static boolean isOperator(String e){//OPERATOR
        return "+=/*-".contains(e);
    }
    //isKeyword позволяет убедиться есть ли только буквы.
    static boolean isKeyword(String e){//KEYWORD
        return "Var ".contains(e);
    }
    //isKeyword позволяет убедиться есть ли только буквы.
    static boolean isChar(String e){//CHAR
        return "()[].,;".contains(e);
    }

    public static void main(String[] args) {

        //Добавление алфавита в виде массива строк.
        String[] alphabet = {"Var","+","-","*","/","(",")", ",", ";", "="};

        //Строки для проверок.
//        String a = "Var a, b   , c; [a = -b7; c=a-b;]";
//        String a = "Var a, b   , c; [a = -b7; c==a/b;]";
//        String a = "Var a, b, c; [a = 7; c=-a+b;]";
//        String a = "Var a     , b   , c; [a = b7; c=a+b;]";
        String a = "VAR a         , b   , c; [a = b7; c=a*b;]";




        System.out.println(a);

        //Добавление пробелов, чтобы можно было просто разделить.
        a=a.replace(",", " , ");
        a=a.replace(";", " ; ");
        a=a.replace("]", " ] ");
        a=a.replace("[", " [ ");
        a=a.replace("*", " * ");
        a=a.replace("/", " / ");
        a=a.replace("=", " = ");
        a=a.replace("+", " + ");
        a=a.replace("-", " - ");

        //Удаление лишних пробелов, чтобы при разделении не было лишних элементов.
        a=a.replaceAll("\\s+", " ");

        //Разделение по пробелам
        String[] array = a.split(" ");

        System.out.println(a);
//        a.split(",");


        //Определение лексем
        for (int i = 0; i < array.length; i++) {
            String lex = null;

            if (isDigitsOnly(array[i])) {
                lex = "CONSTANT";
            } else if (isLettersOnly(array[i])) {
                lex = "IDENTIFIER";
            } else if (isChar(array[i])) {
                lex = "CHARACTER";
            } else if (isKeyword(array[i])) {
                lex = "KEYWORD";
            } else if (isOperator(array[i])) {
                if (Objects.equals(array[i], "-")){
                    if (Objects.equals(array[i - 1], "(") || Objects.equals(array[i - 1], "=")){
                        lex = "UNARY OPERATOR";
                    }else{
                        lex = "BINARY OPERATOR";
                    }
                }
                else{
                    lex = "OPERATOR";
                }

            } else if(array[i].matches("[a-z][0-9]")){
                    lex = "OPERAND";

            }else{
                for (String value : alphabet) {
                    if (value.contains(array[i])) {
                        lex = array[i];
                        break;
                    }
                }
//                if(!s.matches("[0-9][a-z]")){
//                    if (lex == null) {
//                        lex = "OPERAND";
//                    }
//                }

            }

            System.out.println(array[i] + "\t" + lex);
        }


    }
}