import java.util.Scanner;
public class p45 {
   public static void main(String[] args) {
       Scanner iScanner = new Scanner(System.in);
       System.out.printf("int a: ");
       boolean flag = iScanner.hasNextInt();
//       System.out.println(flag);       
       int i = iScanner.nextInt();
//       System.out.println(i);
//       System.out.printf("int b: ");
//       flag = iScanner.hasNextInt();
       System.out.println(flag);       
       int k = iScanner.nextInt();
    //    System.out.println(k);       
       iScanner.close();
   } 
}
