import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/*
Реализовать алгоритм перевода из инфиксной записи в постфиксную для арифметического выражения.
Вычислить запись если это возможно
Написать программу вычисляющую значение сложного арифметического выражения.
Для простоты - выражение всегда вычисляемое
Пример: (2^3 * (10 / (5 - 3)))^(Sin(Pi)) ответ - 1
*/

public class in2post {
    public static void main(String[] args) {

        String exp = "(2^3 * (10 / (5 - 3)))^(Sin(Pi))";
        // String exp = "2^3";
        exp = exp.trim().replace(" ", "").toLowerCase();
        in2po(exp);

    }

    public static void in2po(String exp) {

        // ArrayDeque<StringBuilder> stackOp = new ArrayDeque<>();
        // ArrayDeque<StringBuilder> queueOut = new ArrayDeque<>();
        // var brackets = new HashMap<String, String>(){{
        // put("(", ")");
        // put("<", ">");
        // put("[", "]");
        // put("{", "}");
        // }};
        // var biOp = new HashMap<Char, Integer>(){{
        // put('^'', 1);
        // put('*', 2);
        // put('/', 2);
        // put('+', 3);
        // put('-', 3);
        // }};

        // var pstFixOp = new HashSet<String>(Arrays.asList("!"));
        // var preFixOp = new HashSet<String>(Arrays.asList("sin", "cos", "tg", "ln"));
        ArrayList<StringBuilder> arExp = toArrayExp(exp);
        System.out.println(arExp);
    }

    /**
     * @param exp - получает на вход строку с выражением
     * @return - возвращает список из элементов выражения
     */
    public static ArrayList<StringBuilder> toArrayExp(String exp) {

        ArrayList<StringBuilder> arExp = new ArrayList<>();
        StringBuilder in = new StringBuilder(exp);
        StringBuilder tmp = new StringBuilder();
        Character curCh;
        int tmpSize = tmp.length();
        for (int index = 0; index < in.length(); index++) {
            curCh = in.charAt(index);
            if (chIsDig(curCh)) { // набор числа
                if (tmpSize > 0 && chIsLet(tmp.charAt(tmpSize - 1))) {
                    arExp.add(tmp); // запись слова если есть в список
                    tmp = new StringBuilder();
                }
                tmp.append(curCh);
            } else if (chIsLet(curCh)) { // набор "слова"
                if (tmp.length() > 0 && chIsDig(tmp.charAt(tmpSize - 1))) {
                    arExp.add(tmp); // запись набранного числа если есть в список
                    tmp = new StringBuilder();
                }
                tmp.append(curCh);
            } else {
                if (tmp.length() != 0) {
                    arExp.add(tmp);
                    tmp = new StringBuilder();
                }
                tmp.append(curCh);
                arExp.add(tmp);
                tmp = new StringBuilder();
            }
            System.out.println(tmp);
            if (index == in.length() - 1 && tmpSize > 0)
                arExp.add(tmp);
            else
                tmpSize = tmp.length();
        }
        return arExp;
    }

    public static boolean chIsDig(Character ch) {
        if (Character.isDigit(ch) || ch == '.')
            return true;
        return false;
    }

    public static boolean chIsLet(Character ch) {
        if (ch >= 'a' && ch <= 'z')
            return true;
        return false;
    }

}
