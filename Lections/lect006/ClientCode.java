import java.util.Arrays;
import java.util.HashSet;

public class ClientCode {
    public static void main(String[] args) {
        Worker w1 = new Worker(); // Вызов конструктора
        w1.firstName = "Имя_1";
        w1.lastName = "Фамилия_1";
        w1.salary = 100;
        w1.id = 1000;

        Worker w4 = new Worker();
        w4.firstName = "Имя_1";
        w4.lastName = "Фамилия_1";
        w4.salary = 100;
        w4.id = 1000;

        Worker w2 = new Worker();
        w2.firstName = "Имя_2";
        w2.lastName = "2";
        w2.salary = 200;
        w2.id = 2000;
        
        Worker w3 = new Worker();
        w3.firstName = "Имя_3";
        w3.lastName = "3";
        w3.salary = 300;
        w3.id = 3000;

        System.out.print("\033[H\033[J");
        System.out.println(w1); // Worker@7ad041f3
        System.out.println(w2); // Worker@251a69d7
        System.out.println(w3); // Worker@7344699f
        //#String.format("id: %d\nFirst name: %s\nLast name %s\nSalary: %d", id, firstName, lastName, salary);
        System.out.println(w1.toString()); // Worker@7ad041f3
        System.out.println(w2.toString()); // Worker@251a69d7
        System.out.println(w3.toString()); // Worker@7344699f
        
                
        System.out.println( w1 == w4 );
        System.out.println(w1.equals(w4));
        var workers = new HashSet<Worker>(Arrays.asList(w1, w2, w3));
        System.out.println(workers.contains(w4));
    }
}
