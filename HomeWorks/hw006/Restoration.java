import java.util.HashMap;

public class Restoration {
    final int argsNum = 3;
    final int arg0 = 0;
    final int arg1 = 1;
    final int arg2 = 2;
    
    private String sExp;
    private StringBuilder[] sbArgsArray;
    private int[] lenOfArgs = new int[argsNum];
    private int questionsNum = 0;
    // private int[][] argsQs;
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
    // final HashMap<Integer, Character> ch2int = new HashMap<Character, Integer>() {
    //     {
    //         put('1', 1);
    //         put('2', 2);
    //         put('3', 3);
    //         put('4', 4);
    //         put('5', 5);
    //         put('6', 6);
    //         put('7', 7);
    //         put('8', 8);
    //         put('9', 9);
    //         put('0', 0);
    //         put('?', -1);
    //     }};



    public Restoration(String s) {
        sExp = s;
        sExp2sbAraryParsing();
    }

    

    private void sExp2sbAraryParsing() {
        String tmp = sExp.trim().replace(" ", "");
        sbArgsArray = new StringBuilder[argsNum];
        StringBuilder tmpSB = new StringBuilder();
        int j = 0;
        for (int i = 0; i < tmp.length(); i++) {
            if (tmp.charAt(i) == '+' || tmp.charAt(i) == '=') {
                sbArgsArray[j] = tmpSB;
                j++;
                tmpSB = new StringBuilder();
                continue;
            }
            tmpSB.append(tmp.charAt(i));
        }
        sbArgsArray[j] = tmpSB;
    }

    public String restRes() {
        String result = "";
        int maxLen = calcLengthOfArgs(); // читаем длины и находим больший аргумент
        int[] tmpArgs = new int[argsNum]; // значения аргументов для тек. разряда
        int[] sumArgs = new int[argsNum]; // суммы элементов аргументов
        for(int razr = 0; razr <= maxLen; razr++) {
            parse2tmpArgs(razr, maxLen, tmpArgs);
            if(findVar(tmpArgs, arg0)) {
                summation(sumArgs, tmpArgs, razr);
            }
            else {
                System.out.println("Решений не найдено!");
                System.exit(0);
            }
            if(calcQuestions(tmpArgs)) {
                repairingArgsArray(tmpArgs, razr);
            }          
        }
        System.out.println(sbArgsArray);
        return result;
    }

    

    private void summation(int[] sumArgs, int[] tmpArgs, int razr) {
        for(int i = 0 ; i < argsNum; i++){
            sumArgs[i] += tmpArgs[i]*(int)Math.pow(10, razr);
        }
    }

    private void repairingArgsArray(int[] tmpArgs, int razr){
        int curLen = 0;
        for(int i = 0 ; i < argsNum; i++){
            curLen = sbArgsArray[i].length();
            if(curLen <= razr) {
                sbArgsArray[i].setCharAt(curLen - razr, (char)(tmpArgs[i] + '0'));
            }
        }
    }



    /** Рекурсивный поиск варианта выражения в текущем разряде
     * @param tmpArgs - массив значений в текущем разряде
     * @param argX - текущий аргумент
     * @return - возвращает true, если найдено хоть одно решение
     */
    private boolean findVar(int[] tmpArgs, int argX){
        if(tmpArgs[arg0] != -1 && tmpArgs[arg1] != -1 && tmpArgs[arg2] != -1) {
            if(tmpArgs[arg0] + tmpArgs[arg1] == tmpArgs[arg2]) return true;
            else return false;
        }
        if(tmpArgs[argX] == -1) {
            for(int i = 0; i < 10; i++){
                tmpArgs[argX] = i;
                if(findVar(tmpArgs, ++argX)) return true;
            }
        } else if(findVar(tmpArgs, ++argX)) return true;
        return false;
    }


    private boolean calcQuestions(int[] tmpArgs) {
        int count = 0;
        for(int i = 0; i < argsNum; i++) { // подсчёт ? в тек. разряде
            if(tmpArgs[i] == -1) count++;
        }
        if(count > questionsNum) questionsNum = count; // запись в глоб.
        if(count > 0) return true;
        return false;
    }



    /** заполняем темповый int массив аргументов на теущем разряде
     * @param razr - текущий разряд аргументов
     * @param maxLen - длина аргумента максимальной длины
     * @param tmpArgs - ссылка на темповый int массив
     */
    private void parse2tmpArgs(int razr, int maxLen, int[] tmpArgs) {
        Character tmp;
        for(int i = 0 ; i < argsNum ; i++) {
            if(lenOfArgs[i] > 0) {
                tmp = sbArgsArray[arg0].charAt(lenOfArgs[i] - razr);
                if(ch2int.containsKey(tmp))
                    tmpArgs[i] = sbArgsArray[i].charAt(lenOfArgs[i] - razr);
                else   
                    System.out.println("Неправильный символ в выражении: " + tmp);
                    System.exit(0);
            } else {
                tmp = '0';
            }
            tmpArgs[i] = ch2int.get(tmp);
        }
    } 

    private int calcLengthOfArgs(){
        int tmp = 0;
        for(int i = 0 ; i < argsNum; i++){
            lenOfArgs[i] = sbArgsArray[i].length();
            if(tmp < lenOfArgs[i]) tmp = lenOfArgs[i];
        }
        return tmp; // возвращаем размер аргумента максимальной длины
    }
}
