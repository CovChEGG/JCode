import java.util.PriorityQueue;

public class Ex000_PriorityQueue {
    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        pq.add(123);
        pq.add(3);
        pq.add(13);
        pq.add(1);
        System.out.println(pq); // [1, 3, 13, 123]
        System.out.println(pq.poll()); // извлекаем (удаляем) - 1
        System.out.println(pq.poll()); // 3
        System.out.println(pq.poll()); // 13 
        System.out.println(pq.poll()); // 123
        System.out.println(pq.poll()); // - если очередь пуста - то null     
    }
}

