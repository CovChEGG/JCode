import java.util.HashMap;

public class Restoration {
    final int argsNum = 3;
    final int arg0 = 0;
    final int arg1 = 1;
    final int arg2 = 2;
    final HashMap<Character, Integer> ch2int = new HashMap<Character, Integer>() {
        {
            put('1', 1);
            put('2', 2);
            put('3', 3);
            put('4', 4);
            put('5', 5);
            put('6', 6);
            put('7', 7);
            put('8', 8);
            put('9', 9);
            put('0', 0);
            put('?', -1);
        }};
    private String sExp;
    private StringBuilder[] sbArgsArray;
    private int[] lenOfArgs = new int[argsNum]; 
    private int maxLen = 0;
    private int questionsNum = 0;
    private int fromPreviousRazr = 0;

    public Restoration(String s) {
        sExp = s;
        sExp2sbAraryParsing();
        restRes();
    }    

    /** Переводит выражение из строки в массив StringBuilder,
     *  заполняет массив длин элементов lenOfArgs
     *  и находит длину самого длинного элемента maxLen.
     */
    private void sExp2sbAraryParsing() {
        String tmp = sExp.trim().replace(" ", "");
        sbArgsArray = new StringBuilder[argsNum];
        StringBuilder tmpSB = new StringBuilder();
        int argN = 0;
        for (int i = 0; i <= tmp.length(); i++) {
            if (i == tmp.length() || tmp.charAt(i) == '+' || tmp.charAt(i) == '=') {
                sbArgsArray[argN] = tmpSB;
                lenOfArgs[argN] = tmpSB.length();
                if(lenOfArgs[argN] > maxLen) maxLen = lenOfArgs[argN]; 
                argN++;
                tmpSB = new StringBuilder();
                continue;
            }
            tmpSB.append(tmp.charAt(i));
        }
    }

    private void restRes() {
        int[] tmpArgs; // значения аргументов для тек. разряда
        for(int razr = 0; razr < maxLen; razr++) {
            tmpArgs = new int[argsNum];
            parse2tmpArgs(tmpArgs, razr); // получение значений аргументов в разряде razr
            calcQuestionsInRazr(tmpArgs); // поиск количества '?' в текущем разряде
            if(findVar(tmpArgs, arg0)) {
                repairingArgsArray(tmpArgs, razr);
            }
            else {
                System.out.println("Не найдено вариантов в " + (razr+1) + "разряде");
                questionsNum = 4;
            }
        }
        // проверка на "остаток" для отсутствующего вышестоящего разряда.
        if(fromPreviousRazr != 0) questionsNum = 4;
    }

    /** заполняем темповый int массив аргументов на теущем разряде
     * @param razr - текущий разряд аргументов
     * @param tmpArgs - ссылка на темповый int массив
     */
    private void parse2tmpArgs(int[] tmpArgs, int razr) {
        Character tmp;
        int curIndex;
        for(int i = 0 ; i < argsNum ; i++) {
            curIndex = lenOfArgs[i] - 1 - razr;
            if(curIndex >= 0) {
                tmp = sbArgsArray[i].charAt(curIndex);
                if(ch2int.containsKey(tmp)) tmpArgs[i] = tmp;
                else {
                    System.out.println("Неправильный символ в выражении: " + tmp);
                    System.exit(0);
                }
            }
            else {
                    tmp = '0';
            }
            tmpArgs[i] = ch2int.get(tmp);
        }
    }

    /** Рекурсивный поиск варианта выражения в текущем разряде
     * @param tmpArgs - массив значений в текущем разряде
     * @param argN - текущий аргумент
     * @return - возвращает true, если найдено хоть одно решение
     */
    private boolean findVar(int[] tmpArgs, int argN){
        if(argN >= argsNum ) {
            int sum = tmpArgs[arg0] + tmpArgs[arg1];
            int arg2tmp = tmpArgs[arg2] - fromPreviousRazr;
            if(sum % 10 == arg2tmp) {
                    fromPreviousRazr = sum / 10; // запоминание числа переходящего в следующий разряд
                    return true;
                }
            else return false;
        } else if(tmpArgs[argN] == -1) {
            for(int i = 0; i < 10; i++){
                tmpArgs[argN] = i;
                if(!findVar(tmpArgs, ++argN)) { 
                    argN--;
                    continue; 
                }
                else return true;
            }
        } else if(findVar(tmpArgs, ++argN)) return true;
        return false;
    }
   
    private void repairingArgsArray(int[] tmpArgs, int razr){
        int curIndex;
        for(int i = 0 ; i < argsNum; i++){
            curIndex = lenOfArgs[i] - 1 - razr;
            if(curIndex >= 0) {
                if(sbArgsArray[i].charAt(curIndex) == '?') {
                    sbArgsArray[i].setCharAt(curIndex, (char)(tmpArgs[i] + '0'));
                }
            }
        }
    }

    /**Подсчитывает количество знаков "?" (int -1) в текущем разряде. 
     * Сохраняет максимально встречающееся количество в глобальной 
     * перменной questionsNum для формулировки описания.
     * @param tmpArgs принимает числа из одного разряда аргументов
     */
    private void calcQuestionsInRazr(int[] tmpArgs) {
        int count = 0;
        for(int i = 0; i < argsNum; i++) { // подсчёт "?" в тек. разряде
            if(tmpArgs[i] == -1) count++;
        }
        if(count > questionsNum) questionsNum = count; // запись в глоб.
    }

    public String GetRes() {
        System.out.print("\033[H\033[J");
        System.out.printf("Для выражения: %s ", sExp);
        switch (questionsNum) {
            case 4:
                System.out.println("решений нет!");
                System.exit(0);
                break;
            case 3:
                System.out.println("cуществует множество решений!\nПервое возможное из них:\n");
                break;
            case 2:
                System.out.println("cуществуют несколько решений!\nПервое из них:\n");
                break;
            case 1:
                System.out.println("существует только одно решение:\n");
                break;
            case 0:
                System.out.println("востановление не требуется.");
                System.exit(0);
                break;
            default:
                System.out.println("Что то пошло не так... попробуйте в жизни что-нибудь поменять!");
                break;
        }
        return sbArgsArray[arg0].toString()+"+"+sbArgsArray[arg1].toString()+"="+sbArgsArray[arg2].toString();
    }  
}
