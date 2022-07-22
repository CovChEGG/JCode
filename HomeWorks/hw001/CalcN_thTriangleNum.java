import java.util.Scanner;

public class CalcN_thTriangleNum {

    public static void main(String[] args) {
        
        int n = InputN();
        long res = CalcTriangleNum(n);
        System.out.printf("Triangle number of %d is %d", n, res);
    }

    private static long CalcTriangleNum(int n) {
        long l = (long)n;
        return l*(l + 1)/2;
    }

    public static int InputN() {
        Scanner inputN = new Scanner(System.in);
        System.out.printf("Please, input number N: ");
        while(!inputN.hasNextInt()) {
                System.out.println("Wrong input, try again!!!");
                System.out.printf("input number N: ");
                inputN = new Scanner(System.in);
            }
        int n = inputN.nextInt();
        inputN.close();
        return n;
        }
}