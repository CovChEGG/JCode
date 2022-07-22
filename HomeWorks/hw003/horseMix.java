// Шахматную доску размером NxN обойти конём так,
// чтобы фигура в каждой клетке была строго один раз.

package HomeWork3;
import java.util.Scanner;

public class horseMix {
    public static void main(String[] args) {
        
        menu();
    }
    
    public static int InputN(String text) {
        Scanner inputN = new Scanner(System.in);
        print(text, 0);
        while(!inputN.hasNextInt()) {
                print("Wrong input, try again!!!", 1);
                print("input number N: ", 0);
                inputN = new Scanner(System.in);
            }
        int n = inputN.nextInt();
        inputN.close();
        return n;
        }

    public static void print(String text, int param) {
        switch (param) {
            case 0:
                System.out.printf(text);
                break;
            case 1:
                System.out.println(text);
                break;
            default:
                print("Wrong print parameter!", 1);
                break;
        }

    }

    public static void menu() {
        System.out.print("\033[H\033[J");
        int n = InputN("Please, input size N: ");
        if (n < 5) print("No solutions for desks < 5", 0);
        else if (n < 7) {
            print("Using myHorse method\n", 1);
            myHorse.start(n);
        }
        else if (n < 9) {
            print("Using recursion method\n", 1);
            horseRec.startRec(n);
        }
        else {
            print("The recursive method is not efficient for large boards.", 1);
            print("We will use myHorse method.\n", 1);
            if(!myHorse.start(n)) {
                print("Unfortunately - no solutions yet!!!... trying to find variant for biger board:\n",1);
                while(!myHorse.start(n++));
                System.out.printf("Finded solution for N = %d \n\n", n);
            }
        }
    }
}