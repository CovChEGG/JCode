package HomeWork3;

public class horseRec {

    public static boolean startRec(int size) {
        if(size < 5) {
            System.out.println("No solutions for N < 5!!! ");
            return false;
        }
        else if(size > 8) {
            System.out.println("N > 8 is too big for recursion method!!!");
            return false;
        }
        int[] rowMovings = { +1, +2, +2, +1, -1, -2, -2, -1 };
        int[] columnMovings = { +2, +1, -1, -2, -2, -1, +1, 2 };
        int[][] desk = new int[size][size];
        int rFirst = 0,
            cFirst = 0;
        int[] current = { rFirst, cFirst};
        horseGo(desk, size, current, rowMovings, columnMovings, 0);
        return true;
    }

    public static int horseGo(int[][] desk, int size, int current[],
                                int[] rowMoving, int[] columnMoving, int step) {
        desk[current[0]][current[1]] = ++step;
        for(int variant = 0; variant < 8 && step < size*size; variant++) {
            int[] newMove = { current[0] + rowMoving[variant],
                              current[1] + columnMoving[variant],
                              step } ;
            if(checking(desk, size, newMove)) {
                if(horseGo(desk, size, newMove, rowMoving, columnMoving, step) == size*size) {
                    PrintDesk(desk, size);
                    System.exit(1);
                }
                desk[newMove[0]][newMove[1]] = 0;
            }
        }
        return step;
    }

    public static boolean checking(int[][] desk, int size, int[] newMove) {
        if (newMove[0] >= 0 &&
                newMove[0] < size &&
                newMove[1] >= 0 &&
                newMove[1] < size &&
                desk[newMove[0]][newMove[1]] == 0)
            return true;
        return false;
    }

    public static void PrintDesk(int[][] desk, int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.printf("%4d ", desk[i][j]);
            }
            System.out.println();
            System.out.println();
        }
        System.out.println();
    }
}
