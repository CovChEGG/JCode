/*
 Задано уравнение вида q + w = e, q, w, e >= 0.
 Некоторые цифры могут быть заменены знаком вопроса, например 2? + ?5 = 69.
 Требуется восстановить выражение до верного равенства.
 Предложить хотя бы одно решение или сообщить, что его нет.
 */


public class FillQuestions {
    public static void main(String[] args) {
        // String exp = "1??+?45=3?6";
        // String exp = "1??+?45=326";
        // String exp = "2? + ?5 = 69";
        // String exp = "2? + ?5 = ??";
        // String exp = "2? + ?5 = 69";
        // String exp = "2? + ?5 = 19";
        // String exp = "2? + ?5 = 119";
        String exp = "?7? + ?1 = 41?";
        Restoration reExp = new Restoration(exp);
        System.out.println(reExp.GetRes() + "\n");
    }
}
