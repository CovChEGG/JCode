// package Lesson_04;

import java.util.LinkedList;
import java.util.Queue;

public class Ex002_Queue {
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        System.out.println(queue); // [1, 2, 3, 4]
        int removedItem = queue.remove();
        boolean removeSucceeded = queue.remove(7); // удалеят если есть указанный элемент и возвращает bool
        System.out.println(removeSucceeded);
        System.out.println(queue); // [2, 3, 4]
        System.out.println(removedItem);
        boolean inserted = queue.offer(2809); // добавляет новый элемент если это возможно (доступ)
        removedItem = queue.remove();
        System.out.println(queue); // [3, 4, 2809]
        removedItem = queue.remove();
        System.out.println(queue); // [4, 2809]
        removedItem = queue.remove();
        System.out.println(queue); // [2809]
        System.out.println(queue.peek());
        
        queue.remove(2809); // зачем очередь?? - если его нет - то ничего страшного!
        // queue.element();    // возвращает значение первого элемента очереди
                               //- если его нет - выбрасывет "java.util.NoSuchElementException"
        int head = queue.peek()!=null ? queue.peek() : 0;  // Возвращает "null" - если очередь пуста, либо первый элемент
        System.out.println(head);
    }
}
