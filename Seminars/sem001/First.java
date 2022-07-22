/** 
Реализовать функцию возведения числа а в степень b. a, b ∈ Z. Сводя количество выполняемых
действий к минимуму. 
Пример 1: а = 3, b = 2, ответ: 9 
Пример 2: а = 2, b = -2, ответ: 0.25
Пример 3: а = 3, b = 0, ответ: 1
Пример 4: а = 0, b = 0, ответ: не определено
Пример 5
входные данные находятся в файле input.txt в виде
b 3
a 10
Результат нужно сохранить в файле output.txt
1000
*/
import java.io.*;
public class First {
    public static void main(String[] args) {
        double[] ab = readF();
        double a = ab[0];
        double b = ab[1];
        double res = func(a, b);
        
        if (res == 0) {
            System.out.println("не определено");
        } 
        else {
            saveF(res);
            System.out.println(res);
        }
    }
    public static double[] readF() {
        String st_b = "";
        String st_a = "";
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            st_b = br.readLine().strip();
            st_a = br.readLine().strip();
            System.out.printf("== %s == %s\n", st_b, st_a);
            br.close();
            String[] m_a = st_a.split(" ");
            String[] m_b = st_b.split(" ");
            double a = Double.parseDouble(m_a[1]);
            double b = Double.parseDouble(m_b[1]);
            return new double[] {a, b};
        }
        catch (IOException ex) {
           System.out.println(ex.getMessage());
        }
        return new double[] {0, 0};
    }
    public static double func(double a, double b) {
        double tmp = 1;
        if (a == 0 && b == 0) return 0;
        if (b == 0) return tmp;
        if (b > 0) {
            for(int i = 0; i < b; i++) {
                    tmp *= a;
            }
        }
        if (b < 0) {
            for(int i = 0; i < -b; i++) {
                        tmp *= a;
            }
            tmp = 1/tmp;
        }
        return tmp;   
    }
    public static void saveF(double res) {
        String str_result = Double.toString(res);
        int int_res = (int)res;
        if ((res - int_res) > 0) {
        }
        else {
            str_result = Integer.toString(int_res);
        }
        
        try (FileWriter fw = new FileWriter("output.txt", true)) {
            fw.write(str_result);
            fw.append('\n');
            fw.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
