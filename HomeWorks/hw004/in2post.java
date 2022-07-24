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
        var preFixOp = new HashSet<String>(Arrays.asList(
                "sin", "cos", "tan", "asin", "acos", "atan",
                "sqrt", "exp"));
        var constants = new HashMap<String, Double>() {
            {
                put("pi", Math.PI);
                put("e", Math.E);
            }
        };
        var brackets = new HashMap<Character, Character>() {
            {
                put('(', ')');
                put('<', '>');
                put('[', ']');
                put('{', '}');
            }
        };
        var biOp = new HashMap<String, Integer>() {
            {
                put("^", 2);
                put("*", 3);
                put("/", 3);
                put("+", 4);
                put("-", 4);
            }
        };
        ArrayList<StringBuilder> arExp = toArrayExp(exp);
        System.out.println(arExp);
        ArrayDeque<StringBuilder> stackOp = new ArrayDeque<>();
        ArrayDeque<StringBuilder> queueOut = new ArrayDeque<>();
        for (StringBuilder strBuild : arExp) {
            if (brackets.containsValue(strBuild.charAt(0))) { // проверка наличия закрывающейся скобки
                while (true) {
                    if (stackOp.isEmpty()) { // если стэк пустой и скобки не нашлось
                        System.out.println("Не согласованные скобки!!!");
                        // завершение программы из-за несоответствия скобок
                        System.exit(0);
                        // иначе найдена ли какая-нибудь закрывающая скобка в стеке?
                    } else if (brackets.containsKey(stackOp.peekLast().charAt(0))) {
                        // соответствующая ли скобка?
                        if (brackets.get(stackOp.peekLast().charAt(0)) == strBuild.charAt(0)) {
                            // удаление отрывающейся - соответсвтующей входящей - закрывающейся скобке из
                            // стека
                            stackOp.pollLast();
                            break;
                        }
                    }
                    queueOut.addLast(stackOp.pollLast());
                }
            } else if (brackets.containsKey(strBuild.charAt(0))) {
                stackOp.addLast(strBuild);
            } else if (preFixOp.contains(strBuild.toString())) {
                stackOp.addLast(strBuild);
            } else if (biOp.containsKey(strBuild.toString())) { // если бинарная операция
                while (!stackOp.isEmpty() && (preFixOp.contains(stackOp.peekLast().toString()) || // проверка стека на
                                                                                                  // префиксную операцию
                        biOp.containsKey(stackOp.peekLast().toString()) &&
                                (biOp.get(stackOp.peekLast().toString()) <= biOp.get(strBuild.toString())))) {
                    queueOut.addLast(stackOp.pollLast()); // добавление в очередь из стека преф/оп.
                }
                stackOp.addLast(strBuild);
            } else if ((pstFixOp.contains(strBuild.toString())) || constants.containsKey(strBuild)) {
                queueOut.addLast(strBuild); // добавление констант и постфиксных операторов в очередь
            } else {
                queueOut.addLast(strBuild);
            }
        }
        while (!stackOp.isEmpty()) {
            queueOut.addLast(stackOp.pollLast());
        }
        System.out.println("Выражение в ОПН: " + queueOut); // Получение записи в ОПН

        // вычисление выражения из полученной очереди
        ArrayDeque<Double> stack = new ArrayDeque<>();
        while (!queueOut.isEmpty()) {
            if (chIsDig((Character) queueOut.peekFirst().charAt(0))) {
                stack.addLast(toDoub(queueOut.pollFirst().toString()));
            } else if (constants.containsKey(queueOut.peekFirst().toString())) {
                stack.addLast(constants.get(queueOut.pollFirst().toString()));
            } else if (preFixOp.contains(queueOut.peekFirst().toString()) ||
                    pstFixOp.contains(queueOut.peekFirst().toString())) {
                if (!stack.isEmpty())
                    stack.addLast(calc(queueOut.pollFirst().toString(), stack.pollLast(), 0D));
                else {
                    stop("Отсутсвтует операнд для операци: " + queueOut.peekFirst().toString());
                }
            } else if (biOp.containsKey(queueOut.peekFirst().toString())) {
                Double a = 0D,
                        b = 0D;
                if (!stack.isEmpty())
                    b = stack.pollLast();
                else {
                    stop("Отсутсвтуют операнды для операци: " + "? " + queueOut.peekFirst().toString() + " ?");
                }
                if (!stack.isEmpty())
                    a = stack.pollLast();
                else {
                    stop("Отсутсвтуют операнд для операци: " + "? " + queueOut.peekFirst().toString() + " " + b);
                }
                stack.addLast(calc(queueOut.pollFirst().toString(), a, b));
            }
        }
        System.out.println(stack);
    }

    public static void stop(String text) {
        System.out.println(text);
        System.exit(0);
    }

    public static Double calc(String op, Double a, Double b) {
        switch (op) {
            case "sin":
                return Math.sin(a);
            case "cos":
                return Math.cos(a);
            case "tan":
                return Math.tan(a);
            case "asin":
                return Math.asin(a);
            case "acos":
                return Math.acos(a);
            case "atan":
                return Math.atan(a);
            case "sqrt":
                return Math.sqrt(a);
            case "exp":
                return Math.exp(a);
            case "^":
                return Math.pow(a, b);
            case "*":
                return a * b;
            case "/":
                if (b != 0)
                    return a / b;
                else
                    stop("Деление " + a + " на " + b);
            case "+":
                return a + b;
            case "-":
                return a - b;
            default:
                System.out.println("Не найдена операция: " + op);
        }
        ;
        return null;
    }

    public static Double toDoub(String tobeDouble) throws NumberFormatException {
        try {
            return Double.parseDouble(tobeDouble);
        } catch (NumberFormatException e) {
            System.out.println(tobeDouble + "Не является числом!!! Проверьте формулу");
            System.exit(0);
        }
        return null;
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
