r/*
 Задано уравнение вида q + w = e, q, w, e >= 0.
 Некоторые цифры могут быть заменены знаком вопроса, например 2? + ?5 = 69.
 Требуется восстановить выражение до верного равенства.
 Предложить хотя бы одно решение или сообщить, что его нет.
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class calcWithQestions {
    public static void main(String[] args) {
        String exp = "2?+?5=69";
        exp = exp.trim().replace(" ", "");
        System.out.print("\033[H\033[J");
        System.out.println(exp);
        List<String> ls = knwnNmbr(exp);
        System.out.println(ls);
        // List<String> ls1 = parsNmbr(ls);
        // System.out.println(ls1);

    }

    public static List<Integer> parsNmbr(List<String> str) {
        List<Integer> res = new ArrayList<>();

        // for (String item:str) {
        // res.add(Character.toString(item.charAt(item.length()-1)));
        // }
        return res;
    }



    public static List<String> knwnNmbr(String s){
        Double[] nmbrs = new Double[3];
        int[] razryad= new int[3];
        int i = s.length()-1;
        int digit = 0;
        int currentNmbr = 0;
        int currentRazr = 0;
        String tmp = "";
        Boolean rightPart = true;
        while( i >= 0 ){
            if(s.charAt(i) == '=') rightPart = false;
            
            if( i == 0 ) {
                
            } else if (s.charAt(i) == '+' || s.charAt(i) == '=') {
                
                currentNmbr++;

            }  

            if(Character.isDigit(s.charAt(i)) || s.charAt(i) == '.') {
                if(s.charAt(i) == '.') digit = 0;
                tmp += s.charAt(i);
                i--;
            } else if(s.charAt(i) == '?') {
                tmp += 0;
                i--;
                if(rightPart) {
                    razryad[currentRazr++] = -digit;
                } else {
                    razryad[currentRazr++] = digit;
                }

                digit++;
            }

        }

    public static Double str2double(String strNum) {
        Double res = 0;

    }

        // HashSet<Character> signs = new HashSet<>(Arrays.asList('+', '='));
        // List<String> parts = new List<String>(); 
        // parts = 
        // Double[] data = new Double[9];
        // String res = "";
        // String tmp = "";
        // boolean rightPart = true;
        // int i = 0,
        //     length = s.length(),
        //     qI = 0,
        //     dI = 0;
        // while(i < length){
        //     c = 0;
        //     if(s.charAt(i) == '=') rightPart = false;
        //     while (!signs.contain(s.charAt(i)) || i != length) { 
        //             if
        //             data[dI+1] += 1; // сколько в текущем числе знаков ?
        //             tmp += 0;
        //             c++;
        //         } else if(Character.isDigit(s.charAt(i)) || s.charAt(i) == '.') {
        //                 tmp += s.charAt(i);
        //         } else {
        //             System.out.println("Не правильный символ в выражении: " + s.charAt(i));
        //             System.exit(0);
        //         }            
        //         if(data[dI+1]) {
        //             data[dI+2] = i - c;
        //         }
        //         data[dI] =       
        //         i++;
        //     }
        //     if(s.charAt(i) == '=') 


        }

                result.add(res);
                res="";
        result.add(res);return result;
    }

public static

}
