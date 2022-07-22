
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/* На вход некоторому исполнителю подаётся два числа (a, b). У исполнителя есть две команды
- команда 1 (к1): увеличить в с раза, а умножается на c
- команда 2 (к2): увеличить на d ( +2 ), к a прибавляется d
написать программу, которая выдаёт набор команд, позволяющий число a превратить в число b или сообщить, что это невозможно
Пример 1: а = 1, b = 7, c = 2, d = 1
ответ: к1, к1, к1, к1, к1, к1 или к1, к2, к1, к1, к1 или к1, к1, к2, к1. 
Пример 2: а = 11, b = 7, c = 2, d = 1
ответ: нет решения. 
*Подумать над тем, как сделать минимальное количество команд */

public class Calc {
    public static void main(String[] args) throws IOException {
        int a = 1;
        int b = 7;
        int c = 2;
        int d = 1;
        int mm = max_mlt(a, b, c);
        Queue<String> res = new LinkedList<>();
        //Приоритет умножения - k1
        for (int i = mm; i >= 0; i--) {
            int tmp = a;
            for (int j = 0; j<i; j++) {
                res.add("k1");
                tmp*=c;
            }
            //System.out.println(tmp);
            if (tmp==b){
                for (String item : res) {
                    System.out.printf("%s ",item);
                }
                System.out.println();
                res.clear();
                break;
            }
            int k = max_plus(tmp, b, d);
            for (int j = 0; j < k; j++) {
                res.add("k2");
                tmp+=d;
            }
            //System.out.println(tmp);
            if(tmp==b){
                for (String item : res) {
                    System.out.printf("%s ",item);
                }
                System.out.println();
                res.clear();
            }
        }
        //Приоритет сложения - k2
        int mp = max_plus(a, b, d);
        for (int i = 1; i < mp; i++) {
            int tmp = a;
            for (int j = 0; j<i; j++) {
                res.add("k2");
                tmp+=d;
            }
            //res.add("k2");
            //tmp+=d;
            int k1=max_mlt(tmp, b, c);
            if(k1==0) break;
            for (int j = 0; j<k1; j++) {
                res.add("k1");
                tmp*=c;
            }
            //System.out.println(tmp);
            if (tmp==b){
                for (String item : res) {
                    System.out.printf("%s ",item);
                }
                System.out.println();
                res.clear();
                break;
            }
            int k = max_plus(tmp, b, d);
            //if(k < 1) break;
            for (int j = 0; j < k; j++) {
                res.add("k2");
                tmp+=d;
            }
            //System.out.println(k);
            if(tmp==b){
                for (String item : res) {
                    System.out.printf("%s ",item);
                }
                System.out.println();
                res.clear();
            }
        }
    }
    private static int max_plus(int strt, int kon, int incr){
        int temp = strt;
        int count = 0;
        while(temp<kon){
            temp+=incr;
            count++;
        }
        return count;
    }
    private static int max_mlt(int strt, int kon, int incr){
        int temp = strt;
        int count = 0;
        do{
            temp*=incr;
            count++;
        }while(temp<kon);
        return count-1;
    }
}
