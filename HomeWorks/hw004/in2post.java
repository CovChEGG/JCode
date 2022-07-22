import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/*
Реализовать алгоритм перевода из инфиксной записи в постфиксную для арифметического выражения.
Вычислить запись если это возможно
Написать программу вычисляющую значение сложного арифметического выражения.
Для простоты - выражение всегда вычисляемое
Пример: (2^3 * (10 / (5 - 3)))^(Sin(Pi)) ответ - 1
*/

public class in2post {
    public static void main(String[] args) {

        //String exp = "(2^3 * (10 / (5 - 3)))^(Sin(Pi))";
        String exp = "2+3";
        exp = exp.trim().replace(" ", "").toLowerCase();
        in2po(exp);
    }

    public static void in2po(String exp) {
        var pstFixOp = new HashSet<String>(Arrays.asList("!"));
        var preFixOp = new HashSet<String>(Arrays.asList("sin", "cos", "tg", "ln"));
        var brackets = new HashMap<Character, Character>(){{
            put('(', ')');
        // put("<", ">");
        // put("[", "]");
        // put("{", "}");
        }};
        var biOp = new HashMap<Character, Integer>(){{
            put('^', 2);
            put('*', 3);
            put('/', 3);
            put('+', 4);
            put('-', 4);
        }};
        ArrayList<StringBuilder> arExp = toArrayExp(exp);
        System.out.println(arExp);
        // /*0 
        ArrayDeque<StringBuilder> stackOp = new ArrayDeque<>();
        ArrayDeque<StringBuilder> queueOut = new ArrayDeque<>();
        StringBuilder tmp = new StringBuilder();
        // int numOfBrackets = 0;
        for (StringBuilder strBuild : arExp) {
            if(brackets.containsValue(strBuild)) { // проверка наличия закрывающейся скобки
                System.out.println(strBuild);
                while(!stackOp.getLast().equals(strBuild)) { // выгрузка в очередь из стека до открытой скобки
                    if(!stackOp.isEmpty()){
                        System.out.println("Не согласованные скобки!!!");
                        System.exit(0); // завершение программы из-за несоответствия скобок
                    } else {
                        queueOut.addLast(stackOp.peekLast()); // выгрузка из стека в очередь
                        System.out.println("выгрузка из стека в очередь" + queueOut.getLast());
                    }
                }
                stackOp.peekLast(); // удаление скобки из стека
                tmp = new StringBuilder();
            } else if(brackets.containsKey(strBuild)) {
                tmp.append(brackets.get(strBuild));
                System.out.println(strBuild);
                stackOp.addLast(tmp);
                tmp = new StringBuilder();
            } else if(preFixOp.contains(strBuild.toString())) {
                System.out.println(strBuild);
                stackOp.addLast(strBuild);
            } else if(biOp.containsKey(strBuild.charAt(0))) { // если бинарная операция
                System.out.println(strBuild);
                if(!stackOp.isEmpty()) {
                    // tmp.append(stackOp.getLast());
                    if(preFixOp.contains(stackOp.getLast().toString()) || // проверка стека на префиксную операцию
                        biOp.get(stackOp.getLast().charAt(0)) < // приоритет бинарных операций
                        biOp.get(strBuild.charAt(0)) 
                      ) { 
                        queueOut.addLast(stackOp.peekLast()); // добавление в очередь из стека преф/оп.
                    }
                }
                stackOp.addLast(strBuild);
            } else if((pstFixOp.contains(strBuild.toString()))) {
                queueOut.addLast(strBuild);
            } else {
                System.out.println(strBuild);
                queueOut.addLast(strBuild);
            }
        }
        while(!stackOp.isEmpty()){
            queueOut.addLast(stackOp.pop());
        }
        System.out.println(queueOut);
        // */ 
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
            if (chIsDig(curCh)) { // набор "числа"
                if (tmpSize > 0 && chIsLet(tmp.charAt(tmpSize - 1))) { 
                    arExp.add(tmp); // запись слова если есть в tmp в список
                    
                }
                tmp.append(curCh);
            } else if (chIsLet(curCh)) { // набор "слова"
                if (tmpSize > 0 && chIsDig(tmp.charAt(tmpSize - 1))) {
                    arExp.add(tmp); // запись набранного числа если есть в tmp в список
                    tmp = new StringBuilder();
                }
                tmp.append(curCh);
            } else { // для символов 
                if (tmp.length() != 0) {
                    arExp.add(tmp); // запись tmp в список если появлился символ не из числа или слова
                    tmp = new StringBuilder();
                }
                tmp.append(curCh); 
                arExp.add(tmp); // добавляем новый символ
                tmp = new StringBuilder();
            }
            tmpSize = tmp.length();
            if (index == in.length() - 1 && tmpSize > 0)
                arExp.add(tmp); // запись tmp в список если больше нет элементов
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
