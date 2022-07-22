/**
 * “+Написать программу,
 * показывающую последовательность
 * действий для игры “Ханойская башня”
 * Три штыря, N колец
 */
public class Tower {

    public static void main(String[] args) {
        int[] twFrom = { 1, 2, 3, 4 };
        int[] twTmp = { 0, 0, 0, 0 };
        int[] twTo = { 0, 0, 0, 0 };
        prTW(twFrom, twTmp, twTo);

        playHanoi(twFrom.length, "A", "B", "C");
    }

    public static void prTW(int[] twFrom, int[] twTmp, int[] twTo) {
        int high = twFrom.length;
        for (int i = 0; i < high; i++) {
                System.out.printf(" %d %d %d ", twFrom[i], twTmp[i], twTo[i]);
                System.out.println();
        }
                   
    }

    private static void playHanoi(int n, int[] from, int[] other, int[] to) {
        if (n == 0)
            return;
        if (n > 0)
            playHanoi(n - 1, from, to, other);
        
        System.out.printf("Move one disk from pole %s to pole %s \n ", from, to);
        playHanoi(n - 1, other, from, to);
    }

    // public static int[][] move(int[][] tw,) {

    // while (sumOfColumns(tw, 0) != 0) {

    // }
    // else if ()[];

    // return tw;
    // }

    // public static int calcSumOfColumns(int[][] tw, int j) {
    // int sum = 0;
    // for (i = 0; i < tw.length; i++) {
    // sum += tw[i][j];
    // }
    // return sum;
    // }
}