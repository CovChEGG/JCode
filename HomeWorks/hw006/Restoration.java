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
    
    private String sExp;
    private StringBuilder[] sbArgsArray;
    private int[] lenOfArgs = new int[argsNum]; 
    private int maxLen = 0;
    private int questionsNum = 0;
    private int fromPreviousRazr = 0;
    // private int[][] argsQs;
    
    



    public Restoration(String s) {
        sExp = s;
        sExp2sbAraryParsing();
        for (int i=0; i < argsNum; i++) {
            System.out.println(sbArgsArray[i].toString());
        }
        // System.out.println(maxLen);
        // for (int i : lenOfArgs) System.out.println(i);
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

    public String restRes() {
        String result = "";
        int[] tmpArgs; // значения аргументов для тек. разряда
        int[] sumArgs; // суммы элементов аргументов
        
        for(int razr = 0; razr < maxLen; razr++) {
            tmpArgs = new int[argsNum];
            sumArgs = new int[argsNum];

            // for (int i : tmpArgs) System.out.println(i);

            parse2tmpArgs(razr, tmpArgs);

            System.out.println(17%10 + " " + 7%10 + " " + 17/10 + " " + 7/10);
            for (int i : tmpArgs) System.out.println(i);

            if(findVar(tmpArgs, arg0)) {
                
                for (int i : tmpArgs) System.out.println(i);

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
     * @param argN - текущий аргумент
     * @return - возвращает true, если найдено хоть одно решение
     */
    private boolean findVar(int[] tmpArgs, int argN){
        if(argN >= argsNum ) {
            int sum = tmpArgs[arg0] + tmpArgs[arg1];
            int arg2tmp = tmpArgs[arg2] + fromPreviousRazr;
            if(sum % 10 == arg2tmp) {
                    fromPreviousRazr = sum / 10;
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



    //     if(tmpArgs[arg0] != -1 && tmpArgs[arg1] != -1 && tmpArgs[arg2] != -1) {
    //         if(tmpArgs[arg0] + tmpArgs[arg1] == tmpArgs[arg2]) return true;
    //         else return false;
    //     }
    //     if(tmpArgs[argN] == -1) {
    //         for(int i = 0; i < 10; i++){
    //             tmpArgs[argN] = i;
    //             if(argN < argsNum) argN++;
    //             if(!findVar(tmpArgs, argN)) continue;
    //             else return true;
    //         }
    //     } else if(findVar(tmpArgs, ++argN)) return true;
    //     return false;
    // }


    private boolean calcQuestions(int[] tmpArgs) {
        int count = 0;
        for(int i = 0; i < argsNum; i++) { // подсчёт "?" в тек. разряде
            if(tmpArgs[i] == -1) count++;
        }
        if(count > questionsNum) questionsNum = count; // запись в глоб.
        if(count > 0) return true;
        return false;
    }



    /** заполняем темповый int массив аргументов на теущем разряде
     * @param razr - текущий разряд аргументов
     * @param tmpArgs - ссылка на темповый int массив
     */
    private void parse2tmpArgs(int razr, int[] tmpArgs) {
        Character tmp;
        int curIndex;
        for(int i = 0 ; i < argsNum ; i++) {
            curIndex = lenOfArgs[i] - 1 - razr;

            // System.out.println(curIndex);
            
            if(curIndex >= 0) {
                tmp = sbArgsArray[i].charAt(curIndex);
                
                // System.out.println(tmp);
                
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
            
            // System.out.println(tmpArgs[i]);
            

        }
    } 

}
