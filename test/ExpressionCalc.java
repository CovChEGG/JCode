// Задано уравнение вида q + w = e, q, w, e >= 0. Некоторые цифры могут быть заменены знаком вопроса,
// например 2? + ?5 = 69. Требуется восстановить выражение до верного равенства.
//  Предложить хотя бы одно решение или сообщить, что его нет.

public class ExpressionCalc {
  public static void main(String[] args) {
      String strExpr = "9? + ??5 = ?93";
      System.out.println(strExpr);
      RestoreExpression re = new RestoreExpression(strExpr);

      System.out.println(re.isResultExpr());
  }
}