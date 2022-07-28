import java.util.ArrayDeque;
import java.util.Scanner;

public class wave {
    public static void main(String[] args) {
        
        int x=10,    //  раземеры карты
            y=20;
        int obstacles = 5; // (от 0 до 9) - относительное количество препядствий           
        int[] startEnd = {0, 0, 0, 0}; // массив для передачи начальных и конечных координат

        int[][] map = new int[x][y]; // инициализация карты

        Scanner userInput = new Scanner(System.in);
        fillTheMap(map, obstacles); // заполнение карты начальными условиями
        PrintMap(map, "Заполенная карта препядствиями (-2) и начальной (1) и конечной (-1) точками");
        userInput.nextLine();
        
        startEnd = findMarkers(map, startEnd); // определение координат начала и конца
        if(waveS(map, startEnd)) { // запуск волны и печать результата
            userInput.nextLine();
            wayBack(map, startEnd); // если путь найден - вывести о нём информацию
        }; 
        userInput.nextLine();
        // PrintMap(map);
        userInput.close();
    }

    public static void wayBack (int[][]map, int[] startEnd) {
        int maxX = map.length;
        int maxY = map[0].length;
        ArrayDeque<Integer> qX = new ArrayDeque<>();
        ArrayDeque<Integer> qY = new ArrayDeque<>();
        int sX = startEnd[0];
        int sY = startEnd[1];
        int eX = startEnd[2];
        int eY = startEnd[3];
        qX.add(eX);
        qY.add(eY);
        map[eX][eY] *= -1;
        int[] moveX = {-1, 0, 1 ,0}; // окресность фон Неймана
        int[] moveY = {0, 1, 0, -1};
        int cX = eX;
        int cY = eY;
        int cN = map[eX][eY];
        map[eX][eY] *= 10;
        int x = 0;
        int y = 0;
        int n = 0;
        boolean notStart = true;
        while(notStart) {
            for(int i = 0; i <4 ; i++) {  
                x = cX - moveX[i];
                y = cY - moveY[i];
                n = cN - 1;
                if(x >= 0 && x < maxX && y >= 0 && y < maxY && (map[x][y] == n)) {
                    map[x][y] *= 10;
                    cX = x;
                    cY = y;
                    cN = n;
                    qX.addFirst(cX);
                    qY.addFirst(cY);
                    if (x == sX && y == sY) notStart = false;
                    break;
                }
            }
        }
        StringBuilder way = new StringBuilder();
        while(!qX.isEmpty()) way.append("(" + qX.pollFirst() + ", " + qY.pollFirst() + ")");
        PrintMap(map, "Путь на карте умножен на 10, координаты движения: \n" + way);
    }

    public static void fillTheMap(int[][] map, int obs) {
        if(obs>9 || obs < 0) {
            System.out.println("Неправильный коэффициент препядствий! Он может быть от 0 до 9");
            System.exit(0);
        }
        int maxX = map.length;
        int maxY = map[0].length;
        int x, y;
        int obSnum = maxX * maxY * obs / 10;
        int i = 0;
        while (i <= (obSnum + 1)) {
            x = (int) (Math.random() * maxX);
            y = (int) (Math.random() * maxY);
            if(map[x][y] == 0) {
                if(i < obSnum) map[x][y] = -2; // установка препядствий
                else if(i == obSnum) map[x][y] = 1; // установка начала
                else map[x][y] = -1; // установка конца
                i++;
            }
        }
    }

    public static int[] findMarkers(int[][] map, int[] startEnd) {
        int maxX = map.length;
        int maxY = map[0].length;
        boolean startFinded = false, endFinded = false;
        for (int i = 0; i < maxX; i++) {
            for (int j = 0; j < maxY; j++) {
                if(map[i][j]==1) {
                    startEnd[0]=i; startEnd[1]=j;
                    startFinded=true;
                } else if(map[i][j]==-1) {
                    startEnd[2]=i; startEnd[3]=j;
                    endFinded=true;
                }
                if(startFinded && endFinded) return startEnd;
            }
        }
        if(!startFinded && !endFinded) System.out.println("Не заданы начало('1') и конец('-1')!");
        else if(!startFinded) System.out.println("Не задано начало символом '1'!");
        else System.out.println("Не задан конец символами '-1'!");
        return startEnd;
    }

    public static boolean waveS (int[][] map, int[] startEnd) {
        
        ArrayDeque<Integer> qX = new ArrayDeque<>();
        ArrayDeque<Integer> qY = new ArrayDeque<>();
        ArrayDeque<Integer> qN = new ArrayDeque<>();
        int maxX = map.length;
        int maxY = map[0].length;
        int sX = startEnd[0];
        int sY = startEnd[1];
        int eX = startEnd[2];
        int eY = startEnd[3];
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
            cY = qY.pop();
            cN = qN.pop();
            for(int i = 0; i <4 ; i++) { // маркировка окресности если есть возможность 
                x = cX + moveX[i];
                y = cY + moveY[i];
                n = cN + 1;
                if(x >= 0 && x < maxX && y >= 0 && y < maxY && (map[x][y] == 0 || map[x][y] == -1)) {
                    if (x == eX && y == eY) { // проверка на искомую координату
                    // if (map[x][y] == -1) { // проверка на конечный маркер - "-1"
                        map[x][y] = map[x][y] * n;
                        PrintMap(map, "На " + n + " волне найден маркер " + map[x][y]);
                        finded = true;
                        return finded;
                    }
                    else map[x][y] = n;
                    qX.addLast(x);
                    qY.addLast(y);
                    qN.addLast(n);
                } 
            }          
        }
        PrintMap(map, "Волна не дошла до конечной точки :-(");
        return false;
    }

    public static void PrintMap(int[][] map, String text) {
        System.out.print("\033[H\033[J");
        System.out.println(text + "\n\n");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.printf("%3d ", map[i][j]);
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Нажмите 'Enter' чтобы продолжить");
    }
}
