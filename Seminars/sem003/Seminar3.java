//На шахматной доске расставить 8 ферзей так, чтобы они не били друг друга.
//«Заполнить матрицу размером 8 х 8 нулями и единицами 
//таким образом, чтобы сумма всех элементов матрицы была равна 8, 
//при этом сумма элементов ни в одном столбце, строке или диагональном ряде матрицы не превышала единицы».

public class Seminar3 {
  public static void main(String[] args) {
    PrintDesk(Posit());

  }

  public static void Ferz(int[][] Arr) {
    // boolean f = true;

    for (int i = 0; i < Arr.length; i++) {
      for (int j = 0; j < Arr[0].length; j++) {
        if (Row(Arr, i) && Col(Arr, j) && Diag1(Arr, i, j) && Diag2(Arr, i, j)) {
          Arr[i][j] = 1;
        }
      }
    }
    // for (int i = Arr.length - 1; i >= 0; i--) {
    //   for (int j = Arr[0].length - 1; j >= 0; j--) {
    //     if (Row(Arr, i) && Col(Arr, j) && Diag1(Arr, i, j) && Diag2(Arr, i, j)) {
    //       Arr[i][j] = 1;
    //     }
    //   }
    // }
  }

  public static int[][] Posit() {
    int[][] Arr = new int[8][8];
    for (int i = 0; i < Arr.length; i++) {
      for (int j = 0; j < Arr[0].length; j++) {
        Arr[i][j] = 1;
        Ferz(Arr);
        if (MatrSum(Arr) == 8)
          return Arr;
        Arr = new int[8][8];
      }
    }
    return Arr;
  }

  public static int MatrSum(int[][] Arr) {
    int sum = 0;
    for (int i = 0; i < Arr.length; i++) {
      for (int j = 0; j < Arr[i].length; j++) {
        sum += Arr[i][j];
      }
    }
    return sum;
  }

  public static boolean Diag2(int[][] arr, int r, int c) {
    int count = 0;
    int j = c;
    for (int i = r; i < arr.length; i++) {
      if (j < 0)
        break;
      count += arr[i][j];
      // System.out.printf("%d %d, ",i,j);
      j--;
    }
    // System.out.println();
    if (count > 0)
      return false;
    j = c;
    for (int i = r; i >= 0; i--) {
      if (j >= arr[0].length)
        break;
      count += arr[i][j];
      // System.out.printf("%d %d, ",i,j);
      if (j >= 0) {
        j++;
      } else
        break;
    }
    // System.out.println();
    if (count > 0)
      return false;
    return true;
  }

  public static boolean Row(int[][] arr, int r) {
    boolean f = true;
    int count = 0;
    for (int i = 0; i < arr[r].length; i++) {
      count += arr[r][i];
    }
    if (count > 0)
      f = false;
    return f;
  }

  public static boolean Col(int[][] arr, int c) {
    boolean f = true;
    int count = 0;
    for (int i = 0; i < arr.length; i++) {
      count += arr[i][c];
    }
    if (count > 0)
      f = false;
    return f;
  }

  public static boolean Diag1(int[][] arr, int r, int c) {
    int count = 0;
    int j = c;
    for (int i = r; i < arr.length; i++) {
      if (j >= arr[0].length)
        break;
      count += arr[i][j];
      // System.out.printf("%d %d, ",i,j);
      j++;
    }
    // System.out.println();
    if (count > 0)
      return false;
    j = c;
    for (int i = r; i >= 0; i--) {
      if (j < 0)
        break;
      count += arr[i][j];
      // System.out.printf("%d %d, ",i,j);
      if (j >= 0) {
        j--;
      } else
        break;
    }
    // System.out.println();
    if (count > 0)
      return false;
    return true;
  }

  public static void PrintDesk(int[][] arr) {
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[0].length; j++) {
        System.out.printf("%d  ", arr[i][j]);
      }
      System.out.println();
    }
  }
}