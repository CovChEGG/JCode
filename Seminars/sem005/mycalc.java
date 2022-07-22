/* 
На вход некоторому исполнителю подаётся два числа (a, b). У исполнителя есть две команды
- команда 1 (к1): увеличить в с раза, а умножается на c
- команда 2 (к2): увеличить на d ( +2 ), к a прибавляется d
написать программу, которая выдаёт набор команд, позволяющий число a превратить в число b
или сообщить, что это невозможно
Пример 1: а = 1, b = 7, c = 2, d = 1
ответ: к1, к1, к1, к1, к1, к1 или к1, к2, к1, к1, к1 или к1, к1, к2, к1. 
Пример 2: а = 11, b = 7, c = 2, d = 1
ответ: нет решения. 
*Подумать над тем, как сделать минимальное количество команд
*/

import java.util.Queue;
import java.util.Scanner;

public class mycalc {

    public static void main(String[] args) {
        // int a = InputN("Введите число a: ");
        // System.out.println(a);
        // int b = InputN("Введите число b: ");
        // System.out.println(b);
        int a = 1,
            b = 7,
            c = 2,
            d = 1;
        Queue<Integer> k1 = new LinkedList<Integer>();
        Queue<Integer> k2 = new LinkedList<Integer>();
        int tmp1 = 0;
        int tmp2 = 0;
        while(tmp1<b) {
            tmp1 += d;
            k1.add(tmp1);
        }
        while(tmp2<b) {
            tmp2 *= c;
            k2.add(tmp2);
        }
        int res = 0;
        while(res != b) {
            
        }

    }

    // public private int InputN(String text) {
    //     Scanner inputN = new Scanner(System.in);
    //     System.out.printf(text);
    //     while(!inputN.hasNextInt()) {
    //             System.out.println("Wrong input, try again!!!");
    //             System.out.printf(text);
    //             inputN = new Scanner(System.in);
    //         }
    //     int n = inputN.nextInt();
    //     inputN.close();
    //     return n;
    // }
  
}