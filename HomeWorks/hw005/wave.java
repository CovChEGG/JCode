import java.util.ArrayDeque;

public class wave {
    public static void main(String[] args) {
        int x=5,    //  раземеры карты
            y=10;
        int[][] map = new int[x][y];
    int sX = 3,     // начальные условия
        sY = 2,
        eX = x-1,
        eY = y-2;
    map[sX][sY] = 1; // маркер начала
    map[eX][eY] = -1; // маркер конца

    PrintMap(map);
    waveS(sX, sY, eX, eY, map);
    PrintMap(map); 
    }

    public static void waveS (int sX, int sY, int eX, int eY, int[][] map) {
        
        ArrayDeque<Integer> qX = new ArrayDeque<>();
        ArrayDeque<Integer> qY = new ArrayDeque<>();
        ArrayDeque<Integer> qN = new ArrayDeque<>();
        int maxX = map.length;
        int maxY = map[0].length;
        qX.add(sX);
        qY.add(sY);
        qN.add(map[sX][sY]);

        int[] moveX = {-1, 0, 1 ,0}; // окресность фон Неймана
        int[] moveY = {0, 1, 0, -1};
        int cX = sX;
        int cY = sY;
        int cN = 0;        
        int x = 0;
        int y = 0;
        int n = 0;
        boolean finded = false;

        while(!finded && !qX.isEmpty()) {
            // берем текущий элемент из очереди
                cX = qX.pop();
                // System.out.println(cX + " " + x);
                cY = qY.pop();
                cN = qN.pop();

            for(int i = 0; i <4 ; i++) { // маркировка окресности если есть возможность 
                x = cX + moveX[i];
                y = cY + moveY[i];
                n = cN + 1;
                // System.out.println(x + ' ' + y + ' ' + n);
                if(x >= 0 && x < maxX && y >= 0 && y < maxY && (map[x][y] == 0 || map[x][y] == -1)) {
                    // if (x == eX && y == eY) { // проверка на искомую координату
                    if (map[x][y] == -1) { // проверка на конечный маркер - "-1"
                        map[x][y] = map[x][y] * n;
                        System.out.println("На " + n + " волне найден маркер\n");
                        finded = true;
                        break;
                    }
                    else map[x][y] = n;
                    qX.addLast(x);
                    qY.addLast(y);
                    qN.addLast(n);
                    
                } 
            }          
        }
    }

    public static void PrintMap(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.printf("%4d ", map[i][j]);
            }
            System.out.println();
            System.out.println();
        }
        System.out.println();
    }
}
