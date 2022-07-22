public class Ex000_object {
   public static void main(String[] args) {
      Object o = 1;
      GetType(o); // java.lang.Integer
      o = 1.2;
      GetType(o); // java.lang.Double
      ArrayCop();
   }

   static void GetType(Object obj) {
      System.out.println(obj.getClass().getName());
   }

   public static void ArrayCop() {
      int[] a = new int[] { 1, 9 };
      int[] b = new int[3];
      System.arraycopy(a, 0, b, 0, a.length);

      for (int i : a) { System.out.printf("%d ", i);} // 1 9
      System.out.println();
      for (int j : b) { System.out.printf("%d ", j);} // 0 9 0 0 0 0 0 0 0 0
      System.out.println();
   } 
}