/*
 Задано уравнение вида q + w = e, q, w, e >= 0.
 Некоторые цифры могут быть заменены знаком вопроса, например 2? + ?5 = 69.
 Требуется восстановить выражение до верного равенства.
 Предложить хотя бы одно решение или сообщить, что его нет.
 */


public class FillQuestions {
    public static void main(String[] args) {
        String exp = "1??+?45=3?6";
        // String exp = "1??+?45=326";
        Restoration reExp = new Restoration(exp);
        System.out.println(reExp.GetRes() + "\n");
    }
}
