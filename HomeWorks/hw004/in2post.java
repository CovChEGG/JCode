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

        String exp = "(2^3 * (10 / (5 - 3)))^(Sin(Pi))";
        // String exp = "3 + 4 * 2 / (1 - 5)^2";
        exp = exp.trim().replace(" ", "").toLowerCase();
        in2po(exp);
    }

    public static void in2po(String exp) {
        var pstFixOp = new HashSet<String>(Arrays.asList("!"));
        var constants = new HashSet<String>(Arrays.asList("pi"));
        var preFixOp = new HashSet<String>(Arrays.asList("sin", "cos", "tg", "ln"));
        var brackets = new HashMap<Character, Character>(){{
            put('(', ')');
            put('<', '>');
            put('[', ']');
            put('{', '}');
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
        ArrayDeque<StringBuilder> stackOp = new ArrayDeque<>();
        ArrayDeque<StringBuilder> queueOut = new ArrayDeque<>();
        for (StringBuilder strBuild : arExp) {
            if(brackets.containsValue(strBuild.charAt(0))) { // проверка наличия закрывающейся скобки
                while(true) {
                    if(stackOp.isEmpty()){ // если стэк пустой и скобки не нашлось
                        System.out.println("Не согласованные скобки!!!");
                        // завершение программы из-за несоответствия скобок
                        System.exit(0); 
                        // иначе найдена ли какая-нибудь закрывающая скобка в стеке?
                    } else if(brackets.containsKey(stackOp.peekLast().charAt(0))) { 
                        // соответствующая ли скобка?
                        if(brackets.get(stackOp.peekLast().charAt(0)) == strBuild.charAt(0)) {
                            // удаление отрывающейся - соответсвтующей входящей - закрывающейся скобке из стека
                            stackOp.pollLast();
                            break;
                        }
                    }
                    queueOut.addLast(stackOp.pollLast());
                }
            } else if(brackets.containsKey(strBuild.charAt(0))) {
                stackOp.addLast(strBuild);
            } else if(preFixOp.contains(strBuild.toString())) {
                stackOp.addLast(strBuild);
            } else if(biOp.containsKey(strBuild.charAt(0))) { // если бинарная операция
                while(!stackOp.isEmpty() && (
                        preFixOp.contains(stackOp.peekLast().toString()) ||  // проверка стека на префиксную операцию
                        biOp.containsKey(stackOp.peekLast().charAt(0)) && 
                        (biOp.get(stackOp.peekLast().charAt(0)) <= biOp.get(strBuild.charAt(0))))) { 
                    queueOut.addLast(stackOp.pollLast() ); // добавление в очередь из стека преф/оп.
                }
                stackOp.addLast(strBuild);
            } else if((pstFixOp.contains(strBuild.toString())) || constants.contains(strBuild)) {
                queueOut.addLast(strBuild);
            } else {
                queueOut.addLast(strBuild);
            }
        // System.out.println("Очередь:" + queueOut);
        // System.out.println("Стек:" + stackOp);

        }
        while(!stackOp.isEmpty()){
            queueOut.addLast(stackOp.pollLast());
        }
        System.out.println("Выражение в ОПН: " + queueOut);
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
