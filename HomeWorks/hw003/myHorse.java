package HomeWork3;

public class myHorse {

    public static boolean start(int n) {
        int[] max = { n, n };
        int[][] desk = new int[max[0]][max[1]];
        int elements = max[0] * max[1];
        int tmp = -1;
        for(int i = 0; i < max[0]; i++) {
            for(int j = 0; j < max[1]; j++) {
                desk = new int[max[0]][max[1]];
                int[] current = { i, j, 0};
                moveto(desk, max, current, current);
                desk[i][j] = current[2];
                while (current[2] != tmp) {
                    tmp = current[2];
                    moveRight(desk, max, current);
                    moveDown(desk, max, current);
                    moveLeft(desk, max, current);
                    moveUp(desk, max, current); 
                }
                if (current[2] == elements) {
                    PrintDesk(desk, max[0]);
                    return true;
                }
            }
        }
        return false;
    }

    public static void moveRight(int[][] desk, int[] max, int[] current){
        boolean move = true;
        while(move) {
            if (rightUp(desk, max, current)) move = true;
            else move = false;
            if (rightDown(desk, max, current)) move = true;
            else move = false;
        }
    }

    public static void moveDown(int[][] desk, int[] max, int[] current) {
        boolean move = true;
        while (move) {
            if (downRight(desk, max, current)) move = true;
            else move = false;
            if (downLeft(desk, max, current)) move = true;
            else move = false;
        }
    }

    public static void moveLeft(int[][] desk, int[] max, int[] current) {
        boolean move = true;
        while (move) {
            if (leftDown(desk, max, current)) move = true;
            else move = false;
            if (leftUp(desk, max, current)) move = true;
            else move = false;
        }
    }
    

    public static void moveUp(int[][] desk, int[] max, int[] current) {
        boolean move = true;
        while (move) {
            if (upLeft(desk, max, current)) move = true;
            else move = false;
            if (upRight(desk, max, current)) move = true;
            else move = false;
        }
    }

    public static boolean leftUp(int[][] desk, int[] max, int[] current) {
        int[] newMove = { current[0] - 1, current[1] - 2 };
        if (checking(desk, max, newMove)) {
            moveto(desk, max, current, newMove);
            return true;
        }
        return false;
    }

    public static boolean upLeft(int[][] desk, int[] max, int[] current) {
        int[] newMove = { current[0] - 2, current[1] - 1 };
        if (checking(desk, max, newMove)) {
            moveto(desk, max, current, newMove);
            return true;
        }
        return false;
    }

    public static boolean upRight(int[][] desk, int[] max, int[] current) {
        int[] newMove = { current[0] - 2, current[1] + 1 };
        if (checking(desk, max, newMove)) {
            moveto(desk, max, current, newMove);
            return true;
        }
        return false;
    }

    public static boolean rightUp(int[][] desk, int[] max, int[] current) {
        int[] newMove = { current[0] - 1, current[1] + 2 };
        if (checking(desk, max, newMove)) {
            moveto(desk, max, current, newMove);
            return true;
        }
        return false;
    }

    public static boolean rightDown(int[][] desk, int[] max, int[] current) {
        int[] newMove = { current[0] + 1, current[1] + 2 };
        if (checking(desk, max, newMove)) {
            moveto(desk, max, current, newMove);
            return true;
        }
        return false;
    }

    public static boolean downRight(int[][] desk, int[] max, int[] current) {
        int[] newMove = { current[0] + 2, current[1] + 1 };
        if (checking(desk, max, newMove)) {
            moveto(desk, max, current, newMove);
            return true;
        }
        return false;
    }

    public static boolean downLeft(int[][] desk, int[] max, int[] current) {
        int[] newMove = { current[0] + 2, current[1] - 1 };
        if (checking(desk, max, newMove)) {
            moveto(desk, max, current, newMove);
            return true;
        }
        return false;
    }

    public static boolean leftDown(int[][] desk, int[] max, int[] current) {
        int[] newMove = { current[0] + 1, current[1] - 2 };
        if (checking(desk, max, newMove)) {
            moveto(desk, max, current, newMove);
            return true;
        }
        return false;
    }

    public static boolean checking(int[][] desk, int[] max, int[] newMove) {
        if (newMove[0] >= 0 &&
                newMove[0] < max[0] &&
                newMove[1] >= 0 &&
                newMove[1] < max[1] &&
                desk[newMove[0]][newMove[1]] == 0)
            return true;
        return false;
    }

    public static void moveto(int[][] desk, int[] max, int[] current, int[] newMove) {
        current[0] = newMove[0];
        current[1] = newMove[1];
        current[2] += 1;
        desk[current[0]][current[1]] = current[2];
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


