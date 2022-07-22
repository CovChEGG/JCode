import java.util.*;

// Написать программу, определяющую правильность расстановки скобок в выражении.
// Пример 1: a+(d*3) - истина
// Пример 2: [a+(1*3) - ложь
// Пример 3: [6+(3*3)] - истина
// Пример 4: {a}[+]{(d*3)} - истина
// Пример 5: <{a}+{(d*3)}> - истина
// Пример 6: {a+]}{(d*3)} - ложь


public class brackets {
    public static void main(String[] args) {
        String s1 = "a+(d*3)";
        System.out.println(check(s1));
    }
    

    public static boolean check(String s) {
        Stack<Integer> st = new Stack<>(); // ()
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(' ) { st.push((int)s.charAt(i) + 1) };
            if(s.charAt(i) == '[' ) st.push((int)s.charAt(i) + 1);
            if(s.charAt(i) == '{' ) st.push((int)s.charAt(i) + 2);
            if(s.charAt(i) == '<' ) st.push((int)s.charAt(i) + 2);
            if( s.charAt(i) == ')' ||
                s.charAt(i) == ']' || 
                s.charAt(i) == '}' ||
                s.charAt(i) == '>' ) {
                    if(st.empty()) return false;
                    else {
                        if (st.peek() == (int)s.charAt(i)) {
                            st.pop();
                        } 
                        else return false;
                    }
                }
        }
        if(st.empty()) return true;
        return false;
    }
}
